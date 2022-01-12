package com.geogreenapps.apps.indukaan.fragments.orderFrags;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.activities.ProductCartActivity;
import com.geogreenapps.apps.indukaan.appconfig.Constances;
import com.geogreenapps.apps.indukaan.classes.Cart;
import com.geogreenapps.apps.indukaan.classes.Option;
import com.geogreenapps.apps.indukaan.classes.Order;
import com.geogreenapps.apps.indukaan.classes.Product;
import com.geogreenapps.apps.indukaan.classes.Variant;
import com.geogreenapps.apps.indukaan.controllers.cart.CartController;
import com.geogreenapps.apps.indukaan.controllers.sessions.SessionsController;
import com.geogreenapps.apps.indukaan.controllers.stores.ProductsController;
import com.geogreenapps.apps.indukaan.helper.CommunFunctions;
import com.geogreenapps.apps.indukaan.utils.ProductUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmList;


public class ProductVariantFragment extends Fragment {

    @BindView(R.id.frame_content)
    LinearLayout frame_content;

    @BindView(R.id.layout_custom_order)
    LinearLayout layout_custom_order;
    @BindView(R.id.product_type)
    TextView product_type;
    @BindView(R.id.product_value)
    TextView product_value;
    @BindView(R.id.btn_custom_order)
    AppCompatButton btnCustomOrder;
    // custom quantity fields
    private Context mContext;
    private Order mOrder;
    private Product mProduct;
    private int module_id;
    private String module;
    private float customPrice = -1;

    private RealmList<Variant> selectedVariants;
    private Cart mcart;


    @OnClick(R.id.btn_custom_order)
    public void submit(View view) {


        //fill cart detail
        mcart.setModule_id(module_id);
        mcart.setModule(module);
        mcart.setAmount(customPrice);
        mcart.setProduct(mProduct);
        mcart.setVariants(selectedVariants);
        mcart.setParent_id(mProduct.getStore_id());
        if (SessionsController.isLogged())
            mcart.setUser_id(SessionsController.getSession().getUser().getId());


        if (mProduct.getQty_enabled() > 0) {
            ProductUtils.showBottomSheetDialog(getView(), getActivity(), mcart);
        } else {

            //save cart in the database
            CartController.addProductToCard(mcart);

            //redirect to cart activity
            Intent intent = new Intent(new Intent(getActivity(), ProductCartActivity.class));

            //redirect to checkout activity
            /* Intent intent = new Intent(new Intent(getActivity(), OrderCheckoutActivity.class));*/

            intent.putExtra("module_id", module_id);
            intent.putExtra("module", Constances.ModulesConfig.PRODUCT_MODULE);

            startActivity(intent);
            getActivity().finish();

        }


    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //init custom params
        selectedVariants = new RealmList<>();
        mcart = new Cart();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_variant, container, false);
        mContext = root.getContext();
        ButterKnife.bind(this, root);


        Bundle args = getArguments();
        if (args != null) {
            module_id = args.getInt("module_id");
            module = args.getString("module");
            mProduct = ProductsController.findProductById(module_id);

            if (mProduct != null) {
                //init custom price
                customPrice = mProduct.getProduct_value();

                product_type.setText(getResources().getString(R.string.price));

                product_value.setText(String.format(ProductUtils.parseCurrencyFormat(
                        customPrice > 0 ? customPrice : mProduct.getProduct_value(),
                        CommunFunctions.getDefaultCurrency())));

                generateGroupView(mContext, mProduct);

            }
        }


