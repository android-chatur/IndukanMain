package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsList;
import io.realm.internal.OsObject;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.Property;
import io.realm.internal.ProxyUtils;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.SharedRealm;
import io.realm.internal.Table;
import io.realm.internal.android.JsonUtils;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("all")
public class OfferRealmProxy extends com.geogreenapps.apps.indukaan.classes.Offer
    implements RealmObjectProxy, OfferRealmProxyInterface {

    static final class OfferColumnInfo extends ColumnInfo {
        long idIndex;
        long currencyIndex;
        long store_idIndex;
        long user_idIndex;
        long statusIndex;
        long date_startIndex;
        long date_endIndex;
        long nameIndex;
        long store_nameIndex;
        long imagesIndex;
        long listImagesIndex;
        long distanceIndex;
        long latIndex;
        long lngIndex;
        long featuredIndex;
        long linkIndex;
        long descriptionIndex;
        long short_descriptionIndex;
        long product_typeIndex;
        long product_valueIndex;
        long tagsIndex;
        long is_dealIndex;
        long order_enabledIndex;
        long qty_enabledIndex;
        long order_buttonIndex;
        long cf_idIndex;
        long cfIndex;
        long commissionIndex;
        long stockIndex;
        long is_offerIndex;
        long variantsIndex;

        OfferColumnInfo(OsSchemaInfo schemaInfo) {
            super(31);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Offer");
            this.idIndex = addColumnDetails("id", objectSchemaInfo);
            this.currencyIndex = addColumnDetails("currency", objectSchemaInfo);
            this.store_idIndex = addColumnDetails("store_id", objectSchemaInfo);
            this.user_idIndex = addColumnDetails("user_id", objectSchemaInfo);
            this.statusIndex = addColumnDetails("status", objectSchemaInfo);
            this.date_startIndex = addColumnDetails("date_start", objectSchemaInfo);
            this.date_endIndex = addColumnDetails("date_end", objectSchemaInfo);
            this.nameIndex = addColumnDetails("name", objectSchemaInfo);
            this.store_nameIndex = addColumnDetails("store_name", objectSchemaInfo);
            this.imagesIndex = addColumnDetails("images", objectSchemaInfo);
            this.listImagesIndex = addColumnDetails("listImages", objectSchemaInfo);
            this.distanceIndex = addColumnDetails("distance", objectSchemaInfo);
            this.latIndex = addColumnDetails("lat", objectSchemaInfo);
            this.lngIndex = addColumnDetails("lng", objectSchemaInfo);
            this.featuredIndex = addColumnDetails("featured", objectSchemaInfo);
            this.linkIndex = addColumnDetails("link", objectSchemaInfo);
            this.descriptionIndex = addColumnDetails("description", objectSchemaInfo);
            this.short_descriptionIndex = addColumnDetails("short_description", objectSchemaInfo);
            this.product_typeIndex = addColumnDetails("product_type", objectSchemaInfo);
            this.product_valueIndex = addColumnDetails("product_value", objectSchemaInfo);
            this.tagsIndex = addColumnDetails("tags", objectSchemaInfo);
            this.is_dealIndex = addColumnDetails("is_deal", objectSchemaInfo);
            this.order_enabledIndex = addColumnDetails("order_enabled", objectSchemaInfo);
            this.qty_enabledIndex = addColumnDetails("qty_enabled", objectSchemaInfo);
            this.order_buttonIndex = addColumnDetails("order_button", objectSchemaInfo);
            this.cf_idIndex = addColumnDetails("cf_id", objectSchemaInfo);
            this.cfIndex = addColumnDetails("cf", objectSchemaInfo);
            this.commissionIndex = addColumnDetails("commission", objectSchemaInfo);
            this.stockIndex = addColumnDetails("stock", objectSchemaInfo);
            this.is_offerIndex = addColumnDetails("is_offer", objectSchemaInfo);
            this.variantsIndex = addColumnDetails("variants", objectSchemaInfo);
        }

        OfferColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new OfferColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final OfferColumnInfo src = (OfferColumnInfo) rawSrc;
            final OfferColumnInfo dst = (OfferColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.currencyIndex = src.currencyIndex;
            dst.store_idIndex = src.store_idIndex;
            dst.user_idIndex = src.user_idIndex;
            dst.statusIndex = src.statusIndex;
            dst.date_startIndex = src.date_startIndex;
            dst.date_endIndex = src.date_endIndex;
            dst.nameIndex = src.nameIndex;
            dst.store_nameIndex = src.store_nameIndex;
            dst.imagesIndex = src.imagesIndex;
            dst.listImagesIndex = src.listImagesIndex;
            dst.distanceIndex = src.distanceIndex;
            dst.latIndex = src.latIndex;
            dst.lngIndex = src.lngIndex;
            dst.featuredIndex = src.featuredIndex;
            dst.linkIndex = src.linkIndex;
            dst.descriptionIndex = src.descriptionIndex;
            dst.short_descriptionIndex = src.short_descriptionIndex;
            dst.product_typeIndex = src.product_typeIndex;
            dst.product_valueIndex = src.product_valueIndex;
            dst.tagsIndex = src.tagsIndex;
            dst.is_dealIndex = src.is_dealIndex;
            dst.order_enabledIndex = src.order_enabledIndex;
            dst.qty_enabledIndex = src.qty_enabledIndex;
            dst.order_buttonIndex = src.order_buttonIndex;
            dst.cf_idIndex = src.cf_idIndex;
            dst.cfIndex = src.cfIndex;
            dst.commissionIndex = src.commissionIndex;
            dst.stockIndex = src.stockIndex;
            dst.is_offerIndex = src.is_offerIndex;
            dst.variantsIndex = src.variantsIndex;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>(31);
        fieldNames.add("id");
        fieldNames.add("currency");
        fieldNames.add("store_id");
        fieldNames.add("user_id");
        fieldNames.add("status");
        fieldNames.add("date_start");
        fieldNames.add("date_end");
        fieldNames.add("name");
        fieldNames.add("store_name");
        fieldNames.add("images");
        fieldNames.add("listImages");
        fieldNames.add("distance");
        fieldNames.add("lat");
        fieldNames.add("lng");
        fieldNames.add("featured");
        fieldNames.add("link");
        fieldNames.add("description");
        fieldNames.add("short_description");
        fieldNames.add("product_type");
        fieldNames.add("product_value");
        fieldNames.add("tags");
        fieldNames.add("is_deal");
        fieldNames.add("order_enabled");
        fieldNames.add("qty_enabled");
        fieldNames.add("order_button");
        fieldNames.add("cf_id");
        fieldNames.add("cf");
        fieldNames.add("commission");
        fieldNames.add("stock");
        fieldNames.add("is_offer");
        fieldNames.add("variants");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    private OfferColumnInfo columnInfo;
    private ProxyState<com.geogreenapps.apps.indukaan.classes.Offer> proxyState;
    private RealmList<com.geogreenapps.apps.indukaan.classes.Images> listImagesRealmList;
    private RealmList<com.geogreenapps.apps.indukaan.classes.CF> cfRealmList;
    private RealmList<com.geogreenapps.apps.indukaan.classes.Variant> variantsRealmList;

    OfferRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (OfferColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.geogreenapps.apps.indukaan.classes.Offer>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$id() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.idIndex);
    }

    @Override
    public void realmSet$id(int value) {
        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'id' cannot be changed after object was created.");
    }

    @Override
    public com.geogreenapps.apps.indukaan.classes.Currency realmGet$currency() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNullLink(columnInfo.currencyIndex)) {
            return null;
        }
        return proxyState.getRealm$realm().get(com.geogreenapps.apps.indukaan.classes.Currency.class, proxyState.getRow$realm().getLink(columnInfo.currencyIndex), false, Collections.<String>emptyList());
    }

    @Override
    public void realmSet$currency(com.geogreenapps.apps.indukaan.classes.Currency value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("currency")) {
                return;
            }
            if (value != null && !RealmObject.isManaged(value)) {
                value = ((Realm) proxyState.getRealm$realm()).copyToRealm(value);
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                // Table#nullifyLink() does not support default value. Just using Row.
                row.nullifyLink(columnInfo.currencyIndex);
                return;
            }
            proxyState.checkValidObject(value);
            row.getTable().setLink(columnInfo.currencyIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().nullifyLink(columnInfo.currencyIndex);
            return;
        }
        proxyState.checkValidObject(value);
        proxyState.getRow$realm().setLink(columnInfo.currencyIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$store_id() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.store_idIndex);
    }

    @Override
    public void realmSet$store_id(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.store_idIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.store_idIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$user_id() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.user_idIndex);
    }

    @Override
    public void realmSet$user_id(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.user_idIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.user_idIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$status() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.statusIndex);
    }

    @Override
    public void realmSet$status(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.statusIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.statusIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$date_start() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.date_startIndex);
    }

    @Override
    public void realmSet$date_start(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.date_startIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.date_startIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.date_startIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.date_startIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$date_end() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.date_endIndex);
    }

    @Override
    public void realmSet$date_end(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.date_endIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.date_endIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.date_endIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.date_endIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$name() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.nameIndex);
    }

    @Override
    public void realmSet$name(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.nameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.nameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.nameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.nameIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$store_name() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.store_nameIndex);
    }

    @Override
    public void realmSet$store_name(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.store_nameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.store_nameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.store_nameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.store_nameIndex, value);
    }

    @Override
    public com.geogreenapps.apps.indukaan.classes.Images realmGet$images() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNullLink(columnInfo.imagesIndex)) {
            return null;
        }
        return proxyState.getRealm$realm().get(com.geogreenapps.apps.indukaan.classes.Images.class, proxyState.getRow$realm().getLink(columnInfo.imagesIndex), false, Collections.<String>emptyList());
    }

    @Override
    public void realmSet$images(com.geogreenapps.apps.indukaan.classes.Images value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("images")) {
                return;
            }
            if (value != null && !RealmObject.isManaged(value)) {
                value = ((Realm) proxyState.getRealm$realm()).copyToRealm(value);
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                // Table#nullifyLink() does not support default value. Just using Row.
                row.nullifyLink(columnInfo.imagesIndex);
                return;
            }
            proxyState.checkValidObject(value);
            row.getTable().setLink(columnInfo.imagesIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().nullifyLink(columnInfo.imagesIndex);
            return;
        }
        proxyState.checkValidObject(value);
        proxyState.getRow$realm().setLink(columnInfo.imagesIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
    }

    @Override
    public RealmList<com.geogreenapps.apps.indukaan.classes.Images> realmGet$listImages() {
        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (listImagesRealmList != null) {
            return listImagesRealmList;
        } else {
            OsList osList = proxyState.getRow$realm().getModelList(columnInfo.listImagesIndex);
            listImagesRealmList = new RealmList<com.geogreenapps.apps.indukaan.classes.Images>(com.geogreenapps.apps.indukaan.classes.Images.class, osList, proxyState.getRealm$realm());
            return listImagesRealmList;
        }
    }

    @Override
    public void realmSet$listImages(RealmList<com.geogreenapps.apps.indukaan.classes.Images> value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("listImages")) {
                return;
            }
            // if the list contains unmanaged RealmObjects, convert them to managed.
            if (value != null && !value.isManaged()) {
                final Realm realm = (Realm) proxyState.getRealm$realm();
                final RealmList<com.geogreenapps.apps.indukaan.classes.Images> original = value;
                value = new RealmList<com.geogreenapps.apps.indukaan.classes.Images>();
                for (com.geogreenapps.apps.indukaan.classes.Images item : original) {
                    if (item == null || RealmObject.isManaged(item)) {
                        value.add(item);
                    } else {
                        value.add(realm.copyToRealm(item));
                    }
                }
            }
        }

        proxyState.getRealm$realm().checkIfValid();
        OsList osList = proxyState.getRow$realm().getModelList(columnInfo.listImagesIndex);
        // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
        if (value != null && value.size() == osList.size()) {
            int objects = value.size();
            for (int i = 0; i < objects; i++) {
                com.geogreenapps.apps.indukaan.classes.Images linkedObject = value.get(i);
                proxyState.checkValidObject(linkedObject);
                osList.setRow(i, ((RealmObjectProxy) linkedObject).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else {
            osList.removeAll();
            if (value == null) {
                return;
            }
            int objects = value.size();
            for (int i = 0; i < objects; i++) {
                com.geogreenapps.apps.indukaan.classes.Images linkedObject = value.get(i);
                proxyState.checkValidObject(linkedObject);
                osList.addRow(((RealmObjectProxy) linkedObject).realmGet$proxyState().getRow$realm().getIndex());
            }
        }
    }

    @Override
    @SuppressWarnings("cast")
    public Double realmGet$distance() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.distanceIndex)) {
            return null;
        }
        return (double) proxyState.getRow$realm().getDouble(columnInfo.distanceIndex);
    }

    @Override
    public void realmSet$distance(Double value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.distanceIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setDouble(columnInfo.distanceIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.distanceIndex);
            return;
        }
        proxyState.getRow$realm().setDouble(columnInfo.distanceIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public Double realmGet$lat() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.latIndex)) {
            return null;
        }
        return (double) proxyState.getRow$realm().getDouble(columnInfo.latIndex);
    }

    @Override
    public void realmSet$lat(Double value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.latIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setDouble(columnInfo.latIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.latIndex);
            return;
        }
        proxyState.getRow$realm().setDouble(columnInfo.latIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public Double realmGet$lng() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.lngIndex)) {
            return null;
        }
        return (double) proxyState.getRow$realm().getDouble(columnInfo.lngIndex);
    }

    @Override
    public void realmSet$lng(Double value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.lngIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setDouble(columnInfo.lngIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.lngIndex);
            return;
        }
        proxyState.getRow$realm().setDouble(columnInfo.lngIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$featured() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.featuredIndex);
    }

    @Override
    public void realmSet$featured(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.featuredIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.featuredIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$link() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.linkIndex);
    }

    @Override
    public void realmSet$link(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.linkIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.linkIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.linkIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.linkIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$description() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.descriptionIndex);
    }

    @Override
    public void realmSet$description(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.descriptionIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.descriptionIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.descriptionIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.descriptionIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$short_description() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.short_descriptionIndex);
    }

    @Override
    public void realmSet$short_description(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.short_descriptionIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.short_descriptionIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.short_descriptionIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.short_descriptionIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$product_type() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.product_typeIndex);
    }

    @Override
    public void realmSet$product_type(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.product_typeIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.product_typeIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.product_typeIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.product_typeIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public float realmGet$product_value() {
        proxyState.getRealm$realm().checkIfValid();
        return (float) proxyState.getRow$realm().getFloat(columnInfo.product_valueIndex);
    }

    @Override
    public void realmSet$product_value(float value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setFloat(columnInfo.product_valueIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setFloat(columnInfo.product_valueIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$tags() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.tagsIndex);
    }

    @Override
    public void realmSet$tags(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.tagsIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.tagsIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.tagsIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.tagsIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$is_deal() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.is_dealIndex);
    }

    @Override
    public void realmSet$is_deal(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.is_dealIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.is_dealIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$order_enabled() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.order_enabledIndex);
    }

    @Override
    public void realmSet$order_enabled(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.order_enabledIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.order_enabledIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$qty_enabled() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.qty_enabledIndex);
    }

    @Override
    public void realmSet$qty_enabled(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.qty_enabledIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.qty_enabledIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$order_button() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.order_buttonIndex);
    }

    @Override
    public void realmSet$order_button(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.order_buttonIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.order_buttonIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.order_buttonIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.order_buttonIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$cf_id() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.cf_idIndex);
    }

    @Override
    public void realmSet$cf_id(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.cf_idIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.cf_idIndex, value);
    }

    @Override
    public RealmList<com.geogreenapps.apps.indukaan.classes.CF> realmGet$cf() {
        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (cfRealmList != null) {
            return cfRealmList;
        } else {
            OsList osList = proxyState.getRow$realm().getModelList(columnInfo.cfIndex);
            cfRealmList = new RealmList<com.geogreenapps.apps.indukaan.classes.CF>(com.geogreenapps.apps.indukaan.classes.CF.class, osList, proxyState.getRealm$realm());
            return cfRealmList;
        }
    }

    @Override
    public void realmSet$cf(RealmList<com.geogreenapps.apps.indukaan.classes.CF> value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("cf")) {
                return;
            }
            // if the list contains unmanaged RealmObjects, convert them to managed.
            if (value != null && !value.isManaged()) {
                final Realm realm = (Realm) proxyState.getRealm$realm();
                final RealmList<com.geogreenapps.apps.indukaan.classes.CF> original = value;
                value = new RealmList<com.geogreenapps.apps.indukaan.classes.CF>();
                for (com.geogreenapps.apps.indukaan.classes.CF item : original) {
                    if (item == null || RealmObject.isManaged(item)) {
                        value.add(item);
                    } else {
                        value.add(realm.copyToRealm(item));
                    }
                }
            }
        }

        proxyState.getRealm$realm().checkIfValid();
        OsList osList = proxyState.getRow$realm().getModelList(columnInfo.cfIndex);
        // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
        if (value != null && value.size() == osList.size()) {
            int objects = value.size();
            for (int i = 0; i < objects; i++) {
                com.geogreenapps.apps.indukaan.classes.CF linkedObject = value.get(i);
                proxyState.checkValidObject(linkedObject);
                osList.setRow(i, ((RealmObjectProxy) linkedObject).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else {
            osList.removeAll();
            if (value == null) {
                return;
            }
            int objects = value.size();
            for (int i = 0; i < objects; i++) {
                com.geogreenapps.apps.indukaan.classes.CF linkedObject = value.get(i);
                proxyState.checkValidObject(linkedObject);
                osList.addRow(((RealmObjectProxy) linkedObject).realmGet$proxyState().getRow$realm().getIndex());
            }
        }
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$commission() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.commissionIndex);
    }

    @Override
    public void realmSet$commission(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.commissionIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.commissionIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$stock() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.stockIndex);
    }

    @Override
    public void realmSet$stock(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.stockIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.stockIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$is_offer() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.is_offerIndex);
    }

    @Override
    public void realmSet$is_offer(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.is_offerIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.is_offerIndex, value);
    }

    @Override
    public RealmList<com.geogreenapps.apps.indukaan.classes.Variant> realmGet$variants() {
        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (variantsRealmList != null) {
            return variantsRealmList;
        } else {
            OsList osList = proxyState.getRow$realm().getModelList(columnInfo.variantsIndex);
            variantsRealmList = new RealmList<com.geogreenapps.apps.indukaan.classes.Variant>(com.geogreenapps.apps.indukaan.classes.Variant.class, osList, proxyState.getRealm$realm());
            return variantsRealmList;
        }
    }

    @Override
    public void realmSet$variants(RealmList<com.geogreenapps.apps.indukaan.classes.Variant> value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("variants")) {
                return;
            }
            // if the list contains unmanaged RealmObjects, convert them to managed.
            if (value != null && !value.isManaged()) {
                final Realm realm = (Realm) proxyState.getRealm$realm();
                final RealmList<com.geogreenapps.apps.indukaan.classes.Variant> original = value;
                value = new RealmList<com.geogreenapps.apps.indukaan.classes.Variant>();
                for (com.geogreenapps.apps.indukaan.classes.Variant item : original) {
                    if (item == null || RealmObject.isManaged(item)) {
                        value.add(item);
                    } else {
                        value.add(realm.copyToRealm(item));
                    }
                }
            }
        }

        proxyState.getRealm$realm().checkIfValid();
        OsList osList = proxyState.getRow$realm().getModelList(columnInfo.variantsIndex);
        // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
        if (value != null && value.size() == osList.size()) {
            int objects = value.size();
            for (int i = 0; i < objects; i++) {
                com.geogreenapps.apps.indukaan.classes.Variant linkedObject = value.get(i);
                proxyState.checkValidObject(linkedObject);
                osList.setRow(i, ((RealmObjectProxy) linkedObject).realmGet$proxyState().getRow$realm().getIndex());
            }
        } else {
            osList.removeAll();
            if (value == null) {
                return;
            }
            int objects = value.size();
            for (int i = 0; i < objects; i++) {
                com.geogreenapps.apps.indukaan.classes.Variant linkedObject = value.get(i);
                proxyState.checkValidObject(linkedObject);
                osList.addRow(((RealmObjectProxy) linkedObject).realmGet$proxyState().getRow$realm().getIndex());
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("Offer", 31, 0);
        builder.addPersistedProperty("id", RealmFieldType.INTEGER, Property.PRIMARY_KEY, Property.INDEXED, Property.REQUIRED);
        builder.addPersistedLinkProperty("currency", RealmFieldType.OBJECT, "Currency");
        builder.addPersistedProperty("store_id", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("user_id", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("status", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("date_start", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("date_end", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("name", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("store_name", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedLinkProperty("images", RealmFieldType.OBJECT, "Images");
        builder.addPersistedLinkProperty("listImages", RealmFieldType.LIST, "Images");
        builder.addPersistedProperty("distance", RealmFieldType.DOUBLE, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("lat", RealmFieldType.DOUBLE, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("lng", RealmFieldType.DOUBLE, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("featured", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("link", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("description", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("short_description", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("product_type", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("product_value", RealmFieldType.FLOAT, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("tags", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("is_deal", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("order_enabled", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("qty_enabled", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("order_button", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("cf_id", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedLinkProperty("cf", RealmFieldType.LIST, "CF");
        builder.addPersistedProperty("commission", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("stock", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("is_offer", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedLinkProperty("variants", RealmFieldType.LIST, "Variant");
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static OfferColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new OfferColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "Offer";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.geogreenapps.apps.indukaan.classes.Offer createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = new ArrayList<String>(5);
        com.geogreenapps.apps.indukaan.classes.Offer obj = null;
        if (update) {
            Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Offer.class);
            OfferColumnInfo columnInfo = (OfferColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Offer.class);
            long pkColumnIndex = columnInfo.idIndex;
            long rowIndex = Table.NO_MATCH;
            if (!json.isNull("id")) {
                rowIndex = table.findFirstLong(pkColumnIndex, json.getLong("id"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Offer.class), false, Collections.<String> emptyList());
                    obj = new io.realm.OfferRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("currency")) {
                excludeFields.add("currency");
            }
            if (json.has("images")) {
                excludeFields.add("images");
            }
            if (json.has("listImages")) {
                excludeFields.add("listImages");
            }
            if (json.has("cf")) {
                excludeFields.add("cf");
            }
            if (json.has("variants")) {
                excludeFields.add("variants");
            }
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.OfferRealmProxy) realm.createObjectInternal(com.geogreenapps.apps.indukaan.classes.Offer.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.OfferRealmProxy) realm.createObjectInternal(com.geogreenapps.apps.indukaan.classes.Offer.class, json.getInt("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }

        final OfferRealmProxyInterface objProxy = (OfferRealmProxyInterface) obj;
        if (json.has("currency")) {
            if (json.isNull("currency")) {
                objProxy.realmSet$currency(null);
            } else {
                com.geogreenapps.apps.indukaan.classes.Currency currencyObj = CurrencyRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("currency"), update);
                objProxy.realmSet$currency(currencyObj);
            }
        }
        if (json.has("store_id")) {
            if (json.isNull("store_id")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'store_id' to null.");
            } else {
                objProxy.realmSet$store_id((int) json.getInt("store_id"));
            }
        }
        if (json.has("user_id")) {
            if (json.isNull("user_id")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'user_id' to null.");
            } else {
                objProxy.realmSet$user_id((int) json.getInt("user_id"));
            }
        }
        if (json.has("status")) {
            if (json.isNull("status")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'status' to null.");
            } else {
                objProxy.realmSet$status((int) json.getInt("status"));
            }
        }
        if (json.has("date_start")) {
            if (json.isNull("date_start")) {
                objProxy.realmSet$date_start(null);
            } else {
                objProxy.realmSet$date_start((String) json.getString("date_start"));
            }
        }
        if (json.has("date_end")) {
            if (json.isNull("date_end")) {
                objProxy.realmSet$date_end(null);
            } else {
                objProxy.realmSet$date_end((String) json.getString("date_end"));
            }
        }
        if (json.has("name")) {
            if (json.isNull("name")) {
                objProxy.realmSet$name(null);
            } else {
                objProxy.realmSet$name((String) json.getString("name"));
            }
        }
        if (json.has("store_name")) {
            if (json.isNull("store_name")) {
                objProxy.realmSet$store_name(null);
            } else {
                objProxy.realmSet$store_name((String) json.getString("store_name"));
            }
        }
        if (json.has("images")) {
            if (json.isNull("images")) {
                objProxy.realmSet$images(null);
            } else {
                com.geogreenapps.apps.indukaan.classes.Images imagesObj = ImagesRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("images"), update);
                objProxy.realmSet$images(imagesObj);
            }
        }
        if (json.has("listImages")) {
            if (json.isNull("listImages")) {
                objProxy.realmSet$listImages(null);
            } else {
                objProxy.realmGet$listImages().clear();
                JSONArray array = json.getJSONArray("listImages");
                for (int i = 0; i < array.length(); i++) {
                    com.geogreenapps.apps.indukaan.classes.Images item = ImagesRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    objProxy.realmGet$listImages().add(item);
                }
            }
        }
        if (json.has("distance")) {
            if (json.isNull("distance")) {
                objProxy.realmSet$distance(null);
            } else {
                objProxy.realmSet$distance((double) json.getDouble("distance"));
            }
        }
        if (json.has("lat")) {
            if (json.isNull("lat")) {
                objProxy.realmSet$lat(null);
            } else {
                objProxy.realmSet$lat((double) json.getDouble("lat"));
            }
        }
        if (json.has("lng")) {
            if (json.isNull("lng")) {
                objProxy.realmSet$lng(null);
            } else {
                objProxy.realmSet$lng((double) json.getDouble("lng"));
            }
        }
        if (json.has("featured")) {
            if (json.isNull("featured")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'featured' to null.");
            } else {
                objProxy.realmSet$featured((int) json.getInt("featured"));
            }
        }
        if (json.has("link")) {
            if (json.isNull("link")) {
                objProxy.realmSet$link(null);
            } else {
                objProxy.realmSet$link((String) json.getString("link"));
            }
        }
        if (json.has("description")) {
            if (json.isNull("description")) {
                objProxy.realmSet$description(null);
            } else {
                objProxy.realmSet$description((String) json.getString("description"));
            }
        }
        if (json.has("short_description")) {
            if (json.isNull("short_description")) {
                objProxy.realmSet$short_description(null);
            } else {
                objProxy.realmSet$short_description((String) json.getString("short_description"));
            }
        }
        if (json.has("product_type")) {
            if (json.isNull("product_type")) {
                objProxy.realmSet$product_type(null);
            } else {
                objProxy.realmSet$product_type((String) json.getString("product_type"));
            }
        }
        if (json.has("product_value")) {
            if (json.isNull("product_value")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'product_value' to null.");
            } else {
                objProxy.realmSet$product_value((float) json.getDouble("product_value"));
            }
        }
        if (json.has("tags")) {
            if (json.isNull("tags")) {
                objProxy.realmSet$tags(null);
            } else {
                objProxy.realmSet$tags((String) json.getString("tags"));
            }
        }
        if (json.has("is_deal")) {
            if (json.isNull("is_deal")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'is_deal' to null.");
            } else {
                objProxy.realmSet$is_deal((int) json.getInt("is_deal"));
            }
        }
        if (json.has("order_enabled")) {
            if (json.isNull("order_enabled")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'order_enabled' to null.");
            } else {
                objProxy.realmSet$order_enabled((int) json.getInt("order_enabled"));
            }
        }
        if (json.has("qty_enabled")) {
            if (json.isNull("qty_enabled")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'qty_enabled' to null.");
            } else {
                objProxy.realmSet$qty_enabled((int) json.getInt("qty_enabled"));
            }
        }
        if (json.has("order_button")) {
            if (json.isNull("order_button")) {
                objProxy.realmSet$order_button(null);
            } else {
                objProxy.realmSet$order_button((String) json.getString("order_button"));
            }
        }
        if (json.has("cf_id")) {
            if (json.isNull("cf_id")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'cf_id' to null.");
            } else {
                objProxy.realmSet$cf_id((int) json.getInt("cf_id"));
            }
        }
        if (json.has("cf")) {
            if (json.isNull("cf")) {
                objProxy.realmSet$cf(null);
            } else {
                objProxy.realmGet$cf().clear();
                JSONArray array = json.getJSONArray("cf");
                for (int i = 0; i < array.length(); i++) {
                    com.geogreenapps.apps.indukaan.classes.CF item = CFRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    objProxy.realmGet$cf().add(item);
                }
            }
        }
        if (json.has("commission")) {
            if (json.isNull("commission")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'commission' to null.");
            } else {
                objProxy.realmSet$commission((int) json.getInt("commission"));
            }
        }
        if (json.has("stock")) {
            if (json.isNull("stock")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'stock' to null.");
            } else {
                objProxy.realmSet$stock((int) json.getInt("stock"));
            }
        }
        if (json.has("is_offer")) {
            if (json.isNull("is_offer")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'is_offer' to null.");
            } else {
                objProxy.realmSet$is_offer((int) json.getInt("is_offer"));
            }
        }
        if (json.has("variants")) {
            if (json.isNull("variants")) {
                objProxy.realmSet$variants(null);
            } else {
                objProxy.realmGet$variants().clear();
                JSONArray array = json.getJSONArray("variants");
                for (int i = 0; i < array.length(); i++) {
                    com.geogreenapps.apps.indukaan.classes.Variant item = VariantRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    objProxy.realmGet$variants().add(item);
                }
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.geogreenapps.apps.indukaan.classes.Offer createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.geogreenapps.apps.indukaan.classes.Offer obj = new com.geogreenapps.apps.indukaan.classes.Offer();
        final OfferRealmProxyInterface objProxy = (OfferRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$id((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'id' to null.");
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("currency")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$currency(null);
                } else {
                    com.geogreenapps.apps.indukaan.classes.Currency currencyObj = CurrencyRealmProxy.createUsingJsonStream(realm, reader);
                    objProxy.realmSet$currency(currencyObj);
                }
            } else if (name.equals("store_id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$store_id((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'store_id' to null.");
                }
            } else if (name.equals("user_id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$user_id((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'user_id' to null.");
                }
            } else if (name.equals("status")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$status((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'status' to null.");
                }
            } else if (name.equals("date_start")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$date_start((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$date_start(null);
                }
            } else if (name.equals("date_end")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$date_end((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$date_end(null);
                }
            } else if (name.equals("name")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$name((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$name(null);
                }
            } else if (name.equals("store_name")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$store_name((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$store_name(null);
                }
            } else if (name.equals("images")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$images(null);
                } else {
                    com.geogreenapps.apps.indukaan.classes.Images imagesObj = ImagesRealmProxy.createUsingJsonStream(realm, reader);
                    objProxy.realmSet$images(imagesObj);
                }
            } else if (name.equals("listImages")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$listImages(null);
                } else {
                    objProxy.realmSet$listImages(new RealmList<com.geogreenapps.apps.indukaan.classes.Images>());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.geogreenapps.apps.indukaan.classes.Images item = ImagesRealmProxy.createUsingJsonStream(realm, reader);
                        objProxy.realmGet$listImages().add(item);
                    }
                    reader.endArray();
                }
            } else if (name.equals("distance")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$distance((double) reader.nextDouble());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$distance(null);
                }
            } else if (name.equals("lat")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$lat((double) reader.nextDouble());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$lat(null);
                }
            } else if (name.equals("lng")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$lng((double) reader.nextDouble());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$lng(null);
                }
            } else if (name.equals("featured")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$featured((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'featured' to null.");
                }
            } else if (name.equals("link")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$link((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$link(null);
                }
            } else if (name.equals("description")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$description((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$description(null);
                }
            } else if (name.equals("short_description")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$short_description((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$short_description(null);
                }
            } else if (name.equals("product_type")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$product_type((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$product_type(null);
                }
            } else if (name.equals("product_value")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$product_value((float) reader.nextDouble());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'product_value' to null.");
                }
            } else if (name.equals("tags")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$tags((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$tags(null);
                }
            } else if (name.equals("is_deal")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$is_deal((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'is_deal' to null.");
                }
            } else if (name.equals("order_enabled")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$order_enabled((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'order_enabled' to null.");
                }
            } else if (name.equals("qty_enabled")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$qty_enabled((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'qty_enabled' to null.");
                }
            } else if (name.equals("order_button")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$order_button((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$order_button(null);
                }
            } else if (name.equals("cf_id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$cf_id((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'cf_id' to null.");
                }
            } else if (name.equals("cf")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$cf(null);
                } else {
                    objProxy.realmSet$cf(new RealmList<com.geogreenapps.apps.indukaan.classes.CF>());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.geogreenapps.apps.indukaan.classes.CF item = CFRealmProxy.createUsingJsonStream(realm, reader);
                        objProxy.realmGet$cf().add(item);
                    }
                    reader.endArray();
                }
            } else if (name.equals("commission")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$commission((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'commission' to null.");
                }
            } else if (name.equals("stock")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$stock((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'stock' to null.");
                }
            } else if (name.equals("is_offer")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$is_offer((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'is_offer' to null.");
                }
            } else if (name.equals("variants")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$variants(null);
                } else {
                    objProxy.realmSet$variants(new RealmList<com.geogreenapps.apps.indukaan.classes.Variant>());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.geogreenapps.apps.indukaan.classes.Variant item = VariantRealmProxy.createUsingJsonStream(realm, reader);
                        objProxy.realmGet$variants().add(item);
                    }
                    reader.endArray();
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
        }
        return realm.copyToRealm(obj);
    }

    public static com.geogreenapps.apps.indukaan.classes.Offer copyOrUpdate(Realm realm, com.geogreenapps.apps.indukaan.classes.Offer object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null) {
            final BaseRealm otherRealm = ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm();
            if (otherRealm.threadId != realm.threadId) {
                throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
            }
            if (otherRealm.getPath().equals(realm.getPath())) {
                return object;
            }
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.geogreenapps.apps.indukaan.classes.Offer) cachedRealmObject;
        }

        com.geogreenapps.apps.indukaan.classes.Offer realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Offer.class);
            OfferColumnInfo columnInfo = (OfferColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Offer.class);
            long pkColumnIndex = columnInfo.idIndex;
            long rowIndex = table.findFirstLong(pkColumnIndex, ((OfferRealmProxyInterface) object).realmGet$id());
            if (rowIndex == Table.NO_MATCH) {
                canUpdate = false;
            } else {
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Offer.class), false, Collections.<String> emptyList());
                    realmObject = new io.realm.OfferRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, realmObject, object, cache) : copy(realm, object, update, cache);
    }

    public static com.geogreenapps.apps.indukaan.classes.Offer copy(Realm realm, com.geogreenapps.apps.indukaan.classes.Offer newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.geogreenapps.apps.indukaan.classes.Offer) cachedRealmObject;
        }

        // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
        com.geogreenapps.apps.indukaan.classes.Offer realmObject = realm.createObjectInternal(com.geogreenapps.apps.indukaan.classes.Offer.class, ((OfferRealmProxyInterface) newObject).realmGet$id(), false, Collections.<String>emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);

        OfferRealmProxyInterface realmObjectSource = (OfferRealmProxyInterface) newObject;
        OfferRealmProxyInterface realmObjectCopy = (OfferRealmProxyInterface) realmObject;


        com.geogreenapps.apps.indukaan.classes.Currency currencyObj = realmObjectSource.realmGet$currency();
        if (currencyObj == null) {
            realmObjectCopy.realmSet$currency(null);
        } else {
            com.geogreenapps.apps.indukaan.classes.Currency cachecurrency = (com.geogreenapps.apps.indukaan.classes.Currency) cache.get(currencyObj);
            if (cachecurrency != null) {
                realmObjectCopy.realmSet$currency(cachecurrency);
            } else {
                realmObjectCopy.realmSet$currency(CurrencyRealmProxy.copyOrUpdate(realm, currencyObj, update, cache));
            }
        }
        realmObjectCopy.realmSet$store_id(realmObjectSource.realmGet$store_id());
        realmObjectCopy.realmSet$user_id(realmObjectSource.realmGet$user_id());
        realmObjectCopy.realmSet$status(realmObjectSource.realmGet$status());
        realmObjectCopy.realmSet$date_start(realmObjectSource.realmGet$date_start());
        realmObjectCopy.realmSet$date_end(realmObjectSource.realmGet$date_end());
        realmObjectCopy.realmSet$name(realmObjectSource.realmGet$name());
        realmObjectCopy.realmSet$store_name(realmObjectSource.realmGet$store_name());

        com.geogreenapps.apps.indukaan.classes.Images imagesObj = realmObjectSource.realmGet$images();
        if (imagesObj == null) {
            realmObjectCopy.realmSet$images(null);
        } else {
            com.geogreenapps.apps.indukaan.classes.Images cacheimages = (com.geogreenapps.apps.indukaan.classes.Images) cache.get(imagesObj);
            if (cacheimages != null) {
                realmObjectCopy.realmSet$images(cacheimages);
            } else {
                realmObjectCopy.realmSet$images(ImagesRealmProxy.copyOrUpdate(realm, imagesObj, update, cache));
            }
        }

        RealmList<com.geogreenapps.apps.indukaan.classes.Images> listImagesList = realmObjectSource.realmGet$listImages();
        if (listImagesList != null) {
            RealmList<com.geogreenapps.apps.indukaan.classes.Images> listImagesRealmList = realmObjectCopy.realmGet$listImages();
            listImagesRealmList.clear();
            for (int i = 0; i < listImagesList.size(); i++) {
                com.geogreenapps.apps.indukaan.classes.Images listImagesItem = listImagesList.get(i);
                com.geogreenapps.apps.indukaan.classes.Images cachelistImages = (com.geogreenapps.apps.indukaan.classes.Images) cache.get(listImagesItem);
                if (cachelistImages != null) {
                    listImagesRealmList.add(cachelistImages);
                } else {
                    listImagesRealmList.add(ImagesRealmProxy.copyOrUpdate(realm, listImagesItem, update, cache));
                }
            }
        }

        realmObjectCopy.realmSet$distance(realmObjectSource.realmGet$distance());
        realmObjectCopy.realmSet$lat(realmObjectSource.realmGet$lat());
        realmObjectCopy.realmSet$lng(realmObjectSource.realmGet$lng());
        realmObjectCopy.realmSet$featured(realmObjectSource.realmGet$featured());
        realmObjectCopy.realmSet$link(realmObjectSource.realmGet$link());
        realmObjectCopy.realmSet$description(realmObjectSource.realmGet$description());
        realmObjectCopy.realmSet$short_description(realmObjectSource.realmGet$short_description());
        realmObjectCopy.realmSet$product_type(realmObjectSource.realmGet$product_type());
        realmObjectCopy.realmSet$product_value(realmObjectSource.realmGet$product_value());
        realmObjectCopy.realmSet$tags(realmObjectSource.realmGet$tags());
        realmObjectCopy.realmSet$is_deal(realmObjectSource.realmGet$is_deal());
        realmObjectCopy.realmSet$order_enabled(realmObjectSource.realmGet$order_enabled());
        realmObjectCopy.realmSet$qty_enabled(realmObjectSource.realmGet$qty_enabled());
        realmObjectCopy.realmSet$order_button(realmObjectSource.realmGet$order_button());
        realmObjectCopy.realmSet$cf_id(realmObjectSource.realmGet$cf_id());

        RealmList<com.geogreenapps.apps.indukaan.classes.CF> cfList = realmObjectSource.realmGet$cf();
        if (cfList != null) {
            RealmList<com.geogreenapps.apps.indukaan.classes.CF> cfRealmList = realmObjectCopy.realmGet$cf();
            cfRealmList.clear();
            for (int i = 0; i < cfList.size(); i++) {
                com.geogreenapps.apps.indukaan.classes.CF cfItem = cfList.get(i);
                com.geogreenapps.apps.indukaan.classes.CF cachecf = (com.geogreenapps.apps.indukaan.classes.CF) cache.get(cfItem);
                if (cachecf != null) {
                    cfRealmList.add(cachecf);
                } else {
                    cfRealmList.add(CFRealmProxy.copyOrUpdate(realm, cfItem, update, cache));
                }
            }
        }

        realmObjectCopy.realmSet$commission(realmObjectSource.realmGet$commission());
        realmObjectCopy.realmSet$stock(realmObjectSource.realmGet$stock());
        realmObjectCopy.realmSet$is_offer(realmObjectSource.realmGet$is_offer());

        RealmList<com.geogreenapps.apps.indukaan.classes.Variant> variantsList = realmObjectSource.realmGet$variants();
        if (variantsList != null) {
            RealmList<com.geogreenapps.apps.indukaan.classes.Variant> variantsRealmList = realmObjectCopy.realmGet$variants();
            variantsRealmList.clear();
            for (int i = 0; i < variantsList.size(); i++) {
                com.geogreenapps.apps.indukaan.classes.Variant variantsItem = variantsList.get(i);
                com.geogreenapps.apps.indukaan.classes.Variant cachevariants = (com.geogreenapps.apps.indukaan.classes.Variant) cache.get(variantsItem);
                if (cachevariants != null) {
                    variantsRealmList.add(cachevariants);
                } else {
                    variantsRealmList.add(VariantRealmProxy.copyOrUpdate(realm, variantsItem, update, cache));
                }
            }
        }

        return realmObject;
    }

    public static long insert(Realm realm, com.geogreenapps.apps.indukaan.classes.Offer object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Offer.class);
        long tableNativePtr = table.getNativePtr();
        OfferColumnInfo columnInfo = (OfferColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Offer.class);
        long pkColumnIndex = columnInfo.idIndex;
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((OfferRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((OfferRealmProxyInterface) object).realmGet$id());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((OfferRealmProxyInterface) object).realmGet$id());
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);

        com.geogreenapps.apps.indukaan.classes.Currency currencyObj = ((OfferRealmProxyInterface) object).realmGet$currency();
        if (currencyObj != null) {
            Long cachecurrency = cache.get(currencyObj);
            if (cachecurrency == null) {
                cachecurrency = CurrencyRealmProxy.insert(realm, currencyObj, cache);
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.currencyIndex, rowIndex, cachecurrency, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.store_idIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$store_id(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.user_idIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$user_id(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.statusIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$status(), false);
        String realmGet$date_start = ((OfferRealmProxyInterface) object).realmGet$date_start();
        if (realmGet$date_start != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.date_startIndex, rowIndex, realmGet$date_start, false);
        }
        String realmGet$date_end = ((OfferRealmProxyInterface) object).realmGet$date_end();
        if (realmGet$date_end != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.date_endIndex, rowIndex, realmGet$date_end, false);
        }
        String realmGet$name = ((OfferRealmProxyInterface) object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        String realmGet$store_name = ((OfferRealmProxyInterface) object).realmGet$store_name();
        if (realmGet$store_name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.store_nameIndex, rowIndex, realmGet$store_name, false);
        }

        com.geogreenapps.apps.indukaan.classes.Images imagesObj = ((OfferRealmProxyInterface) object).realmGet$images();
        if (imagesObj != null) {
            Long cacheimages = cache.get(imagesObj);
            if (cacheimages == null) {
                cacheimages = ImagesRealmProxy.insert(realm, imagesObj, cache);
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.imagesIndex, rowIndex, cacheimages, false);
        }

        RealmList<com.geogreenapps.apps.indukaan.classes.Images> listImagesList = ((OfferRealmProxyInterface) object).realmGet$listImages();
        if (listImagesList != null) {
            OsList listImagesOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.listImagesIndex);
            for (com.geogreenapps.apps.indukaan.classes.Images listImagesItem : listImagesList) {
                Long cacheItemIndexlistImages = cache.get(listImagesItem);
                if (cacheItemIndexlistImages == null) {
                    cacheItemIndexlistImages = ImagesRealmProxy.insert(realm, listImagesItem, cache);
                }
                listImagesOsList.addRow(cacheItemIndexlistImages);
            }
        }
        Double realmGet$distance = ((OfferRealmProxyInterface) object).realmGet$distance();
        if (realmGet$distance != null) {
            Table.nativeSetDouble(tableNativePtr, columnInfo.distanceIndex, rowIndex, realmGet$distance, false);
        }
        Double realmGet$lat = ((OfferRealmProxyInterface) object).realmGet$lat();
        if (realmGet$lat != null) {
            Table.nativeSetDouble(tableNativePtr, columnInfo.latIndex, rowIndex, realmGet$lat, false);
        }
        Double realmGet$lng = ((OfferRealmProxyInterface) object).realmGet$lng();
        if (realmGet$lng != null) {
            Table.nativeSetDouble(tableNativePtr, columnInfo.lngIndex, rowIndex, realmGet$lng, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.featuredIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$featured(), false);
        String realmGet$link = ((OfferRealmProxyInterface) object).realmGet$link();
        if (realmGet$link != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.linkIndex, rowIndex, realmGet$link, false);
        }
        String realmGet$description = ((OfferRealmProxyInterface) object).realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.descriptionIndex, rowIndex, realmGet$description, false);
        }
        String realmGet$short_description = ((OfferRealmProxyInterface) object).realmGet$short_description();
        if (realmGet$short_description != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.short_descriptionIndex, rowIndex, realmGet$short_description, false);
        }
        String realmGet$product_type = ((OfferRealmProxyInterface) object).realmGet$product_type();
        if (realmGet$product_type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.product_typeIndex, rowIndex, realmGet$product_type, false);
        }
        Table.nativeSetFloat(tableNativePtr, columnInfo.product_valueIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$product_value(), false);
        String realmGet$tags = ((OfferRealmProxyInterface) object).realmGet$tags();
        if (realmGet$tags != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.tagsIndex, rowIndex, realmGet$tags, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.is_dealIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$is_deal(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.order_enabledIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$order_enabled(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.qty_enabledIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$qty_enabled(), false);
        String realmGet$order_button = ((OfferRealmProxyInterface) object).realmGet$order_button();
        if (realmGet$order_button != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.order_buttonIndex, rowIndex, realmGet$order_button, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.cf_idIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$cf_id(), false);

        RealmList<com.geogreenapps.apps.indukaan.classes.CF> cfList = ((OfferRealmProxyInterface) object).realmGet$cf();
        if (cfList != null) {
            OsList cfOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.cfIndex);
            for (com.geogreenapps.apps.indukaan.classes.CF cfItem : cfList) {
                Long cacheItemIndexcf = cache.get(cfItem);
                if (cacheItemIndexcf == null) {
                    cacheItemIndexcf = CFRealmProxy.insert(realm, cfItem, cache);
                }
                cfOsList.addRow(cacheItemIndexcf);
            }
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.commissionIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$commission(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.stockIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$stock(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.is_offerIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$is_offer(), false);

        RealmList<com.geogreenapps.apps.indukaan.classes.Variant> variantsList = ((OfferRealmProxyInterface) object).realmGet$variants();
        if (variantsList != null) {
            OsList variantsOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.variantsIndex);
            for (com.geogreenapps.apps.indukaan.classes.Variant variantsItem : variantsList) {
                Long cacheItemIndexvariants = cache.get(variantsItem);
                if (cacheItemIndexvariants == null) {
                    cacheItemIndexvariants = VariantRealmProxy.insert(realm, variantsItem, cache);
                }
                variantsOsList.addRow(cacheItemIndexvariants);
            }
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Offer.class);
        long tableNativePtr = table.getNativePtr();
        OfferColumnInfo columnInfo = (OfferColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Offer.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.geogreenapps.apps.indukaan.classes.Offer object = null;
        while (objects.hasNext()) {
            object = (com.geogreenapps.apps.indukaan.classes.Offer) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((OfferRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((OfferRealmProxyInterface) object).realmGet$id());
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((OfferRealmProxyInterface) object).realmGet$id());
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, rowIndex);

            com.geogreenapps.apps.indukaan.classes.Currency currencyObj = ((OfferRealmProxyInterface) object).realmGet$currency();
            if (currencyObj != null) {
                Long cachecurrency = cache.get(currencyObj);
                if (cachecurrency == null) {
                    cachecurrency = CurrencyRealmProxy.insert(realm, currencyObj, cache);
                }
                table.setLink(columnInfo.currencyIndex, rowIndex, cachecurrency, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.store_idIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$store_id(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.user_idIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$user_id(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.statusIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$status(), false);
            String realmGet$date_start = ((OfferRealmProxyInterface) object).realmGet$date_start();
            if (realmGet$date_start != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.date_startIndex, rowIndex, realmGet$date_start, false);
            }
            String realmGet$date_end = ((OfferRealmProxyInterface) object).realmGet$date_end();
            if (realmGet$date_end != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.date_endIndex, rowIndex, realmGet$date_end, false);
            }
            String realmGet$name = ((OfferRealmProxyInterface) object).realmGet$name();
            if (realmGet$name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
            }
            String realmGet$store_name = ((OfferRealmProxyInterface) object).realmGet$store_name();
            if (realmGet$store_name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.store_nameIndex, rowIndex, realmGet$store_name, false);
            }

            com.geogreenapps.apps.indukaan.classes.Images imagesObj = ((OfferRealmProxyInterface) object).realmGet$images();
            if (imagesObj != null) {
                Long cacheimages = cache.get(imagesObj);
                if (cacheimages == null) {
                    cacheimages = ImagesRealmProxy.insert(realm, imagesObj, cache);
                }
                table.setLink(columnInfo.imagesIndex, rowIndex, cacheimages, false);
            }

            RealmList<com.geogreenapps.apps.indukaan.classes.Images> listImagesList = ((OfferRealmProxyInterface) object).realmGet$listImages();
            if (listImagesList != null) {
                OsList listImagesOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.listImagesIndex);
                for (com.geogreenapps.apps.indukaan.classes.Images listImagesItem : listImagesList) {
                    Long cacheItemIndexlistImages = cache.get(listImagesItem);
                    if (cacheItemIndexlistImages == null) {
                        cacheItemIndexlistImages = ImagesRealmProxy.insert(realm, listImagesItem, cache);
                    }
                    listImagesOsList.addRow(cacheItemIndexlistImages);
                }
            }
            Double realmGet$distance = ((OfferRealmProxyInterface) object).realmGet$distance();
            if (realmGet$distance != null) {
                Table.nativeSetDouble(tableNativePtr, columnInfo.distanceIndex, rowIndex, realmGet$distance, false);
            }
            Double realmGet$lat = ((OfferRealmProxyInterface) object).realmGet$lat();
            if (realmGet$lat != null) {
                Table.nativeSetDouble(tableNativePtr, columnInfo.latIndex, rowIndex, realmGet$lat, false);
            }
            Double realmGet$lng = ((OfferRealmProxyInterface) object).realmGet$lng();
            if (realmGet$lng != null) {
                Table.nativeSetDouble(tableNativePtr, columnInfo.lngIndex, rowIndex, realmGet$lng, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.featuredIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$featured(), false);
            String realmGet$link = ((OfferRealmProxyInterface) object).realmGet$link();
            if (realmGet$link != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.linkIndex, rowIndex, realmGet$link, false);
            }
            String realmGet$description = ((OfferRealmProxyInterface) object).realmGet$description();
            if (realmGet$description != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.descriptionIndex, rowIndex, realmGet$description, false);
            }
            String realmGet$short_description = ((OfferRealmProxyInterface) object).realmGet$short_description();
            if (realmGet$short_description != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.short_descriptionIndex, rowIndex, realmGet$short_description, false);
            }
            String realmGet$product_type = ((OfferRealmProxyInterface) object).realmGet$product_type();
            if (realmGet$product_type != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.product_typeIndex, rowIndex, realmGet$product_type, false);
            }
            Table.nativeSetFloat(tableNativePtr, columnInfo.product_valueIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$product_value(), false);
            String realmGet$tags = ((OfferRealmProxyInterface) object).realmGet$tags();
            if (realmGet$tags != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.tagsIndex, rowIndex, realmGet$tags, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.is_dealIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$is_deal(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.order_enabledIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$order_enabled(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.qty_enabledIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$qty_enabled(), false);
            String realmGet$order_button = ((OfferRealmProxyInterface) object).realmGet$order_button();
            if (realmGet$order_button != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.order_buttonIndex, rowIndex, realmGet$order_button, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.cf_idIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$cf_id(), false);

            RealmList<com.geogreenapps.apps.indukaan.classes.CF> cfList = ((OfferRealmProxyInterface) object).realmGet$cf();
            if (cfList != null) {
                OsList cfOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.cfIndex);
                for (com.geogreenapps.apps.indukaan.classes.CF cfItem : cfList) {
                    Long cacheItemIndexcf = cache.get(cfItem);
                    if (cacheItemIndexcf == null) {
                        cacheItemIndexcf = CFRealmProxy.insert(realm, cfItem, cache);
                    }
                    cfOsList.addRow(cacheItemIndexcf);
                }
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.commissionIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$commission(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.stockIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$stock(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.is_offerIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$is_offer(), false);

            RealmList<com.geogreenapps.apps.indukaan.classes.Variant> variantsList = ((OfferRealmProxyInterface) object).realmGet$variants();
            if (variantsList != null) {
                OsList variantsOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.variantsIndex);
                for (com.geogreenapps.apps.indukaan.classes.Variant variantsItem : variantsList) {
                    Long cacheItemIndexvariants = cache.get(variantsItem);
                    if (cacheItemIndexvariants == null) {
                        cacheItemIndexvariants = VariantRealmProxy.insert(realm, variantsItem, cache);
                    }
                    variantsOsList.addRow(cacheItemIndexvariants);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.geogreenapps.apps.indukaan.classes.Offer object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Offer.class);
        long tableNativePtr = table.getNativePtr();
        OfferColumnInfo columnInfo = (OfferColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Offer.class);
        long pkColumnIndex = columnInfo.idIndex;
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((OfferRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((OfferRealmProxyInterface) object).realmGet$id());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((OfferRealmProxyInterface) object).realmGet$id());
        }
        cache.put(object, rowIndex);

        com.geogreenapps.apps.indukaan.classes.Currency currencyObj = ((OfferRealmProxyInterface) object).realmGet$currency();
        if (currencyObj != null) {
            Long cachecurrency = cache.get(currencyObj);
            if (cachecurrency == null) {
                cachecurrency = CurrencyRealmProxy.insertOrUpdate(realm, currencyObj, cache);
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.currencyIndex, rowIndex, cachecurrency, false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.currencyIndex, rowIndex);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.store_idIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$store_id(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.user_idIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$user_id(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.statusIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$status(), false);
        String realmGet$date_start = ((OfferRealmProxyInterface) object).realmGet$date_start();
        if (realmGet$date_start != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.date_startIndex, rowIndex, realmGet$date_start, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.date_startIndex, rowIndex, false);
        }
        String realmGet$date_end = ((OfferRealmProxyInterface) object).realmGet$date_end();
        if (realmGet$date_end != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.date_endIndex, rowIndex, realmGet$date_end, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.date_endIndex, rowIndex, false);
        }
        String realmGet$name = ((OfferRealmProxyInterface) object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
        }
        String realmGet$store_name = ((OfferRealmProxyInterface) object).realmGet$store_name();
        if (realmGet$store_name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.store_nameIndex, rowIndex, realmGet$store_name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.store_nameIndex, rowIndex, false);
        }

        com.geogreenapps.apps.indukaan.classes.Images imagesObj = ((OfferRealmProxyInterface) object).realmGet$images();
        if (imagesObj != null) {
            Long cacheimages = cache.get(imagesObj);
            if (cacheimages == null) {
                cacheimages = ImagesRealmProxy.insertOrUpdate(realm, imagesObj, cache);
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.imagesIndex, rowIndex, cacheimages, false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.imagesIndex, rowIndex);
        }

        OsList listImagesOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.listImagesIndex);
        RealmList<com.geogreenapps.apps.indukaan.classes.Images> listImagesList = ((OfferRealmProxyInterface) object).realmGet$listImages();
        if (listImagesList != null && listImagesList.size() == listImagesOsList.size()) {
            // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
            int objects = listImagesList.size();
            for (int i = 0; i < objects; i++) {
                com.geogreenapps.apps.indukaan.classes.Images listImagesItem = listImagesList.get(i);
                Long cacheItemIndexlistImages = cache.get(listImagesItem);
                if (cacheItemIndexlistImages == null) {
                    cacheItemIndexlistImages = ImagesRealmProxy.insertOrUpdate(realm, listImagesItem, cache);
                }
                listImagesOsList.setRow(i, cacheItemIndexlistImages);
            }
        } else {
            listImagesOsList.removeAll();
            if (listImagesList != null) {
                for (com.geogreenapps.apps.indukaan.classes.Images listImagesItem : listImagesList) {
                    Long cacheItemIndexlistImages = cache.get(listImagesItem);
                    if (cacheItemIndexlistImages == null) {
                        cacheItemIndexlistImages = ImagesRealmProxy.insertOrUpdate(realm, listImagesItem, cache);
                    }
                    listImagesOsList.addRow(cacheItemIndexlistImages);
                }
            }
        }

        Double realmGet$distance = ((OfferRealmProxyInterface) object).realmGet$distance();
        if (realmGet$distance != null) {
            Table.nativeSetDouble(tableNativePtr, columnInfo.distanceIndex, rowIndex, realmGet$distance, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.distanceIndex, rowIndex, false);
        }
        Double realmGet$lat = ((OfferRealmProxyInterface) object).realmGet$lat();
        if (realmGet$lat != null) {
            Table.nativeSetDouble(tableNativePtr, columnInfo.latIndex, rowIndex, realmGet$lat, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.latIndex, rowIndex, false);
        }
        Double realmGet$lng = ((OfferRealmProxyInterface) object).realmGet$lng();
        if (realmGet$lng != null) {
            Table.nativeSetDouble(tableNativePtr, columnInfo.lngIndex, rowIndex, realmGet$lng, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.lngIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.featuredIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$featured(), false);
        String realmGet$link = ((OfferRealmProxyInterface) object).realmGet$link();
        if (realmGet$link != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.linkIndex, rowIndex, realmGet$link, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.linkIndex, rowIndex, false);
        }
        String realmGet$description = ((OfferRealmProxyInterface) object).realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.descriptionIndex, rowIndex, realmGet$description, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.descriptionIndex, rowIndex, false);
        }
        String realmGet$short_description = ((OfferRealmProxyInterface) object).realmGet$short_description();
        if (realmGet$short_description != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.short_descriptionIndex, rowIndex, realmGet$short_description, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.short_descriptionIndex, rowIndex, false);
        }
        String realmGet$product_type = ((OfferRealmProxyInterface) object).realmGet$product_type();
        if (realmGet$product_type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.product_typeIndex, rowIndex, realmGet$product_type, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.product_typeIndex, rowIndex, false);
        }
        Table.nativeSetFloat(tableNativePtr, columnInfo.product_valueIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$product_value(), false);
        String realmGet$tags = ((OfferRealmProxyInterface) object).realmGet$tags();
        if (realmGet$tags != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.tagsIndex, rowIndex, realmGet$tags, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.tagsIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.is_dealIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$is_deal(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.order_enabledIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$order_enabled(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.qty_enabledIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$qty_enabled(), false);
        String realmGet$order_button = ((OfferRealmProxyInterface) object).realmGet$order_button();
        if (realmGet$order_button != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.order_buttonIndex, rowIndex, realmGet$order_button, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.order_buttonIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.cf_idIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$cf_id(), false);

        OsList cfOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.cfIndex);
        RealmList<com.geogreenapps.apps.indukaan.classes.CF> cfList = ((OfferRealmProxyInterface) object).realmGet$cf();
        if (cfList != null && cfList.size() == cfOsList.size()) {
            // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
            int objects = cfList.size();
            for (int i = 0; i < objects; i++) {
                com.geogreenapps.apps.indukaan.classes.CF cfItem = cfList.get(i);
                Long cacheItemIndexcf = cache.get(cfItem);
                if (cacheItemIndexcf == null) {
                    cacheItemIndexcf = CFRealmProxy.insertOrUpdate(realm, cfItem, cache);
                }
                cfOsList.setRow(i, cacheItemIndexcf);
            }
        } else {
            cfOsList.removeAll();
            if (cfList != null) {
                for (com.geogreenapps.apps.indukaan.classes.CF cfItem : cfList) {
                    Long cacheItemIndexcf = cache.get(cfItem);
                    if (cacheItemIndexcf == null) {
                        cacheItemIndexcf = CFRealmProxy.insertOrUpdate(realm, cfItem, cache);
                    }
                    cfOsList.addRow(cacheItemIndexcf);
                }
            }
        }

        Table.nativeSetLong(tableNativePtr, columnInfo.commissionIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$commission(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.stockIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$stock(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.is_offerIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$is_offer(), false);

        OsList variantsOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.variantsIndex);
        RealmList<com.geogreenapps.apps.indukaan.classes.Variant> variantsList = ((OfferRealmProxyInterface) object).realmGet$variants();
        if (variantsList != null && variantsList.size() == variantsOsList.size()) {
            // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
            int objects = variantsList.size();
            for (int i = 0; i < objects; i++) {
                com.geogreenapps.apps.indukaan.classes.Variant variantsItem = variantsList.get(i);
                Long cacheItemIndexvariants = cache.get(variantsItem);
                if (cacheItemIndexvariants == null) {
                    cacheItemIndexvariants = VariantRealmProxy.insertOrUpdate(realm, variantsItem, cache);
                }
                variantsOsList.setRow(i, cacheItemIndexvariants);
            }
        } else {
            variantsOsList.removeAll();
            if (variantsList != null) {
                for (com.geogreenapps.apps.indukaan.classes.Variant variantsItem : variantsList) {
                    Long cacheItemIndexvariants = cache.get(variantsItem);
                    if (cacheItemIndexvariants == null) {
                        cacheItemIndexvariants = VariantRealmProxy.insertOrUpdate(realm, variantsItem, cache);
                    }
                    variantsOsList.addRow(cacheItemIndexvariants);
                }
            }
        }

        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Offer.class);
        long tableNativePtr = table.getNativePtr();
        OfferColumnInfo columnInfo = (OfferColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Offer.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.geogreenapps.apps.indukaan.classes.Offer object = null;
        while (objects.hasNext()) {
            object = (com.geogreenapps.apps.indukaan.classes.Offer) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((OfferRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((OfferRealmProxyInterface) object).realmGet$id());
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((OfferRealmProxyInterface) object).realmGet$id());
            }
            cache.put(object, rowIndex);

            com.geogreenapps.apps.indukaan.classes.Currency currencyObj = ((OfferRealmProxyInterface) object).realmGet$currency();
            if (currencyObj != null) {
                Long cachecurrency = cache.get(currencyObj);
                if (cachecurrency == null) {
                    cachecurrency = CurrencyRealmProxy.insertOrUpdate(realm, currencyObj, cache);
                }
                Table.nativeSetLink(tableNativePtr, columnInfo.currencyIndex, rowIndex, cachecurrency, false);
            } else {
                Table.nativeNullifyLink(tableNativePtr, columnInfo.currencyIndex, rowIndex);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.store_idIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$store_id(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.user_idIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$user_id(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.statusIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$status(), false);
            String realmGet$date_start = ((OfferRealmProxyInterface) object).realmGet$date_start();
            if (realmGet$date_start != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.date_startIndex, rowIndex, realmGet$date_start, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.date_startIndex, rowIndex, false);
            }
            String realmGet$date_end = ((OfferRealmProxyInterface) object).realmGet$date_end();
            if (realmGet$date_end != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.date_endIndex, rowIndex, realmGet$date_end, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.date_endIndex, rowIndex, false);
            }
            String realmGet$name = ((OfferRealmProxyInterface) object).realmGet$name();
            if (realmGet$name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
            }
            String realmGet$store_name = ((OfferRealmProxyInterface) object).realmGet$store_name();
            if (realmGet$store_name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.store_nameIndex, rowIndex, realmGet$store_name, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.store_nameIndex, rowIndex, false);
            }

            com.geogreenapps.apps.indukaan.classes.Images imagesObj = ((OfferRealmProxyInterface) object).realmGet$images();
            if (imagesObj != null) {
                Long cacheimages = cache.get(imagesObj);
                if (cacheimages == null) {
                    cacheimages = ImagesRealmProxy.insertOrUpdate(realm, imagesObj, cache);
                }
                Table.nativeSetLink(tableNativePtr, columnInfo.imagesIndex, rowIndex, cacheimages, false);
            } else {
                Table.nativeNullifyLink(tableNativePtr, columnInfo.imagesIndex, rowIndex);
            }

            OsList listImagesOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.listImagesIndex);
            RealmList<com.geogreenapps.apps.indukaan.classes.Images> listImagesList = ((OfferRealmProxyInterface) object).realmGet$listImages();
            if (listImagesList != null && listImagesList.size() == listImagesOsList.size()) {
                // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
                int objectCount = listImagesList.size();
                for (int i = 0; i < objectCount; i++) {
                    com.geogreenapps.apps.indukaan.classes.Images listImagesItem = listImagesList.get(i);
                    Long cacheItemIndexlistImages = cache.get(listImagesItem);
                    if (cacheItemIndexlistImages == null) {
                        cacheItemIndexlistImages = ImagesRealmProxy.insertOrUpdate(realm, listImagesItem, cache);
                    }
                    listImagesOsList.setRow(i, cacheItemIndexlistImages);
                }
            } else {
                listImagesOsList.removeAll();
                if (listImagesList != null) {
                    for (com.geogreenapps.apps.indukaan.classes.Images listImagesItem : listImagesList) {
                        Long cacheItemIndexlistImages = cache.get(listImagesItem);
                        if (cacheItemIndexlistImages == null) {
                            cacheItemIndexlistImages = ImagesRealmProxy.insertOrUpdate(realm, listImagesItem, cache);
                        }
                        listImagesOsList.addRow(cacheItemIndexlistImages);
                    }
                }
            }

            Double realmGet$distance = ((OfferRealmProxyInterface) object).realmGet$distance();
            if (realmGet$distance != null) {
                Table.nativeSetDouble(tableNativePtr, columnInfo.distanceIndex, rowIndex, realmGet$distance, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.distanceIndex, rowIndex, false);
            }
            Double realmGet$lat = ((OfferRealmProxyInterface) object).realmGet$lat();
            if (realmGet$lat != null) {
                Table.nativeSetDouble(tableNativePtr, columnInfo.latIndex, rowIndex, realmGet$lat, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.latIndex, rowIndex, false);
            }
            Double realmGet$lng = ((OfferRealmProxyInterface) object).realmGet$lng();
            if (realmGet$lng != null) {
                Table.nativeSetDouble(tableNativePtr, columnInfo.lngIndex, rowIndex, realmGet$lng, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.lngIndex, rowIndex, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.featuredIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$featured(), false);
            String realmGet$link = ((OfferRealmProxyInterface) object).realmGet$link();
            if (realmGet$link != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.linkIndex, rowIndex, realmGet$link, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.linkIndex, rowIndex, false);
            }
            String realmGet$description = ((OfferRealmProxyInterface) object).realmGet$description();
            if (realmGet$description != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.descriptionIndex, rowIndex, realmGet$description, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.descriptionIndex, rowIndex, false);
            }
            String realmGet$short_description = ((OfferRealmProxyInterface) object).realmGet$short_description();
            if (realmGet$short_description != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.short_descriptionIndex, rowIndex, realmGet$short_description, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.short_descriptionIndex, rowIndex, false);
            }
            String realmGet$product_type = ((OfferRealmProxyInterface) object).realmGet$product_type();
            if (realmGet$product_type != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.product_typeIndex, rowIndex, realmGet$product_type, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.product_typeIndex, rowIndex, false);
            }
            Table.nativeSetFloat(tableNativePtr, columnInfo.product_valueIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$product_value(), false);
            String realmGet$tags = ((OfferRealmProxyInterface) object).realmGet$tags();
            if (realmGet$tags != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.tagsIndex, rowIndex, realmGet$tags, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.tagsIndex, rowIndex, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.is_dealIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$is_deal(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.order_enabledIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$order_enabled(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.qty_enabledIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$qty_enabled(), false);
            String realmGet$order_button = ((OfferRealmProxyInterface) object).realmGet$order_button();
            if (realmGet$order_button != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.order_buttonIndex, rowIndex, realmGet$order_button, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.order_buttonIndex, rowIndex, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.cf_idIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$cf_id(), false);

            OsList cfOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.cfIndex);
            RealmList<com.geogreenapps.apps.indukaan.classes.CF> cfList = ((OfferRealmProxyInterface) object).realmGet$cf();
            if (cfList != null && cfList.size() == cfOsList.size()) {
                // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
                int objectCount = cfList.size();
                for (int i = 0; i < objectCount; i++) {
                    com.geogreenapps.apps.indukaan.classes.CF cfItem = cfList.get(i);
                    Long cacheItemIndexcf = cache.get(cfItem);
                    if (cacheItemIndexcf == null) {
                        cacheItemIndexcf = CFRealmProxy.insertOrUpdate(realm, cfItem, cache);
                    }
                    cfOsList.setRow(i, cacheItemIndexcf);
                }
            } else {
                cfOsList.removeAll();
                if (cfList != null) {
                    for (com.geogreenapps.apps.indukaan.classes.CF cfItem : cfList) {
                        Long cacheItemIndexcf = cache.get(cfItem);
                        if (cacheItemIndexcf == null) {
                            cacheItemIndexcf = CFRealmProxy.insertOrUpdate(realm, cfItem, cache);
                        }
                        cfOsList.addRow(cacheItemIndexcf);
                    }
                }
            }

            Table.nativeSetLong(tableNativePtr, columnInfo.commissionIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$commission(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.stockIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$stock(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.is_offerIndex, rowIndex, ((OfferRealmProxyInterface) object).realmGet$is_offer(), false);

            OsList variantsOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.variantsIndex);
            RealmList<com.geogreenapps.apps.indukaan.classes.Variant> variantsList = ((OfferRealmProxyInterface) object).realmGet$variants();
            if (variantsList != null && variantsList.size() == variantsOsList.size()) {
                // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
                int objectCount = variantsList.size();
                for (int i = 0; i < objectCount; i++) {
                    com.geogreenapps.apps.indukaan.classes.Variant variantsItem = variantsList.get(i);
                    Long cacheItemIndexvariants = cache.get(variantsItem);
                    if (cacheItemIndexvariants == null) {
                        cacheItemIndexvariants = VariantRealmProxy.insertOrUpdate(realm, variantsItem, cache);
                    }
                    variantsOsList.setRow(i, cacheItemIndexvariants);
                }
            } else {
                variantsOsList.removeAll();
                if (variantsList != null) {
                    for (com.geogreenapps.apps.indukaan.classes.Variant variantsItem : variantsList) {
                        Long cacheItemIndexvariants = cache.get(variantsItem);
                        if (cacheItemIndexvariants == null) {
                            cacheItemIndexvariants = VariantRealmProxy.insertOrUpdate(realm, variantsItem, cache);
                        }
                        variantsOsList.addRow(cacheItemIndexvariants);
                    }
                }
            }

        }
    }

    public static com.geogreenapps.apps.indukaan.classes.Offer createDetachedCopy(com.geogreenapps.apps.indukaan.classes.Offer realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.geogreenapps.apps.indukaan.classes.Offer unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.geogreenapps.apps.indukaan.classes.Offer();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.geogreenapps.apps.indukaan.classes.Offer) cachedObject.object;
            }
            unmanagedObject = (com.geogreenapps.apps.indukaan.classes.Offer) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        OfferRealmProxyInterface unmanagedCopy = (OfferRealmProxyInterface) unmanagedObject;
        OfferRealmProxyInterface realmSource = (OfferRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());

        // Deep copy of currency
        unmanagedCopy.realmSet$currency(CurrencyRealmProxy.createDetachedCopy(realmSource.realmGet$currency(), currentDepth + 1, maxDepth, cache));
        unmanagedCopy.realmSet$store_id(realmSource.realmGet$store_id());
        unmanagedCopy.realmSet$user_id(realmSource.realmGet$user_id());
        unmanagedCopy.realmSet$status(realmSource.realmGet$status());
        unmanagedCopy.realmSet$date_start(realmSource.realmGet$date_start());
        unmanagedCopy.realmSet$date_end(realmSource.realmGet$date_end());
        unmanagedCopy.realmSet$name(realmSource.realmGet$name());
        unmanagedCopy.realmSet$store_name(realmSource.realmGet$store_name());

        // Deep copy of images
        unmanagedCopy.realmSet$images(ImagesRealmProxy.createDetachedCopy(realmSource.realmGet$images(), currentDepth + 1, maxDepth, cache));

        // Deep copy of listImages
        if (currentDepth == maxDepth) {
            unmanagedCopy.realmSet$listImages(null);
        } else {
            RealmList<com.geogreenapps.apps.indukaan.classes.Images> managedlistImagesList = realmSource.realmGet$listImages();
            RealmList<com.geogreenapps.apps.indukaan.classes.Images> unmanagedlistImagesList = new RealmList<com.geogreenapps.apps.indukaan.classes.Images>();
            unmanagedCopy.realmSet$listImages(unmanagedlistImagesList);
            int nextDepth = currentDepth + 1;
            int size = managedlistImagesList.size();
            for (int i = 0; i < size; i++) {
                com.geogreenapps.apps.indukaan.classes.Images item = ImagesRealmProxy.createDetachedCopy(managedlistImagesList.get(i), nextDepth, maxDepth, cache);
                unmanagedlistImagesList.add(item);
            }
        }
        unmanagedCopy.realmSet$distance(realmSource.realmGet$distance());
        unmanagedCopy.realmSet$lat(realmSource.realmGet$lat());
        unmanagedCopy.realmSet$lng(realmSource.realmGet$lng());
        unmanagedCopy.realmSet$featured(realmSource.realmGet$featured());
        unmanagedCopy.realmSet$link(realmSource.realmGet$link());
        unmanagedCopy.realmSet$description(realmSource.realmGet$description());
        unmanagedCopy.realmSet$short_description(realmSource.realmGet$short_description());
        unmanagedCopy.realmSet$product_type(realmSource.realmGet$product_type());
        unmanagedCopy.realmSet$product_value(realmSource.realmGet$product_value());
        unmanagedCopy.realmSet$tags(realmSource.realmGet$tags());
        unmanagedCopy.realmSet$is_deal(realmSource.realmGet$is_deal());
        unmanagedCopy.realmSet$order_enabled(realmSource.realmGet$order_enabled());
        unmanagedCopy.realmSet$qty_enabled(realmSource.realmGet$qty_enabled());
        unmanagedCopy.realmSet$order_button(realmSource.realmGet$order_button());
        unmanagedCopy.realmSet$cf_id(realmSource.realmGet$cf_id());

        // Deep copy of cf
        if (currentDepth == maxDepth) {
            unmanagedCopy.realmSet$cf(null);
        } else {
            RealmList<com.geogreenapps.apps.indukaan.classes.CF> managedcfList = realmSource.realmGet$cf();
            RealmList<com.geogreenapps.apps.indukaan.classes.CF> unmanagedcfList = new RealmList<com.geogreenapps.apps.indukaan.classes.CF>();
            unmanagedCopy.realmSet$cf(unmanagedcfList);
            int nextDepth = currentDepth + 1;
            int size = managedcfList.size();
            for (int i = 0; i < size; i++) {
                com.geogreenapps.apps.indukaan.classes.CF item = CFRealmProxy.createDetachedCopy(managedcfList.get(i), nextDepth, maxDepth, cache);
                unmanagedcfList.add(item);
            }
        }
        unmanagedCopy.realmSet$commission(realmSource.realmGet$commission());
        unmanagedCopy.realmSet$stock(realmSource.realmGet$stock());
        unmanagedCopy.realmSet$is_offer(realmSource.realmGet$is_offer());

        // Deep copy of variants
        if (currentDepth == maxDepth) {
            unmanagedCopy.realmSet$variants(null);
        } else {
            RealmList<com.geogreenapps.apps.indukaan.classes.Variant> managedvariantsList = realmSource.realmGet$variants();
            RealmList<com.geogreenapps.apps.indukaan.classes.Variant> unmanagedvariantsList = new RealmList<com.geogreenapps.apps.indukaan.classes.Variant>();
            unmanagedCopy.realmSet$variants(unmanagedvariantsList);
            int nextDepth = currentDepth + 1;
            int size = managedvariantsList.size();
            for (int i = 0; i < size; i++) {
                com.geogreenapps.apps.indukaan.classes.Variant item = VariantRealmProxy.createDetachedCopy(managedvariantsList.get(i), nextDepth, maxDepth, cache);
                unmanagedvariantsList.add(item);
            }
        }

        return unmanagedObject;
    }

    static com.geogreenapps.apps.indukaan.classes.Offer update(Realm realm, com.geogreenapps.apps.indukaan.classes.Offer realmObject, com.geogreenapps.apps.indukaan.classes.Offer newObject, Map<RealmModel, RealmObjectProxy> cache) {
        OfferRealmProxyInterface realmObjectTarget = (OfferRealmProxyInterface) realmObject;
        OfferRealmProxyInterface realmObjectSource = (OfferRealmProxyInterface) newObject;
        com.geogreenapps.apps.indukaan.classes.Currency currencyObj = realmObjectSource.realmGet$currency();
        if (currencyObj == null) {
            realmObjectTarget.realmSet$currency(null);
        } else {
            com.geogreenapps.apps.indukaan.classes.Currency cachecurrency = (com.geogreenapps.apps.indukaan.classes.Currency) cache.get(currencyObj);
            if (cachecurrency != null) {
                realmObjectTarget.realmSet$currency(cachecurrency);
            } else {
                realmObjectTarget.realmSet$currency(CurrencyRealmProxy.copyOrUpdate(realm, currencyObj, true, cache));
            }
        }
        realmObjectTarget.realmSet$store_id(realmObjectSource.realmGet$store_id());
        realmObjectTarget.realmSet$user_id(realmObjectSource.realmGet$user_id());
        realmObjectTarget.realmSet$status(realmObjectSource.realmGet$status());
        realmObjectTarget.realmSet$date_start(realmObjectSource.realmGet$date_start());
        realmObjectTarget.realmSet$date_end(realmObjectSource.realmGet$date_end());
        realmObjectTarget.realmSet$name(realmObjectSource.realmGet$name());
        realmObjectTarget.realmSet$store_name(realmObjectSource.realmGet$store_name());
        com.geogreenapps.apps.indukaan.classes.Images imagesObj = realmObjectSource.realmGet$images();
        if (imagesObj == null) {
            realmObjectTarget.realmSet$images(null);
        } else {
            com.geogreenapps.apps.indukaan.classes.Images cacheimages = (com.geogreenapps.apps.indukaan.classes.Images) cache.get(imagesObj);
            if (cacheimages != null) {
                realmObjectTarget.realmSet$images(cacheimages);
            } else {
                realmObjectTarget.realmSet$images(ImagesRealmProxy.copyOrUpdate(realm, imagesObj, true, cache));
            }
        }
        RealmList<com.geogreenapps.apps.indukaan.classes.Images> listImagesList = realmObjectSource.realmGet$listImages();
        RealmList<com.geogreenapps.apps.indukaan.classes.Images> listImagesRealmList = realmObjectTarget.realmGet$listImages();
        if (listImagesList != null && listImagesList.size() == listImagesRealmList.size()) {
            // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
            int objects = listImagesList.size();
            for (int i = 0; i < objects; i++) {
                com.geogreenapps.apps.indukaan.classes.Images listImagesItem = listImagesList.get(i);
                com.geogreenapps.apps.indukaan.classes.Images cachelistImages = (com.geogreenapps.apps.indukaan.classes.Images) cache.get(listImagesItem);
                if (cachelistImages != null) {
                    listImagesRealmList.set(i, cachelistImages);
                } else {
                    listImagesRealmList.set(i, ImagesRealmProxy.copyOrUpdate(realm, listImagesItem, true, cache));
                }
            }
        } else {
            listImagesRealmList.clear();
            if (listImagesList != null) {
                for (int i = 0; i < listImagesList.size(); i++) {
                    com.geogreenapps.apps.indukaan.classes.Images listImagesItem = listImagesList.get(i);
                    com.geogreenapps.apps.indukaan.classes.Images cachelistImages = (com.geogreenapps.apps.indukaan.classes.Images) cache.get(listImagesItem);
                    if (cachelistImages != null) {
                        listImagesRealmList.add(cachelistImages);
                    } else {
                        listImagesRealmList.add(ImagesRealmProxy.copyOrUpdate(realm, listImagesItem, true, cache));
                    }
                }
            }
        }
        realmObjectTarget.realmSet$distance(realmObjectSource.realmGet$distance());
        realmObjectTarget.realmSet$lat(realmObjectSource.realmGet$lat());
        realmObjectTarget.realmSet$lng(realmObjectSource.realmGet$lng());
        realmObjectTarget.realmSet$featured(realmObjectSource.realmGet$featured());
        realmObjectTarget.realmSet$link(realmObjectSource.realmGet$link());
        realmObjectTarget.realmSet$description(realmObjectSource.realmGet$description());
        realmObjectTarget.realmSet$short_description(realmObjectSource.realmGet$short_description());
        realmObjectTarget.realmSet$product_type(realmObjectSource.realmGet$product_type());
        realmObjectTarget.realmSet$product_value(realmObjectSource.realmGet$product_value());
        realmObjectTarget.realmSet$tags(realmObjectSource.realmGet$tags());
        realmObjectTarget.realmSet$is_deal(realmObjectSource.realmGet$is_deal());
        realmObjectTarget.realmSet$order_enabled(realmObjectSource.realmGet$order_enabled());
        realmObjectTarget.realmSet$qty_enabled(realmObjectSource.realmGet$qty_enabled());
        realmObjectTarget.realmSet$order_button(realmObjectSource.realmGet$order_button());
        realmObjectTarget.realmSet$cf_id(realmObjectSource.realmGet$cf_id());
        RealmList<com.geogreenapps.apps.indukaan.classes.CF> cfList = realmObjectSource.realmGet$cf();
        RealmList<com.geogreenapps.apps.indukaan.classes.CF> cfRealmList = realmObjectTarget.realmGet$cf();
        if (cfList != null && cfList.size() == cfRealmList.size()) {
            // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
            int objects = cfList.size();
            for (int i = 0; i < objects; i++) {
                com.geogreenapps.apps.indukaan.classes.CF cfItem = cfList.get(i);
                com.geogreenapps.apps.indukaan.classes.CF cachecf = (com.geogreenapps.apps.indukaan.classes.CF) cache.get(cfItem);
                if (cachecf != null) {
                    cfRealmList.set(i, cachecf);
                } else {
                    cfRealmList.set(i, CFRealmProxy.copyOrUpdate(realm, cfItem, true, cache));
                }
            }
        } else {
            cfRealmList.clear();
            if (cfList != null) {
                for (int i = 0; i < cfList.size(); i++) {
                    com.geogreenapps.apps.indukaan.classes.CF cfItem = cfList.get(i);
                    com.geogreenapps.apps.indukaan.classes.CF cachecf = (com.geogreenapps.apps.indukaan.classes.CF) cache.get(cfItem);
                    if (cachecf != null) {
                        cfRealmList.add(cachecf);
                    } else {
                        cfRealmList.add(CFRealmProxy.copyOrUpdate(realm, cfItem, true, cache));
                    }
                }
            }
        }
        realmObjectTarget.realmSet$commission(realmObjectSource.realmGet$commission());
        realmObjectTarget.realmSet$stock(realmObjectSource.realmGet$stock());
        realmObjectTarget.realmSet$is_offer(realmObjectSource.realmGet$is_offer());
        RealmList<com.geogreenapps.apps.indukaan.classes.Variant> variantsList = realmObjectSource.realmGet$variants();
        RealmList<com.geogreenapps.apps.indukaan.classes.Variant> variantsRealmList = realmObjectTarget.realmGet$variants();
        if (variantsList != null && variantsList.size() == variantsRealmList.size()) {
            // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
            int objects = variantsList.size();
            for (int i = 0; i < objects; i++) {
                com.geogreenapps.apps.indukaan.classes.Variant variantsItem = variantsList.get(i);
                com.geogreenapps.apps.indukaan.classes.Variant cachevariants = (com.geogreenapps.apps.indukaan.classes.Variant) cache.get(variantsItem);
                if (cachevariants != null) {
                    variantsRealmList.set(i, cachevariants);
                } else {
                    variantsRealmList.set(i, VariantRealmProxy.copyOrUpdate(realm, variantsItem, true, cache));
                }
            }
        } else {
            variantsRealmList.clear();
            if (variantsList != null) {
                for (int i = 0; i < variantsList.size(); i++) {
                    com.geogreenapps.apps.indukaan.classes.Variant variantsItem = variantsList.get(i);
                    com.geogreenapps.apps.indukaan.classes.Variant cachevariants = (com.geogreenapps.apps.indukaan.classes.Variant) cache.get(variantsItem);
                    if (cachevariants != null) {
                        variantsRealmList.add(cachevariants);
                    } else {
                        variantsRealmList.add(VariantRealmProxy.copyOrUpdate(realm, variantsItem, true, cache));
                    }
                }
            }
        }
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Offer = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{currency:");
        stringBuilder.append(realmGet$currency() != null ? "Currency" : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{store_id:");
        stringBuilder.append(realmGet$store_id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{user_id:");
        stringBuilder.append(realmGet$user_id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{status:");
        stringBuilder.append(realmGet$status());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{date_start:");
        stringBuilder.append(realmGet$date_start() != null ? realmGet$date_start() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{date_end:");
        stringBuilder.append(realmGet$date_end() != null ? realmGet$date_end() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name() != null ? realmGet$name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{store_name:");
        stringBuilder.append(realmGet$store_name() != null ? realmGet$store_name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{images:");
        stringBuilder.append(realmGet$images() != null ? "Images" : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{listImages:");
        stringBuilder.append("RealmList<Images>[").append(realmGet$listImages().size()).append("]");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{distance:");
        stringBuilder.append(realmGet$distance() != null ? realmGet$distance() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{lat:");
        stringBuilder.append(realmGet$lat() != null ? realmGet$lat() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{lng:");
        stringBuilder.append(realmGet$lng() != null ? realmGet$lng() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{featured:");
        stringBuilder.append(realmGet$featured());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{link:");
        stringBuilder.append(realmGet$link() != null ? realmGet$link() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{description:");
        stringBuilder.append(realmGet$description() != null ? realmGet$description() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{short_description:");
        stringBuilder.append(realmGet$short_description() != null ? realmGet$short_description() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{product_type:");
        stringBuilder.append(realmGet$product_type() != null ? realmGet$product_type() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{product_value:");
        stringBuilder.append(realmGet$product_value());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{tags:");
        stringBuilder.append(realmGet$tags() != null ? realmGet$tags() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{is_deal:");
        stringBuilder.append(realmGet$is_deal());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{order_enabled:");
        stringBuilder.append(realmGet$order_enabled());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{qty_enabled:");
        stringBuilder.append(realmGet$qty_enabled());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{order_button:");
        stringBuilder.append(realmGet$order_button() != null ? realmGet$order_button() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{cf_id:");
        stringBuilder.append(realmGet$cf_id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{cf:");
        stringBuilder.append("RealmList<CF>[").append(realmGet$cf().size()).append("]");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{commission:");
        stringBuilder.append(realmGet$commission());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{stock:");
        stringBuilder.append(realmGet$stock());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{is_offer:");
        stringBuilder.append(realmGet$is_offer());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{variants:");
        stringBuilder.append("RealmList<Variant>[").append(realmGet$variants().size()).append("]");
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState<?> realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long rowIndex = proxyState.getRow$realm().getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfferRealmProxy aOffer = (OfferRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aOffer.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aOffer.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aOffer.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
