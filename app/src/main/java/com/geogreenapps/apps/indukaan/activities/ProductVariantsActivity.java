package com.geogreenapps.apps.indukaan.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.fragments.orderFrags.ProductVariantFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProductVariantsActivity extends GlobalActivity {

    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_description)
    TextView toolbarDescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        setContentView(R.layout.fragment_bookmark);
        ButterKnife.bind(this);

        initToolbar();

        if (getIntent().hasExtra("module_id")) {
            Bundle bundle = new Bundle();
            bundle.putInt("module_id", getIntent().getIntExtra("module_id", -1));
            bundle.putString("module", getIntent().getStringExtra("module"));

            ProductVariantFragment fragment = new ProductVariantFragment();
            fragment.setArguments(bundle);

            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();

        } else {
            Toast.makeText(this, R.string.something_went_wrong, Toast.LENGTH_LONG).show();
            finish();
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarDescription.setVisibility(View.GONE);

        toolbarTitle.setText(R.string.order_detail_checkout);


    }
}
