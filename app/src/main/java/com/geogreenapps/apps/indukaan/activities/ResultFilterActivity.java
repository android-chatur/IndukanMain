package com.geogreenapps.apps.indukaan.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.appconfig.Constances;
import com.geogreenapps.apps.indukaan.classes.Category;
import com.geogreenapps.apps.indukaan.fragments.ListOffersFragment;
import com.geogreenapps.apps.indukaan.fragments.ListProductsFragment;
import com.geogreenapps.apps.indukaan.fragments.ListStoresFragment;

import java.util.HashMap;

public class ResultFilterActivity extends GlobalActivity {

    Toolbar toolbar;
    private TextView APP_TITLE_VIEW = null;
    private TextView APP_DESC_VIEW = null;

    private final Category mCat = null;
    private HashMap<String, Object> searchParams;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        setContentView(R.layout.fragment_result_filter);

        initToolbar();


        if (getIntent().getExtras().containsKey("searchParams"))
            handleResultSearchByModule((HashMap<String, Object>) getIntent().getExtras().getSerializable("searchParams"));
        else
            onBackPressed();

    }


    private void handleResultSearchByModule(HashMap<String, Object> _searchParams) {

        //fill params
        searchParams = _searchParams;

        Bundle b = new Bundle();
        b.putSerializable("searchParams", _searchParams);
        Fragment fragResult = null;
        if (_searchParams.containsKey("module") && _searchParams.get("module").equals(Constances.ModulesConfig.STORE_MODULE)) {
            APP_TITLE_VIEW.setText(getString(R.string.stores_result));
            fragResult = new ListStoresFragment();
        } else if (_searchParams.containsKey("module") && _searchParams.get("module").equals(Constances.ModulesConfig.PRODUCT_MODULE)) {
            APP_TITLE_VIEW.setText(getString(R.string.products_result));
            fragResult = new ListProductsFragment();
        } else if (_searchParams.containsKey("module") && _searchParams.get("module").equals(Constances.ModulesConfig.OFFER_MODULE)) {
            APP_TITLE_VIEW.setText(getString(R.string.offers_result));
            fragResult = new ListOffersFragment();
        }

        FragmentManager manager = getSupportFragmentManager();
        if (fragResult != null) {
            fragResult.setArguments(b);
            manager.beginTransaction()
                    .replace(R.id.result_layout, fragResult)
                    .commit();
        } else {
            onBackPressed();
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);

        menu.findItem(R.id.search_icon).setVisible(true);
        menu.findItem(R.id.qr_scanner).setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (android.R.id.home == item.getItemId()) {
            finish();
        } else if (item.getItemId() == R.id.search_icon) {
            Intent intent = new Intent(this, CustomSearchActivity.class);
            intent.putExtra("useCacheFields", "enabled");
            intent.putExtra("searchParams", searchParams);
            startActivityForResult(intent, 50505);
            //finish();
        }

        return super.onOptionsItemSelected(item);
    }


    public void initToolbar() {

        toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        APP_TITLE_VIEW = toolbar.findViewById(R.id.toolbar_title);
        APP_DESC_VIEW = toolbar.findViewById(R.id.toolbar_description);
        APP_DESC_VIEW.setVisibility(View.GONE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (50505 == requestCode && resultCode == Activity.RESULT_OK) {
            searchParams = (HashMap<String, Object>) data.getExtras().getSerializable("searchParams");
            handleResultSearchByModule(searchParams);
        }
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        menu.findItem(R.id.list_view_icon).setVisible(true);
        menu.findItem(R.id.search_icon).setVisible(true);
        menu.findItem(R.id.notification_action).setVisible(false);
        menu.findItem(R.id.cart_icon).setVisible(false);
        menu.findItem(R.id.qr_scanner).setVisible(false);

        super.onPrepareOptionsMenu(menu);


        return false;
    }


}
