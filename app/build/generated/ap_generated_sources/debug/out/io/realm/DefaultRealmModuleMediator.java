package io.realm;


import android.util.JsonReader;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.RealmProxyMediator;
import io.realm.internal.Row;
import io.realm.internal.SharedRealm;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

@io.realm.annotations.RealmModule
class DefaultRealmModuleMediator extends RealmProxyMediator {

    private static final Set<Class<? extends RealmModel>> MODEL_CLASSES;
    static {
        Set<Class<? extends RealmModel>> modelClasses = new HashSet<Class<? extends RealmModel>>(30);
        modelClasses.add(com.geogreenapps.apps.indukaan.classes.Category.class);
        modelClasses.add(com.geogreenapps.apps.indukaan.classes.Currency.class);
        modelClasses.add(com.geogreenapps.apps.indukaan.classes.Variant.class);
        modelClasses.add(com.geogreenapps.apps.indukaan.classes.Store.class);
        modelClasses.add(com.geogreenapps.apps.indukaan.classes.Notification.class);
        modelClasses.add(com.geogreenapps.apps.indukaan.classes.Option.class);
        modelClasses.add(com.geogreenapps.apps.indukaan.classes.SavedStores.class);
        modelClasses.add(com.geogreenapps.apps.indukaan.classes.CF.class);
        modelClasses.add(com.geogreenapps.apps.indukaan.classes.Bookmark.class);
        modelClasses.add(com.geogreenapps.apps.indukaan.classes.User.class);
        modelClasses.add(com.geogreenapps.apps.indukaan.classes.Review.class);
        modelClasses.add(com.geogreenapps.apps.indukaan.classes.TimeLine.class);
        modelClasses.add(com.geogreenapps.apps.indukaan.classes.Cart.class);
        modelClasses.add(com.geogreenapps.apps.indukaan.classes.Product.class);
        modelClasses.add(com.geogreenapps.apps.indukaan.classes.PaymentGateway.class);
        modelClasses.add(com.geogreenapps.apps.indukaan.classes.Discussion.class);
        modelClasses.add(com.geogreenapps.apps.indukaan.classes.Item.class);
        modelClasses.add(com.geogreenapps.apps.indukaan.classes.Setting.class);
        modelClasses.add(com.geogreenapps.apps.indukaan.classes.Inbox.class);
        modelClasses.add(com.geogreenapps.apps.indukaan.classes.Order.class);
        modelClasses.add(com.geogreenapps.apps.indukaan.classes.Session.class);
        modelClasses.add(com.geogreenapps.apps.indukaan.classes.Offer.class);
        modelClasses.add(com.geogreenapps.apps.indukaan.classes.CountriesModel.class);
        modelClasses.add(com.geogreenapps.apps.indukaan.classes.Images.class);
        modelClasses.add(com.geogreenapps.apps.indukaan.classes.Message.class);
        modelClasses.add(com.geogreenapps.apps.indukaan.classes.Guest.class);
        modelClasses.add(com.geogreenapps.apps.indukaan.classes.Banner.class);
        modelClasses.add(com.geogreenapps.apps.indukaan.classes.OpeningTime.class);
        modelClasses.add(com.geogreenapps.apps.indukaan.classes.Fee.class);
        modelClasses.add(com.geogreenapps.apps.indukaan.classes.Module.class);
        MODEL_CLASSES = Collections.unmodifiableSet(modelClasses);
    }

    @Override
    public Map<Class<? extends RealmModel>, OsObjectSchemaInfo> getExpectedObjectSchemaInfoMap() {
        Map<Class<? extends RealmModel>, OsObjectSchemaInfo> infoMap = new HashMap<Class<? extends RealmModel>, OsObjectSchemaInfo>(30);
        infoMap.put(com.geogreenapps.apps.indukaan.classes.Category.class, io.realm.CategoryRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.geogreenapps.apps.indukaan.classes.Currency.class, io.realm.CurrencyRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.geogreenapps.apps.indukaan.classes.Variant.class, io.realm.VariantRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.geogreenapps.apps.indukaan.classes.Store.class, io.realm.StoreRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.geogreenapps.apps.indukaan.classes.Notification.class, io.realm.NotificationRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.geogreenapps.apps.indukaan.classes.Option.class, io.realm.OptionRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.geogreenapps.apps.indukaan.classes.SavedStores.class, io.realm.SavedStoresRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.geogreenapps.apps.indukaan.classes.CF.class, io.realm.CFRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.geogreenapps.apps.indukaan.classes.Bookmark.class, io.realm.BookmarkRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.geogreenapps.apps.indukaan.classes.User.class, io.realm.UserRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.geogreenapps.apps.indukaan.classes.Review.class, io.realm.ReviewRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.geogreenapps.apps.indukaan.classes.TimeLine.class, io.realm.TimeLineRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.geogreenapps.apps.indukaan.classes.Cart.class, io.realm.CartRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.geogreenapps.apps.indukaan.classes.Product.class, io.realm.ProductRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.geogreenapps.apps.indukaan.classes.PaymentGateway.class, io.realm.PaymentGatewayRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.geogreenapps.apps.indukaan.classes.Discussion.class, io.realm.DiscussionRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.geogreenapps.apps.indukaan.classes.Item.class, io.realm.ItemRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.geogreenapps.apps.indukaan.classes.Setting.class, io.realm.SettingRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.geogreenapps.apps.indukaan.classes.Inbox.class, io.realm.InboxRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.geogreenapps.apps.indukaan.classes.Order.class, io.realm.OrderRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.geogreenapps.apps.indukaan.classes.Session.class, io.realm.SessionRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.geogreenapps.apps.indukaan.classes.Offer.class, io.realm.OfferRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.geogreenapps.apps.indukaan.classes.CountriesModel.class, io.realm.CountriesModelRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.geogreenapps.apps.indukaan.classes.Images.class, io.realm.ImagesRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.geogreenapps.apps.indukaan.classes.Message.class, io.realm.MessageRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.geogreenapps.apps.indukaan.classes.Guest.class, io.realm.GuestRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.geogreenapps.apps.indukaan.classes.Banner.class, io.realm.BannerRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.geogreenapps.apps.indukaan.classes.OpeningTime.class, io.realm.OpeningTimeRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.geogreenapps.apps.indukaan.classes.Fee.class, io.realm.FeeRealmProxy.getExpectedObjectSchemaInfo());
        infoMap.put(com.geogreenapps.apps.indukaan.classes.Module.class, io.realm.ModuleRealmProxy.getExpectedObjectSchemaInfo());
        return infoMap;
    }

