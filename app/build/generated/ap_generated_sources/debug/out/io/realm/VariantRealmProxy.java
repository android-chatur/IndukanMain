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
public class VariantRealmProxy extends com.geogreenapps.apps.indukaan.classes.Variant
    implements RealmObjectProxy, VariantRealmProxyInterface {

    static final class VariantColumnInfo extends ColumnInfo {
        long group_idIndex;
        long group_labelIndex;
        long typeIndex;
        long optionsIndex;
        long currencyIndex;

        VariantColumnInfo(OsSchemaInfo schemaInfo) {
            super(5);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Variant");
            this.group_idIndex = addColumnDetails("group_id", objectSchemaInfo);
            this.group_labelIndex = addColumnDetails("group_label", objectSchemaInfo);
            this.typeIndex = addColumnDetails("type", objectSchemaInfo);
            this.optionsIndex = addColumnDetails("options", objectSchemaInfo);
            this.currencyIndex = addColumnDetails("currency", objectSchemaInfo);
        }

        VariantColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new VariantColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final VariantColumnInfo src = (VariantColumnInfo) rawSrc;
            final VariantColumnInfo dst = (VariantColumnInfo) rawDst;
            dst.group_idIndex = src.group_idIndex;
            dst.group_labelIndex = src.group_labelIndex;
            dst.typeIndex = src.typeIndex;
            dst.optionsIndex = src.optionsIndex;
            dst.currencyIndex = src.currencyIndex;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>(5);
        fieldNames.add("group_id");
        fieldNames.add("group_label");
        fieldNames.add("type");
        fieldNames.add("options");
        fieldNames.add("currency");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    private VariantColumnInfo columnInfo;
    private ProxyState<com.geogreenapps.apps.indukaan.classes.Variant> proxyState;
    private RealmList<com.geogreenapps.apps.indukaan.classes.Option> optionsRealmList;

    VariantRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (VariantColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.geogreenapps.apps.indukaan.classes.Variant>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$group_id() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.group_idIndex);
    }

    @Override
    public void realmSet$group_id(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.group_idIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.group_idIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$group_label() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.group_labelIndex);
    }

    @Override
    public void realmSet$group_label(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.group_labelIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.group_labelIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.group_labelIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.group_labelIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$type() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.typeIndex);
    }

    @Override
    public void realmSet$type(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.typeIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.typeIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.typeIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.typeIndex, value);
    }

    @Override
    public RealmList<com.geogreenapps.apps.indukaan.classes.Option> realmGet$options() {
        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (optionsRealmList != null) {
            return optionsRealmList;
        } else {
            OsList osList = proxyState.getRow$realm().getModelList(columnInfo.optionsIndex);
            optionsRealmList = new RealmList<com.geogreenapps.apps.indukaan.classes.Option>(com.geogreenapps.apps.indukaan.classes.Option.class, osList, proxyState.getRealm$realm());
            return optionsRealmList;
        }
    }

    @Override
    public void realmSet$options(RealmList<com.geogreenapps.apps.indukaan.classes.Option> value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("options")) {
                return;
            }
            // if the list contains unmanaged RealmObjects, convert them to managed.
            if (value != null && !value.isManaged()) {
                final Realm realm = (Realm) proxyState.getRealm$realm();
                final RealmList<com.geogreenapps.apps.indukaan.classes.Option> original = value;
                value = new RealmList<com.geogreenapps.apps.indukaan.classes.Option>();
                for (com.geogreenapps.apps.indukaan.classes.Option item : original) {
                    if (item == null || RealmObject.isManaged(item)) {
                        value.add(item);
                    } else {
                        value.add(realm.copyToRealm(item));
                    }
                }
            }
        }

        proxyState.getRealm$realm().checkIfValid();
        OsList osList = proxyState.getRow$realm().getModelList(columnInfo.optionsIndex);
        // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
        if (value != null && value.size() == osList.size()) {
            int objects = value.size();
            for (int i = 0; i < objects; i++) {
                com.geogreenapps.apps.indukaan.classes.Option linkedObject = value.get(i);
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
                com.geogreenapps.apps.indukaan.classes.Option linkedObject = value.get(i);
                proxyState.checkValidObject(linkedObject);
                osList.addRow(((RealmObjectProxy) linkedObject).realmGet$proxyState().getRow$realm().getIndex());
            }
        }
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
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("Variant", 5, 0);
        builder.addPersistedProperty("group_id", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("group_label", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("type", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedLinkProperty("options", RealmFieldType.LIST, "Option");
        builder.addPersistedLinkProperty("currency", RealmFieldType.OBJECT, "Currency");
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static VariantColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new VariantColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "Variant";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.geogreenapps.apps.indukaan.classes.Variant createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = new ArrayList<String>(2);
        if (json.has("options")) {
            excludeFields.add("options");
        }
        if (json.has("currency")) {
            excludeFields.add("currency");
        }
        com.geogreenapps.apps.indukaan.classes.Variant obj = realm.createObjectInternal(com.geogreenapps.apps.indukaan.classes.Variant.class, true, excludeFields);

        final VariantRealmProxyInterface objProxy = (VariantRealmProxyInterface) obj;
        if (json.has("group_id")) {
            if (json.isNull("group_id")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'group_id' to null.");
            } else {
                objProxy.realmSet$group_id((int) json.getInt("group_id"));
            }
        }
        if (json.has("group_label")) {
            if (json.isNull("group_label")) {
                objProxy.realmSet$group_label(null);
            } else {
                objProxy.realmSet$group_label((String) json.getString("group_label"));
            }
        }
        if (json.has("type")) {
            if (json.isNull("type")) {
                objProxy.realmSet$type(null);
            } else {
                objProxy.realmSet$type((String) json.getString("type"));
            }
        }
        if (json.has("options")) {
            if (json.isNull("options")) {
                objProxy.realmSet$options(null);
            } else {
                objProxy.realmGet$options().clear();
                JSONArray array = json.getJSONArray("options");
                for (int i = 0; i < array.length(); i++) {
                    com.geogreenapps.apps.indukaan.classes.Option item = OptionRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    objProxy.realmGet$options().add(item);
                }
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
    public static com.geogreenapps.apps.indukaan.classes.Variant createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        final com.geogreenapps.apps.indukaan.classes.Variant obj = new com.geogreenapps.apps.indukaan.classes.Variant();
        final VariantRealmProxyInterface objProxy = (VariantRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("group_id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$group_id((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'group_id' to null.");
                }
            } else if (name.equals("group_label")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$group_label((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$group_label(null);
                }
            } else if (name.equals("type")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$type((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$type(null);
                }
            } else if (name.equals("options")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$options(null);
                } else {
                    objProxy.realmSet$options(new RealmList<com.geogreenapps.apps.indukaan.classes.Option>());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.geogreenapps.apps.indukaan.classes.Option item = OptionRealmProxy.createUsingJsonStream(realm, reader);
                        objProxy.realmGet$options().add(item);
                    }
                    reader.endArray();
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

    public static com.geogreenapps.apps.indukaan.classes.Variant copyOrUpdate(Realm realm, com.geogreenapps.apps.indukaan.classes.Variant object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
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
            return (com.geogreenapps.apps.indukaan.classes.Variant) cachedRealmObject;
        }

        return copy(realm, object, update, cache);
    }

    public static com.geogreenapps.apps.indukaan.classes.Variant copy(Realm realm, com.geogreenapps.apps.indukaan.classes.Variant newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.geogreenapps.apps.indukaan.classes.Variant) cachedRealmObject;
        }

        // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
        com.geogreenapps.apps.indukaan.classes.Variant realmObject = realm.createObjectInternal(com.geogreenapps.apps.indukaan.classes.Variant.class, false, Collections.<String>emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);

        VariantRealmProxyInterface realmObjectSource = (VariantRealmProxyInterface) newObject;
        VariantRealmProxyInterface realmObjectCopy = (VariantRealmProxyInterface) realmObject;

        realmObjectCopy.realmSet$group_id(realmObjectSource.realmGet$group_id());
        realmObjectCopy.realmSet$group_label(realmObjectSource.realmGet$group_label());
        realmObjectCopy.realmSet$type(realmObjectSource.realmGet$type());

        RealmList<com.geogreenapps.apps.indukaan.classes.Option> optionsList = realmObjectSource.realmGet$options();
        if (optionsList != null) {
            RealmList<com.geogreenapps.apps.indukaan.classes.Option> optionsRealmList = realmObjectCopy.realmGet$options();
            optionsRealmList.clear();
            for (int i = 0; i < optionsList.size(); i++) {
                com.geogreenapps.apps.indukaan.classes.Option optionsItem = optionsList.get(i);
                com.geogreenapps.apps.indukaan.classes.Option cacheoptions = (com.geogreenapps.apps.indukaan.classes.Option) cache.get(optionsItem);
                if (cacheoptions != null) {
                    optionsRealmList.add(cacheoptions);
                } else {
                    optionsRealmList.add(OptionRealmProxy.copyOrUpdate(realm, optionsItem, update, cache));
                }
            }
        }


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

    public static long insert(Realm realm, com.geogreenapps.apps.indukaan.classes.Variant object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Variant.class);
        long tableNativePtr = table.getNativePtr();
        VariantColumnInfo columnInfo = (VariantColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Variant.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.group_idIndex, rowIndex, ((VariantRealmProxyInterface) object).realmGet$group_id(), false);
        String realmGet$group_label = ((VariantRealmProxyInterface) object).realmGet$group_label();
        if (realmGet$group_label != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.group_labelIndex, rowIndex, realmGet$group_label, false);
        }
        String realmGet$type = ((VariantRealmProxyInterface) object).realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
        }

        RealmList<com.geogreenapps.apps.indukaan.classes.Option> optionsList = ((VariantRealmProxyInterface) object).realmGet$options();
        if (optionsList != null) {
            OsList optionsOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.optionsIndex);
            for (com.geogreenapps.apps.indukaan.classes.Option optionsItem : optionsList) {
                Long cacheItemIndexoptions = cache.get(optionsItem);
                if (cacheItemIndexoptions == null) {
                    cacheItemIndexoptions = OptionRealmProxy.insert(realm, optionsItem, cache);
                }
                optionsOsList.addRow(cacheItemIndexoptions);
            }
        }

        com.geogreenapps.apps.indukaan.classes.Currency currencyObj = ((VariantRealmProxyInterface) object).realmGet$currency();
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
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Variant.class);
        long tableNativePtr = table.getNativePtr();
        VariantColumnInfo columnInfo = (VariantColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Variant.class);
        com.geogreenapps.apps.indukaan.classes.Variant object = null;
        while (objects.hasNext()) {
            object = (com.geogreenapps.apps.indukaan.classes.Variant) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = OsObject.createRow(table);
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.group_idIndex, rowIndex, ((VariantRealmProxyInterface) object).realmGet$group_id(), false);
            String realmGet$group_label = ((VariantRealmProxyInterface) object).realmGet$group_label();
            if (realmGet$group_label != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.group_labelIndex, rowIndex, realmGet$group_label, false);
            }
            String realmGet$type = ((VariantRealmProxyInterface) object).realmGet$type();
            if (realmGet$type != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
            }

            RealmList<com.geogreenapps.apps.indukaan.classes.Option> optionsList = ((VariantRealmProxyInterface) object).realmGet$options();
            if (optionsList != null) {
                OsList optionsOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.optionsIndex);
                for (com.geogreenapps.apps.indukaan.classes.Option optionsItem : optionsList) {
                    Long cacheItemIndexoptions = cache.get(optionsItem);
                    if (cacheItemIndexoptions == null) {
                        cacheItemIndexoptions = OptionRealmProxy.insert(realm, optionsItem, cache);
                    }
                    optionsOsList.addRow(cacheItemIndexoptions);
                }
            }

            com.geogreenapps.apps.indukaan.classes.Currency currencyObj = ((VariantRealmProxyInterface) object).realmGet$currency();
            if (currencyObj != null) {
                Long cachecurrency = cache.get(currencyObj);
                if (cachecurrency == null) {
                    cachecurrency = CurrencyRealmProxy.insert(realm, currencyObj, cache);
                }
                table.setLink(columnInfo.currencyIndex, rowIndex, cachecurrency, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.geogreenapps.apps.indukaan.classes.Variant object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Variant.class);
        long tableNativePtr = table.getNativePtr();
        VariantColumnInfo columnInfo = (VariantColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Variant.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.group_idIndex, rowIndex, ((VariantRealmProxyInterface) object).realmGet$group_id(), false);
        String realmGet$group_label = ((VariantRealmProxyInterface) object).realmGet$group_label();
        if (realmGet$group_label != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.group_labelIndex, rowIndex, realmGet$group_label, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.group_labelIndex, rowIndex, false);
        }
        String realmGet$type = ((VariantRealmProxyInterface) object).realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.typeIndex, rowIndex, false);
        }

        OsList optionsOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.optionsIndex);
        RealmList<com.geogreenapps.apps.indukaan.classes.Option> optionsList = ((VariantRealmProxyInterface) object).realmGet$options();
        if (optionsList != null && optionsList.size() == optionsOsList.size()) {
            // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
            int objects = optionsList.size();
            for (int i = 0; i < objects; i++) {
                com.geogreenapps.apps.indukaan.classes.Option optionsItem = optionsList.get(i);
                Long cacheItemIndexoptions = cache.get(optionsItem);
                if (cacheItemIndexoptions == null) {
                    cacheItemIndexoptions = OptionRealmProxy.insertOrUpdate(realm, optionsItem, cache);
                }
                optionsOsList.setRow(i, cacheItemIndexoptions);
            }
        } else {
            optionsOsList.removeAll();
            if (optionsList != null) {
                for (com.geogreenapps.apps.indukaan.classes.Option optionsItem : optionsList) {
                    Long cacheItemIndexoptions = cache.get(optionsItem);
                    if (cacheItemIndexoptions == null) {
                        cacheItemIndexoptions = OptionRealmProxy.insertOrUpdate(realm, optionsItem, cache);
                    }
                    optionsOsList.addRow(cacheItemIndexoptions);
                }
            }
        }


        com.geogreenapps.apps.indukaan.classes.Currency currencyObj = ((VariantRealmProxyInterface) object).realmGet$currency();
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
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Variant.class);
        long tableNativePtr = table.getNativePtr();
        VariantColumnInfo columnInfo = (VariantColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Variant.class);
        com.geogreenapps.apps.indukaan.classes.Variant object = null;
        while (objects.hasNext()) {
            object = (com.geogreenapps.apps.indukaan.classes.Variant) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = OsObject.createRow(table);
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.group_idIndex, rowIndex, ((VariantRealmProxyInterface) object).realmGet$group_id(), false);
            String realmGet$group_label = ((VariantRealmProxyInterface) object).realmGet$group_label();
            if (realmGet$group_label != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.group_labelIndex, rowIndex, realmGet$group_label, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.group_labelIndex, rowIndex, false);
            }
            String realmGet$type = ((VariantRealmProxyInterface) object).realmGet$type();
            if (realmGet$type != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.typeIndex, rowIndex, false);
            }

            OsList optionsOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.optionsIndex);
            RealmList<com.geogreenapps.apps.indukaan.classes.Option> optionsList = ((VariantRealmProxyInterface) object).realmGet$options();
            if (optionsList != null && optionsList.size() == optionsOsList.size()) {
                // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
                int objectCount = optionsList.size();
                for (int i = 0; i < objectCount; i++) {
                    com.geogreenapps.apps.indukaan.classes.Option optionsItem = optionsList.get(i);
                    Long cacheItemIndexoptions = cache.get(optionsItem);
                    if (cacheItemIndexoptions == null) {
                        cacheItemIndexoptions = OptionRealmProxy.insertOrUpdate(realm, optionsItem, cache);
                    }
                    optionsOsList.setRow(i, cacheItemIndexoptions);
                }
            } else {
                optionsOsList.removeAll();
                if (optionsList != null) {
                    for (com.geogreenapps.apps.indukaan.classes.Option optionsItem : optionsList) {
                        Long cacheItemIndexoptions = cache.get(optionsItem);
                        if (cacheItemIndexoptions == null) {
                            cacheItemIndexoptions = OptionRealmProxy.insertOrUpdate(realm, optionsItem, cache);
                        }
                        optionsOsList.addRow(cacheItemIndexoptions);
                    }
                }
            }


            com.geogreenapps.apps.indukaan.classes.Currency currencyObj = ((VariantRealmProxyInterface) object).realmGet$currency();
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

    public static com.geogreenapps.apps.indukaan.classes.Variant createDetachedCopy(com.geogreenapps.apps.indukaan.classes.Variant realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.geogreenapps.apps.indukaan.classes.Variant unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.geogreenapps.apps.indukaan.classes.Variant();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.geogreenapps.apps.indukaan.classes.Variant) cachedObject.object;
            }
            unmanagedObject = (com.geogreenapps.apps.indukaan.classes.Variant) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        VariantRealmProxyInterface unmanagedCopy = (VariantRealmProxyInterface) unmanagedObject;
        VariantRealmProxyInterface realmSource = (VariantRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$group_id(realmSource.realmGet$group_id());
        unmanagedCopy.realmSet$group_label(realmSource.realmGet$group_label());
        unmanagedCopy.realmSet$type(realmSource.realmGet$type());

        // Deep copy of options
        if (currentDepth == maxDepth) {
            unmanagedCopy.realmSet$options(null);
        } else {
            RealmList<com.geogreenapps.apps.indukaan.classes.Option> managedoptionsList = realmSource.realmGet$options();
            RealmList<com.geogreenapps.apps.indukaan.classes.Option> unmanagedoptionsList = new RealmList<com.geogreenapps.apps.indukaan.classes.Option>();
            unmanagedCopy.realmSet$options(unmanagedoptionsList);
            int nextDepth = currentDepth + 1;
            int size = managedoptionsList.size();
            for (int i = 0; i < size; i++) {
                com.geogreenapps.apps.indukaan.classes.Option item = OptionRealmProxy.createDetachedCopy(managedoptionsList.get(i), nextDepth, maxDepth, cache);
                unmanagedoptionsList.add(item);
            }
        }

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
        StringBuilder stringBuilder = new StringBuilder("Variant = proxy[");
        stringBuilder.append("{group_id:");
        stringBuilder.append(realmGet$group_id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{group_label:");
        stringBuilder.append(realmGet$group_label() != null ? realmGet$group_label() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{type:");
        stringBuilder.append(realmGet$type() != null ? realmGet$type() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{options:");
        stringBuilder.append("RealmList<Option>[").append(realmGet$options().size()).append("]");
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
        VariantRealmProxy aVariant = (VariantRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aVariant.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aVariant.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aVariant.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
