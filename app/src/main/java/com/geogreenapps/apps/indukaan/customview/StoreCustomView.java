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
import com.geogreenapps.apps.indukaan.activities.StoreDetailActivity;
import com.geogreenapps.apps.indukaan.activities.StoresListActivity;
import com.geogreenapps.apps.indukaan.adapter.lists.StoreListAdapter;
import com.geogreenapps.apps.indukaan.animation.Animation;
import com.geogreenapps.apps.indukaan.appconfig.Constances;
import com.geogreenapps.apps.indukaan.classes.Store;
import com.geogreenapps.apps.indukaan.controllers.stores.StoreController;
import com.geogreenapps.apps.indukaan.network.ServiceHandler;
import com.geogreenapps.apps.indukaan.network.VolleySingleton;
import com.geogreenapps.apps.indukaan.network.api_request.SimpleRequest;
import com.geogreenapps.apps.indukaan.parser.api_parser.StoreParser;
import com.geogreenapps.apps.indukaan.parser.tags.Tags;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.realm.RealmList;

import static com.geogreenapps.apps.indukaan.appconfig.AppConfig.APP_DEBUG;


public class StoreCustomView extends HorizontalView implements StoreListAdapter.ClickListener {

    private final Context mContext;
    private StoreListAdapter adapter;
    private RecyclerView listView;
    private Map<String, Object> optionalParams;
    private ShimmerRecyclerView shimmerRecycler;
    private View storeContainer;
    private TextView showMore;


    public StoreCustomView(Context context) {
        super(context);
        mContext = context;
        setRecyclerViewAdapter();
    }

    public StoreCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        setCustomAttribute(context, attrs);

