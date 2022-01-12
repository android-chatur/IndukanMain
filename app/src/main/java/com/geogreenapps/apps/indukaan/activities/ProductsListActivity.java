package com.geogreenapps.apps.indukaan.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.appconfig.Constances;
import com.geogreenapps.apps.indukaan.classes.Store;
import com.geogreenapps.apps.indukaan.controllers.stores.StoreController;
import com.geogreenapps.apps.indukaan.fragments.ListProductsFragment;

import java.util.Objects;


public class ProductsListActivity extends GlobalActivity {

    Toolbar toolbar;
    private TextView APP_TITLE_VIEW = null;
    private TextView APP_DESC_VIEW = null;

    private int store_id = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        setContentView(R.layout.fragment_bookmark);
        initToolbar();

        Intent intent = getIntent();
        Bundle b = new Bundle();


        try {

            if (intent.hasExtra("store_id")) {
                b.putInt("store_id", Objects.requireNonNull(intent.getExtras()).getInt("store_id"));
                store_id = intent.getExtras().getInt("store_id");
                Store store = StoreController.findStoreById(store_id);
                APP_DESC_VIEW.setText(store.getName());
                APP_DESC_VIEW.setVisibility(View.VISIBLE);
                APP_TITLE_VIEW.setText(R.string.products);
            } else {
                APP_TITLE_VIEW.setText(R.string.recent_products);
                APP_DESC_VIEW.setVisibility(View.GONE);
            }

            if (intent.getExtras().containsKey("searchParams")) {
                APP_TITLE_VIEW.setText(R.string.products_result);
                b.putSerializable("searchParams", intent.getExtras().getSerializable("searchParams"));
            }

        } catch (Exception e) {

        }


        ListProductsFragment fragment = new ListProductsFragment();
        try {
            b.putInt("category", getIntent().getExtras().getInt("category"));
        } catch (Exception e) {

        }
        fragment.setArguments(b);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (android.R.id.home == item.getItemId()) {
            finish();
        } else if (item.getItemId() == R.id.search_icon) {
            Intent intent = new Intent(this, CustomSearchActivity.class);
            intent.putExtra("selected_module", Constances.ModulesConfig.PRODUCT_MODULE);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
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


    public void initToolbar() {

        toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        APP_TITLE_VIEW = toolbar.findViewById(R.id.toolbar_title);
        APP_DESC_VIEW = toolbar.findViewById(R.id.toolbar_description);

        APP_DESC_VIEW.setVisibility(View.GONE);

    }
}
