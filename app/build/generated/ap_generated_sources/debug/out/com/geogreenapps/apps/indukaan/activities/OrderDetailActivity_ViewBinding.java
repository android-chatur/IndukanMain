// Generated code from Butter Knife. Do not modify!
package com.geogreenapps.apps.indukaan.activities;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.AppCompatButton;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.geogreenapps.apps.indukaan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OrderDetailActivity_ViewBinding implements Unbinder {
  private OrderDetailActivity target;

  private View view7f0a0168;

  @UiThread
  public OrderDetailActivity_ViewBinding(OrderDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OrderDetailActivity_ViewBinding(final OrderDetailActivity target, View source) {
    this.target = target;

    View view;
    target.toolbarTitle = Utils.findRequiredViewAsType(source, R.id.toolbar_title, "field 'toolbarTitle'", TextView.class);
    target.toolbarDescription = Utils.findRequiredViewAsType(source, R.id.toolbar_description, "field 'toolbarDescription'", TextView.class);
    target.order_id = Utils.findRequiredViewAsType(source, R.id.order_id, "field 'order_id'", TextView.class);
    target.payment_method = Utils.findRequiredViewAsType(source, R.id.payment_method, "field 'payment_method'", TextView.class);
    target.delivery_on = Utils.findRequiredViewAsType(source, R.id.delivery_on, "field 'delivery_on'", TextView.class);
    target.total_price = Utils.findRequiredViewAsType(source, R.id.total_price, "field 'total_price'", TextView.class);
    target.order_status = Utils.findRequiredViewAsType(source, R.id.order_status, "field 'order_status'", TextView.class);
    target.item_wrapper = Utils.findRequiredViewAsType(source, R.id.item_detail, "field 'item_wrapper'", LinearLayout.class);
    target.order_tracking = Utils.findRequiredViewAsType(source, R.id.order_tracking, "field 'order_tracking'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.btn_track_order, "field 'btn_track_order' and method 'updateStatusClick'");
    target.btn_track_order = Utils.castView(view, R.id.btn_track_order, "field 'btn_track_order'", AppCompatButton.class);
    view7f0a0168 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.updateStatusClick(p0);
      }
    });
    target.item_wrapper_fees = Utils.findRequiredViewAsType(source, R.id.item_wrapper_fees, "field 'item_wrapper_fees'", LinearLayout.class);
    target.tax_layout = Utils.findRequiredViewAsType(source, R.id.tax_layout, "field 'tax_layout'", LinearLayout.class);
    target.tax_value = Utils.findRequiredViewAsType(source, R.id.tax_value, "field 'tax_value'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OrderDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbarTitle = null;
    target.toolbarDescription = null;
    target.order_id = null;
    target.payment_method = null;
    target.delivery_on = null;
    target.total_price = null;
    target.order_status = null;
    target.item_wrapper = null;
    target.order_tracking = null;
    target.btn_track_order = null;
    target.item_wrapper_fees = null;
    target.tax_layout = null;
    target.tax_value = null;

    view7f0a0168.setOnClickListener(null);
    view7f0a0168 = null;
  }
}
