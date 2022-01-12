// Generated code from Butter Knife. Do not modify!
package com.geogreenapps.apps.indukaan.fragments.orderFrags;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geogreenapps.apps.indukaan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PaymentFragment_ViewBinding implements Unbinder {
  private PaymentFragment target;

  @UiThread
  public PaymentFragment_ViewBinding(PaymentFragment target, View source) {
    this.target = target;

    target.mScroll = Utils.findRequiredViewAsType(source, R.id.mScroll, "field 'mScroll'", ScrollView.class);
    target.listPayment = Utils.findRequiredViewAsType(source, R.id.list_payment, "field 'listPayment'", RecyclerView.class);
    target.paymentDetailLayout = Utils.findRequiredViewAsType(source, R.id.payment_detail_layout, "field 'paymentDetailLayout'", LinearLayout.class);
    target.layoutFees = Utils.findRequiredViewAsType(source, R.id.layout_fees, "field 'layoutFees'", LinearLayout.class);
    target.layoutSubtotal = Utils.findRequiredViewAsType(source, R.id.layout_subtotal, "field 'layoutSubtotal'", LinearLayout.class);
    target.subtotalVal = Utils.findRequiredViewAsType(source, R.id.subtotal_val, "field 'subtotalVal'", TextView.class);
    target.layoutTotal = Utils.findRequiredViewAsType(source, R.id.layout_total, "field 'layoutTotal'", LinearLayout.class);
    target.totalValue = Utils.findRequiredViewAsType(source, R.id.total_value, "field 'totalValue'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PaymentFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mScroll = null;
    target.listPayment = null;
    target.paymentDetailLayout = null;
    target.layoutFees = null;
    target.layoutSubtotal = null;
    target.subtotalVal = null;
    target.layoutTotal = null;
    target.totalValue = null;
  }
}
