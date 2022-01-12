package com.geogreenapps.apps.indukaan.adapter.lists;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.geogreenapps.apps.indukaan.AppController;
import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.animation.ImageLoaderAnimation;
import com.geogreenapps.apps.indukaan.animation.ItemAnimation;
import com.geogreenapps.apps.indukaan.classes.Product;
import com.geogreenapps.apps.indukaan.utils.ProductUtils;
import com.geogreenapps.apps.indukaan.utils.Utils;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.mViewHolder> {


    private final LayoutInflater infalter;
    private final List<Product> data;
    private final Context context;
    private ClickListener clickListener;
    private boolean isHorizontalList = false;
    private float width = 0, height = 0;
    private int lastPosition = -1;
    private boolean on_attach = true;

    public ProductListAdapter(Context context, List<Product> data, boolean isHorizontalList) {
        this.data = data;
        this.infalter = LayoutInflater.from(context);
        this.context = context;
        this.isHorizontalList = isHorizontalList;
    }


    public ProductListAdapter(Context context, List<Product> data, boolean isHorizontalList, float width, float height) {
        this.data = data;
        this.infalter = LayoutInflater.from(context);
        this.context = context;
        this.isHorizontalList = isHorizontalList;
        this.width = width;
        this.height = height;
    }

    @Override
    public ProductListAdapter.mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = null;
        if (isHorizontalList) rootView = infalter.inflate(R.layout.v2_item_product, parent, false);
        else rootView = infalter.inflate(R.layout.fragment_product_custom_item_v2, parent, false);

        mViewHolder holder = new mViewHolder(rootView);

        return holder;
    }

    @SuppressLint("StringFormatInvalid")
    @Override
    public void onBindViewHolder(ProductListAdapter.mViewHolder holder, int position) {

        setAnimation(holder.itemView, position);


      /*  if (height > 0 && width > 0) {
            //set set the dp dimention
            int dp1 = Utils.dip2pix(context, 1);
            CardView.LayoutParams params = new CardView.LayoutParams((int) width, (int) height);
            params.setMargins((5 * dp1), (5 * dp1), (5 * dp1), (5 * dp1));
            holder.itemView.setLayoutParams(params);
        }
*/


        if (data.get(position).getProduct_type() != null && !data.get(position).getProduct_type().equals("")) {
            if (data.get(position).getProduct_type().equalsIgnoreCase("Percent") && (data.get(position).getProduct_value() > 0 || data.get(position).getProduct_value() < 0)) {
                DecimalFormat decimalFormat = new DecimalFormat("#0");
                holder.price.setText(decimalFormat.format(data.get(position).getProduct_value()) + "%");
            } else {
                if (data.get(position).getProduct_type().equalsIgnoreCase("Price") && data.get(position).getProduct_value() != 0) {

                    holder.price.setText(ProductUtils.parseCurrencyFormat(
                            data.get(position).getProduct_value(),
                            data.get(position).getCurrency()));

                } else {
                    holder.price.setText(context.getString(R.string.promo));
                }
            }
        }

        if (data.get(position).getProduct_type().equalsIgnoreCase("unspecifie")) {
            holder.price.setText(context.getString(R.string.promo));

        }


        if (!isHorizontalList && data.get(position).getIs_deal() == 1) {
            String diff_end_product = prepareCountDownView(data.get(position));
            if (!diff_end_product.equals("")) {
                holder.deal_cd.setVisibility(View.VISIBLE);
            } else {
                holder.deal_cd.setVisibility(View.GONE);
            }

        } else {
            holder.deal_cd.setVisibility(View.GONE);
        }

        if (data.get(position).getOriginal_value() != 0) {

            double discounted_price = data.get(position).getProduct_value();
            double original_price = data.get(position).getOriginal_value();

            if (discounted_price > 0 && original_price > discounted_price) {
                //calculate percent
                int percent = (int) (((original_price - discounted_price) / original_price) * 100);

                holder.offer.setVisibility(View.VISIBLE);
                holder.offer.setText(
                        String.format(context.getString(R.string.discount_off),
                                percent + "%")
                );


            } else holder.offer.setVisibility(View.GONE);

            holder.old_price.setText(ProductUtils.parseCurrencyFormat((float) original_price, data.get(position).getCurrency()));
            holder.old_price.setPaintFlags(holder.old_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.old_price.setVisibility(View.VISIBLE);
        } else {
            holder.old_price.setVisibility(View.GONE);
            holder.offer.setVisibility(View.GONE);
        }


        holder.name.setText(data.get(position).getName());
        double asd=data.get(position).getDistance();
        holder.distance.setText(Utils.prepareDistanceKm(this.data.get(position).getDistance())
                + " " +
                Utils.getDistanceByKm(this.data.get(position).getDistance())
        );

        holder.address.setText(data.get(position).getStore_name());

        Drawable locationDrawable = new IconicsDrawable(context)
                .icon(CommunityMaterial.Icon.cmd_map_marker)
                .color(ResourcesCompat.getColor(context.getResources(), R.color.black, null))
                .sizeDp(12);

        if (isHorizontalList) {
            holder.address.setCompoundDrawables(null, null, null, null);
        } else {
            if (!AppController.isRTL())
                holder.address.setCompoundDrawables(locationDrawable, null, null, null);
            else
                holder.address.setCompoundDrawables(null, null, locationDrawable, null);

        }
        holder.address.setCompoundDrawablePadding(14);



        if (data.get(position).getImages() != null) {
            Glide.with(context).load(data.get(position).getImages().getUrl500_500())
                    .placeholder(ImageLoaderAnimation.glideLoader(context))
                    .fitCenter().into(holder.image);
        } else {

            Glide.with(context).load(R.drawable.def_logo)
                    .into(holder.image);
        }


        if (data.get(position).getFeatured() == 0) {
            holder.featured.setVisibility(View.GONE);
        } else {
            holder.featured.setVisibility(View.VISIBLE);
        }

    }

    public void removeAll() {
        data.clear();
    }

    public Product getItem(int position) {

        try {
            return data.get(position);
        } catch (Exception e) {
            return null;
        }

    }

    public void addItem(Product item) {
        data.add(item);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setClickListener(ClickListener clicklistener) {

        this.clickListener = clicklistener;

    }

    private String prepareCountDownView(Product mProduct) {


        String result = "";

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date event_date = dateFormat.parse(mProduct.getDate_end());
            Date current_date = new Date();
            if (!current_date.after(event_date)) {
                long diff = event_date.getTime() - current_date.getTime();
                long Days = diff / (24 * 60 * 60 * 1000);
                long Hours = diff / (60 * 60 * 1000) % 24;
                long Minutes = diff / (60 * 1000) % 60;
                long Seconds = diff / 1000 % 60;

                result = String.format("%02d", Days) + ":" +
                        String.format("%02d", Hours) + ":" +
                        String.format("%02d", Minutes) + ":" +
                        String.format("%02d", Seconds);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                on_attach = false;
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        super.onAttachedToRecyclerView(recyclerView);
    }

    private void setAnimation(View view, int position) {
        if (position > lastPosition) {
            ItemAnimation.animate(view, on_attach ? position : -1, ItemAnimation.FADE_IN);
            lastPosition = position;
        }

    }

    public interface ClickListener {
        void itemClicked(View view, int position);

    }

    public class mViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView image;
        public TextView name;
        public TextView address;
        public TextView offer;
        public TextView old_price;
        public ImageView deal_cd;
        public TextView price;
        public TextView distance;
        public ImageView featured;


        public mViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            deal_cd = itemView.findViewById(R.id.deals);
            price = itemView.findViewById(R.id.price);
            distance = itemView.findViewById(R.id.distance);
            featured = itemView.findViewById(R.id.featured);
            old_price = itemView.findViewById(R.id.old_price);
            offer = itemView.findViewById(R.id.offer);

            itemView.setOnClickListener(this);


        }


        @Override
        public void onClick(View v) {


            if (clickListener != null) {
                clickListener.itemClicked(v, getPosition());
            }

            //delete(getPosition());


        }
    }


}
