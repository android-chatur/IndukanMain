package com.geogreenapps.apps.indukaan.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.geogreenapps.apps.indukaan.AppController;
import com.geogreenapps.apps.indukaan.Services.NotifyDataNotificationEvent;
import com.geogreenapps.apps.indukaan.appconfig.AppConfig;
import com.geogreenapps.apps.indukaan.appconfig.AppContext;
import com.geogreenapps.apps.indukaan.appconfig.Constances;
import com.geogreenapps.apps.indukaan.classes.Module;
import com.geogreenapps.apps.indukaan.classes.Notification;
import com.geogreenapps.apps.indukaan.classes.Setting;
import com.geogreenapps.apps.indukaan.controllers.SettingsController;
import com.geogreenapps.apps.indukaan.controllers.notification.NotificationController;
import com.geogreenapps.apps.indukaan.controllers.orders.PaymentController;
import com.geogreenapps.apps.indukaan.controllers.sessions.SessionsController;
import com.geogreenapps.apps.indukaan.network.VolleySingleton;
import com.geogreenapps.apps.indukaan.network.api_request.SimpleRequest;
import com.geogreenapps.apps.indukaan.parser.ModuleParser;
import com.geogreenapps.apps.indukaan.parser.Parser;
import com.geogreenapps.apps.indukaan.parser.api_parser.NotificationParser;
import com.geogreenapps.apps.indukaan.parser.api_parser.PayGWParser;
import com.geogreenapps.apps.indukaan.parser.api_parser.SettingParser;
import com.geogreenapps.apps.indukaan.parser.tags.Tags;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.realm.RealmList;

import static com.geogreenapps.apps.indukaan.appconfig.AppConfig.APP_DEBUG;

public class CommunApiCalls {

    public static void getNotifications(final Context context, final int user_id) {

        RequestQueue queue = VolleySingleton.getInstance(context).getRequestQueue();

        SimpleRequest request = new SimpleRequest(Request.Method.POST,
                Constances.API.API_NOTIFICATIONS_GET, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    if (AppConfig.APP_DEBUG) {
                        Log.e("getNotificationResponse", response);
                    }

                    JSONObject jsonObject = new JSONObject(response);
                    // Log.e("response", jsonObject.toString());
                    final NotificationParser mNotificationParser = new NotificationParser(jsonObject);
                    int success = Integer.parseInt(mNotificationParser.getStringAttr(Tags.SUCCESS));

                    if (success == 1 && mNotificationParser.getNotifications(context).size() > 0) {
                        NotificationController.removeAll();
                        NotificationController.insertNotifications(
                                mNotificationParser.getNotifications(context)
                        );


                        //subscrib event listener to update the badge number
                        int countUnseenNotif = NotificationController.countUnseenNotifications();
                        EventBus.getDefault().post(new NotifyDataNotificationEvent("update_badges"));
                    }

                } catch (JSONException e) {
                    //send a rapport to support
                    e.printStackTrace();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (AppConfig.APP_DEBUG) {
                    Log.e("ERROR", error.toString());
                }
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("auth_type", "user");
                params.put("auth_id", String.valueOf(user_id));
                if (AppConfig.APP_DEBUG) {
                    Log.e("LoginActivity", "  params get notification :" + params.toString());
                }

                return params;
            }

        };

