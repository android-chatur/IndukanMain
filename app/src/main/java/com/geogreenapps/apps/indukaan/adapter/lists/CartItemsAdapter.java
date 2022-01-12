package com.geogreenapps.apps.indukaan.adapter.lists;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.activities.ProductDetailActivity;
import com.geogreenapps.apps.indukaan.appconfig.Constances;
import com.geogreenapps.apps.indukaan.classes.Cart;
import com.geogreenapps.apps.indukaan.classes.Currency;
import com.geogreenapps.apps.indukaan.classes.Option;
import com.geogreenapps.apps.indukaan.classes.Product;
import com.geogreenapps.apps.indukaan.classes.Variant;
import com.geogreenapps.apps.indukaan.controllers.cart.CartController;
import com.geogreenapps.apps.indukaan.utils.ProductUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CartItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Cart> items = new ArrayList<>();
    private final Context ctx;
    private ClickListener mClickListener;

    public CartItemsAdapter(Context context, List<Cart> items) {
        this.items = items;
        ctx = context;
    }

    public List<Cart> getItems() {
        return items;
    }

    public void setItems(List<Cart> items) {
        this.items = items;
    }

    public void setClickListener(final ClickListener mItemClickListener) {
        this.mClickListener = mItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_product_item, parent, false);
        vh = new ItemViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder view = (ItemViewHolder) holder;


            final Cart cart = items.get(position);

            if (cart.getProduct() != null) {
                Product currentProduct = cart.getProduct();

                view.title_product.setText(currentProduct.getName());
                view.product_qte.setText(String.format(ctx.getString(R.string.product_qty), cart.getQte()));

                //parse the amount
                if (cart.getAmount() > 0) {
                    //calculate  the amount based on qty
                    double amountOrder = cart.getAmount() * (cart.getQte() > 0 ? cart.getQte() : 1);
                    view.price_product.setText(ProductUtils.parseCurrencyFormat(
                            (float) amountOrder,
                            currentProduct.getCurrency()));
                } else {
                    view.price_product.setVisibility(View.GONE);
                }

                //parse the images
                if (currentProduct.getImages() != null && !currentProduct.getImages().equals("")) {
                    Glide.with(ctx)
                            .load(currentProduct.getImages().getUrl200_200())
                            .centerCrop().placeholder(R.drawable.def_logo)
                            .into(view.image_product);
                } else {
                    Glide.with(ctx).load(R.drawable.def_logo)
                            .centerCrop().into(view.image_product);
                }

                //parse variant
                if (cart.getVariants() != null && cart.getVariants().size() > 0) {
                    String formattedVariant = "";
                    List<Variant> variants = cart.getVariants();
                    for (Variant var : variants) {
                        formattedVariant += "-" + var.getGroup_label() + "\n";
                        if (var.getOptions() != null && var.getOptions().size() > 0) {
                            List<Option> options = var.getOptions();
                            for (Option opt : options) {
                                formattedVariant += "\t" + opt.getLabel();
                                if (opt.getValue() > 0) formattedVariant += "\t " + opt.getValue();
                                formattedVariant += "\n";
                            }
                        }
                    }

                    view.product_variants.setText(formattedVariant);
                    view.product_variants.setVisibility(View.VISIBLE);

                } else {
                    view.product_variants.setVisibility(View.GONE);
                }

            }


            view.product_detail_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cart.getModule().equals(Constances.ModulesConfig.PRODUCT_MODULE)) {
                        Intent intentProduct = new Intent(ctx, ProductDetailActivity.class);
                        intentProduct.putExtra("id", cart.getModule_id());
                        Objects.requireNonNull(ctx).startActivity(intentProduct);
                    } else {
                        Toast.makeText(ctx, "product not found", Toast.LENGTH_LONG).show();
                    }

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


    public boolean removeItem(final int position) {

        if (items.get(position) != null) {
            //remove the item from database
            CartController.removeItem(items.get(position).getId());

            //remove item from adapter
            items.remove(position);

            notifyItemRemoved(position);
            notifyItemRangeChanged(position, items.size());

            return true;

        }
        return false;


    }


    public double getTotalPrice() {
        double total_price = 0;
        if (items != null && items.size() > 0) {
            for (Cart cart : items) {
                total_price = total_price + (cart.getAmount() * cart.getQte());
            }
        }
        //return Math.round(total_price);
        return total_price;
    }

    public Currency getCurrency() {
        if (items != null && items.size() > 0) {
            if (items.get(0).getProduct() == null) return null;
            return items.get(0).getProduct().getCurrency();
        }
        return null;
    }

    public void addItem(Cart item) {

        int index = (items.size());
        items.add(item);
        notifyItemInserted(index);
    }

    public void addAll(final List<Cart> productList) {
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
        void onDeleteItemClick(View view, int pos);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        LinearLayout product_detail_layout;
        ImageView image_product;
        TextView title_product;
        TextView product_qte;
        TextView product_variants;
        TextView price_product;
        ImageView delete_item;


        public ItemViewHolder(View v) {
            super(v);

            product_detail_layout = v.findViewById(R.id.product_detail_layout);
            image_product = v.findViewById(R.id.image_product);
            title_product = v.findViewById(R.id.title_product);
            product_qte = v.findViewById(R.id.product_qte);
            product_variants = v.findViewById(R.id.product_variants);
            price_product = v.findViewById(R.id.price_product);
            delete_item = v.findViewById(R.id.delete_item);


            delete_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mClickListener != null) {
                        mClickListener.onDeleteItemClick(view, getPosition());
                    }

                }
            });


        }


    }


}