package com.geogreenapps.apps.indukaan.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.appconfig.Constances;
import com.geogreenapps.apps.indukaan.classes.Store;
import com.geogreenapps.apps.indukaan.classes.User;
import com.geogreenapps.apps.indukaan.controllers.sessions.SessionsController;
import com.geogreenapps.apps.indukaan.restApis.OrderApis;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReportOrderActivity extends GlobalActivity implements OrderApis.OrderRestAPisDelegate {

    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_description)
    TextView toolbarDescription;


    @BindView(R.id.report_choices_rg)
    RadioGroup report_choices_rg;
    @BindView(R.id.btn_update_status)
    AppCompatButton btn_update_status;

    @BindView(R.id.custom_report_message)
    TextView custom_report_message;

    @BindView(R.id.custom_report_message_layout)
    LinearLayout custom_report_message_layout;

    private String issueMessage = null;
    private OrderApis call;
    private int order_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        setContentView(R.layout.activity_report_issue);
        ButterKnife.bind(this);


        initToolbar();

        if (getIntent() != null && getIntent().hasExtra("order_id"))
            order_id = getIntent().getIntExtra("order_id", -1);

        //delegate a listener to retrieve data
        call = OrderApis.newInstance();
        call.delegate = this;


        addListenerOnButton();
    }


    private void retrieveOptionFromApi() {

    }


    public void initToolbar() {

        toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbarDescription.setVisibility(View.GONE);
        toolbarTitle.setText(R.string.order_detail);
    }


    public void addListenerOnButton() {

        report_choices_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.choice_other:
                        // do operations specific to this selection
                        custom_report_message_layout.setVisibility(View.VISIBLE);

                        break;
                    default:
                        custom_report_message_layout.setVisibility(View.GONE);
                        // find the radiobutton by returned id
                        RadioButton radioButton = findViewById(checkedId);
                        if (radioButton != null)
                            issueMessage = radioButton.getText().toString();
                        break;

                }
            }

        });

        btn_update_status.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                HashMap<String, String> listParams = new HashMap<>();

                listParams.put("delivery_id", String.valueOf(SessionsController.getSession().getUser().getId()));
                listParams.put("order_id", String.valueOf(order_id));
                listParams.put("message", issueMessage);
                listParams.put("delivery_status", String.valueOf(Constances.DELIVERY_STATUS.REPORTED));
                listParams.put("status", String.valueOf(Constances.DELIVERY_STATUS.PENDING));

                //call api to update status
                call.updateOrderStatus(listParams);



            }

        });


    }


    @Override
    public void onStoreSuccess(Store storeData) {

    }

    @Override
    public void onCustomerSuccess(User userData) {

    }

    @Override
    public void onOrderUpdate(JSONObject jsonObject) {

        if (jsonObject != null) {
            try {

                if (jsonObject.has("success") && jsonObject.getInt("success") == 1)
                    Toast.makeText(getApplicationContext(), getString(R.string.order_successfuly_updated), Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(), getString(R.string.error_try_later), Toast.LENGTH_LONG).show();


                finish();

            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), getString(R.string.error_try_later), Toast.LENGTH_LONG).show();
            }

        }


    }

    @Override
    public void onError(OrderApis object, Map<String, String> errors) {

    }
}