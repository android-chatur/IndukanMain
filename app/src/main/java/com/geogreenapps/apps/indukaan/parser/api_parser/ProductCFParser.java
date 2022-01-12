package com.geogreenapps.apps.indukaan.parser.api_parser;


import com.geogreenapps.apps.indukaan.classes.CF;
import com.geogreenapps.apps.indukaan.parser.Parser;
import com.geogreenapps.apps.indukaan.parser.tags.Tags;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmList;


public class ProductCFParser extends Parser {

    public ProductCFParser(JSONObject json) {
        super(json);
    }


    public RealmList<CF> getCFs() {
        RealmList<CF> list = new RealmList<CF>();

        try {

            JSONObject jsonResult = this.json.getJSONObject(Tags.RESULT);


            for (int i = 0; i < jsonResult.length(); i++) {

                JSONObject jsonRow = jsonResult.getJSONObject(String.valueOf(i));


                if (jsonRow.has("fields") && !jsonRow.isNull("fields")) {

                    JSONArray fieldsArray = new JSONArray(jsonRow.getString("fields"));
                    for (int j = 0; j < fieldsArray.length(); j++) {
                        CF mCF = new CF();
                        JSONObject field = fieldsArray.getJSONObject(j);
                        mCF.setLabel(field.getString("label"));
                        mCF.setRequired(field.getInt("required"));
                        mCF.setStep(field.getInt("step"));
                        mCF.setOrder(field.getInt("order"));
                        mCF.setType(field.getString("type"));
                        list.add(mCF);
                    }
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return list;

    }


}
