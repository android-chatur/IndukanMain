package com.geogreenapps.apps.indukaan.parser.api_parser;


import com.geogreenapps.apps.indukaan.classes.Images;
import com.geogreenapps.apps.indukaan.classes.Product;
import com.geogreenapps.apps.indukaan.parser.Parser;
import com.geogreenapps.apps.indukaan.parser.tags.Tags;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmList;


public class ProductParser extends Parser {

    public ProductParser(JSONObject json) {
        super(json);
    }

    public RealmList<Product> getProducts() {

        RealmList<Product> list = new RealmList<Product>();

        try {

            JSONObject json_array = json.getJSONObject(Tags.RESULT);

            for (int i = 0; i < json_array.length(); i++) {

                try {
                    JSONObject json_product = json_array.getJSONObject(i + "");
                    Product product = new Product();

                    product.setId(json_product.getInt("id_product"));
                    product.setName(json_product.getString("name"));
                    product.setDate_end(json_product.getString("date_end"));
                    product.setDate_start(json_product.getString("date_start"));
                    product.setStatus(json_product.getInt("status"));
                    product.setStore_id(json_product.getInt("store_id"));
                    product.setStore_name(json_product.getString("store_name"));
                    product.setDistance(json_product.getDouble("distance"));
                    product.setProduct_value((float) json_product.getDouble("product_value"));
                    product.setDescription(json_product.getString("description"));
                    product.setShort_description(json_product.getString("short_description"));
                    product.setProduct_type(json_product.getString("product_type"));
                    product.setLat(json_product.getDouble("latitude"));
                    product.setLng(json_product.getDouble("longitude"));
                    product.setLink(json_product.getString("link"));
                    product.setCommission(json_product.getInt("commission"));
                    product.setIs_offer(json_product.getInt("is_offer"));
                    product.setStock(json_product.getInt("stock"));


                    if (json_product.has("original_value") && !json_product.isNull("original_value"))
                        product.setOriginal_value((float) json_product.getDouble("original_value"));


                    if (json_product.has("featured") && !json_product.isNull("featured")) {
                        product.setFeatured(json_product.getInt("featured"));
                    }

                    if (json_product.has("is_deal") && !json_product.isNull("is_deal"))
                        product.setIs_deal(json_product.getInt("is_deal"));

                    if (json_product.has("cf_id") && !json_product.isNull("cf_id"))
                        product.setCf_id(json_product.getInt("cf_id"));


                    if (json_product.has("parent_id") && !json_product.isNull("parent_id"))
                        product.setParent_id(json_product.getInt("parent_id"));


                    if (json_product.has("order_enabled") && !json_product.isNull("order_enabled"))
                        product.setOrder_enabled(json_product.getInt("order_enabled"));


                    try {
                        product.setQty_enabled(json_product.getInt("qty_enabled"));
                    } catch (Exception e) {
                        product.setQty_enabled(1);
                    }


                    if (json_product.has("order_button") && !json_product.isNull("order_button"))
                        product.setOrder_button(json_product.getString("order_button"));

                    if (json_product.has("cf") && !json_product.isNull("cf")) {
                        ProductCFParser mProductCurrencyParser = new ProductCFParser(new JSONObject(json_product.getString("cf")));
                        product.setCf(mProductCurrencyParser.getCFs());
                    }

                    if (json_product.has("currency") && !json_product.isNull("currency")) {
                        ProductCurrencyParser mProductCurrencyParser = new ProductCurrencyParser(new JSONObject(
                                json_product.getString("currency")
                        ));
                        product.setCurrency(mProductCurrencyParser.getCurrency());
                    }


                    if (json_product.has("variants") && !json_product.isNull("variants")) {
                        VariantParser variantsParser = new VariantParser(new JSONObject(json_product.getString("variants")));
                        product.setVariants(variantsParser.getVariants());
                    }

                    if (json_product.has("images") && !json_product.isNull("images")) {
                        String jsonValues = "";

                        try {
                            jsonValues = json_product.getJSONObject("images").toString();
                            JSONObject jsonObject = new JSONObject(jsonValues);
                            ImagesParser imgp = new ImagesParser(jsonObject);

                            if (imgp.getImagesList().size() > 0) {
                                product.setListImages(imgp.getImagesList());
                                product.setImages(imgp.getImagesList().get(0));
                            }

                        } catch (JSONException jex) {
                            product.setListImages(new RealmList<Images>());
                        }

                    }


                    if (json_product.has("store_order_enabled") && !json_product.isNull("store_order_enabled"))
                        product.setStore_order_enabled(json_product.getInt("store_order_enabled"));

                    if (json_product.has("store_order_based_on_op") && !json_product.isNull("store_order_based_on_op"))
                        product.setStore_order_based_on_op(json_product.getInt("store_order_based_on_op"));


                    list.add(product);
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
