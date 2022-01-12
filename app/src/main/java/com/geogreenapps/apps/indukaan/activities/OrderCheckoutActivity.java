package com.geogreenapps.apps.indukaan.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.geogreenapps.apps.indukaan.AppController;
import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.Services.GenericNotifyEvent;
import com.geogreenapps.apps.indukaan.appconfig.AppContext;
import com.geogreenapps.apps.indukaan.appconfig.Constances;
import com.geogreenapps.apps.indukaan.classes.CF;
import com.geogreenapps.apps.indukaan.classes.Cart;
import com.geogreenapps.apps.indukaan.classes.Option;
import com.geogreenapps.apps.indukaan.classes.OrderableItem;
import com.geogreenapps.apps.indukaan.classes.Variant;
import com.geogreenapps.apps.indukaan.controllers.SettingsController;
import com.geogreenapps.apps.indukaan.controllers.cart.CartController;
import com.geogreenapps.apps.indukaan.controllers.sessions.SessionsController;
import com.geogreenapps.apps.indukaan.controllers.stores.OffersController;
import com.geogreenapps.apps.indukaan.controllers.stores.ProductsController;
import com.geogreenapps.apps.indukaan.customview.AdvancedWebViewActivity;
import com.geogreenapps.apps.indukaan.fragments.orderFrags.ConfirmationFragment;
import com.geogreenapps.apps.indukaan.fragments.orderFrags.OrderInfoFragment;
import com.geogreenapps.apps.indukaan.fragments.orderFrags.PaymentFragment;
import com.geogreenapps.apps.indukaan.network.VolleySingleton;
import com.geogreenapps.apps.indukaan.network.api_request.SimpleRequest;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.geogreenapps.apps.indukaan.appconfig.AppConfig.APP_DEBUG;
import static com.geogreenapps.apps.indukaan.security.Security.newInstance;

public class OrderCheckoutActivity extends GlobalActivity {


    //checkout navigation fields
    private enum State {ORDER, PAYMENT, CONFIRMATION}

    State[] array_state = new State[]{State.ORDER, State.CONFIRMATION, State.PAYMENT};
    private View line_first, line_second;
    private ImageView image_shipping, image_payment, image_confirm;
    private TextView tv_shipping, tv_payment, tv_confirm;
    private int idx_state = 0;


    //init static params
    public static HashMap<String, String> orderFields;
    public static int order_id = -1;
    public static int module_id = -1;
    private String module;

    public static OrderableItem mItemOrderble;


    public static List<Cart> mCart;


    public static int PAYMENT_CALLBACK_CODE = 2020;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        setContentView(R.layout.activity_order_checkout_step);
        initToolbar();

        initComponent();

        buttonRtlSupp();

        handleIntentAction();


