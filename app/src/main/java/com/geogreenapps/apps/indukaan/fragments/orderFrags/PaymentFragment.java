package com.geogreenapps.apps.indukaan.fragments.orderFrags;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.activities.OrderCheckoutActivity;
import com.geogreenapps.apps.indukaan.adapter.lists.PayGWAdapter;
import com.geogreenapps.apps.indukaan.appconfig.Constances;
import com.geogreenapps.apps.indukaan.classes.Cart;
import com.geogreenapps.apps.indukaan.classes.Fee;
import com.geogreenapps.apps.indukaan.classes.PaymentGateway;
import com.geogreenapps.apps.indukaan.classes.Setting;
import com.geogreenapps.apps.indukaan.controllers.SettingsController;
import com.geogreenapps.apps.indukaan.controllers.orders.PaymentController;
import com.geogreenapps.apps.indukaan.network.VolleySingleton;
import com.geogreenapps.apps.indukaan.network.api_request.SimpleRequest;
import com.geogreenapps.apps.indukaan.parser.api_parser.PayGWParser;
import com.geogreenapps.apps.indukaan.parser.tags.Tags;
import com.geogreenapps.apps.indukaan.utils.ProductUtils;
import com.geogreenapps.apps.indukaan.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;

import static com.geogreenapps.apps.indukaan.activities.OrderCheckoutActivity.mCart;
import static com.geogreenapps.apps.indukaan.appconfig.AppConfig.APP_DEBUG;


public class PaymentFragment extends Fragment implements PayGWAdapter.ClickListener {


    @BindView(R.id.mScroll)
    ScrollView mScroll;


    @BindView(R.id.list_payment)
    RecyclerView listPayment;

    @BindView(R.id.payment_detail_layout)
    LinearLayout paymentDetailLayout;

    @BindView(R.id.layout_fees)
    LinearLayout layoutFees;


    @BindView(R.id.layout_subtotal)
    LinearLayout layoutSubtotal;

    @BindView(R.id.subtotal_val)
    TextView subtotalVal;

    @BindView(R.id.layout_total)
    LinearLayout layoutTotal;

    @BindView(R.id.total_value)
    TextView totalValue;


    private PayGWAdapter mAdapter;
    private Context mContext;

    public static int paymentChoosed = -1;


