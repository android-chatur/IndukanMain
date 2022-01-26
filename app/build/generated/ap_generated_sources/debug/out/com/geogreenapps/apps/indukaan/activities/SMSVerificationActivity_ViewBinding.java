// Generated code from Butter Knife. Do not modify!
package com.geogreenapps.apps.indukaan.activities;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.geogreenapps.apps.indukaan.R;
import com.rengwuxian.materialedittext.MaterialEditText;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SMSVerificationActivity_ViewBinding implements Unbinder {
  private SMSVerificationActivity target;

  private View view7f0a0233;

  private View view7f0a06a6;

  @UiThread
  public SMSVerificationActivity_ViewBinding(SMSVerificationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SMSVerificationActivity_ViewBinding(final SMSVerificationActivity target, View source) {
    this.target = target;

    View view;
    target.toolbarTitle = Utils.findRequiredViewAsType(source, R.id.toolbar_title, "field 'toolbarTitle'", TextView.class);
    target.toolbarDescription = Utils.findRequiredViewAsType(source, R.id.toolbar_description, "field 'toolbarDescription'", TextView.class);
    view = Utils.findRequiredView(source, R.id.confirm, "field 'confirm' and method 'confirmAuth'");
    target.confirm = Utils.castView(view, R.id.confirm, "field 'confirm'", Button.class);
    view7f0a0233 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.confirmAuth(p0);
      }
    });
    target.sms_code = Utils.findRequiredViewAsType(source, R.id.sms_code, "field 'sms_code'", MaterialEditText.class);
    view = Utils.findRequiredView(source, R.id.resendCode, "field 'resendCode' and method 'resendVerifCode'");
    target.resendCode = Utils.castView(view, R.id.resendCode, "field 'resendCode'", TextView.class);
    view7f0a06a6 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.resendVerifCode(p0);
      }
    });
    target.refresh = Utils.findRequiredViewAsType(source, R.id.refresh, "field 'refresh'", SwipeRefreshLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SMSVerificationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbarTitle = null;
    target.toolbarDescription = null;
    target.confirm = null;
    target.sms_code = null;
    target.resendCode = null;
    target.refresh = null;

    view7f0a0233.setOnClickListener(null);
    view7f0a0233 = null;
    view7f0a06a6.setOnClickListener(null);
    view7f0a06a6 = null;
  }
}
