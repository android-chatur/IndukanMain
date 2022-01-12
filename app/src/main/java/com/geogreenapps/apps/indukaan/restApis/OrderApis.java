package com.geogreenapps.apps.indukaan.restApis;

import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.geogreenapps.apps.indukaan.AppController;
import com.geogreenapps.apps.indukaan.appconfig.AppConfig;
import com.geogreenapps.apps.indukaan.appconfig.Constances;
import com.geogreenapps.apps.indukaan.classes.Store;
import com.geogreenapps.apps.indukaan.classes.User;
import com.geogreenapps.apps.indukaan.network.ServiceHandler;
import com.geogreenapps.apps.indukaan.network.VolleySingleton;
import com.geogreenapps.apps.indukaan.network.api_request.SimpleRequest;
import com.geogreenapps.apps.indukaan.parser.api_parser.StoreParser;
import com.geogreenapps.apps.indukaan.parser.api_parser.UserParser;
import com.geogreenapps.apps.indukaan.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.realm.RealmList;

import static com.geogreenapps.apps.indukaan.appconfig.AppConfig.APP_DEBUG;

public class OrderApis {


    private final RequestQueue queue;
    public OrderRestAPisDelegate delegate;

    public OrderApis() {
        queue = VolleySingleton.getInstance(AppController.getInstance()).getRequestQueue();
    }

    public static OrderApis newInstance() {
        return new OrderApis();
    }


    public interface OrderRestAPisDelegate {
        void onStoreSuccess(Store storeData);

        void onCustomerSuccess(User userData);

        void onOrderUpdate(JSONObject jsonObject);

        void onError(OrderApis object, Map<String, String> errors);
    }


    public void getStoreDetail(final HashMap<String, String> _params) {

        SimpleRequest request = new SimpleRequest(Request.Method.POST,
                Constances.API.API_USER_GET_STORES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    if (AppConfig.APP_DEBUG) {
                        Log.e("responseStoresString", response);
                    }

                    JSONObject jsonObject = new JSONObject(response);
                    final StoreParser mStoreParser = new StoreParser(jsonObject);
                    RealmList<Store> list = mStoreParser.getStore();

                    if (mStoreParser.getSuccess() == 1) {
                        if (delegate != null && list.size() > 0)
                            delegate.onStoreSuccess(list.get(0));

                    } else {
                        if (delegate != null)
                            delegate.onError(OrderApis.this, mStoreParser.getErrors());
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

                if (_params != null && !_params.isEmpty()) {
                    for (Map.Entry<String, String> entry : _params.entrySet()) {
                        params.put(entry.getKey(), entry.getValue());
                    }
                }


                return params;
            }

        };


        request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);


    }


    public void getUserDetail(final HashMap<String, String> _params) {


        SimpleRequest request = new SimpleRequest(Request.Method.POST,
                Constances.API.API_GET_USERS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    if (APP_DEBUG) {
                        Log.e("responseUsersString", response);
                    }

                    JSONObject jsonObject = new JSONObject(response);

                    final UserParser mUsersParser = new UserParser(jsonObject);

                    if (mUsersParser.getSuccess() == 1) {
                        if (delegate != null && mUsersParser.getUser().size() > 0)
                            delegate.onCustomerSuccess(mUsersParser.getUser().first());

                    } else {
                        if (delegate != null)
                            delegate.onError(OrderApis.this, mUsersParser.getErrors());
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
                }
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("token", Utils.getToken(AppController.getInstance()));
                params.put("mac_adr", ServiceHandler.getMacAddr());
                params.put("limit", "1");
                params.put("page", "1");

                if (_params != null && !_params.isEmpty()) {
                    for (Map.Entry<String, String> entry : _params.entrySet()) {
                        params.put(entry.getKey(), entry.getValue());
                    }
                }

                return params;
            }

        };

        request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);


    }


    public void updateOrderStatus(final HashMap<String, String> _params) {


        SimpleRequest request = new SimpleRequest(Request.Method.POST,
                Constances.API.API_UPDATE_ORDER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (delegate != null)
                        delegate.onOrderUpdate(jsonObject);

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

                if (_params != null && !_params.isEmpty()) {
                    for (Map.Entry<String, String> entry : _params.entrySet()) {
                        params.put(entry.getKey(), entry.getValue());
                    }
                }

                if (AppConfig.APP_DEBUG) {
                    Log.e("Update_order", "  params :" + params.toString());
                }

                return params;
            }

        };

        request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);

    }


    /*public static void updateOrderStatus( final HashMap<String, String> listParams) {

        RequestQueue queue = VolleySingleton.getInstance(AppController.getInstance()).getRequestQueue();

        SimpleRequest request = new SimpleRequest(Request.Method.POST,
                Constances.API.API_UPDATE_ORDER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);


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

                if (listParams != null && !listParams.isEmpty()) {
                    for (Map.Entry<String, String> entry : listParams.entrySet()) {
                        params.put(entry.getKey(), entry.getValue());
                    }
                }

                if (AppConfig.APP_DEBUG) {
                    Log.e("Update_order", "  params :" + params.toString());
                }

                return params;
            }

        };

        request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);

    }*/


}
