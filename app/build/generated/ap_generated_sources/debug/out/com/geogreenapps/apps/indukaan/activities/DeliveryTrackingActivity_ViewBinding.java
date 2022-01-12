// Generated code from Butter Knife. Do not modify!
package com.geogreenapps.apps.indukaan.activities;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.AppCompatButton;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geogreenapps.apps.indukaan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DeliveryTrackingActivity_ViewBinding implements Unbinder {
  private DeliveryTrackingActivity target;

  @UiThread
  public DeliveryTrackingActivity_ViewBinding(DeliveryTrackingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DeliveryTrackingActivity_ViewBinding(DeliveryTrackingActivity target, View source) {
    this.target = target;

    target.toolbarTitle = Utils.findRequiredViewAsType(source, R.id.toolbar_title, "field 'toolbarTitle'", TextView.class);
    target.toolbarDescription = Utils.findRequiredViewAsType(source, R.id.toolbar_description, "field 'toolbarDescription'", TextView.class);
    target.item_focus_layout = Utils.findRequiredViewAsType(source, R.id.item_focus_layout, "field 'item_focus_layout'", LinearLayout.class);
    target.btn_contact = Utils.findRequiredViewAsType(source, R.id.btn_contact, "field 'btn_contact'", AppCompatButton.class);
    target.closeLayout = Utils.findRequiredViewAsType(source, R.id.closeLayout, "field 'closeLayout'", ImageView.class);
    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
    target.address = Utils.findRequiredViewAsType(source, R.id.address, "field 'address'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DeliveryTrackingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbarTitle = null;
    target.toolbarDescription = null;
    target.item_focus_layout = null;
    target.btn_contact = null;
    target.closeLayout = null;
    target.name = null;
    target.address = null;
  }
}
