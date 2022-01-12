package com.geogreenapps.apps.indukaan.adapter.order;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.activities.OrderCheckoutActivity;
import com.geogreenapps.apps.indukaan.adapter.lists.ItemsAdapter;
import com.geogreenapps.apps.indukaan.animation.ViewAnimation;
import com.geogreenapps.apps.indukaan.classes.Order;
import com.geogreenapps.apps.indukaan.helper.CommunFunctions;
import com.geogreenapps.apps.indukaan.utils.ProductUtils;
import com.geogreenapps.apps.indukaan.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

public class OrdersListExpandAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Order> data = new ArrayList<>();

    private final Context ctx;
    private OnItemClickListener mOnItemClickListener;

    public OrdersListExpandAdapter(Context context, List<Order> data) {
        this.data = data;
        ctx = context;
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_expand, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {


        if (holder instanceof OriginalViewHolder) {
            final OriginalViewHolder view = (OriginalViewHolder) holder;

            float extraFees = 0;

            final Order mOrder = data.get(position);
            view.orderID.setText(
                    String.format(ctx.getString(R.string.statusID), mOrder.getId())
            );

            //set status with color
            String[] arrayStatus = mOrder.getStatus().split(";");
            if (arrayStatus.length > 0) {
                view.status.setText(arrayStatus[0].substring(0, 1).toUpperCase() + arrayStatus[0].substring(1));
                if (arrayStatus[1] != null && !arrayStatus[0].equals("null")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        view.status.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(arrayStatus[1])));
                    }
                }
            }


            //set status with color
            String[] arrayPayStatus = mOrder.getPayment_status().split(";");
            if (arrayPayStatus.length > 0) {

                view.order_payment_status.setText(arrayPayStatus[0].substring(0, 1).toUpperCase() + arrayPayStatus[0].substring(1));

                if (arrayPayStatus[1] != null && !arrayPayStatus[0].equals("null")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        view.order_payment_status.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(arrayPayStatus[1])));
                    }
                }

                //showup a pay now button when the order is not paid yet
                if (arrayPayStatus[0].equals("unpaid")) {
                    view.btn_pay_now.setVisibility(View.VISIBLE);

                    view.btn_order_detail.setVisibility(View.GONE);

                    view.btn_pay_now.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Send The Message Receiver ID & Name
                            Intent intent = new Intent(v.getContext(), OrderCheckoutActivity.class);
                            intent.putExtra("fragmentToOpen", "fragment_payment");
                            intent.putExtra("order_id", mOrder.getId());
                            if (mOrder.getCart() != null && !mOrder.getCart().equals("null")) {
                                try {
                                    JSONObject cartArray = new JSONObject(mOrder.getCart());
                                    if (cartArray.length() > 0) {
                                        JSONObject cartObj = cartArray.getJSONObject("0");
                                        if (cartObj == null) return;

                                        intent.putExtra("module_id", cartObj.getInt("module_id"));
                                        intent.putExtra("module", cartObj.getString("module"));
                                        intent.putExtra("cart", mOrder.getCart());
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            v.getContext().startActivity(intent);
                        }
                    });
                } else {
                    view.btn_pay_now.setVisibility(View.GONE);
                    view.btn_order_detail.setVisibility(View.VISIBLE);
                }
            }


            //setup product items in recyclerview
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(ctx, 1);
            ItemsAdapter mProductAdapter = new ItemsAdapter(ctx, mOrder.getItems());
            view.list_items.setHasFixedSize(true);
            view.list_items.setLayoutManager(mLayoutManager);
            view.list_items.setItemAnimator(new DefaultItemAnimator());
            view.list_items.setAdapter(mProductAdapter);


            //display total price
            if (mOrder.getAmount() > 0) {

                //format 2 digit
                double amount = mOrder.getAmount();
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                amount = Float.parseFloat(decimalFormat.format(amount));

                view.total_price_layout.setVisibility(View.VISIBLE);
                view.total_price_items.setText(ProductUtils.parseCurrencyFormat((float) amount, mProductAdapter.getCurrency()));
            } else if (mProductAdapter.getTotalPrice() > 0) {

                //format 2 digit
                float totalPrice = mProductAdapter.getTotalPrice();
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                totalPrice = Float.parseFloat(decimalFormat.format(totalPrice));

                view.total_price_layout.setVisibility(View.VISIBLE);
                view.total_price_items.setText(ProductUtils.parseCurrencyFormat(totalPrice, mProductAdapter.getCurrency()));
            } else {
                view.total_price_layout.setVisibility(View.GONE);
            }

            if (mOrder.getExtras() != null && !mOrder.getExtras().equals("null")) {
                try {
                    extraFees = CommunFunctions.parseExtraFees(view.item_wrapper_fees, mOrder.getExtras(), mProductAdapter.getCurrency());
                    view.item_wrapper_fees.setVisibility(View.VISIBLE);

                } catch (JSONException e) {
                    e.printStackTrace();
                    view.item_wrapper_fees.setVisibility(View.GONE);

                }

            } else {
                view.item_wrapper_fees.setVisibility(View.GONE);
            }

            //display tax if exist
            if (mProductAdapter.getTotalPrice() > 0 && ((mOrder.getAmount() - mProductAdapter.getTotalPrice()) >= 1)) {
                view.tax_layout.setVisibility(View.VISIBLE);

                float taxes = (float) (mOrder.getAmount() - mProductAdapter.getTotalPrice() - extraFees);
                if (taxes <= 0) view.tax_layout.setVisibility(View.GONE);

                view.tax_value.setText(
                        ProductUtils.parseCurrencyFormat((float) (mOrder.getAmount() - mProductAdapter.getTotalPrice() - extraFees), mProductAdapter.getCurrency()));
            } else {
                view.tax_layout.setVisibility(View.GONE);
            }

            //hide pay now button if the items doesn't have prices
            if (mProductAdapter.getTotalPrice() == 0) {
                view.btn_pay_now.setVisibility(View.GONE);
            }


            view.bt_expand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean show = toggleLayoutExpand(!mOrder.expanded, v, view.lyt_expand);
                    data.get(position).expanded = show;
                }
            });

            view.itemOrderlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean show = toggleLayoutExpand(!mOrder.expanded, view.bt_expand, view.lyt_expand);
                    data.get(position).expanded = show;
                }
            });


            // void recycling view
            if (mOrder.expanded) {
                view.lyt_expand.setVisibility(View.VISIBLE);
            } else {
                view.lyt_expand.setVisibility(View.GONE);
            }
            Utils.toggleArrow(mOrder.expanded, view.bt_expand, false);


        }
    }


    public Order getItem(int position) {

        try {
            return data.get(position);
        } catch (Exception e) {
            return null;
        }

    }

    public void addItem(Order item) {

        int index = (data.size());
        data.add(item);
        notifyItemInserted(index);

    }

    public void addAllItems(RealmList<Order> listCats) {

        data.addAll(listCats);
        notifyDataSetChanged();

       /* selectedItems = new int[data.size()];
        initializeSeledtedItems();*/
    }

    public void removeAll() {
        int size = this.data.size();

        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.data.remove(0);
            }

            if (size > 0)
                this.notifyItemRangeRemoved(0, size);

        }


    }

    private boolean toggleLayoutExpand(boolean show, View view, View lyt_expand) {
        Utils.toggleArrow(show, view);
        if (show) {
            ViewAnimation.expand(lyt_expand);
        } else {
            ViewAnimation.collapse(lyt_expand);
        }
        return show;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onOrderDetailClick(int position);

        void onOrderTrackClick(int position);

    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        View itemOrderlayout;
        TextView orderID;
        TextView status;
        TextView order_payment_status;
        TextView tax_value;
        ImageButton bt_expand;
        View lyt_expand;
        View lyt_parent;
        RecyclerView list_items;
        TextView total_price_items;
        LinearLayout total_price_layout;
        LinearLayout tax_layout;
        LinearLayout item_wrapper_fees;
        AppCompatButton btn_pay_now;
        AppCompatButton btn_track_order;
        AppCompatButton btn_order_detail;

        public OriginalViewHolder(View v) {
            super(v);
            itemOrderlayout = v.findViewById(R.id.itemOrderlayout);
            orderID = v.findViewById(R.id.order_id);
            status = v.findViewById(R.id.order_status);
            order_payment_status = v.findViewById(R.id.order_payment_status);
            bt_expand = v.findViewById(R.id.bt_expand);
            lyt_expand = v.findViewById(R.id.lyt_expand);
            lyt_parent = v.findViewById(R.id.lyt_parent);
            list_items = v.findViewById(R.id.list_items);
            total_price_items = v.findViewById(R.id.total_price_items);
            total_price_layout = v.findViewById(R.id.total_price_layout);
            item_wrapper_fees = v.findViewById(R.id.item_wrapper_fees);
            tax_layout = v.findViewById(R.id.tax_layout);
            tax_value = v.findViewById(R.id.tax_value);
            btn_pay_now = v.findViewById(R.id.btn_pay_now);
            btn_order_detail = v.findViewById(R.id.btn_order_detail);
            btn_track_order = v.findViewById(R.id.btn_track_order);


            //click listener
            itemOrderlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(getAdapterPosition());
                    }
                }
            });

            btn_order_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onOrderDetailClick(getAdapterPosition());
                    }
                }
            });

            btn_track_order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onOrderTrackClick(getAdapterPosition());
                    }
                }
            });

        }
    }


}