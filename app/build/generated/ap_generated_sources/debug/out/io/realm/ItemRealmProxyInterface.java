package io.realm;


public interface ItemRealmProxyInterface {
    public String realmGet$id();
    public void realmSet$id(String value);
    public String realmGet$name();
    public void realmSet$name(String value);
    public String realmGet$image();
    public void realmSet$image(String value);
    public String realmGet$module();
    public void realmSet$module(String value);
    public int realmGet$qty();
    public void realmSet$qty(int value);
    public double realmGet$amount();
    public void realmSet$amount(double value);
    public com.geogreenapps.apps.indukaan.classes.Currency realmGet$currency();
    public void realmSet$currency(com.geogreenapps.apps.indukaan.classes.Currency value);
}
