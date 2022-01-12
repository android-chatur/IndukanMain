package com.geogreenapps.apps.indukaan.fragments;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.geogreenapps.apps.indukaan.GPS.GPStracker;
import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.Services.LocationChangedEvent;
import com.geogreenapps.apps.indukaan.activities.StoreDetailActivity;
import com.geogreenapps.apps.indukaan.animation.Animation;
import com.geogreenapps.apps.indukaan.appconfig.AppConfig;
import com.geogreenapps.apps.indukaan.appconfig.Constances;
import com.geogreenapps.apps.indukaan.classes.Store;
import com.geogreenapps.apps.indukaan.customview.ProductCustomView;
import com.geogreenapps.apps.indukaan.load_manager.ViewManager;
import com.geogreenapps.apps.indukaan.network.VolleySingleton;
import com.geogreenapps.apps.indukaan.network.api_request.SimpleRequest;
import com.geogreenapps.apps.indukaan.parser.api_parser.StoreParser;
import com.geogreenapps.apps.indukaan.parser.tags.Tags;
import com.geogreenapps.apps.indukaan.utils.BadgeNotificationUtils;
import com.geogreenapps.apps.indukaan.utils.DateUtils;
import com.geogreenapps.apps.indukaan.utils.MapsUtils;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import static com.geogreenapps.apps.indukaan.appconfig.AppConfig.APP_DEBUG;
import static com.geogreenapps.apps.indukaan.utils.Utils.myLocation;


public class GeoStoresFragment extends Fragment implements OnMapReadyCallback {

    public ViewManager mViewManager;
    public int INT_RESULT_VERSION = 1001;
    private GoogleMap mMap;
    //private Context context;
    private LatLng myPosition;
    private int COUNT = 0;
    private Toolbar toolbar;
    //init request http
    private RequestQueue queue;
    private GPStracker mGPS;
    private final TextView APP_TITLE_VIEW = null;
    private final TextView APP_DESC_VIEW = null;
    private LinearLayout content;
    private ProductCustomView horizentalProductView;
    private LinearLayout storeProductLayout;
    private HashMap<String, Object> searchParams;
    private String order_by_recent;
    private LatLng recentStoreLocation;
    private LinearLayout store_focus_layout;
    private boolean requestStarted = false;
    private int REQUEST_RANGE_RADIUS = -1;
    private String REQUEST_SEARCH = "";
    private int REQUEST_CATEGORY = -1;
    private LatLng LOCATION = null;
    private int REQUEST_PAGE = 1;


    // newInstance constructor for creating fragment with arguments
    public static GeoStoresFragment newInstance(int page, String title) {
        GeoStoresFragment fragmentFirst = new GeoStoresFragment();
        Bundle args = new Bundle();
        args.putInt("id", page);
        args.putString("title", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map_main, container, false);
        //context = rootView.getContext();

        mViewManager = new ViewManager(getContext());
        mViewManager.setLoadingLayout(rootView.findViewById(R.id.loading));
        mViewManager.setResultLayout(rootView.findViewById(R.id.content_my_store));
        mViewManager.setErrorLayout(rootView.findViewById(R.id.error));
        mViewManager.setEmpty(rootView.findViewById(R.id.empty));
        mViewManager.loading();


        mGPS = new GPStracker(getContext());

        if (!mGPS.canGetLocation()) {
            mGPS.showSettingsAlert();
        } else {
            myPosition = new LatLng(mGPS.getLatitude(), mGPS.getLongitude());
        }


        queue = VolleySingleton.getInstance(getContext()).getRequestQueue();


        store_focus_layout = rootView.findViewById(R.id.store_focus_layout);
        storeProductLayout = rootView.findViewById(R.id.store_products_layout);
        content = rootView.findViewById(R.id.content_my_store);
        initStoreProductFocusLayout();

        attachMap();


        return rootView;


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        REQUEST_RANGE_RADIUS = Integer.parseInt(getResources().getString(R.string.DISTANCE_MAX_DISPLAY_ROUTE));

    }


    private void initStoreProductFocusLayout() {

        store_focus_layout.setVisibility(View.GONE);
        storeProductLayout.setVisibility(View.GONE);

    }

