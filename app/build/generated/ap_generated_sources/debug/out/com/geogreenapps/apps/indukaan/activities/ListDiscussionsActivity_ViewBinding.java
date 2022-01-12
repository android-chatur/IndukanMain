// Generated code from Butter Knife. Do not modify!
package com.geogreenapps.apps.indukaan.activities;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geogreenapps.apps.indukaan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ListDiscussionsActivity_ViewBinding implements Unbinder {
  private ListDiscussionsActivity target;

  @UiThread
  public ListDiscussionsActivity_ViewBinding(ListDiscussionsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ListDiscussionsActivity_ViewBinding(ListDiscussionsActivity target, View source) {
    this.target = target;

    target.toolbarTitle = Utils.findRequiredViewAsType(source, R.id.toolbar_title, "field 'toolbarTitle'", TextView.class);
    target.toolbarDescription = Utils.findRequiredViewAsType(source, R.id.toolbar_description, "field 'toolbarDescription'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ListDiscussionsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbarTitle = null;
    target.toolbarDescription = null;
  }
}
