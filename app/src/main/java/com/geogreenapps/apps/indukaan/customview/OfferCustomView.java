package com.geogreenapps.apps.indukaan.customview;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.geogreenapps.apps.indukaan.AppController;
import com.geogreenapps.apps.indukaan.GPS.GPStracker;
import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.activities.OfferDetailActivity;
import com.geogreenapps.apps.indukaan.activities.OffersListActivity;
import com.geogreenapps.apps.indukaan.adapter.lists.OfferListAdapter;
import com.geogreenapps.apps.indukaan.animation.Animation;
import com.geogreenapps.apps.indukaan.appconfig.Constances;
import com.geogreenapps.apps.indukaan.classes.Category;
import com.geogreenapps.apps.indukaan.classes.Offer;
import com.geogreenapps.apps.indukaan.controllers.categories.CategoryController;
import com.geogreenapps.apps.indukaan.controllers.stores.OffersController;
import com.geogreenapps.apps.indukaan.network.ServiceHandler;
import com.geogreenapps.apps.indukaan.network.VolleySingleton;
import com.geogreenapps.apps.indukaan.network.api_request.SimpleRequest;
import com.geogreenapps.apps.indukaan.parser.api_parser.OfferParser;
import com.geogreenapps.apps.indukaan.parser.tags.Tags;
import com.geogreenapps.apps.indukaan.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import io.realm.RealmList;

import static com.geogreenapps.apps.indukaan.appconfig.AppConfig.APP_DEBUG;

public class OfferCustomView extends HorizontalView implements OfferListAdapter.ClickListener {

    private final Context mContext;
    private OfferListAdapter adapter;
    private RecyclerView listView;
    private Map<String, Object> optionalParams;

    private View offerContainer;
    private ShimmerRecyclerView shimmerRecycler;
    private TextView showMore;

    public OfferCustomView(Context context) {
        super(context);
        mContext = context;
        setRecyclerViewAdapter();
    }

    public OfferCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;


        setCustomAttribute(context, attrs);

