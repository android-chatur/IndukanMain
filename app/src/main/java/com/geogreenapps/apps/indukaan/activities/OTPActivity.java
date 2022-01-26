package com.geogreenapps.apps.indukaan.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.geogreenapps.apps.indukaan.R;

import butterknife.BindView;

public class OTPActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_description)
    TextView toolbarDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpactivity);
        initToolbar();

    }
    public void initToolbar() {

        toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        toolbarDescription = toolbar.findViewById(R.id.toolbar_description);

        toolbarDescription.setVisibility(View.GONE);
        toolbarTitle.setText(getResources().getText(R.string.sign_up));
    }

}