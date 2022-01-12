package com.geogreenapps.apps.indukaan.activities;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
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
import com.geogreenapps.apps.indukaan.classes.Cart;
import com.geogreenapps.apps.indukaan.classes.Images;
import com.geogreenapps.apps.indukaan.classes.Offer;
import com.geogreenapps.apps.indukaan.controllers.CampagneController;
import com.geogreenapps.apps.indukaan.controllers.SettingsController;
import com.geogreenapps.apps.indukaan.controllers.cart.CartController;
import com.geogreenapps.apps.indukaan.controllers.sessions.SessionsController;
import com.geogreenapps.apps.indukaan.controllers.stores.OffersController;
import com.geogreenapps.apps.indukaan.customview.ProductCustomView;
import com.geogreenapps.apps.indukaan.fragments.SlideshowDialogFragment;
import com.geogreenapps.apps.indukaan.load_manager.ViewManager;
import com.geogreenapps.apps.indukaan.network.VolleySingleton;
import com.geogreenapps.apps.indukaan.network.api_request.SimpleRequest;
import com.geogreenapps.apps.indukaan.parser.api_parser.OfferParser;
import com.geogreenapps.apps.indukaan.utils.DateUtils;
import com.geogreenapps.apps.indukaan.utils.ProductUtils;
import com.geogreenapps.apps.indukaan.utils.TextUtils;
import com.geogreenapps.apps.indukaan.utils.Utils;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
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
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.iwgang.countdownview.CountdownView;
import io.realm.Realm;
import io.realm.RealmList;

import static com.geogreenapps.apps.indukaan.appconfig.AppConfig.APP_DEBUG;
import static com.geogreenapps.apps.indukaan.appconfig.AppConfig.SHOW_ADS;
import static com.geogreenapps.apps.indukaan.appconfig.AppConfig.SHOW_ADS_IN_OFFER;

/**
 * Created by Droideve on 11/13/2017.
 */

public class OfferDetailActivity extends GlobalActivity implements ViewManager.CustomView {

    @BindView(R.id.app_bar)
    Toolbar toolbar;

