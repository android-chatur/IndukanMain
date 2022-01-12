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
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.geogreenapps.apps.indukaan.AppController;
import com.geogreenapps.apps.indukaan.GPS.GPStracker;
import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.activities.ProductDetailActivity;
import com.geogreenapps.apps.indukaan.adapter.lists.ProductListAdapter;
import com.geogreenapps.apps.indukaan.appconfig.Constances;
import com.geogreenapps.apps.indukaan.classes.Product;
import com.geogreenapps.apps.indukaan.controllers.ErrorsController;
import com.geogreenapps.apps.indukaan.controllers.SettingsController;
import com.geogreenapps.apps.indukaan.controllers.stores.ProductsController;
import com.geogreenapps.apps.indukaan.customview.OfferCustomView;
import com.geogreenapps.apps.indukaan.load_manager.ViewManager;
import com.geogreenapps.apps.indukaan.network.ServiceHandler;
import com.geogreenapps.apps.indukaan.network.VolleySingleton;
import com.geogreenapps.apps.indukaan.network.api_request.SimpleRequest;
import com.geogreenapps.apps.indukaan.parser.api_parser.ProductParser;
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

import butterknife.ButterKnife;
import io.realm.RealmList;

import static com.geogreenapps.apps.indukaan.R.layout.fragment_product_list_new;
import static com.geogreenapps.apps.indukaan.appconfig.AppConfig.APP_DEBUG;

