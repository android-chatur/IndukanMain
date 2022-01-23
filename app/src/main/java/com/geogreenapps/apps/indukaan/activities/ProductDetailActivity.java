package com.geogreenapps.apps.indukaan.activities;

import static com.geogreenapps.apps.indukaan.appconfig.AppConfig.APP_DEBUG;
import static com.geogreenapps.apps.indukaan.appconfig.AppConfig.SHOW_ADS;
import static com.geogreenapps.apps.indukaan.appconfig.AppConfig.SHOW_ADS_IN_OFFER;
import static com.geogreenapps.apps.indukaan.controllers.sessions.SessionsController.isLogged;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
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
import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.animation.ImageLoaderAnimation;
import com.geogreenapps.apps.indukaan.appconfig.AppConfig;
import com.geogreenapps.apps.indukaan.appconfig.Constances;
import com.geogreenapps.apps.indukaan.classes.Cart;
import com.geogreenapps.apps.indukaan.classes.Guest;
import com.geogreenapps.apps.indukaan.classes.Images;
import com.geogreenapps.apps.indukaan.classes.Product;
import com.geogreenapps.apps.indukaan.classes.Store;
import com.geogreenapps.apps.indukaan.classes.User;
import com.geogreenapps.apps.indukaan.controllers.CampagneController;
import com.geogreenapps.apps.indukaan.controllers.SettingsController;
import com.geogreenapps.apps.indukaan.controllers.cart.CartController;
import com.geogreenapps.apps.indukaan.controllers.sessions.GuestController;
import com.geogreenapps.apps.indukaan.controllers.sessions.SessionsController;
import com.geogreenapps.apps.indukaan.controllers.stores.ProductsController;
import com.geogreenapps.apps.indukaan.controllers.stores.StoreController;
import com.geogreenapps.apps.indukaan.customview.OfferCustomView;
import com.geogreenapps.apps.indukaan.fragments.SlideshowDialogFragment;
import com.geogreenapps.apps.indukaan.helper.CommunFunctions;
import com.geogreenapps.apps.indukaan.load_manager.ViewManager;
import com.geogreenapps.apps.indukaan.network.ServiceHandler;
import com.geogreenapps.apps.indukaan.network.VolleySingleton;
import com.geogreenapps.apps.indukaan.network.api_request.SimpleRequest;
import com.geogreenapps.apps.indukaan.parser.api_parser.ProductParser;
import com.geogreenapps.apps.indukaan.parser.api_parser.StoreParser;
import com.geogreenapps.apps.indukaan.parser.tags.Tags;
import com.geogreenapps.apps.indukaan.utils.DateUtils;
import com.geogreenapps.apps.indukaan.utils.ProductUtils;
import com.geogreenapps.apps.indukaan.utils.TextUtils;
import com.geogreenapps.apps.indukaan.utils.Utils;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.nirhart.parallaxscroll.views.ParallaxScrollView;
import com.wuadam.awesomewebview.AwesomeWebView;

import org.bluecabin.textoo.LinksHandler;
import org.bluecabin.textoo.Textoo;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;
import io.realm.Realm;
import io.realm.RealmList;


