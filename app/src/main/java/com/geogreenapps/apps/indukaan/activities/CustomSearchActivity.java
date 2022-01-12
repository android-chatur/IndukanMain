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
import com.geogreenapps.apps.indukaan.fragments.CustomSearchFragment;


public class CustomSearchActivity extends GlobalActivity {

    Toolbar toolbar;
    private TextView APP_TITLE_VIEW = null;
    private TextView APP_DESC_VIEW = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        setContentView(R.layout.activity_search_custom);

        initToolbar();

        APP_TITLE_VIEW.setText(getString(R.string.search));

        CustomSearchFragment fragment = new CustomSearchFragment();

        Bundle bundle = new Bundle();

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra("useCacheFields")) {
            bundle.putString("useCacheFields", intent.getStringExtra("useCacheFields"));
        }

        if (intent != null && intent.hasExtra("searchParams")) {
            bundle.putSerializable("searchParams", intent.getSerializableExtra("searchParams"));
        }

        if (intent != null && intent.hasExtra("selected_module")) {
            bundle.putString("selected_module", intent.getStringExtra("selected_module"));
        }


        fragment.setArguments(bundle);

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.search_content, fragment)
                .commit();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        menu.findItem(R.id.search_icon).setVisible(false);
        menu.findItem(R.id.cart_icon).setVisible(false);
        menu.findItem(R.id.qr_scanner).setVisible(false);

        return true;
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
    protected void onResume() {
        super.onResume();
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

}
