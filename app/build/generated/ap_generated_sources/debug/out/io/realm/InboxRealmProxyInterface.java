package io.realm;


public interface InboxRealmProxyInterface {
    public String realmGet$discussionId();
    public void realmSet$discussionId(String value);
    public com.geogreenapps.apps.indukaan.classes.User realmGet$senderUser();
    public void realmSet$senderUser(com.geogreenapps.apps.indukaan.classes.User value);
    public int realmGet$receiverId();
    public void realmSet$receiverId(int value);
    public RealmList<com.geogreenapps.apps.indukaan.classes.Message> realmGet$messages();
    public void realmSet$messages(RealmList<com.geogreenapps.apps.indukaan.classes.Message> value);
    public String realmGet$createdAt();
    public void realmSet$createdAt(String value);
    public int realmGet$status();
    public void realmSet$status(int value);
    public boolean realmGet$isSystem();
    public void realmSet$isSystem(boolean value);
}
