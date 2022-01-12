package com.geogreenapps.apps.indukaan.parser.api_parser;


import com.geogreenapps.apps.indukaan.classes.Currency;
import com.geogreenapps.apps.indukaan.parser.Parser;

import org.json.JSONException;
import org.json.JSONObject;


public class ProductCurrencyParser extends Parser {

    public ProductCurrencyParser(JSONObject json) {
        super(json);
    }

    public Currency getCurrency() {

        Currency mCurrency = new Currency();

        try {


            mCurrency.setId((int) json.getDouble("id"));
            mCurrency.setCode(json.getString("code"));
            mCurrency.setSymbol(json.getString("symbol"));
            mCurrency.setName(json.getString("name"));
            mCurrency.setFormat((int) json.getDouble("format"));
            mCurrency.setRate((int) json.getDouble("rate"));

            mCurrency.setCfd((int) json.getDouble("cfd"));
            mCurrency.setCdp(json.getString("cdp"));
            mCurrency.setCts(json.getString("cts"));

            return mCurrency;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }


}
