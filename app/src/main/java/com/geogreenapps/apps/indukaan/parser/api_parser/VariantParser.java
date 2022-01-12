package com.geogreenapps.apps.indukaan.parser.api_parser;

import com.geogreenapps.apps.indukaan.classes.Variant;
import com.geogreenapps.apps.indukaan.parser.Parser;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmList;

public class VariantParser extends Parser {


    public VariantParser(JSONObject json) {
        super(json);
    }


    public RealmList<Variant> getVariants() {

        RealmList<Variant> list = new RealmList<>();

        try {

            for (int i = 0; i < json.length(); i++) {

                JSONObject json_options = json.getJSONObject(i + "");
                Variant variant = new Variant();

                variant.setGroup_id(json_options.getInt("group_id"));
                variant.setGroup_label(json_options.getString("group_label"));
                variant.setType(json_options.getString("type"));

                if (json_options.has("options") && !json_options.isNull("options")) {
                    OptionParser optionsParser = new OptionParser(new JSONObject(json_options.getString("options")));
                    variant.setOptions(optionsParser.getOptions());
                }

                if (json_options.has("currency") && !json_options.isNull("currency")) {
                    ProductCurrencyParser mProductCurrencyParser = new ProductCurrencyParser(new JSONObject(
                            json_options.getString("currency")
                    ));
                    variant.setCurrency(mProductCurrencyParser.getCurrency());
                }

                list.add(variant);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return list;
    }
}
