package com.geogreenapps.apps.indukaan.fragments.orderFrags;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.animation.ImageLoaderAnimation;
import com.geogreenapps.apps.indukaan.classes.Cart;
import com.geogreenapps.apps.indukaan.utils.ProductUtils;
import com.geogreenapps.apps.indukaan.utils.Utils;

import java.text.DecimalFormat;
import java.util.Map;

import static android.widget.LinearLayout.HORIZONTAL;
import static android.widget.LinearLayout.VERTICAL;
import static com.geogreenapps.apps.indukaan.activities.OrderCheckoutActivity.mCart;
import static com.geogreenapps.apps.indukaan.activities.OrderCheckoutActivity.mItemOrderble;
import static com.geogreenapps.apps.indukaan.activities.OrderCheckoutActivity.orderFields;


public class ConfirmationFragment extends Fragment {

    private Context mContext;
    private float totalPriceCart = 0;


    public ConfirmationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_confirmation, container, false);
        mContext = root.getContext();

        parserInputViews(root);

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    public void calculateTotalPrice(View view) {

        if (mCart == null || mCart.size() == 0) return;

        for (Cart c : mCart) {
            if (c.getAmount() <= 0) {
                totalPriceCart = 0;
                return;
            } else {
                totalPriceCart += c.getAmount() * c.getQte();
            }
        }

    }


    private void parserInputViews(View view) {

        //generate products
        generateProductFromCart(view);

        //calculate total price
        calculateTotalPrice(view);

        //geneate custom fields
        generateCustomFields(view);


        if (mItemOrderble != null) {

            //product PRICE
            if (mItemOrderble.getCurrency() != null) {

                if (mItemOrderble.getProduct_type().equalsIgnoreCase("percent") && (mItemOrderble.getProduct_value() > 0 || mItemOrderble.getProduct_value() < 0)) {
                    DecimalFormat decimalFormat = new DecimalFormat("#0");
                    ((TextView) view.findViewById(R.id.total_price)).setText(ProductUtils.parseCurrencyFormat(
                            0, mItemOrderble.getCurrency()));
                } else {
                    if (mItemOrderble.getProduct_type().equalsIgnoreCase("Price") && mItemOrderble.getProduct_value() != 0) {

                        ((TextView) view.findViewById(R.id.total_price)).setText(ProductUtils.parseCurrencyFormat(
                                totalPriceCart,
                                mItemOrderble.getCurrency()));

                    } else {
                        ((TextView) view.findViewById(R.id.price_product)).setText(getString(R.string.promo));
                        ((TextView) view.findViewById(R.id.total_price)).setText(getString(R.string.promo));
                    }
                }


            }
        }


    }


    public void generateProductFromCart(View view) {

        //global fields
        LinearLayout.LayoutParams lp_match_wrap = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams lp_wrap_wrap = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout itemWrapper = view.findViewById(R.id.products_container);


        if (mCart == null || mCart.size() == 0) return;

        for (Cart c : mCart) {

            if (c.getOrderableItem() == null) return;

            //built static content
            LinearLayout product_detail_layout = new LinearLayout(mContext);
            LinearLayout.LayoutParams product_detail_layout_lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            product_detail_layout_lp.setMargins(0, (int) getResources().getDimension(R.dimen.spacing_medium), 0, 0);
            product_detail_layout.setOrientation(HORIZONTAL);
            product_detail_layout.setLayoutParams(product_detail_layout_lp);


            ImageView image_product = new ImageView(mContext);
            image_product.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams layout_916 = new LinearLayout.LayoutParams(120, 120);
            image_product.setLayoutParams(layout_916);
            product_detail_layout.addView(image_product);


            LinearLayout sub_layout = new LinearLayout(mContext);
            sub_layout.setPaddingRelative((int) getResources().getDimension(R.dimen.spacing_medium), 0, 0, 0);
            sub_layout.setOrientation(VERTICAL);
            LinearLayout.LayoutParams lp_match_wrap_c1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp_match_wrap_c1.setMarginStart((int) getResources().getDimension(R.dimen.spacing_medium));
            sub_layout.setLayoutParams(lp_match_wrap_c1);

            LinearLayout linearLayout_512 = new LinearLayout(mContext);
            linearLayout_512.setOrientation(HORIZONTAL);

            linearLayout_512.setLayoutParams(lp_match_wrap);

            TextView title_product = new TextView(mContext);
            title_product.setId(R.id.title_product);
            title_product.setTextColor(getResources().getColor(R.color.black));
            title_product.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            LinearLayout.LayoutParams lp_match_wrap_c2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp_match_wrap_c2.weight = 1;
            title_product.setLayoutParams(lp_match_wrap_c2);
            linearLayout_512.addView(title_product);

            TextView price_product = new TextView(mContext);
            price_product.setId(R.id.price_product);
            price_product.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
            price_product.setTypeface(price_product.getTypeface(), Typeface.BOLD);
            price_product.setTextColor(getResources().getColor(R.color.black));
            price_product.setLayoutParams(lp_wrap_wrap);
            linearLayout_512.addView(price_product);
            sub_layout.addView(linearLayout_512);

            TextView desc_product = new TextView(mContext);
            desc_product.setId(R.id.desc_product);
            desc_product.setTextColor(getResources().getColor(R.color.black));
            LinearLayout.LayoutParams lp_match_wrap_c3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp_match_wrap_c3.topMargin = (int) getResources().getDimension(R.dimen.spacing_medium);
            desc_product.setLayoutParams(lp_match_wrap_c3);


            //dynamic content

            //product image
            Glide.with(mContext)
                    .load(c.getOrderableItem().getImages()
                            .getUrl100_100())
                    .placeholder(ImageLoaderAnimation.glideLoader(mContext))
                    .centerCrop()
                    .into(image_product);

            title_product.setText(c.getOrderableItem().getName());

            if (c.getOrderableItem().getProduct_type().equalsIgnoreCase("percent") && (c.getOrderableItem().getProduct_value() > 0 || c.getOrderableItem().getProduct_value() < 0)) {
                price_product.setText(ProductUtils.parseCurrencyFormat(0, c.getOrderableItem().getCurrency()));
            } else {
                if (c.getOrderableItem().getProduct_type().equalsIgnoreCase("Price") && c.getOrderableItem().getProduct_value() != 0) {

                    price_product.setText(ProductUtils.parseCurrencyFormat(
                            (float) (c.getAmount() * c.getQte()),
                            c.getOrderableItem().getCurrency()));
                } else {
                    price_product.setText(getString(R.string.promo));
                }
            }


            desc_product.setText(String.format(getString(R.string.order_qty), c.getQte()));

            sub_layout.addView(desc_product);

            product_detail_layout.addView(sub_layout);

            itemWrapper.addView(product_detail_layout);
        }


    }

    private void generateCustomFields(View view) {
        LinearLayout itemWrapper = view.findViewById(R.id.inputs_fields_wrapper);
        itemWrapper.setOrientation(VERTICAL);
        itemWrapper.setPaddingRelative((int) getResources().getDimension(R.dimen.spacing_large), 0, 0, 0);
        LinearLayout.LayoutParams layout_336 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        itemWrapper.setLayoutParams(layout_336);


        if (orderFields != null) {

            for (Map.Entry<String, String> entry : orderFields.entrySet()) {


                LinearLayout itemLayoutView = new LinearLayout(mContext);
                itemLayoutView.setOrientation(HORIZONTAL);
                LinearLayout.LayoutParams layout_379 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layout_379.setMargins(Utils.dpToPx(5), Utils.dpToPx(3), Utils.dpToPx(3), Utils.dpToPx(3));
                itemLayoutView.setLayoutParams(layout_379);

                TextView titleField = new TextView(mContext);
                titleField.setText(entry.getKey());
                titleField.setTypeface(titleField.getTypeface(), Typeface.BOLD);
                titleField.setTextColor(getResources().getColor(R.color.black));
                titleField.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                LinearLayout.LayoutParams layout_143 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layout_143.weight = 1;
                titleField.setLayoutParams(layout_143);
                itemLayoutView.addView(titleField);


                TextView valueField = new TextView(mContext);
                String value = entry.getValue().trim();

                //handle the case location : city ; lat ; lng
                if (value != null && value.split(";").length >= 2) {
                    value = value.split(";")[0];
                }


                valueField.setText(value);
                valueField.setTextColor(getResources().getColor(R.color.grey_60));
                valueField.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
                LinearLayout.LayoutParams layout_655 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layout_655.leftMargin = (int) getResources().getDimension(R.dimen.spacing_large);
                layout_655.rightMargin = (int) getResources().getDimension(R.dimen.spacing_large);
                valueField.setLayoutParams(layout_655);
                itemLayoutView.addView(valueField);


                itemWrapper.addView(itemLayoutView);


            }
        }


    }


}