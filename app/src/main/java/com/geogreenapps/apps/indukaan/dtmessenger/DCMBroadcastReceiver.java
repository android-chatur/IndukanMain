package com.geogreenapps.apps.indukaan.dtmessenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.geogreenapps.apps.indukaan.GPS.GPStracker;
import com.geogreenapps.apps.indukaan.appconfig.AppConfig;
import com.geogreenapps.apps.indukaan.appconfig.Constances;
import com.geogreenapps.apps.indukaan.classes.Guest;
import com.geogreenapps.apps.indukaan.controllers.sessions.GuestController;
import com.geogreenapps.apps.indukaan.fragments.SettingsFragment;
import com.geogreenapps.apps.indukaan.network.ServiceHandler;
import com.geogreenapps.apps.indukaan.network.VolleySingleton;
import com.geogreenapps.apps.indukaan.network.api_request.SimpleRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.geogreenapps.apps.indukaan.appconfig.AppConfig.APP_DEBUG;

/**
 * Created by Droideve on 7/24/2016.
 */

public class DCMBroadcastReceiver extends BroadcastReceiver {

    protected List<NetworkStateReceiverListener> listeners;
    protected Boolean connected;
    private String Message;
    private RequestQueue queue;


    public DCMBroadcastReceiver() {
        listeners = new ArrayList<NetworkStateReceiverListener>();
        connected = null;
    }

    @Override
    public void onReceive(final Context context, final Intent intent) {

        if (intent == null || intent.getExtras() == null)
            return;

        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                runBgApp(context, intent);
            }
        }, 5000);

    }

    private void runBgApp(Context context, Intent intent) {

        if (AppConfig.APP_DEBUG)
            Log.e("DCMBroadcastReceiver", "changed-->" + ServiceHandler.isNetworkAvailable(context));

        if (ServiceHandler.isNetworkAvailable(context)) {

            //refresh position
            if (GuestController.isStored()) {
                refreshPositionGuest(GuestController.getGuest(), context);
            }

            //get nearby stores
            if (SettingsFragment.isNotifyNearTrue(context)) {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                boolean Notif_NearBy = sh.getBoolean("notif_nearby_stores", true);
                if (Notif_NearBy) {
                    // getNearStore(context);
                }
            }

            // the thread
            boolean notif_upcomingevent = PreferenceManager.getDefaultSharedPreferences(context)
                    .getBoolean("notif_upcomingevent", false);



            connected = true;

        } else if (intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, Boolean.FALSE)) {

            connected = false;
        }
    }

    private void notifyState(NetworkStateReceiverListener listener) {
        if (connected == null || listener == null)
            return;

        if (connected == true) {
            listener.networkAvailable();
        } else {
            listener.networkUnavailable();
        }
    }

    public void addListener(NetworkStateReceiverListener l) {
        listeners.add(l);
        notifyState(l);
    }

    private void refreshPositionGuest(final Guest mGuest, final Context context) {


        GPStracker gps = new GPStracker(context);
        if (mGuest != null && gps.canGetLocation()) {

            queue = VolleySingleton.getInstance(context).getRequestQueue();

            final int user_id = mGuest.getId();
            final double lat = gps.getLatitude();
            final double lng = gps.getLongitude();

            SimpleRequest request = new SimpleRequest(Request.Method.POST,
                    Constances.API.API_REFRESH_POSITION, new Response.Listener<String>() {
                @Override
                public void onResponse(final String response) {

                    Log.e("response", response);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("ERROR", error.toString());

                }
            }) {

                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();

                    params.put("guest_id", String.valueOf(user_id));
                    params.put("lat", String.valueOf(lat));
                    params.put("lng", String.valueOf(lng));

                    if (APP_DEBUG)
                        Log.e("onRefreshSync", params.toString());

                    return params;
                }

            };


            request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            queue.add(request);

        }
    }

    public interface NetworkStateReceiverListener {
        void networkAvailable();

        void networkUnavailable();
    }


}
