package com.geogreenapps.apps.indukaan.network.api_request;


import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.geogreenapps.apps.indukaan.AppController;
import com.geogreenapps.apps.indukaan.appconfig.AppConfig;
import com.geogreenapps.apps.indukaan.appconfig.AppContext;
import com.geogreenapps.apps.indukaan.controllers.sessions.GuestController;
import com.geogreenapps.apps.indukaan.controllers.sessions.SessionsController;
import com.geogreenapps.apps.indukaan.utils.DateUtils;
import com.geogreenapps.apps.indukaan.utils.Translator;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;


public class SimpleRequest extends StringRequest {

    public static int TIME_OUT = 40000;

    public SimpleRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }


    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {

        Map<String, String> customHeader = new HashMap<>();

        try {

            customHeader = AppController.getTokens();
            customHeader.put("Language", Translator.DefaultLang);
            customHeader.put("Debug", String.valueOf(AppContext.DEBUG));
            customHeader.put("Api-key-android", AppConfig.ANDROID_API_KEY);
            customHeader.put("date", DateUtils.getUTC("yyyy-MM-dd HH:mm"));
            customHeader.put("Timezone", TimeZone.getDefault().getID());

            if (GuestController.isStored()) {
                customHeader.put("Session-Guest-Id", String.valueOf(GuestController.getGuest().getId()));
            }

            if (SessionsController.isLogged()) {
                customHeader.put("Session-User-Id", String.valueOf(SessionsController.getSession().getUser().getId()));
            }


            if (AppConfig.APP_DEBUG)
                Log.e("getHeaders", customHeader.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }


        return customHeader;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {


        return super.getParams();
    }
}