public class ListProductsFragment extends Fragment
        implements ProductListAdapter.ClickListener, SwipeRefreshLayout.OnRefreshListener, ViewManager.CustomView, View.OnClickListener {


    private final List<Product> listStores = new ArrayList<>();
    private final String custom_filter = Constances.OrderByFilter.RECENT;
    public ViewManager mViewManager;
    //loading
    // public SwipeRefreshLayout swipeRefreshLayout;
    //  ShimmerRecyclerView shimmerRecycler;
    //for scrolling params
    SpacesItemDecoration decoration;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private RecyclerView list_new;
    private ProductListAdapter adapter;
    //init request http
    private RequestQueue queue;
    private boolean loading = true;
    //pager
    private View rootview;
    private int COUNT = 0;
    private int REQUEST_PAGE = 1;
    private GPStracker mGPS;
    private int REQUEST_RANGE_RADIUS = -1;
    private String REQUEST_SEARCH = "";
    private int REQUEST_CATEGORY = -1;
    private LatLng LOCATION = null;
    private String current_date;
    private int store_id, category_id = 0;
    private HashMap<String, Object> searchParams;

    // newInstance constructor for creating fragment with arguments
    public static ListProductsFragment newInstance(int page, String title) {
        ListProductsFragment fragmentFirst = new ListProductsFragment();
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

        menu.findItem(R.id.list_view_icon).setVisible(true);
        menu.findItem(R.id.search_icon).setVisible(true);
        menu.findItem(R.id.notification_action).setVisible(false);
        menu.findItem(R.id.qr_scanner).setVisible(false);

        super.onPrepareOptionsMenu(menu);
        updateBadges();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.list_view_icon) {

            final StaggeredGridLayoutManager  mLayoutManager;
            if (Utils.listViewFormat("list_view_format_products") == 1) {

                mLayoutManager = new  StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

                item.setIcon(R.drawable.ic_view_list_default);
                Utils.setListViewFormat("list_view_format_products", 2);
            } else {
               // mLayoutManager = new GridLayoutManager(getActivity(), 1);
                mLayoutManager = new  StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

                item.setIcon(R.drawable.ic_list_view_grid);
                Utils.setListViewFormat("list_view_format_products", 1);
            }

            list_new.setLayoutManager(mLayoutManager);

        }
        return super.onOptionsItemSelected(item);
    }

    private void handleExtraParams() {
        try {
            store_id = getArguments().getInt("store_id");
            category_id = getArguments().getInt("category");

            if (getArguments().containsKey("searchParams")) {
                searchParams = (HashMap<String, Object>) getArguments().getSerializable("searchParams");
            }

        } catch (Exception e) {
            store_id = 0;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(fragment_product_list_new, container, false);
        rootview = rootView.getRootView();
        ButterKnife.bind(this, rootview);

        //initialize the shimmer : recyclerview loader
        //  shimmerRecycler = rootView.findViewById(R.id.shimmer_rv_products);

        handleExtraParams();


        mGPS = new GPStracker(getActivity());
        queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();

        setupViewManager(rootView);
        initOfferRV();


        setupRecyclerViewAdapter(rootView);


        //setupRefreshLayout(rootView);


        if (ServiceHandler.isNetworkAvailable(this.getActivity())) {
            getProducts(REQUEST_PAGE);

        } else {

            //swipeRefreshLayout.setRefreshing(false);
            Toast.makeText(getActivity(), getString(R.string.check_network), Toast.LENGTH_LONG).show();
            if (adapter.getItemCount() == 0)
                mViewManager.error();
        }


        return rootView;
    }

    private void initOfferRV() {
        OfferCustomView mOfferCustomView = rootview.findViewById(R.id.horizontalOfferList);
        if (!SettingsController.isModuleEnabled(Constances.ModulesConfig.OFFER_MODULE)) {
            mOfferCustomView.hide();
        } else {
            mOfferCustomView.loadData(false, new HashMap<>());
            mOfferCustomView.show();
        }

    }


    /*private void setupRefreshLayout(View rootView) {
        swipeRefreshLayout = rootView.findViewById(R.id.refresh);

        swipeRefreshLayout.setOnRefreshListener(this);


        swipeRefreshLayout.setColorSchemeResources(
                R.color.colorAccent,
                R.color.colorAccent,
                R.color.colorAccent,
                R.color.colorAccent
        );
    }*/

    private void setupRecyclerViewAdapter(View rootView) {
        list_new = rootView.findViewById(R.id.list_new);
        decoration = new SpacesItemDecoration(8);

        adapter = new ProductListAdapter(getActivity(), listStores, false);
        list_new.addItemDecoration(decoration);
        adapter.setClickListener(this);

        list_new.setHasFixedSize(true);

      //  final RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), Utils.listViewFormat("list_view_format_products"));
        final StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        list_new.setLayoutManager(mLayoutManager);
        list_new.setItemAnimator(new DefaultItemAnimator());

        list_new.setAdapter(adapter);
        // list.addItemDecoration(decoration);


        list_new.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                visibleItemCount = mLayoutManager.getChildCount();
                totalItemCount = mLayoutManager.getItemCount();
                  final RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), Utils.listViewFormat("list_view_format_products"));

                pastVisiblesItems = ((GridLayoutManager) mLayoutManager).findFirstVisibleItemPosition();
                if (loading) {

                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        loading = false;
                        if (ServiceHandler.isNetworkAvailable(getContext())) {
                            if (COUNT > adapter.getItemCount())
                                getProducts(REQUEST_PAGE);
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

        Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
        intent.putExtra("product_id", adapter.getItem(position).getId());
        startActivity(intent);

    }

    public void getProducts(final int page) {

        // swipeRefreshLayout.setRefreshing(true);
        mGPS = new GPStracker(getActivity());

        //if (adapter.getItemCount() == 0)
        // shimmerRecycler.showShimmerAdapter();

        SimpleRequest request = new SimpleRequest(Request.Method.POST,
                Constances.API.API_GET_PRODUCTS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    if (APP_DEBUG) {
                        Log.e("responseProductsString", response);
                    }

                    JSONObject jsonObject = new JSONObject(response);


                    final ProductParser mProductParser = new ProductParser(jsonObject);
                    COUNT = 0;
                    COUNT = mProductParser.getIntArg(Tags.COUNT);
                    //mViewManager.showResult();


                    //check server permission and display the errors
                    if (mProductParser.getSuccess() == -1) {
                        ErrorsController.serverPermissionError(getActivity());
                    }

                    if (page == 1) adapter.removeAll();

                    RealmList<Product> list = mProductParser.getProducts();

                    //remove all items from adapter before listing new items


                    for (int i = 0; i < list.size(); i++) {
                        Product ofr = list.get(i);
                        if (mGPS.getLongitude() == 0 && mGPS.getLatitude() == 0) {
                            ofr.setDistance((double) 0);
                        }
                        // if (list.get(i).getDistance() <= REQUEST_RANGE_RADIUS)
                        adapter.addItem(ofr);
                    }

                    ProductsController.removeAll();
                    //set it into database
                    ProductsController.insertProducts(list);

                    loading = true;

                    if (COUNT > adapter.getItemCount())
                        REQUEST_PAGE++;

                    if (COUNT == 0 || adapter.getItemCount() == 0) {
                        mViewManager.empty();
                    } else {
                        mViewManager.showResult();
                    }

                    //hide shimmer RV
                    //shimmerRecycler.hideShimmerAdapter();
                    // swipeRefreshLayout.setRefreshing(false);


                    if (APP_DEBUG) {
                        Log.e("count ", COUNT + " page = " + page);
                    }


                } catch (JSONException e) {
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
                //shimmerRecycler.hideShimmerAdapter();

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
                    else if (category_id != 0) params.put("category_id", category_id + "");


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
                    Log.e("ListProductFragment", "  params getProducts :" + params.toString());
                }


                return params;
            }

        };


        request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
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


        getProducts(1);
        //swipeRefreshLayout.setRefreshing(true);


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

                mViewManager.loading();
                REQUEST_SEARCH = "";
                REQUEST_RANGE_RADIUS = -1;
                REQUEST_CATEGORY = -1;
                LOCATION = null;
                REQUEST_PAGE = 1;

                getProducts(REQUEST_PAGE);

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


                getProducts(1);
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


        if (Utils.listViewFormat("list_view_format_products") == 1) {
            menu.findItem(R.id.list_view_icon).setIcon(R.drawable.ic_list_view_grid);

        } else {
            menu.findItem(R.id.list_view_icon).setIcon(R.drawable.ic_view_list_default);
        }
    }

    @Override
    public void onClick(View view) {

    }
}
