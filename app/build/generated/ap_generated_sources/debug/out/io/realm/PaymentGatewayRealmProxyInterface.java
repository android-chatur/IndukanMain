package io.realm;


public interface PaymentGatewayRealmProxyInterface {
    public int realmGet$id();
    public void realmSet$id(int value);
    public String realmGet$payment();
    public void realmSet$payment(String value);
    public String realmGet$image();
    public void realmSet$image(String value);
    public String realmGet$description();
    public void realmSet$description(String value);
    public RealmList<com.geogreenapps.apps.indukaan.classes.Fee> realmGet$fees();
    public void realmSet$fees(RealmList<com.geogreenapps.apps.indukaan.classes.Fee> value);
}
