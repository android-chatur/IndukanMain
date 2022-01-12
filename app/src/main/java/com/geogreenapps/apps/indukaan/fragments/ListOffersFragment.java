package com.geogreenapps.apps.indukaan.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
import com.geogreenapps.apps.indukaan.adapter.lists.OfferListAdapter;
import com.geogreenapps.apps.indukaan.appconfig.Constances;
import com.geogreenapps.apps.indukaan.classes.Offer;
import com.geogreenapps.apps.indukaan.controllers.ErrorsController;
import com.geogreenapps.apps.indukaan.controllers.stores.OffersController;
import com.geogreenapps.apps.indukaan.load_manager.ViewManager;
import com.geogreenapps.apps.indukaan.network.ServiceHandler;
import com.geogreenapps.apps.indukaan.network.VolleySingleton;
import com.geogreenapps.apps.indukaan.network.api_request.SimpleRequest;
import com.geogreenapps.apps.indukaan.parser.api_parser.OfferParser;
import com.geogreenapps.apps.indukaan.parser.tags.Tags;
import com.geogreenapps.apps.indukaan.utils.BadgeNotificationUtils;
import com.geogreenapps.apps.indukaan.utils.DateUtils;
import com.geogreenapps.apps.indukaan.utils.Utils;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import io.realm.RealmList;

import static com.geogreenapps.apps.indukaan.R.layout.fragment_product_list;
import static com.geogreenapps.apps.indukaan.appconfig.AppConfig.APP_DEBUG;

