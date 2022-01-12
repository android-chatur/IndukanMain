package com.geogreenapps.apps.indukaan.classes;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Driver {

    public String driverId;
    public double lng;
    public double lat;

    public Driver() {
    }

    public Driver(double lng, double lat) {
        this.lng = lng;
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "Driver{" +
                ", lng=" + lng +
                ", lat=" + lat +
                '}';
    }
}
