package com.geogreenapps.apps.indukaan.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.geogreenapps.apps.indukaan.GPS.GPStracker;
import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.activities.StoreDetailActivity;
import com.geogreenapps.apps.indukaan.adapter.lists.StoreListAdapter;
import com.geogreenapps.apps.indukaan.appconfig.Constances;
import com.geogreenapps.apps.indukaan.classes.Category;
import com.geogreenapps.apps.indukaan.classes.Store;
import com.geogreenapps.apps.indukaan.controllers.ErrorsController;
import com.geogreenapps.apps.indukaan.controllers.stores.StoreController;
import com.geogreenapps.apps.indukaan.load_manager.ViewManager;
import com.geogreenapps.apps.indukaan.network.ServiceHandler;
import com.geogreenapps.apps.indukaan.network.VolleySingleton;
import com.geogreenapps.apps.indukaan.network.api_request.SimpleRequest;
import com.geogreenapps.apps.indukaan.parser.api_parser.StoreParser;
import com.geogreenapps.apps.indukaan.parser.tags.Tags;
import com.geogreenapps.apps.indukaan.utils.BadgeNotificationUtils;
import com.geogreenapps.apps.indukaan.utils.DateUtils;
import com.geogreenapps.apps.indukaan.utils.Utils;
import com.google.android.gms.maps.model.LatLng;
import com.google.zxing.Result;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Logger;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import io.realm.Realm;
import io.realm.RealmList;
import me.dm7.barcodescanner.zxing.ZXingScannerView;


import static android.content.Context.WINDOW_SERVICE;
import static com.geogreenapps.apps.indukaan.appconfig.AppConfig.APP_DEBUG;