        setRecyclerViewAdapter();
    }


    public void loadData(boolean fromDatabase) {

        shimmerRecycler.showShimmerAdapter();
        listView.setVisibility(GONE);

        //OFFLINE MODE
        if (ServiceHandler.isNetworkAvailable(mContext)) {

            if (!fromDatabase) loadDataFromAPi(new HashMap<>());
            else loadDataFromDB();
        } else {
            loadDataFromDB();
        }

    }

    public void loadDataFromDB() {
        //ensure the data exist on the database if not load it from api
        RealmList<Store> list = StoreController.list();
        if (!list.isEmpty()) {
            adapter.clear();
            if (!list.isEmpty()) {
                adapter.addAllItems(list);
                listView.setVisibility(VISIBLE);
                storeContainer.setVisibility(VISIBLE);
                shimmerRecycler.hideShimmerAdapter();
            } else {
                listView.setVisibility(GONE);
                storeContainer.setVisibility(GONE);

            }
            adapter.notifyDataSetChanged();
        } else {
            loadDataFromAPi(new HashMap<>());
        }

    }


    private void loadDataFromAPi(Map<String, Object> additionalParams) {
        RequestQueue queue = VolleySingleton.getInstance(mContext).getRequestQueue();

        GPStracker mGPS = new GPStracker(mContext);


        SimpleRequest request = new SimpleRequest(Request.Method.POST,
                Constances.API.API_USER_GET_STORES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    if (APP_DEBUG) {
                        Log.e("responseStoresString", response);
                    }

                    JSONObject jsonObject = new JSONObject(response);

                    //Log.e("response",response);

                    final StoreParser mStoreParser = new StoreParser(jsonObject);
                    int success = Integer.parseInt(mStoreParser.getStringAttr(Tags.SUCCESS));
                    if (success == 1) {

                        RealmList<Store> list = mStoreParser.getStore();
                        adapter.removeAll();
                        for (int i = 0; i < list.size(); i++) {
                            Store sTr = list.get(i);
                            if (mGPS.getLatitude() == 0 && mGPS.getLongitude() == 0) {
                                sTr.setDistance((double) 0);
                            }
                            //if (list.get(i).getDistance() <=REQUEST_RANGE_RADIUS)
                            adapter.addItem(sTr);

                        }

                        //save data into database
                        if (list.size() > 0)
                            StoreController.insertStores(list);

                        if (adapter.getItemCount() > 0) {
                            storeContainer.setVisibility(VISIBLE);
                            listView.setVisibility(VISIBLE);
                        } else {
                            storeContainer.setVisibility(GONE);
                            listView.setVisibility(GONE);
                        }


                        adapter.notifyDataSetChanged();


                        String limit_param = optionalParams != null && optionalParams.containsKey("strLimit") ? String.valueOf(optionalParams.get("strLimit")) : "30";
                        int limit = Integer.parseInt(limit_param);

                        if (limit < mStoreParser.getIntArg(Tags.COUNT)) {
                            Animation.startZoomEffect(showMore.findViewById(R.id.card_show_more));
                        }
                    }

                } catch (JSONException e) {
                    //send a rapport to support
                    if (APP_DEBUG)
                        Log.e("ERROR", e.toString());

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

                if (additionalParams != null && !additionalParams.isEmpty()) {
                    for (Map.Entry<String, Object> entry : additionalParams.entrySet()) {
                        params.put(entry.getKey(), String.valueOf(entry.getValue()));
                    }
                }

                params.put("order_by", Constances.OrderByFilter.NEARBY);
                params.put("limit", String.valueOf(optionalParams.get("strLimit")));
                params.put("page", 1 + "");

                if (APP_DEBUG) {
                    Log.e("paramsStoresString", "  params getStores :" + params.toString());
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
        inflater.inflate(R.layout.v2_horizontal_list_store, this, true);


        //setup show more view
        storeContainer = getChildAt(0);

        //layout direction
        storeContainer.setLayoutDirection(AppController.isRTL() ? View.LAYOUT_DIRECTION_RTL : View.LAYOUT_DIRECTION_LTR);

        // hide container until getting data from api
        storeContainer.setVisibility(GONE);

        //header setup
        if (optionalParams.containsKey("header") && optionalParams.get("header") != null)
            ((TextView) storeContainer.findViewById(R.id.card_title)).setText((String) optionalParams.get("header"));

        //setup show more view
        showMore = storeContainer.findViewById(R.id.card_show_more);


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
                mContext.startActivity(new Intent(mContext, StoresListActivity.class));

            }
        });


        //list item setup
        listView = getChildAt(0).findViewById(R.id.list);
        adapter = new StoreListAdapter(mContext, new ArrayList<>(), true, (Float) optionalParams.get("width"), (Float) optionalParams.get("height"));
        //start showLoading shimmer effect
        shimmerRecycler = getChildAt(0).findViewById(R.id.shimmer_view_container);

        if ((Boolean) optionalParams.get("loader")) {
            shimmerRecycler.setVisibility(VISIBLE);
        } else {
            shimmerRecycler.hideShimmerAdapter();
        }


        if ((Boolean) optionalParams.get("displayHeader")) {
            storeContainer.findViewById(R.id.layoutStoreHeader).setVisibility(VISIBLE);
        } else {
            storeContainer.findViewById(R.id.layoutStoreHeader).setVisibility(GONE);
        }


        listView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        if (AppController.isRTL())
            mLayoutManager.setReverseLayout(true);

        listView.setLayoutManager(mLayoutManager);
        listView.setAdapter(adapter);
        adapter.setClickListener(this);


    }

    @Override
    public void itemClicked(View view, int position) {

        if (APP_DEBUG)
            Log.e("_1_store_id", String.valueOf(adapter.getItem(position).getId()));

        Intent intent = new Intent(mContext, StoreDetailActivity.class);
        intent.putExtra("id", adapter.getItem(position).getId());
        mContext.startActivity(intent);
    }


    private void setCustomAttribute(Context context, @Nullable AttributeSet attrs) {

        optionalParams = new HashMap<>();
        //get the attributes specified in attrs.xml using the name we included
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.StoreCustomView, 0, 0);

        try {
            //get the text and colors specified using the names in attrs.xml
            optionalParams.put("strLimit", a.getInteger(R.styleable.StoreCustomView_strLimit, 30));
            optionalParams.put("height", a.getDimension(R.styleable.StoreCustomView_storeItemHeight, 0));
            optionalParams.put("width", a.getDimension(R.styleable.StoreCustomView_storeItemWidth, 0));
            optionalParams.put("loader", a.getBoolean(R.styleable.StoreCustomView_strLoader, true));
            optionalParams.put("header", a.getString(R.styleable.StoreCustomView_strHeader));
            optionalParams.put("displayHeader", a.getBoolean(R.styleable.StoreCustomView_stpDisplayHeader, false));


        } finally {
            a.recycle();
        }
    }


}
