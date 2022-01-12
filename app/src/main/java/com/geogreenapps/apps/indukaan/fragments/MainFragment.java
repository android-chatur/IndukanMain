package com.geogreenapps.apps.indukaan.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.appconfig.AppConfig;
import com.geogreenapps.apps.indukaan.views.SwipeDisabledViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.greenrobot.eventbus.EventBus;


public class MainFragment extends Fragment {

    public final static String TAG = "mainfragment";
    private static SwipeDisabledViewPager pager;
    static BottomNavigationView navigation;
    private Listener mListener;
    private FragmentActivity myContext;


    public static ViewPager getPager() {
        return pager;
    }

    public void setListener(final Listener mItemListener) {
        this.mListener = mItemListener;
    }

    public interface Listener {
        void onScrollHorizontal(int position);

        void onScrollVertical(int scrollXs, int scrollY);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        initViewPagerAdapter(rootView);

        initBottomNavigation(rootView);



        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        myContext = (FragmentActivity) activity;
    }


    private void initViewPagerAdapter(View view) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(myContext.getSupportFragmentManager());
        pager = view.findViewById(R.id.pager);
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(4);
    }

    @Override
    public void onResume() {


        super.onResume();
    }

    public void setCurrentFragment(int position) {
        navigation.setSelectedItemId(position);
        pager.setCurrentItem(position);
    }


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }


    public class ViewPagerAdapter extends FragmentStatePagerAdapter {

        int FRAGS_ITEMS_NUM = 5;

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return FRAGS_ITEMS_NUM;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return HomeFragment.newInstance(0, "Page # 1");
                case 1:
                    return ListProductsFragment.newInstance(0, "Page # 2");
                case 2:
                    return ListStoresFragment.newInstance(1, "Page # 3");
                case 3:
                    return GeoStoresFragment.newInstance(3, "Page # 4");
                case 4:
                    return InboxFragment.newInstance(4, "Page # 5");
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }


    }


    private void initBottomNavigation(View view) {


        View navigation_bottom = view.findViewById(R.id.navigation_bottom);
        navigation = navigation_bottom.findViewById(R.id.navigation);

        //setup bottom navigation
        if (AppConfig.HOME_QUICK_ACCESS) {
            navigation.getMenu().removeItem(R.id.navigation_product);
            navigation.getMenu().removeItem(R.id.navigation_stores);
            pager.setCurrentItem(0);
        } else {
            //navigation.getMenu().removeItem(R.id.navigation_maps);
            //navigation.getMenu().removeItem(R.id.navigation_home);
            pager.setCurrentItem(1);
        }


        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                PageViewEvent mPageViewEvent = new PageViewEvent();


                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        mPageViewEvent.title = getString(R.string.Home);
                        pager.setCurrentItem(0);

                        break;
                    case R.id.navigation_product:
                        mPageViewEvent.title = getString(R.string.tab_products);
                        pager.setCurrentItem(1);

                        break;
                    case R.id.navigation_stores:
                        mPageViewEvent.title = getString(R.string.tab_stores);
                        pager.setCurrentItem(2);

                        break;
                    case R.id.navigation_maps:
                        mPageViewEvent.title = getString(R.string.MapStores);
                        pager.setCurrentItem(3);

                        break;
                    case R.id.navigation_inbox:
                        pager.setCurrentItem(4);
                        break;
                }

                EventBus.getDefault().post(mPageViewEvent);
                return true;
            }
        });


    }


    public class PageViewEvent {
        public String title;
        public int position;
    }

}
