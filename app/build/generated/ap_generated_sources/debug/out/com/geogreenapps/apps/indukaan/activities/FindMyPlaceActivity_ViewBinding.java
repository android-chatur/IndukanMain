// Generated code from Butter Knife. Do not modify!
package com.geogreenapps.apps.indukaan.activities;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.balysv.materialripple.MaterialRippleLayout;
import com.geogreenapps.apps.indukaan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FindMyPlaceActivity_ViewBinding implements Unbinder {
  private FindMyPlaceActivity target;

  private View view7f0a0235;

  @UiThread
  public FindMyPlaceActivity_ViewBinding(FindMyPlaceActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FindMyPlaceActivity_ViewBinding(final FindMyPlaceActivity target, View source) {
    this.target = target;

    View view;
    target.toolbarTitle = Utils.findRequiredViewAsType(source, R.id.toolbar_title, "field 'toolbarTitle'", TextView.class);
    target.toolbarDescription = Utils.findRequiredViewAsType(source, R.id.toolbar_description, "field 'toolbarDescription'", TextView.class);
    view = Utils.findRequiredView(source, R.id.confirm_btn, "field 'confirmBtn' and method 'submit'");
    target.confirmBtn = Utils.castView(view, R.id.confirm_btn, "field 'confirmBtn'", MaterialRippleLayout.class);
    view7f0a0235 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.submit(p0);
      }
    });
    target.defaultMarker = Utils.findRequiredViewAsType(source, R.id.default_marker, "field 'defaultMarker'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FindMyPlaceActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbarTitle = null;
    target.toolbarDescription = null;
    target.confirmBtn = null;
    target.defaultMarker = null;

    view7f0a0235.setOnClickListener(null);
    view7f0a0235 = null;
  }
}
