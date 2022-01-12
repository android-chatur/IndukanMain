package com.geogreenapps.apps.indukaan.parser.api_parser;


import com.geogreenapps.apps.indukaan.classes.Item;
import com.geogreenapps.apps.indukaan.parser.Parser;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmList;


public class ItemParser extends Parser {

    int order_id;

    public ItemParser(JSONObject json, int _order_id) {
        super(json);

        order_id = _order_id;
    }

    public RealmList<Item> getItems() {

        RealmList<Item> list = new RealmList<>();

        try {

            JSONObject json_array = json.getJSONObject("items");


            for (int i = 0; i < json_array.length(); i++) {

                try {
                    JSONObject json_user = json_array.getJSONObject(i + "");
                    Item item = new Item();
                    item.setId(order_id + "_" + json_user.getInt("id"));
                    item.setName(json_user.getString("name"));
                    item.setModule(json_user.getString("module"));
                    item.setAmount(json_user.getDouble("amount"));
                    item.setImage(json_user.getString("image"));
                    item.setQty(json_user.getInt("qty"));

                    if (json_user.has("currency") && !json_user.isNull("currency")) {
                        ProductCurrencyParser mProductCurrencyParser = new ProductCurrencyParser(new JSONObject(
                                json_user.getString("currency")
                        ));
                        item.setCurrency(mProductCurrencyParser.getCurrency());

                    }
                    list.add(item);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return list;
    }


}