    @Override
    public ColumnInfo createColumnInfo(Class<? extends RealmModel> clazz, OsSchemaInfo schemaInfo) {
        checkClass(clazz);

        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Category.class)) {
            return io.realm.CategoryRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Currency.class)) {
            return io.realm.CurrencyRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Variant.class)) {
            return io.realm.VariantRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Store.class)) {
            return io.realm.StoreRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Notification.class)) {
            return io.realm.NotificationRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Option.class)) {
            return io.realm.OptionRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.SavedStores.class)) {
            return io.realm.SavedStoresRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.CF.class)) {
            return io.realm.CFRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Bookmark.class)) {
            return io.realm.BookmarkRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.User.class)) {
            return io.realm.UserRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Review.class)) {
            return io.realm.ReviewRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.TimeLine.class)) {
            return io.realm.TimeLineRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Cart.class)) {
            return io.realm.CartRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Product.class)) {
            return io.realm.ProductRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.PaymentGateway.class)) {
            return io.realm.PaymentGatewayRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Discussion.class)) {
            return io.realm.DiscussionRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Item.class)) {
            return io.realm.ItemRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Setting.class)) {
            return io.realm.SettingRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Inbox.class)) {
            return io.realm.InboxRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Order.class)) {
            return io.realm.OrderRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Session.class)) {
            return io.realm.SessionRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Offer.class)) {
            return io.realm.OfferRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.CountriesModel.class)) {
            return io.realm.CountriesModelRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Images.class)) {
            return io.realm.ImagesRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Message.class)) {
            return io.realm.MessageRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Guest.class)) {
            return io.realm.GuestRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Banner.class)) {
            return io.realm.BannerRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.OpeningTime.class)) {
            return io.realm.OpeningTimeRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Fee.class)) {
            return io.realm.FeeRealmProxy.createColumnInfo(schemaInfo);
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Module.class)) {
            return io.realm.ModuleRealmProxy.createColumnInfo(schemaInfo);
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public List<String> getFieldNames(Class<? extends RealmModel> clazz) {
        checkClass(clazz);

        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Category.class)) {
            return io.realm.CategoryRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Currency.class)) {
            return io.realm.CurrencyRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Variant.class)) {
            return io.realm.VariantRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Store.class)) {
            return io.realm.StoreRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Notification.class)) {
            return io.realm.NotificationRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Option.class)) {
            return io.realm.OptionRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.SavedStores.class)) {
            return io.realm.SavedStoresRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.CF.class)) {
            return io.realm.CFRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Bookmark.class)) {
            return io.realm.BookmarkRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.User.class)) {
            return io.realm.UserRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Review.class)) {
            return io.realm.ReviewRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.TimeLine.class)) {
            return io.realm.TimeLineRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Cart.class)) {
            return io.realm.CartRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Product.class)) {
            return io.realm.ProductRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.PaymentGateway.class)) {
            return io.realm.PaymentGatewayRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Discussion.class)) {
            return io.realm.DiscussionRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Item.class)) {
            return io.realm.ItemRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Setting.class)) {
            return io.realm.SettingRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Inbox.class)) {
            return io.realm.InboxRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Order.class)) {
            return io.realm.OrderRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Session.class)) {
            return io.realm.SessionRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Offer.class)) {
            return io.realm.OfferRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.CountriesModel.class)) {
            return io.realm.CountriesModelRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Images.class)) {
            return io.realm.ImagesRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Message.class)) {
            return io.realm.MessageRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Guest.class)) {
            return io.realm.GuestRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Banner.class)) {
            return io.realm.BannerRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.OpeningTime.class)) {
            return io.realm.OpeningTimeRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Fee.class)) {
            return io.realm.FeeRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Module.class)) {
            return io.realm.ModuleRealmProxy.getFieldNames();
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public String getSimpleClassNameImpl(Class<? extends RealmModel> clazz) {
        checkClass(clazz);

        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Category.class)) {
            return io.realm.CategoryRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Currency.class)) {
            return io.realm.CurrencyRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Variant.class)) {
            return io.realm.VariantRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Store.class)) {
            return io.realm.StoreRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Notification.class)) {
            return io.realm.NotificationRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Option.class)) {
            return io.realm.OptionRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.SavedStores.class)) {
            return io.realm.SavedStoresRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.CF.class)) {
            return io.realm.CFRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Bookmark.class)) {
            return io.realm.BookmarkRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.User.class)) {
            return io.realm.UserRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Review.class)) {
            return io.realm.ReviewRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.TimeLine.class)) {
            return io.realm.TimeLineRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Cart.class)) {
            return io.realm.CartRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Product.class)) {
            return io.realm.ProductRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.PaymentGateway.class)) {
            return io.realm.PaymentGatewayRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Discussion.class)) {
            return io.realm.DiscussionRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Item.class)) {
            return io.realm.ItemRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Setting.class)) {
            return io.realm.SettingRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Inbox.class)) {
            return io.realm.InboxRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Order.class)) {
            return io.realm.OrderRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Session.class)) {
            return io.realm.SessionRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Offer.class)) {
            return io.realm.OfferRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.CountriesModel.class)) {
            return io.realm.CountriesModelRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Images.class)) {
            return io.realm.ImagesRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Message.class)) {
            return io.realm.MessageRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Guest.class)) {
            return io.realm.GuestRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Banner.class)) {
            return io.realm.BannerRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.OpeningTime.class)) {
            return io.realm.OpeningTimeRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Fee.class)) {
            return io.realm.FeeRealmProxy.getSimpleClassName();
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Module.class)) {
            return io.realm.ModuleRealmProxy.getSimpleClassName();
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> E newInstance(Class<E> clazz, Object baseRealm, Row row, ColumnInfo columnInfo, boolean acceptDefaultValue, List<String> excludeFields) {
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        try {
            objectContext.set((BaseRealm) baseRealm, row, columnInfo, acceptDefaultValue, excludeFields);
            checkClass(clazz);

            if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Category.class)) {
                return clazz.cast(new io.realm.CategoryRealmProxy());
            }
            if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Currency.class)) {
                return clazz.cast(new io.realm.CurrencyRealmProxy());
            }
            if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Variant.class)) {
                return clazz.cast(new io.realm.VariantRealmProxy());
            }
            if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Store.class)) {
                return clazz.cast(new io.realm.StoreRealmProxy());
            }
            if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Notification.class)) {
                return clazz.cast(new io.realm.NotificationRealmProxy());
            }
            if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Option.class)) {
                return clazz.cast(new io.realm.OptionRealmProxy());
            }
            if (clazz.equals(com.geogreenapps.apps.indukaan.classes.SavedStores.class)) {
                return clazz.cast(new io.realm.SavedStoresRealmProxy());
            }
            if (clazz.equals(com.geogreenapps.apps.indukaan.classes.CF.class)) {
                return clazz.cast(new io.realm.CFRealmProxy());
            }
            if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Bookmark.class)) {
                return clazz.cast(new io.realm.BookmarkRealmProxy());
            }
            if (clazz.equals(com.geogreenapps.apps.indukaan.classes.User.class)) {
                return clazz.cast(new io.realm.UserRealmProxy());
            }
            if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Review.class)) {
                return clazz.cast(new io.realm.ReviewRealmProxy());
            }
            if (clazz.equals(com.geogreenapps.apps.indukaan.classes.TimeLine.class)) {
                return clazz.cast(new io.realm.TimeLineRealmProxy());
            }
            if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Cart.class)) {
                return clazz.cast(new io.realm.CartRealmProxy());
            }
            if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Product.class)) {
                return clazz.cast(new io.realm.ProductRealmProxy());
            }
            if (clazz.equals(com.geogreenapps.apps.indukaan.classes.PaymentGateway.class)) {
                return clazz.cast(new io.realm.PaymentGatewayRealmProxy());
            }
            if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Discussion.class)) {
                return clazz.cast(new io.realm.DiscussionRealmProxy());
            }
            if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Item.class)) {
                return clazz.cast(new io.realm.ItemRealmProxy());
            }
            if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Setting.class)) {
                return clazz.cast(new io.realm.SettingRealmProxy());
            }
            if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Inbox.class)) {
                return clazz.cast(new io.realm.InboxRealmProxy());
            }
            if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Order.class)) {
                return clazz.cast(new io.realm.OrderRealmProxy());
            }
            if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Session.class)) {
                return clazz.cast(new io.realm.SessionRealmProxy());
            }
            if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Offer.class)) {
                return clazz.cast(new io.realm.OfferRealmProxy());
            }
            if (clazz.equals(com.geogreenapps.apps.indukaan.classes.CountriesModel.class)) {
                return clazz.cast(new io.realm.CountriesModelRealmProxy());
            }
            if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Images.class)) {
                return clazz.cast(new io.realm.ImagesRealmProxy());
            }
            if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Message.class)) {
                return clazz.cast(new io.realm.MessageRealmProxy());
            }
            if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Guest.class)) {
                return clazz.cast(new io.realm.GuestRealmProxy());
            }
            if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Banner.class)) {
                return clazz.cast(new io.realm.BannerRealmProxy());
            }
            if (clazz.equals(com.geogreenapps.apps.indukaan.classes.OpeningTime.class)) {
                return clazz.cast(new io.realm.OpeningTimeRealmProxy());
            }
            if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Fee.class)) {
                return clazz.cast(new io.realm.FeeRealmProxy());
            }
            if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Module.class)) {
                return clazz.cast(new io.realm.ModuleRealmProxy());
            }
            throw getMissingProxyClassException(clazz);
        } finally {
            objectContext.clear();
        }
    }

    @Override
    public Set<Class<? extends RealmModel>> getModelClasses() {
        return MODEL_CLASSES;
    }

    @Override
    public <E extends RealmModel> E copyOrUpdate(Realm realm, E obj, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Category.class)) {
            return clazz.cast(io.realm.CategoryRealmProxy.copyOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Category) obj, update, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Currency.class)) {
            return clazz.cast(io.realm.CurrencyRealmProxy.copyOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Currency) obj, update, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Variant.class)) {
            return clazz.cast(io.realm.VariantRealmProxy.copyOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Variant) obj, update, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Store.class)) {
            return clazz.cast(io.realm.StoreRealmProxy.copyOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Store) obj, update, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Notification.class)) {
            return clazz.cast(io.realm.NotificationRealmProxy.copyOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Notification) obj, update, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Option.class)) {
            return clazz.cast(io.realm.OptionRealmProxy.copyOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Option) obj, update, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.SavedStores.class)) {
            return clazz.cast(io.realm.SavedStoresRealmProxy.copyOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.SavedStores) obj, update, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.CF.class)) {
            return clazz.cast(io.realm.CFRealmProxy.copyOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.CF) obj, update, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Bookmark.class)) {
            return clazz.cast(io.realm.BookmarkRealmProxy.copyOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Bookmark) obj, update, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.User.class)) {
            return clazz.cast(io.realm.UserRealmProxy.copyOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.User) obj, update, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Review.class)) {
            return clazz.cast(io.realm.ReviewRealmProxy.copyOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Review) obj, update, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.TimeLine.class)) {
            return clazz.cast(io.realm.TimeLineRealmProxy.copyOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.TimeLine) obj, update, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Cart.class)) {
            return clazz.cast(io.realm.CartRealmProxy.copyOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Cart) obj, update, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Product.class)) {
            return clazz.cast(io.realm.ProductRealmProxy.copyOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Product) obj, update, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.PaymentGateway.class)) {
            return clazz.cast(io.realm.PaymentGatewayRealmProxy.copyOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.PaymentGateway) obj, update, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Discussion.class)) {
            return clazz.cast(io.realm.DiscussionRealmProxy.copyOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Discussion) obj, update, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Item.class)) {
            return clazz.cast(io.realm.ItemRealmProxy.copyOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Item) obj, update, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Setting.class)) {
            return clazz.cast(io.realm.SettingRealmProxy.copyOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Setting) obj, update, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Inbox.class)) {
            return clazz.cast(io.realm.InboxRealmProxy.copyOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Inbox) obj, update, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Order.class)) {
            return clazz.cast(io.realm.OrderRealmProxy.copyOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Order) obj, update, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Session.class)) {
            return clazz.cast(io.realm.SessionRealmProxy.copyOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Session) obj, update, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Offer.class)) {
            return clazz.cast(io.realm.OfferRealmProxy.copyOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Offer) obj, update, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.CountriesModel.class)) {
            return clazz.cast(io.realm.CountriesModelRealmProxy.copyOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.CountriesModel) obj, update, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Images.class)) {
            return clazz.cast(io.realm.ImagesRealmProxy.copyOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Images) obj, update, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Message.class)) {
            return clazz.cast(io.realm.MessageRealmProxy.copyOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Message) obj, update, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Guest.class)) {
            return clazz.cast(io.realm.GuestRealmProxy.copyOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Guest) obj, update, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Banner.class)) {
            return clazz.cast(io.realm.BannerRealmProxy.copyOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Banner) obj, update, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.OpeningTime.class)) {
            return clazz.cast(io.realm.OpeningTimeRealmProxy.copyOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.OpeningTime) obj, update, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Fee.class)) {
            return clazz.cast(io.realm.FeeRealmProxy.copyOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Fee) obj, update, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Module.class)) {
            return clazz.cast(io.realm.ModuleRealmProxy.copyOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Module) obj, update, cache));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public void insert(Realm realm, RealmModel object, Map<RealmModel, Long> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Category.class)) {
            io.realm.CategoryRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Category) object, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Currency.class)) {
            io.realm.CurrencyRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Currency) object, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Variant.class)) {
            io.realm.VariantRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Variant) object, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Store.class)) {
            io.realm.StoreRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Store) object, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Notification.class)) {
            io.realm.NotificationRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Notification) object, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Option.class)) {
            io.realm.OptionRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Option) object, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.SavedStores.class)) {
            io.realm.SavedStoresRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.SavedStores) object, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.CF.class)) {
            io.realm.CFRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.CF) object, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Bookmark.class)) {
            io.realm.BookmarkRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Bookmark) object, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.User.class)) {
            io.realm.UserRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.User) object, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Review.class)) {
            io.realm.ReviewRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Review) object, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.TimeLine.class)) {
            io.realm.TimeLineRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.TimeLine) object, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Cart.class)) {
            io.realm.CartRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Cart) object, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Product.class)) {
            io.realm.ProductRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Product) object, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.PaymentGateway.class)) {
            io.realm.PaymentGatewayRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.PaymentGateway) object, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Discussion.class)) {
            io.realm.DiscussionRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Discussion) object, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Item.class)) {
            io.realm.ItemRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Item) object, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Setting.class)) {
            io.realm.SettingRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Setting) object, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Inbox.class)) {
            io.realm.InboxRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Inbox) object, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Order.class)) {
            io.realm.OrderRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Order) object, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Session.class)) {
            io.realm.SessionRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Session) object, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Offer.class)) {
            io.realm.OfferRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Offer) object, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.CountriesModel.class)) {
            io.realm.CountriesModelRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.CountriesModel) object, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Images.class)) {
            io.realm.ImagesRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Images) object, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Message.class)) {
            io.realm.MessageRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Message) object, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Guest.class)) {
            io.realm.GuestRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Guest) object, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Banner.class)) {
            io.realm.BannerRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Banner) object, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.OpeningTime.class)) {
            io.realm.OpeningTimeRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.OpeningTime) object, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Fee.class)) {
            io.realm.FeeRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Fee) object, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Module.class)) {
            io.realm.ModuleRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Module) object, cache);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public void insert(Realm realm, Collection<? extends RealmModel> objects) {
        Iterator<? extends RealmModel> iterator = objects.iterator();
        RealmModel object = null;
        Map<RealmModel, Long> cache = new HashMap<RealmModel, Long>(objects.size());
        if (iterator.hasNext()) {
            //  access the first element to figure out the clazz for the routing below
            object = iterator.next();
            // This cast is correct because obj is either
            // generated by RealmProxy or the original type extending directly from RealmObject
            @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

            if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Category.class)) {
                io.realm.CategoryRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Category) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Currency.class)) {
                io.realm.CurrencyRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Currency) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Variant.class)) {
                io.realm.VariantRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Variant) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Store.class)) {
                io.realm.StoreRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Store) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Notification.class)) {
                io.realm.NotificationRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Notification) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Option.class)) {
                io.realm.OptionRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Option) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.SavedStores.class)) {
                io.realm.SavedStoresRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.SavedStores) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.CF.class)) {
                io.realm.CFRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.CF) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Bookmark.class)) {
                io.realm.BookmarkRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Bookmark) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.User.class)) {
                io.realm.UserRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.User) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Review.class)) {
                io.realm.ReviewRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Review) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.TimeLine.class)) {
                io.realm.TimeLineRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.TimeLine) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Cart.class)) {
                io.realm.CartRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Cart) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Product.class)) {
                io.realm.ProductRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Product) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.PaymentGateway.class)) {
                io.realm.PaymentGatewayRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.PaymentGateway) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Discussion.class)) {
                io.realm.DiscussionRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Discussion) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Item.class)) {
                io.realm.ItemRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Item) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Setting.class)) {
                io.realm.SettingRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Setting) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Inbox.class)) {
                io.realm.InboxRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Inbox) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Order.class)) {
                io.realm.OrderRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Order) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Session.class)) {
                io.realm.SessionRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Session) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Offer.class)) {
                io.realm.OfferRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Offer) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.CountriesModel.class)) {
                io.realm.CountriesModelRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.CountriesModel) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Images.class)) {
                io.realm.ImagesRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Images) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Message.class)) {
                io.realm.MessageRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Message) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Guest.class)) {
                io.realm.GuestRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Guest) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Banner.class)) {
                io.realm.BannerRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Banner) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.OpeningTime.class)) {
                io.realm.OpeningTimeRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.OpeningTime) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Fee.class)) {
                io.realm.FeeRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Fee) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Module.class)) {
                io.realm.ModuleRealmProxy.insert(realm, (com.geogreenapps.apps.indukaan.classes.Module) object, cache);
            } else {
                throw getMissingProxyClassException(clazz);
            }
            if (iterator.hasNext()) {
                if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Category.class)) {
                    io.realm.CategoryRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Currency.class)) {
                    io.realm.CurrencyRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Variant.class)) {
                    io.realm.VariantRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Store.class)) {
                    io.realm.StoreRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Notification.class)) {
                    io.realm.NotificationRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Option.class)) {
                    io.realm.OptionRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.SavedStores.class)) {
                    io.realm.SavedStoresRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.CF.class)) {
                    io.realm.CFRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Bookmark.class)) {
                    io.realm.BookmarkRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.User.class)) {
                    io.realm.UserRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Review.class)) {
                    io.realm.ReviewRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.TimeLine.class)) {
                    io.realm.TimeLineRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Cart.class)) {
                    io.realm.CartRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Product.class)) {
                    io.realm.ProductRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.PaymentGateway.class)) {
                    io.realm.PaymentGatewayRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Discussion.class)) {
                    io.realm.DiscussionRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Item.class)) {
                    io.realm.ItemRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Setting.class)) {
                    io.realm.SettingRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Inbox.class)) {
                    io.realm.InboxRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Order.class)) {
                    io.realm.OrderRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Session.class)) {
                    io.realm.SessionRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Offer.class)) {
                    io.realm.OfferRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.CountriesModel.class)) {
                    io.realm.CountriesModelRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Images.class)) {
                    io.realm.ImagesRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Message.class)) {
                    io.realm.MessageRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Guest.class)) {
                    io.realm.GuestRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Banner.class)) {
                    io.realm.BannerRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.OpeningTime.class)) {
                    io.realm.OpeningTimeRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Fee.class)) {
                    io.realm.FeeRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Module.class)) {
                    io.realm.ModuleRealmProxy.insert(realm, iterator, cache);
                } else {
                    throw getMissingProxyClassException(clazz);
                }
            }
        }
    }

    @Override
    public void insertOrUpdate(Realm realm, RealmModel obj, Map<RealmModel, Long> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Category.class)) {
            io.realm.CategoryRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Category) obj, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Currency.class)) {
            io.realm.CurrencyRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Currency) obj, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Variant.class)) {
            io.realm.VariantRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Variant) obj, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Store.class)) {
            io.realm.StoreRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Store) obj, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Notification.class)) {
            io.realm.NotificationRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Notification) obj, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Option.class)) {
            io.realm.OptionRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Option) obj, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.SavedStores.class)) {
            io.realm.SavedStoresRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.SavedStores) obj, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.CF.class)) {
            io.realm.CFRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.CF) obj, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Bookmark.class)) {
            io.realm.BookmarkRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Bookmark) obj, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.User.class)) {
            io.realm.UserRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.User) obj, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Review.class)) {
            io.realm.ReviewRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Review) obj, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.TimeLine.class)) {
            io.realm.TimeLineRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.TimeLine) obj, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Cart.class)) {
            io.realm.CartRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Cart) obj, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Product.class)) {
            io.realm.ProductRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Product) obj, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.PaymentGateway.class)) {
            io.realm.PaymentGatewayRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.PaymentGateway) obj, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Discussion.class)) {
            io.realm.DiscussionRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Discussion) obj, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Item.class)) {
            io.realm.ItemRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Item) obj, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Setting.class)) {
            io.realm.SettingRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Setting) obj, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Inbox.class)) {
            io.realm.InboxRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Inbox) obj, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Order.class)) {
            io.realm.OrderRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Order) obj, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Session.class)) {
            io.realm.SessionRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Session) obj, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Offer.class)) {
            io.realm.OfferRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Offer) obj, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.CountriesModel.class)) {
            io.realm.CountriesModelRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.CountriesModel) obj, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Images.class)) {
            io.realm.ImagesRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Images) obj, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Message.class)) {
            io.realm.MessageRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Message) obj, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Guest.class)) {
            io.realm.GuestRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Guest) obj, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Banner.class)) {
            io.realm.BannerRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Banner) obj, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.OpeningTime.class)) {
            io.realm.OpeningTimeRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.OpeningTime) obj, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Fee.class)) {
            io.realm.FeeRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Fee) obj, cache);
        } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Module.class)) {
            io.realm.ModuleRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Module) obj, cache);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public void insertOrUpdate(Realm realm, Collection<? extends RealmModel> objects) {
        Iterator<? extends RealmModel> iterator = objects.iterator();
        RealmModel object = null;
        Map<RealmModel, Long> cache = new HashMap<RealmModel, Long>(objects.size());
        if (iterator.hasNext()) {
            //  access the first element to figure out the clazz for the routing below
            object = iterator.next();
            // This cast is correct because obj is either
            // generated by RealmProxy or the original type extending directly from RealmObject
            @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

            if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Category.class)) {
                io.realm.CategoryRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Category) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Currency.class)) {
                io.realm.CurrencyRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Currency) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Variant.class)) {
                io.realm.VariantRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Variant) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Store.class)) {
                io.realm.StoreRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Store) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Notification.class)) {
                io.realm.NotificationRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Notification) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Option.class)) {
                io.realm.OptionRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Option) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.SavedStores.class)) {
                io.realm.SavedStoresRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.SavedStores) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.CF.class)) {
                io.realm.CFRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.CF) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Bookmark.class)) {
                io.realm.BookmarkRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Bookmark) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.User.class)) {
                io.realm.UserRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.User) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Review.class)) {
                io.realm.ReviewRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Review) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.TimeLine.class)) {
                io.realm.TimeLineRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.TimeLine) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Cart.class)) {
                io.realm.CartRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Cart) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Product.class)) {
                io.realm.ProductRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Product) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.PaymentGateway.class)) {
                io.realm.PaymentGatewayRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.PaymentGateway) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Discussion.class)) {
                io.realm.DiscussionRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Discussion) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Item.class)) {
                io.realm.ItemRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Item) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Setting.class)) {
                io.realm.SettingRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Setting) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Inbox.class)) {
                io.realm.InboxRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Inbox) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Order.class)) {
                io.realm.OrderRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Order) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Session.class)) {
                io.realm.SessionRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Session) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Offer.class)) {
                io.realm.OfferRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Offer) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.CountriesModel.class)) {
                io.realm.CountriesModelRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.CountriesModel) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Images.class)) {
                io.realm.ImagesRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Images) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Message.class)) {
                io.realm.MessageRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Message) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Guest.class)) {
                io.realm.GuestRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Guest) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Banner.class)) {
                io.realm.BannerRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Banner) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.OpeningTime.class)) {
                io.realm.OpeningTimeRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.OpeningTime) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Fee.class)) {
                io.realm.FeeRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Fee) object, cache);
            } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Module.class)) {
                io.realm.ModuleRealmProxy.insertOrUpdate(realm, (com.geogreenapps.apps.indukaan.classes.Module) object, cache);
            } else {
                throw getMissingProxyClassException(clazz);
            }
            if (iterator.hasNext()) {
                if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Category.class)) {
                    io.realm.CategoryRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Currency.class)) {
                    io.realm.CurrencyRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Variant.class)) {
                    io.realm.VariantRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Store.class)) {
                    io.realm.StoreRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Notification.class)) {
                    io.realm.NotificationRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Option.class)) {
                    io.realm.OptionRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.SavedStores.class)) {
                    io.realm.SavedStoresRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.CF.class)) {
                    io.realm.CFRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Bookmark.class)) {
                    io.realm.BookmarkRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.User.class)) {
                    io.realm.UserRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Review.class)) {
                    io.realm.ReviewRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.TimeLine.class)) {
                    io.realm.TimeLineRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Cart.class)) {
                    io.realm.CartRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Product.class)) {
                    io.realm.ProductRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.PaymentGateway.class)) {
                    io.realm.PaymentGatewayRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Discussion.class)) {
                    io.realm.DiscussionRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Item.class)) {
                    io.realm.ItemRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Setting.class)) {
                    io.realm.SettingRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Inbox.class)) {
                    io.realm.InboxRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Order.class)) {
                    io.realm.OrderRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Session.class)) {
                    io.realm.SessionRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Offer.class)) {
                    io.realm.OfferRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.CountriesModel.class)) {
                    io.realm.CountriesModelRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Images.class)) {
                    io.realm.ImagesRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Message.class)) {
                    io.realm.MessageRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Guest.class)) {
                    io.realm.GuestRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Banner.class)) {
                    io.realm.BannerRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.OpeningTime.class)) {
                    io.realm.OpeningTimeRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Fee.class)) {
                    io.realm.FeeRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Module.class)) {
                    io.realm.ModuleRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else {
                    throw getMissingProxyClassException(clazz);
                }
            }
        }
    }

    @Override
    public <E extends RealmModel> E createOrUpdateUsingJsonObject(Class<E> clazz, Realm realm, JSONObject json, boolean update)
        throws JSONException {
        checkClass(clazz);

        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Category.class)) {
            return clazz.cast(io.realm.CategoryRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Currency.class)) {
            return clazz.cast(io.realm.CurrencyRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Variant.class)) {
            return clazz.cast(io.realm.VariantRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Store.class)) {
            return clazz.cast(io.realm.StoreRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Notification.class)) {
            return clazz.cast(io.realm.NotificationRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Option.class)) {
            return clazz.cast(io.realm.OptionRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.SavedStores.class)) {
            return clazz.cast(io.realm.SavedStoresRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.CF.class)) {
            return clazz.cast(io.realm.CFRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Bookmark.class)) {
            return clazz.cast(io.realm.BookmarkRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.User.class)) {
            return clazz.cast(io.realm.UserRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Review.class)) {
            return clazz.cast(io.realm.ReviewRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.TimeLine.class)) {
            return clazz.cast(io.realm.TimeLineRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Cart.class)) {
            return clazz.cast(io.realm.CartRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Product.class)) {
            return clazz.cast(io.realm.ProductRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.PaymentGateway.class)) {
            return clazz.cast(io.realm.PaymentGatewayRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Discussion.class)) {
            return clazz.cast(io.realm.DiscussionRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Item.class)) {
            return clazz.cast(io.realm.ItemRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Setting.class)) {
            return clazz.cast(io.realm.SettingRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Inbox.class)) {
            return clazz.cast(io.realm.InboxRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Order.class)) {
            return clazz.cast(io.realm.OrderRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Session.class)) {
            return clazz.cast(io.realm.SessionRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Offer.class)) {
            return clazz.cast(io.realm.OfferRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.CountriesModel.class)) {
            return clazz.cast(io.realm.CountriesModelRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Images.class)) {
            return clazz.cast(io.realm.ImagesRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Message.class)) {
            return clazz.cast(io.realm.MessageRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Guest.class)) {
            return clazz.cast(io.realm.GuestRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Banner.class)) {
            return clazz.cast(io.realm.BannerRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.OpeningTime.class)) {
            return clazz.cast(io.realm.OpeningTimeRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Fee.class)) {
            return clazz.cast(io.realm.FeeRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Module.class)) {
            return clazz.cast(io.realm.ModuleRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> E createUsingJsonStream(Class<E> clazz, Realm realm, JsonReader reader)
        throws IOException {
        checkClass(clazz);

        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Category.class)) {
            return clazz.cast(io.realm.CategoryRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Currency.class)) {
            return clazz.cast(io.realm.CurrencyRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Variant.class)) {
            return clazz.cast(io.realm.VariantRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Store.class)) {
            return clazz.cast(io.realm.StoreRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Notification.class)) {
            return clazz.cast(io.realm.NotificationRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Option.class)) {
            return clazz.cast(io.realm.OptionRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.SavedStores.class)) {
            return clazz.cast(io.realm.SavedStoresRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.CF.class)) {
            return clazz.cast(io.realm.CFRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Bookmark.class)) {
            return clazz.cast(io.realm.BookmarkRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.User.class)) {
            return clazz.cast(io.realm.UserRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Review.class)) {
            return clazz.cast(io.realm.ReviewRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.TimeLine.class)) {
            return clazz.cast(io.realm.TimeLineRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Cart.class)) {
            return clazz.cast(io.realm.CartRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Product.class)) {
            return clazz.cast(io.realm.ProductRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.PaymentGateway.class)) {
            return clazz.cast(io.realm.PaymentGatewayRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Discussion.class)) {
            return clazz.cast(io.realm.DiscussionRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Item.class)) {
            return clazz.cast(io.realm.ItemRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Setting.class)) {
            return clazz.cast(io.realm.SettingRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Inbox.class)) {
            return clazz.cast(io.realm.InboxRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Order.class)) {
            return clazz.cast(io.realm.OrderRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Session.class)) {
            return clazz.cast(io.realm.SessionRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Offer.class)) {
            return clazz.cast(io.realm.OfferRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.CountriesModel.class)) {
            return clazz.cast(io.realm.CountriesModelRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Images.class)) {
            return clazz.cast(io.realm.ImagesRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Message.class)) {
            return clazz.cast(io.realm.MessageRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Guest.class)) {
            return clazz.cast(io.realm.GuestRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Banner.class)) {
            return clazz.cast(io.realm.BannerRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.OpeningTime.class)) {
            return clazz.cast(io.realm.OpeningTimeRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Fee.class)) {
            return clazz.cast(io.realm.FeeRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Module.class)) {
            return clazz.cast(io.realm.ModuleRealmProxy.createUsingJsonStream(realm, reader));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> E createDetachedCopy(E realmObject, int maxDepth, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) realmObject.getClass().getSuperclass();

        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Category.class)) {
            return clazz.cast(io.realm.CategoryRealmProxy.createDetachedCopy((com.geogreenapps.apps.indukaan.classes.Category) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Currency.class)) {
            return clazz.cast(io.realm.CurrencyRealmProxy.createDetachedCopy((com.geogreenapps.apps.indukaan.classes.Currency) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Variant.class)) {
            return clazz.cast(io.realm.VariantRealmProxy.createDetachedCopy((com.geogreenapps.apps.indukaan.classes.Variant) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Store.class)) {
            return clazz.cast(io.realm.StoreRealmProxy.createDetachedCopy((com.geogreenapps.apps.indukaan.classes.Store) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Notification.class)) {
            return clazz.cast(io.realm.NotificationRealmProxy.createDetachedCopy((com.geogreenapps.apps.indukaan.classes.Notification) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Option.class)) {
            return clazz.cast(io.realm.OptionRealmProxy.createDetachedCopy((com.geogreenapps.apps.indukaan.classes.Option) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.SavedStores.class)) {
            return clazz.cast(io.realm.SavedStoresRealmProxy.createDetachedCopy((com.geogreenapps.apps.indukaan.classes.SavedStores) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.CF.class)) {
            return clazz.cast(io.realm.CFRealmProxy.createDetachedCopy((com.geogreenapps.apps.indukaan.classes.CF) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Bookmark.class)) {
            return clazz.cast(io.realm.BookmarkRealmProxy.createDetachedCopy((com.geogreenapps.apps.indukaan.classes.Bookmark) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.User.class)) {
            return clazz.cast(io.realm.UserRealmProxy.createDetachedCopy((com.geogreenapps.apps.indukaan.classes.User) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Review.class)) {
            return clazz.cast(io.realm.ReviewRealmProxy.createDetachedCopy((com.geogreenapps.apps.indukaan.classes.Review) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.TimeLine.class)) {
            return clazz.cast(io.realm.TimeLineRealmProxy.createDetachedCopy((com.geogreenapps.apps.indukaan.classes.TimeLine) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Cart.class)) {
            return clazz.cast(io.realm.CartRealmProxy.createDetachedCopy((com.geogreenapps.apps.indukaan.classes.Cart) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Product.class)) {
            return clazz.cast(io.realm.ProductRealmProxy.createDetachedCopy((com.geogreenapps.apps.indukaan.classes.Product) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.PaymentGateway.class)) {
            return clazz.cast(io.realm.PaymentGatewayRealmProxy.createDetachedCopy((com.geogreenapps.apps.indukaan.classes.PaymentGateway) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Discussion.class)) {
            return clazz.cast(io.realm.DiscussionRealmProxy.createDetachedCopy((com.geogreenapps.apps.indukaan.classes.Discussion) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Item.class)) {
            return clazz.cast(io.realm.ItemRealmProxy.createDetachedCopy((com.geogreenapps.apps.indukaan.classes.Item) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Setting.class)) {
            return clazz.cast(io.realm.SettingRealmProxy.createDetachedCopy((com.geogreenapps.apps.indukaan.classes.Setting) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Inbox.class)) {
            return clazz.cast(io.realm.InboxRealmProxy.createDetachedCopy((com.geogreenapps.apps.indukaan.classes.Inbox) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Order.class)) {
            return clazz.cast(io.realm.OrderRealmProxy.createDetachedCopy((com.geogreenapps.apps.indukaan.classes.Order) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Session.class)) {
            return clazz.cast(io.realm.SessionRealmProxy.createDetachedCopy((com.geogreenapps.apps.indukaan.classes.Session) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Offer.class)) {
            return clazz.cast(io.realm.OfferRealmProxy.createDetachedCopy((com.geogreenapps.apps.indukaan.classes.Offer) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.CountriesModel.class)) {
            return clazz.cast(io.realm.CountriesModelRealmProxy.createDetachedCopy((com.geogreenapps.apps.indukaan.classes.CountriesModel) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Images.class)) {
            return clazz.cast(io.realm.ImagesRealmProxy.createDetachedCopy((com.geogreenapps.apps.indukaan.classes.Images) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Message.class)) {
            return clazz.cast(io.realm.MessageRealmProxy.createDetachedCopy((com.geogreenapps.apps.indukaan.classes.Message) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Guest.class)) {
            return clazz.cast(io.realm.GuestRealmProxy.createDetachedCopy((com.geogreenapps.apps.indukaan.classes.Guest) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Banner.class)) {
            return clazz.cast(io.realm.BannerRealmProxy.createDetachedCopy((com.geogreenapps.apps.indukaan.classes.Banner) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.OpeningTime.class)) {
            return clazz.cast(io.realm.OpeningTimeRealmProxy.createDetachedCopy((com.geogreenapps.apps.indukaan.classes.OpeningTime) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Fee.class)) {
            return clazz.cast(io.realm.FeeRealmProxy.createDetachedCopy((com.geogreenapps.apps.indukaan.classes.Fee) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.geogreenapps.apps.indukaan.classes.Module.class)) {
            return clazz.cast(io.realm.ModuleRealmProxy.createDetachedCopy((com.geogreenapps.apps.indukaan.classes.Module) realmObject, 0, maxDepth, cache));
        }
        throw getMissingProxyClassException(clazz);
    }

}
