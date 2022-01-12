// Generated code from Butter Knife. Do not modify!
package com.geogreenapps.apps.indukaan.activities;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.geogreenapps.apps.indukaan.R;
import com.google.android.gms.ads.AdView;
import com.nirhart.parallaxscroll.views.ParallaxScrollView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class StoreDetailActivity_ViewBinding implements Unbinder {
  private StoreDetailActivity target;

  @UiThread
  public StoreDetailActivity_ViewBinding(StoreDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public StoreDetailActivity_ViewBinding(StoreDetailActivity target, View source) {
    this.target = target;

    target.toolbarTitle = Utils.findRequiredViewAsType(source, R.id.toolbar_title, "field 'toolbarTitle'", TextView.class);
    target.toolbar_back = Utils.findRequiredViewAsType(source, R.id.toolbar_back, "field 'toolbar_back'", ImageView.class);
    target.description_label = Utils.findRequiredViewAsType(source, R.id.description_label, "field 'description_label'", TextView.class);
    target.image = Utils.findRequiredViewAsType(source, R.id.image, "field 'image'", ImageView.class);
    target.nbrPictures = Utils.findRequiredViewAsType(source, R.id.nbrPictures, "field 'nbrPictures'", TextView.class);
    target.distanceView = Utils.findRequiredViewAsType(source, R.id.distanceView, "field 'distanceView'", TextView.class);
    target.progressMapLL = Utils.findRequiredViewAsType(source, R.id.progressMapLL, "field 'progressMapLL'", LinearLayout.class);
    target.mapcontainer = Utils.findRequiredViewAsType(source, R.id.mapcontainer, "field 'mapcontainer'", LinearLayout.class);
    target.adView = Utils.findRequiredViewAsType(source, R.id.adView, "field 'adView'", AdView.class);
    target.adsLayout = Utils.findRequiredViewAsType(source, R.id.adsLayout, "field 'adsLayout'", LinearLayout.class);
    target.addressContent = Utils.findRequiredViewAsType(source, R.id.address_content, "field 'addressContent'", TextView.class);
    target.catImage = Utils.findRequiredViewAsType(source, R.id.catImage, "field 'catImage'", ImageView.class);
    target.categoryContent = Utils.findRequiredViewAsType(source, R.id.category_content, "field 'categoryContent'", TextView.class);
    target.categoryLayout = Utils.findRequiredViewAsType(source, R.id.category_layout, "field 'categoryLayout'", LinearLayout.class);
    target.descriptionContent = Utils.findRequiredViewAsType(source, R.id.description_content, "field 'descriptionContent'", TextView.class);
    target.productsBtn = Utils.findRequiredViewAsType(source, R.id.productsBtn, "field 'productsBtn'", Button.class);
    target.productsBtnLayout = Utils.findRequiredViewAsType(source, R.id.productsBtnLayout, "field 'productsBtnLayout'", LinearLayout.class);
    target.reviewsBtn = Utils.findRequiredViewAsType(source, R.id.reviewsBtn, "field 'reviewsBtn'", Button.class);
    target.reviewsBtnLayout = Utils.findRequiredViewAsType(source, R.id.reviewsBtnLayout, "field 'reviewsBtnLayout'", LinearLayout.class);
    target.galleryBtn = Utils.findRequiredViewAsType(source, R.id.galleryBtn, "field 'galleryBtn'", Button.class);
    target.galleryBtnLayout = Utils.findRequiredViewAsType(source, R.id.galleryBtnLayout, "field 'galleryBtnLayout'", LinearLayout.class);
    target.storeContentBlockBtns = Utils.findRequiredViewAsType(source, R.id.store_content_block_btns, "field 'storeContentBlockBtns'", LinearLayout.class);
    target.scontainer = Utils.findRequiredViewAsType(source, R.id.scontainer, "field 'scontainer'", LinearLayout.class);
    target.storeContentBlock = Utils.findRequiredViewAsType(source, R.id.store_content_block, "field 'storeContentBlock'", LinearLayout.class);
    target.btnChatCustomer = Utils.findRequiredViewAsType(source, R.id.btn_chat_customer, "field 'btnChatCustomer'", ImageButton.class);
    target.btnWebsite = Utils.findRequiredViewAsType(source, R.id.websiteBtn, "field 'btnWebsite'", ImageButton.class);
    target.phoneBtn = Utils.findRequiredViewAsType(source, R.id.phoneBtn, "field 'phoneBtn'", ImageButton.class);
    target.mapBtn = Utils.findRequiredViewAsType(source, R.id.mapBtn, "field 'mapBtn'", ImageButton.class);
    target.shareBtn = Utils.findRequiredViewAsType(source, R.id.shareBtn, "field 'shareBtn'", ImageButton.class);
    target.btnsLayout = Utils.findRequiredViewAsType(source, R.id.btnsLayout, "field 'btnsLayout'", LinearLayout.class);
    target.mScroll = Utils.findRequiredViewAsType(source, R.id.mScroll, "field 'mScroll'", ParallaxScrollView.class);
    target.opening_time_container = Utils.findRequiredViewAsType(source, R.id.opening_time_container, "field 'opening_time_container'", LinearLayout.class);
    target.opening_time_content = Utils.findRequiredViewAsType(source, R.id.opening_time_content, "field 'opening_time_content'", TextView.class);
    target.badge_closed = Utils.findRequiredViewAsType(source, R.id.badge_closed, "field 'badge_closed'", TextView.class);
    target.badge_open = Utils.findRequiredViewAsType(source, R.id.badge_open, "field 'badge_open'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    StoreDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbarTitle = null;
    target.toolbar_back = null;
    target.description_label = null;
    target.image = null;
    target.nbrPictures = null;
    target.distanceView = null;
    target.progressMapLL = null;
    target.mapcontainer = null;
    target.adView = null;
    target.adsLayout = null;
    target.addressContent = null;
    target.catImage = null;
    target.categoryContent = null;
    target.categoryLayout = null;
    target.descriptionContent = null;
    target.productsBtn = null;
    target.productsBtnLayout = null;
    target.reviewsBtn = null;
    target.reviewsBtnLayout = null;
    target.galleryBtn = null;
    target.galleryBtnLayout = null;
    target.storeContentBlockBtns = null;
    target.scontainer = null;
    target.storeContentBlock = null;
    target.btnChatCustomer = null;
    target.btnWebsite = null;
    target.phoneBtn = null;
    target.mapBtn = null;
    target.shareBtn = null;
    target.btnsLayout = null;
    target.mScroll = null;
    target.opening_time_container = null;
    target.opening_time_content = null;
    target.badge_closed = null;
    target.badge_open = null;
  }
}