        request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);

    }


    public static void availableModulesAPI(Context context) {

        RequestQueue queue = VolleySingleton.getInstance(context).getRequestQueue();

        SimpleRequest request = new SimpleRequest(Request.Method.GET,
                Constances.API.API_AVAILABLE_MODULES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    if (AppContext.DEBUG)
                        Log.e("modules_manager", response);

                    JSONObject js = new JSONObject(response);

                    // Log.e("response", jsonObject.toString());
                    final ModuleParser mModuleParser = new ModuleParser(js);
                    int success = Integer.parseInt(mModuleParser.getStringAttr(Tags.SUCCESS));
                    if (success == 1) {
                        RealmList<Module> listModules = mModuleParser.getModules();
                        if (listModules.size() > 0)
                            SettingsController.updateModules(listModules);
                    }

                } catch (JSONException e) {
                    //send a rapport to support
                    e.printStackTrace();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (APP_DEBUG) {
                    Log.e("ERROR", error.toString());
                }
            }
        }) {

        };

        request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);

    }


    public static void countUnseenNotifications(final Context context) {


        Map<String, String> params = new HashMap<String, String>();

        if (SessionsController.getLocalDatabase.isLogged()) {
            params.put("user_id", String.valueOf(SessionsController.getLocalDatabase.getUserId()));
            params.put("guest_id", String.valueOf(SessionsController.getLocalDatabase.getGuestId()));
        } else {
            params.put("auth_type", "guest");
            params.put("auth_id", String.valueOf(SessionsController.getLocalDatabase.getGuestId()));
        }

        params.put("status", "0");

        RequestQueue queue = VolleySingleton.getInstance(context).getRequestQueue();

        SimpleRequest request = new SimpleRequest(Request.Method.POST,
                Constances.API.API_NOTIFICATIONS_COUNT_GET, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    if (AppConfig.APP_DEBUG) {
                        Log.e("getNotificationResponse", response);
                    }

                    JSONObject jsonObject = new JSONObject(response);
                    // Log.e("response", jsonObject.toString());
                    final Parser mNotificationParser = new Parser(jsonObject);

                    if (mNotificationParser.getSuccess() == 1) {

                        //subscrib esvent listener to update the badge number
                        int countUnseenNotif = Integer.parseInt(mNotificationParser.getStringAttr(Tags.RESULT));

                        if (AppConfig.APP_DEBUG)
                            Log.e("NotificationsCount", "unseen notification " + countUnseenNotif);

                        Notification.notificationsUnseen = countUnseenNotif;

                        //update ui
                        EventBus.getDefault().post(new NotifyDataNotificationEvent("update_badges"));
                    }

                } catch (JSONException e) {
                    //send a rapport to support
                    e.printStackTrace();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (AppConfig.APP_DEBUG) {
                    Log.e("ERROR", error.toString());
                }
            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                if (AppConfig.APP_DEBUG) {
                    Log.e("UnseenNotifCount", "  params get notification :" + params.toString());
                }

                return params;
            }

        };

        request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);

    }


    public static void appSettings() {

        RequestQueue queue = VolleySingleton.getInstance(AppController.getInstance()).getRequestQueue();

        SimpleRequest request = new SimpleRequest(Request.Method.GET,
                Constances.API.API_APP_CONFIG, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    if (AppContext.DEBUG)
                        Log.e("app_config", response);

                    JSONObject js = new JSONObject(response);

                    final SettingParser mSettingParser = new SettingParser(js);
                    int success = Integer.parseInt(mSettingParser.getStringAttr(Tags.SUCCESS));
                    if (success == 1) {
                        RealmList<Setting> appConfigs = mSettingParser.getSettings();
                        if (appConfigs.size() > 0)
                            SettingsController.
                                    updateSettings(appConfigs);
                    }

                } catch (JSONException e) {
                    //send a rapport to support
                    e.printStackTrace();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (APP_DEBUG) {
                    Log.e("ERROR", error.toString());
                }
            }
        }) {

        };

        request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);

    }


    public static void getPaymentGateway() {

        RequestQueue queue = VolleySingleton.getInstance(AppController.getInstance()).getRequestQueue();

        SimpleRequest request = new SimpleRequest(Request.Method.GET,
                Constances.API.API_PAYMENT_GATEWAY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    if (APP_DEBUG) {
                        Log.e("paymentGateways", response);
                    }

                    JSONObject jsonObject = new JSONObject(response);
                    // Log.e("response", jsonObject.toString());
                    final PayGWParser mModuleParser = new PayGWParser(jsonObject);
                    int success = Integer.parseInt(mModuleParser.getStringAttr(Tags.SUCCESS));

                    if (success == 1 && mModuleParser.getPaymentGetway().size() > 0) {
                        PaymentController.insertPaymentGatewayList(
                                mModuleParser.getPaymentGetway()
                        );
                    }

                } catch (JSONException e) {
                    //send a rapport to support
                    e.printStackTrace();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (APP_DEBUG) {
                    Log.e("ERROR", error.toString());
                }
            }
        }) {

        };

        request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);


    }


}