        setRecyclerViewAdapter();

    }


    public void loadData(boolean fromDatabase, Map<String, Object> optionalParams) {

        shimmerRecycler.showShimmerAdapter();
        listView.setVisibility(GONE);

        if (!fromDatabase) loadDataFromAPi(optionalParams);
    }

    public List<Category> getData() {

        List<Category> results = new ArrayList<>();

        RealmList<Category> listCats = CategoryController.list();

        for (Category cat : listCats) {
            if (cat.getNumCat() > 0)
                results.add(cat);
        }

        return results;
    }


    private void loadDataFromAPi(Map<String, Object> additionalParams) {


        RequestQueue queue = VolleySingleton.getInstance(mContext).getRequestQueue();

        GPStracker mGPS = new GPStracker(mContext);


        SimpleRequest request = new SimpleRequest(Request.Method.POST,
                Constances.API.API_GET_OFFERS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    if (APP_DEBUG) {
                        Log.e("responseOfferString", response);
                    }

                    JSONObject jsonObject = new JSONObject(response);


                    final OfferParser mOfferParser = new OfferParser(jsonObject);


                    RealmList<Offer> list = mOfferParser.getOffers();

                    //set into the database
                    OffersController.insertOrUpdateOffers(list);


                    adapter.removeAll();
                    for (int i = 0; i < list.size(); i++) {
                        Offer ofr = list.get(i);
                        if (mGPS.getLongitude() == 0 && mGPS.getLatitude() == 0) {
                            ofr.setDistance((double) 0);
                        }
                        adapter.addItem(ofr);
                    }

                    //hide the custom event view when the there's no event on the adapter
                    if (adapter.getItemCount() == 0) {
                        offerContainer.setVisibility(GONE);
                    } else {
                        offerContainer.setVisibility(VISIBLE);
                        listView.setVisibility(VISIBLE);
                    }


                    String limit_param = optionalParams != null && optionalParams.containsKey("ocvLimit") ? String.valueOf(optionalParams.get("ocvLimit")) : "30";
                    int limit = Integer.parseInt(limit_param);

                    if (limit < mOfferParser.getIntArg(Tags.COUNT)) {
                        Animation.startZoomEffect(showMore.findViewById(R.id.card_show_more));
                    }


                } catch (JSONException e) {
                    //send a rapport to support
                    if (APP_DEBUG)
                        e.printStackTrace();
                }
                shimmerRecycler.hideShimmerAdapter();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (APP_DEBUG) {
                    Log.e("ERROR", error.toString());
                }
                shimmerRecycler.hideShimmerAdapter();

            }

        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                if (mGPS.canGetLocation()) {
                    params.put("latitude", mGPS.getLatitude() + "");
                    params.put("longitude", mGPS.getLongitude() + "");
                }

                params.put("token", Utils.getToken(AppController.getInstance()));
                params.put("mac_adr", ServiceHandler.getMacAddr());


                if (additionalParams != null && !additionalParams.isEmpty()) {
                    for (Map.Entry<String, Object> entry : additionalParams.entrySet()) {
                        params.put(entry.getKey(), String.valueOf(entry.getValue()));
                    }
                }


                params.put("limit", optionalParams != null && optionalParams.containsKey("ocvLimit") ? String.valueOf(optionalParams.get("ocvLimit")) : "30");
                params.put("page", 1 + "");
                params.put("timezone", TimeZone.getDefault().getID());
                params.put("order_by", Constances.OrderByFilter.NEARBY);

                if (APP_DEBUG) {
                    Log.e("OfferCustomView", "  params getOffer :" + params.toString());
                }

                return params;
            }

        };


        request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);

    }

    private void setRecyclerViewAdapter() {

        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_HORIZONTAL);

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.v2_horizontal_list_offer, this, true);

        offerContainer = getChildAt(0).findViewById(R.id.offer_container);
        //layout direction
        offerContainer.setLayoutDirection(AppController.isRTL() ? View.LAYOUT_DIRECTION_RTL : View.LAYOUT_DIRECTION_LTR);
        // hide container until getting data from api
        offerContainer.setVisibility(GONE);


        if ((Boolean) optionalParams.get("displayHeader")) {
            offerContainer.findViewById(R.id.layoutOfferHeader).setVisibility(VISIBLE);
        } else {
            offerContainer.findViewById(R.id.layoutOfferHeader).setVisibility(GONE);
        }

        shimmerRecycler = getChildAt(0).findViewById(R.id.shimmer_view_container);


        if ((Boolean) optionalParams.get("loader")) {
            shimmerRecycler.showShimmerAdapter();
            shimmerRecycler.setVisibility(VISIBLE);
        } else {
            shimmerRecycler.hideShimmerAdapter();
            shimmerRecycler.setVisibility(GONE);

        }


        listView = getChildAt(0).findViewById(R.id.list);

        adapter = new OfferListAdapter(mContext, new ArrayList<>(), true, (Float) optionalParams.get("width"), (Float) optionalParams.get("height"));

        listView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        listView.setLayoutManager(mLayoutManager);
        listView.setAdapter(adapter);
        adapter.setClickListener(this);


        //setup show more view
        showMore = getChildAt(0).findViewById(R.id.card_show_more);
        Drawable arrowIcon = getResources().getDrawable(R.drawable.ic_arrow_forward);
        if (AppController.isRTL()) {
            arrowIcon = getResources().getDrawable(R.drawable.ic_arrow_back);
        }

        DrawableCompat.setTint(
                DrawableCompat.wrap(arrowIcon),
                ContextCompat.getColor(mContext, R.color.colorPrimary)
        );

        if (!AppController.isRTL()) {
            showMore.setCompoundDrawablesWithIntrinsicBounds(null, null, arrowIcon, null);
        } else {
            showMore.setCompoundDrawablesWithIntrinsicBounds(arrowIcon, null, null, null);
        }

        showMore.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, OffersListActivity.class);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public void itemClicked(View view, int position) {
        if (APP_DEBUG)
            Log.e("_1_PRODUCT_id", String.valueOf(adapter.getItemId(position)));

        Intent intent = new Intent(mContext, OfferDetailActivity.class);
        intent.putExtra("id", adapter.getItem(position).getId());
        mContext.startActivity(intent);
    }


    private void setCustomAttribute(Context context, @Nullable AttributeSet attrs) {

        optionalParams = new HashMap<>();
        //get the attributes specified in attrs.xml using the name we included
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.OfferCustomView, 0, 0);

        try {
            //get the text and colors specified using the names in attrs.xml
            optionalParams.put("ocvLimit", a.getInteger(R.styleable.OfferCustomView_ocvLimit, 0));
            optionalParams.put("displayHeader", a.getBoolean(R.styleable.OfferCustomView_ccDisplayHeader, false));
            optionalParams.put("height", a.getDimension(R.styleable.OfferCustomView_offerItemHeight, 0));
            optionalParams.put("width", a.getDimension(R.styleable.OfferCustomView_offerItemWidth, 0));
            optionalParams.put("loader", a.getBoolean(R.styleable.OfferCustomView_ocvLoader, true));
        } finally {
            a.recycle();
        }
    }

}
