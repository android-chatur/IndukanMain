package com.geogreenapps.apps.indukaan.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.geogreenapps.apps.indukaan.AppController;
import com.geogreenapps.apps.indukaan.GPS.GPStracker;
import com.geogreenapps.apps.indukaan.GPS.Position;
import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.animation.ImageLoaderAnimation;
import com.geogreenapps.apps.indukaan.appconfig.AppConfig;
import com.geogreenapps.apps.indukaan.appconfig.Constances;
import com.geogreenapps.apps.indukaan.classes.Category;
import com.geogreenapps.apps.indukaan.classes.Discussion;
import com.geogreenapps.apps.indukaan.classes.OpeningTime;
import com.geogreenapps.apps.indukaan.classes.Setting;
import com.geogreenapps.apps.indukaan.classes.Store;
import com.geogreenapps.apps.indukaan.classes.User;
import com.geogreenapps.apps.indukaan.controllers.CampagneController;
import com.geogreenapps.apps.indukaan.controllers.SettingsController;
import com.geogreenapps.apps.indukaan.controllers.categories.CategoryController;
import com.geogreenapps.apps.indukaan.controllers.sessions.SessionsController;
import com.geogreenapps.apps.indukaan.controllers.stores.StoreController;
import com.geogreenapps.apps.indukaan.customview.OfferCustomView;
import com.geogreenapps.apps.indukaan.fragments.GalleryFragment;
import com.geogreenapps.apps.indukaan.fragments.StoreProductsFragment;
import com.geogreenapps.apps.indukaan.fragments.StoreReviewsFragment;
import com.geogreenapps.apps.indukaan.helper.CommunFunctions;
import com.geogreenapps.apps.indukaan.load_manager.ViewManager;
import com.geogreenapps.apps.indukaan.network.ServiceHandler;
import com.geogreenapps.apps.indukaan.network.VolleySingleton;
import com.geogreenapps.apps.indukaan.network.api_request.SimpleRequest;
import com.geogreenapps.apps.indukaan.parser.api_parser.StoreParser;
import com.geogreenapps.apps.indukaan.parser.tags.Tags;
import com.geogreenapps.apps.indukaan.unbescape.html.HtmlEscape;
import com.geogreenapps.apps.indukaan.utils.DateUtils;
import com.geogreenapps.apps.indukaan.utils.TextUtils;
import com.geogreenapps.apps.indukaan.utils.Utils;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.nirhart.parallaxscroll.views.ParallaxScrollView;
import com.wuadam.awesomewebview.AwesomeWebView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmList;

import static com.geogreenapps.apps.indukaan.appconfig.AppConfig.APP_DEBUG;
import static com.geogreenapps.apps.indukaan.controllers.sessions.SessionsController.isLogged;