    private void hideStoreFocusLayout() {

        if (store_focus_layout.isShown())
            Animation.hideWithZoomEffect(store_focus_layout);

    }

    private void showStoreFocusLayout(final Store store) {


        TextView title = store_focus_layout.findViewById(R.id.name);
        RatingBar rateBar = store_focus_layout.findViewById(R.id.ratingBar2);
        TextView rateNbr = store_focus_layout.findViewById(R.id.rate);

        title.setText(store.getName());
        rateBar.setRating((float) store.getVotes());

        float rated = (float) store.getVotes();
        DecimalFormat decim = new DecimalFormat("#.##");

        rateNbr.setText(decim.format(rated) + "  (" + store.getNbr_votes() + ")");


        store_focus_layout.setVisibility(View.VISIBLE);
        Animation.startCustomZoom(store_focus_layout);
        //give permission to hide this layout after 3 second


        store_focus_layout.findViewById(R.id.closeLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hideStoreFocusLayout();

            }
        });

        store_focus_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hideStoreFocusLayout();

                int id = store.getId();
                Intent intent = new Intent(getActivity(), StoreDetailActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);

            }
        });


    }

    private void showProductFocusLayout(final Store store) {

        if (store.getNbrProduct() == 0) {
            storeProductLayout.setVisibility(View.GONE);
        } else {
            HashMap<String, Object> optionalParams = new HashMap<>();
            optionalParams.put("store_id", String.valueOf(store.getId()));
            horizentalProductView = getView().findViewById(R.id.horizontalProductList);
            horizentalProductView.loadData(false, optionalParams);
            storeProductLayout.setVisibility(View.VISIBLE);
            Animation.startCustomZoom(storeProductLayout);
            storeProductLayout.findViewById(R.id.closeProductLayoutBtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (storeProductLayout.isShown())
                        Animation.hideWithZoomEffect(storeProductLayout);
                }
            });
        }

    }

    private void attachMap() {

        try {

            SupportMapFragment mSupportMapFragment = (SupportMapFragment) getChildFragmentManager()
                    .findFragmentById(R.id.map);
            if (mSupportMapFragment == null) {
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                mSupportMapFragment = SupportMapFragment.newInstance();
                mSupportMapFragment.setRetainInstance(true);
                fragmentTransaction.replace(R.id.mapping, mSupportMapFragment).commit();
            }
            mSupportMapFragment.getMapAsync(this);

        } catch (Exception e) {

        }

    }

    private void moveHandler() {

        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (requestStarted) {

                    LatLng po = new LatLng(
                            mMap.getCameraPosition().target.latitude,
                            mMap.getCameraPosition().target.longitude
                    );

                    // getStores(po,true);
                }
            }
        }, 3000);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        mMap.setOnCameraMoveCanceledListener(new GoogleMap.OnCameraMoveCanceledListener() {
            @Override
            public void onCameraMoveCanceled() {

                if (AppConfig.APP_DEBUG)
                    Log.i("onCameraMoveCanceled", String.valueOf(mMap.getCameraPosition().target.latitude));
            }
        });


        mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {

                if (AppConfig.APP_DEBUG)
                    Log.i("onCameraMove", String.valueOf(mMap.getCameraPosition().target.latitude));

                moveHandler();

            }
        });

        mMap.setOnCameraMoveStartedListener(new GoogleMap.OnCameraMoveStartedListener() {
            @Override
            public void onCameraMoveStarted(int i) {

                if (AppConfig.APP_DEBUG)
                    Log.i("onCameraMoveStarted", String.valueOf(i));

            }
        });

        mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {

                if (AppConfig.APP_DEBUG)
                    Log.i("onCameraIdle", String.valueOf(mMap.getCameraPosition().target.latitude));
            }
        });


    }


    private void initMapping() {

        if (mMap != null) {

            mMap.getUiSettings().setZoomControlsEnabled(true);


            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(false);


            REQUEST_SEARCH = "";
            REQUEST_PAGE = 1;
            REQUEST_RANGE_RADIUS = -1;
            REQUEST_CATEGORY = -1;
            LOCATION = myLocation(getContext());

            getStores(LOCATION, false, getContext());

        }
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        updateBadges();

        if (menu != null) {
            menu.findItem(R.id.list_view_icon).setVisible(false);
            menu.findItem(R.id.search_icon).setVisible(false);
            menu.findItem(R.id.notification_action).setVisible(true);
            menu.findItem(R.id.qr_scanner).setVisible(false);

        }

    }

    private void updateBadges() {
        BadgeNotificationUtils.updateMessengerBadge(getActivity());
        BadgeNotificationUtils.updateNotificationBadge(getActivity());
    }


    @Override
    public void onPause() {
        super.onPause();
    }


    private void getStores(final LatLng position, final boolean refresh, final Context context) {

        requestStarted = true;

        if (refresh == false) {
            mViewManager.loading();
        }

        SimpleRequest request = new SimpleRequest(Request.Method.POST,
                Constances.API.API_USER_GET_STORES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    requestStarted = false;
                    if (APP_DEBUG)
                        Log.i("____response", response);

                    JSONObject jsonObject = new JSONObject(response);
                    final StoreParser mStoreParser = new StoreParser(jsonObject);

                    COUNT = mStoreParser.getIntArg(Tags.COUNT);
                    int success = Integer.parseInt(mStoreParser.getStringAttr("success"));

                    if (success == 1) {

                        if (COUNT > 0) {

                            //if (!refresh) {
                            if (position != null)
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 10));

                            mViewManager.showResult();
                            //}

                            final List<Store> list = mStoreParser.getStore();


                            if (refresh) {
                                mMap.clear();
                            }


                            for (int i = 0; i < list.size(); i++) {


                                String imageUrl = null;

                                if (list.get(i).getListImages() != null && list.get(i).getListImages().size() > 0) {
                                    imageUrl = list.get(i).getListImages().get(0).getUrl100_100();
                                }

                                if (imageUrl != null) {

                                    final int finalI = i;


                                    Glide.with(context.getApplicationContext())
                                            .asBitmap()
                                            .load(imageUrl)
                                            .into(new CustomTarget<Bitmap>() {
                                                @Override
                                                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {

                                                    String promo;
                                                    if (list.get(finalI) != null && list.get(finalI).getNbrProduct() > 0) {
                                                        promo = String.valueOf(list.get(finalI).getNbrProduct());
                                                    } else {
                                                        promo = null;
                                                    }

                                                    Marker marker = null;
                                                    marker = mMap.addMarker(

                                                            MapsUtils.generateMarker(getActivity(),
                                                                    String.valueOf(list.get(finalI).getId()),
                                                                    new LatLng(list.get(finalI).getLatitude(), list.get(finalI).getLongitude()
                                                                    ),
                                                                    resource,
                                                                    promo
                                                            ).draggable(false)

                                                    );

                                                    marker.setTag(finalI);
                                                    MapsUtils.addMarker(String.valueOf(list.get(finalI).getId()), marker);

                                                }

                                                @Override
                                                public void onLoadCleared(@Nullable Drawable placeholder) {
                                                }
                                            });


                                } else {

                                    String promo;
                                    if (list.get(i) != null && list.get(i).getNbrProduct() > 0) {
                                        promo = String.valueOf(list.get(i).getNbrProduct());
                                    } else {
                                        promo = null;
                                    }

                                    Marker marker = null;
                                    marker = mMap.addMarker(

                                            MapsUtils.generateMarker(getActivity(),
                                                    String.valueOf(list.get(i).getId()),
                                                    new LatLng(list.get(i).getLatitude(), list.get(i).getLongitude()
                                                    ),
                                                    null,
                                                    promo
                                            ).draggable(false)

                                    );

                                    marker.setTag(i);
                                    MapsUtils.addMarker(String.valueOf(list.get(i).getId()), marker);

                                }


                            }

                            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(Marker marker) {

                                    int position = (int) (marker.getTag());

                                    if (APP_DEBUG)
                                        Toast.makeText(getActivity(), list.get(position).getName(),
                                                Toast.LENGTH_LONG).show();

//
                                    showStoreFocusLayout(list.get(position));
                                    showProductFocusLayout(list.get(position));

                                    return false;
                                }
                            });

                        } else {
                            Toast.makeText(getActivity(), "No business found !! ", Toast.LENGTH_LONG).show();
                        }
                    }


                } catch (JSONException e) {
                    //send a rapport to support
                    e.printStackTrace();

                    requestStarted = false;
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (APP_DEBUG) {
                    Log.e("ERROR", error.toString());
                }

                //mViewManager.showError();
                requestStarted = false;
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();


                if (position != null) {
                    params.put("latitude", position.latitude + "");
                    params.put("longitude", position.longitude + "");
                } else if (mGPS.canGetLocation()) {
                    params.put("latitude", mGPS.getLatitude() + "");
                    params.put("longitude", mGPS.getLongitude() + "");
                }

                if (REQUEST_RANGE_RADIUS > -1) {
                    if (REQUEST_RANGE_RADIUS <= 99)
                        params.put("radius", String.valueOf((REQUEST_RANGE_RADIUS * 1024)));
                }

                if (REQUEST_CATEGORY > -1) params.put("category_id", REQUEST_CATEGORY + "");

                params.put("search", REQUEST_SEARCH);
                params.put("order_by", Constances.OrderByFilter.NEARBY);
                params.put("current_date", DateUtils.getUTC("yyyy-MM-dd H:m:s"));
                params.put("current_tz", TimeZone.getDefault().getID());
                params.put("limit", context.getResources().getString(R.string.NBR_STORES_MAX_GEO_MAPS));
                params.put("page", "1");

                if (APP_DEBUG) {
                    Log.i("mapsStoresActivity", "  params map :" + params.toString());
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
    public void setUserVisibleHint(boolean isVisibleToUser) {

        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isResumed()) {
            if (!mGPS.canGetLocation())
                mGPS.showSettingsAlert();
            else
                initMapping();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.search_icon) {

            SearchDialog.newInstance(getActivity()).setOnSearchListener(new SearchDialog.Listener() {
                @Override
                public void onSearchClicked(SearchDialog mSearchDialog, String value, int radius, int cat, LatLng location) {

                    if (mSearchDialog.isShowing())
                        mSearchDialog.dismiss();

                    if (APP_DEBUG)
                        Toast.makeText(getContext(), value + " " + radius, Toast.LENGTH_LONG).show();

                    REQUEST_RANGE_RADIUS = radius;
                    REQUEST_SEARCH = value;
                    REQUEST_PAGE = 1;
                    REQUEST_CATEGORY = cat;

                    if (location == null)
                        location = myLocation(getActivity());

                    LOCATION = location;

                    getStores(LOCATION, true, getContext());


                }
            }).setHeader(getString(R.string.searchOnMaps)).showDialog();

        }
        return super.onOptionsItemSelected(item);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SearchDialog.AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == AutocompleteActivity.RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                Log.i("CustomSearchFrag", "Place: " + place.getName() + ", " + place.getId() + ", " + place.getAddress() + ", " + place.getLatLng());
                EventBus.getDefault().postSticky(new LocationChangedEvent(place));

            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the showError.
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i("CustomSearchFrag", status.getStatusMessage());
            } else if (resultCode == AutocompleteActivity.RESULT_CANCELED) {
                // The user canceled the operation.
            }
        } else if (INT_RESULT_VERSION == requestCode && resultCode == Activity.RESULT_OK) {

            if (data != null && data.hasExtra("searchParams")) {
                searchParams = (HashMap<String, Object>) data.getSerializableExtra("searchParams");
            }

            REQUEST_SEARCH = "";
            REQUEST_PAGE = 1;
            REQUEST_RANGE_RADIUS = -1;
            REQUEST_CATEGORY = -1;
            LOCATION = myPosition;
            getStores(LOCATION, true, getContext());
        } else {
            Toast.makeText(getContext(), R.string.error_try_later, Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (menu != null) {
            menu.clear();
            inflater.inflate(R.menu.home_menu, menu);
        }
        super.onCreateOptionsMenu(menu, inflater);


    }
}
