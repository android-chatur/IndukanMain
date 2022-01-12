package com.geogreenapps.apps.indukaan.classes;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Droideve on 11/8/2017.
 */

public class Product extends RealmObject implements OrderableItem {


    @PrimaryKey
    private int id;
    private Currency currency;
    private int store_id;
    private int user_id;
    private int status;
    private String date_start;
    private String date_end;
    private String name;
    private String store_name;
    private Images images;
    private RealmList<Images> listImages;
    private Double distance;
    private Double lat;
    private Double lng;
    private int featured;
    private String link;
    private String description;
    private String short_description;
    private String product_type;
    private float product_value;
    private float original_value;
    private String tags;
    private int is_deal;
    private int order_enabled;
    private int qty_enabled;
    private String order_button;
    private int cf_id;
    private RealmList<CF> cf;
    private int commission;
    private int stock;
    private int is_offer;
    private RealmList<Variant> variants;
    private int parent_id;
    private int store_order_enabled;
    private int store_order_based_on_op;


    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getFeatured() {
        return featured;
    }

    public void setFeatured(int featured) {
        this.featured = featured;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public String getDate_end() {
        return date_end;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public float getProduct_value() {
        return product_value;
    }

    public void setProduct_value(float product_value) {
        this.product_value = product_value;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }


    public int getIs_deal() {
        return is_deal;
    }

    public void setIs_deal(int is_deal) {
        this.is_deal = is_deal;
    }

    public int getOrder_enabled() {
        return order_enabled;
    }

    public void setOrder_enabled(int order_enabled) {
        this.order_enabled = order_enabled;
    }

    public String getOrder_button() {
        return order_button;
    }

    public void setOrder_button(String order_button) {
        this.order_button = order_button;
    }

    public int getCf_id() {
        return cf_id;
    }

    public void setCf_id(int cf_id) {
        this.cf_id = cf_id;
    }

    public RealmList<CF> getCf() {
        return cf;
    }

    public void setCf(RealmList<CF> cf) {
        this.cf = cf;
    }

    public RealmList<Images> getListImages() {
        return listImages;
    }

    public void setListImages(RealmList<Images> listImages) {
        this.listImages = listImages;
    }

    public int getQty_enabled() {
        return qty_enabled;
    }

    public void setQty_enabled(int qty_enabled) {
        this.qty_enabled = qty_enabled;
    }


    public int getCommission() {
        return commission;
    }

    public void setCommission(int commission) {
        this.commission = commission;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getIs_offer() {
        return is_offer;
    }

    public void setIs_offer(int is_product) {
        this.is_offer = is_product;
    }

    public RealmList<Variant> getVariants() {
        return variants;
    }

    public void setVariants(RealmList<Variant> variants) {
        this.variants = variants;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public float getOriginal_value() {
        return original_value;
    }

    public void setOriginal_value(float original_value) {
        this.original_value = original_value;
    }


    public int getStore_order_enabled() {
        return store_order_enabled;
    }

    public void setStore_order_enabled(int store_order_enabled) {
        this.store_order_enabled = store_order_enabled;
    }

    public int getStore_order_based_on_op() {
        return store_order_based_on_op;
    }

    public void setStore_order_based_on_op(int store_order_based_on_op) {
        this.store_order_based_on_op = store_order_based_on_op;
    }
}
