package com.geogreenapps.apps.indukaan.controllers.orders;

import com.geogreenapps.apps.indukaan.classes.Order;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Droideve on 11/12/2017.
 */

public class OrdersController {


    public static Order findOrderById(int id) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Order.class).equalTo("id", id).findFirst();
    }


    public static boolean insertOrders(final RealmList<Order> list) {

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (Order order : list) {
                    realm.copyToRealmOrUpdate(order);
                }
            }
        });
        return true;
    }


    public static void removeAll() {
        Realm realm = Realm.getDefaultInstance();
        if (realm.isInTransaction()) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults<Order> result = realm.where(Order.class).findAll();
                    result.deleteAllFromRealm();
                }
            });
        }

    }

}
