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
public class SettingRealmProxy extends com.geogreenapps.apps.indukaan.classes.Setting
    implements RealmObjectProxy, SettingRealmProxyInterface {

    static final class SettingColumnInfo extends ColumnInfo {
        long idIndex;
        long _keyIndex;
        long valueIndex;
        long _typeIndex;
        long is_verifiedIndex;
        long user_idIndex;
        long versionIndex;
        long updated_atIndex;
        long created_atIndex;

        SettingColumnInfo(OsSchemaInfo schemaInfo) {
            super(9);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Setting");
            this.idIndex = addColumnDetails("id", objectSchemaInfo);
            this._keyIndex = addColumnDetails("_key", objectSchemaInfo);
            this.valueIndex = addColumnDetails("value", objectSchemaInfo);
            this._typeIndex = addColumnDetails("_type", objectSchemaInfo);
            this.is_verifiedIndex = addColumnDetails("is_verified", objectSchemaInfo);
            this.user_idIndex = addColumnDetails("user_id", objectSchemaInfo);
            this.versionIndex = addColumnDetails("version", objectSchemaInfo);
            this.updated_atIndex = addColumnDetails("updated_at", objectSchemaInfo);
            this.created_atIndex = addColumnDetails("created_at", objectSchemaInfo);
        }

        SettingColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new SettingColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final SettingColumnInfo src = (SettingColumnInfo) rawSrc;
            final SettingColumnInfo dst = (SettingColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst._keyIndex = src._keyIndex;
            dst.valueIndex = src.valueIndex;
            dst._typeIndex = src._typeIndex;
            dst.is_verifiedIndex = src.is_verifiedIndex;
            dst.user_idIndex = src.user_idIndex;
            dst.versionIndex = src.versionIndex;
            dst.updated_atIndex = src.updated_atIndex;
            dst.created_atIndex = src.created_atIndex;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>(9);
        fieldNames.add("id");
        fieldNames.add("_key");
        fieldNames.add("value");
        fieldNames.add("_type");
        fieldNames.add("is_verified");
        fieldNames.add("user_id");
        fieldNames.add("version");
        fieldNames.add("updated_at");
        fieldNames.add("created_at");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    private SettingColumnInfo columnInfo;
    private ProxyState<com.geogreenapps.apps.indukaan.classes.Setting> proxyState;

    SettingRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (SettingColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.geogreenapps.apps.indukaan.classes.Setting>(this);
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
    @SuppressWarnings("cast")
    public String realmGet$_key() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo._keyIndex);
    }

    @Override
    public void realmSet$_key(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo._keyIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo._keyIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo._keyIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo._keyIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$value() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.valueIndex);
    }

    @Override
    public void realmSet$value(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.valueIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.valueIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.valueIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.valueIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$_type() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo._typeIndex);
    }

    @Override
    public void realmSet$_type(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo._typeIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo._typeIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo._typeIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo._typeIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$is_verified() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.is_verifiedIndex);
    }

    @Override
    public void realmSet$is_verified(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.is_verifiedIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.is_verifiedIndex, value);
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
    public String realmGet$version() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.versionIndex);
    }

    @Override
    public void realmSet$version(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.versionIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.versionIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.versionIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.versionIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$updated_at() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.updated_atIndex);
    }

    @Override
    public void realmSet$updated_at(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.updated_atIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.updated_atIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.updated_atIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.updated_atIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$created_at() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.created_atIndex);
    }

    @Override
    public void realmSet$created_at(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.created_atIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.created_atIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.created_atIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.created_atIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("Setting", 9, 0);
        builder.addPersistedProperty("id", RealmFieldType.INTEGER, Property.PRIMARY_KEY, Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("_key", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("value", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("_type", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("is_verified", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("user_id", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("version", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("updated_at", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("created_at", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static SettingColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new SettingColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "Setting";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.geogreenapps.apps.indukaan.classes.Setting createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.geogreenapps.apps.indukaan.classes.Setting obj = null;
        if (update) {
            Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Setting.class);
            SettingColumnInfo columnInfo = (SettingColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Setting.class);
            long pkColumnIndex = columnInfo.idIndex;
            long rowIndex = Table.NO_MATCH;
            if (!json.isNull("id")) {
                rowIndex = table.findFirstLong(pkColumnIndex, json.getLong("id"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Setting.class), false, Collections.<String> emptyList());
                    obj = new io.realm.SettingRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.SettingRealmProxy) realm.createObjectInternal(com.geogreenapps.apps.indukaan.classes.Setting.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.SettingRealmProxy) realm.createObjectInternal(com.geogreenapps.apps.indukaan.classes.Setting.class, json.getInt("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }

        final SettingRealmProxyInterface objProxy = (SettingRealmProxyInterface) obj;
        if (json.has("_key")) {
            if (json.isNull("_key")) {
                objProxy.realmSet$_key(null);
            } else {
                objProxy.realmSet$_key((String) json.getString("_key"));
            }
        }
        if (json.has("value")) {
            if (json.isNull("value")) {
                objProxy.realmSet$value(null);
            } else {
                objProxy.realmSet$value((String) json.getString("value"));
            }
        }
        if (json.has("_type")) {
            if (json.isNull("_type")) {
                objProxy.realmSet$_type(null);
            } else {
                objProxy.realmSet$_type((String) json.getString("_type"));
            }
        }
        if (json.has("is_verified")) {
            if (json.isNull("is_verified")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'is_verified' to null.");
            } else {
                objProxy.realmSet$is_verified((int) json.getInt("is_verified"));
            }
        }
        if (json.has("user_id")) {
            if (json.isNull("user_id")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'user_id' to null.");
            } else {
                objProxy.realmSet$user_id((int) json.getInt("user_id"));
            }
        }
        if (json.has("version")) {
            if (json.isNull("version")) {
                objProxy.realmSet$version(null);
            } else {
                objProxy.realmSet$version((String) json.getString("version"));
            }
        }
        if (json.has("updated_at")) {
            if (json.isNull("updated_at")) {
                objProxy.realmSet$updated_at(null);
            } else {
                objProxy.realmSet$updated_at((String) json.getString("updated_at"));
            }
        }
        if (json.has("created_at")) {
            if (json.isNull("created_at")) {
                objProxy.realmSet$created_at(null);
            } else {
                objProxy.realmSet$created_at((String) json.getString("created_at"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.geogreenapps.apps.indukaan.classes.Setting createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.geogreenapps.apps.indukaan.classes.Setting obj = new com.geogreenapps.apps.indukaan.classes.Setting();
        final SettingRealmProxyInterface objProxy = (SettingRealmProxyInterface) obj;
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
            } else if (name.equals("_key")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$_key((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$_key(null);
                }
            } else if (name.equals("value")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$value((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$value(null);
                }
            } else if (name.equals("_type")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$_type((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$_type(null);
                }
            } else if (name.equals("is_verified")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$is_verified((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'is_verified' to null.");
                }
            } else if (name.equals("user_id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$user_id((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'user_id' to null.");
                }
            } else if (name.equals("version")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$version((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$version(null);
                }
            } else if (name.equals("updated_at")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$updated_at((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$updated_at(null);
                }
            } else if (name.equals("created_at")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$created_at((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$created_at(null);
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

    public static com.geogreenapps.apps.indukaan.classes.Setting copyOrUpdate(Realm realm, com.geogreenapps.apps.indukaan.classes.Setting object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
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
            return (com.geogreenapps.apps.indukaan.classes.Setting) cachedRealmObject;
        }

        com.geogreenapps.apps.indukaan.classes.Setting realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Setting.class);
            SettingColumnInfo columnInfo = (SettingColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Setting.class);
            long pkColumnIndex = columnInfo.idIndex;
            long rowIndex = table.findFirstLong(pkColumnIndex, ((SettingRealmProxyInterface) object).realmGet$id());
            if (rowIndex == Table.NO_MATCH) {
                canUpdate = false;
            } else {
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Setting.class), false, Collections.<String> emptyList());
                    realmObject = new io.realm.SettingRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, realmObject, object, cache) : copy(realm, object, update, cache);
    }

    public static com.geogreenapps.apps.indukaan.classes.Setting copy(Realm realm, com.geogreenapps.apps.indukaan.classes.Setting newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.geogreenapps.apps.indukaan.classes.Setting) cachedRealmObject;
        }

        // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
        com.geogreenapps.apps.indukaan.classes.Setting realmObject = realm.createObjectInternal(com.geogreenapps.apps.indukaan.classes.Setting.class, ((SettingRealmProxyInterface) newObject).realmGet$id(), false, Collections.<String>emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);

        SettingRealmProxyInterface realmObjectSource = (SettingRealmProxyInterface) newObject;
        SettingRealmProxyInterface realmObjectCopy = (SettingRealmProxyInterface) realmObject;

        realmObjectCopy.realmSet$_key(realmObjectSource.realmGet$_key());
        realmObjectCopy.realmSet$value(realmObjectSource.realmGet$value());
        realmObjectCopy.realmSet$_type(realmObjectSource.realmGet$_type());
        realmObjectCopy.realmSet$is_verified(realmObjectSource.realmGet$is_verified());
        realmObjectCopy.realmSet$user_id(realmObjectSource.realmGet$user_id());
        realmObjectCopy.realmSet$version(realmObjectSource.realmGet$version());
        realmObjectCopy.realmSet$updated_at(realmObjectSource.realmGet$updated_at());
        realmObjectCopy.realmSet$created_at(realmObjectSource.realmGet$created_at());
        return realmObject;
    }

    public static long insert(Realm realm, com.geogreenapps.apps.indukaan.classes.Setting object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Setting.class);
        long tableNativePtr = table.getNativePtr();
        SettingColumnInfo columnInfo = (SettingColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Setting.class);
        long pkColumnIndex = columnInfo.idIndex;
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((SettingRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((SettingRealmProxyInterface) object).realmGet$id());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((SettingRealmProxyInterface) object).realmGet$id());
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$_key = ((SettingRealmProxyInterface) object).realmGet$_key();
        if (realmGet$_key != null) {
            Table.nativeSetString(tableNativePtr, columnInfo._keyIndex, rowIndex, realmGet$_key, false);
        }
        String realmGet$value = ((SettingRealmProxyInterface) object).realmGet$value();
        if (realmGet$value != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.valueIndex, rowIndex, realmGet$value, false);
        }
        String realmGet$_type = ((SettingRealmProxyInterface) object).realmGet$_type();
        if (realmGet$_type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo._typeIndex, rowIndex, realmGet$_type, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.is_verifiedIndex, rowIndex, ((SettingRealmProxyInterface) object).realmGet$is_verified(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.user_idIndex, rowIndex, ((SettingRealmProxyInterface) object).realmGet$user_id(), false);
        String realmGet$version = ((SettingRealmProxyInterface) object).realmGet$version();
        if (realmGet$version != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.versionIndex, rowIndex, realmGet$version, false);
        }
        String realmGet$updated_at = ((SettingRealmProxyInterface) object).realmGet$updated_at();
        if (realmGet$updated_at != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.updated_atIndex, rowIndex, realmGet$updated_at, false);
        }
        String realmGet$created_at = ((SettingRealmProxyInterface) object).realmGet$created_at();
        if (realmGet$created_at != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.created_atIndex, rowIndex, realmGet$created_at, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Setting.class);
        long tableNativePtr = table.getNativePtr();
        SettingColumnInfo columnInfo = (SettingColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Setting.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.geogreenapps.apps.indukaan.classes.Setting object = null;
        while (objects.hasNext()) {
            object = (com.geogreenapps.apps.indukaan.classes.Setting) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((SettingRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((SettingRealmProxyInterface) object).realmGet$id());
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((SettingRealmProxyInterface) object).realmGet$id());
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, rowIndex);
            String realmGet$_key = ((SettingRealmProxyInterface) object).realmGet$_key();
            if (realmGet$_key != null) {
                Table.nativeSetString(tableNativePtr, columnInfo._keyIndex, rowIndex, realmGet$_key, false);
            }
            String realmGet$value = ((SettingRealmProxyInterface) object).realmGet$value();
            if (realmGet$value != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.valueIndex, rowIndex, realmGet$value, false);
            }
            String realmGet$_type = ((SettingRealmProxyInterface) object).realmGet$_type();
            if (realmGet$_type != null) {
                Table.nativeSetString(tableNativePtr, columnInfo._typeIndex, rowIndex, realmGet$_type, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.is_verifiedIndex, rowIndex, ((SettingRealmProxyInterface) object).realmGet$is_verified(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.user_idIndex, rowIndex, ((SettingRealmProxyInterface) object).realmGet$user_id(), false);
            String realmGet$version = ((SettingRealmProxyInterface) object).realmGet$version();
            if (realmGet$version != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.versionIndex, rowIndex, realmGet$version, false);
            }
            String realmGet$updated_at = ((SettingRealmProxyInterface) object).realmGet$updated_at();
            if (realmGet$updated_at != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.updated_atIndex, rowIndex, realmGet$updated_at, false);
            }
            String realmGet$created_at = ((SettingRealmProxyInterface) object).realmGet$created_at();
            if (realmGet$created_at != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.created_atIndex, rowIndex, realmGet$created_at, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.geogreenapps.apps.indukaan.classes.Setting object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Setting.class);
        long tableNativePtr = table.getNativePtr();
        SettingColumnInfo columnInfo = (SettingColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Setting.class);
        long pkColumnIndex = columnInfo.idIndex;
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((SettingRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((SettingRealmProxyInterface) object).realmGet$id());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((SettingRealmProxyInterface) object).realmGet$id());
        }
        cache.put(object, rowIndex);
        String realmGet$_key = ((SettingRealmProxyInterface) object).realmGet$_key();
        if (realmGet$_key != null) {
            Table.nativeSetString(tableNativePtr, columnInfo._keyIndex, rowIndex, realmGet$_key, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo._keyIndex, rowIndex, false);
        }
        String realmGet$value = ((SettingRealmProxyInterface) object).realmGet$value();
        if (realmGet$value != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.valueIndex, rowIndex, realmGet$value, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.valueIndex, rowIndex, false);
        }
        String realmGet$_type = ((SettingRealmProxyInterface) object).realmGet$_type();
        if (realmGet$_type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo._typeIndex, rowIndex, realmGet$_type, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo._typeIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.is_verifiedIndex, rowIndex, ((SettingRealmProxyInterface) object).realmGet$is_verified(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.user_idIndex, rowIndex, ((SettingRealmProxyInterface) object).realmGet$user_id(), false);
        String realmGet$version = ((SettingRealmProxyInterface) object).realmGet$version();
        if (realmGet$version != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.versionIndex, rowIndex, realmGet$version, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.versionIndex, rowIndex, false);
        }
        String realmGet$updated_at = ((SettingRealmProxyInterface) object).realmGet$updated_at();
        if (realmGet$updated_at != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.updated_atIndex, rowIndex, realmGet$updated_at, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.updated_atIndex, rowIndex, false);
        }
        String realmGet$created_at = ((SettingRealmProxyInterface) object).realmGet$created_at();
        if (realmGet$created_at != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.created_atIndex, rowIndex, realmGet$created_at, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.created_atIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Setting.class);
        long tableNativePtr = table.getNativePtr();
        SettingColumnInfo columnInfo = (SettingColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Setting.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.geogreenapps.apps.indukaan.classes.Setting object = null;
        while (objects.hasNext()) {
            object = (com.geogreenapps.apps.indukaan.classes.Setting) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((SettingRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((SettingRealmProxyInterface) object).realmGet$id());
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((SettingRealmProxyInterface) object).realmGet$id());
            }
            cache.put(object, rowIndex);
            String realmGet$_key = ((SettingRealmProxyInterface) object).realmGet$_key();
            if (realmGet$_key != null) {
                Table.nativeSetString(tableNativePtr, columnInfo._keyIndex, rowIndex, realmGet$_key, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo._keyIndex, rowIndex, false);
            }
            String realmGet$value = ((SettingRealmProxyInterface) object).realmGet$value();
            if (realmGet$value != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.valueIndex, rowIndex, realmGet$value, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.valueIndex, rowIndex, false);
            }
            String realmGet$_type = ((SettingRealmProxyInterface) object).realmGet$_type();
            if (realmGet$_type != null) {
                Table.nativeSetString(tableNativePtr, columnInfo._typeIndex, rowIndex, realmGet$_type, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo._typeIndex, rowIndex, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.is_verifiedIndex, rowIndex, ((SettingRealmProxyInterface) object).realmGet$is_verified(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.user_idIndex, rowIndex, ((SettingRealmProxyInterface) object).realmGet$user_id(), false);
            String realmGet$version = ((SettingRealmProxyInterface) object).realmGet$version();
            if (realmGet$version != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.versionIndex, rowIndex, realmGet$version, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.versionIndex, rowIndex, false);
            }
            String realmGet$updated_at = ((SettingRealmProxyInterface) object).realmGet$updated_at();
            if (realmGet$updated_at != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.updated_atIndex, rowIndex, realmGet$updated_at, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.updated_atIndex, rowIndex, false);
            }
            String realmGet$created_at = ((SettingRealmProxyInterface) object).realmGet$created_at();
            if (realmGet$created_at != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.created_atIndex, rowIndex, realmGet$created_at, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.created_atIndex, rowIndex, false);
            }
        }
    }

    public static com.geogreenapps.apps.indukaan.classes.Setting createDetachedCopy(com.geogreenapps.apps.indukaan.classes.Setting realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.geogreenapps.apps.indukaan.classes.Setting unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.geogreenapps.apps.indukaan.classes.Setting();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.geogreenapps.apps.indukaan.classes.Setting) cachedObject.object;
            }
            unmanagedObject = (com.geogreenapps.apps.indukaan.classes.Setting) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        SettingRealmProxyInterface unmanagedCopy = (SettingRealmProxyInterface) unmanagedObject;
        SettingRealmProxyInterface realmSource = (SettingRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$_key(realmSource.realmGet$_key());
        unmanagedCopy.realmSet$value(realmSource.realmGet$value());
        unmanagedCopy.realmSet$_type(realmSource.realmGet$_type());
        unmanagedCopy.realmSet$is_verified(realmSource.realmGet$is_verified());
        unmanagedCopy.realmSet$user_id(realmSource.realmGet$user_id());
        unmanagedCopy.realmSet$version(realmSource.realmGet$version());
        unmanagedCopy.realmSet$updated_at(realmSource.realmGet$updated_at());
        unmanagedCopy.realmSet$created_at(realmSource.realmGet$created_at());

        return unmanagedObject;
    }

    static com.geogreenapps.apps.indukaan.classes.Setting update(Realm realm, com.geogreenapps.apps.indukaan.classes.Setting realmObject, com.geogreenapps.apps.indukaan.classes.Setting newObject, Map<RealmModel, RealmObjectProxy> cache) {
        SettingRealmProxyInterface realmObjectTarget = (SettingRealmProxyInterface) realmObject;
        SettingRealmProxyInterface realmObjectSource = (SettingRealmProxyInterface) newObject;
        realmObjectTarget.realmSet$_key(realmObjectSource.realmGet$_key());
        realmObjectTarget.realmSet$value(realmObjectSource.realmGet$value());
        realmObjectTarget.realmSet$_type(realmObjectSource.realmGet$_type());
        realmObjectTarget.realmSet$is_verified(realmObjectSource.realmGet$is_verified());
        realmObjectTarget.realmSet$user_id(realmObjectSource.realmGet$user_id());
        realmObjectTarget.realmSet$version(realmObjectSource.realmGet$version());
        realmObjectTarget.realmSet$updated_at(realmObjectSource.realmGet$updated_at());
        realmObjectTarget.realmSet$created_at(realmObjectSource.realmGet$created_at());
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Setting = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{_key:");
        stringBuilder.append(realmGet$_key() != null ? realmGet$_key() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{value:");
        stringBuilder.append(realmGet$value() != null ? realmGet$value() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{_type:");
        stringBuilder.append(realmGet$_type() != null ? realmGet$_type() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{is_verified:");
        stringBuilder.append(realmGet$is_verified());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{user_id:");
        stringBuilder.append(realmGet$user_id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{version:");
        stringBuilder.append(realmGet$version() != null ? realmGet$version() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{updated_at:");
        stringBuilder.append(realmGet$updated_at() != null ? realmGet$updated_at() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{created_at:");
        stringBuilder.append(realmGet$created_at() != null ? realmGet$created_at() : "null");
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
        SettingRealmProxy aSetting = (SettingRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aSetting.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aSetting.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aSetting.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
