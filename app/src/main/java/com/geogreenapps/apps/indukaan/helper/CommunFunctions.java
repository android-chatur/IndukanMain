package com.geogreenapps.apps.indukaan.helper;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.geogreenapps.apps.indukaan.AppController;
import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.appconfig.AppConfig;
import com.geogreenapps.apps.indukaan.appconfig.Constances;
import com.geogreenapps.apps.indukaan.classes.Currency;
import com.geogreenapps.apps.indukaan.classes.Setting;
import com.geogreenapps.apps.indukaan.classes.User;
import com.geogreenapps.apps.indukaan.controllers.SettingsController;
import com.geogreenapps.apps.indukaan.controllers.sessions.SessionsController;
import com.geogreenapps.apps.indukaan.network.VolleySingleton;
import com.geogreenapps.apps.indukaan.network.api_request.SimpleRequest;
import com.geogreenapps.apps.indukaan.parser.api_parser.ProductCurrencyParser;
import com.geogreenapps.apps.indukaan.parser.api_parser.UserParser;
import com.geogreenapps.apps.indukaan.parser.tags.Tags;
import com.geogreenapps.apps.indukaan.utils.ProductUtils;
import com.geogreenapps.apps.indukaan.utils.Utils;
import com.geogreenapps.apps.indukaan.views.CustomDialog;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static android.widget.LinearLayout.HORIZONTAL;
import static com.geogreenapps.apps.indukaan.appconfig.AppConfig.APP_DEBUG;

public class CommunFunctions {


    public static CustomDialog showErrors(Map<String, String> errors, Context context) {
        final CustomDialog dialog = new CustomDialog(context);

        dialog.setContentView(R.layout.fragment_dialog_costum);
        dialog.setCancelable(false);


        String text = "";
        for (String key : errors.keySet()) {
            if (!text.equals(""))
                text = text + "<br>";


            text = text + "#" + errors.get(key);
        }

        Button ok = dialog.findViewById(R.id.ok);
        Button cancel = dialog.findViewById(R.id.cancel);

        TextView msgbox = dialog.findViewById(R.id.msgbox);

        if (!text.equals("")) {
            msgbox.setText(Html.fromHtml(text));
        }
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cancel.setVisibility(View.GONE);
        dialog.show();

        return dialog;

    }


    public static String convertMessages(Map<String, String> errors) {
        String text = "";
        for (String key : errors.keySet()) {
            if (!text.equals(""))
                text = text + "<br>";


            text = text + "#" + errors.get(key);
        }

        return text;
    }


    public static String createImageFile(Context contxt) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = contxt.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents

