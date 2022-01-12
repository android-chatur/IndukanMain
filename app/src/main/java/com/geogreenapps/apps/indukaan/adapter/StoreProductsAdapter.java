package com.geogreenapps.apps.indukaan.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.activities.ProductsActivity;
import com.geogreenapps.apps.indukaan.animation.ImageLoaderAnimation;
import com.geogreenapps.apps.indukaan.appconfig.AppConfig;
import com.geogreenapps.apps.indukaan.classes.Product;
import com.geogreenapps.apps.indukaan.utils.ProductUtils;

import java.text.DecimalFormat;
import java.util.List;


public class StoreProductsAdapter {

    private final Context context;
    private List<Product> list;
    private int resLayout;
    private LayoutInflater inflater;
    private Listener mListener;

    public StoreProductsAdapter(Context context) {
        this.context = context;
        try {
            inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        } catch (Exception e) {
        }

    }

    public static StoreProductsAdapter newInstance(Context context) {
        return new StoreProductsAdapter(context);
    }

    public StoreProductsAdapter load(List<Product> list) {
        this.list = list;
        return this;
    }

    public StoreProductsAdapter inflate(int resLayout) {

        this.resLayout = resLayout;

        return this;
    }

    public StoreProductsAdapter into(LinearLayout rootView) {
        loop(rootView);
        return this;
    }

    private View prepareView(View layout, Product product_id) {

        ImageView image = layout.findViewById(R.id.image);
        TextView title = layout.findViewById(R.id.name);
        TextView detail = layout.findViewById(R.id.detail);
        TextView price = layout.findViewById(R.id.price);

        title.setText(product_id.getName());
        detail.setText(Html.fromHtml(product_id.getShort_description()));


        if (product_id.getProduct_type() != null && !product_id.getProduct_type().equals("")) {
            if (product_id.getProduct_type().equalsIgnoreCase("Percent") && (product_id.getProduct_value() > 0 || product_id.getProduct_value() < 0)) {
                DecimalFormat decimalFormat = new DecimalFormat("#0");
                price.setText(decimalFormat.format(product_id.getProduct_value()) + "%");
            } else {
                if (product_id.getProduct_type().equalsIgnoreCase("Price") && product_id.getProduct_value() != 0) {

                    price.setText(ProductUtils.parseCurrencyFormat(
                            product_id.getProduct_value(),
                            product_id.getCurrency()));
                } else {
                    price.setText(context.getString(R.string.promo));
                }
            }
        }

        if (product_id.getProduct_type().equalsIgnoreCase("unspecifie"))
            price.setText(context.getString(R.string.promo));



        try {

            Glide.with(context)
                    .load(product_id.getImages()
                            .getUrl200_200())
                    .placeholder(ImageLoaderAnimation.glideLoader(context))
                    .centerCrop()
                    .into(image);

        } catch (Exception e) {

        }


        return layout;
    }

    public void loop(LinearLayout rootView) {

        rootView.removeAllViews();
        if (inflater != null) {

            for (int i = 0; i < list.size(); i++) {

                View layout = inflater.inflate(resLayout, null);


                final int finalI = i;
                layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null)
                            mListener
                                    .onProductClicked(finalI);
                    }
                });

                rootView.addView(prepareView(layout, list.get(i)));

                if (i == 5)
                    break;
            }
        }

        if (list.size() >= 7) {

            try {

                View layout = inflater.inflate(R.layout.item_store_review_load_more, null);
                Button button = layout.findViewById(R.id.loadMore);
                button.setPaintFlags(button.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(context, ProductsActivity.class);
                        intent.putExtra("store_id", list.get(0).getStore_id());
                        context.startActivity(intent);

                    }
                });

                rootView.addView(layout);

            } catch (Exception e) {
                if (AppConfig.APP_DEBUG)
                    e.printStackTrace();
            }


        }

    }

    public void setOnistener(Listener l) {
        mListener = l;
    }

    public interface Listener {
        void onProductClicked(int position);
    }
}
