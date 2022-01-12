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
public class ItemRealmProxy extends com.geogreenapps.apps.indukaan.classes.Item
    implements RealmObjectProxy, ItemRealmProxyInterface {

    static final class ItemColumnInfo extends ColumnInfo {
        long idIndex;
        long nameIndex;
        long imageIndex;
        long moduleIndex;
        long qtyIndex;
        long amountIndex;
        long currencyIndex;

        ItemColumnInfo(OsSchemaInfo schemaInfo) {
            super(7);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Item");
            this.idIndex = addColumnDetails("id", objectSchemaInfo);
            this.nameIndex = addColumnDetails("name", objectSchemaInfo);
            this.imageIndex = addColumnDetails("image", objectSchemaInfo);
            this.moduleIndex = addColumnDetails("module", objectSchemaInfo);
            this.qtyIndex = addColumnDetails("qty", objectSchemaInfo);
            this.amountIndex = addColumnDetails("amount", objectSchemaInfo);
            this.currencyIndex = addColumnDetails("currency", objectSchemaInfo);
        }

        ItemColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new ItemColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final ItemColumnInfo src = (ItemColumnInfo) rawSrc;
            final ItemColumnInfo dst = (ItemColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.nameIndex = src.nameIndex;
            dst.imageIndex = src.imageIndex;
            dst.moduleIndex = src.moduleIndex;
            dst.qtyIndex = src.qtyIndex;
            dst.amountIndex = src.amountIndex;
            dst.currencyIndex = src.currencyIndex;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>(7);
        fieldNames.add("id");
        fieldNames.add("name");
        fieldNames.add("image");
        fieldNames.add("module");
        fieldNames.add("qty");
        fieldNames.add("amount");
        fieldNames.add("currency");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    private ItemColumnInfo columnInfo;
    private ProxyState<com.geogreenapps.apps.indukaan.classes.Item> proxyState;

    ItemRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (ItemColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.geogreenapps.apps.indukaan.classes.Item>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$id() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.idIndex);
    }

    @Override
    public void realmSet$id(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.idIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.idIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.idIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.idIndex, value);
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
    public String realmGet$image() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.imageIndex);
    }

    @Override
    public void realmSet$image(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.imageIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.imageIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.imageIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.imageIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$module() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.moduleIndex);
    }

    @Override
    public void realmSet$module(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.moduleIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.moduleIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.moduleIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.moduleIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$qty() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.qtyIndex);
    }

    @Override
    public void realmSet$qty(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.qtyIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.qtyIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public double realmGet$amount() {
        proxyState.getRealm$realm().checkIfValid();
        return (double) proxyState.getRow$realm().getDouble(columnInfo.amountIndex);
    }

    @Override
    public void realmSet$amount(double value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setDouble(columnInfo.amountIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setDouble(columnInfo.amountIndex, value);
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

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("Item", 7, 0);
        builder.addPersistedProperty("id", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("name", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("image", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("module", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("qty", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("amount", RealmFieldType.DOUBLE, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedLinkProperty("currency", RealmFieldType.OBJECT, "Currency");
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static ItemColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new ItemColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "Item";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.geogreenapps.apps.indukaan.classes.Item createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = new ArrayList<String>(1);
        if (json.has("currency")) {
            excludeFields.add("currency");
        }
        com.geogreenapps.apps.indukaan.classes.Item obj = realm.createObjectInternal(com.geogreenapps.apps.indukaan.classes.Item.class, true, excludeFields);

        final ItemRealmProxyInterface objProxy = (ItemRealmProxyInterface) obj;
        if (json.has("id")) {
            if (json.isNull("id")) {
                objProxy.realmSet$id(null);
            } else {
                objProxy.realmSet$id((String) json.getString("id"));
            }
        }
        if (json.has("name")) {
            if (json.isNull("name")) {
                objProxy.realmSet$name(null);
            } else {
                objProxy.realmSet$name((String) json.getString("name"));
            }
        }
        if (json.has("image")) {
            if (json.isNull("image")) {
                objProxy.realmSet$image(null);
            } else {
                objProxy.realmSet$image((String) json.getString("image"));
            }
        }
        if (json.has("module")) {
            if (json.isNull("module")) {
                objProxy.realmSet$module(null);
            } else {
                objProxy.realmSet$module((String) json.getString("module"));
            }
        }
        if (json.has("qty")) {
            if (json.isNull("qty")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'qty' to null.");
            } else {
                objProxy.realmSet$qty((int) json.getInt("qty"));
            }
        }
        if (json.has("amount")) {
            if (json.isNull("amount")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'amount' to null.");
            } else {
                objProxy.realmSet$amount((double) json.getDouble("amount"));
            }
        }
        if (json.has("currency")) {
            if (json.isNull("currency")) {
                objProxy.realmSet$currency(null);
            } else {
                com.geogreenapps.apps.indukaan.classes.Currency currencyObj = CurrencyRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("currency"), update);
                objProxy.realmSet$currency(currencyObj);
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.geogreenapps.apps.indukaan.classes.Item createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        final com.geogreenapps.apps.indukaan.classes.Item obj = new com.geogreenapps.apps.indukaan.classes.Item();
        final ItemRealmProxyInterface objProxy = (ItemRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$id((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$id(null);
                }
            } else if (name.equals("name")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$name((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$name(null);
                }
            } else if (name.equals("image")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$image((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$image(null);
                }
            } else if (name.equals("module")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$module((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$module(null);
                }
            } else if (name.equals("qty")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$qty((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'qty' to null.");
                }
            } else if (name.equals("amount")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$amount((double) reader.nextDouble());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'amount' to null.");
                }
            } else if (name.equals("currency")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$currency(null);
                } else {
                    com.geogreenapps.apps.indukaan.classes.Currency currencyObj = CurrencyRealmProxy.createUsingJsonStream(realm, reader);
                    objProxy.realmSet$currency(currencyObj);
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return realm.copyToRealm(obj);
    }

    public static com.geogreenapps.apps.indukaan.classes.Item copyOrUpdate(Realm realm, com.geogreenapps.apps.indukaan.classes.Item object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
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
            return (com.geogreenapps.apps.indukaan.classes.Item) cachedRealmObject;
        }

        return copy(realm, object, update, cache);
    }

    public static com.geogreenapps.apps.indukaan.classes.Item copy(Realm realm, com.geogreenapps.apps.indukaan.classes.Item newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.geogreenapps.apps.indukaan.classes.Item) cachedRealmObject;
        }

        // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
        com.geogreenapps.apps.indukaan.classes.Item realmObject = realm.createObjectInternal(com.geogreenapps.apps.indukaan.classes.Item.class, false, Collections.<String>emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);

        ItemRealmProxyInterface realmObjectSource = (ItemRealmProxyInterface) newObject;
        ItemRealmProxyInterface realmObjectCopy = (ItemRealmProxyInterface) realmObject;

        realmObjectCopy.realmSet$id(realmObjectSource.realmGet$id());
        realmObjectCopy.realmSet$name(realmObjectSource.realmGet$name());
        realmObjectCopy.realmSet$image(realmObjectSource.realmGet$image());
        realmObjectCopy.realmSet$module(realmObjectSource.realmGet$module());
        realmObjectCopy.realmSet$qty(realmObjectSource.realmGet$qty());
        realmObjectCopy.realmSet$amount(realmObjectSource.realmGet$amount());

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
        return realmObject;
    }

    public static long insert(Realm realm, com.geogreenapps.apps.indukaan.classes.Item object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Item.class);
        long tableNativePtr = table.getNativePtr();
        ItemColumnInfo columnInfo = (ItemColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Item.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, rowIndex);
        String realmGet$id = ((ItemRealmProxyInterface) object).realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id, false);
        }
        String realmGet$name = ((ItemRealmProxyInterface) object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        String realmGet$image = ((ItemRealmProxyInterface) object).realmGet$image();
        if (realmGet$image != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.imageIndex, rowIndex, realmGet$image, false);
        }
        String realmGet$module = ((ItemRealmProxyInterface) object).realmGet$module();
        if (realmGet$module != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.moduleIndex, rowIndex, realmGet$module, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.qtyIndex, rowIndex, ((ItemRealmProxyInterface) object).realmGet$qty(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.amountIndex, rowIndex, ((ItemRealmProxyInterface) object).realmGet$amount(), false);

        com.geogreenapps.apps.indukaan.classes.Currency currencyObj = ((ItemRealmProxyInterface) object).realmGet$currency();
        if (currencyObj != null) {
            Long cachecurrency = cache.get(currencyObj);
            if (cachecurrency == null) {
                cachecurrency = CurrencyRealmProxy.insert(realm, currencyObj, cache);
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.currencyIndex, rowIndex, cachecurrency, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Item.class);
        long tableNativePtr = table.getNativePtr();
        ItemColumnInfo columnInfo = (ItemColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Item.class);
        com.geogreenapps.apps.indukaan.classes.Item object = null;
        while (objects.hasNext()) {
            object = (com.geogreenapps.apps.indukaan.classes.Item) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = OsObject.createRow(table);
            cache.put(object, rowIndex);
            String realmGet$id = ((ItemRealmProxyInterface) object).realmGet$id();
            if (realmGet$id != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id, false);
            }
            String realmGet$name = ((ItemRealmProxyInterface) object).realmGet$name();
            if (realmGet$name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
            }
            String realmGet$image = ((ItemRealmProxyInterface) object).realmGet$image();
            if (realmGet$image != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.imageIndex, rowIndex, realmGet$image, false);
            }
            String realmGet$module = ((ItemRealmProxyInterface) object).realmGet$module();
            if (realmGet$module != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.moduleIndex, rowIndex, realmGet$module, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.qtyIndex, rowIndex, ((ItemRealmProxyInterface) object).realmGet$qty(), false);
            Table.nativeSetDouble(tableNativePtr, columnInfo.amountIndex, rowIndex, ((ItemRealmProxyInterface) object).realmGet$amount(), false);

            com.geogreenapps.apps.indukaan.classes.Currency currencyObj = ((ItemRealmProxyInterface) object).realmGet$currency();
            if (currencyObj != null) {
                Long cachecurrency = cache.get(currencyObj);
                if (cachecurrency == null) {
                    cachecurrency = CurrencyRealmProxy.insert(realm, currencyObj, cache);
                }
                table.setLink(columnInfo.currencyIndex, rowIndex, cachecurrency, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.geogreenapps.apps.indukaan.classes.Item object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Item.class);
        long tableNativePtr = table.getNativePtr();
        ItemColumnInfo columnInfo = (ItemColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Item.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, rowIndex);
        String realmGet$id = ((ItemRealmProxyInterface) object).realmGet$id();
        if (realmGet$id != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.idIndex, rowIndex, false);
        }
        String realmGet$name = ((ItemRealmProxyInterface) object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
        }
        String realmGet$image = ((ItemRealmProxyInterface) object).realmGet$image();
        if (realmGet$image != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.imageIndex, rowIndex, realmGet$image, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.imageIndex, rowIndex, false);
        }
        String realmGet$module = ((ItemRealmProxyInterface) object).realmGet$module();
        if (realmGet$module != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.moduleIndex, rowIndex, realmGet$module, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.moduleIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.qtyIndex, rowIndex, ((ItemRealmProxyInterface) object).realmGet$qty(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.amountIndex, rowIndex, ((ItemRealmProxyInterface) object).realmGet$amount(), false);

        com.geogreenapps.apps.indukaan.classes.Currency currencyObj = ((ItemRealmProxyInterface) object).realmGet$currency();
        if (currencyObj != null) {
            Long cachecurrency = cache.get(currencyObj);
            if (cachecurrency == null) {
                cachecurrency = CurrencyRealmProxy.insertOrUpdate(realm, currencyObj, cache);
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.currencyIndex, rowIndex, cachecurrency, false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.currencyIndex, rowIndex);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Item.class);
        long tableNativePtr = table.getNativePtr();
        ItemColumnInfo columnInfo = (ItemColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Item.class);
        com.geogreenapps.apps.indukaan.classes.Item object = null;
        while (objects.hasNext()) {
            object = (com.geogreenapps.apps.indukaan.classes.Item) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = OsObject.createRow(table);
            cache.put(object, rowIndex);
            String realmGet$id = ((ItemRealmProxyInterface) object).realmGet$id();
            if (realmGet$id != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.idIndex, rowIndex, realmGet$id, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.idIndex, rowIndex, false);
            }
            String realmGet$name = ((ItemRealmProxyInterface) object).realmGet$name();
            if (realmGet$name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
            }
            String realmGet$image = ((ItemRealmProxyInterface) object).realmGet$image();
            if (realmGet$image != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.imageIndex, rowIndex, realmGet$image, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.imageIndex, rowIndex, false);
            }
            String realmGet$module = ((ItemRealmProxyInterface) object).realmGet$module();
            if (realmGet$module != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.moduleIndex, rowIndex, realmGet$module, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.moduleIndex, rowIndex, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.qtyIndex, rowIndex, ((ItemRealmProxyInterface) object).realmGet$qty(), false);
            Table.nativeSetDouble(tableNativePtr, columnInfo.amountIndex, rowIndex, ((ItemRealmProxyInterface) object).realmGet$amount(), false);

            com.geogreenapps.apps.indukaan.classes.Currency currencyObj = ((ItemRealmProxyInterface) object).realmGet$currency();
            if (currencyObj != null) {
                Long cachecurrency = cache.get(currencyObj);
                if (cachecurrency == null) {
                    cachecurrency = CurrencyRealmProxy.insertOrUpdate(realm, currencyObj, cache);
                }
                Table.nativeSetLink(tableNativePtr, columnInfo.currencyIndex, rowIndex, cachecurrency, false);
            } else {
                Table.nativeNullifyLink(tableNativePtr, columnInfo.currencyIndex, rowIndex);
            }
        }
    }

    public static com.geogreenapps.apps.indukaan.classes.Item createDetachedCopy(com.geogreenapps.apps.indukaan.classes.Item realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.geogreenapps.apps.indukaan.classes.Item unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.geogreenapps.apps.indukaan.classes.Item();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.geogreenapps.apps.indukaan.classes.Item) cachedObject.object;
            }
            unmanagedObject = (com.geogreenapps.apps.indukaan.classes.Item) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        ItemRealmProxyInterface unmanagedCopy = (ItemRealmProxyInterface) unmanagedObject;
        ItemRealmProxyInterface realmSource = (ItemRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$name(realmSource.realmGet$name());
        unmanagedCopy.realmSet$image(realmSource.realmGet$image());
        unmanagedCopy.realmSet$module(realmSource.realmGet$module());
        unmanagedCopy.realmSet$qty(realmSource.realmGet$qty());
        unmanagedCopy.realmSet$amount(realmSource.realmGet$amount());

        // Deep copy of currency
        unmanagedCopy.realmSet$currency(CurrencyRealmProxy.createDetachedCopy(realmSource.realmGet$currency(), currentDepth + 1, maxDepth, cache));

        return unmanagedObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Item = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id() != null ? realmGet$id() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name() != null ? realmGet$name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{image:");
        stringBuilder.append(realmGet$image() != null ? realmGet$image() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{module:");
        stringBuilder.append(realmGet$module() != null ? realmGet$module() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{qty:");
        stringBuilder.append(realmGet$qty());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{amount:");
        stringBuilder.append(realmGet$amount());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{currency:");
        stringBuilder.append(realmGet$currency() != null ? "Currency" : "null");
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
        ItemRealmProxy aItem = (ItemRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aItem.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aItem.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aItem.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
