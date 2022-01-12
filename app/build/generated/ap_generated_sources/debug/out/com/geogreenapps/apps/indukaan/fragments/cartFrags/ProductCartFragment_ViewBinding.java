// Generated code from Butter Knife. Do not modify!
package com.geogreenapps.apps.indukaan.fragments.cartFrags;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.geogreenapps.apps.indukaan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ProductCartFragment_ViewBinding implements Unbinder {
  private ProductCartFragment target;

  private View view7f0a0160;

  @UiThread
  public ProductCartFragment_ViewBinding(final ProductCartFragment target, View source) {
    this.target = target;

    View view;
    target.list = Utils.findRequiredViewAsType(source, R.id.list, "field 'list'", RecyclerView.class);
    target.refresh = Utils.findRequiredViewAsType(source, R.id.refresh, "field 'refresh'", SwipeRefreshLayout.class);
    target.layout_custom_order = Utils.findRequiredViewAsType(source, R.id.layout_custom_order, "field 'layout_custom_order'", LinearLayout.class);
    target.product_type = Utils.findRequiredViewAsType(source, R.id.product_type, "field 'product_type'", TextView.class);
    target.product_value = Utils.findRequiredViewAsType(source, R.id.product_value, "field 'product_value'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_custom_order, "field 'btn_custom_order' and method 'submit'");
    target.btn_custom_order = Utils.castView(view, R.id.btn_custom_order, "field 'btn_custom_order'", AppCompatButton.class);
    view7f0a0160 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.submit(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ProductCartFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.list = null;
    target.refresh = null;
    target.layout_custom_order = null;
    target.product_type = null;
    target.product_value = null;
    target.btn_custom_order = null;

    view7f0a0160.setOnClickListener(null);
    view7f0a0160 = null;
  }
}