        if (getIntent().hasExtra("fragmentToOpen") && getIntent().hasExtra("order_id")) {
            if (getIntent().getStringExtra("fragmentToOpen").equals("fragment_payment")) {
                order_id = getIntent().getIntExtra("order_id", -1);
                navigateToPaymentFragment();
            }
        } else {
            // display the first fragment as a default page
            displayFragment(State.ORDER);
        }

    }


    private void buttonRtlSupp() {
        //rtl
        if (AppController.isRTL()) {
            ((ImageView) findViewById(R.id.arrow_next)).setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.forward, null));
            ((ImageView) findViewById(R.id.arrow_previous)).setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.forward, null));
        } else {
            ((ImageView) findViewById(R.id.arrow_next)).setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.back, null));
            ((ImageView) findViewById(R.id.arrow_previous)).setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.back, null));
        }

        ((ImageView) findViewById(R.id.arrow_next)).setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        ((ImageView) findViewById(R.id.arrow_previous)).setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);

    }

    private void handleIntentAction() {
        //checkout from offer
        if (getIntent().hasExtra("module_id") && getIntent().hasExtra("module")) {

            if (getIntent().hasExtra("cart")) {
                try {
                    JSONObject mJsonObject = new JSONObject(getIntent().getStringExtra("cart"));

                    mCart = new ArrayList<>();

                    for (int i = 0; i < mJsonObject.length(); i++) {

                        JSONObject jsonRow = mJsonObject.getJSONObject(String.valueOf(i));
                        Cart c = new Cart();
                        c.setModule_id(jsonRow.getInt("module_id"));
                        c.setModule(jsonRow.getString("module"));
                        c.setAmount(jsonRow.getDouble("amount"));
                        c.setQte(jsonRow.getInt("qty"));
                        mCart.add(c);
                    }

                    if (mCart.size() > 0)
                        if (mCart.get(0).getModule().equalsIgnoreCase(Constances.ModulesConfig.OFFER_MODULE))
                            mItemOrderble = OffersController.findOfferById(mCart.get(0).getModule_id());
                        else if (mCart.get(0).getModule().equalsIgnoreCase(Constances.ModulesConfig.PRODUCT_MODULE))
                            mItemOrderble = ProductsController.findProductById(mCart.get(0).getModule_id());


                    if (mItemOrderble != null) {
                        //disable payment if the modules is disabled or the product_id price equal to 0
                        if (!SettingsController.isModuleEnabled(Constances.ModulesConfig.ORDER_PAYMENT_MODULE) || mItemOrderble.getProduct_value() == 0 || mItemOrderble.getProduct_type().equalsIgnoreCase("Percent")) {
                            disablePaymentModule();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Something went wrong, please try later!", Toast.LENGTH_LONG).show();
                        finish();
                    }

                } catch (JSONException e) {
                    mCart = null;
                }


            }


        }
        //checkout from cart list
        if (getIntent().hasExtra("fromCart")) {
            //get data from database
            mCart = CartController.listProducts(SessionsController.getSession().getUser().getId());
            mItemOrderble = (mCart != null && mCart.size() > 0) ? mCart.get(0).getProduct() : null;
            module = Constances.ModulesConfig.PRODUCT_MODULE;

        }


    }

    private void navigateToPaymentFragment() {
        idx_state = array_state.length - 1;
        buttonStatusChange();
        displayFragment(State.PAYMENT);
    }

    private void disablePaymentModule() {
        //hide payment wizard
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) findViewById(R.id.wizard_confirm).getLayoutParams();
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);

        findViewById(R.id.wizard_payment).setVisibility(View.GONE);
        //findViewById(R.id.line_second).setVisibility(View.GONE);
        //remove the payment from state list
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            array_state = Arrays.stream(array_state)
                    .filter(obj -> !obj.equals(State.PAYMENT))
                    .toArray(State[]::new);
        }
    }


    private void initComponent() {
        line_first = findViewById(R.id.line_first);
        line_second = findViewById(R.id.line_second);
        image_shipping = findViewById(R.id.image_shipping);
        image_payment = findViewById(R.id.image_payment);
        image_confirm = findViewById(R.id.image_confirm);

        tv_shipping = findViewById(R.id.tv_shipping);
        tv_payment = findViewById(R.id.tv_payment);
        tv_confirm = findViewById(R.id.tv_confirm);

        image_payment.setColorFilter(getResources().getColor(R.color.grey_20), PorterDuff.Mode.SRC_ATOP);
        image_confirm.setColorFilter(getResources().getColor(R.color.grey_20), PorterDuff.Mode.SRC_ATOP);

        (findViewById(R.id.lyt_next)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //check for required field
                if (array_state[idx_state] == State.ORDER)
                    if (checkRequiredFields(mItemOrderble)) {
                        //display error message and cancel the operation
                        Toast.makeText(OrderCheckoutActivity.this, getString(R.string.complet_required_fileds), Toast.LENGTH_LONG).show();
                        return;
                    }

                //check content format
                if (!checkRegexFormatField(mItemOrderble)) {
                    //display error message and cancel the operation
                    return;
                }


                //Submit order action
                if (array_state[idx_state] == State.CONFIRMATION) {
                    submitOrderAPI();
                    if (idx_state == array_state.length - 1) {
                        return;

                    }
                }


                // Pay order action
                if (array_state[idx_state] == State.PAYMENT) {

                    if (SettingsController.isModuleEnabled(Constances.ModulesConfig.ORDER_PAYMENT_MODULE) && mItemOrderble.getProduct_value() > 0) {
                        if (PaymentFragment.paymentChoosed == -1) {
                            Toast.makeText(OrderCheckoutActivity.this, "Please , Choose your payment gateway!", Toast.LENGTH_LONG).show();
                            return;
                        }
                        generatePaymentLinkAPi(v);


                        return;

                    }

                }

                //navigate to the next fragment
                idx_state++;
                displayFragment(array_state[idx_state]);

                //change btton status after click
                buttonStatusChange();

            }
        });

        (

                findViewById(R.id.lyt_previous)).

                setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (idx_state < 1) return;
                        idx_state--;
                        displayFragment(array_state[idx_state]);

                        buttonStatusChange();

                    }
                });

    }

    private void showSuccessPage() {

        findViewById(R.id.layout_content).setVisibility(View.GONE);
        findViewById(R.id.layout_done).setVisibility(View.VISIBLE);
        findViewById(R.id.lyt_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // run event to update  the order list
                EventBus.getDefault().postSticky(new GenericNotifyEvent("order_updated"));


                finish();
            }
        });

        //update color
        findViewById(R.id.lyt_done).setBackgroundColor(getResources().getColor(R.color.green));


    }

    private void buttonStatusChange() {


        if (idx_state == array_state.length - 1) {

            if (mItemOrderble == null)   //if the order is not available close the checkout activity
            {
                Toast.makeText(getApplicationContext(), "Oops!! Order not found...", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }

            if (!SettingsController.isModuleEnabled(Constances.ModulesConfig.ORDER_PAYMENT_MODULE) || mItemOrderble.getProduct_value() == 0 || mItemOrderble.getProduct_type().equalsIgnoreCase("Percent")) {
                ((TextView) findViewById(R.id.btn_next)).setText(getString(R.string.confirm_order));
            } else {
                ((TextView) findViewById(R.id.btn_next)).setText(getString(R.string.confirm_payment));
                findViewById(R.id.lyt_previous).setVisibility(View.GONE);
            }

            (findViewById(R.id.arrow_next)).setVisibility(View.GONE);
        } else {
            ((TextView) findViewById(R.id.btn_next)).setText(getString(R.string.next));
            (findViewById(R.id.arrow_next)).setVisibility(View.VISIBLE);
            (findViewById(R.id.lyt_previous)).setVisibility(View.VISIBLE);

        }

    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.close);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void displayFragment(State state) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putInt("module_id", module_id);
        bundle.putString("module", module);

        Fragment fragment = null;


        refreshStepTitle();

        if (state.name().equalsIgnoreCase(State.ORDER.name())) {
            fragment = new OrderInfoFragment();
            fragment.setArguments(bundle);
            tv_shipping.setTextColor(getResources().getColor(R.color.colorPrimary));

            image_shipping.clearColorFilter();
            line_first.setBackgroundColor(getResources().getColor(R.color.grey_20));
            line_second.setBackgroundColor(getResources().getColor(R.color.grey_20));
        } else if (state.name().equalsIgnoreCase(State.CONFIRMATION.name())) {
            fragment = new ConfirmationFragment();
            fragment.setArguments(bundle);

            tv_shipping.setTextColor(getResources().getColor(R.color.colorPrimary));
            tv_confirm.setTextColor(getResources().getColor(R.color.colorPrimary));


            line_first.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

            //when payment is disabled
            // todo : check this only for offers
            if (!SettingsController.isModuleEnabled(Constances.ModulesConfig.ORDER_PAYMENT_MODULE) || mItemOrderble.getProduct_value() == 0 || mItemOrderble.getProduct_type().equalsIgnoreCase("Percent")) {
                line_second.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }

            image_confirm.clearColorFilter();
        } else if (state.name().equalsIgnoreCase(State.PAYMENT.name())) {
            fragment = new PaymentFragment();
            fragment.setArguments(bundle);

            tv_shipping.setTextColor(getResources().getColor(R.color.colorPrimary));
            tv_payment.setTextColor(getResources().getColor(R.color.colorPrimary));
            tv_confirm.setTextColor(getResources().getColor(R.color.colorPrimary));

            line_first.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            line_second.setBackgroundColor(getResources().getColor(R.color.colorPrimary));


            image_shipping.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
            image_payment.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
            image_confirm.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);

        }

        if (fragment == null) return;
        fragmentTransaction.replace(R.id.frame_content, fragment);
        fragmentTransaction.commit();
    }

    private void refreshStepTitle() {
        tv_shipping.setTextColor(getResources().getColor(R.color.grey_40));
        tv_payment.setTextColor(getResources().getColor(R.color.grey_40));
        tv_confirm.setTextColor(getResources().getColor(R.color.grey_40));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_setting, menu);
        //Tools.changeMenuIconColor(menu, getResources().getColor(R.color.grey_20));
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        orderFields = null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean checkRequiredFields(final OrderableItem mItemOrderble) {

        Boolean result = false;
        if (mItemOrderble != null) {
            for (CF mCF : mItemOrderble.getCf()) {
                if (mCF.getRequired() == 1) {
                    if (orderFields != null && !orderFields.containsKey(mCF.getLabel())
                            || (orderFields.containsKey(mCF.getLabel())
                            && orderFields.get(mCF.getLabel()).trim().length() == 0)) {
                        result = true;
                        break;
                    }
                }

            }
        }
        return result;
    }


    private boolean checkRegexFormatField(final OrderableItem mItemOrderble) {

        Boolean result = true;
        if (mItemOrderble != null) {
            for (CF mCF : mItemOrderble.getCf()) {
                if (mCF.getType() != null) {
                    String[] arrayType = mCF.getType().split("\\.");

                    //check if location field is good
                    if (arrayType.length > 0 && arrayType[1].equals("location")) {
                        if (orderFields != null && orderFields.containsKey(mCF.getLabel())) {
                            String[] locationFormat = orderFields.get(mCF.getLabel()).split(";");
                            if (locationFormat.length != 3) {
                                Toast.makeText(this, getString(R.string.location_format_not_correct), Toast.LENGTH_SHORT).show();
                                result = false;
                                break;
                            } else {
                                if (locationFormat[0].length() == 0) {
                                    Toast.makeText(this, getString(R.string.location_address_not_correct), Toast.LENGTH_SHORT).show();
                                    result = false;
                                    break;
                                }
                                //check if float
                                try {
                                    Float.parseFloat(locationFormat[1]);
                                } catch (NumberFormatException e) {
                                    Toast.makeText(this, getString(R.string.location_format_not_correct), Toast.LENGTH_SHORT).show();
                                    result = false;
                                    break;
                                }

                                //check if float
                                try {
                                    Float.parseFloat(locationFormat[2]);
                                } catch (NumberFormatException e) {
                                    Toast.makeText(this, getString(R.string.location_format_not_correct), Toast.LENGTH_SHORT).show();
                                    result = false;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }


    private void displayPaymentWebView(final String plink) {


        // Encode data on your side using BASE64
        String cryptedLink = newInstance().encrypt(plink);
        String link = Constances.API.API_PAYMENT_LINK_CALL + "?redirect=" + cryptedLink + "&token=" + SessionsController.getSession().getUser().getToken();

        if (AppContext.DEBUG)
            Log.e("paymentLink", link);


        Intent intent = new Intent(this, AdvancedWebViewActivity.class);
        intent.putExtra("plink", link);
        startActivityForResult(intent, PAYMENT_CALLBACK_CODE);


    }

    private void submitOrderAPI() {

        RequestQueue queue = VolleySingleton.getInstance(this).getRequestQueue();
        queue = VolleySingleton.getInstance(this).getRequestQueue();
        Gson gson = new Gson();

        final Map<String, String> params = new HashMap<String, String>();

        if (SessionsController.isLogged()) {
            params.put("user_id", SessionsController.getSession().getUser().getId() + "");
        }


        params.put("module", Constances.ModulesConfig.STORE_MODULE);
        params.put("module_id", String.valueOf(mItemOrderble.getStore_id()));
        params.put("req_cf_id", String.valueOf(mItemOrderble.getCf_id()));

        if (orderFields != null && !orderFields.isEmpty()) {
            String json = gson.toJson(orderFields); // convert hashmaps to json format
            params.put("req_cf_data", json);
        }

        try {
            JSONArray carts = new JSONArray();

            for (Cart c : mCart) {
                JSONObject cart = new JSONObject();
                cart.put("module", c.getModule());
                cart.put("module_id", c.getModule_id());
                cart.put("qty", c.getQte());
                cart.put("amount", c.getAmount() > 0 ? c.getAmount() : 0);

                if (c.getVariants() != null && c.getVariants().size() > 0) {
                    List<Variant> variants = c.getVariants();
                    JSONObject variantsParser = new JSONObject();
                    for (Variant var : variants) {
                        JSONObject optJson = new JSONObject();
                        for (Option opt : var.getOptions()) {
                            optJson.put(opt.getLabel(), String.valueOf(opt.getValue()));
                        }
                        variantsParser.put(var.getGroup_label(), optJson);
                    }

                    cart.put("variants", variantsParser.toString());

                }
                carts.put(cart);
            }
            params.put("cart", carts.toString());


        } catch (JSONException e) {
            e.printStackTrace();
        }


        //send these params only when payment is enabled
        if (SettingsController.isModuleEnabled(Constances.ModulesConfig.ORDER_PAYMENT_MODULE) && mItemOrderble.getProduct_value() > 0 && mItemOrderble.getProduct_type().equalsIgnoreCase("Price")) {
            params.put("user_token", SessionsController.getSession().getUser().getToken());
            params.put("payment_method", String.valueOf(PaymentFragment.paymentChoosed));
        }


        SimpleRequest request = new SimpleRequest(Request.Method.POST,
                Constances.API.API_ORDERS_CREATE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    if (AppContext.DEBUG)
                        Log.e("order_api_output", response);

                    JSONObject jso = new JSONObject(response);
                    int success = jso.getInt("success");
                    if (success == 1) {
                        order_id = jso.getInt("result");
                        if (!SettingsController.isModuleEnabled(Constances.ModulesConfig.ORDER_PAYMENT_MODULE) || mItemOrderble.getProduct_value() == 0 || mItemOrderble.getProduct_type().equalsIgnoreCase("Percent")) {
                            showSuccessPage();
                        } else {
                            // displayPaymentWebView(jso.getString("plink"));
                            navigateToPaymentFragment();
                        }


                        //todo : complete this method
                        //Save custom field in shared pref
                        if (orderFields != null && !orderFields.isEmpty()) {

                            int userId = SessionsController.getSession().getUser().getId();
                            int cfId = mItemOrderble.getCf_id();
                            final SharedPreferences sharedPref = AppController.getInstance()
                                    .getSharedPreferences("savedCF_" + cfId + "_" + userId, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putInt("user_id", userId);
                            editor.putInt("req_cf_id", mItemOrderble.getCf_id());
                            editor.putString("cf", gson.toJson(orderFields));
                            editor.commit();
                        }


                    } else {
                        Toast.makeText(OrderCheckoutActivity.this, getString(R.string.error_try_later), Toast.LENGTH_LONG).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (APP_DEBUG) Log.e("ERROR", error.toString());
            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                if (AppContext.DEBUG)
                    Log.e("order_api_input", params.toString());

                return params;
            }

        };

        request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);

    }


    private void generatePaymentLinkAPi(View v) {

        //disable click
        v.setEnabled(false);
        v.setClickable(false);

        RequestQueue queue = VolleySingleton.getInstance(this).getRequestQueue();

        final Map<String, String> params = new HashMap<String, String>();

        if (SessionsController.isLogged()) {
            params.put("user_id", String.valueOf(SessionsController.getSession().getUser().getId()));
            params.put("user_token", String.valueOf(SessionsController.getSession().getUser().getToken()));
        }


        params.put("order_id", String.valueOf(order_id));
        params.put("payment", String.valueOf(PaymentFragment.paymentChoosed));


        SimpleRequest request = new SimpleRequest(Request.Method.POST,
                Constances.API.API_PAYMENT_LINK, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jso = new JSONObject(response);
                    String payment_link = jso.getString("result");
                    if (payment_link != null) {
                        displayPaymentWebView(payment_link);
                    } else {
                        Toast.makeText(OrderCheckoutActivity.this, getString(R.string.error_try_later), Toast.LENGTH_LONG).show();

                    }

                    //enable  button
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            v.setEnabled(true);
                            v.setClickable(true);
                        }
                    }, 3500);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (APP_DEBUG) Log.e("ERROR", error.toString());
            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                if (AppContext.DEBUG)
                    Log.e("orders_params", params.toString());

                return params;
            }

        };

        request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PAYMENT_CALLBACK_CODE) {
            if (resultCode == AutocompleteActivity.RESULT_OK) {


                showSuccessPage();
            } else {
                Toast.makeText(this, getString(R.string.payment_error), Toast.LENGTH_LONG).show();

            }
        }
    }
}

