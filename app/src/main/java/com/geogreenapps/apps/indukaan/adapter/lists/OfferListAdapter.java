package com.geogreenapps.apps.indukaan.adapter.lists;

import android.content.Context;
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
import com.geogreenapps.apps.indukaan.classes.Offer;
import com.geogreenapps.apps.indukaan.utils.ProductUtils;
import com.geogreenapps.apps.indukaan.utils.Utils;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class OfferListAdapter extends RecyclerView.Adapter<OfferListAdapter.mViewHolder> {


    private final LayoutInflater infalter;
    private final List<Offer> data;
    private final Context context;
    private ClickListener clickListener;
    private boolean isHorizontalList = false;
    private float width = 0, height = 0;
    private int lastPosition = -1;
    private boolean on_attach = true;

    public OfferListAdapter(Context context, List<Offer> data, boolean isHorizontalList) {
        this.data = data;
        this.infalter = LayoutInflater.from(context);
        this.context = context;
        this.isHorizontalList = isHorizontalList;
    }


    public OfferListAdapter(Context context, List<Offer> data, boolean isHorizontalList, float width, float height) {
        this.data = data;
        this.infalter = LayoutInflater.from(context);
        this.context = context;
        this.isHorizontalList = isHorizontalList;
        this.width = width;
        this.height = height;
    }

    @Override
    public OfferListAdapter.mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = null;
        if (isHorizontalList) rootView = infalter.inflate(R.layout.v2_item_offer, parent, false);
        else rootView = infalter.inflate(R.layout.fragment_offer_custom_item_v2, parent, false);

        mViewHolder holder = new mViewHolder(rootView);

        return holder;
    }

    @Override
    public void onBindViewHolder(OfferListAdapter.mViewHolder holder, int position) {

        setAnimation(holder.itemView, position);


        if (height > 0 && width > 0) {
            //set set the dp dimention
            int dp1 = Utils.dip2pix(context, 1);
            CardView.LayoutParams params = new CardView.LayoutParams((int) width, (int) height);
            params.setMargins((5 * dp1), (5 * dp1), (5 * dp1), (5 * dp1));
            holder.itemView.setLayoutParams(params);
        }


        //if (data.get(position).getCurrency() != null) {

        if (data.get(position).getProduct_type() != null
                && !data.get(position).getProduct_type().equals("")) {

            if (data.get(position).getProduct_type().equalsIgnoreCase("Percent")
                    && (data.get(position).getProduct_value() > 0 || data.get(position).getProduct_value() < 0)) {

                DecimalFormat decimalFormat = new DecimalFormat("#0");
                holder.product_id.setText(
                        decimalFormat.format(data.get(position).getProduct_value()) + "%"
                );

            } else {

                if (data.get(position).getProduct_type().equalsIgnoreCase("Price")
                        && data.get(position).getProduct_value() != 0) {

                    holder.product_id.setText(ProductUtils.parseCurrencyFormat(
                            data.get(position).getProduct_value(),
                            data.get(position).getCurrency()));

                } else {
                    holder.product_id.setText(context.getString(R.string.promo));
                }
            }
        }

        if (data.get(position).getProduct_type().equalsIgnoreCase("unspecifie")) {
            holder.product_id.setText(context.getString(R.string.promo));

        }


        holder.name.setText(data.get(position).getName());
        holder.description.setText(data.get(position).getStore_name());

        Drawable locationDrawable = new IconicsDrawable(context)
                .icon(CommunityMaterial.Icon.cmd_map_marker)
                .color(ResourcesCompat.getColor(context.getResources(), R.color.black, null))
                .sizeDp(12);

        if (isHorizontalList) {
            holder.description.setCompoundDrawables(null, null, null, null);
        } else {
            if (!AppController.isRTL())
                holder.description.setCompoundDrawables(locationDrawable, null, null, null);
            else
                holder.description.setCompoundDrawables(null, null, locationDrawable, null);

        }

        holder.description.setCompoundDrawablePadding(14);

        if (data.get(position).getImages() != null) {
            Glide.with(context).load(data.get(position).getImages().getUrl100_100())
                    .placeholder(ImageLoaderAnimation.glideLoader(context))
                    .centerCrop().into(holder.image);
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

    public Offer getItem(int position) {

        try {
            return data.get(position);
        } catch (Exception e) {
            return null;
        }

    }

    public void addItem(Offer item) {
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

    private String prepareCountDownView(Offer mOffer) {


        String result = "";

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date event_date = dateFormat.parse(mOffer.getDate_end());
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

        public TextView description;
        public TextView product_id;
        public ImageView featured;


        public mViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);

            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.address);
            product_id = itemView.findViewById(R.id.product);
            featured = itemView.findViewById(R.id.featured);

            itemView.setOnClickListener(this);


        }


        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.itemClicked(v, getPosition());
            }
        }
    }


}
