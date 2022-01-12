package com.geogreenapps.apps.indukaan.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.activities.ProductCartActivity;
import com.geogreenapps.apps.indukaan.animation.ImageLoaderAnimation;
import com.geogreenapps.apps.indukaan.appconfig.Constances;
import com.geogreenapps.apps.indukaan.classes.Cart;
import com.geogreenapps.apps.indukaan.classes.Currency;
import com.geogreenapps.apps.indukaan.classes.Product;
import com.geogreenapps.apps.indukaan.controllers.cart.CartController;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.DecimalFormat;

/**
 * Created by Droideve on 1/26/2018.
 */

public class ProductUtils {


    public static String parseCurrencyFormat(float price, Currency cData) {


        if (cData != null) {
            ///fix the number of digit after separator CFD
            //DecimalFormat decim = new DecimalFormat("##.##");
            String ps = String.format("%." + cData.getCfd() + "f", price);
            //separator CDP
            ps =  ps.replace(".",cData.getCdp());


            //parse format
            switch (cData.getFormat()) {
                case 1:
                    return cData.getSymbol() + ps;
                case 2:
                    return ps + cData.getSymbol();
                case 3:
                    return cData.getSymbol() + " " + ps;
                case 4:
                    return ps + " " + cData.getSymbol();
                case 5:
                    return ps;
                case 6:
                    return cData.getSymbol() + ps + " " + cData.getCode();
                case 7:
                    return cData.getSymbol() + ps;
                case 8:
                    return ps + cData.getCode();
            }


        }


        return String.valueOf(price);


    }


    public static void parseProductValue(Context context, TextView priceView, Product mProduct, float custom_price) {
        if (mProduct.getProduct_type().equalsIgnoreCase("Percent") && (mProduct.getProduct_value() > 0 || mProduct.getProduct_value() < 0)) {
            DecimalFormat decimalFormat = new DecimalFormat("#0");

            priceView.setText(
                    String.format(context.getString(R.string.product_off),
                            decimalFormat.format(mProduct.getProduct_value()) + "%"
                    )
            );

            priceView.setVisibility(View.VISIBLE);


        } else {

            if (mProduct.getProduct_type().equalsIgnoreCase("Price") && mProduct.getProduct_value() != 0) {

                priceView.setText(ProductUtils.parseCurrencyFormat(
                        custom_price > 0 ? custom_price : mProduct.getProduct_value(),
                        mProduct.getCurrency()));

                priceView.setVisibility(View.VISIBLE);

            } else {
                priceView.setVisibility(View.VISIBLE);
                priceView.setText(context.getString(R.string.promo));
            }
        }
    }


    public static void showBottomSheetDialog(View mView, Activity activity, Cart mcart) {


        //action qte buttons
        final float[] finalOriginal_price = {-1};
        final float[] finalCustom_price = {-1};
        final int[] finalCustomQte = {1};

        FrameLayout bottom_sheet = null;
        if (mView != null)
            bottom_sheet = mView.findViewById(R.id.bottom_sheet);
        else
            activity.findViewById(R.id.bottom_sheet);

        bottom_sheet.setVisibility(View.VISIBLE);

        final BottomSheetBehavior[] mBehavior = {BottomSheetBehavior.from(bottom_sheet)};

        final BottomSheetDialog[] mBottomSheetDialog = {new BottomSheetDialog(activity)};


        if (mBehavior[0].getState() == BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior[0].setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

        final View[] view = {activity.getLayoutInflater().inflate(R.layout.order_quantity_sheet, null)};

        if (mcart.getProduct() != null) {

            //set default  values
            if (mcart.getAmount() > 0) {
                finalCustom_price[0] = finalOriginal_price[0] = (float) mcart.getAmount();
            } else if (mcart.getProduct().getProduct_type().equalsIgnoreCase("Price") && mcart.getProduct().getProduct_value() != 0) {
                finalCustom_price[0] = finalOriginal_price[0] = mcart.getProduct().getProduct_value();
            }
            finalCustomQte[0] = 1;

            //set product_id image
            if (mcart.getProduct().getImages() != null) {
                Glide.with(activity)
                        .load(mcart.getProduct().getImages().getUrl200_200())
                        .centerCrop()
                        .placeholder(ImageLoaderAnimation.glideLoader(activity))

                        .centerCrop().into(((ImageView) view[0].findViewById(R.id.image_product)));
                //set product_id name
                ((TextView) view[0].findViewById(R.id.product_name)).setText(mcart.getProduct().getName());

                //set product_id price
                ProductUtils.parseProductValue(activity, view[0].findViewById(R.id.product_price), mcart.getProduct(), (float) mcart.getAmount());


                (view[0].findViewById(R.id.btn_less_qte)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (finalCustomQte[0] <= 1) return;
                        finalCustomQte[0]--;
                        if (finalOriginal_price[0] < finalCustom_price[0]) {
                            finalCustom_price[0] = finalCustom_price[0] - finalOriginal_price[0];
                        }
                        //set custom quantity
                        ((TextView) view[0].findViewById(R.id.product_quantity)).setText(finalCustomQte[0] + "");

                        //set custom price
                        ProductUtils.parseProductValue(activity, view[0].findViewById(R.id.product_price), mcart.getProduct(), finalCustom_price[0]);


                    }
                });

                (view[0].findViewById(R.id.btn_more_qte)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finalCustomQte[0]++;
                        finalCustom_price[0] = finalCustom_price[0] + finalOriginal_price[0];

                        //set custom quantity
                        ((TextView) view[0].findViewById(R.id.product_quantity)).setText(finalCustomQte[0] + "");

                        //set custom price
                        ProductUtils.parseProductValue(activity, view[0].findViewById(R.id.product_price), mcart.getProduct(), finalCustom_price[0]);
                    }
                });


            }


            (view[0].findViewById(R.id.bt_confirm)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mcart.setQte(finalCustomQte[0]);

                    //save cart into database
                    CartController.addProductToCard(mcart);


                    Intent intent = new Intent(new Intent(activity, ProductCartActivity.class));

                    //send array as serializable field
                    intent.putExtra("module_id", mcart.getProduct().getId());
                    intent.putExtra("module", Constances.ModulesConfig.PRODUCT_MODULE);

                    //save cart in the database

                    activity.startActivity(intent);
                    activity.overridePendingTransition(R.anim.lefttoright_enter, R.anim.lefttoright_exit);
                    activity.finish();

                    mBottomSheetDialog[0].dismiss();

                }
            });

            (view[0].findViewById(R.id.btn_add_to_cart)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mcart.setQte(finalCustomQte[0]);

                    if (CartController.addProductToCard(mcart))
                        Toast.makeText(activity, "Successfully added to the cart", Toast.LENGTH_LONG).show();

                   /* Intent intent = new Intent(activity, ProductCartActivity.class);

                    activity.startActivity(intent);
                    activity.overridePendingTransition(R.anim.lefttoright_enter, R.anim.lefttoright_exit);*/


                    mBottomSheetDialog[0].dismiss();
                }
            });

            mBottomSheetDialog[0].setContentView(view[0]);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mBottomSheetDialog[0].getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }

            mBottomSheetDialog[0].show();
            mBottomSheetDialog[0].setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    mBottomSheetDialog[0] = null;
                }
            });
        }

    }

}