    public PaymentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_payment, container, false);
        mContext = root.getContext();
        ButterKnife.bind(this, root);


        setupAdapter();

        return root;
    }


    public List<PaymentGateway> getData() {

        List<PaymentGateway> results = new ArrayList<>();

        RealmList<PaymentGateway> listCats = PaymentController.list();

        for (PaymentGateway cat : listCats) {
            results.add(cat);
        }


        return results;
    }

    @Override
    public void onStart() {
        super.onStart();

        if (getData() == null || getData().size() == 0) {
            getPaymentGatewayFromAPI();
        }
    }


    private void setupAdapter() {
        mAdapter = new PayGWAdapter(getActivity(), getData());
        mAdapter.setClickListener(this);
        listPayment.setHasFixedSize(true);

        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listPayment.setLayoutManager(mLayoutManager);
        listPayment.setAdapter(mAdapter);


        ((SimpleItemAnimator) listPayment.getItemAnimator()).setSupportsChangeAnimations(false);

    }

    @Override
    public void itemClicked(View view, int position) {

        paymentDetailLayout.setVisibility(View.VISIBLE);

        //scroll to the view
        Utils.scrollToView(mScroll, paymentDetailLayout);


        PaymentGateway mPG = mAdapter.getItemDetail(position);
        paymentChoosed = mPG.getId();

        //sum of all the item added in the card ( card = sum(amount * qte)
        double calculateTotal = 0;
        double totalFees = 0; //init total fees to 1


        //product price x qty
        for (Cart c : mCart) {
            if (c.getAmount() <= 0) calculateTotal = 0;
            else calculateTotal += c.getAmount() * c.getQte();
        }

        subtotalVal.setText(ProductUtils.parseCurrencyFormat(
                (float) calculateTotal,
                OrderCheckoutActivity.mItemOrderble.getCurrency()));

        //check fees
        if (mPG.getFees() != null && mPG.getFees().size() > 0) {
            for (Fee mFee : mPG.getFees()) {

                //fixing bug related to duplicated payment detail
                layoutFees.removeAllViews();


                //Build Layout item Fee
                LinearLayout itemLayoutFee = new LinearLayout(mContext);
                itemLayoutFee.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams itemLayoutFeeLP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                itemLayoutFeeLP.setMargins(Utils.dpToPx(5), Utils.dpToPx(5), Utils.dpToPx(5), Utils.dpToPx(5));
                itemLayoutFee.setLayoutParams(itemLayoutFeeLP);

                //Build Fee Name
                TextView feeName = new TextView(view.getContext());
                LinearLayout.LayoutParams feeNameLP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                feeNameLP.weight = 1;
                feeName.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                feeName.setLayoutParams(feeNameLP);
                feeName.setTypeface(feeName.getTypeface(), Typeface.BOLD);
                itemLayoutFee.addView(feeName);

                //Build Fee Value
                TextView feeValue = new TextView(view.getContext());
                LinearLayout.LayoutParams feeValueLP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                feeValue.setLayoutParams(feeValueLP);
                feeValue.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
                feeValue.setTypeface(feeName.getTypeface(), Typeface.BOLD);
                itemLayoutFee.addView(feeValue);


                //calculate total fees
                double feesValue = ((float) (mFee.getValue() / 100) * calculateTotal);
                totalFees += feesValue;

                feeName.setText(mFee.getName() + "(" + String.format("%s%%", (int) mFee.getValue()) + ")");

                feeValue.setText(
                        ProductUtils.parseCurrencyFormat(
                                (float) feesValue,
                                OrderCheckoutActivity.mItemOrderble.getCurrency()));

                layoutFees.addView(itemLayoutFee);

            }


        }

        if (SettingsController.isModuleEnabled(Constances.ModulesConfig.DELIVERY_MODULE)) {
            totalFees = parseDeliveryFees(view, totalFees, calculateTotal);
        }


        //add fees
        if (totalFees > 0)
            calculateTotal = calculateTotal + totalFees;

        totalValue.setText(ProductUtils.parseCurrencyFormat(
                (float) calculateTotal,
                OrderCheckoutActivity.mItemOrderble.getCurrency()));


    }


    private double parseDeliveryFees(View view, double totalFees, double calculateTotal) {


        //Build Layout item Fee
        LinearLayout itemLayoutFee = new LinearLayout(mContext);
        itemLayoutFee.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams itemLayoutFeeLP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        itemLayoutFeeLP.setMargins(Utils.dpToPx(5), Utils.dpToPx(5), Utils.dpToPx(5), Utils.dpToPx(5));
        itemLayoutFee.setLayoutParams(itemLayoutFeeLP);

        //Build Fee Name
        TextView feeName = new TextView(view.getContext());
        LinearLayout.LayoutParams feeNameLP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        feeNameLP.weight = 1;
        feeName.setLayoutParams(feeNameLP);
        feeName.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
        feeName.setTypeface(feeName.getTypeface(), Typeface.BOLD);
        itemLayoutFee.addView(feeName);

        //Build Fee Value
        TextView feeValue = new TextView(view.getContext());
        LinearLayout.LayoutParams feeValueLP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        feeValue.setLayoutParams(feeValueLP);
        feeValue.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        feeValue.setTypeface(feeName.getTypeface(), Typeface.BOLD);
        itemLayoutFee.addView(feeValue);


        String delivery_fees_type = null, delivery_fees_value = null;
        Setting dft = SettingsController.findSettingFiled("DELIVERY_FEES_TYPE");
        if (dft != null && !dft.getValue().equals("")) {
            delivery_fees_type = dft.getValue();
        }

        Setting dfv = SettingsController.findSettingFiled("DELIVERY_FEES_VALUE");
        if (dfv != null && !dfv.getValue().equals("")) {
            delivery_fees_value = dfv.getValue();
        }


        double feesValue = 0;
        if (delivery_fees_type.equalsIgnoreCase("disabled")) { // hide fees view¬
            itemLayoutFee.setVisibility(View.GONE);
        } else {
            if (delivery_fees_type != null && delivery_fees_type.equalsIgnoreCase("commission")) {
                feesValue = ((float) (Double.valueOf(delivery_fees_value) / 100) * calculateTotal);
                feeName.setText(mContext.getResources().getString(R.string.delivery_fees) + " (" + String.format("%s%%", delivery_fees_value) + ")");

            } else if (delivery_fees_type != null && delivery_fees_type.equalsIgnoreCase("fixed")) {
                feesValue = Double.valueOf(delivery_fees_value);
                feeName.setText(mContext.getResources().getString(R.string.delivery_fees));
            }
            itemLayoutFee.setVisibility(View.VISIBLE);

        }

        //calculate the total price
        totalFees += feesValue;

        feeValue.setText(
                ProductUtils.parseCurrencyFormat(
                        (float) feesValue,
                        OrderCheckoutActivity.mItemOrderble.getCurrency()));

        layoutFees.addView(itemLayoutFee);


        return totalFees;


    }


    private void getPaymentGatewayFromAPI() {


        RequestQueue queue = VolleySingleton.getInstance(mContext).getRequestQueue();

        SimpleRequest request = new SimpleRequest(Request.Method.GET,
                Constances.API.API_PAYMENT_GATEWAY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    if (APP_DEBUG) {
                        Log.e("paymentGateways", response);
                    }

                    JSONObject jsonObject = new JSONObject(response);
                    // Log.e("response", jsonObject.toString());
                    final PayGWParser mModuleParser = new PayGWParser(jsonObject);
                    int success = Integer.parseInt(mModuleParser.getStringAttr(Tags.SUCCESS));

                    if (success == 1 && mModuleParser.getPaymentGetway().size() > 0) {
                        mAdapter.addAll(mModuleParser.getPaymentGetway());

                        mAdapter.notifyDataSetChanged();

                        PaymentController.insertPaymentGatewayList(
                                mModuleParser.getPaymentGetway()
                        );
                    }

                } catch (JSONException e) {
                    //send a rapport to support
                    e.printStackTrace();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (APP_DEBUG) {
                    Log.e("ERROR", error.toString());
                }
            }
        }) {

        };

        request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);


    }

}