// Generated code from Butter Knife. Do not modify!
package com.geogreenapps.apps.indukaan.activities;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geogreenapps.apps.indukaan.R;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.gms.ads.AdView;
import com.nirhart.parallaxscroll.views.ParallaxScrollView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OfferDetailActivity_ViewBinding implements Unbinder {
  private OfferDetailActivity target;

  @UiThread
  public OfferDetailActivity_ViewBinding(OfferDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OfferDetailActivity_ViewBinding(OfferDetailActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.app_bar, "field 'toolbar'", Toolbar.class);
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progressBar, "field 'progressBar'", SpinKitView.class);
    target.loading = Utils.findRequiredViewAsType(source, R.id.loading, "field 'loading'", LinearLayout.class);
    target.toolbar_back = Utils.findRequiredViewAsType(source, R.id.toolbar_back, "field 'toolbar_back'", ImageView.class);
    target.toolbarTitle = Utils.findRequiredViewAsType(source, R.id.toolbar_title, "field 'toolbarTitle'", TextView.class);
    target.product_label = Utils.findRequiredViewAsType(source, R.id.product_label, "field 'product_label'", TextView.class);
    target.nbrPictures = Utils.findRequiredViewAsType(source, R.id.nbrPictures, "field 'nbrPictures'", TextView.class);
    target.image = Utils.findRequiredViewAsType(source, R.id.image, "field 'image'", ImageView.class);
    target.distanceView = Utils.findRequiredViewAsType(source, R.id.distanceView, "field 'distanceView'", TextView.class);
    target.priceView = Utils.findRequiredViewAsType(source, R.id.priceView, "field 'priceView'", TextView.class);
    target.detailProduct = Utils.findRequiredViewAsType(source, R.id.detail_product, "field 'detailProduct'", TextView.class);
    target.productUpTo = Utils.findRequiredViewAsType(source, R.id.product_up_to, "field 'productUpTo'", TextView.class);
    target.storeBtn = Utils.findRequiredViewAsType(source, R.id.storeBtn, "field 'storeBtn'", TextView.class);
    target.storeBtnLayout = Utils.findRequiredViewAsType(source, R.id.storeBtnLayout, "field 'storeBtnLayout'", LinearLayout.class);
    target.adView = Utils.findRequiredViewAsType(source, R.id.adView, "field 'adView'", AdView.class);
    target.ads = Utils.findRequiredViewAsType(source, R.id.ads, "field 'ads'", LinearLayout.class);
    target.dealLayout = Utils.findRequiredViewAsType(source, R.id.deal_layout, "field 'dealLayout'", LinearLayout.class);
    target.mScroll = Utils.findRequiredViewAsType(source, R.id.mScroll, "field 'mScroll'", ParallaxScrollView.class);
    target.layout_custom_order = Utils.findRequiredViewAsType(source, R.id.layout_custom_order, "field 'layout_custom_order'", LinearLayout.class);
    target.product_type = Utils.findRequiredViewAsType(source, R.id.product_type, "field 'product_type'", TextView.class);
    target.product_value = Utils.findRequiredViewAsType(source, R.id.product_value, "field 'product_value'", TextView.class);
    target.btnCustomOrder = Utils.findRequiredViewAsType(source, R.id.btn_custom_order, "field 'btnCustomOrder'", AppCompatButton.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OfferDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.progressBar = null;
    target.loading = null;
    target.toolbar_back = null;
    target.toolbarTitle = null;
    target.product_label = null;
    target.nbrPictures = null;
    target.image = null;
    target.distanceView = null;
    target.priceView = null;
    target.detailProduct = null;
    target.productUpTo = null;
    target.storeBtn = null;
    target.storeBtnLayout = null;
    target.adView = null;
    target.ads = null;
    target.dealLayout = null;
    target.mScroll = null;
    target.layout_custom_order = null;
    target.product_type = null;
    target.product_value = null;
    target.btnCustomOrder = null;
  }
}
