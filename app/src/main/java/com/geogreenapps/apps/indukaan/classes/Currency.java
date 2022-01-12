package com.geogreenapps.apps.indukaan.classes;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Currency extends RealmObject {

    @PrimaryKey
    private int id;
    private int status;
    private String code;
    private String symbol;
    private String name;
    private int format;
    private int rate;
    private int cfd;
    private String cdp;
    private String cts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFormat() {
        return format;
    }

    public void setFormat(int format) {
        this.format = format;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getCfd() {
        return cfd;
    }

    public void setCfd(int cfd) {
        this.cfd = cfd;
    }

    public String getCdp() {
        return cdp;
    }

    public void setCdp(String cdp) {
        this.cdp = cdp;
    }

    public String getCts() {
        return cts;
    }

    public void setCts(String cts) {
        this.cts = cts;
    }
}
