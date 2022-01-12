package com.geogreenapps.apps.indukaan.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.appconfig.AppConfig;
import com.geogreenapps.apps.indukaan.appconfig.Constances;
import com.geogreenapps.apps.indukaan.classes.Category;
import com.geogreenapps.apps.indukaan.fragments.ListStoresFragment;
import com.geogreenapps.apps.indukaan.fragments.MainFragment;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;


public class ListStoresActivity extends GlobalActivity {

    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_description)
    TextView toolbarDescription;
    @BindView(R.id.frame)
    LinearLayout frame;

    private Category mCat = null;
    private int userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        setContentView(R.layout.store_list_activity);
        ButterKnife.bind(this);

        initToolbar();
        Realm realm = Realm.getDefaultInstance();

        try {
            userId = Objects.requireNonNull(getIntent().getExtras()).getInt("user_id");

            int catId = getIntent().getExtras().getInt("category");
            mCat = realm.where(Category.class).equalTo("numCat", catId).findFirst();
            if (mCat != null) {
                toolbarTitle.setText(mCat.getNameCat());
            }
        } catch (Exception ex) {
            if (AppConfig.APP_DEBUG)
                Toast.makeText(this, "Error @655", Toast.LENGTH_LONG).show();

            if (AppConfig.APP_DEBUG)
                ex.printStackTrace();

            finish();
        }


        ListStoresFragment listFrag = new ListStoresFragment();
        Bundle b = new Bundle();
        try {
            b.putInt("category", mCat.getNumCat());
        } catch (Exception e) {

        }

        b.putInt("user_id", userId);
        listFrag.setArguments(b);


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.frame, listFrag, MainFragment.TAG);
        // transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (android.R.id.home == item.getItemId()) {
            finish();
        } else if (item.getItemId() == R.id.search_icon) {
            Intent intent = new Intent(this, CustomSearchActivity.class);
            intent.putExtra("selected_module", Constances.ModulesConfig.STORE_MODULE);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cat_menu, menu);

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void initToolbar() {

        toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarDescription.setVisibility(View.GONE);

    }
}
