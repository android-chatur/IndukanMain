// Generated code from Butter Knife. Do not modify!
package com.geogreenapps.apps.indukaan.fragments;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.geogreenapps.apps.indukaan.R;
import com.google.android.material.textfield.TextInputEditText;
import com.jaygoo.widget.RangeSeekBar;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CustomSearchFragment_ViewBinding implements Unbinder {
  private CustomSearchFragment target;

  private View view7f0a015b;

  @UiThread
  public CustomSearchFragment_ViewBinding(final CustomSearchFragment target, View source) {
    this.target = target;

    View view;
    target.rangeSeekBar = Utils.findRequiredViewAsType(source, R.id.range_seek_bar, "field 'rangeSeekBar'", SeekBar.class);
    target.range_seek_bar_text = Utils.findRequiredViewAsType(source, R.id.range_seek_bar_text, "field 'range_seek_bar_text'", TextView.class);
    target.searchField = Utils.findRequiredViewAsType(source, R.id.searchField, "field 'searchField'", TextView.class);
    target.dateBeginTxt = Utils.findRequiredViewAsType(source, R.id.date_begin_txt, "field 'dateBeginTxt'", TextInputEditText.class);
    target.filterStoresBtn = Utils.findRequiredViewAsType(source, R.id.filterStoresBtn, "field 'filterStoresBtn'", Button.class);
    target.filterProductsBtn = Utils.findRequiredViewAsType(source, R.id.filterProductsBtn, "field 'filterProductsBtn'", Button.class);
    target.filterOffersBtn = Utils.findRequiredViewAsType(source, R.id.filterOffersBtn, "field 'filterOffersBtn'", Button.class);
    target.searchOffers = Utils.findRequiredViewAsType(source, R.id.searchOffers, "field 'searchOffers'", LinearLayout.class);
    target.searchProducts = Utils.findRequiredViewAsType(source, R.id.searchProducts, "field 'searchProducts'", LinearLayout.class);
    target.searchFilterCategory = Utils.findRequiredViewAsType(source, R.id.searchFilterCategory, "field 'searchFilterCategory'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.btnSearchLayout, "field 'btnSearchLayout' and method 'searchAction'");
    target.btnSearchLayout = Utils.castView(view, R.id.btnSearchLayout, "field 'btnSearchLayout'", Button.class);
    view7f0a015b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.searchAction(p0);
      }
    });
    target.btnsOffersPrice = Utils.findRequiredViewAsType(source, R.id.btnsOffersPrice, "field 'btnsOffersPrice'", LinearLayout.class);
    target.price_offer_btn = Utils.findRequiredViewAsType(source, R.id.price_offer_btn, "field 'price_offer_btn'", Button.class);
    target.discount_offer_btn = Utils.findRequiredViewAsType(source, R.id.discount_offer_btn, "field 'discount_offer_btn'", Button.class);
    target.btnsOffersDiscountProps = Utils.findRequiredViewAsType(source, R.id.btnsOffersDiscountProps, "field 'btnsOffersDiscountProps'", LinearLayout.class);
    target.btnsOffersPriceFormat = Utils.findRequiredViewAsType(source, R.id.btnsOffersPriceFormat, "field 'btnsOffersPriceFormat'", LinearLayout.class);
    target.orderByDate = Utils.findRequiredViewAsType(source, R.id.orderByDate, "field 'orderByDate'", Button.class);
    target.orderByGeo = Utils.findRequiredViewAsType(source, R.id.orderByGeo, "field 'orderByGeo'", Button.class);
    target.layoutLocationChanger = Utils.findRequiredViewAsType(source, R.id.layoutLocationChanger, "field 'layoutLocationChanger'", LinearLayout.class);
    target.locationLbl = Utils.findRequiredViewAsType(source, R.id.locationLbl, "field 'locationLbl'", TextView.class);
    target.range_price = Utils.findRequiredViewAsType(source, R.id.range_price, "field 'range_price'", RangeSeekBar.class);
    target.range_seek_indicator = Utils.findRequiredViewAsType(source, R.id.range_seek_indicator, "field 'range_seek_indicator'", TextView.class);
    target.btnsModules = Utils.findRequiredViewAsType(source, R.id.btnsModules, "field 'btnsModules'", LinearLayout.class);
    target.btnOrder = Utils.findRequiredViewAsType(source, R.id.btnOrder, "field 'btnOrder'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CustomSearchFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rangeSeekBar = null;
    target.range_seek_bar_text = null;
    target.searchField = null;
    target.dateBeginTxt = null;
    target.filterStoresBtn = null;
    target.filterProductsBtn = null;
    target.filterOffersBtn = null;
    target.searchOffers = null;
    target.searchProducts = null;
    target.searchFilterCategory = null;
    target.btnSearchLayout = null;
    target.btnsOffersPrice = null;
    target.price_offer_btn = null;
    target.discount_offer_btn = null;
    target.btnsOffersDiscountProps = null;
    target.btnsOffersPriceFormat = null;
    target.orderByDate = null;
    target.orderByGeo = null;
    target.layoutLocationChanger = null;
    target.locationLbl = null;
    target.range_price = null;
    target.range_seek_indicator = null;
    target.btnsModules = null;
    target.btnOrder = null;

    view7f0a015b.setOnClickListener(null);
    view7f0a015b = null;
  }
}
