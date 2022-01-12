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
public class ModuleRealmProxy extends com.geogreenapps.apps.indukaan.classes.Module
    implements RealmObjectProxy, ModuleRealmProxyInterface {

    static final class ModuleColumnInfo extends ColumnInfo {
        long nameIndex;
        long enabledIndex;

        ModuleColumnInfo(OsSchemaInfo schemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Module");
            this.nameIndex = addColumnDetails("name", objectSchemaInfo);
            this.enabledIndex = addColumnDetails("enabled", objectSchemaInfo);
        }

        ModuleColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new ModuleColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final ModuleColumnInfo src = (ModuleColumnInfo) rawSrc;
            final ModuleColumnInfo dst = (ModuleColumnInfo) rawDst;
            dst.nameIndex = src.nameIndex;
            dst.enabledIndex = src.enabledIndex;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>(2);
        fieldNames.add("name");
        fieldNames.add("enabled");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    private ModuleColumnInfo columnInfo;
    private ProxyState<com.geogreenapps.apps.indukaan.classes.Module> proxyState;

    ModuleRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (ModuleColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.geogreenapps.apps.indukaan.classes.Module>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
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
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'name' cannot be changed after object was created.");
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$enabled() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.enabledIndex);
    }

    @Override
    public void realmSet$enabled(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.enabledIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.enabledIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("Module", 2, 0);
        builder.addPersistedProperty("name", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("enabled", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static ModuleColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new ModuleColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "Module";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.geogreenapps.apps.indukaan.classes.Module createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.geogreenapps.apps.indukaan.classes.Module obj = null;
        if (update) {
            Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Module.class);
            ModuleColumnInfo columnInfo = (ModuleColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Module.class);
            long pkColumnIndex = columnInfo.nameIndex;
            long rowIndex = Table.NO_MATCH;
            if (json.isNull("name")) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString("name"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Module.class), false, Collections.<String> emptyList());
                    obj = new io.realm.ModuleRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("name")) {
                if (json.isNull("name")) {
                    obj = (io.realm.ModuleRealmProxy) realm.createObjectInternal(com.geogreenapps.apps.indukaan.classes.Module.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.ModuleRealmProxy) realm.createObjectInternal(com.geogreenapps.apps.indukaan.classes.Module.class, json.getString("name"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'name'.");
            }
        }

        final ModuleRealmProxyInterface objProxy = (ModuleRealmProxyInterface) obj;
        if (json.has("enabled")) {
            if (json.isNull("enabled")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'enabled' to null.");
            } else {
                objProxy.realmSet$enabled((int) json.getInt("enabled"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.geogreenapps.apps.indukaan.classes.Module createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.geogreenapps.apps.indukaan.classes.Module obj = new com.geogreenapps.apps.indukaan.classes.Module();
        final ModuleRealmProxyInterface objProxy = (ModuleRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("name")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$name((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$name(null);
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("enabled")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$enabled((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'enabled' to null.");
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'name'.");
        }
        return realm.copyToRealm(obj);
    }

    public static com.geogreenapps.apps.indukaan.classes.Module copyOrUpdate(Realm realm, com.geogreenapps.apps.indukaan.classes.Module object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
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
            return (com.geogreenapps.apps.indukaan.classes.Module) cachedRealmObject;
        }

        com.geogreenapps.apps.indukaan.classes.Module realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Module.class);
            ModuleColumnInfo columnInfo = (ModuleColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Module.class);
            long pkColumnIndex = columnInfo.nameIndex;
            String value = ((ModuleRealmProxyInterface) object).realmGet$name();
            long rowIndex = Table.NO_MATCH;
            if (value == null) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, value);
            }
            if (rowIndex == Table.NO_MATCH) {
                canUpdate = false;
            } else {
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Module.class), false, Collections.<String> emptyList());
                    realmObject = new io.realm.ModuleRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, realmObject, object, cache) : copy(realm, object, update, cache);
    }

    public static com.geogreenapps.apps.indukaan.classes.Module copy(Realm realm, com.geogreenapps.apps.indukaan.classes.Module newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.geogreenapps.apps.indukaan.classes.Module) cachedRealmObject;
        }

        // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
        com.geogreenapps.apps.indukaan.classes.Module realmObject = realm.createObjectInternal(com.geogreenapps.apps.indukaan.classes.Module.class, ((ModuleRealmProxyInterface) newObject).realmGet$name(), false, Collections.<String>emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);

        ModuleRealmProxyInterface realmObjectSource = (ModuleRealmProxyInterface) newObject;
        ModuleRealmProxyInterface realmObjectCopy = (ModuleRealmProxyInterface) realmObject;

        realmObjectCopy.realmSet$enabled(realmObjectSource.realmGet$enabled());
        return realmObject;
    }

    public static long insert(Realm realm, com.geogreenapps.apps.indukaan.classes.Module object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Module.class);
        long tableNativePtr = table.getNativePtr();
        ModuleColumnInfo columnInfo = (ModuleColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Module.class);
        long pkColumnIndex = columnInfo.nameIndex;
        String primaryKeyValue = ((ModuleRealmProxyInterface) object).realmGet$name();
        long rowIndex = Table.NO_MATCH;
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.enabledIndex, rowIndex, ((ModuleRealmProxyInterface) object).realmGet$enabled(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Module.class);
        long tableNativePtr = table.getNativePtr();
        ModuleColumnInfo columnInfo = (ModuleColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Module.class);
        long pkColumnIndex = columnInfo.nameIndex;
        com.geogreenapps.apps.indukaan.classes.Module object = null;
        while (objects.hasNext()) {
            object = (com.geogreenapps.apps.indukaan.classes.Module) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            String primaryKeyValue = ((ModuleRealmProxyInterface) object).realmGet$name();
            long rowIndex = Table.NO_MATCH;
            if (primaryKeyValue == null) {
                rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
            } else {
                rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.enabledIndex, rowIndex, ((ModuleRealmProxyInterface) object).realmGet$enabled(), false);
        }
    }

    public static long insertOrUpdate(Realm realm, com.geogreenapps.apps.indukaan.classes.Module object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Module.class);
        long tableNativePtr = table.getNativePtr();
        ModuleColumnInfo columnInfo = (ModuleColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Module.class);
        long pkColumnIndex = columnInfo.nameIndex;
        String primaryKeyValue = ((ModuleRealmProxyInterface) object).realmGet$name();
        long rowIndex = Table.NO_MATCH;
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
        }
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.enabledIndex, rowIndex, ((ModuleRealmProxyInterface) object).realmGet$enabled(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Module.class);
        long tableNativePtr = table.getNativePtr();
        ModuleColumnInfo columnInfo = (ModuleColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Module.class);
        long pkColumnIndex = columnInfo.nameIndex;
        com.geogreenapps.apps.indukaan.classes.Module object = null;
        while (objects.hasNext()) {
            object = (com.geogreenapps.apps.indukaan.classes.Module) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            String primaryKeyValue = ((ModuleRealmProxyInterface) object).realmGet$name();
            long rowIndex = Table.NO_MATCH;
            if (primaryKeyValue == null) {
                rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
            } else {
                rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
            }
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.enabledIndex, rowIndex, ((ModuleRealmProxyInterface) object).realmGet$enabled(), false);
        }
    }

    public static com.geogreenapps.apps.indukaan.classes.Module createDetachedCopy(com.geogreenapps.apps.indukaan.classes.Module realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.geogreenapps.apps.indukaan.classes.Module unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.geogreenapps.apps.indukaan.classes.Module();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.geogreenapps.apps.indukaan.classes.Module) cachedObject.object;
            }
            unmanagedObject = (com.geogreenapps.apps.indukaan.classes.Module) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        ModuleRealmProxyInterface unmanagedCopy = (ModuleRealmProxyInterface) unmanagedObject;
        ModuleRealmProxyInterface realmSource = (ModuleRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$name(realmSource.realmGet$name());
        unmanagedCopy.realmSet$enabled(realmSource.realmGet$enabled());

        return unmanagedObject;
    }

    static com.geogreenapps.apps.indukaan.classes.Module update(Realm realm, com.geogreenapps.apps.indukaan.classes.Module realmObject, com.geogreenapps.apps.indukaan.classes.Module newObject, Map<RealmModel, RealmObjectProxy> cache) {
        ModuleRealmProxyInterface realmObjectTarget = (ModuleRealmProxyInterface) realmObject;
        ModuleRealmProxyInterface realmObjectSource = (ModuleRealmProxyInterface) newObject;
        realmObjectTarget.realmSet$enabled(realmObjectSource.realmGet$enabled());
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Module = proxy[");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name() != null ? realmGet$name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{enabled:");
        stringBuilder.append(realmGet$enabled());
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
        ModuleRealmProxy aModule = (ModuleRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aModule.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aModule.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aModule.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
