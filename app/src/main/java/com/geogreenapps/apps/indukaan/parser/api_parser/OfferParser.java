package com.geogreenapps.apps.indukaan.parser.api_parser;


import com.geogreenapps.apps.indukaan.classes.Images;
import com.geogreenapps.apps.indukaan.classes.Offer;
import com.geogreenapps.apps.indukaan.parser.Parser;
import com.geogreenapps.apps.indukaan.parser.tags.Tags;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmList;


public class OfferParser extends Parser {

    public OfferParser(JSONObject json) {
        super(json);
    }

    public RealmList<Offer> getOffers() {

        RealmList<Offer> list = new RealmList<Offer>();

        try {

            JSONObject json_array = json.getJSONObject(Tags.RESULT);

            for (int i = 0; i < json_array.length(); i++) {

                try {
                    JSONObject json_product = json_array.getJSONObject(i + "");
                    Offer offer = new Offer();
                    offer.setId(json_product.getInt("id_product"));
                    offer.setName(json_product.getString("name"));
                    offer.setDate_end(json_product.getString("date_end"));
                    offer.setDate_start(json_product.getString("date_start"));
                    offer.setStatus(json_product.getInt("status"));
                    offer.setStore_id(json_product.getInt("store_id"));
                    offer.setStore_name(json_product.getString("store_name"));
                    offer.setDistance(json_product.getDouble("distance"));
                    offer.setDescription(json_product.getString("description"));
                    offer.setProduct_value((float) json_product.getDouble("product_value"));
                    offer.setDescription(json_product.getString("description"));
                    offer.setShort_description(json_product.getString("short_description"));
                    offer.setProduct_type(json_product.getString("product_type"));
                    offer.setLat(json_product.getDouble("latitude"));
                    offer.setLng(json_product.getDouble("longitude"));
                    offer.setLink(json_product.getString("link"));
                    offer.setCommission(json_product.getInt("commission"));
                    offer.setIs_offer(json_product.getInt("is_offer"));
                    offer.setStock(json_product.getInt("stock"));


                    if (json_product.has("featured") && !json_product.isNull("featured")) {
                        offer.setFeatured(json_product.getInt("featured"));
                    }

                    if (json_product.has("is_deal") && !json_product.isNull("is_deal"))
                        offer.setIs_deal(json_product.getInt("is_deal"));

                    if (json_product.has("cf_id") && !json_product.isNull("cf_id"))
                        offer.setCf_id(json_product.getInt("cf_id"));

                    if (json_product.has("order_enabled") && !json_product.isNull("order_enabled"))
                        offer.setOrder_enabled(json_product.getInt("order_enabled"));


                    try {
                        offer.setQty_enabled(json_product.getInt("qty_enabled"));
                    } catch (Exception e) {
                        offer.setQty_enabled(1);
                    }


                    if (json_product.has("order_button") && !json_product.isNull("order_button"))
                        offer.setOrder_button(json_product.getString("order_button"));

                    if (json_product.has("cf") && !json_product.isNull("cf")) {
                        ProductCFParser mProductCurrencyParser = new ProductCFParser(new JSONObject(json_product.getString("cf")));
                        offer.setCf(mProductCurrencyParser.getCFs());
                    }

                    if (json_product.has("currency") && !json_product.isNull("currency")) {
                        ProductCurrencyParser mOfferCurrencyParser = new ProductCurrencyParser(new JSONObject(
                                json_product.getString("currency")
                        ));
                        offer.setCurrency(mOfferCurrencyParser.getCurrency());
                    }


                    if (json_product.has("variants") && !json_product.isNull("variants")) {
                        VariantParser variantsParser = new VariantParser(new JSONObject(json_product.getString("variants")));
                        offer.setVariants(variantsParser.getVariants());
                    }

                    if (json_product.has("images") && !json_product.isNull("images")) {
                        String jsonValues = "";

                        try {
                            jsonValues = json_product.getJSONObject("images").toString();
                            JSONObject jsonObject = new JSONObject(jsonValues);
                            ImagesParser imgp = new ImagesParser(jsonObject);

                            if (imgp.getImagesList().size() > 0) {
                                offer.setListImages(imgp.getImagesList());
                                offer.setImages(imgp.getImagesList().get(0));
                            }

                        } catch (JSONException jex) {
                            offer.setListImages(new RealmList<Images>());
                        }

                    }


                    list.add(offer);
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