public class ListStoresFragment extends Fragment
        implements ZXingScannerView.ResultHandler,StoreListAdapter.ClickListener, SwipeRefreshLayout.OnRefreshListener, ViewManager.CustomView {

    public ViewManager mViewManager;
    //loading
    public SwipeRefreshLayout swipeRefreshLayout;
    //for scrolling params
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    LinearLayoutManager mLayoutManager;
    private final int listType = 1;
    private RecyclerView list;
    Dialog myDialog;
    private StoreListAdapter adapter;
    //init request http
    private RequestQueue queue;
    private boolean loading = true;
    //pager
    private ZXingScannerView mScannerView;
    private int COUNT = 0;
    private int REQUEST_PAGE = 1;
    private Category mCat;
    private GPStracker mGPS;
    private final List<Store> listStores = new ArrayList<>();
    private int CatId;


    private int REQUEST_RANGE_RADIUS = -1;
    private String REQUEST_SEARCH = "";
    private int REQUEST_CATEGORY = -1;
    private LatLng LOCATION = null;
    private int Fav = 0;
    private int owner_id = 0;
    private HashMap<String, Object> searchParams;


    //private String custom_filter;

    ShimmerRecyclerView shimmerRecycler;


    // newInstance constructor for creating fragment with arguments
    public static ListStoresFragment newInstance(int page, String title) {
        ListStoresFragment fragmentFirst = new ListStoresFragment();
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
    }

    private void updateBadges() {
        BadgeNotificationUtils.updateMessengerBadge(getActivity());
        BadgeNotificationUtils.updateNotificationBadge(getActivity());
        BadgeNotificationUtils.updateCartItemsBadge(getActivity());
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        if ((CatId > 0 || Fav == -1)) return;

        menu.findItem(R.id.list_view_icon).setVisible(false);
        menu.findItem(R.id.qr_scanner).setVisible(true);
        menu.findItem(R.id.search_icon).setVisible(true);
        menu.findItem(R.id.qr_scanner).setVisible(true);

        menu.findItem(R.id.notification_action).setVisible(false);


        updateBadges();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.qr_scanner) {
           Showscanner();

          /*  IntentIntegrator intentIntegrator = new IntentIntegrator(getActivity());
            intentIntegrator.setPrompt("Scan a barcode or QR Code");
            intentIntegrator.setOrientationLocked(true);
            intentIntegrator.initiateScan();*/
        }
        if (item.getItemId() == R.id.list_view_icon) {

            RecyclerView.LayoutManager mLayoutManager;
            if (Utils.listViewFormat("list_view_format_stores") == 1) {
                mLayoutManager = new GridLayoutManager(getActivity(), 2);
                item.setIcon(R.drawable.ic_view_list_default);
                Utils.setListViewFormat("list_view_format_stores", 2);
            } else {
                mLayoutManager = new GridLayoutManager(getActivity(), 1);
                item.setIcon(R.drawable.ic_list_view_grid);
                Utils.setListViewFormat("list_view_format_stores", 1);
            }

            list.setLayoutManager(mLayoutManager);

        }
        return super.onOptionsItemSelected(item);
    }

    private void Showscanner() {

        View view;

        view = getLayoutInflater().inflate(R.layout.scanner_code, null);

        myDialog = new Dialog(getContext(), android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        myDialog.setContentView(view);
     /*   mScannerView = new ZXingScannerView(getContext());
        mScannerView.setResultHandler(this);
*/

        mScannerView = new ZXingScannerView(getActivity());
        mScannerView.setResultHandler(this);
        // Set the scanner view as the content view




        RelativeLayout rl = view.findViewById(R.id.relative_scan);
        rl.addView(mScannerView);
        mScannerView.startCamera();








        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        myDialog.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_store_list, container, false);


        //initialize the shimmer : recyclerview loader
        shimmerRecycler = rootView.findViewById(R.id.shimmer_rv_stores);

        handleExtraParams();

        queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();

        setupViewManager(rootView);

        setupRecyclerViewAdapter(rootView);


        setupRefreshLayout(rootView);


        if (ServiceHandler.isNetworkAvailable(this.getActivity())) {
            getStores(REQUEST_PAGE);
        } else {

            swipeRefreshLayout.setRefreshing(false);
            Toast.makeText(getActivity(), getString(R.string.check_network), Toast.LENGTH_LONG).show();
            if (adapter.getItemCount() == 0)
                mViewManager.error();
        }


        return rootView;
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
       // mScannerView = new ZXingScannerView(this);
        adapter = new StoreListAdapter(getActivity(), listStores, false);
        adapter.setClickListener(this);


        list.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        final RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), Utils.listViewFormat("list_view_format_stores"));

        //mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        list.setItemAnimator(new DefaultItemAnimator());
        list.setLayoutManager(mLayoutManager);
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
                                getStores(REQUEST_PAGE);
                        } else {
                            Toast.makeText(getContext(), "Network not available ", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
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

    private void handleExtraParams() {
        try {

            owner_id = getArguments().getInt("user_id");
            CatId = getArguments().getInt("category");

            if (getArguments().containsKey("searchParams")) {
                searchParams = (HashMap<String, Object>) getArguments().getSerializable("searchParams");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void itemClicked(View view, int position) {


        Store store = adapter.getItem(position);

        if (store != null) {


            if (APP_DEBUG)
                Log.e("_1_store_id", String.valueOf(store.getId()));

            Intent intent = new Intent(getActivity(), StoreDetailActivity.class);
            intent.putExtra("id", store.getId());
            startActivity(intent);
        }

    }

    public void getStores(final int page) {

        swipeRefreshLayout.setRefreshing(true);

        mGPS = new GPStracker(getActivity());

        shimmerRecycler.showShimmerAdapter();

        /*if (adapter.getItemCount() == 0)
            mViewManager.loading();*/

        //IF ther's no category in the db then go to the home page ( 0 )
        Realm realm = Realm.getDefaultInstance();
        mCat = realm.where(Category.class).equalTo("numCat", CatId).findFirst();
        final int numCat = mCat != null ? mCat.getNumCat() : Constances.initConfig.Tabs.HOME;

        final String strIds = StoreController.getSavedStoresAsString();

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
                    // List<Store> list = mStoreParser.getEventRealm();
                    COUNT = 0;
                    COUNT = mStoreParser.getIntArg(Tags.COUNT);
                    //mViewManager.showResult();


                    //check server permission and display the errors
                    if (mStoreParser.getSuccess() == -1) {
                        ErrorsController.serverPermissionError(getActivity());
                    }


                    RealmList<Store> list = mStoreParser.getStore();

                    if (list.size() > 0) {
                        StoreController.insertStores(list);
                    }

                    //remove all items from adapter before listing new items
                    if (page == 1)
                        adapter.removeAll();


                    for (int i = 0; i < list.size(); i++) {
                        Store sTr = list.get(i);
                        if (mGPS.getLatitude() == 0 && mGPS.getLongitude() == 0) {
                            sTr.setDistance((double) 0);
                        }
                        //if (list.get(i).getDistance() <=REQUEST_RANGE_RADIUS)
                        adapter.addItem(sTr);


                    }

                    loading = true;

                    // mViewManager.showResult();

                    if (COUNT > adapter.getItemCount())
                        REQUEST_PAGE++;

                    if (COUNT == 0 || adapter.getItemCount() == 0) {
                        mViewManager.empty();
                    } else {
                        mViewManager.showResult();
                    }


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


                shimmerRecycler.hideShimmerAdapter();
                swipeRefreshLayout.setRefreshing(false);

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


                    if (REQUEST_RANGE_RADIUS > -1) {
                        if (REQUEST_RANGE_RADIUS <= 99)
                            params.put("radius", String.valueOf((REQUEST_RANGE_RADIUS * 1024)));
                    }

                    params.put("limit", "30");

                    if (owner_id > 0)
                        params.put("user_id", String.valueOf(owner_id));

                    if (Fav == -1) {
                        if (!strIds.equals(""))
                            params.put("store_ids", strIds);
                        else {
                            params.put("store_ids", "0");
                        }
                    } else {
                        if (numCat == Constances.initConfig.Tabs.BOOKMAKRS) {

                            if (!strIds.equals(""))
                                params.put("store_ids", strIds);
                            else {
                                params.put("store_ids", "0");
                            }
                        }
                        if (numCat == Constances.initConfig.Tabs.MOST_RATED) {

                            params.put("order_by", String.valueOf(Constances.initConfig.Tabs.MOST_RATED));

                        }
                        if (numCat == Constances.initConfig.Tabs.MOST_RECENT) {
                            params.put("order_by", String.valueOf(Constances.initConfig.Tabs.MOST_RECENT));

                        } else if (numCat == Constances.initConfig.Tabs.HOME) {

                        } else {
                            params.put("category_id", numCat + "");
                        }

                    }

                    if (REQUEST_CATEGORY > -1) params.put("category_id", REQUEST_CATEGORY + "");
                    params.put("search", REQUEST_SEARCH);


                }


                //global params to send
                params.put("page", page + "");
                params.put("current_date", DateUtils.getUTC("yyyy-MM-dd H:m:s"));
                params.put("current_tz", TimeZone.getDefault().getID());


                if (APP_DEBUG) {
                    Log.e("ListStoreFragment", "  params getStores :" + params.toString());
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
        /*mGPS = new GPStracker(getActivity());
        if (mGPS.canGetLocation()) {

        } else {
            swipeRefreshLayout.setRefreshing(false);
            //mGPS.showSettingsAlert();
        }*/

        REQUEST_SEARCH = "";
        REQUEST_PAGE = 1;
        REQUEST_RANGE_RADIUS = -1;
        REQUEST_CATEGORY = -1;
        LOCATION = null;
        getStores(1);
        swipeRefreshLayout.setRefreshing(true);

    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle args = getArguments();
        if (args != null) {
            //Toast.makeText(getActivity(), "  is Liked  :"+args.get("isLiked"), Toast.LENGTH_LONG).show();
            Fav = args.getInt("fav");

        }
    }

    @Override
    public void customErrorView(View v) {

        Button retry = v.findViewById(R.id.btn);

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mViewManager.loading();

                REQUEST_SEARCH = "";
                REQUEST_RANGE_RADIUS = -1;
                REQUEST_CATEGORY = -1;
                LOCATION = null;
                REQUEST_PAGE = 1;

                getStores(REQUEST_PAGE);
            }
        });

    }

    @Override
    public void customLoadingView(View v) {


    }
    @Override
    public void onResume() {
        super.onResume();
        // Register ourselves as a handler for scan results.
       // mScannerView.setResultHandler(this);
        // Start camera on resume
       // mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        // Stop camera on pause
       // mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        // Prints scan results
        Log.d("result", rawResult.getText());
        String url = rawResult.getText();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

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

                getStores(REQUEST_PAGE);

            }
        });


    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (menu != null) {
            menu.clear();
            if (CatId > 0 || Fav == -1) inflater.inflate(R.menu.cat_menu, menu);
            else {
                inflater.inflate(R.menu.home_menu, menu);
                if (Utils.listViewFormat("list_view_format_stores") == 1) {
                    menu.findItem(R.id.list_view_icon).setIcon(R.drawable.ic_list_view_grid);

                } else {
                    menu.findItem(R.id.list_view_icon).setIcon(R.drawable.ic_view_list_default);
                }
            }
        }
        super.onCreateOptionsMenu(menu, inflater);


    }


}