public class ProductDetailActivity extends GlobalActivity implements ViewManager.CustomView, OnMapReadyCallback {
    @BindView(R.id.app_bar)
    Toolbar toolbar;
    @BindView(R.id.progressBar)
    SpinKitView progressBar;
    @BindView(R.id.loading)
    LinearLayout loading;
    @BindView(R.id.toolbar_back)
    ImageView toolbar_back;
    @BindView(R.id.phoneBtn)
    ImageButton phoneBtn;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.product_label)
    TextView product_label;
    @BindView(R.id.nbrPictures)
    TextView nbrPictures;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.stock)
    TextView stock;
    @BindView(R.id.priceView)
    TextView priceView;
    @BindView(R.id.old_price)
    TextView old_price;
    AppCompatEditText et_comment;
    @BindView(R.id.detail_product)
    TextView detailProduct;
    @BindView(R.id.product_up_to)
    TextView productUpTo;
    @BindView(R.id.storeBtn)
    TextView storeBtn;
    @BindView(R.id.storeBtn1)
    TextView storeBtn1;
    @BindView(R.id.btn_chat_customer)
    ImageButton btn_chat_customer;
    @BindView(R.id.storeBtnLayout)
    LinearLayout storeBtnLayout;
    @BindView(R.id.adView)
    AdView adView;
    @BindView(R.id.ads)
    LinearLayout ads;
    String Comment;
    ImageButton save_comment;
    @BindView(R.id.deal_layout)
    LinearLayout dealLayout;
    @BindView(R.id.mScroll)
    ParallaxScrollView mScroll;
    @BindView(R.id.mapBtn)
    ImageButton mapBtn;
    @BindView(R.id.progressMapLL)
    LinearLayout progressMapLL;
    @BindView(R.id.layout_custom_order)
    LinearLayout layout_custom_order;
    @BindView(R.id.product_type)
    TextView product_type;
    @BindView(R.id.product_value)
    TextView product_value;
    @BindView(R.id.followors)
    TextView followors;
    @BindView(R.id.btn_custom_order)
    AppCompatButton btnCustomOrder;
    private GoogleMap mMap;
    private RequestQueue queue;
    private GPStracker mGPS;
    private Context context;
    private OfferCustomView horizontalOfferList;
    private Store storedata;
    private Store relatedStore;
    private int product_id = 0;
    private ViewManager mViewManager;
    private Product productData;
    private Menu menuContext;
    private LatLng customerPosition;
    // custom quantity fields
    private int custom_qte = 1;
    private float custom_price = -1;
    private float original_price = -1;
    private HashMap<String, Object> cartlist;

    @OnClick(R.id.btn_custom_order)
    void onOrderClick(View view) {

        //check if order is associated t the opening time
        if (productData.getStore_order_enabled() == 1 && productData.getStore_order_based_on_op() == 1) {

            if (StoreController.getStore(productData.getStore_id()) != null) {
                relatedStore = StoreController.getStore(productData.getStore_id());
            } else {
                syncStoreFromAPI(productData.getStore_id());
            }

            if (relatedStore != null)
                if (relatedStore.getOpening() != 1) {  // if store is closed disable button click
                    Toast.makeText(ProductDetailActivity.this, relatedStore.getName() + getString(R.string.store_closed_order), Toast.LENGTH_LONG).show();
                    return;
                }
        }


        if (SessionsController.isLogged()) {

            //check pickup from
            if (!CartController.checkProductStore(productData.getStore_id(), SessionsController.getSession().getUser().getId())) {
                Toast.makeText(ProductDetailActivity.this, getString(R.string.order_items_error), Toast.LENGTH_LONG).show();
                return;
            }

            //check product stock
            if (productData.getStock() == 0) {
                Toast.makeText(ProductDetailActivity.this, getString(R.string.out_of_stock), Toast.LENGTH_LONG).show();
                return;
            }


            //fill cart detail
            Cart mCart = new Cart();
            mCart.setModule_id(productData.getId());
            mCart.setModule(Constances.ModulesConfig.PRODUCT_MODULE);
            mCart.setAmount(productData.getProduct_value());
            mCart.setProduct(productData);
            mCart.setQte(1);

            mCart.setParent_id(productData.getStore_id());
            mCart.setUser_id(SessionsController.getSession().getUser().getId());


            //check if the product has  variants
            if (productData.getVariants() != null && productData.getVariants().size() > 0) {

                Intent intent = new Intent(ProductDetailActivity.this, ProductVariantsActivity.class);

                intent.putExtra("module_id", productData.getId());
                intent.putExtra("module", Constances.ModulesConfig.PRODUCT_MODULE);

                startActivity(intent);
                overridePendingTransition(R.anim.lefttoright_enter, R.anim.lefttoright_exit);
                finish();

            } else {
                if (productData.getQty_enabled() > 0) {
                    showBottomSheetDialog(mCart);
                } else {
                    Intent intent = new Intent(new Intent(ProductDetailActivity.this, OrderCheckoutActivity.class));

                    //save cart in the database
                    CartController.addProductToCard(mCart);

                    intent.putExtra("module_id", productData.getId());
                    intent.putExtra("module", Constances.ModulesConfig.PRODUCT_MODULE);

                    startActivity(intent);
                    overridePendingTransition(R.anim.lefttoright_enter, R.anim.lefttoright_exit);
                    finish();

                }
            }


        } else {
            Intent intent = new Intent(ProductDetailActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    protected void onResume() {

        if (adView != null)
            adView.resume();

        super.onResume();
    }

    @Override
    protected void onPause() {

        if (adView != null)
            adView.pause();

        super.onPause();
    }

    @Override
    protected void onDestroy() {

        if (adView != null)
            adView.destroy();

        super.onDestroy();
    }

    private void toolbarTransactionScroll() {
        //set default color toolbar
        toolbar_back.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mScroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (AppConfig.APP_DEBUG)
                        Log.e("onScrollChange", "scrollX=" + scrollX + ";scrollY=" + scrollY);

                    if (scrollY < 600) {
                        toolbar.setBackground(getDrawable(R.drawable.gradient_bg_top_to_bottom_70));
                        toolbarTitle.setVisibility(View.GONE);
                        toolbar_back.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            menuContext.findItem(R.id.rate_review).setIconTintList(ContextCompat.getColorStateList(getApplicationContext(), android.R.color.white));
                            menuContext.findItem(R.id.send_location).setIconTintList(ContextCompat.getColorStateList(getApplicationContext(), android.R.color.white));
                        }

                    } else {
                        toolbar.setBackgroundColor(getColor(R.color.toolbarColor));
                        toolbarTitle.setTextColor(getColor(R.color.color_toolbar_action));
                        toolbarTitle.setVisibility(View.VISIBLE);
                        toolbar_back.setColorFilter(getResources().getColor(R.color.color_toolbar_action), PorterDuff.Mode.SRC_ATOP);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            menuContext.findItem(R.id.rate_review).setIconTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.color_toolbar_action));
                            menuContext.findItem(R.id.send_location).setIconTintList(ContextCompat.getColorStateList(getApplicationContext(), R.color.color_toolbar_action));
                        }
                    }
                }
            });
        }
    }


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this);
        save_comment = findViewById(R.id.save_comment);
        et_comment = findViewById(R.id.et_comment);

        setupToolbar();
        toolbarTransactionScroll();
        cartlist = new HashMap<>();

        //ADD ADMOB BANNER
        if (SHOW_ADS && SHOW_ADS_IN_OFFER) {

            ads.setVisibility(View.VISIBLE);

            adView = findViewById(R.id.adView);
            adView.setVisibility(View.VISIBLE);
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice("4A55E4EA2535643C0D74A5A73C4F550A")
                    .addTestDevice("3CB74DFA141BF4D0823B8EA7D94531B5")
                    .build();
            adView.loadAd(adRequest);
        }

        //INIT VIEW MANAGER
        mViewManager = new ViewManager(this);
        mViewManager.setLoadingLayout(findViewById(R.id.loading));
        mViewManager.setResultLayout(findViewById(R.id.content_product));
        mViewManager.setErrorLayout(findViewById(R.id.error));
        mViewManager.setEmpty(findViewById(R.id.empty));
        mViewManager.setCustumizeView(this);

        mViewManager.loading();


        try {


            //get it from external url (deep linking)
            if (product_id == 0) {
                try {

                    Intent appLinkIntent = getIntent();
                    String appLinkAction = appLinkIntent.getAction();
                    Uri appLinkData = appLinkIntent.getData();

                    if (appLinkAction != null && appLinkAction.equals(Intent.ACTION_VIEW)) {

                        if (APP_DEBUG)
                            Toast.makeText(getApplicationContext(), appLinkData.toString(), Toast.LENGTH_LONG).show();
                        product_id = Utils.dp_get_id_from_url(appLinkData.toString(), "product_id");
                        attachMap();
                        if (APP_DEBUG)
                            Log.e("product_id", product_id + "");

                        if (APP_DEBUG)
                            Toast.makeText(getApplicationContext(), "The ID: " + product_id + " " + appLinkAction, Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (product_id == 0)
                product_id = getIntent().getExtras().getInt("product_id");


            if (product_id == 0)
                product_id = getIntent().getExtras().getInt("id");


            if (APP_DEBUG)
                Toast.makeText(this, String.valueOf(product_id), Toast.LENGTH_LONG).show();


        } catch (Exception e) {
            e.printStackTrace();
            finish();
        }

        final Product mProduct = ProductsController.findProductById(product_id);

        //OFFLINE MODE
        if (ServiceHandler.isNetworkAvailable(this)) {
            getProduct(product_id);
        } else {
            if (mProduct != null && mProduct.isLoaded() && mProduct.isValid()) {
                productData = mProduct;
                setupViews();
                mViewManager.showResult();


            } else {
                getProduct(product_id);
            }
        }

        phoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + storedata.getPhone()));
                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                        String[] permission = new String[]{Manifest.permission.CALL_PHONE};
                        SettingsController.requestPermissionM(ProductDetailActivity.this, permission);
                        return;
                    }
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getApplicationContext(), getString(R.string.store_call_error) + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_chat_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    // String smsNumber = edittextSmsNumber.getText().toString();
                    String smsNumber = storedata.getPhone();
                    String smsText = "hello";

                    Uri uri = Uri.parse("smsto:" + smsNumber);
                    Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                    intent.putExtra("sms_body", smsText);
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getApplicationContext(), getString(R.string.store_call_error) + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (productData != null && productData.getListImages().size() > 0) {
                    List<Images> listProductsImg = productData.getListImages();
                    SlideshowDialogFragment.newInstance().show(ProductDetailActivity.this, listProductsImg, 0);
                }


            }
        });

        nbrPictures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (productData != null && productData.getListImages().size() > 0) {
                    List<Images> listProductsImg = productData.getListImages();
                    SlideshowDialogFragment.newInstance().show(ProductDetailActivity.this, listProductsImg, 0);
                }


            }
        });


        /*
         *
         *   DATE & COUNTDOWN
         *
         */

        String date = "";


        try {
            date = mProduct.getDate_start();
            date = DateUtils.prepareOutputDate(date, "dd MMMM yyyy  hh:mm", this);
        } catch (Exception e) {

            getProduct(product_id);
            return;

        }
        initGLobalParams();

        buttonClickListener();
        final Guest guest = GuestController.getGuest();
        int gid = 0;
        if (guest != null)
            gid = guest.getId();

        final int finalGid = gid;


        followors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isLogged()) {

                    try {
                        User currentUser = SessionsController.getSession().getUser();

                        if (storedata.getSaved() > 0) {
                            removeStoreToBookmarks(ProductDetailActivity.this, currentUser.getId(), storedata.getId());
                        } else {
                            saveStoreToBookmarks(ProductDetailActivity.this, currentUser.getId(), storedata.getId());
                        }
                    } catch (Exception e) {
                        //send a rapport to support
                        if (APP_DEBUG) e.printStackTrace();
                    }

                } else {
                    startActivity(new Intent(ProductDetailActivity.this, LoginActivity.class));
                }
            }
        });

        save_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLogged()) {


                    if (validate()) {
                        //send review

                        sendReview(


                                Comment,
                                finalGid

                        );

                    } else {
                        Toast.makeText(ProductDetailActivity.this, "Please enter Comments", Toast.LENGTH_SHORT).show();

                    }


                } else {
                    Intent intent = new Intent(context, LoginActivity.class);
                    startActivity(intent);
                    finish();
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
//                        if (storedata != null) {
//                            setBookmarkMenu();
//                        }
                        followors.setText("Follow");

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
                    Log.d("respo", response);
                    if (jsonObject.getInt(Tags.SUCCESS) == 1) {

                        storedata = StoreController.doSave(storedata.getId(), 1);
                        followors.setText("Following");
                        /*if (storedata != null) {
                            setBookmarkMenu();
                        }*/

                        //check if notification agreement is enabled
//                        Setting defaultAppSetting = SettingsController.findSettingFiled("_NOTIFICATION_AGREEMENT_USE");
//                        if (defaultAppSetting != null && defaultAppSetting.getValue().equals("1"))
//                            showBottomSheetDialog(jsonObject.getInt(Tags.RESULT));

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


    private boolean validate() {
        Comment = et_comment.getText().toString().trim();

        if (android.text.TextUtils.isEmpty(et_comment.getText().toString())) {
            et_comment.setError("Please Enter Password");
            et_comment.requestFocus();
            return false;
        }
        return true;
    }

    public void sendReview(String review, int guest_id) {

        RequestQueue queue = VolleySingleton.getInstance(ProductDetailActivity.this).getRequestQueue();
        queue = VolleySingleton.getInstance(ProductDetailActivity.this).getRequestQueue();


        User user = SessionsController.getSession().getUser();


        Log.d("pro", String.valueOf(productData.getStore_id()));
        Log.d("user", user.getUsername());

        if (review.trim().trim().equals(""))
            review = " ";


        final Map<String, String> params = new HashMap<String, String>();

        params.put("store_id", productData.getStore_id() + "");
        params.put("rate", "" + "");
        params.put("review", review + "");
        params.put("pseudo", user.getUsername() + "");
        params.put("guest_id", guest_id + "");
        try {
            params.put("token", Utils.getToken(AppController.getInstance()));
        } catch (Exception e) {

        }
        params.put("mac_adr", ServiceHandler.getMacAddr());
        params.put("limit", "7");

        SimpleRequest request = new SimpleRequest(Request.Method.POST, Constances.API.API_RATING_STORE, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                Log.d("resul123", response);

                try {

                    try {


                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    JSONObject jso = new JSONObject(response);
                    int success = jso.getInt("success");
                    if (success == 1) {
                        et_comment.getText().clear();
                        Toast.makeText(ProductDetailActivity.this, getString(R.string.thankYou), Toast.LENGTH_LONG).show();

                        final Store store = StoreController.findStoreById(productData.getStore_id());
                        if (store != null) {
                            Realm realm = Realm.getDefaultInstance();
                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    store.setNbr_votes(String.valueOf((Integer.parseInt(store.getNbr_votes()) + 1)));
                                    realm.copyToRealmOrUpdate(store);
                                }
                            });
                        }

                    } else {

//                        mainLayout.setVisibility(View.VISIBLE);
//                        progress.setVisibility(View.GONE);

                    }

                    //add view

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (APP_DEBUG) Log.e("ERROR", error.toString());
            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                return params;
            }

        };


        request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);

    }

    private void initGLobalParams() {
        toolbar_back.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        queue = VolleySingleton.getInstance(this).getRequestQueue();
        context = this;
        mScroll = findViewById(R.id.mScroll);
        horizontalOfferList = findViewById(R.id.horizontalOfferList);
        //Initialize map fragment
        mGPS = new GPStracker(this);
        // shareBtn.setVisibility(View.GONE); //HIDE THE SHARE BTN
    }

    private void buttonClickListener() {

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (AppConfig.ENABLE_LOCAL_MAPS_DIRECTION) {
                    if (storedata != null && mGPS.canGetLocation()) {

                        Intent intent = new Intent(ProductDetailActivity.this, MapDirectionActivity.class);
                        intent.putExtra("latitude", storedata.getLatitude() + "");
                        Log.d("asdf", String.valueOf(storedata.getLatitude()));
                        intent.putExtra("longitude", storedata.getLongitude() + "");
                        // intent.putExtra("longitude", storedata.getLng() + "");
                        intent.putExtra("name", storedata.getName() + "");
                        intent.putExtra("description", storedata.getAddress() + "");
                        intent.putExtra("distance", storedata.getDistance() + "");

                        startActivity(intent);

                    } else if (!mGPS.canGetLocation()) {
                        mGPS.showSettingsAlert();
                        Toast.makeText(ProductDetailActivity.this, R.string.enable_gps_map_direction, Toast.LENGTH_LONG).show();
                    } else if (!ServiceHandler.isNetworkAvailable(context)) {
                        mGPS.showSettingsAlert();
                        Toast.makeText(ProductDetailActivity.this, R.string.enable_network_map_direction, Toast.LENGTH_LONG).show();
                    }
                } else {
                    Uri gmmIntentUri = Uri.parse(String.format(Locale.ENGLISH, "http://maps.google.com/maps?q=loc:%f,%f", productData.getLat(), productData.getLng()));

                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    if (mapIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(mapIntent);
                    }
                }

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        menuContext = menu;


        /////////////////////////////
        menu.findItem(R.id.send_location).setVisible(true);
        Drawable send_location = new IconicsDrawable(this)
                .icon(CommunityMaterial.Icon.cmd_share_variant)
                .color(ResourcesCompat.getColor(getResources(), R.color.defaultWhiteColor, null))
                .sizeDp(22);
        menu.findItem(R.id.send_location).setIcon(send_location);
        /////////////////////////////


        return true;
    }


    public void onClick(View v) {
        if (v.getId() == R.id.phoneBtn) {
            try {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                // intent.setData(Uri.parse("tel:" + productData.getPhone().trim()));
                intent.setData(Uri.parse("tel:" + "9997775588"));
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    String[] permission = new String[]{Manifest.permission.CALL_PHONE};
                    SettingsController.requestPermissionM(ProductDetailActivity.this, permission);
                    return;
                }
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(getApplicationContext(), getString(R.string.store_call_error) + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else if (v.getId() == R.id.websiteBtn) {

            new AwesomeWebView.Builder(ProductDetailActivity.this)
                    .statusBarColorRes(R.color.colorPrimary)
                    .theme(R.style.FinestWebViewAppTheme)
                    .titleColor(ResourcesCompat.getColor(getResources(), R.color.defaultWhiteColor, null))
                    .urlColor(ResourcesCompat.getColor(getResources(), R.color.defaultWhiteColor, null));
            //.show(productData.getWebsite());
        }
    }


    public void onMapLoaded() {

        Toast.makeText(getApplicationContext(), "Map is ready ", Toast.LENGTH_SHORT).show();
    }


    private void setupViews() {

        toolbarTitle.setText(productData.getName());
        product_label.setText(productData.getName());
        attachMap();

        if (productData.getStore_id() > 0) {


            Drawable storeDrawable = new IconicsDrawable(this)
                    .icon(CommunityMaterial.Icon.cmd_map_marker)
                    .color(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null))
                    .sizeDp(18);


            if (productData.getOriginal_value() != 0) {
                double original_price = productData.getOriginal_value();
                old_price.setText(ProductUtils.parseCurrencyFormat((float) original_price, productData.getCurrency()));
                old_price.setPaintFlags(old_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                old_price.setVisibility(View.VISIBLE);
            } else {
                old_price.setVisibility(View.GONE);
            }


            if (productData.getStock() > 0 && productData.getOriginal_value() == 0) {
                this.stock.setTextColor(ResourcesCompat.getColor(getResources(), R.color.green, null));
                this.stock.setText(getString(R.string.in_stock));
                this.stock.setVisibility(View.VISIBLE);
            } else if (productData.getStock() == 0) {
                this.stock.setTextColor(ResourcesCompat.getColor(getResources(), R.color.red, null));
                this.stock.setText(getString(R.string.out_of_stock));
                this.stock.setVisibility(View.VISIBLE);
                this.old_price.setVisibility(View.GONE);


            } else {
                this.stock.setVisibility(View.GONE);
            }


            storeBtn1.setText(productData.getStore_name());

            if (AppController.isRTL()) {
                storeBtn.setCompoundDrawables(null, null, storeDrawable, null);
            } else {
                storeBtn.setCompoundDrawables(storeDrawable, null, null, null);
            }

            storeBtn.setCompoundDrawablePadding(20);
            storeBtn1.setCompoundDrawablePadding(10);
            storeBtn.setPaintFlags(storeBtn.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


            //fixing bug reported in crashlytics : io.realm.internal.UncheckedRow.nativeGetLong
            final int store_id = productData.getStore_id();

            syncStore(store_id);

            storeBtnLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {

                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();

                        if (!StoreDetailActivity.isOpend()) {
                            Intent intent = new Intent(ProductDetailActivity.this, StoreDetailActivity.class);
                            intent.putExtra("id", store_id);
                            startActivity(intent);
                        }

                        realm.commitTransaction();

                    } catch (Exception e) {
                        if (APP_DEBUG)
                            e.printStackTrace();

                        Toast.makeText(ProductDetailActivity.this, getString(R.string.store_not_found), Toast.LENGTH_LONG).show();

                    }

                }
            });

            storeBtnLayout.setVisibility(View.VISIBLE);
        } else
            storeBtnLayout.setVisibility(View.GONE);


        if (productData.getListImages() != null && productData.getListImages().size() > 1) {

            Drawable camera = new IconicsDrawable(this)
                    .icon(CommunityMaterial.Icon.cmd_camera)
                    .color(ResourcesCompat.getColor(getResources(), R.color.colorWhite, null))
                    .sizeDp(12);

            nbrPictures.setText(productData.getListImages().size() + "");
            nbrPictures.setCompoundDrawables(camera, null, null, null);
            nbrPictures.setCompoundDrawablePadding(10);

        } else {
            nbrPictures.setVisibility(View.GONE);
        }


        parseProductValue(priceView, -1);


        if (productData.getImages() != null)
            Glide.with(AppController.getInstance())
                    .load(productData.getImages().getUrl500_500())
                    .placeholder(ImageLoaderAnimation.glideLoader(this))
                    .into(image);
        //  .fitCenter().into(image);


        detailProduct.setText(productData.getDescription());
        new TextUtils.decodeHtml(detailProduct).execute(productData.getDescription());

        Textoo
                .config(detailProduct)
                .linkifyWebUrls()  // or just .linkifyAll()
                .addLinksHandler(new LinksHandler() {
                    @Override
                    public boolean onClick(View view, String url) {

                        if (Utils.isValidURL(url)) {

                            new AwesomeWebView.Builder(ProductDetailActivity.this)
                                    .showMenuOpenWith(false)
                                    .statusBarColorRes(R.color.colorPrimary)
                                    .theme(R.style.FinestWebViewAppTheme)
                                    .titleColor(
                                            ResourcesCompat.getColor(getResources(), R.color.defaultWhiteColor, null)
                                    ).urlColor(
                                    ResourcesCompat.getColor(getResources(), R.color.defaultWhiteColor, null)
                            ).show(url);

                            return true;
                        } else {
                            return false;
                        }
                    }
                })
                .apply();

        try {

            if (getIntent().hasExtra("cid")) {
                int cid = Integer.parseInt(getIntent().getExtras().getString("cid"));
                CampagneController.markView(cid);
            }
        } catch (Exception e) {
            if (APP_DEBUG)
                e.printStackTrace();
        }


        Drawable storeDrawable = new IconicsDrawable(this)
                .icon(CommunityMaterial.Icon.cmd_calendar)
                .color(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null))
                .sizeDp(18);
        productUpTo.setCompoundDrawables(storeDrawable, null, null, null);
        productUpTo.setCompoundDrawablePadding(20);


        setupCountDown();


        if (productData.getOrder_enabled() == 1 && SettingsController.isModuleEnabled(Constances.ModulesConfig.ORDERS_MODULE)) {


            if (productData.getProduct_type().equalsIgnoreCase("Percent") && (productData.getProduct_value() > 0 || productData.getProduct_value() < 0)) {
                DecimalFormat decimalFormat = new DecimalFormat("#0");

                product_type.setText(getResources().getString(R.string.percent));
                product_value.setText(decimalFormat.format(productData.getProduct_value()) + "%");

            } else {

                if (productData.getProduct_type().equalsIgnoreCase("Price") && productData.getProduct_value() != 0) {

                    product_type.setText(getResources().getString(R.string.price));

                    product_value.setText(String.format(ProductUtils.parseCurrencyFormat(
                            custom_price > 0 ? custom_price : productData.getProduct_value(),
                            productData.getCurrency())));

                } else {
                    product_value.setVisibility(View.GONE);
                    product_type.setText(getString(R.string.promo));
                }
            }


            if (productData.getOrder_button() != null && !productData.getOrder_button().equalsIgnoreCase("")) {
                btnCustomOrder.setText(productData.getOrder_button().toUpperCase());
            }
            layout_custom_order.setVisibility(View.VISIBLE);

        } else {
            btnCustomOrder.setVisibility(View.GONE);
        }


        //hide order action when store doesn't allow order option
        if (productData.getStore_order_enabled() != 1)
            layout_custom_order.setVisibility(View.GONE);


    }

    private void syncStore(int store_id) {

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
                        Log.d("data1234", storedata.getAddress());
                        setupDataIntoStoreDetailViews();

                        mViewManager.showResult();
                        storeBtn.setText(storedata.getAddress());

                    } else {


                        Toast.makeText(ProductDetailActivity.this, getString(R.string.store_not_found), Toast.LENGTH_LONG).show();
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

    private void setupDataIntoStoreDetailViews() {

        // setBookmarkMenu();

        // showProductFrag();

        attachMap();


       /* description_label.setText(storedata.getName());
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


        *//****
         * Start  : Opening time
         *//*
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
        *//****
         * End  : Opening time
         *//*


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
*/

        /*new StoreDetailActivity.decodeHtml().execute(storedata.getDetail());

        initOfferRV(storedata.getId());

        tabsClickListener();


        productsBtn.setText(String.format(getString(R.string.products)));
        reviewsBtn.setText(getString(R.string.review_title));*/

        /*try {

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
*/

           /* if (cat.getImages() != null) {
                Glide.with(AppController.getInstance()).load(cat.getImages().getUrl500_500())
                        .placeholder(ImageLoaderAnimation.glideLoader(context))
                        .centerCrop().into(catImage);
            }*/

    } /*catch (Exception e) {
           // categoryLayout.setVisibility(View.GONE);
        }*/


        /*if (storedata.getCanChat() == 1 && AppConfig.ENABLE_CHAT) {
            btnChatCustomer.setVisibility(View.VISIBLE);
        }


        if (storedata.isVoted() && menuContext != null) {
            menuContext.findItem(R.id.rate_review).setVisible(true);
        }*/


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
                mSupportMapFragment.getMapAsync(
                        ProductDetailActivity.this);
            }

        } catch (Exception e) {
            progressMapLL.setVisibility(View.GONE);
        }

    }

    private void parseProductValue(TextView priceView, float custom_price) {
        if (productData.getProduct_type().equalsIgnoreCase("Percent") && (productData.getProduct_value() > 0 || productData.getProduct_value() < 0)) {
            DecimalFormat decimalFormat = new DecimalFormat("#0");

            priceView.setText(String.format(getString(R.string.product_off), decimalFormat.format(productData.getProduct_value()) + "%"));
            priceView.setVisibility(View.VISIBLE);

        } else {

            if (productData.getProduct_type().equalsIgnoreCase("Price") && productData.getProduct_value() != 0) {

                priceView.setText(ProductUtils.parseCurrencyFormat(custom_price > 0 ? custom_price : productData.getProduct_value(), productData.getCurrency()));
                priceView.setVisibility(View.VISIBLE);

            } else {
                priceView.setVisibility(View.VISIBLE);
                priceView.setText(getString(R.string.promo));
            }
        }
    }

    private void setupCountDown() {

        String dateStartAt = "";
        String dateEndAt = "";


        try {
            dateStartAt = productData.getDate_start();
            dateStartAt = DateUtils.prepareOutputDate(dateStartAt, "dd MMMM yyyy", this);
        } catch (Exception e) {
            return;
        }

        try {
            dateEndAt = productData.getDate_end();
            dateEndAt = DateUtils.prepareOutputDate(dateEndAt, "dd MMMM yyyy", this);
        } catch (Exception e) {
            return;
        }


        String inputDateSatrt = DateUtils.prepareOutputDate(productData.getDate_start(), "yyyy-MM-dd HH:mm:ss", this);
        long diff_Will_Start = DateUtils.getDiff(inputDateSatrt, "yyyy-MM-dd HH:mm:ss");

        if (APP_DEBUG) {

            Log.e("_start_at_server", productData.getDate_start());
            Log.e("_start_at_device ", dateStartAt);
            Log.e("_start_at_diff ", String.valueOf(diff_Will_Start));
        }

        if (diff_Will_Start > 0) {

            if (productData.getIs_deal() == 1) {
                CountdownView mCvCountdownView = findViewById(R.id.cv_countdownViewTest1);
                /*if (mCvCountdownView.getDay() > 1) {
                    ((TextView) findViewById(R.id.cd_btn)).setText(R.string.ends_in + " " + mCvCountdownView.getDay());
                    diff_Will_Start = diff_Will_Start - (mCvCountdownView.getDay() * 3600000);
                }*/

                mCvCountdownView.start(diff_Will_Start); // Millisecond
                dealLayout.setVisibility(View.VISIBLE);
            }

            productUpTo.setText(String.format(getString(R.string.product_start_at), dateStartAt));

        }


        String inputDateEnd = DateUtils.prepareOutputDate(productData.getDate_end(), "yyyy-MM-dd HH:mm:ss", this);
        long diff_will_end = DateUtils.getDiff(inputDateEnd, "yyyy-MM-dd HH:mm:ss");


        if (APP_DEBUG) {
            Log.e("_end_at_server", productData.getDate_end());
            Log.e("_end_at_device ", dateEndAt);
            Log.e("_end_at_diff ", String.valueOf(diff_will_end));
        }

        if (diff_will_end > 0 && diff_Will_Start < 0) {


            if (productData.getIs_deal() == 1) {
                CountdownView mCvCountdownView = findViewById(R.id.cv_countdownViewTest1);
                mCvCountdownView.start(diff_will_end); // Millisecond
                dealLayout.setVisibility(View.VISIBLE);

            }

            productUpTo.setText(String.format(getString(R.string.product_end_at), dateEndAt));

        }


        if (diff_Will_Start < 0 && diff_will_end < 0) {
            productUpTo.setText(String.format(getString(R.string.product_ended_at), dateEndAt));
        }


    }


    public void setupToolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
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


    public void getProduct(final int product_id) {

        mViewManager.loading();

        final GPStracker mGPS = new GPStracker(this);

        SimpleRequest request = new SimpleRequest(Request.Method.POST,
                Constances.API.API_GET_PRODUCTS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                mViewManager.showResult();


                try {

                    if (APP_DEBUG) {
                        Log.e("responseProductsString", response);
                    }

                    JSONObject jsonObject = new JSONObject(response);
                    final ProductParser mProductParser = new ProductParser(jsonObject);
                    RealmList<Product> list = mProductParser.getProducts();

                    if (list.size() > 0) {

                        ProductsController.insertProducts(list);

                        productData = list.get(0);
                        setupViews();


                    } else {

                        Toast.makeText(ProductDetailActivity.this, getString(R.string.store_not_found), Toast.LENGTH_LONG).show();
                        finish();

                    }

                } catch (JSONException e) {
                    //send a rapport to support
                    if (APP_DEBUG)
                        e.printStackTrace();

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

                if (mGPS.canGetLocation()) {
                    params.put("lat", mGPS.getLatitude() + "");
                    params.put("lng", mGPS.getLongitude() + "");
                }

                params.put("limit", "1");
                params.put("product_id", product_id + "");

                if (APP_DEBUG) {
                    Log.e("ProductDetail", "  params getProducts :" + params.toString());
                }

                return params;
            }

        };


        request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        VolleySingleton.getInstance(this).getRequestQueue().add(request);

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
    public boolean onOptionsItemSelected(MenuItem item) {

        if (android.R.id.home == item.getItemId()) {
            if (!MainActivity.isOpend()) {
                startActivity(new Intent(this, MainActivity.class));
            }
            finish();
        }

        if (item.getItemId() == R.id.send_location) {
            {
                @SuppressLint({"StringFormatInvalid", "LocalSuppress", "StringFormatMatches"}) String shared_text =
                        String.format(getString(R.string.shared_text),
                                productData.getName(),
                                getString(R.string.app_name),
                                productData.getLink()
                        );


                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, shared_text);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

            }
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {

        if (!MainActivity.isOpend()) {
            startActivity(new Intent(this, MainActivity.class));
        } else {

        }

        super.onBackPressed();
    }


    private void showBottomSheetDialog(Cart mCart) {


        FrameLayout bottom_sheet = findViewById(R.id.bottom_sheet);
        bottom_sheet.setVisibility(View.VISIBLE);

        final BottomSheetBehavior[] mBehavior = {BottomSheetBehavior.from(bottom_sheet)};

        final BottomSheetDialog[] mBottomSheetDialog = {new BottomSheetDialog(this)};


        if (mBehavior[0].getState() == BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior[0].setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

        final View[] view = {getLayoutInflater().inflate(R.layout.order_quantity_sheet, null)};

        if (productData != null) {

            //set default  values
            if (productData.getProduct_type().equalsIgnoreCase("Price") && productData.getProduct_value() != 0) {
                custom_price = original_price = productData.getProduct_value();
                custom_qte = 1;
            }

            //set product_id image
            if (productData.getImages() != null) {
                Glide.with(AppController.getInstance())
                        .load(productData.getImages().getUrl200_200())
                        .centerCrop()
                        .placeholder(ImageLoaderAnimation.glideLoader(this))

                        .centerCrop().into(((ImageView) view[0].findViewById(R.id.image_product)));
                //set product_id name
                ((TextView) view[0].findViewById(R.id.product_name)).setText(productData.getName());

                //set product_id price
                parseProductValue(view[0].findViewById(R.id.product_price), -1);

                //action qte buttons
                (view[0].findViewById(R.id.btn_less_qte)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (custom_qte <= 1) return;
                        custom_qte--;
                        if (original_price < custom_price) {
                            custom_price = custom_price - original_price;
                        }
                        //set custom quantity
                        ((TextView) view[0].findViewById(R.id.product_quantity)).setText(custom_qte + "");

                        //set custom price
                        parseProductValue(view[0].findViewById(R.id.product_price), custom_price);

                    }
                });

                (view[0].findViewById(R.id.btn_more_qte)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        custom_qte++;
                        custom_price = custom_price + original_price;

                        //set custom quantity
                        ((TextView) view[0].findViewById(R.id.product_quantity)).setText(custom_qte + "");

                        //set custom price
                        parseProductValue(view[0].findViewById(R.id.product_price), custom_price);

                    }
                });


            }


            (view[0].findViewById(R.id.bt_confirm)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(new Intent(ProductDetailActivity.this, ProductCartActivity.class));

                    mCart.setQte(custom_qte);

                    //save cart in the database
                    CartController.addProductToCard(mCart);

                    intent.putExtra("module_id", productData.getId());
                    intent.putExtra("module", Constances.ModulesConfig.PRODUCT_MODULE);

                    startActivity(intent);
                    overridePendingTransition(R.anim.lefttoright_enter, R.anim.lefttoright_exit);
                    finish();


                    mBottomSheetDialog[0].dismiss();

                }
            });

            (view[0].findViewById(R.id.btn_add_to_cart)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    mCart.setQte(custom_qte);
                    //save cart in the database
                    if (CartController.addProductToCard(mCart))
                        Toast.makeText(ProductDetailActivity.this, getResources().getText(R.string.sucess_add_to_cart), Toast.LENGTH_LONG).show();


                    mBottomSheetDialog[0].dismiss();
                }
            });

            mBottomSheetDialog[0].setContentView(view[0]);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mBottomSheetDialog[0].getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }

            mBottomSheetDialog[0].show();
            mBottomSheetDialog[0].setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    mBottomSheetDialog[0] = null;
                }

            });
        }

    }


    public void syncStoreFromAPI(final int store_id) {

        RequestQueue queue = VolleySingleton.getInstance(this).getRequestQueue();

        SimpleRequest request = new SimpleRequest(Request.Method.POST,
                Constances.API.API_USER_GET_STORES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);

                    final StoreParser mStoreParser = new StoreParser(jsonObject);
                    RealmList<Store> list = mStoreParser.getStore();

                    if (list.size() > 0) {
                        relatedStore = list.get(0);
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

                //
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mMap = googleMap;
        if (productData.getLat() != null && productData.getLng() != null) {

            double TraderLat = productData.getLat();
            double TraderLng = productData.getLng();
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

}
