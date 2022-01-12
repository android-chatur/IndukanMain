package com.geogreenapps.apps.indukaan.parser.api_parser;


import com.geogreenapps.apps.indukaan.classes.Order;
import com.geogreenapps.apps.indukaan.parser.Parser;
import com.geogreenapps.apps.indukaan.parser.tags.Tags;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmList;


public class OrderParser extends Parser {

    public OrderParser(JSONObject json) {
        super(json);
    }

    public RealmList<Order> getOrders() {

        RealmList<Order> list = new RealmList<>();

        try {

            JSONObject json_array = json.getJSONObject(Tags.RESULT);

            for (int i = 0; i < json_array.length(); i++) {


                try {
                    JSONObject json_order = json_array.getJSONObject(i + "");
                    Order order = new Order();
                    order.setId(json_order.getInt("id"));
                    order.setStatus(json_order.getString("status"));
                    order.setStatus_id(json_order.getInt("status_id"));
                    order.setDelivery_status(json_order.getInt("delivery_status"));
                    order.setName(json_order.getString("name"));
                    order.setId_store(json_order.getInt("id_store"));
                    order.setPayment_status(json_order.getString("payment_status_data"));
                    order.setUser_id(json_order.getInt("user_id"));
                    order.setReq_cf_id(json_order.getInt("req_cf_id"));
                    order.setCart(json_order.getString("cart"));
                    order.setReq_cf_data(json_order.getString("req_cf_data"));
                    order.setUpdated_at(json_order.getString("updated_at"));
                    order.setCreated_at(json_order.getString("created_at"));


                    if (json_order.has("amount") && !json_order.isNull("amount"))
                        order.setAmount(json_order.getDouble("amount"));

                    if (json_order.has("extras") && !json_order.isNull("extras"))
                        order.setExtras(json_order.getString("extras"));

                    if (json_order.has("delivery_id") && !json_order.isNull("delivery_id"))
                        order.setDelivery_id(json_order.getInt("delivery_id"));

                    if (json_order.has("timeline") && !json_order.isNull("timeline")) {
                        TimeLineParser timeLineParser = new TimeLineParser(new JSONObject(json_order.getString("timeline")));
                        order.setTimeLines(timeLineParser.getTimeLines());
                    }

                    if (!json_order.isNull("items")) {
                        ItemParser items = new ItemParser(json_order, json_order.getInt("id"));
                        order.setItems(items.getItems());
                    } else {
                        order.setItems(null);
                    }

                    list.add(order);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        } catch (
                JSONException e) {
            e.printStackTrace();
        }


        return list;
    }


}