    @BindView(R.id.progressBar)
    SpinKitView progressBar;
    @BindView(R.id.loading)
    LinearLayout loading;
    @BindView(R.id.toolbar_back)
    ImageView toolbar_back;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.product_label)
    TextView product_label;
    @BindView(R.id.nbrPictures)
    TextView nbrPictures;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.distanceView)
    TextView distanceView;
    @BindView(R.id.priceView)
    TextView priceView;
    @BindView(R.id.detail_product)
    TextView detailProduct;
    @BindView(R.id.product_up_to)
    TextView productUpTo;
    @BindView(R.id.storeBtn)
    TextView storeBtn;

    @BindView(R.id.storeBtnLayout)
    LinearLayout storeBtnLayout;
    @BindView(R.id.adView)
    AdView adView;
    @BindView(R.id.ads)
    LinearLayout ads;

    @BindView(R.id.deal_layout)
    LinearLayout dealLayout;

    @BindView(R.id.mScroll)
    ParallaxScrollView mScroll;


    @BindView(R.id.layout_custom_order)
    LinearLayout layout_custom_order;

    @BindView(R.id.product_type)
    TextView product_type;
    @BindView(R.id.product_value)
    TextView product_value;

    @BindView(R.id.btn_custom_order)
    AppCompatButton btnCustomOrder;


    private int offer_id = 0;
    private ViewManager mViewManager;
    private Offer offerData;
    private Menu menuContext;

    // custom quantity fields
    private int custom_qte = 1;
    private float custom_price = -1;
    private float original_price = -1;


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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        setContentView(R.layout.activity_offer_detail);
        ButterKnife.bind(this);

        setupToolbar();
        toolbarTransactionScroll();


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
            if (offer_id == 0) {
                try {

                    Intent appLinkIntent = getIntent();
                    String appLinkAction = appLinkIntent.getAction();
                    Uri appLinkData = appLinkIntent.getData();

                    if (appLinkAction != null && appLinkAction.equals(Intent.ACTION_VIEW)) {

                        if (APP_DEBUG)
                            Toast.makeText(getApplicationContext(), appLinkData.toString(), Toast.LENGTH_LONG).show();
                        offer_id = Utils.dp_get_id_from_url(appLinkData.toString(), "offer_id");

                        if (APP_DEBUG)
                            Log.e("offer_id", offer_id + "");

                        if (APP_DEBUG)
                            Toast.makeText(getApplicationContext(), "The ID: " + offer_id + " " + appLinkAction, Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (offer_id == 0) {
                offer_id = getIntent().getExtras().getInt("offer_id");
            }

            if (offer_id == 0) {
                offer_id = getIntent().getExtras().getInt("id");
            }


            if (offer_id == 0) {
                offer_id = Integer.parseInt(getIntent().getExtras().getString("id"));
            }

            if (APP_DEBUG)
                Toast.makeText(this, String.valueOf(offer_id), Toast.LENGTH_LONG).show();


        } catch (Exception e) {
            e.printStackTrace();
            finish();
        }


        final Offer mOffer = OffersController.findOfferById(offer_id);
        if (mOffer != null) {
            mViewManager.showResult();
            offerData = mOffer;
            putInsideViews();

        } else {
            getOffer(offer_id);
        }


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (offerData != null && offerData.getListImages().size() > 0) {
                    List<Images> listOffersImg = offerData.getListImages();
                    SlideshowDialogFragment.newInstance().show(OfferDetailActivity.this, listOffersImg, 0);
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
            date = mOffer.getDate_start();
            date = DateUtils.prepareOutputDate(date, "dd MMMM yyyy  hh:mm", this);
        } catch (Exception e) {

            getOffer(offer_id);
            return;

        }


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

    private void initProductRv(final int parent_id) {

        ProductCustomView mProductCustomView = findViewById(R.id.horizontalProductList);
        if (!SettingsController.isModuleEnabled(Constances.ModulesConfig.PRODUCT_MODULE)) {
            mProductCustomView.hide();
        } else {
            HashMap<String, Object> optionalParams = new HashMap<>();
            optionalParams.put("parent_id", String.valueOf(parent_id));
            mProductCustomView.loadData(false, optionalParams);
            findViewById(R.id.card_show_more).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(OfferDetailActivity.this, ProductsListActivity.class);
                    intent.putExtra("searchParams", optionalParams);
                    startActivity(intent);
                    overridePendingTransition(R.anim.lefttoright_enter, R.anim.lefttoright_exit);
                }
            });


            mProductCustomView.show();
        }
    }

    private void putInsideViews() {

        toolbarTitle.setText(offerData.getName());
        product_label.setText(offerData.getName());


        if (offerData.getStore_id() > 0) {

            Drawable storeDrawable = new IconicsDrawable(this)
                    .icon(CommunityMaterial.Icon.cmd_map_marker)
                    .color(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null))
                    .sizeDp(18);


            GPStracker mGPS = new GPStracker(this);
            Position newPosition = new Position();
            Double mDistance = newPosition.distance(mGPS.getLatitude(), mGPS.getLongitude(), offerData.getLat(), offerData.getLng());

            String disStr = parseDistanceByUnit(mDistance);

            if (mGPS.getLatitude() == 0 && mGPS.getLongitude() == 0) {
                distanceView.setVisibility(View.GONE);
            } else {
                distanceView.setVisibility(View.VISIBLE);
                distanceView.setText(
                        String.format(getString(R.string.productIn), disStr)
                );
            }

            //related product
            initProductRv(offerData.getId());


            storeBtn.setText(offerData.getStore_name());

            if (AppController.isRTL()) {
                storeBtn.setCompoundDrawables(null, null, storeDrawable, null);
            } else {
                storeBtn.setCompoundDrawables(storeDrawable, null, null, null);
            }

            storeBtn.setCompoundDrawablePadding(20);
            storeBtn.setPaintFlags(storeBtn.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


            //fixing bug reported in crashlytics : io.realm.internal.UncheckedRow.nativeGetLong
            final int store_id = offerData.getStore_id();
            storeBtnLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {

                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();

                        if (!StoreDetailActivity.isOpend()) {
                            Intent intent = new Intent(OfferDetailActivity.this, StoreDetailActivity.class);
                            intent.putExtra("id", store_id);
                            startActivity(intent);
                        }

                        realm.commitTransaction();

                    } catch (Exception e) {
                        if (APP_DEBUG)
                            e.printStackTrace();

                        Toast.makeText(OfferDetailActivity.this, getString(R.string.store_not_found), Toast.LENGTH_LONG).show();

                    }

                }
            });

            storeBtnLayout.setVisibility(View.VISIBLE);
        } else
            storeBtnLayout.setVisibility(View.GONE);


        if (offerData.getListImages() != null && offerData.getListImages().size() > 1) {

            Drawable camera = new IconicsDrawable(this)
                    .icon(CommunityMaterial.Icon.cmd_camera)
                    .color(ResourcesCompat.getColor(getResources(), R.color.colorWhite, null))
                    .sizeDp(12);

            nbrPictures.setText(offerData.getListImages().size() + "");
            nbrPictures.setCompoundDrawables(camera, null, null, null);
            nbrPictures.setCompoundDrawablePadding(10);

        } else {
            nbrPictures.setVisibility(View.GONE);
        }


        parseOfferValue(priceView, offerData, -1);


        if (offerData.getImages() != null)
            Glide.with(AppController.getInstance())
                    .load(offerData.getImages().getUrl500_500())
                    .placeholder(ImageLoaderAnimation.glideLoader(this))
                    .into(image);


        detailProduct.setText(offerData.getDescription());
        new TextUtils.decodeHtml(detailProduct).execute(offerData.getDescription());

        Textoo
                .config(detailProduct)
                .linkifyWebUrls()  // or just .linkifyAll()
                .addLinksHandler(new LinksHandler() {
                    @Override
                    public boolean onClick(View view, String url) {

                        if (Utils.isValidURL(url)) {

                            new AwesomeWebView.Builder(OfferDetailActivity.this)
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


        setupCountDown(offerData);


        if (offerData.getOrder_enabled() == 1 && SettingsController.isModuleEnabled(Constances.ModulesConfig.ORDERS_MODULE)) {


            if (offerData.getProduct_type().equalsIgnoreCase("Percent") && (offerData.getProduct_value() > 0 || offerData.getProduct_value() < 0)) {
                DecimalFormat decimalFormat = new DecimalFormat("#0");

                product_type.setText(getResources().getString(R.string.percent));
                product_value.setText(decimalFormat.format(offerData.getProduct_value()) + "%");

            } else {

                if (offerData.getProduct_type().equalsIgnoreCase("Price") && offerData.getProduct_value() != 0) {

                    product_type.setText(getResources().getString(R.string.price));

                    product_value.setText(String.format(ProductUtils.parseCurrencyFormat(
                            custom_price > 0 ? custom_price : (int) offerData.getProduct_value(),
                            offerData.getCurrency())));

                } else {
                    product_value.setVisibility(View.GONE);
                    product_type.setText(getString(R.string.promo));
                }
            }


            if (offerData.getOrder_button() != null) {
                btnCustomOrder.setText(offerData.getOrder_button().toUpperCase());
            }


            if (offerData.getOrder_enabled() != 0) {
                layout_custom_order.setVisibility(View.VISIBLE);
            } else {
                layout_custom_order.setVisibility(View.GONE);
            }


            btnCustomOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (SessionsController.isLogged()) {

                        if (!CartController.checkProductStore(offerData.getStore_id(), SessionsController.getSession().getUser().getId())) {
                            Toast.makeText(OfferDetailActivity.this, getString(R.string.order_items_error), Toast.LENGTH_LONG).show();
                            return;
                        }

                        //fill cart detail
                        Cart mCart = new Cart();
                        mCart.setModule_id(offerData.getId());
                        mCart.setModule(Constances.ModulesConfig.OFFER_MODULE);
                        mCart.setAmount(offerData.getProduct_value());
                        mCart.setOffer(offerData);
                        mCart.setQte(1);

                        mCart.setParent_id(offerData.getStore_id());
                        mCart.setUser_id(SessionsController.getSession().getUser().getId());


                        if (offerData.getQty_enabled() > 0) {
                            showBottomSheetDialog(mCart);
                        } else {
                            Intent intent = new Intent(new Intent(OfferDetailActivity.this, OrderCheckoutActivity.class));

                            //save cart in the database
                            CartController.addOfferToCard(mCart);

                            intent.putExtra("module_id", offerData.getId());
                            intent.putExtra("module", Constances.ModulesConfig.OFFER_MODULE);

                            startActivity(intent);
                            overridePendingTransition(R.anim.lefttoright_enter, R.anim.lefttoright_exit);
                        }


                    } else {
                        Intent intent = new Intent(OfferDetailActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }
            });
        } else {
            btnCustomOrder.setVisibility(View.GONE);
            layout_custom_order.setVisibility(View.GONE);
        }

    }

    private String parseDistanceByUnit(Double mDistance) {
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
        return disStr;
    }

    private void parseOfferValue(TextView priceView, Offer mOffer, float custom_price) {
        if (mOffer.getProduct_type().equalsIgnoreCase("Percent") && (mOffer.getProduct_value() > 0 || mOffer.getProduct_value() < 0)) {
            DecimalFormat decimalFormat = new DecimalFormat("#0");

            priceView.setText(
                    String.format(getString(R.string.product_off),
                            decimalFormat.format(mOffer.getProduct_value()) + "%"
                    )
            );

            priceView.setVisibility(View.VISIBLE);


        } else {

            if (mOffer.getProduct_type().equalsIgnoreCase("Price") && mOffer.getProduct_value() != 0) {

                priceView.setText(
                        String.format(getString(R.string.for_only),
                                ProductUtils.parseCurrencyFormat(
                                        custom_price > 0 ? custom_price : mOffer.getProduct_value(),
                                        mOffer.getCurrency())
                        )

                );

                priceView.setVisibility(View.VISIBLE);

            } else {
                priceView.setVisibility(View.VISIBLE);
                priceView.setText(getString(R.string.promo));
            }
        }
    }

    private void setupCountDown(Offer mOffer) {

        String dateStartAt = "";
        String dateEndAt = "";


        try {
            dateStartAt = mOffer.getDate_start();
            dateStartAt = DateUtils.prepareOutputDate(dateStartAt, "dd MMMM yyyy", this);
        } catch (Exception e) {
            return;
        }

        try {
            dateEndAt = mOffer.getDate_end();
            dateEndAt = DateUtils.prepareOutputDate(dateEndAt, "dd MMMM yyyy", this);
        } catch (Exception e) {
            return;
        }


        String inputDateSatrt = DateUtils.prepareOutputDate(mOffer.getDate_start(), "yyyy-MM-dd HH:mm:ss", this);
        long diff_Will_Start = DateUtils.getDiff(inputDateSatrt, "yyyy-MM-dd HH:mm:ss");

        if (APP_DEBUG) {
            Log.e("_start_at_server", mOffer.getDate_start());
            Log.e("_start_at_device ", dateStartAt);
            Log.e("_start_at_diff ", String.valueOf(diff_Will_Start));
        }

        if (diff_Will_Start > 0) {

            if (mOffer.getIs_deal() == 1) {
                CountdownView mCvCountdownView = findViewById(R.id.cv_countdownViewTest1);
                mCvCountdownView.start(diff_Will_Start); // Millisecond
                dealLayout.setVisibility(View.VISIBLE);
            }

            productUpTo.setText(String.format(getString(R.string.product_start_at), dateStartAt));

        }


        String inputDateEnd = DateUtils.prepareOutputDate(mOffer.getDate_end(), "yyyy-MM-dd HH:mm:ss", this);
        long diff_will_end = DateUtils.getDiff(inputDateEnd, "yyyy-MM-dd HH:mm:ss");


        if (APP_DEBUG) {
            Log.e("_end_at_server", mOffer.getDate_end());
            Log.e("_end_at_device ", dateEndAt);
            Log.e("_end_at_diff ", String.valueOf(diff_will_end));
        }

        if (diff_will_end > 0 && diff_Will_Start < 0) {


            if (mOffer.getIs_deal() == 1) {
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


    public void getOffer(final int offer_id) {

        mViewManager.loading();

        final GPStracker mGPS = new GPStracker(this);

        SimpleRequest request = new SimpleRequest(Request.Method.POST,
                Constances.API.API_GET_OFFERS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                mViewManager.showResult();

                try {

                    if (APP_DEBUG) {
                        Log.e("responseOffersString", response);
                    }

                    JSONObject jsonObject = new JSONObject(response);
                    final OfferParser mOfferParser = new OfferParser(jsonObject);
                    RealmList<Offer> list = mOfferParser.getOffers();

                    if (list.size() > 0) {

                        OffersController.insertOrUpdateOffers(list);
                        offerData = list.get(0);
                        putInsideViews();

                    } else {

                        Toast.makeText(OfferDetailActivity.this, getString(R.string.store_not_found), Toast.LENGTH_LONG).show();
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
                params.put("offer_id", offer_id + "");

                if (APP_DEBUG) {
                    Log.e("ListOffersFragment", "  params getOffers :" + params.toString());
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
                                offerData.getName(),
                                getString(R.string.app_name),
                                offerData.getLink()
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

        if (offerData != null) {

            //set default  values
            if (offerData.getProduct_type().equalsIgnoreCase("Price") && offerData.getProduct_value() != 0) {
                custom_price = original_price = offerData.getProduct_value();
                custom_qte = 1;
            }

            //set offer_id image
            if (offerData.getImages() != null) {
                Glide.with(AppController.getInstance())
                        .load(offerData.getImages().getUrl200_200())
                        .centerCrop()
                        .placeholder(ImageLoaderAnimation.glideLoader(this))

                        .centerCrop().into(((ImageView) view[0].findViewById(R.id.image_product)));
                //set offer_id name
                ((TextView) view[0].findViewById(R.id.product_name)).setText(offerData.getName());

                //set offer_id price
                parseOfferValue(view[0].findViewById(R.id.product_price), offerData, -1);

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
                        parseOfferValue(view[0].findViewById(R.id.product_price), offerData, custom_price);

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
                        parseOfferValue(view[0].findViewById(R.id.product_price), offerData, custom_price);

                    }
                });


            }


            (view[0].findViewById(R.id.bt_confirm)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(new Intent(OfferDetailActivity.this, OrderCheckoutActivity.class));

                    mCart.setQte(custom_qte);

                    //save cart in the database
                    CartController.addOfferToCard(mCart);

                    intent.putExtra("module_id", offerData.getId());
                    intent.putExtra("module", Constances.ModulesConfig.OFFER_MODULE);

                    startActivity(intent);
                    overridePendingTransition(R.anim.lefttoright_enter, R.anim.lefttoright_exit);

                    mBottomSheetDialog[0].dismiss();

                }
            });

            (view[0].findViewById(R.id.btn_add_to_cart)).setVisibility(View.GONE);

            /*(view[0].findViewById(R.id.btn_add_to_cart)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mBottomSheetDialog[0].dismiss();
                }
            });*/

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
}
