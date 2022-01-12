package com.geogreenapps.apps.indukaan.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.classes.Store;
import com.geogreenapps.apps.indukaan.controllers.stores.StoreController;
import com.geogreenapps.apps.indukaan.fragments.ListProductsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProductsActivity extends GlobalActivity {

    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_description)
    TextView toolbarDescription;
    @BindView(R.id.container)
    LinearLayout storeContent;


    private int store_id = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        setContentView(R.layout.fragment_bookmark);
        ButterKnife.bind(this);
        initToolbar();


        try {
            store_id = getIntent().getExtras().getInt("store_id");
        } catch (Exception e) {
        }


        Bundle bundle = new Bundle();
        bundle.putInt("store_id", store_id);

        ListProductsFragment fragment = new ListProductsFragment();
        fragment.setArguments(bundle);


        FragmentManager manager = getSupportFragmentManager();

        manager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();

        Store store = StoreController.findStoreById(store_id);

        toolbarTitle.setText(R.string.products);
        toolbarDescription.setText(store.getName());
        toolbarDescription.setVisibility(View.VISIBLE);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (android.R.id.home == item.getItemId()) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void initToolbar() {

        toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolbarDescription = toolbar.findViewById(R.id.toolbar_description);
        //Utils.setFont(.+);
        //Utils.setFont(.+);

        toolbarDescription.setVisibility(View.GONE);

    }
}
