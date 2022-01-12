package com.geogreenapps.apps.indukaan.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.activities.ResultFilterActivity;
import com.geogreenapps.apps.indukaan.appconfig.AppConfig;
import com.geogreenapps.apps.indukaan.appconfig.Constances;
import com.geogreenapps.apps.indukaan.classes.Category;
import com.geogreenapps.apps.indukaan.classes.Setting;
import com.geogreenapps.apps.indukaan.controllers.SettingsController;
import com.geogreenapps.apps.indukaan.controllers.categories.CategoryController;
import com.geogreenapps.apps.indukaan.customview.CategoryCustomView;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.textfield.TextInputEditText;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.jaygoo.widget.OnRangeChangedListener;
import com.jaygoo.widget.RangeSeekBar;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.geogreenapps.apps.indukaan.AppController.getInstance;


public class CustomSearchFragment extends Fragment implements View.OnClickListener {

    public static Class<?> previousPageClass = null;
    private static int mOldDistance = 100;
    private static final String mOldValue = "";
    private String distance_unit;

    @BindView(R.id.range_seek_bar)
    SeekBar rangeSeekBar;
    @BindView(R.id.range_seek_bar_text)
    TextView range_seek_bar_text;
    @BindView(R.id.searchField)
    TextView searchField;
    @BindView(R.id.date_begin_txt)
    TextInputEditText dateBeginTxt;

    @BindView(R.id.filterStoresBtn)
    Button filterStoresBtn;
    @BindView(R.id.filterProductsBtn)
    Button filterProductsBtn;

    @BindView(R.id.filterOffersBtn)
    Button filterOffersBtn;

    @BindView(R.id.searchOffers)
    LinearLayout searchOffers;

    @BindView(R.id.searchProducts)
    LinearLayout searchProducts;

    @BindView(R.id.searchFilterCategory)
    LinearLayout searchFilterCategory;
    @BindView(R.id.btnSearchLayout)
    Button btnSearchLayout;
    @BindView(R.id.btnsOffersPrice)
    LinearLayout btnsOffersPrice;
    @BindView(R.id.price_offer_btn)
    Button price_offer_btn;
    @BindView(R.id.discount_offer_btn)
    Button discount_offer_btn;
    @BindView(R.id.btnsOffersDiscountProps)
    LinearLayout btnsOffersDiscountProps;
    @BindView(R.id.btnsOffersPriceFormat)
    LinearLayout btnsOffersPriceFormat;
    @BindView(R.id.orderByDate)
    Button orderByDate;
    @BindView(R.id.orderByGeo)
    Button orderByGeo;
    @BindView(R.id.layoutLocationChanger)
    LinearLayout layoutLocationChanger;
    @BindView(R.id.locationLbl)
    TextView locationLbl;

    @BindView(R.id.range_price)
    RangeSeekBar range_price;


    @BindView(R.id.range_seek_indicator)
    TextView range_seek_indicator;

    @BindView(R.id.btnsModules)
    LinearLayout btnsModules;

    @BindView(R.id.btnOrder)
    LinearLayout btnOrder;


    @OnClick(R.id.btnSearchLayout)
    void searchAction(View view) {

        String searchFieldTxt = searchField.getText().toString().trim();
        searchParams.put("search", searchFieldTxt);
        searchParams.put("category", CategoryCustomView.itemCategoryselectedId);
        searchParams.put("category_selected_index", CategoryCustomView.itemCategoryselectedIndex);


        if (min_price > 0)
            searchParams.put("price_min", String.valueOf(min_price));

        if (max_price < 10000)
            searchParams.put("price_max", String.valueOf(max_price));


        searchParams.put("category_id", CATEGORY_SELECTED);

        if (REQUEST_RANGE_RADIUS > -1) {
            if (REQUEST_RANGE_RADIUS <= 99)
                searchParams.put("radius", String.valueOf((REQUEST_RANGE_RADIUS * 1000)));
        }

        searchParams.put("date_begin", dateBeginTxt.getText().toString().trim());


        if (previousPageClass != null) {
            Intent intent = new Intent();
            previousPageClass = null;
            intent.putExtra("searchParams", searchParams);
            getActivity().setResult(Activity.RESULT_OK, intent);
            getActivity().finish();
            return;
        } else {

            //save view state
            saveCurrentSearchFields(searchParams);

            if (getArguments() != null && getArguments().containsKey("useCacheFields") && getArguments().getString("useCacheFields").equals("enabled")) {
                Intent intent = new Intent();
                intent.putExtra("searchParams", searchParams);
                getActivity().setResult(Activity.RESULT_OK, intent);
                getActivity().finish();

            } else {

                Intent intent = new Intent(getActivity(), ResultFilterActivity.class);
                intent.putExtra("searchParams", searchParams);
                getActivity().startActivity(intent);
                getActivity().finish();
            }


        }

    }

