package com.geogreenapps.apps.indukaan.classes;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

public class Order extends RealmObject {


    @Ignore
    public boolean expanded = false;
    @Ignore
    public boolean parent = false;
    @Ignore
    public boolean swiped = false; // flag when item swiped
    @PrimaryKey
    private int id;
    private int user_id;
    private int module_id;
    private int id_store;
    private String name;
    private int req_cf_id;
    private int delivery_status;
    private int delivery_id;
    private int status_id;
    private String status;
    private String module;
    private String cart;
    private String req_cf_data;
    private String updated_at;
    private String created_at;
    private String payment_status;
    private String commission;
    private String payment_status_data;
    private RealmList<Item> items;
    private RealmList<TimeLine> timeLines;
    private double amount;
    private RealmList<Variant> variants;
    private String extras;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCart() {
        return cart;
    }

    public void setCart(String cart) {
        this.cart = cart;
    }

    public String getReq_cf_data() {
        return req_cf_data;
    }

    public void setReq_cf_data(String req_cf_data) {
        this.req_cf_data = req_cf_data;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public RealmList<Item> getItems() {
        return items;
    }

    public void setItems(RealmList<Item> items) {
        this.items = items;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    public int getModule_id() {
        return module_id;
    }

    public void setModule_id(int module_id) {
        this.module_id = module_id;
    }

    public int getId_store() {
        return id_store;
    }

    public void setId_store(int id_store) {
        this.id_store = id_store;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReq_cf_id() {
        return req_cf_id;
    }

    public void setReq_cf_id(int req_cf_id) {
        this.req_cf_id = req_cf_id;
    }


    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getPayment_status_data() {
        return payment_status_data;
    }

    public void setPayment_status_data(String payment_status_data) {
        this.payment_status_data = payment_status_data;
    }

    public RealmList<Variant> getVariants() {
        return variants;
    }

    public void setVariants(RealmList<Variant> variants) {
        this.variants = variants;
    }

    public int getDelivery_status() {
        return delivery_status;
    }

    public void setDelivery_status(int delivery_status) {
        this.delivery_status = delivery_status;
    }

    public RealmList<TimeLine> getTimeLines() {
        return timeLines;
    }

    public void setTimeLines(RealmList<TimeLine> timeLines) {
        this.timeLines = timeLines;
    }

    public int getDelivery_id() {
        return delivery_id;
    }

    public void setDelivery_id(int delivery_id) {
        this.delivery_id = delivery_id;
    }


    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }
}
