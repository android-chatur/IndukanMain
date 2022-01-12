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
import com.geogreenapps.apps.indukaan.activities.ProductDetailActivity;
import com.geogreenapps.apps.indukaan.activities.ProductsListActivity;
import com.geogreenapps.apps.indukaan.adapter.lists.ProductListAdapter;
import com.geogreenapps.apps.indukaan.animation.Animation;
import com.geogreenapps.apps.indukaan.appconfig.Constances;
import com.geogreenapps.apps.indukaan.classes.Category;
import com.geogreenapps.apps.indukaan.classes.Product;
import com.geogreenapps.apps.indukaan.controllers.categories.CategoryController;
import com.geogreenapps.apps.indukaan.network.ServiceHandler;
import com.geogreenapps.apps.indukaan.network.VolleySingleton;
import com.geogreenapps.apps.indukaan.network.api_request.SimpleRequest;
import com.geogreenapps.apps.indukaan.parser.api_parser.ProductParser;
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

public class ProductCustomView extends HorizontalView implements ProductListAdapter.ClickListener {

    private final Context mContext;
    private ProductListAdapter adapter;
    private RecyclerView listView;
    private Map<String, Object> optionalParams;
    private HashMap<String, Object> searchParams;
    private ShimmerRecyclerView shimmerRecycler;
    private View productContainer;
    private TextView showMore;


    public ProductCustomView(Context context) {
        super(context);
        mContext = context;
        setRecyclerViewAdapter();
    }

    public ProductCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;


        setCustomAttribute(context, attrs);

        setRecyclerViewAdapter();

    }


    public void loadData(boolean fromDatabase, HashMap<String, Object> _searchParams) {

        searchParams = _searchParams;

        if (!fromDatabase) loadDataFromAPi(searchParams);
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


        shimmerRecycler.showShimmerAdapter();
        listView.setVisibility(GONE);


        RequestQueue queue = VolleySingleton.getInstance(mContext).getRequestQueue();

        GPStracker mGPS = new GPStracker(mContext);
        SimpleRequest request = new SimpleRequest(Request.Method.POST,
                Constances.API.API_GET_PRODUCTS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    if (APP_DEBUG) {
                        Log.e("responseProductString", response);
                    }

                    JSONObject jsonObject = new JSONObject(response);


                    //Log.e("response",response);

                    final ProductParser mProductParser = new ProductParser(jsonObject);


                    RealmList<Product> list = mProductParser.getProducts();


                    adapter.removeAll();
                    for (int i = 0; i < list.size(); i++) {
                        Product ofr = list.get(i);
                        if (mGPS.getLongitude() == 0 && mGPS.getLatitude() == 0) {
                            ofr.setDistance((double) 0);
                        }
                        adapter.addItem(ofr);
                    }

                    //hide the custom event view when the there's no event on the adapter
                    if (adapter.getItemCount() == 0) {
                        productContainer.setVisibility(GONE);
                    } else {

                        productContainer.setVisibility(VISIBLE);
                        listView.setVisibility(VISIBLE);
                    }


                    String limit_param = optionalParams != null && optionalParams.containsKey("pcvLimit") ? String.valueOf(optionalParams.get("pcvLimit")) : "30";
                    int limit = Integer.parseInt(limit_param);

                    if (limit < mProductParser.getIntArg(Tags.COUNT)) {
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


                params.put("limit", optionalParams != null && optionalParams.containsKey("pcvLimit") ? String.valueOf(optionalParams.get("pcvLimit")) : "30");
                params.put("page", 1 + "");
                params.put("timezone", TimeZone.getDefault().getID());
                params.put("order_by", Constances.OrderByFilter.RECENT);


                if (APP_DEBUG) {
                    Log.e("ProductCustomView", "  params getProduct :" + params.toString());
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
        inflater.inflate(R.layout.v2_horizontal_list_product, this, true);

        //layout direction
        //setup show more view
        productContainer = getChildAt(0).findViewById(R.id.product_container);
        productContainer.setVisibility(GONE); // hide container until getting data from api

        //layout direction
        productContainer.setLayoutDirection(AppController.isRTL() ? View.LAYOUT_DIRECTION_RTL : View.LAYOUT_DIRECTION_LTR);


        if ((Boolean) optionalParams.get("displayHeader")) {
            productContainer.findViewById(R.id.layoutProductHeader).setVisibility(VISIBLE);
            if (optionalParams.get("header") != null)
                ((TextView) productContainer.findViewById(R.id.card_title)).setText((String) optionalParams.get("header"));

        } else {
            productContainer.findViewById(R.id.layoutProductHeader).setVisibility(GONE);
        }


        listView = getChildAt(0).findViewById(R.id.list);

        adapter = new ProductListAdapter(mContext, new ArrayList<>(), true, (Float) optionalParams.get("width"), (Float) optionalParams.get("height"));
        //start showLoading shimmer effect
        shimmerRecycler = getChildAt(0).findViewById(R.id.shimmer_view_container);

        if ((Boolean) optionalParams.get("loader")) {
            shimmerRecycler.showShimmerAdapter();
            shimmerRecycler.setVisibility(VISIBLE);
        } else {
            shimmerRecycler.hideShimmerAdapter();
            shimmerRecycler.setVisibility(GONE);

        }


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
                Intent intent = new Intent(mContext, ProductsListActivity.class);
                intent.putExtra("searchParams", searchParams);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public void itemClicked(View view, int position) {
        if (APP_DEBUG)
            Log.e("_1_PRODUCT_id", String.valueOf(adapter.getItemId(position)));

        Intent intent = new Intent(mContext, ProductDetailActivity.class);
        intent.putExtra("id", adapter.getItem(position).getId());
        mContext.startActivity(intent);
    }


    private void setCustomAttribute(Context context, @Nullable AttributeSet attrs) {

        optionalParams = new HashMap<>();
        //get the attributes specified in attrs.xml using the name we included
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ProductCustomView, 0, 0);

        try {
            //get the text and colors specified using the names in attrs.xml
            optionalParams.put("pcvLimit", a.getInteger(R.styleable.ProductCustomView_pcvLimit, 0));
            optionalParams.put("header", a.getString(R.styleable.ProductCustomView_pcvHeader));
            optionalParams.put("displayHeader", a.getBoolean(R.styleable.ProductCustomView_pcDisplayHeader, false));
            optionalParams.put("height", a.getDimension(R.styleable.ProductCustomView_productItemHeight, 0));
            optionalParams.put("width", a.getDimension(R.styleable.ProductCustomView_productItemWidth, 0));
            optionalParams.put("loader", a.getBoolean(R.styleable.ProductCustomView_pcvLoader, true));

        } finally {
            a.recycle();
        }
    }

}
