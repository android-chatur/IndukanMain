// Generated code from Butter Knife. Do not modify!
package com.geogreenapps.apps.indukaan.activities;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geogreenapps.apps.indukaan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BookmarkActivity_ViewBinding implements Unbinder {
  private BookmarkActivity target;

  @UiThread
  public BookmarkActivity_ViewBinding(BookmarkActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BookmarkActivity_ViewBinding(BookmarkActivity target, View source) {
    this.target = target;

    target.toolbarTitle = Utils.findRequiredViewAsType(source, R.id.toolbar_title, "field 'toolbarTitle'", TextView.class);
    target.toolbarDescription = Utils.findRequiredViewAsType(source, R.id.toolbar_description, "field 'toolbarDescription'", TextView.class);
    target.storeContent = Utils.findRequiredViewAsType(source, R.id.container, "field 'storeContent'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BookmarkActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbarTitle = null;
    target.toolbarDescription = null;
    target.storeContent = null;
  }
}
