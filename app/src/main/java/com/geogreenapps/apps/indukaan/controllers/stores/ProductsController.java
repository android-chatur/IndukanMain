package com.geogreenapps.apps.indukaan.controllers.stores;

import com.geogreenapps.apps.indukaan.classes.Product;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Droideve on 11/12/2017.
 */

public class ProductsController {


    public static Product findProductById(int id) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(Product.class).equalTo("id", id).findFirst();
    }

    public static RealmResults<Product> findProductsByStoreId(int id) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Product> result = realm.where(Product.class).equalTo("store_id", id).findAllSorted("id", Sort.DESCENDING);
        /*List<Product> array = new ArrayList<>();
        array.addAll(result.subList(0, result.size()));*/
        return result;
    }

    public static void deleteAllProducts(int id) {
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<Product> result = realm.where(Product.class).equalTo("store_id", id).findAllSorted("id", Sort.DESCENDING);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                result.deleteAllFromRealm();
            }
        });
    }

    public static boolean insertProducts(final RealmList<Product> list) {

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (Product product : list) {
                    realm.copyToRealmOrUpdate(product);
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
                    RealmResults<Product> result = realm.where(Product.class).findAll();
                    result.deleteAllFromRealm();

                }
            });
        }

    }

}
