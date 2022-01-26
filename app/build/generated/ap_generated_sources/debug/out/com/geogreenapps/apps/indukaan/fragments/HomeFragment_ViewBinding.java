// Generated code from Butter Knife. Do not modify!
package com.geogreenapps.apps.indukaan.fragments;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.geogreenapps.apps.indukaan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeFragment_ViewBinding implements Unbinder {
  private HomeFragment target;

  private View view7f0a0471;

  @UiThread
  public HomeFragment_ViewBinding(final HomeFragment target, View source) {
    this.target = target;

    View view;
    target.mNestedScrollView = Utils.findRequiredViewAsType(source, R.id.mScroll, "field 'mNestedScrollView'", NestedScrollView.class);
    view = Utils.findRequiredView(source, R.id.lbox, "field 'lbox' and method 'submit'");
    target.lbox = Utils.castView(view, R.id.lbox, "field 'lbox'", LinearLayout.class);
    view7f0a0471 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.submit(p0);
      }
    });
    target.refresh = Utils.findRequiredViewAsType(source, R.id.refresh, "field 'refresh'", SwipeRefreshLayout.class);
    target.deliveryWidget = Utils.findRequiredViewAsType(source, R.id.delivery_widget, "field 'deliveryWidget'", CardView.class);
    target.deliveryAppName = Utils.findRequiredViewAsType(source, R.id.deliver_app_name, "field 'deliveryAppName'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mNestedScrollView = null;
    target.lbox = null;
    target.refresh = null;
    target.deliveryWidget = null;
    target.deliveryAppName = null;

    view7f0a0471.setOnClickListener(null);
    view7f0a0471 = null;
  }
}