    private HashMap<String, Object> searchParams;
    private final int AUTOCOMPLETE_REQUEST_CODE = 1001;
    private int min_price = -1, max_price = -1;

    private int REQUEST_RANGE_RADIUS = -1;
    private final Button[] btnOffersDiscount = new Button[4];
    private final Button[] btnOffersPrice = new Button[4];
    private Button btn_discount_unfocus, btn_price_unfocus;
    private final int[] btn_id_discount = {R.id.discount_less_than_25, R.id.discount_less_than_50, R.id.discount_less_than_75, R.id.discount_less_than_100};
    private final int[] btn_id_price = {R.id.price_one_number, R.id.price_two_numbers, R.id.price_three_numbers, R.id.price_four_numbers};

    private int CATEGORY_SELECTED = -1;
    private List<Category> listCats;


    public CustomSearchFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.v2_fragment_custom_search, container, false);
        ButterKnife.bind(this, view);

        initParams(view);

        initRangeSeekBar();

        //setup category List
        setupCategoryFilter(view);

        handleClickEventListener();

        initPlacesAPi(view.getContext());

        rangeSeekBarPrice();


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        //set store as a default view
        setDefaultModuleSelected(getView(), (getArguments() != null && getArguments().containsKey("selected_module")) ? getArguments().getString("selected_module") : Constances.ModulesConfig.STORE_MODULE);


        //get data from cache if exist
        if (getArguments() != null && getArguments().containsKey("useCacheFields") && getArguments().getString("useCacheFields").equals("enabled")) {

            HashMap<String, Object> cacheSearchObj = null;
            if (getArguments().containsKey("searchParams")) {  //this will serve when choosing a category from home fragment
                cacheSearchObj = (HashMap<String, Object>) getArguments().getSerializable("searchParams");
            } else { // if there's no fields then we should get the detail from cache
                cacheSearchObj = getSavedSearchFields();
            }

            if (cacheSearchObj != null) {
                HashMap<String, Object> finalCacheSearchObj = cacheSearchObj;
                (new android.os.Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        importDataFromPrefObj(finalCacheSearchObj);
                    }
                }, 800);
            }
        }


        //disable module filter buttons for maps activity
        if (previousPageClass != null) {
            btnOrder.setVisibility(View.GONE);
            btnsModules.setVisibility(View.GONE);
        }


    }

    private void initPlacesAPi(Context context) {

        String apiKey = getString(R.string.places_api_key);

        /**
         * Initialize Places. For simplicity, the API key is hard-coded. In a production
         * environment we recommend using a secure mechanism to manage API keys.
         */
        if (!Places.isInitialized()) {
            Places.initialize(context, apiKey);
        }

        // Create a new Places client instance.
        //PlacesClient placesClient = Places.createClient(context);


        putDrawableText(getResources().getString(R.string.current_location), CommunityMaterial.Icon.cmd_crosshairs_gps);

    }

    private void getLocationGP() {

        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_place_autocomplet);
        dialog.setCancelable(true);


        dialog.findViewById(R.id.change_location_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Set the fields to specify which types of place data to return.
                List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG);
                // Start the autocomplete intent.
                Intent intent = new Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.FULLSCREEN, fields)
                        .build(getActivity());
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);

                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.keep_current_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (searchParams.containsKey("latitude") && searchParams.containsKey("longitude")) {
                    searchParams.remove("latitude");
                    searchParams.remove("longitude");
                }

                putDrawableText(getResources().getString(R.string.current_location), CommunityMaterial.Icon.cmd_crosshairs_gps);

                dialog.dismiss();
            }
        });

        dialog.show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == AutocompleteActivity.RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                Log.i("CustomSearchFrag", "Place: " + place.getName() + ", " + place.getId() + ", " + place.getAddress() + ", " + place.getLatLng());
                if (place != null && place.getLatLng() != null) {
                    searchParams.put("latitude", place.getLatLng().latitude);
                    searchParams.put("longitude", place.getLatLng().longitude);
                    searchParams.put("city", place.getName());
                    putDrawableText(place.getName(), CommunityMaterial.Icon.cmd_crosshairs_gps);
                }

            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the showError.
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i("CustomSearchFrag", status.getStatusMessage());
            } else if (resultCode == AutocompleteActivity.RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }


    @SuppressLint("ResourceType")
    private void setupCategoryFilter(View view) {

        HorizontalScrollView scrollView = view.findViewById(R.id.categoryWrapper);

        LinearLayout.LayoutParams customParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        customParams.setMargins(
                0,
                (int) getResources().getDimension(R.dimen.spacing_small),
                0,
                (int) getResources().getDimension(R.dimen.spacing_small));

        LinearLayout rowBtns = new LinearLayout(getContext());
        rowBtns.setOrientation(LinearLayout.HORIZONTAL);
        rowBtns.setLayoutParams(customParams);

        //get the list of group title first
        listCats = CategoryController.getArrayList();

        //add all cat first
        Category all_categories_menu = new Category(-1,
                getContext().getString(R.string.all_categories_menu), 0, null);
        listCats.add(0, all_categories_menu);


        for (Category cat : listCats) {

            Button catBtn = new Button(getContext());
            catBtn.setPadding(
                    (int) getResources().getDimension(R.dimen.spacing_large),
                    0,
                    (int) getResources().getDimension(R.dimen.spacing_large),
                    0);

            LinearLayout.LayoutParams catBtnLP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            catBtnLP.setMargins(
                    0,
                    (int) getResources().getDimension(R.dimen.spacing_small),
                    (int) getResources().getDimension(R.dimen.spacing_small),
                    (int) getResources().getDimension(R.dimen.spacing_small)
            );
            catBtn.setLayoutParams(catBtnLP);

            catBtn.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.btn_rect_outline_white));

            int valueInPixels = (int) (getResources().getDimension(R.dimen.title_size_small) / getResources().getDisplayMetrics().density);
            catBtn.setTextSize(TypedValue.COMPLEX_UNIT_DIP, valueInPixels);
            catBtn.setTextColor(AppCompatResources.getColorStateList(getContext(), R.drawable.btn_rect_outline_text_white));
            catBtn.setText(cat.getNameCat());
            catBtn.setTag(cat.getNumCat());
            catBtn.setId(cat.getNumCat());

            catBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int i = 0; i < listCats.size(); i++) {
                        if (view.getId() == listCats.get(i).getNumCat()) {
                            getView().findViewWithTag(listCats.get(i).getNumCat()).setSelected(true);
                            CATEGORY_SELECTED = cat.getNumCat();
                        } else {
                            getView().findViewWithTag(listCats.get(i).getNumCat()).setSelected(false);
                        }
                    }
                }
            });

            rowBtns.addView(catBtn);
        }


        //Category Listener


        scrollView.addView(rowBtns);


    }


    private void putDrawableText(String text, CommunityMaterial.Icon cmd_map_marker) {

        //change text
        locationLbl.setText(text);

        //set the marker when location is changed
        Drawable locationDrawable = new IconicsDrawable(getActivity())
                .icon(cmd_map_marker)
                .color(ResourcesCompat.getColor(getActivity().getResources(), R.color.colorAccent, null))
                .sizeDp(12);

        locationLbl.setCompoundDrawables(locationDrawable, null, null, null);
        locationLbl.setCompoundDrawablePadding(8);
    }

    private void offersDiscountBtnClick() {
        for (int i = 0; i < btnOffersDiscount.length; i++) {
            btnOffersDiscount[i] = btnsOffersDiscountProps.findViewById(btn_id_discount[i]);
            btnOffersDiscount[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    searchParams.put("discount_selected_value", v.getTag());

                    switch (v.getId()) {

                        case R.id.discount_less_than_25:

                            setDiscountFocus(btn_discount_unfocus, btnOffersDiscount[0]);
                            break;

                        case R.id.discount_less_than_50:
                            setDiscountFocus(btn_discount_unfocus, btnOffersDiscount[1]);
                            break;

                        case R.id.discount_less_than_75:
                            setDiscountFocus(btn_discount_unfocus, btnOffersDiscount[2]);
                            break;

                        case R.id.discount_less_than_100:
                            setDiscountFocus(btn_discount_unfocus, btnOffersDiscount[3]);
                            break;
                    }

                }
            });
        }

        btn_discount_unfocus = btnOffersDiscount[0];
    }

    private void setDiscountFocus(Button btn_discount_unfocus, Button btn_focus) {
        if (btn_discount_unfocus.getId() == btn_focus.getId()) {
            btn_focus.setSelected(!btn_focus.isSelected());
        } else {
            btn_focus.setSelected(true);
            btn_discount_unfocus.setSelected(false);
        }
        this.btn_discount_unfocus = btn_focus;

        searchParams.put("value_type", "percent");
        searchParams.put("offer_value", btn_focus.getText().toString());
    }


    private void offersPriceBtnClick() {
        for (int i = 0; i < btnOffersPrice.length; i++) {
            btnOffersPrice[i] = btnsOffersPriceFormat.findViewById(btn_id_price[i]);
            btnOffersPrice[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //save button state
                    searchParams.put("offer_selected_value", v.getTag());

                    switch (v.getId()) {
                        case R.id.price_one_number:
                            setPriceFocus(btn_price_unfocus, btnOffersPrice[0]);
                            break;
                        case R.id.price_two_numbers:
                            setPriceFocus(btn_price_unfocus, btnOffersPrice[1]);
                            break;
                        case R.id.price_three_numbers:
                            setPriceFocus(btn_price_unfocus, btnOffersPrice[2]);
                            break;

                        case R.id.price_four_numbers:
                            setPriceFocus(btn_price_unfocus, btnOffersPrice[3]);
                            break;
                    }

                }
            });
        }

        btn_price_unfocus = btnOffersPrice[0];

    }

    @SuppressLint("ResourceType")
    private void setPriceFocus(Button btn_discount_unfocus, Button btn_focus) {
        if (btn_discount_unfocus.getId() == btn_focus.getId()) {
            btn_focus.setSelected(!btn_focus.isSelected());
        } else {
            btn_focus.setSelected(true);
            btn_discount_unfocus.setSelected(false);
        }
        this.btn_price_unfocus = btn_focus;


        searchParams.put("value_type", "price");
        searchParams.put("offer_value", btn_focus.getText().toString());

    }


    private void initParams(View view) {
        searchParams = new HashMap<>();

        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getContext());
        distance_unit = sh.getString("distance_unit", "km");
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void handleClickEventListener() {
        filterStoresBtn.setOnClickListener(this);
        filterProductsBtn.setOnClickListener(this);
        filterOffersBtn.setOnClickListener(this);
        price_offer_btn.setOnClickListener(this);
        discount_offer_btn.setOnClickListener(this);
        orderByDate.setOnClickListener(this);
        orderByGeo.setOnClickListener(this);
        dateBeginTxt.setOnClickListener(this);
        locationLbl.setOnClickListener(this);

        searchParams.put("order_by", Constances.OrderByFilter.NEARBY);


        //make geo location  section selected as a default
        orderByGeo.setSelected(true);

    }

    private void setDefaultModuleSelected(View view, String module_name) {
        if (module_name.equals(Constances.ModulesConfig.PRODUCT_MODULE)) {
            makeProductFocusable();
        } else {
            makeStoreFocusable();
        }
    }

    private void initRangeSeekBar() {


        if (mOldDistance == -1) {
            int radius = PreferenceManager.getDefaultSharedPreferences(getContext()).getInt("distance_value", 100);
            mOldDistance = radius;
        }

        String val = String.valueOf(mOldDistance);
        if (mOldDistance == 100) {
            val = "+" + mOldDistance;
        }

        @SuppressLint("StringFormatMatches") String msg = String.format(getContext().getString(R.string.settings_notification_distance_dis), val, distance_unit);

        if (!mOldValue.equals(""))
            msg = mOldValue;

        rangeSeekBar.setProgress(mOldDistance);

        range_seek_bar_text.setText(msg);
        range_seek_bar_text.setVisibility(View.VISIBLE);

        rangeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                String val = String.valueOf(progress);
                if (progress == 100) {
                    val = "+" + progress;
                }

                @SuppressLint("StringFormatMatches") String msg = String.format(getContext().getString(R.string.settings_notification_distance_dis), val, distance_unit);
                range_seek_bar_text.setText(msg);
                mOldDistance = progress;

                REQUEST_RANGE_RADIUS = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.filterStoresBtn) {
            makeStoreFocusable();
        } else if (v.getId() == R.id.filterProductsBtn) {
            makeProductFocusable();
        } else if (v.getId() == R.id.filterOffersBtn) {
            makeOfferFocusable();
        } else if (v.getId() == R.id.price_offer_btn) {
            price_offer_btn.setSelected(true);
            discount_offer_btn.setSelected(false);
            btnsOffersDiscountProps.setVisibility(View.GONE);
            btnsOffersPriceFormat.setVisibility(View.VISIBLE);
            searchParams.put("price_offer_btn", price_offer_btn.isSelected());
            searchParams.remove("discount_offer_btn");
        } else if (v.getId() == R.id.discount_offer_btn) {
            price_offer_btn.setSelected(false);
            discount_offer_btn.setSelected(true);
            btnsOffersDiscountProps.setVisibility(View.VISIBLE);
            btnsOffersPriceFormat.setVisibility(View.GONE);

            searchParams.put("discount_offer_btn", discount_offer_btn.isSelected());
            searchParams.remove("price_offer_btn");

        } /*else if (v.getId() == R.id.date_begin_txt) {
            dialogDatePickerLight(dateBeginTxt);
        }*/ else if (v.getId() == R.id.orderByGeo) {
            orderByDate.setSelected(false);
            orderByGeo.setSelected(true);
            layoutLocationChanger.setVisibility(View.VISIBLE);
            searchParams.put("order_by", Constances.OrderByFilter.NEARBY);
        } else if (v.getId() == R.id.orderByDate) {
            orderByGeo.setSelected(false);
            orderByDate.setSelected(true);
            layoutLocationChanger.setVisibility(View.GONE);
            searchParams.put("order_by", Constances.OrderByFilter.RECENT);
        } else if (v.getId() == R.id.locationLbl) {
            getLocationGP();
        }
    }

    private void makeStoreFocusable() {

        searchParams.put("module", Constances.ModulesConfig.STORE_MODULE);

        filterStoresBtn.setSelected(true);
        filterProductsBtn.setSelected(false);
        filterProductsBtn.setSelected(false);


        searchOffers.setVisibility(View.GONE);
        searchProducts.setVisibility(View.GONE);


    }

    private void makeOfferFocusable() {

        searchParams.put("module", Constances.ModulesConfig.OFFER_MODULE);


        filterStoresBtn.setSelected(false);
        filterProductsBtn.setSelected(false);
        filterOffersBtn.setSelected(true);


        price_offer_btn.setSelected(true);
        searchOffers.setVisibility(View.VISIBLE);
        searchProducts.setVisibility(View.GONE);


        btnsOffersPrice.setVisibility(View.VISIBLE);
        btnsOffersPriceFormat.setVisibility(View.VISIBLE);

        offersDiscountBtnClick();
        offersPriceBtnClick();
    }


    private void makeProductFocusable() {

        searchParams.put("module", Constances.ModulesConfig.PRODUCT_MODULE);

        filterStoresBtn.setSelected(false);
        filterProductsBtn.setSelected(true);
        filterOffersBtn.setSelected(false);

        searchProducts.setVisibility(View.VISIBLE);
        searchOffers.setVisibility(View.GONE);

    }


    private void saveCurrentSearchFields(final HashMap<String, Object> searchObj) {
        final SharedPreferences sharedPref = getInstance()
                .getSharedPreferences("searchObjPref", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("searchParams", gson.toJson(searchObj));
        editor.commit();
    }

    private HashMap<String, Object> getSavedSearchFields() {
        SharedPreferences saveSO = getInstance().getSharedPreferences("searchObjPref", Context.MODE_PRIVATE);

        if (saveSO != null) {
            Type type = new TypeToken<HashMap<String, String>>() {
            }.getType();
            Gson gson = new Gson();
            return gson.fromJson(saveSO.getString("searchParams", null), type);
        } else {
            return null;
        }
    }


    private void rangeSeekBarPrice() {


        range_price.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {

                //get currrency from appConfig API
                String defaultLocalCurrency = "$";
                Setting defaultAppSetting = SettingsController.findSettingFiled("DEFAULT_CURRENCY");
                if (defaultAppSetting != null && !defaultAppSetting.getValue().equals("")) {
                    defaultLocalCurrency = defaultAppSetting.getValue();
                }

                min_price = (int) leftValue;
                max_price = (int) rightValue;

                range_seek_indicator.setText(min_price + " - " + max_price + " " + defaultLocalCurrency);


            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }
        });

        range_price.setRange(0, 10000);
        range_price.setProgress(0, 10000);

        range_price.setMinimumHeight(1);

    }

    private Bitmap castToBitmap(Drawable drawable) {
        try {
            Bitmap bitmap;
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            // Handle the error
            return null;
        }
    }

    private void importDataFromPrefObj(final HashMap<String, Object> cacheSearchObj) {

        if (cacheSearchObj == null) {
            Toast.makeText(getActivity(), "Cache data isn't available right now", Toast.LENGTH_LONG).show();
            return;
        }

        if (AppConfig.APP_DEBUG)
            Log.e("cacheSearchObj", cacheSearchObj.toString());

        if (cacheSearchObj.containsKey("module")) {
            setDefaultModuleSelected(getView(), (String) cacheSearchObj.get("module"));
        }

        if (cacheSearchObj.containsKey("search")) {
            searchField.setText((String) cacheSearchObj.get("search"));
        }

        if (cacheSearchObj.containsKey("category")) {
            CategoryCustomView.itemCategoryselectedId = (int) cacheSearchObj.get("category");
        }

        if (cacheSearchObj.containsKey("category_selected_index")) {
            if (listCats != null && listCats.size() > 0 && (int) cacheSearchObj.get("category_selected_index") > 0)
                getView().findViewWithTag(listCats.get((int) cacheSearchObj.get("category_selected_index")).getNumCat()).setSelected(true);
        }

        if (cacheSearchObj.containsKey("radius")) {

            int radius = Integer.parseInt((String) cacheSearchObj.get("radius")) / 1000;
            REQUEST_RANGE_RADIUS = radius;
            rangeSeekBar.setProgress(radius);
            @SuppressLint("StringFormatMatches") String msg = String.format(getContext().getString(R.string.settings_notification_distance_dis), radius, distance_unit);
            range_seek_bar_text.setText(msg);
        }


        if (cacheSearchObj.containsKey("date_begin")) {
            dateBeginTxt.setText((String) cacheSearchObj.get("date_begin"));
        }


        if (cacheSearchObj.containsKey("order_by")) {
            if (cacheSearchObj.get("order_by").equals(Constances.OrderByFilter.NEARBY)) {
                orderByDate.setSelected(false);
                orderByGeo.setSelected(true);
                layoutLocationChanger.setVisibility(View.VISIBLE);
            } else if (cacheSearchObj.get("order_by").equals(Constances.OrderByFilter.RECENT)) {
                orderByGeo.setSelected(false);
                orderByDate.setSelected(true);
                layoutLocationChanger.setVisibility(View.GONE);
            }
        }


        if (cacheSearchObj.containsKey("latitude") && cacheSearchObj.containsKey("longitude")) {
            searchParams.put("latitude", cacheSearchObj.get("latitude"));
            searchParams.put("longitude", cacheSearchObj.get("longitude"));
        }

        if (cacheSearchObj.containsKey("city")) {
            searchParams.put("city", cacheSearchObj.get("city"));
            putDrawableText((String) cacheSearchObj.get("city"), CommunityMaterial.Icon.cmd_crosshairs_gps);
        }

        if (cacheSearchObj.containsKey("discount_offer_btn")) {
            discount_offer_btn.performClick();
        }

        if (cacheSearchObj.containsKey("discount_selected_value")) {
            Objects.requireNonNull(getView()).findViewWithTag(cacheSearchObj.get("discount_selected_value")).performClick();
        }

        if (cacheSearchObj.containsKey("offer_selected_value")) {
            Objects.requireNonNull(getView()).findViewWithTag(cacheSearchObj.get("offer_selected_value")).performClick();
        }

        if (cacheSearchObj.containsKey("price_offer_btn")) {
            price_offer_btn.performClick();
        }


    }


    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);

        menu.findItem(R.id.notification_action).setVisible(false);

    }
}
