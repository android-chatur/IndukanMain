// Generated code from Butter Knife. Do not modify!
package com.geogreenapps.apps.indukaan.fragments.orderFrags;

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

public class ProductVariantFragment_ViewBinding implements Unbinder {
  private ProductVariantFragment target;

  private View view7f0a0160;

  @UiThread
  public ProductVariantFragment_ViewBinding(final ProductVariantFragment target, View source) {
    this.target = target;

    View view;
    target.frame_content = Utils.findRequiredViewAsType(source, R.id.frame_content, "field 'frame_content'", LinearLayout.class);
    target.layout_custom_order = Utils.findRequiredViewAsType(source, R.id.layout_custom_order, "field 'layout_custom_order'", LinearLayout.class);
    target.product_type = Utils.findRequiredViewAsType(source, R.id.product_type, "field 'product_type'", TextView.class);
    target.product_value = Utils.findRequiredViewAsType(source, R.id.product_value, "field 'product_value'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btn_custom_order, "field 'btnCustomOrder' and method 'submit'");
    target.btnCustomOrder = Utils.castView(view, R.id.btn_custom_order, "field 'btnCustomOrder'", AppCompatButton.class);
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
    ProductVariantFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.frame_content = null;
    target.layout_custom_order = null;
    target.product_type = null;
    target.product_value = null;
    target.btnCustomOrder = null;

    view7f0a0160.setOnClickListener(null);
    view7f0a0160 = null;
  }
}
