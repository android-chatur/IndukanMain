package com.geogreenapps.apps.indukaan.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.adapter.lists.ItemsAdapter;
import com.geogreenapps.apps.indukaan.adapter.order.TimeLineAdapter;
import com.geogreenapps.apps.indukaan.appconfig.Constances;
import com.geogreenapps.apps.indukaan.classes.Currency;
import com.geogreenapps.apps.indukaan.classes.Item;
import com.geogreenapps.apps.indukaan.classes.Order;
import com.geogreenapps.apps.indukaan.classes.Store;
import com.geogreenapps.apps.indukaan.classes.User;
import com.geogreenapps.apps.indukaan.controllers.orders.OrdersController;
import com.geogreenapps.apps.indukaan.helper.CommunFunctions;
import com.geogreenapps.apps.indukaan.restApis.OrderApis;
import com.geogreenapps.apps.indukaan.utils.DateUtils;
import com.geogreenapps.apps.indukaan.utils.ProductUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.Gravity.END;

public class OrderDetailActivity extends GlobalActivity implements OrderApis.OrderRestAPisDelegate {

    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_description)
    TextView toolbarDescription;


    @BindView(R.id.order_id)
    TextView order_id;
    @BindView(R.id.payment_method)
    TextView payment_method;
    @BindView(R.id.delivery_on)
    TextView delivery_on;

    @BindView(R.id.total_price)
    TextView total_price;

    @BindView(R.id.order_status)
    TextView order_status;


    @BindView(R.id.item_detail)
    LinearLayout item_wrapper;

    @BindView(R.id.order_tracking)
    LinearLayout order_tracking;

    @BindView(R.id.btn_track_order)
    AppCompatButton btn_track_order;

    @BindView(R.id.item_wrapper_fees)
    LinearLayout item_wrapper_fees;

    @BindView(R.id.tax_layout)
    LinearLayout tax_layout;

    @BindView(R.id.tax_value)
    TextView tax_value;


    private Currency defaultCurrency = null;

    private RecyclerView list;
    private Order mOrder;
    private OrderApis call;
    private double extraFees;


    @OnClick(R.id.btn_track_order)
    void updateStatusClick(View view) {
        // display a popup
        String[] list_status = new String[]{getString(R.string.report)};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.update_order_status);
        builder.setSingleChoiceItems(list_status, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                int selectedPosition = ((AlertDialog) dialogInterface).getListView().getCheckedItemPosition();
                if (selectedPosition == 0) {
                    Intent intent = new Intent(OrderDetailActivity.this, ReportOrderActivity.class);
                    intent.putExtra("order_id", mOrder.getId());
                    startActivity(intent);
                    finish();
                }

            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }

        });

        builder.show();

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        setContentView(R.layout.fragment_order_detail);
        ButterKnife.bind(this);

        initToolbar();

        //delegate a listener to retrieve data
        call = OrderApis.newInstance();
        call.delegate = this;

        retrieveDatafromOrder();

        setupTimeLineRV();

    }


    @Override
    protected void onStart() {
        super.onStart();
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

    private void retrieveDatafromOrder() {
        try {
            if (getIntent() != null && getIntent().hasExtra("id"))
                mOrder = OrdersController.findOrderById(getIntent().getExtras().getInt("id"));

            if (mOrder != null) {

                //get default currency from the first item
                if (mOrder.getItems() != null && mOrder.getItems().size() > 0)
                    defaultCurrency = mOrder.getItems().get(0).getCurrency();

                if (mOrder.getPayment_status() != null) {
                    String[] payment_array = mOrder.getPayment_status().split(";");
                    payment_method.setText(payment_array.length > 0 ? payment_array[0] : "");
                }

                String inputDate = DateUtils.prepareOutputDate(mOrder.getCreated_at(), "dd MMMM yyyy  hh:mm", this);
                order_id.setText("#" + mOrder.getId());
                delivery_on.setText(inputDate);

                //set status with color
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    CommunFunctions.getDelivetStatuFromID(order_status, mOrder.getDelivery_status());
                }

                if (mOrder.getExtras() != null && !mOrder.getExtras().equals("null")) {
                    try {
                        extraFees = CommunFunctions.parseExtraFees(item_wrapper_fees, mOrder.getExtras(), defaultCurrency);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    item_wrapper_fees.setVisibility(View.VISIBLE);
                } else {
                    item_wrapper_fees.setVisibility(View.GONE);
                }

                //check if can track the order
                //show tracking option when delievry status is picked up
                if (mOrder.getDelivery_status() == Constances.DELIVERY_STATUS.PICKED_UP) {
                    btn_track_order.setVisibility(View.VISIBLE);
                    btn_track_order.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(OrderDetailActivity.this, DeliveryTrackingActivity.class);
                            HashMap<String, String> params = new HashMap<>();
                            params.put("delivery_id", String.valueOf(mOrder.getDelivery_id()));
                            intent.putExtra("params", params);
                            startActivity(intent);
                        }
                    });
                } else {
                    btn_track_order.setVisibility(View.GONE);
                }


                //display tax if exist
                ItemsAdapter mProductAdapter = new ItemsAdapter(this, mOrder.getItems());
                if (mProductAdapter.getTotalPrice() > 0 && ((mOrder.getAmount() - mProductAdapter.getTotalPrice()) >= 1)) {
                    tax_layout.setVisibility(View.VISIBLE);

                    float taxes = (float) (mOrder.getAmount() - mProductAdapter.getTotalPrice() - extraFees);
                    if (taxes < 0) tax_layout.setVisibility(View.GONE);

                    tax_value.setText(
                            ProductUtils.parseCurrencyFormat(taxes, mProductAdapter.getCurrency()));
                } else {
                    tax_layout.setVisibility(View.GONE);
                }


                //parse products from order
                generateViewFromData(mOrder, defaultCurrency);


            }

        } catch (Exception e) {

        }
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


    private void setupTimeLineRV() {

        if (mOrder.getTimeLines() != null && mOrder.getTimeLines().size() > 0) {
            TimeLineAdapter adapter = new TimeLineAdapter(this, mOrder.getTimeLines());
            list = findViewById(R.id.recyclerView);
            list.setHasFixedSize(false);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
            mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            list.setLayoutManager(mLayoutManager);
            list.setAdapter(adapter);

            order_tracking.setVisibility(View.VISIBLE);

        } else {
            order_tracking.setVisibility(View.GONE);
        }


    }


    private void generateViewFromData(final Order mOrder, final Currency _defCurrency) {

        //global fields
        LinearLayout.LayoutParams lp_match_wrap = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        if (mOrder != null && mOrder.getItems() != null && mOrder.getItems().size() > 0) {
            for (Item item : mOrder.getItems()) {


                LinearLayout item_detail = new LinearLayout(this);
                item_detail.setOrientation(LinearLayout.VERTICAL);
                item_detail.setLayoutParams(lp_match_wrap);

                LinearLayout linearLayout_11 = new LinearLayout(this);
                linearLayout_11.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams lp_match_wrap_11 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                //lp_match_wrap_11.setMargins((int) getResources().getDimension(R.dimen.spacing_middle), 0, 0, (int) getResources().getDimension(R.dimen.spacing_middle));
                linearLayout_11.setLayoutParams(lp_match_wrap_11);

                TextView item_name = new TextView(this);
                LinearLayout.LayoutParams lp_item_name = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp_item_name.weight = 1;
                item_name.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                item_name.setLayoutParams(lp_item_name);
                linearLayout_11.addView(item_name);

                TextView item_price = new TextView(this);
                item_price.setGravity(END);
                item_price.setTypeface(item_price.getTypeface(), Typeface.BOLD);
                item_price.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);

                LinearLayout.LayoutParams lp_item_price = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                item_price.setLayoutParams(lp_item_price);
                linearLayout_11.addView(item_price);
                item_detail.addView(linearLayout_11);


                item_wrapper.addView(item_detail);


                //dynamic content
                item_name.setText(item.getName() + " x " + item.getQty());

                if (item.getAmount() > 0) {
                    //calculate  the amount based on qty
                    //double amountOrder = item.getAmount() * (item.getQty() > 0 ? item.getQty() : 1);
                    item_price.setText(ProductUtils.parseCurrencyFormat(
                            (float) item.getAmount() * item.getQty(), _defCurrency));
                    item_price.setVisibility(View.VISIBLE);

                } else {
                    item_price.setVisibility(View.GONE);
                }


            }

        }

        total_price.setText(ProductUtils.parseCurrencyFormat((float) mOrder.getAmount(), _defCurrency));


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

            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), getString(R.string.error_try_later), Toast.LENGTH_LONG).show();
            }

        }


    }

    @Override
    public void onError(OrderApis object, Map<String, String> errors) {

    }
}
