package com.geogreenapps.apps.indukaan.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.activities.CustomSearchActivity;
import com.geogreenapps.apps.indukaan.animation.Animation;
import com.geogreenapps.apps.indukaan.appconfig.Constances;
import com.geogreenapps.apps.indukaan.classes.Setting;
import com.geogreenapps.apps.indukaan.controllers.SettingsController;
import com.geogreenapps.apps.indukaan.customview.CategoryCustomView;
import com.geogreenapps.apps.indukaan.customview.OfferCustomView;
import com.geogreenapps.apps.indukaan.customview.ProductCustomView;
import com.geogreenapps.apps.indukaan.customview.SliderCustomView;
import com.geogreenapps.apps.indukaan.customview.StoreCustomView;
import com.geogreenapps.apps.indukaan.utils.BadgeNotificationUtils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    public final static String TAG = "homefragment";
    //binding
    @BindView(R.id.mScroll)
    NestedScrollView mNestedScrollView;

    @BindView(R.id.lbox)
    LinearLayout lbox;

    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    @BindView(R.id.delivery_widget)
    CardView deliveryWidget;

    @BindView(R.id.deliver_app_name)
    TextView deliveryAppName;


    @OnClick(R.id.lbox)
    public void submit(View view) {
        getActivity().overridePendingTransition(R.anim.lefttoright_enter, R.anim.lefttoright_exit);
        startActivity(new Intent(getActivity(), CustomSearchActivity.class));
    }

    private View rootview;
    private Listener mListener;

    // newInstance constructor for creating fragment with arguments
    public static HomeFragment newInstance(int page, String title) {
        HomeFragment fragmentFirst = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt("id", page);
        args.putString("title", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.v2_fragment_home, container, false);
        rootview = rootView.getRootView();
        ButterKnife.bind(this, rootview);
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);

        initSwipeRefresh();

        setupDeliveryWidget();

        setupScroll();


        initCategoryRV();

        initSliderCustomView();

        initFeaturedProductRv();

        initOfferRV();

        initProductRv();

        initStoreRV();



        return rootview;
    }


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        updateBadges();

        if (menu != null) {
            menu.findItem(R.id.list_view_icon).setVisible(false);
            menu.findItem(R.id.search_icon).setVisible(false);
            menu.findItem(R.id.notification_action).setVisible(true);
            menu.findItem(R.id.qr_scanner).setVisible(false);
        }

        super.onPrepareOptionsMenu(menu);

    }

    private void updateBadges() {
        BadgeNotificationUtils.updateMessengerBadge(getActivity());
        BadgeNotificationUtils.updateNotificationBadge(getActivity());
        BadgeNotificationUtils.updateCartItemsBadge(getActivity());

    }


    private void setupDeliveryWidget() {

        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Setting defaultAppSetting = SettingsController.findSettingFiled("DELIVERY_ANDROID_LINK");
                if (defaultAppSetting != null && !defaultAppSetting.getValue().equals("") &&
                        SettingsController.isModuleEnabled(Constances.ModulesConfig.DELIVERY_MODULE)) {

                    deliveryAppName.setText(String.format(getString(R.string.delivery_name), getString(R.string.app_name)));
                    Animation.startZoomEffect(deliveryWidget);
                    //deliveryWidget.setVisibility(View.VISIBLE);

                    deliveryWidget.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(defaultAppSetting.getValue()));
                            startActivity(intent);
                        }
                    });
                } else {
                    deliveryWidget.setVisibility(View.GONE);
                }
            }
        }, 4000);


    }

    private void setupScroll() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mNestedScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    Log.e(getTag(), scrollX + " - " + scrollY);

                    if (mListener != null)
                        mListener.onScroll(scrollX, scrollY);
                }
            });
        }
    }

    private void initCategoryRV() {
        CategoryCustomView mCategoryCustomView = rootview.findViewById(R.id.rectCategoryList);
        mCategoryCustomView.loadData(false);
        mCategoryCustomView.show();
    }

    private void initStoreRV() {
        StoreCustomView mStoreCustomView = rootview.findViewById(R.id.horizontalStoreList);
        mStoreCustomView.loadData(false);

        mStoreCustomView.show();
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

    private void initSliderCustomView() {
        SliderCustomView mSliderCustomView = rootview.findViewById(R.id.sliderCV);

        if (!SettingsController.isModuleEnabled(Constances.ModulesConfig.SLIDER_MODULE)) {
            mSliderCustomView.hide();
        } else {
            mSliderCustomView.loadData(false);
            mSliderCustomView.startAutoSlider();

            mSliderCustomView.show();
        }

    }

    private void initProductRv() {
      /*  ProductCustomView mProductCustomView = rootview.findViewById(R.id.horizontalProductList);
        if (!SettingsController.isModuleEnabled(Constances.ModulesConfig.PRODUCT_MODULE)) {
            mProductCustomView.hide();
        } else {


            HashMap<String, Object> optionalParams = new HashMap<>();
            optionalParams.put("is_featured", 0);
            mProductCustomView.loadData(false, optionalParams);
            mProductCustomView.show();*/
       // }
    }

    private void initFeaturedProductRv() {
        ProductCustomView mFeaturedProductCustomView = rootview.findViewById(R.id.featured_products);
        if (!SettingsController.isModuleEnabled(Constances.ModulesConfig.PRODUCT_MODULE)) {
            mFeaturedProductCustomView.hide();
        } else {

            HashMap<String, Object> optionalParams = new HashMap<>();
            optionalParams.put("is_featured", 1);
            mFeaturedProductCustomView.loadData(false, optionalParams);
            mFeaturedProductCustomView.show();
        }
    }

    @Override
    public void onRefresh() {

        initCategoryRV();

        initSliderCustomView();

        initFeaturedProductRv();

        initOfferRV();

        initProductRv();

        initStoreRV();

        //end refreshing
        refresh.setRefreshing(false);

    }

    private void initSwipeRefresh() {

        refresh.setOnRefreshListener(this);
        refresh.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorPrimary,
                R.color.colorPrimary,
                R.color.colorPrimary
        );

    }

    public void setListener(final Listener mItemListener) {
        this.mListener = mItemListener;
    }

    public interface Listener {
        void onScroll(int scrollX, int scrollY);
    }


}
