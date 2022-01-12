package com.geogreenapps.apps.indukaan.navigationdrawer;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geogreenapps.apps.indukaan.AppController;
import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.activities.AboutActivity;
import com.geogreenapps.apps.indukaan.activities.BookmarkActivity;
import com.geogreenapps.apps.indukaan.activities.CategoriesActivity;
import com.geogreenapps.apps.indukaan.activities.EditProfileActivity;
import com.geogreenapps.apps.indukaan.activities.ListOrdersActivity;
import com.geogreenapps.apps.indukaan.activities.ListUsersActivity;
import com.geogreenapps.apps.indukaan.activities.LoginActivity;
import com.geogreenapps.apps.indukaan.activities.MapStoresListActivity;
import com.geogreenapps.apps.indukaan.activities.SettingActivity;
import com.geogreenapps.apps.indukaan.activities.SplashActivity;
import com.geogreenapps.apps.indukaan.adapter.navigation.SimpleListAdapterNavDrawer;
import com.geogreenapps.apps.indukaan.appconfig.AppConfig;
import com.geogreenapps.apps.indukaan.appconfig.Constances;
import com.geogreenapps.apps.indukaan.classes.HeaderItem;
import com.geogreenapps.apps.indukaan.classes.ItemNav;
import com.geogreenapps.apps.indukaan.controllers.SettingsController;
import com.geogreenapps.apps.indukaan.controllers.sessions.SessionsController;
import com.geogreenapps.apps.indukaan.fragments.MainFragment;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.wuadam.awesomewebview.AwesomeWebView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class NavigationDrawerFragment extends Fragment implements SimpleListAdapterNavDrawer.ClickListener {


    public static final String PREF_FILE_NAME = "testpref";
    public static final String KEY_USER_LEARNED_DRAWER = "learned_user_drawer";
    public static int INT_CHAT_BOX = 5;
    private static DrawerLayout mDrawerLayout;
    List<ItemNav> listItemNavs = Collections.emptyList();
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private boolean mUserLearedLayout;
    private boolean mFromSaveInstanceState;


    //init request http
    private SimpleListAdapterNavDrawer adapter;

    public static DrawerLayout getInstance() {
        return mDrawerLayout;
    }

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(preferenceName, preferenceValue);
        edit.apply();

    }

    public static String readFromPreferences(Context context, String preferenceName, String defaultValue) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, defaultValue);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mUserLearedLayout = Boolean.valueOf(readFromPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));
        if (savedInstanceState != null) {
            mFromSaveInstanceState = true;
        }

       /* if(SessionsController.isLogged())
             user = SessionsController.getSession().getUser();*/

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.navigation_drawer_content, container, false);

        rootView.setClickable(true);

        RecyclerView drawerList = rootView.findViewById(R.id.drawerLayout);
        drawerList.setVisibility(View.VISIBLE);

        adapter = new SimpleListAdapterNavDrawer(getActivity(), getData());

        drawerList.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        drawerList.setLayoutManager(mLayoutManager);
        drawerList.setAdapter(adapter);

        adapter.setClickListener(this);

        return rootView;

    }


    public List<ItemNav> getData() {

        listItemNavs = new ArrayList<ItemNav>();


        HeaderItem header_item = new HeaderItem();
        header_item.setName(AppController.getInstance().getResources().getString(R.string.Home));
        header_item.setEnabled(true);


        ItemNav webdashboard = new ItemNav();
        webdashboard.setName(AppController.getInstance().getResources().getString(R.string.ManageThings));
        webdashboard.setImageId(R.drawable.ic_briefcase_variant_outline);
        webdashboard.setID(12);


        ItemNav homeItemNav = new ItemNav();
        homeItemNav.setName(AppController.getInstance().getResources().getString(R.string.Home));
        homeItemNav.setIconDraw(CommunityMaterial.Icon.cmd_home_outline);
        homeItemNav.setID(1);


        String categoriesMenu = AppController.getInstance().getResources().getString(R.string.Categories);
        ItemNav catItemNav = new ItemNav();
        catItemNav.setName(categoriesMenu);
        catItemNav.setIconDraw(CommunityMaterial.Icon.cmd_format_list_bulleted);
        catItemNav.setID(2);


        ItemNav findNewCustomers = null;
        findNewCustomers = new ItemNav();

        if (SessionsController.isLogged()) {

            findNewCustomers.setName(AppController.getInstance().getResources().getString(R.string.FindCustomers));
            findNewCustomers.setIconDraw(CommunityMaterial.Icon.cmd_account_outline);
            findNewCustomers.setID(3);

        } else {

            findNewCustomers.setName(AppController.getInstance().getResources().getString(R.string.Login));
            findNewCustomers.setIconDraw(CommunityMaterial.Icon.cmd_account_outline);
            findNewCustomers.setID(4);

        }


        ItemNav savesItemNav = new ItemNav();
        savesItemNav.setName(AppController.getInstance().getResources().getString(R.string.Favoris));
        savesItemNav.setIconDraw(CommunityMaterial.Icon.cmd_bookmark_outline);
        savesItemNav.setID(Menu.FAV);


        ItemNav aboutItemNav = new ItemNav();
        aboutItemNav.setName(AppController.getInstance().getResources().getString(R.string.about));
        aboutItemNav.setIconDraw(CommunityMaterial.Icon.cmd_information_outline);
        aboutItemNav.setID(Menu.ABOUT);


        ItemNav settingItemNav = new ItemNav();
        settingItemNav.setName(AppController.getInstance().getResources().getString(R.string.Settings));
        settingItemNav.setImageId(R.drawable.ic_cog_outline);
        settingItemNav.setID(Menu.SETTING);


        ItemNav mapStoresItemNav = new ItemNav();
        mapStoresItemNav.setName(AppController.getInstance().getResources().getString(R.string.MapStoresMenu));
        mapStoresItemNav.setIconDraw(CommunityMaterial.Icon.cmd_google_maps);
        mapStoresItemNav.setID(Menu.MAP_STORES);

        mapStoresItemNav.setEnabled(!AppConfig.HOME_QUICK_ACCESS);

        ItemNav editProdile = new ItemNav();
        editProdile.setName(AppController.getInstance().getResources().getString(R.string.editProfile));
        editProdile.setIconDraw(CommunityMaterial.Icon.cmd_account_outline);
        editProdile.setID(7);


        ItemNav logout = new ItemNav();
        logout.setName(AppController.getInstance().getResources().getString(R.string.Logout));
        logout.setIconDraw(CommunityMaterial.Icon.cmd_exit_to_app);
        logout.setID(11);

        ItemNav orders = new ItemNav();
        orders.setName(AppController.getInstance().getResources().getString(R.string.orders));

        //Enable or disable order from config API
        orders.setEnabled(SettingsController.isModuleEnabled(Constances.ModulesConfig.ORDERS_MODULE));

        orders.setIconDraw(CommunityMaterial.Icon.cmd_cart_outline);
        orders.setID(Menu.ORDERS);

        if (header_item.isEnabled())
            listItemNavs.add(header_item);

        //HOME
        if (homeItemNav.isEnabled())
            listItemNavs.add(homeItemNav);

        //Categories
        if (catItemNav.isEnabled())
            listItemNavs.add(catItemNav);

        //Geo Stores
        if (mapStoresItemNav.isEnabled()) {
            listItemNavs.add(mapStoresItemNav);
        }

        //Orders
        if (orders.isEnabled() && SessionsController.isLogged())
            listItemNavs.add(orders);


        //People Around Me
        if (AppConfig.ENABLE_CHAT && findNewCustomers != null) {
            if (SessionsController.isLogged() && !SettingsController.isModuleEnabled(Constances.ModulesConfig.MESSENGER_MODULE)) {
                // hide item when user is logged and chat module is disabled

            } else {
                if (AppConfig.ENABLE_PEOPLE_AROUND_ME)
                    listItemNavs.add(findNewCustomers);
            }

        }

        /*//Notifications
        if (orders.isEnabled())
            listItemNavs.add(notification);*/


        //Edit Profile
        if (editProdile.isEnabled() && SessionsController.isLogged())
            listItemNavs.add(editProdile);

        //Settings
        if (AppConfig.ENABLE_WEB_DASHBOARD)
            listItemNavs.add(webdashboard);


        //My Favorite
        if (savesItemNav.isEnabled() && SettingsController.isModuleEnabled(Constances.ModulesConfig.STORE_MODULE)) {
            listItemNavs.add(savesItemNav);
        }

        //Edit Profile
        if (logout.isEnabled() && SessionsController.isLogged())
            listItemNavs.add(logout);

        //Settings
        if (settingItemNav.isEnabled())
            listItemNavs.add(settingItemNav);

        //About US
        if (aboutItemNav.isEnabled())
            listItemNavs.add(aboutItemNav);


        return listItemNavs;
    }

    public void setUp(int FragId, DrawerLayout drawerlayout, final Toolbar toolbar) {

        View containerView = Objects.requireNonNull(getView()).findViewById(FragId);
        mDrawerLayout = drawerlayout;

        //mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);

        mActionBarDrawerToggle = new ActionBarDrawerToggle(
                getActivity(),
                drawerlayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        ) {
            @Override
            public void onDrawerOpened(View drawerView) {

                super.onDrawerOpened(drawerView);
                if (!mUserLearedLayout) {
                    mUserLearedLayout = true;
                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearedLayout + "");
                }


                //getActivity().invalidateOptionsMenu();

            }

            @Override
            public void onDrawerClosed(View drawerView) {

                super.onDrawerClosed(drawerView);
                //getActivity().invalidateOptionsMenu();

            }


            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);

            }
        };

        if (!mUserLearedLayout && !mFromSaveInstanceState) {
            mDrawerLayout.closeDrawer(containerView);
        }


        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mActionBarDrawerToggle.syncState();

            }
        });

    }


    @Override
    public void onStart() {
        //EventBus.getDefault().register(this);


        super.onStart();

    }

    @Override
    public void onPause() {
        super.onPause();
        //EventBus.getDefault().unregister(this);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == INT_CHAT_BOX) {

            adapter.getData().get(1).setNotify(0);
            adapter.update(1, adapter.getData().get(1));

        }
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void itemClicked(View view, int position) {


        //MainFragment mf = (MainFragment) getFragmentManager().findFragmentByTag(MainFragment.TAG);

        ItemNav item = adapter.getData().get(position);
        if (item != null) {
            switch (item.getID()) {
                case Menu.HOME_ID:

                    if (mDrawerLayout != null)
                        mDrawerLayout.closeDrawers();

                    MainFragment mf = (MainFragment) getFragmentManager().findFragmentByTag(MainFragment.TAG);

                    if (AppConfig.HOME_QUICK_ACCESS)
                        mf.setCurrentFragment(0);
                    else
                        mf.setCurrentFragment(1);

                    break;
                case Menu.CAT_ID:

                    if (mDrawerLayout != null)
                        mDrawerLayout.closeDrawers();

                    startActivity(new Intent(getActivity(), CategoriesActivity.class));
                    getActivity().overridePendingTransition(R.anim.lefttoright_enter, R.anim.lefttoright_exit);


                    break;
                case Menu.PEOPLE_AROUND_ME:

                    if (SessionsController.isLogged()) {
                        startActivity(new Intent(getActivity(), ListUsersActivity.class));
                        getActivity().overridePendingTransition(R.anim.lefttoright_enter, R.anim.lefttoright_exit);
                    }

                    break;
                case Menu.CHAT_LOGIN_ID:

                    if (!SessionsController.isLogged()) {
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                    }

                    break;
                case Menu.FAV:

                    startActivity(new Intent(getActivity(), BookmarkActivity.class));
                    getActivity().overridePendingTransition(R.anim.lefttoright_enter, R.anim.lefttoright_exit);

                    break;
                case Menu.EDIT:

                    if (mDrawerLayout != null)
                        mDrawerLayout.closeDrawers();

                    startActivity(new Intent(getActivity(), EditProfileActivity.class));

                    break;
                case Menu.ABOUT:

                    if (mDrawerLayout != null)
                        mDrawerLayout.closeDrawers();

                    startActivity(new Intent(getActivity(), AboutActivity.class));
                    getActivity().overridePendingTransition(R.anim.lefttoright_enter, R.anim.lefttoright_exit);

                    break;
                case Menu.ORDERS:

                    startActivity(new Intent(getActivity(), ListOrdersActivity.class));
                    getActivity().overridePendingTransition(R.anim.lefttoright_enter, R.anim.lefttoright_exit);

                    break;
                case Menu.SETTING:

                    if (mDrawerLayout != null)
                        mDrawerLayout.closeDrawers();

                    startActivity(new Intent(getActivity(), SettingActivity.class));
                    getActivity().overridePendingTransition(R.anim.lefttoright_enter, R.anim.lefttoright_exit);

                    break;
                case Menu.MAP_STORES:

//                    if(mDrawerLayout!=null)
//                        mDrawerLayout.closeDrawers();

                    startActivity(new Intent(getActivity(), MapStoresListActivity.class));
                    getActivity().overridePendingTransition(R.anim.lefttoright_enter, R.anim.lefttoright_exit);

                    break;
                case Menu.LOGOUT:

                    SessionsController.logOut();
                    getActivity().finish();
                    startActivity(new Intent(getActivity(), SplashActivity.class));

                    break;

                case Menu.WEB_DASHBOARD:

                    if (!AppConfig.ENABLE_WEB_DASHBOARD)
                        break;

                    if (mDrawerLayout != null)
                        mDrawerLayout.closeDrawers();

                    String url = AppConfig.BASE_URL + "/webdashboard/";

                    CookieManager.getInstance().setAcceptCookie(true);

                    new AwesomeWebView.Builder(getActivity())
                            .webViewCookieEnabled(true)
                            .showMenuOpenWith(false)
                            .fileChooserEnabled(true)
                            .statusBarColorRes(R.color.colorPrimary)
                            .theme(R.style.FinestWebViewAppTheme)
                            .titleColor(ResourcesCompat.getColor(getResources(), R.color.defaultWhiteColor, null))
                            .urlColor(ResourcesCompat.getColor(getResources(), R.color.defaultWhiteColor, null))
                            .show(url);


                    break;


            }
        }


    }

    private static class Menu {
        static final int HOME_ID = 1;
        static final int CAT_ID = 2;
        static final int PEOPLE_AROUND_ME = 3;
        static final int CHAT_LOGIN_ID = 4;
        static final int NOTIFICATIONS = 5;
        static final int EDIT = 7;
        static final int ABOUT = 8;
        static final int CREATE_STORE = 9;
        static final int SETTING = 10;
        static final int LOGOUT = 11;
        static final int WEB_DASHBOARD = 12;
        static final int MAP_STORES = 13;
        static final int FAV = 14;
        static final int ORDERS = 15;

    }


}
