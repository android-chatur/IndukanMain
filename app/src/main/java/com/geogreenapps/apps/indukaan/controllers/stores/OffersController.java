package com.geogreenapps.apps.indukaan.controllers.stores;

import com.geogreenapps.apps.indukaan.classes.Offer;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Droideve on 11/12/2017.
 */

public class OffersController {


    public static Offer findOfferById(int id) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Offer.class).equalTo("id", id).findFirst();
    }

    public static RealmResults<Offer> findOffersByStoreId(int id) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Offer> result = realm.where(Offer.class).equalTo("store_id", id).findAllSorted("id", Sort.DESCENDING);
        return result;
    }

    public static void deleteAllOffers(int id) {
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<Offer> result = realm.where(Offer.class).equalTo("store_id", id).findAllSorted("id", Sort.DESCENDING);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                result.deleteAllFromRealm();
            }
        });
    }

    public static boolean insertOrUpdateOffers(final RealmList<Offer> list) {

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (Offer offer : list) {
                    realm.copyToRealmOrUpdate(offer);
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
                    RealmResults<Offer> result = realm.where(Offer.class).findAll();
                    result.deleteAllFromRealm();

                }
            });
        }

    }

}