public class ListOffersFragment extends Fragment
        implements OfferListAdapter.ClickListener, SwipeRefreshLayout.OnRefreshListener, ViewManager.CustomView, View.OnClickListener {


    public ViewManager mViewManager;
    //loading
    public SwipeRefreshLayout swipeRefreshLayout;
    //for scrolling params
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private RecyclerView list;
    private OfferListAdapter adapter;
    //init request http
    private RequestQueue queue;
    private boolean loading = true;
    //pager
    private int COUNT = 0;
    private int REQUEST_PAGE = 1;
    private GPStracker mGPS;
    private final List<Offer> listOffers = new ArrayList<>();
    private int REQUEST_RANGE_RADIUS = -1;
    private String REQUEST_SEARCH = "";
    private int REQUEST_CATEGORY = -1;
    private LatLng LOCATION = null;
    private HashMap<String, Object> searchParams;


    private String current_date;

    private String custom_filter = Constances.OrderByFilter.RECENT;

    private int store_id = 0;

    ShimmerRecyclerView shimmerRecycler;

    // newInstance constructor for creating fragment with arguments
    public static ListOffersFragment newInstance(int page, String title) {
        ListOffersFragment fragmentFirst = new ListOffersFragment();
        Bundle args = new Bundle();
        args.putInt("id", page);
        args.putString("title", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        REQUEST_RANGE_RADIUS = Integer.parseInt(getResources().getString(R.string.DISTANCE_MAX_DISPLAY_ROUTE));

        current_date = DateUtils.getUTC("yyyy-MM-dd H:m:s");

    }


    private void updateBadges() {
        BadgeNotificationUtils.updateMessengerBadge(getActivity());
        BadgeNotificationUtils.updateNotificationBadge(getActivity());
        BadgeNotificationUtils.updateCartItemsBadge(getActivity());
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {

        menu.findItem(R.id.list_view_icon).setVisible(false);
        menu.findItem(R.id.search_icon).setVisible(true);
        menu.findItem(R.id.notification_action).setVisible(false);
        menu.findItem(R.id.qr_scanner).setVisible(false);

        super.onPrepareOptionsMenu(menu);
        updateBadges();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.list_view_icon) {

            final RecyclerView.LayoutManager mLayoutManager;
            if (Utils.listViewFormat("list_view_format_offers") == 1) {
                mLayoutManager = new GridLayoutManager(getActivity(), 2);
                item.setIcon(R.drawable.ic_view_list_default);
                Utils.setListViewFormat("list_view_format_offers", 2);
            } else {
                mLayoutManager = new GridLayoutManager(getActivity(), 1);
                item.setIcon(R.drawable.ic_list_view_grid);
                Utils.setListViewFormat("list_view_format_offers", 1);
            }
            list.setLayoutManager(mLayoutManager);


        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(fragment_product_list, container, false);

        //setupFilterView(rootView);

        //initialize the shimmer : recyclerview loader
        shimmerRecycler = rootView.findViewById(R.id.shimmer_rv_products);

        handleExtraParams();


        mGPS = new GPStracker(getActivity());
        queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();

        setupViewManager(rootView);

        setupRecyclerViewAdapter(rootView);

        setupRefreshLayout(rootView);


        if (ServiceHandler.isNetworkAvailable(this.getActivity())) {
            getOffers(REQUEST_PAGE);

        } else {

            swipeRefreshLayout.setRefreshing(false);
            Toast.makeText(getActivity(), getString(R.string.check_network), Toast.LENGTH_LONG).show();
            if (adapter.getItemCount() == 0)
                mViewManager.error();
        }


        return rootView;
    }

    /*private void setupFilterView(View rootView) {
        FilterView filterView = FilterUtils.filter_setup(rootView);
        filterView.setEnabledLeft();
        filterView.setClickListener(new FilterView.ClickListener() {
            @Override
            public void leftClicked(FilterView f) {
                custom_filter = Constances.OrderByFilter.RECENT;
                REQUEST_PAGE = 1;
                getOffers(REQUEST_PAGE);
            }

            @Override
            public void rightClicked(FilterView f) {
                if (!mGPS.canGetLocation()) {
                    mGPS.showSettingsAlert();
                } else {
                    custom_filter = Constances.OrderByFilter.NEARBY;
                    REQUEST_PAGE = 1;
                    getOffers(REQUEST_PAGE);
                }
            }
        });
    }*/

    private void handleExtraParams() {
        try {
            store_id = getArguments().getInt("store_id");
            if (getArguments().containsKey("searchParams")) {
                searchParams = (HashMap<String, Object>) getArguments().getSerializable("searchParams");
            }

        } catch (Exception e) {
            store_id = 0;
        }
    }

    private void setupRefreshLayout(View rootView) {
        swipeRefreshLayout = rootView.findViewById(R.id.refresh);

        swipeRefreshLayout.setOnRefreshListener(this);


        swipeRefreshLayout.setColorSchemeResources(
                R.color.colorAccent,
                R.color.colorAccent,
                R.color.colorAccent,
                R.color.colorAccent
        );
    }

    private void setupRecyclerViewAdapter(View rootView) {
        list = rootView.findViewById(R.id.list);

        adapter = new OfferListAdapter(getActivity(), listOffers, false);
        adapter.setClickListener(this);

        list.setHasFixedSize(true);

        final RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), Utils.listViewFormat("list_view_format_offers"));
        list.setLayoutManager(mLayoutManager);
        list.setItemAnimator(new DefaultItemAnimator());
        list.setAdapter(adapter);


        list.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                visibleItemCount = mLayoutManager.getChildCount();
                totalItemCount = mLayoutManager.getItemCount();
                pastVisiblesItems = ((GridLayoutManager) mLayoutManager).findFirstVisibleItemPosition();
                if (loading) {

                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        loading = false;
                        if (ServiceHandler.isNetworkAvailable(getContext())) {
                            if (COUNT > adapter.getItemCount())
                                getOffers(REQUEST_PAGE);
                        } else {
                            Toast.makeText(getContext(), "Network not available ", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);


            }
        });
    }

    private void setupViewManager(View rootView) {
        mViewManager = new ViewManager(getActivity());
        mViewManager.setLoadingLayout(rootView.findViewById(R.id.loading));
        mViewManager.setResultLayout(rootView.findViewById(R.id.content_my_store));
        mViewManager.setErrorLayout(rootView.findViewById(R.id.error));
        mViewManager.setEmpty(rootView.findViewById(R.id.empty));
        mViewManager.setCustumizeView(this);
    }

    @Override
    public void itemClicked(View view, int position) {

        Intent intent = new Intent(getActivity(), OfferDetailActivity.class);
        intent.putExtra("offer_id", adapter.getItem(position).getId());
        startActivity(intent);

    }

    public void getOffers(final int page) {

        swipeRefreshLayout.setRefreshing(true);


        mGPS = new GPStracker(getActivity());

        //if (adapter.getItemCount() == 0)
        shimmerRecycler.showShimmerAdapter();

        SimpleRequest request = new SimpleRequest(Request.Method.POST,
                Constances.API.API_GET_OFFERS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    if (APP_DEBUG) {
                        Log.e("responseOffersString", response);
                    }

                    JSONObject jsonObject = new JSONObject(response);

                    //Log.e("response",response);

                    final OfferParser mOffersParser = new OfferParser(jsonObject);
                    // List<Store> list = mStoreParser.getEventRealm();
                    COUNT = 0;
                    COUNT = mOffersParser.getIntArg(Tags.COUNT);
                    //mViewManager.showResult();


                    //check server permission and display the errors
                    if (mOffersParser.getSuccess() == -1) {
                        ErrorsController.serverPermissionError(getActivity());
                    }

                    if (page == 1)  //remove all items from adapter before listing new items
                        adapter.removeAll();


                    RealmList<Offer> list = mOffersParser.getOffers();

                    for (int i = 0; i < list.size(); i++) {
                        Offer ofr = list.get(i);
                        if (mGPS.getLongitude() == 0 && mGPS.getLatitude() == 0) {
                            ofr.setDistance((double) 0);
                        }
                        // if (list.get(i).getDistance() <= REQUEST_RANGE_RADIUS)
                        adapter.addItem(ofr);
                    }

                    //set it into database
                    OffersController.removeAll();
                    OffersController.insertOrUpdateOffers(list);

                    loading = true;

                    if (COUNT > adapter.getItemCount())
                        REQUEST_PAGE++;

                    if (COUNT == 0 || adapter.getItemCount() == 0) {
                        mViewManager.empty();
                    } else {
                        mViewManager.showResult();
                    }

                    //hide shimmer RV
                    shimmerRecycler.hideShimmerAdapter();
                    swipeRefreshLayout.setRefreshing(false);


                    if (APP_DEBUG) {
                        Log.e("count ", COUNT + " page = " + page);
                    }

                } catch (
                        JSONException e) {
                    //send a rapport to support
                    if (APP_DEBUG)
                        e.printStackTrace();

                    if (adapter.getItemCount() == 0)
                        mViewManager.error();


                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (APP_DEBUG) {
                    Log.e("ERROR", error.toString());
                }

                //hide shimmer RV
                shimmerRecycler.hideShimmerAdapter();

                mViewManager.error();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                if (LOCATION != null) {
                    params.put("latitude", LOCATION.latitude + "");
                    params.put("longitude", LOCATION.longitude + "");
                    //order by geo
                    params.put("order_by", Constances.OrderByFilter.NEARBY);
                } else if (mGPS.canGetLocation()) {
                    params.put("latitude", mGPS.getLatitude() + "");
                    params.put("longitude", mGPS.getLongitude() + "");
                    //order by geo
                    params.put("order_by", Constances.OrderByFilter.NEARBY);
                } else {
                    //order by date
                    params.put("order_by", Constances.OrderByFilter.RECENT);
                }

                if (searchParams != null && !searchParams.isEmpty()) {

                    //fetch all params retrieved from search frag
                    for (Map.Entry<String, Object> entry : searchParams.entrySet()) {
                        params.put(entry.getKey(), String.valueOf(entry.getValue()));
                    }

                } else {

                    //set custom filter
                    if (custom_filter != null)
                        params.put("order_by", custom_filter);


                    if (REQUEST_CATEGORY > -1) params.put("category_id", REQUEST_CATEGORY + "");


                    if (store_id > 0)
                        params.put("store_id", String.valueOf(store_id));


                    if (REQUEST_RANGE_RADIUS != -1) {
                        if (REQUEST_RANGE_RADIUS <= 99)
                            params.put("radius", String.valueOf((REQUEST_RANGE_RADIUS * 1024)));
                    }

                    params.put("search", REQUEST_SEARCH);

                }

                params.put("token", Utils.getToken(AppController.getInstance()));
                params.put("mac_adr", ServiceHandler.getMacAddr());
                params.put("limit", "30");
                params.put("page", page + "");
                params.put("date", current_date);
                params.put("timezone", TimeZone.getDefault().getID());


                if (APP_DEBUG) {
                    Log.e("ListOfferFragment", "  params getOffers :" + params.toString());
                }


                return params;
            }

        };


        request.setRetryPolicy(new

                DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);

    }

    @Override
    public void onRefresh() {

        REQUEST_SEARCH = "";
        REQUEST_PAGE = 1;
        REQUEST_RANGE_RADIUS = -1;
        REQUEST_CATEGORY = -1;
        LOCATION = null;
        getOffers(1);
        swipeRefreshLayout.setRefreshing(true);


    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void customErrorView(View v) {

        Button retry = v.findViewById(R.id.btn);

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mGPS = new GPStracker(getActivity());

                /*if (!mGPS.canGetLocation() && listType == 1)
                    mGPS.showSettingsAlert();*/
                mViewManager.loading();


                REQUEST_SEARCH = "";
                REQUEST_RANGE_RADIUS = -1;
                REQUEST_CATEGORY = -1;
                LOCATION = null;
                REQUEST_PAGE = 1;

                getOffers(REQUEST_PAGE);

            }
        });

    }

    @Override
    public void customLoadingView(View v) {


    }

    @Override
    public void customEmptyView(View v) {

        Button btn = v.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewManager.loading();

                REQUEST_SEARCH = "";
                REQUEST_RANGE_RADIUS = -1;
                REQUEST_CATEGORY = -1;
                LOCATION = null;
                REQUEST_PAGE = 1;


                getOffers(1);
                REQUEST_PAGE = 1;
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (menu != null) {
            menu.clear();
            inflater.inflate(R.menu.home_menu, menu);
        }
        super.onCreateOptionsMenu(menu, inflater);


        if (Utils.listViewFormat("list_view_format_offers") == 1) {
            menu.findItem(R.id.list_view_icon).setIcon(R.drawable.ic_list_view_grid);

        } else {
            menu.findItem(R.id.list_view_icon).setIcon(R.drawable.ic_view_list_default);
        }
    }

    @Override
    public void onClick(View view) {

    }
}