        return root;

    }


    private void generateGroupView(Context context, Product product) {

        if (product.getVariants() != null && product.getVariants().size() > 0) {


            //global fields
            LinearLayout.LayoutParams lp_match_wrap = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout.LayoutParams lp_wrap_wrap = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);


            for (Variant variant : product.getVariants()) {

                //fill the selected variant
                Variant variant1 = new Variant();
                variant1.setGroup_id(variant.getGroup_id());


                //group linear layout
                LinearLayout group_wrapper = new LinearLayout(context);
                group_wrapper.setOrientation(LinearLayout.VERTICAL);
                group_wrapper.setPaddingRelative((int) getResources().getDimension(R.dimen.spacing_middle), 0, (int) getResources().getDimension(R.dimen.spacing_middle), (int) getResources().getDimension(R.dimen.spacing_middle));
                LinearLayout.LayoutParams grpLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                grpLayoutParams.setMargins(0, 0, 0, 0);
                group_wrapper.setLayoutParams(grpLayoutParams);

                //group title txt
                TextView group_label = new TextView(context);
                group_label.setText(variant.getGroup_label());
                group_label.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                group_label.setTypeface(group_label.getTypeface(), Typeface.BOLD);
                group_label.setTextColor(ContextCompat.getColorStateList(context, R.color.defaultBlackColor));
                group_label.setTextSize(20);


                lp_match_wrap.setMargins(0, 0, 0, (int) getResources().getDimension(R.dimen.spacing_medium));

                group_label.setLayoutParams(lp_match_wrap);

                //add group title to the layou
                group_wrapper.addView(group_label);


                if (variant.getOptions() != null && variant.getOptions().size() > 0) {

                    if (variant.getType() != null && variant.getType().equalsIgnoreCase(Variant.ONE_OPTION)) {

                        Variant tempVariantOO = Realm.getDefaultInstance().copyFromRealm(variant);


                        /********* ONE_OPTION   *********/

                        final double[] one_option_price = {-1};
                        for (Option option : variant.getOptions()) {


                            //choice  with price
                            LinearLayout linearLayout_376 = new LinearLayout(mContext);
                            linearLayout_376.setOrientation(LinearLayout.HORIZONTAL);
                            linearLayout_376.setLayoutParams(lp_match_wrap);


                            //radio
                            RadioButton radioBtn = new RadioButton(mContext);
                            LinearLayout.LayoutParams lp_rb = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            lp_rb.weight = 1;
                            radioBtn.setLayoutParams(lp_rb);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                                radioBtn.setButtonTintList(ContextCompat.getColorStateList(context, R.color.colorPrimary));

                            //dynamic content
                            radioBtn.setText(option.getLabel());
                            radioBtn.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                            radioBtn.setTag(option.getId());
                            radioBtn.setId(option.getId());

                            //todo: action click listener
                            radioBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {


                                    for (int i = 0; i < variant.getOptions().size(); i++) {
                                        if (view.getId() == variant.getOptions().get(i).getId()) {
                                            RealmList<Option> tempOption = new RealmList<>();
                                            tempOption.add(variant.getOptions().get(i));
                                            tempVariantOO.setOptions(tempOption);


                                            if (tempVariantOO.getOptions() != null && tempVariantOO.getOptions().size() > 0) {
                                                selectedVariants.remove(tempVariantOO);
                                                selectedVariants.add(tempVariantOO);
                                            }
                                            /*if (tempVariantOO.getOptions() != null && !tempVariantOO.getOptions().contains(variant.getOptions().get(i)))
                                                tempVariantOO.getOptions().add(variant.getOptions().get(i));*/

                                            ((RadioButton) getView().findViewWithTag(variant.getOptions().get(i).getId())).setChecked(true);
                                        } else {
                                            /*if (tempVariantOO.getOptions() != null && tempVariantOO.getOptions().contains(variant.getOptions().get(i)))
                                                tempVariantOO.getOptions().remove(variant.getOptions().get(i));*/
                                            ((RadioButton) getView().findViewWithTag(variant.getOptions().get(i).getId())).setChecked(false);
                                        }
                                    }


                                    //calculate total amount
                                    if (((RadioButton) view).isChecked()) {

                                        if (option.getValue() > 0) {
                                            if (one_option_price[0] == -1) {
                                                one_option_price[0] = option.getValue();
                                            } else {
                                                customPrice = (float) (customPrice - one_option_price[0]);
                                            }

                                            customPrice = (float) (customPrice + option.getValue());
                                            product_value.setText(String.format(ProductUtils.parseCurrencyFormat(
                                                    customPrice > 0 ? customPrice : (int) mProduct.getProduct_value(),
                                                    CommunFunctions.getDefaultCurrency())));
                                        }

                                    }

                                }
                            });

                            linearLayout_376.addView(radioBtn);

                            if (option.getValue() > 0) {

                                LinearLayout.LayoutParams lp_wrap_wrap_price = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                lp_match_wrap.setMargins((int) getResources().getDimension(R.dimen.spacing_medium), 0, 0, (int) getResources().getDimension(R.dimen.spacing_medium));

                                //txt price
                                TextView price_option = new TextView(mContext);
                                price_option.setText(String.format(getContext().getString(R.string.variant_additional_cost), option.getParsed_value()));
                                price_option.setTextColor(getResources().getColor(R.color.colorPrimary));
                                price_option.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
                                price_option.setTypeface(price_option.getTypeface(), Typeface.BOLD);
                                price_option.setLayoutParams(lp_wrap_wrap_price);

                                linearLayout_376.addView(price_option);
                            }

                            group_wrapper.addView(linearLayout_376);

                        }

                    } else if (variant.getType() != null && variant.getType().equalsIgnoreCase(Variant.MULTI_OPTIONS)) {
                        /********* MULTI_OPTIONS   *********/

                        Variant variantMO = Realm.getDefaultInstance().copyFromRealm(variant);
                        variantMO.getOptions().clear();

                        LinearLayout group_level_3_options = new LinearLayout(mContext);
                        group_level_3_options.setOrientation(LinearLayout.VERTICAL);
                        group_level_3_options.setLayoutParams(lp_match_wrap);

                        for (Option option : variant.getOptions()) {


                            //Linear layout checkbox
                            LinearLayout.LayoutParams checkBox_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                            //choice 1 linearlayout
                            LinearLayout linearLayout_ch_1 = new LinearLayout(mContext);
                            linearLayout_ch_1.setOrientation(LinearLayout.HORIZONTAL);
                            linearLayout_ch_1.setLayoutParams(lp_match_wrap);


                            //choice 1 checkbox
                            CheckBox checkBox = new CheckBox(mContext);
                            checkBox_params.weight = 1;

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                                checkBox.setButtonTintList(ContextCompat.getColorStateList(context, R.color.colorPrimary));


                            //dynamic content
                            checkBox.setText(option.getLabel());
                            checkBox.setTag(option.getId());
                            checkBox.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                            checkBox.setId(option.getId());


                            //click listener
                            checkBox.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                        variantMO.getOptions().stream()
                                                .filter(option1 -> option1.getId() == option.getId())
                                                .findFirst()
                                                .map(p -> {
                                                    variantMO.getOptions().remove(p);
                                                    return p;
                                                });
                                    }


                                    if (((CheckBox) view).isChecked()) {

                                        variantMO.getOptions().add(option);
                                        //calculate the amount
                                        if (option.getValue() > 0)
                                            customPrice = (float) (customPrice + option.getValue());

                                    } else {


                                        //calculate the amount
                                        if (option.getValue() > 0)
                                            customPrice = (float) (customPrice - option.getValue());
                                    }

                                    if (variantMO.getOptions() != null && variantMO.getOptions().size() > 0) {
                                        selectedVariants.remove(variantMO);
                                        selectedVariants.add(variantMO);
                                    }


                                    //display the amount on realtime
                                    product_value.setText(String.format(ProductUtils.parseCurrencyFormat(
                                            customPrice > 0 ? customPrice : (int) mProduct.getProduct_value(),
                                            CommunFunctions.getDefaultCurrency())));
                                }
                            });


                            checkBox.setLayoutParams(checkBox_params);

                            linearLayout_ch_1.addView(checkBox);

                            //choice 1 price
                            TextView checkBox_price = new TextView(mContext);
                            checkBox_price.setText(String.format(getContext().getString(R.string.variant_additional_cost), option.getParsed_value()));
                            checkBox_price.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
                            checkBox_price.setTextColor(getResources().getColor(R.color.colorPrimary));
                            checkBox_price.setTypeface(checkBox_price.getTypeface(), Typeface.BOLD);
                            checkBox_price.setLayoutParams(lp_wrap_wrap);

                            linearLayout_ch_1.addView(checkBox_price);

                            group_level_3_options.addView(linearLayout_ch_1);
                        }

                        group_wrapper.addView(group_level_3_options);

                    }
                }

                frame_content.addView(group_wrapper);
            }


        }

    }


}
