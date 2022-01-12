package com.geogreenapps.apps.indukaan.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.activities.ProductDetailActivity;
import com.geogreenapps.apps.indukaan.adapter.StoreProductsAdapter;
import com.geogreenapps.apps.indukaan.appconfig.Constances;
import com.geogreenapps.apps.indukaan.classes.Product;
import com.geogreenapps.apps.indukaan.controllers.stores.ProductsController;
import com.geogreenapps.apps.indukaan.network.VolleySingleton;
import com.geogreenapps.apps.indukaan.network.api_request.SimpleRequest;
import com.geogreenapps.apps.indukaan.parser.api_parser.ProductParser;
import com.geogreenapps.apps.indukaan.utils.DateUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import io.realm.RealmList;
import io.realm.RealmResults;

import static com.geogreenapps.apps.indukaan.appconfig.AppConfig.APP_DEBUG;


public class StoreProductsFragment extends Fragment {

    private int store_id;

    private LinearLayout emptyLayout;
    private LinearLayout loadingLayout;
    private LinearLayout containerLayout;
    private RealmResults<Product> listProducts;
    private String current_date;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        current_date = DateUtils.getUTC("yyyy-MM-dd H:m:s");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_store_products, container, false);


        emptyLayout = rootView.findViewById(R.id.emptyLayout);
        loadingLayout = rootView.findViewById(R.id.loadingLayout);
        containerLayout = rootView.findViewById(R.id.container);


        try {
            store_id = getArguments().getInt("store_id", 0);
        } catch (Exception e) {
            return rootView;
        }

        reloadProducts(rootView);

        //do update from server
        getProducts(rootView);


        return rootView;

    }


    private void reloadProducts(View rootView) {

        listProducts = ProductsController.findProductsByStoreId(store_id);


        if (listProducts.size() > 0) {

            containerLayout.setVisibility(View.VISIBLE);
            loadingLayout.setVisibility(View.GONE);
            emptyLayout.setVisibility(View.GONE);

            StoreProductsAdapter
                    .newInstance(getActivity()).load(listProducts)
                    .inflate(R.layout.item_store_product)
                    .into(rootView.findViewById(R.id.container)).setOnistener(new StoreProductsAdapter.Listener() {
                @Override
                public void onProductClicked(int position) {

                    Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                    intent.putExtra("product_id", listProducts.get(position).getId());
                    startActivity(intent);

                    if (APP_DEBUG)
                        Toast.makeText(getActivity(), "t" + position, Toast.LENGTH_LONG).show();
                }
            });

        } else {

            containerLayout.setVisibility(View.GONE);
            loadingLayout.setVisibility(View.GONE);
            emptyLayout.setVisibility(View.GONE);

        }

    }


    public void getProducts(final View rootView) {

        RequestQueue queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();


        if (listProducts.size() == 0) {
            containerLayout.setVisibility(View.GONE);
            loadingLayout.setVisibility(View.VISIBLE);
            emptyLayout.setVisibility(View.GONE);
        }

        SimpleRequest request = new SimpleRequest(Request.Method.POST,
                Constances.API.API_GET_PRODUCTS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    if (APP_DEBUG) {
                        Log.e("responseProductsString", response);
                    }


                    JSONObject jsonObject = new JSONObject(response);
                    final ProductParser mProductParser = new ProductParser(jsonObject);

                    RealmList<Product> list = mProductParser.getProducts();
                    ProductsController.deleteAllProducts(store_id);

                    if (list.size() > 0) {

                        ProductsController.insertProducts(list);
                        reloadProducts(rootView);

                        containerLayout.setVisibility(View.VISIBLE);
                        loadingLayout.setVisibility(View.GONE);
                        emptyLayout.setVisibility(View.GONE);

                    } else {

                        containerLayout.setVisibility(View.GONE);
                        loadingLayout.setVisibility(View.GONE);
                        emptyLayout.setVisibility(View.VISIBLE);

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

                containerLayout.setVisibility(View.GONE);
                loadingLayout.setVisibility(View.GONE);
                emptyLayout.setVisibility(View.VISIBLE);
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("store_id", String.valueOf(store_id));
                params.put("page", "1");
                params.put("limit", "7");
                params.put("date", current_date);
                params.put("timezone", TimeZone.getDefault().getID());

                if (APP_DEBUG) {
                    Log.e("ListStoreProductsFrags", "  params getProducts :" + params.toString());
                }
                return params;
            }

        };


        request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);


    }


}
