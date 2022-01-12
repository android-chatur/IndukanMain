package com.geogreenapps.apps.indukaan.adapter.lists;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.activities.ProductDetailActivity;
import com.geogreenapps.apps.indukaan.classes.Currency;
import com.geogreenapps.apps.indukaan.classes.Item;
import com.geogreenapps.apps.indukaan.utils.ProductUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Item> items = new ArrayList<>();
    private final Context ctx;
    private ClickListener mClickListener;

    public ItemsAdapter(Context context, List<Item> items) {
        this.items = items;
        ctx = context;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setClickListener(final ClickListener mItemClickListener) {
        this.mClickListener = mItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_product_item, parent, false);
        vh = new ItemViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder view = (ItemViewHolder) holder;


            final Item product = items.get(position);
            view.title_product.setText(product.getName());
            view.desc_product.setText(
                    String.format(ctx.getString(R.string.product_qty), items.get(position).getQty()));

            if (product.getAmount() > 0) {
                //calculate  the amount based on qty
                double amountOrder = product.getAmount() * (product.getQty() > 0 ? product.getQty() : 1);
                view.price_product.setText(ProductUtils.parseCurrencyFormat(
                        (float) amountOrder,
                        product.getCurrency()));
            } else {
                view.price_product.setVisibility(View.GONE);
            }


            if (product.getImage() != null && !product.getImage().equals("")) {
                Glide.with(ctx)
                        .load(product.getImage())
                        .centerCrop().placeholder(R.drawable.def_logo)
                        .into(view.image_product);
            } else {
                Glide.with(ctx).load(R.drawable.def_logo)
                        .centerCrop().into(view.image_product);
            }


            view.product_detail_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentProduct = new Intent(ctx, ProductDetailActivity.class);

                    String[] list = product.getId().split("_");
                    if (list.length == 0) return;

                    intentProduct.putExtra("id", Integer.valueOf(list[1]));
                    Objects.requireNonNull(ctx).startActivity(intentProduct);
                }
            });


        }
    }

    public void removeAll() {

        int size = this.items.size();

        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.items.remove(0);
            }

            if (size > 0)
                this.notifyItemRangeRemoved(0, size);
        }
    }

    public float getTotalPrice() {
        float total_price = 0;
        if (items != null && items.size() > 0) {
            for (Item product : items) {
                total_price = (float) (total_price + (product.getAmount() * product.getQty()));
            }
        }
        return total_price;
    }

    public Currency getCurrency() {
        if (items != null && items.size() > 0) {
            return items.get(0).getCurrency();
        }
        return null;
    }

    public void addItem(Item item) {

        int index = (items.size());
        items.add(item);
        notifyItemInserted(index);
    }

    public void addAll(final List<Item> productList) {
        int size = productList.size();

        items.clear();
        if (size > 0) {
            //remove all items before adding new items
            for (int i = 0; i < size; i++) {
                items.add(productList.get(i));
            }

            notifyDataSetChanged();
        }


    }

    @Override
    public int getItemCount() {
        if (items == null) return 0;
        return items.size();
    }


    public interface ClickListener {
        void onItemClick(View view, int pos);

    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        LinearLayout product_detail_layout;
        ImageView image_product;
        TextView title_product;
        TextView desc_product;
        TextView price_product;


        public ItemViewHolder(View v) {
            super(v);

            product_detail_layout = v.findViewById(R.id.product_detail_layout);
            image_product = v.findViewById(R.id.image_product);
            title_product = v.findViewById(R.id.title_product);
            desc_product = v.findViewById(R.id.desc_product);
            price_product = v.findViewById(R.id.price_product);

        }

    }


}