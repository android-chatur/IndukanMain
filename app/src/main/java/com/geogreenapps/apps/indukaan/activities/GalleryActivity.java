package com.geogreenapps.apps.indukaan.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.appconfig.Constances;
import com.geogreenapps.apps.indukaan.classes.Product;
import com.geogreenapps.apps.indukaan.classes.Store;
import com.geogreenapps.apps.indukaan.controllers.stores.ProductsController;
import com.geogreenapps.apps.indukaan.controllers.stores.StoreController;
import com.geogreenapps.apps.indukaan.fragments.GalleryFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalleryActivity extends GlobalActivity {


    @BindView(R.id.toolbar_title)
    TextView APP_TITLE_VIEW;
    @BindView(R.id.toolbar_description)
    TextView APP_DESC_VIEW;
    @BindView(R.id.app_bar)
    Toolbar toolbar;


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);
        initToolbar();


        int int_id = 0;
        String type = "";
        try {

            int_id = getIntent().getExtras().getInt("int_id", 0);
            type = getIntent().getExtras().getString("type", "store");

            if (int_id == 0)
                finish();

            String iname = "";
            if (type.equals(Constances.ModulesConfig.STORE_MODULE)) {
                Store store = StoreController.findStoreById(int_id);
                iname = store.getName();
            } else if (type.equals(Constances.ModulesConfig.PRODUCT_MODULE)) {
                Product product_id = ProductsController.findProductById(int_id);
                iname = product_id.getName();
            } else {
                finish();
            }

            APP_TITLE_VIEW.setText(getResources().getString(R.string.gallery));
            APP_DESC_VIEW.setText(iname);
            APP_TITLE_VIEW.setVisibility(View.VISIBLE);
            APP_DESC_VIEW.setVisibility(View.VISIBLE);

        } catch (Exception e) {
            e.printStackTrace();
            finish();
            return;
        }

        GalleryFragment frag = new GalleryFragment();
        frag.setParent_width(MainActivity.width);

        Bundle b = new Bundle();
        b.putInt("int_id", int_id);
        b.putString("type", type);
        frag.setArguments(b);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frame, frag).commit();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        APP_DESC_VIEW.setVisibility(View.GONE);
        //Utils.setFont(.+);
        //Utils.setFont(.+);
        String about = Constances.initConfig.AppInfos.ABOUT_CONTENT;
        APP_TITLE_VIEW.setText(R.string.gallery);
        APP_DESC_VIEW.setVisibility(View.GONE);

    }


}