public class StoreDetailActivity extends GlobalActivity implements ViewManager.CustomView,
        GoogleMap.OnMapLoadedCallback, View.OnClickListener, OnMapReadyCallback {
    private static boolean opened = false;
    private static boolean isFirstTime = true;
    public ViewManager mViewManager;
    Dialog myDialog;
    Button share;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_back)
    ImageView toolbar_back;
    @BindView(R.id.description_label)
    TextView description_label;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.nbrPictures)
    TextView nbrPictures;
    @BindView(R.id.distanceView)
    TextView distanceView;
    @BindView(R.id.progressMapLL)
    LinearLayout progressMapLL;
    @BindView(R.id.mapcontainer)
    LinearLayout mapcontainer;
    @BindView(R.id.adView)
    AdView adView;
    @BindView(R.id.adsLayout)
    LinearLayout adsLayout;
    @BindView(R.id.address_content)
    TextView addressContent;
    @BindView(R.id.catImage)
    ImageView catImage;
    @BindView(R.id.category_content)
    TextView categoryContent;
    @BindView(R.id.category_layout)
    LinearLayout categoryLayout;
    @BindView(R.id.description_content)
    TextView descriptionContent;
    @BindView(R.id.productsBtn)
    Button productsBtn;
    @BindView(R.id.productsBtnLayout)
    LinearLayout productsBtnLayout;
    @BindView(R.id.reviewsBtn)
    Button reviewsBtn;
    @BindView(R.id.reviewsBtnLayout)
    LinearLayout reviewsBtnLayout;
    @BindView(R.id.galleryBtn)
    Button galleryBtn;
    @BindView(R.id.galleryBtnLayout)
    LinearLayout galleryBtnLayout;
    @BindView(R.id.store_content_block_btns)
    LinearLayout storeContentBlockBtns;
    @BindView(R.id.scontainer)
    LinearLayout scontainer;
    @BindView(R.id.store_content_block)
    LinearLayout storeContentBlock;
    @BindView(R.id.btn_chat_customer)
    ImageButton btnChatCustomer;
    @BindView(R.id.websiteBtn)
    ImageButton btnWebsite;
    @BindView(R.id.phoneBtn)
    ImageButton phoneBtn;
    private int REQUEST_PAGE = 1;
    @BindView(R.id.mapBtn)
    ImageButton mapBtn;
    @BindView(R.id.shareBtn)
    ImageButton shareBtn;
    @BindView(R.id.btnsLayout)
    LinearLayout btnsLayout;
    @BindView(R.id.mScroll)
    ParallaxScrollView mScroll;
    @BindView(R.id.opening_time_container)
    LinearLayout opening_time_container;
    @BindView(R.id.opening_time_content)
    TextView opening_time_content;
    @BindView(R.id.badge_closed)
    TextView badge_closed;
    @BindView(R.id.badge_open)
    TextView badge_open;
    String QRcode_url;
    private QRGEncoder qrgEncoder;
    private String inputValue;
    private Bitmap bitmap;
    private ImageView qr_image;
    private Context context;
    ////////////////////////MAPPING
    private GoogleMap mMap;
    private LatLng customerPosition;
    private Toolbar toolbar;
    private Store storedata;
    private GPStracker mGPS;
    //init request http
    private RequestQueue queue;
    private User mUserSession;
    private Menu menuContext;
    private BottomSheetDialog mBottomSheetDialog;


    //custom view
    private OfferCustomView horizontalOfferList;


    public static boolean isOpend() {
        return opened;
    }

    private void attachMap() {

        try {

            SupportMapFragment mSupportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.mapping);
            if (mSupportMapFragment == null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                mSupportMapFragment = SupportMapFragment.newInstance();
                mSupportMapFragment.setRetainInstance(true);
                fragmentTransaction.replace(R.id.mapping, mSupportMapFragment).commit();
            }
            if (mSupportMapFragment != null) {
                mSupportMapFragment.getMapAsync(StoreDetailActivity.this);
            }

        } catch (Exception e) {
            progressMapLL.setVisibility(View.GONE);
        }

    }

    private void initOfferRV(int store_id) {
        Map<String, Object> optionalParams = new HashMap<>();
        optionalParams.put("store_id", String.valueOf(store_id));


        if (!SettingsController.isModuleEnabled(Constances.ModulesConfig.OFFER_MODULE)) {
            horizontalOfferList.hide();
        } else {

            if (storedata.getNbrOffers() == 0)
                horizontalOfferList.hide();
            else {
                horizontalOfferList.loadData(false, optionalParams);
                findViewById(R.id.card_show_more).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(StoreDetailActivity.this, OffersListActivity.class));
                        overridePendingTransition(R.anim.lefttoright_enter, R.anim.lefttoright_exit);
                    }
                });
                horizontalOfferList.show();
            }

        }


    }


    private void setupBadges() {


        Drawable badge_closed_background = badge_closed.getBackground();
        if (badge_closed_background instanceof ShapeDrawable) {
            ((ShapeDrawable) badge_closed_background).getPaint().setColor(ContextCompat.getColor(this, R.color.quantum_orange600));
        } else if (badge_closed_background instanceof GradientDrawable) {
            ((GradientDrawable) badge_closed_background).setColor(ContextCompat.getColor(this, R.color.quantum_orange600));
        } else if (badge_closed_background instanceof ColorDrawable) {
            ((ColorDrawable) badge_closed_background).setColor(ContextCompat.getColor(this, R.color.quantum_orange600));
        }

        Drawable badge_open_background = badge_open.getBackground();
        if (badge_closed_background instanceof ShapeDrawable) {
            ((ShapeDrawable) badge_open_background).getPaint().setColor(ContextCompat.getColor(this, R.color.seaGreen));
        } else if (badge_closed_background instanceof GradientDrawable) {
            ((GradientDrawable) badge_open_background).setColor(ContextCompat.getColor(this, R.color.seaGreen));
        } else if (badge_closed_background instanceof ColorDrawable) {
            ((ColorDrawable) badge_open_background).setColor(ContextCompat.getColor(this, R.color.seaGreen));
        }

    }


    private void parseOpeningTime() {
        /*
         * Opening time table
         */


        if (storedata.getOpening() == 1 || storedata.getOpening() == -1) {

            opening_time_container.setVisibility(View.VISIBLE);
            String opt_string = "";

            for (int i = 0; i < storedata.getOpening_time_table_list().size(); i++) {

                OpeningTime opt = storedata.getOpening_time_table_list().get(i);

                if (APP_DEBUG) {
                    Log.e("__getDay__", opt.toString());
                }

                String formatted_opening = DateUtils.getPrepareSimpleDate("01-01-2011 " + opt.getOpening(), AppConfig.FORMAT_24 ? "HH:mm" : "hh:mm a");
                String formatted_closing = DateUtils.getPrepareSimpleDate("01-01-2011 " + opt.getClosing(), AppConfig.FORMAT_24 ? "HH:mm" : "hh:mm a");

                String opening_status = "";

                String opening_day = opt.getDay().substring(0, 3);


                if (storedata.getOpening() == 1 && DateUtils.getCurrentDay().toLowerCase().equals(opening_day.toLowerCase())) {
                    opening_status = " \t - <b><font color=" + ContextCompat.getColor(this, R.color.seaGreen) + ">" + getString(R.string.open_now) + "</font><b>";
                } else if (storedata.getOpening() == -1 && DateUtils.getCurrentDay().toLowerCase().equals(opening_day.toLowerCase())) {
                    opening_status = "\t - <b><font color=\"red\">" + getString(R.string.closed) + "</font><b>";
                }

                //translate language
                if (opt.getDay() != null) {
                    switch (opt.getDay().toLowerCase()) {
                        case "monday":
                            opening_day = getString(R.string.monday);
                            break;
                        case "tuesday":
                            opening_day = getString(R.string.tuesday);
                            break;
                        case "wednesday":
                            opening_day = getString(R.string.wednesday);
                            break;
                        case "thursday":
                            opening_day = getString(R.string.thursday);
                            break;
                        case "friday":
                            opening_day = getString(R.string.friday);
                            break;
                        case "saturday":
                            opening_day = getString(R.string.saturday);
                            break;
                        case "sunday":
                            opening_day = getString(R.string.sunday);
                            break;

                    }

                }


                if (opt.getOpening().equals(opt.getClosing())) {
                    // opt_string = opt_string + "<b>" + TextUtils.capitalizeFirstLetter(opening_day) + "</b>: <font color=\"red\">N/A</font> " + opening_status + "<br>";
                    opt_string = opt_string + "<b>" + TextUtils.capitalizeFirstLetter(opening_day) + "</b>: \t  <i> " + opening_status + "</i> <br>";
                } else {
                    opt_string = opt_string + "<b>" + TextUtils.capitalizeFirstLetter(opening_day) + "</b>: \t - <i> " + formatted_opening + " - " + formatted_closing + " " + opening_status + "</i> <br>";
                }


            }

            opening_time_content.setText(Html.fromHtml(opt_string));

        } else {
            opening_time_container.setVisibility(View.GONE);

            badge_open.setVisibility(View.GONE);
            badge_closed.setVisibility(View.GONE);

        }


        /*
         * End Opening time table
         */
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mMap = googleMap;
        if (storedata.getLatitude() != null && storedata.getLatitude() != null) {

            double TraderLat = storedata.getLatitude();
            double TraderLng = storedata.getLongitude();
            customerPosition = new LatLng(TraderLat, TraderLng);
            //INITIALIZE MY LOCATION
            GPStracker trackMe = new GPStracker(this);
            if (APP_DEBUG)
                Log.e("__lat", String.valueOf(customerPosition.latitude));
            moveToPosition(mMap, customerPosition);
        }

        progressMapLL.setVisibility(View.GONE);

    }

    private void moveToPosition(GoogleMap gm, LatLng targetPosition) {

        gm.moveCamera(CameraUpdateFactory.newLatLngZoom(targetPosition, 16));
        gm.getUiSettings().setZoomControlsEnabled(true);
        gm.addMarker(new MarkerOptions()
                .title(context.getString(R.string.your_destination))
                .icon(CommunFunctions.BitmapFromVector(R.drawable.ic_marker))
                .position(targetPosition));
    }

    @Override
    protected void onDestroy() {

        if (adView != null)
            adView.destroy();


        super.onDestroy();
        opened = false;

        final android.app.FragmentManager fragManager = this.getFragmentManager();
        final Fragment fragment = fragManager.findFragmentById(R.id.mapping);
        if (fragment != null) {
            fragManager.beginTransaction().remove(fragment).commit();
        }
    }

    private final void focusOnView(final int redId) {

        mScroll.post(new Runnable() {
            @Override
            public void run() {
                //mScroll.scrollTo(0, (findViewById(redId)).getBottom());
                // mScroll.fullScroll(View.FOCUS_DOWN);

                View lastChild = mScroll.getChildAt(mScroll.getChildCount() - 1);
                int bottom = lastChild.getBottom() + mScroll.getPaddingBottom();
                int sy = mScroll.getScrollY();
                int sh = mScroll.getHeight();
                int delta = bottom - (sy + sh);

                mScroll.smoothScrollBy(0, delta);
            }
        });


    }

    @Override
    protected void onPause() {

        if (adView != null)
            adView.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (adView != null)
            adView.resume();
        super.onResume();
    }


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(null);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        setContentView(R.layout.activity_store_detail);
        ButterKnife.bind(this);

        //invalidateOptionsMenu();

        initGLobalParams();

        //INIT TOOLBAR
        setupToolbar();

        //set default color toolbar

        handleScrollListener();

        setupAdmob();


        reviewsBtnLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        productsBtnLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        galleryBtnLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        ////////////////


        //GET USER SESSION
        if (isLogged())
            mUserSession = SessionsController.getSession().getUser();


        //GET DATA
        if (isFirstTime == true) {

            (new Handler()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    getStore();

                    isFirstTime = false;
                    // openMap();
                    mViewManager.showResult();
                }
            }, 1000);


        } else {

            (new Handler()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    mViewManager.showResult();
                    getStore();
                }
            }, 500);
        }


        //  INIT BUTTON CLICK LISTNER
        //make links in a TextView clickable
        descriptionContent.setMovementMethod(LinkMovementMethod.getInstance());

        buttonClickListener();


        setupViewManager();

        setupBadges();


        try {
            int cid = Integer.parseInt(getIntent().getExtras().getString("cid"));
            CampagneController.markView(cid);
        } catch (Exception e) {

        }


    }

    private void initGLobalParams() {
        toolbar_back.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        queue = VolleySingleton.getInstance(this).getRequestQueue();
        context = this;
        mScroll = findViewById(R.id.mScroll);
        horizontalOfferList = findViewById(R.id.horizontalOfferList);
        //Initialize map fragment
        mGPS = new GPStracker(this);
        shareBtn.setVisibility(View.GONE); //HIDE THE SHARE BTN
    }

    private void setupAdmob() {
        if (AppConfig.SHOW_ADS && AppConfig.SHOW_ADS_IN_STORE) {

            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice("FFD811D6CAB26FA340E98A773B3408ED")
                    .addTestDevice("3CB74DFA141BF4D0823B8EA7D94531B5")
                    .build();
            adView.loadAd(adRequest);
            adView.setVisibility(View.VISIBLE);
            adsLayout.setVisibility(View.VISIBLE);

        } else
            adsLayout.setVisibility(View.GONE);
    }

    private void handleScrollListener() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mScroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (APP_DEBUG)
                        Log.e("onScrollChange", "scrollX=" + scrollX + ";scrollY=" + scrollY);

                    if (scrollY < 600) {
                        toolbar.setBackground(getDrawable(R.drawable.gradient_bg_top_to_bottom_70));
                        toolbarTitle.setVisibility(View.GONE);
                        toolbar_back.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            menuContext.findItem(R.id.rate_review).setIconTintList(ContextCompat.getColorStateList(getApplicationContext(), android.R.color.white));
                            menuContext.findItem(R.id.send_location).setIconTintList(ContextCompat.getColorStateList(getApplicationContext(), android.R.color.white));
                             menuContext.findItem(R.id.my_qr).setIconTintList(ContextCompat.getColorStateList(getApplicationContext(), android.R.color.white));
                        }

                    } else {
                        toolbar.setBackgroundColor(getColor(R.color.toolbarColor));
                        toolbarTitle.setTextColor(getColor(R.color.color_toolbar_action));
                        toolbarTitle.setVisibility(View.VISIBLE);
                        toolbar_back.setColorFilter(getResources().getColor(R.color.color_toolbar_action), PorterDuff.Mode.SRC_ATOP);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            menuContext.findItem(R.id.rate_review).setIconTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.color_toolbar_action));
                            menuContext.findItem(R.id.send_location).setIconTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.color_toolbar_action));
                            menuContext.findItem(R.id.bookmarks_icon).setIconTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.color_toolbar_action));
                            menuContext.findItem(R.id.my_qr).setIconTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.color_toolbar_action));
                        }
                    }
                }
            });
        }
    }

    private void setupViewManager() {
        //INIT VIEW MANAGER
        mViewManager = new ViewManager(this);
        mViewManager.setLoadingLayout(findViewById(R.id.loading));
        mViewManager.setResultLayout(findViewById(R.id.content_my_store));
        mViewManager.setErrorLayout(findViewById(R.id.error));
        mViewManager.setEmpty(findViewById(R.id.empty));
        mViewManager.setCustumizeView(this);
        mViewManager.loading();
    }

    private void buttonClickListener() {

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (AppConfig.ENABLE_LOCAL_MAPS_DIRECTION) {
                    if (storedata != null && mGPS.canGetLocation()) {

                        Intent intent = new Intent(StoreDetailActivity.this, MapDirectionActivity.class);
                        intent.putExtra("latitude", storedata.getLatitude() + "");
                        intent.putExtra("longitude", storedata.getLongitude() + "");
                        intent.putExtra("name", storedata.getName() + "");
                        intent.putExtra("description", storedata.getAddress() + "");
                        intent.putExtra("distance", storedata.getDistance() + "");

                        startActivity(intent);

                    } else if (!mGPS.canGetLocation()) {
                        mGPS.showSettingsAlert();
                        Toast.makeText(StoreDetailActivity.this, R.string.enable_gps_map_direction, Toast.LENGTH_LONG).show();
                    } else if (!ServiceHandler.isNetworkAvailable(context)) {
                        mGPS.showSettingsAlert();
                        Toast.makeText(StoreDetailActivity.this, R.string.enable_network_map_direction, Toast.LENGTH_LONG).show();
                    }
                } else {
                    Uri gmmIntentUri = Uri.parse(String.format(Locale.ENGLISH, "http://maps.google.com/maps?q=loc:%f,%f", storedata.getLatitude(), storedata.getLongitude()));

                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    if (mapIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(mapIntent);
                    }
                }

            }
        });

    }

    public void removeStoreToBookmarks(final Context context, final int user_id, final int int_id) {

        RequestQueue queue = VolleySingleton.getInstance(context).getRequestQueue();
        SimpleRequest request = new SimpleRequest(Request.Method.POST,
                Constances.API.API_REMOVE_STORE_BOOKMARK, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (APP_DEBUG) {
                    Log.e("response", response);
                }

                try {

                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.getInt(Tags.SUCCESS) == 1) {
                        storedata = StoreController.doSave(storedata.getId(), 0);
                        if (storedata != null) {
                            setBookmarkMenu();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Something went wrong , please try later ", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    //send a rapport to support
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (APP_DEBUG) {
                    Log.e("ERROR", error.toString());
                }


            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("user_id", String.valueOf(user_id));
                params.put("store_id", String.valueOf(int_id));

                return params;
            }

        };

        request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);

    }


    public void saveStoreToBookmarks(final Context context, final int user_id, final int int_id) {

        RequestQueue queue = VolleySingleton.getInstance(context).getRequestQueue();
        SimpleRequest request = new SimpleRequest(Request.Method.POST,
                Constances.API.API_SAVE_STORE_BOOKMARK, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (APP_DEBUG) {
                    Log.e("response", response);
                }

                try {

                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.getInt(Tags.SUCCESS) == 1) {

                        storedata = StoreController.doSave(storedata.getId(), 1);
                        if (storedata != null) {
                            setBookmarkMenu();
                        }

                        //check if notification agreement is enabled
                        Setting defaultAppSetting = SettingsController.findSettingFiled("_NOTIFICATION_AGREEMENT_USE");
                        if (defaultAppSetting != null && defaultAppSetting.getValue().equals("1"))
                            showBottomSheetDialog(jsonObject.getInt(Tags.RESULT));

                    } else {
                        Toast.makeText(getApplicationContext(), "Something went wrong , please try later ", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    //send a rapport to support
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (APP_DEBUG) {
                    Log.e("ERROR", error.toString());
                }


            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("user_id", String.valueOf(user_id));
                params.put("store_id", String.valueOf(int_id));

                return params;
            }

        };

        request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);

    }

    private void setBookmarkMenu() {
        MenuItem bookmarksItemMenu = menuContext.findItem(R.id.bookmarks_icon);
        if (bookmarksItemMenu != null) {
            if ((isLogged() && storedata.getSaved() > 0)) {
                Drawable cmd_bookmark = new IconicsDrawable(this)
                        .icon(CommunityMaterial.Icon.cmd_bookmark)
                        .color(ResourcesCompat.getColor(getResources(), R.color.white, null))
                        .sizeDp(18);
                bookmarksItemMenu.setIcon(cmd_bookmark);
            } else {
                Drawable cmd_bookmark = new IconicsDrawable(this)
                        .icon(CommunityMaterial.Icon.cmd_bookmark_outline)
                        .color(ResourcesCompat.getColor(getResources(), R.color.white, null))
                        .sizeDp(18);
                bookmarksItemMenu.setIcon(cmd_bookmark);
            }
        }

    }

    private void showBottomSheetDialog(final int bookmark_id) {

        final View view = getLayoutInflater().inflate(R.layout.notifyme_sheet, null);
        ((TextView) view.findViewById(R.id.name)).setText(R.string.receive_notification);
        ((TextView) view.findViewById(R.id.address)).setText(R.string.agree_to_recieve_notification);
        (view.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.dismiss();
            }
        });

        (view.findViewById(R.id.bt_details)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationAgreement(bookmark_id, SessionsController.getSession().getUser().getId(), 1);
                mBottomSheetDialog.dismiss();
            }
        });

        mBottomSheetDialog = new BottomSheetDialog(this);
        mBottomSheetDialog.setContentView(view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBottomSheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        mBottomSheetDialog.show();
        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mBottomSheetDialog = null;
            }
        });
    }


    public void notificationAgreement(final int bookmark_id, final int user_id, final int notificationStatus) {

        RequestQueue queue = VolleySingleton.getInstance(this).getRequestQueue();

        SimpleRequest request = new SimpleRequest(Request.Method.POST,
                Constances.API.API_NOTIFICATIONS_AGREEMENT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    if (APP_DEBUG) {
                        Log.e("notificationAgreement", "response  : " + response);
                    }

                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.getInt(Tags.SUCCESS) == 1) {
                        Toast.makeText(StoreDetailActivity.this, "Notification agreement granted for this business ", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(StoreDetailActivity.this, "Something went wrong , please try later ", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    //send a rapport to support
                    e.printStackTrace();

                    mViewManager.error();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (APP_DEBUG) {
                    Log.e("ERROR", error.toString());
                }
                mViewManager.error();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("bookmark_id", String.valueOf(bookmark_id));
                params.put("user_id", String.valueOf(user_id));
                params.put("agreement", String.valueOf(notificationStatus)); //todo : set the agreement according to the store status

                if (APP_DEBUG) {
                    Log.e("notificationAgreement", "params :" + params.toString());
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
    public void customErrorView(View v) {

    }

    @Override
    public void customLoadingView(View v) {

    }

    @Override
    public void customEmptyView(View v) {

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        menuContext = menu;

        /////////////////////////////
        menu.findItem(R.id.bookmarks_icon).setVisible(true);
        menu.findItem(R.id.my_qr).setVisible(true);


        /////////////////////////////
        menu.findItem(R.id.rate_review).setVisible(false);
        Drawable review = new IconicsDrawable(this)
                .icon(CommunityMaterial.Icon.cmd_star_outline)
                .color(ResourcesCompat.getColor(getResources(), R.color.white, null))
                .sizeDp(24);
        menu.findItem(R.id.rate_review).setIcon(review);
        //////////////////////////////


        /////////////////////////////
        menu.findItem(R.id.send_location).setVisible(true);

        Drawable send_location = new IconicsDrawable(this)
                .icon(CommunityMaterial.Icon.cmd_share_variant)
                .color(ResourcesCompat.getColor(getResources(), R.color.white, null))
                .sizeDp(20);
        menu.findItem(R.id.send_location).setIcon(send_location);
        /////////////////////////////


        return true;
    }


    @Override
    public void onStart() {
        super.onStart();


        opened = true;
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void setupToolbar() {

        toolbar = findViewById(R.id.app_bar);
        //toolbar.getContext().setTheme(R.style.ActionBarThemeOverlayCustom);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        //getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbarTitle.setText(R.string.store_title_detail);

        if (AppController.isRTL()) {
            toolbar_back.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.forward, null));
        } else {
            toolbar_back.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.back, null));
        }


        toolbar_back.setVisibility(View.VISIBLE);
        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }


    private void getStore() {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        int store_id = 0;


        //get it from external url (deep linking)
        try {

            Intent appLinkIntent = getIntent();
            String appLinkAction = appLinkIntent.getAction();
            Uri appLinkData = appLinkIntent.getData();

            if (appLinkAction.equals(Intent.ACTION_VIEW)) {

                if (APP_DEBUG)
                    Toast.makeText(getApplicationContext(), appLinkData.toString(), Toast.LENGTH_LONG).show();
                store_id = Utils.dp_get_id_from_url(appLinkData.toString(), "store");

                if (APP_DEBUG)
                    Toast.makeText(getApplicationContext(), "The ID: " + store_id + " " + appLinkAction, Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {

        }


        //get it from internal app
        if (store_id == 0) {

            Bundle bundle = getIntent().getExtras();
            store_id = bundle.getInt("id");
            QRcode_url = "https://indukaan.com/indukaan-v1-0/store/id/" + store_id;
          //  Log.d("abc", QRcode_url);
            try {
                if (store_id == 0) {
                    store_id = Integer.parseInt(bundle.getString("id"));
                }
            } catch (Exception e) {
                store_id = 0;
            }
        }


        if (APP_DEBUG)
            Log.e("_2_store_id", String.valueOf(store_id));

        storedata = StoreController.getStore(store_id);

        if (storedata != null) {
            setupDataIntoStoreDetailViews();
        } else {
            syncStore(store_id);
        }

        realm.commitTransaction();
        realm.close();
    }

    private void setupDataIntoStoreDetailViews() {

        setBookmarkMenu();

        showProductFrag();

        attachMap();


        description_label.setText(storedata.getName());
        toolbarTitle.setText(storedata.getName());

        if (storedata.getGallery() > 0) {

            int width = storeContentBlockBtns.getLayoutParams().width;

            storeContentBlockBtns.setWeightSum(12);

            LinearLayout.LayoutParams productsBtnLayoutParams = (LinearLayout.LayoutParams) productsBtnLayout.getLayoutParams();
            productsBtnLayoutParams.width = width / 3;
            productsBtnLayoutParams.weight = 4;
            productsBtnLayout.setLayoutParams(productsBtnLayoutParams);

            LinearLayout.LayoutParams reviewsBtnLayoutParams = (LinearLayout.LayoutParams) reviewsBtnLayout.getLayoutParams();
            reviewsBtnLayoutParams.width = width / 3;
            reviewsBtnLayoutParams.weight = 4;
            reviewsBtnLayout.setLayoutParams(reviewsBtnLayoutParams);

            LinearLayout.LayoutParams galleryBtnLayoutParams = (LinearLayout.LayoutParams) galleryBtnLayout.getLayoutParams();
            galleryBtnLayoutParams.width = width / 3;
            galleryBtnLayoutParams.weight = 4;
            galleryBtnLayout.setLayoutParams(galleryBtnLayoutParams);

        } else {

            int width = storeContentBlockBtns.getLayoutParams().width;

            storeContentBlockBtns.setWeightSum(10);

            LinearLayout.LayoutParams productsBtnLayoutParams = (LinearLayout.LayoutParams) productsBtnLayout.getLayoutParams();
            productsBtnLayoutParams.width = width / 2;
            productsBtnLayoutParams.weight = 5;
            productsBtnLayout.setLayoutParams(productsBtnLayoutParams);


            LinearLayout.LayoutParams reviewsBtnLayoutParams = (LinearLayout.LayoutParams) reviewsBtnLayout.getLayoutParams();
            reviewsBtnLayoutParams.width = width / 2;
            reviewsBtnLayoutParams.weight = 5;
            reviewsBtnLayout.setLayoutParams(reviewsBtnLayoutParams);


            galleryBtnLayout.setVisibility(View.GONE);
            LinearLayout.LayoutParams galleryBtnLayoutParams = (LinearLayout.LayoutParams) galleryBtnLayout.getLayoutParams();
            galleryBtnLayoutParams.width = 0;
            galleryBtnLayoutParams.weight = 0;
            galleryBtnLayout.setLayoutParams(galleryBtnLayoutParams);


        }

        if (storedata.getListImages() != null && storedata.getListImages().size() > 0) {

            mapcontainer.setVisibility(View.VISIBLE);

            Glide.with(getBaseContext())
                    .load(storedata.getListImages().get(0)
                            .getUrl500_500())
                    .centerCrop().placeholder(R.drawable.def_logo)
                    .into(image);

        } else {

            Glide.with(getBaseContext())
                    .load(R.drawable.def_logo)
                    .centerCrop().placeholder(R.drawable.def_logo)
                    .into(image);

        }


        /****
         * Start  : Opening time
         */
        if (storedata.getOpening() == 0 || storedata.getOpening() == -1) {
            if (storedata.getOpening_time_table_list() != null && storedata.getOpening_time_table_list().size() > 0)
                badge_closed.setVisibility(View.VISIBLE);
            else
                badge_closed.setVisibility(View.GONE);

            badge_open.setVisibility(View.GONE);
        } else if (storedata.getOpening() == 1) {

            badge_closed.setVisibility(View.GONE);
            badge_open.setVisibility(View.VISIBLE);
        }

        parseOpeningTime();
        /****
         * End  : Opening time
         */


        Drawable markerDrwable = new IconicsDrawable(context)
                .icon(CommunityMaterial.Icon.cmd_map_marker)
                .color(ResourcesCompat.getColor(context.getResources(), R.color.colorPrimary, null))
                .sizeDp(18);

        addressContent.setText(storedata.getAddress());
        addressContent.setCompoundDrawablePadding(10);
        addressContent.setCompoundDrawables(markerDrwable, null, null, null);

        phoneBtn.setOnClickListener(this);
        if (storedata.getPhone().trim().equals("")) {
            phoneBtn.setVisibility(View.GONE);
        }

        if (storedata.getWebsite() != null && !storedata.getWebsite().equals("null")) {
            btnWebsite.setOnClickListener(this);
            btnWebsite.setVisibility(View.VISIBLE);
        } else {
            btnWebsite.setVisibility(View.GONE);
        }

        if (storedata.getListImages() != null && storedata.getListImages().size() > 1) {

            Drawable camera = new IconicsDrawable(context)
                    .icon(CommunityMaterial.Icon.cmd_camera)
                    .color(ResourcesCompat.getColor(context.getResources(), R.color.colorWhite, null))
                    .sizeDp(12);

            nbrPictures.setText(storedata.getListImages().size() + "");
            nbrPictures.setCompoundDrawables(camera, null, null, null);
            nbrPictures.setCompoundDrawablePadding(10);

        } else {
            nbrPictures.setVisibility(View.GONE);
        }

        Position newPosition = new Position();
        if (mGPS.getLatitude() == 0 && mGPS.getLongitude() == 0) {
            distanceView.setVisibility(View.GONE);
        }


        Double mDistance = newPosition.distance(mGPS.getLatitude(), mGPS.getLongitude(), storedata.getLatitude(), storedata.getLongitude());
        parseDisitanceByUnit(mDistance);

        btnChatCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLogged()) {

                    int userId = 0;
                    try {
                        userId = storedata.getUser().getId();
                    } catch (Exception e) {
                        userId = storedata.getUser_id();
                    }

                    Intent intent = new Intent(StoreDetailActivity.this, MessengerActivity.class);
                    intent.putExtra("type", Discussion.DISCUSION_WITH_USER);
                    intent.putExtra("userId", userId);
                    intent.putExtra("storeName", storedata.getName());
                    startActivity(intent);

                } else {
                    Intent intent = new Intent(context, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });


        new decodeHtml().execute(storedata.getDetail());

        initOfferRV(storedata.getId());

        tabsClickListener();


        productsBtn.setText(String.format(getString(R.string.products)));
        reviewsBtn.setText(getString(R.string.review_title));

        try {

            final Category cat = CategoryController.findId(storedata.getCategory_id());
            categoryContent.setText(cat.getNameCat());

            categoryLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(StoreDetailActivity.this, ListStoresActivity.class);
                    intent.putExtra("category", cat.getNumCat());
                    overridePendingTransition(R.anim.lefttoright_enter, R.anim.lefttoright_exit);
                    startActivity(intent);

                }
            });


            if (cat.getImages() != null) {
                Glide.with(AppController.getInstance()).load(cat.getImages().getUrl500_500())
                        .placeholder(ImageLoaderAnimation.glideLoader(context))
                        .centerCrop().into(catImage);
            }

        } catch (Exception e) {
            categoryLayout.setVisibility(View.GONE);
        }


        if (storedata.getCanChat() == 1 && AppConfig.ENABLE_CHAT) {
            btnChatCustomer.setVisibility(View.VISIBLE);
        }


        if (storedata.isVoted() && menuContext != null) {
            menuContext.findItem(R.id.rate_review).setVisible(true);
        }

    }

    private void parseDisitanceByUnit(Double mDistance) {
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(this);
        String distance_unit = sh.getString("distance_unit", "km");

        String disStr = null;
        if (distance_unit.equals("km")) {
            disStr = Utils.prepareDistanceKm(mDistance)
                    + " " +
                    Utils.getDistanceByKm(mDistance).toLowerCase();
        } else {
            disStr = Utils.prepareDistanceMiles(mDistance)
                    + " " +
                    Utils.getDistanceMiles(mDistance).toLowerCase();
        }
        distanceView.setText(disStr);
    }

    private void tabsClickListener() {
        productsBtnLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProductFrag();
            }
        });

        reviewsBtnLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReviewsFrag();
            }
        });

        galleryBtnLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGalleryFrag();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.phoneBtn) {
            try {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + storedata.getPhone().trim()));
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    String[] permission = new String[]{Manifest.permission.CALL_PHONE};
                    SettingsController.requestPermissionM(StoreDetailActivity.this, permission);
                    return;
                }
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(getApplicationContext(), getString(R.string.store_call_error) + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else if (v.getId() == R.id.websiteBtn) {

            new AwesomeWebView.Builder(StoreDetailActivity.this)
                    .statusBarColorRes(R.color.colorPrimary)
                    .theme(R.style.FinestWebViewAppTheme)
                    .titleColor(ResourcesCompat.getColor(getResources(), R.color.defaultWhiteColor, null))
                    .urlColor(ResourcesCompat.getColor(getResources(), R.color.defaultWhiteColor, null))
                    .show(storedata.getWebsite());
        }
    }


    @Override
    public void onMapLoaded() {

        Toast.makeText(getApplicationContext(), "Map is ready ", Toast.LENGTH_SHORT).show();
    }


    public void syncStore(final int store_id) {

        mViewManager.loading();

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
                    RealmList<Store> list = mStoreParser.getStore();

                    if (list.size() > 0) {

                        StoreController.insertStores(list);

                        storedata = list.get(0);
                        setupDataIntoStoreDetailViews();

                        mViewManager.showResult();


                    } else {


                        Toast.makeText(StoreDetailActivity.this, getString(R.string.store_not_found), Toast.LENGTH_LONG).show();
                        finish();

                    }

                } catch (JSONException e) {
                    //send a rapport to support
                    e.printStackTrace();

                    mViewManager.error();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (APP_DEBUG) {
                    Log.e("ERROR", error.toString());
                }
                mViewManager.error();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();


                params.put("limit", "1");
                params.put("store_id", String.valueOf(store_id));


                return params;
            }

        };


        request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);


    }

    private void showProductFrag() {


        getProductFragment();

        productsBtn.setTextColor(getResources().getColor(R.color.colorWhite));
        productsBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        productsBtnLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        reviewsBtn.setTextColor(getResources().getColor(R.color.colorPrimary));
        reviewsBtn.setBackgroundColor(getResources().getColor(R.color.colorAccentTransparent));
        reviewsBtnLayout.setBackgroundColor(getResources().getColor(R.color.colorAccentTransparent));

        galleryBtn.setTextColor(getResources().getColor(R.color.colorPrimary));
        galleryBtn.setBackgroundColor(getResources().getColor(R.color.colorAccentTransparent));
        galleryBtnLayout.setBackgroundColor(getResources().getColor(R.color.colorAccentTransparent));

        storeContentBlock.setVisibility(View.VISIBLE);

    }

    private void showReviewsFrag() {

        getReviewsFragment();

        productsBtn.setTextColor(getResources().getColor(R.color.colorPrimary));
        productsBtn.setBackgroundColor(getResources().getColor(R.color.colorAccentTransparent));
        productsBtnLayout.setBackgroundColor(getResources().getColor(R.color.colorAccentTransparent));

        reviewsBtn.setTextColor(getResources().getColor(R.color.colorWhite));
        reviewsBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        reviewsBtnLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        galleryBtn.setTextColor(getResources().getColor(R.color.colorPrimary));
        galleryBtn.setBackgroundColor(getResources().getColor(R.color.colorAccentTransparent));
        galleryBtnLayout.setBackgroundColor(getResources().getColor(R.color.colorAccentTransparent));

        storeContentBlock.setVisibility(View.VISIBLE);
    }

    private void showGalleryFrag() {

        getGalleryFragment();

        //setLeftIcon(productsBtn, productIconWhite);
        productsBtn.setTextColor(getResources().getColor(R.color.colorPrimary));
        productsBtn.setBackgroundColor(getResources().getColor(R.color.colorAccentTransparent));
        productsBtnLayout.setBackgroundColor(getResources().getColor(R.color.colorAccentTransparent));

        // setLeftIcon(reviewsBtn, reviewsIconWhite);
        reviewsBtn.setTextColor(getResources().getColor(R.color.colorPrimary));
        reviewsBtn.setBackgroundColor(getResources().getColor(R.color.colorAccentTransparent));
        reviewsBtnLayout.setBackgroundColor(getResources().getColor(R.color.colorAccentTransparent));

        // setLeftIcon(galleryBtn, galleryIconActive);
        galleryBtn.setTextColor(getResources().getColor(R.color.colorWhite));
        galleryBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        galleryBtnLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        storeContentBlock.setVisibility(View.VISIBLE);

    }

    private void getReviewsFragment() {

        try {
            StoreReviewsFragment frag = new StoreReviewsFragment();
            Bundle b = new Bundle();

            if (APP_DEBUG)
                Log.e("_3_store_id", String.valueOf(storedata.getId()));

            b.putInt("store_id", storedata.getId());
            frag.setArguments(b);

            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.scontainer, frag).commit();
        } catch (Exception e) {
            if (APP_DEBUG)
                e.printStackTrace();
        }

    }

    private void getProductFragment() {

        try {
            StoreProductsFragment frag = new StoreProductsFragment();
            Bundle b = new Bundle();

            b.putInt("store_id", storedata.getId());
            frag.setArguments(b);

            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.scontainer, frag).commit();

        } catch (Exception e) {
            if (APP_DEBUG)
                e.printStackTrace();
        }

    }

    private void getGalleryFragment() {

        try {

            GalleryFragment frag = new GalleryFragment();
            frag.setParent_width(storeContentBlockBtns.getWidth());
            frag.setShort_mode(true);


            Bundle b = new Bundle();
            b.putInt("int_id", storedata.getId());
            b.putString("type", "store");
            frag.setArguments(b);
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.scontainer, frag).commit();

        } catch (Exception e) {
            if (APP_DEBUG)
                e.printStackTrace();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == R.id.my_qr) {
            showqr();
        }

        if (item.getItemId() == android.R.id.home) {
            if (!MainActivity.isOpend()) {
                startActivity(new Intent(this, MainActivity.class));
            }
            finish();
        } else if (item.getItemId() == R.id.bookmarks_icon) {
            if (isLogged()) {

                try {
                    User currentUser = SessionsController.getSession().getUser();

                    if (storedata.getSaved() > 0) {
                        removeStoreToBookmarks(this, currentUser.getId(), storedata.getId());
                    } else {
                        saveStoreToBookmarks(this, currentUser.getId(), storedata.getId());
                    }
                } catch (Exception e) {
                    //send a rapport to support
                    if (APP_DEBUG) e.printStackTrace();
                }

            } else {
                startActivity(new Intent(StoreDetailActivity.this, LoginActivity.class));
            }

        } else if (item.getItemId() == R.id.rate_review) {

            focusOnView(R.id.store_content_block);
            showReviewsFrag();

        } else if (item.getItemId() == R.id.send_location) {


            double lat = storedata.getLatitude();
            double lon = storedata.getLongitude();
            String mapLink = null;

            try {
                //https://www.google.com/maps/search/?api=1&query=47.5951518,-122.3316393
                mapLink = "https://maps.google.com/?q=" + URLEncoder.encode(storedata.getAddress(), "UTF-8") + "&ll=" + String.format("%f,%f", lat, lon);
            } catch (UnsupportedEncodingException e) {
                mapLink = "https://maps.google.com/?ll=" + String.format("%f,%f", lat, lon);
                e.printStackTrace();
            }


            @SuppressLint({"StringFormatInvalid", "LocalSuppress", "StringFormatMatches"}) String shared_text =
                    String.format(getString(R.string.shared_text),
                            storedata.getName(),
                            getString(R.string.app_name),
                            storedata.getLink()
                    );

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, shared_text);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

        }


        return super.onOptionsItemSelected(item);
    }

    private void showqr() {

        View view;

        view = getLayoutInflater().inflate(R.layout.qr_code, null);

        myDialog = new Dialog(this);
        myDialog.setContentView(view);

        qr_image = view.findViewById(R.id.qr_image);
        share = view.findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View content = view.findViewById(R.id.qr_image);
               /* content.setDrawingCacheEnabled(true);

                Bitmap bitmap = content.getDrawingCache();*/
                File root = Environment.getExternalStorageDirectory();
                File cachePath = new File(root.getAbsolutePath() + "/DCIM/Camera/image.jpg");
                try {
                    cachePath.createNewFile();
                    FileOutputStream ostream = new FileOutputStream(cachePath);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
                    ostream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/*");
                share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(cachePath));
                startActivity(Intent.createChooser(share, "Share via"));
            }
        });
        inputValue = QRcode_url;
        if (inputValue.length() > 0) {
            WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            int width = point.x;
            int height = point.y;
            int smallerDimension = width < height ? width : height;
            smallerDimension = smallerDimension * 3 / 4;

            qrgEncoder = new QRGEncoder(
                    inputValue, null,
                    QRGContents.Type.TEXT,
                    smallerDimension);
            qrgEncoder.setColorBlack(Color.BLACK);
            qrgEncoder.setColorWhite(Color.WHITE);
            try {
                bitmap = qrgEncoder.getBitmap();
                qr_image.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

            Toast.makeText(context, "Value Required", Toast.LENGTH_SHORT).show();
            //edtValue.setError("Value Required");
        }



      /*  retunto_home = view.findViewById(R.id.retunto_home);
        retunto_home.setTypeface(heding);
        ammo = view.findViewById(R.id.ammo);
        ammo.setTypeface(typebold);
        tv_bank_name = view.findViewById(R.id.tv_bank_name);
        tv_bank_name.setTypeface(heding);

        time = view.findViewById(R.id.time);
        time.setTypeface(normal);

        on = view.findViewById(R.id.on);
        on.setTypeface(typeHeader);

        walletid = view.findViewById(R.id.walletid);
        walletid.setTypeface(typeHeader);
        wallet_id_txt = view.findViewById(R.id.wallet_id_txt);
        wallet_id_txt.setTypeface(normal);
        acoouno = view.findViewById(R.id.acoouno);
        acoouno.setTypeface(normal);
        tv_acou_name = view.findViewById(R.id.tv_acou_name);
        tv_acou_name.setTypeface(normal);
        commi = view.findViewById(R.id.commi);
        commi.setTypeface(normal);
        net_amo = view.findViewById(R.id.net_amo);
        net_amo.setTypeface(normal);

        ac1oouno = view.findViewById(R.id.ac1oouno);
        ac1oouno.setTypeface(typeHeader);

        succ_mess = view.findViewById(R.id.succ_mess);
        succ_mess.setTypeface(normal);*/

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        myDialog.show();
    }

    @Override
    public void onBackPressed() {

        if (!MainActivity.isOpend()) {
            startActivity(new Intent(this, MainActivity.class));
        }
        super.onBackPressed();
    }

    private class decodeHtml extends AsyncTask<String, String, String> {

        @Override
        protected void onPostExecute(final String text) {
            super.onPostExecute(text);
            descriptionContent.setText(Html.fromHtml(text));
            Realm realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    try {
                        storedata.setDetail(text);
                        realm.copyToRealmOrUpdate(storedata);
                    } catch (Exception e) {

                    }

                }
            });
            //eventData.setDescription(text);
        }

        @Override
        protected String doInBackground(String... params) {

            return HtmlEscape.unescapeHtml(params[0]);
        }
    }
}
