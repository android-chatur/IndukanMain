package com.geogreenapps.apps.indukaan.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.controllers.sessions.SessionsController;
import com.geogreenapps.apps.indukaan.fragments.InboxFragment;
import com.geogreenapps.apps.indukaan.fragments.LoginFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ListDiscussionsActivity extends GlobalActivity {

    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_description)
    TextView toolbarDescription;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        setContentView(R.layout.fragment_discussions_list);
        ButterKnife.bind(this);

        initToolbar();

        toolbarTitle.setText(R.string.tab_inbox);

        Bundle bundle = new Bundle();


        Fragment fragment;
        if (SessionsController.isLogged()) {
            fragment = new InboxFragment();
        } else
            fragment = new LoginFragment();


        fragment.setArguments(bundle);


        FragmentManager manager = getSupportFragmentManager();

        manager.beginTransaction()
                .replace(R.id.inbox_content, fragment)
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

    }
}
