package com.geogreenapps.apps.indukaan.classes;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Droideve on 8/1/2016.
 */

public class Session extends RealmObject {


    @PrimaryKey
    private int sessionId;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

}
