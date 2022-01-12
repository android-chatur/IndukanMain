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
import com.geogreenapps.apps.indukaan.fragments.ListStoresFragment;

import java.util.Objects;


public class StoresListActivity extends GlobalActivity {

    Toolbar toolbar;
    private TextView APP_TITLE_VIEW = null;
    private TextView APP_DESC_VIEW = null;
    private boolean favStores = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        setContentView(R.layout.fragment_bookmark);

        initToolbar();


        Bundle bundle = new Bundle();
        if (getIntent().hasExtra("fav")) {
            favStores = getIntent().hasExtra("fav");
            bundle.putInt("fav", Objects.requireNonNull(getIntent().getExtras()).getInt("fav"));
            APP_TITLE_VIEW.setText(R.string.my_stores);
        } else {
            APP_TITLE_VIEW.setText(R.string.stores_nearby);

        }

        ListStoresFragment fragment = new ListStoresFragment();
        fragment.setArguments(bundle);


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
            intent.putExtra("selected_module", Constances.ModulesConfig.STORE_MODULE);
            startActivity(intent);
        } else if (item.getItemId() == R.id.cart_icon) {
            Intent intent = new Intent(this, ProductCartActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.lefttoright_enter, R.anim.lefttoright_exit);
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);

        menu.findItem(R.id.search_icon).setVisible(true);
        menu.findItem(R.id.qr_scanner).setVisible(false);
        menu.findItem(R.id.notification_action).setVisible(false);


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
}