        return image.getAbsolutePath();
    }


    public static void uploadImage(final int uid, Context context, Uri imageToUpload) {

        RequestQueue queue = VolleySingleton.getInstance(context).getRequestQueue();

        Toast.makeText(context, context.getString(R.string.fileUploading), Toast.LENGTH_LONG).show();

        SimpleRequest request = new SimpleRequest(Request.Method.POST,
                Constances.API.API_USER_UPLOAD64, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                // pdialog.dismiss();
                try {

                    if (AppConfig.APP_DEBUG)
                        Log.i("EditProfile", response);
                    JSONObject js = new JSONObject(response);

                    UserParser mUserParser = new UserParser(js);
                    int success = Integer.parseInt(mUserParser.getStringAttr(Tags.SUCCESS));
                    if (success == 1) {

                        final List<User> list = mUserParser.getUser();
                        if (list.size() > 0) {
                            SessionsController.updateSession(list.get(0));
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (APP_DEBUG) {
                    Log.e("ERROR", error.toString());
                    Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                }
                //pdialog.dismiss();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                Bitmap bm = BitmapFactory.decodeFile(imageToUpload.getPath());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                byte[] b = baos.toByteArray();
                String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                params.put("image", encodedImage);

                params.put("int_id", String.valueOf(uid));
                params.put("type", "user");


                return params;
            }

        };


        request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void getDelivetStatuFromID(final TextView textView, final int delivery_status_id) {


        Context ctx = AppController.getInstance();
        switch (delivery_status_id) {
            case Constances.DELIVERY_STATUS.DELIVERED:
                textView.setText(ctx.getString(R.string.delivred));
                textView.setBackgroundTintList(ColorStateList.valueOf(ctx.getResources().getColor(R.color.green)));
                break;
            case Constances.DELIVERY_STATUS.ONGOING:
                textView.setText(ctx.getString(R.string.ongoing));
                textView.setBackgroundTintList(ColorStateList.valueOf(ctx.getResources().getColor(android.R.color.holo_blue_dark)));
                break;
            case Constances.DELIVERY_STATUS.PICKED_UP:
                textView.setText(ctx.getString(R.string.picked_up));
                textView.setBackgroundTintList(ColorStateList.valueOf(ctx.getResources().getColor(android.R.color.holo_blue_light)));
                break;
            case Constances.DELIVERY_STATUS.REPORTED:
                textView.setText(ctx.getString(R.string.reported));
                textView.setBackgroundTintList(ColorStateList.valueOf(ctx.getResources().getColor(R.color.red)));
                break;
            default:
                textView.setText(ctx.getString(R.string.pending));
                textView.setBackgroundTintList(ColorStateList.valueOf(ctx.getResources().getColor(android.R.color.holo_orange_dark)));
                break;
        }

    }


    public static BitmapDescriptor BitmapFromVector(int vectorResId) {
        // below line is use to generate a drawable.
        Drawable vectorDrawable = ContextCompat.getDrawable(AppController.getInstance(), vectorResId);
        vectorDrawable.setColorFilter(AppController.getInstance().getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        // below line is use to set bounds to our vector drawable.
        vectorDrawable.setBounds(0, 0, Utils.dpToPx(60) /* vectorDrawable.getIntrinsicWidth()*/, /*vectorDrawable.getIntrinsicHeight() */ Utils.dpToPx(60));

        // below line is use to create a bitmap for our
        // drawable which we have added.
        Bitmap bitmap = Bitmap.createBitmap(/*vectorDrawable.getIntrinsicWidth()*/ Utils.dpToPx(60), /*vectorDrawable.getIntrinsicHeight()*/ Utils.dpToPx(60), Bitmap.Config.ARGB_8888);

        // below line is use to add bitmap in our canvas.
        Canvas canvas = new Canvas(bitmap);

        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable.draw(canvas);

        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }


    public static float parseExtraFees(LinearLayout view, String fees, Currency currency) throws JSONException {

        float extraFees = 0;

        JSONObject jsonObject = new JSONObject(fees.trim());
        Iterator<String> keys = jsonObject.keys();

        view.removeAllViews();

        while (keys.hasNext()) {
            String key = keys.next();

            LinearLayout total_price_layout = new LinearLayout(view.getContext());
            total_price_layout.setOrientation(HORIZONTAL);
            LinearLayout.LayoutParams layout_379 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout_379.gravity = ConstraintLayout.LayoutParams.END;
            total_price_layout.setLayoutParams(layout_379);

            TextView textView_951 = new TextView(view.getContext());
            textView_951.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
            textView_951.setTypeface(textView_951.getTypeface(), Typeface.ITALIC);
            textView_951.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);

            LinearLayout.LayoutParams layout_335 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout_335.weight = 1;
            layout_335.leftMargin = (int) view.getContext().getResources().getDimension(R.dimen.spacing_middle);
            textView_951.setLayoutParams(layout_335);
            total_price_layout.addView(textView_951);


            TextView total_price_items = new TextView(view.getContext());
            total_price_items.setTypeface(total_price_items.getTypeface(), Typeface.ITALIC);
            total_price_items.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
            total_price_items.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
            LinearLayout.LayoutParams layout_991 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout_991.leftMargin = (int) view.getContext().getResources().getDimension(R.dimen.spacing_middle);

            total_price_items.setLayoutParams(layout_991);

            total_price_layout.addView(total_price_items);

            //dynamic content
            textView_951.setText(Utils.capitalizeString(key.replace("_", " ")));
            total_price_items.setText(ProductUtils.parseCurrencyFormat(Float.valueOf(jsonObject.get(key).toString()), currency));


            view.addView(total_price_layout);


            extraFees += Float.valueOf(jsonObject.get(key).toString());


        }


        return extraFees;

    }


    public static Currency getDefaultCurrency() {
        Currency defaulCurrency = null;

        Setting defaultAppSetting = SettingsController.findSettingFiled("CURRENCY_OBJECT");
        if (defaultAppSetting.getValue() != null && defaultAppSetting.get_type().equalsIgnoreCase("string")) {

            ProductCurrencyParser mProductCurrencyParser = null;
            try {
                mProductCurrencyParser = new ProductCurrencyParser(new JSONObject(defaultAppSetting.getValue()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            defaulCurrency = mProductCurrencyParser.getCurrency();

        }


        return defaulCurrency;

    }


}
