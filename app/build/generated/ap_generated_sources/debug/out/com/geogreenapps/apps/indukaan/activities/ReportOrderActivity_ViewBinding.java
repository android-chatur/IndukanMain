// Generated code from Butter Knife. Do not modify!
package com.geogreenapps.apps.indukaan.activities;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.AppCompatButton;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geogreenapps.apps.indukaan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ReportOrderActivity_ViewBinding implements Unbinder {
  private ReportOrderActivity target;

  @UiThread
  public ReportOrderActivity_ViewBinding(ReportOrderActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ReportOrderActivity_ViewBinding(ReportOrderActivity target, View source) {
    this.target = target;

    target.toolbarTitle = Utils.findRequiredViewAsType(source, R.id.toolbar_title, "field 'toolbarTitle'", TextView.class);
    target.toolbarDescription = Utils.findRequiredViewAsType(source, R.id.toolbar_description, "field 'toolbarDescription'", TextView.class);
    target.report_choices_rg = Utils.findRequiredViewAsType(source, R.id.report_choices_rg, "field 'report_choices_rg'", RadioGroup.class);
    target.btn_update_status = Utils.findRequiredViewAsType(source, R.id.btn_update_status, "field 'btn_update_status'", AppCompatButton.class);
    target.custom_report_message = Utils.findRequiredViewAsType(source, R.id.custom_report_message, "field 'custom_report_message'", TextView.class);
    target.custom_report_message_layout = Utils.findRequiredViewAsType(source, R.id.custom_report_message_layout, "field 'custom_report_message_layout'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ReportOrderActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbarTitle = null;
    target.toolbarDescription = null;
    target.report_choices_rg = null;
    target.btn_update_status = null;
    target.custom_report_message = null;
    target.custom_report_message_layout = null;
  }
}
