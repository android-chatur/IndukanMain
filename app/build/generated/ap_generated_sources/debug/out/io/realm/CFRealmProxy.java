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
public class CFRealmProxy extends com.geogreenapps.apps.indukaan.classes.CF
    implements RealmObjectProxy, CFRealmProxyInterface {

    static final class CFColumnInfo extends ColumnInfo {
        long labelIndex;
        long typeIndex;
        long requiredIndex;
        long orderIndex;
        long stepIndex;

        CFColumnInfo(OsSchemaInfo schemaInfo) {
            super(5);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("CF");
            this.labelIndex = addColumnDetails("label", objectSchemaInfo);
            this.typeIndex = addColumnDetails("type", objectSchemaInfo);
            this.requiredIndex = addColumnDetails("required", objectSchemaInfo);
            this.orderIndex = addColumnDetails("order", objectSchemaInfo);
            this.stepIndex = addColumnDetails("step", objectSchemaInfo);
        }

        CFColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new CFColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final CFColumnInfo src = (CFColumnInfo) rawSrc;
            final CFColumnInfo dst = (CFColumnInfo) rawDst;
            dst.labelIndex = src.labelIndex;
            dst.typeIndex = src.typeIndex;
            dst.requiredIndex = src.requiredIndex;
            dst.orderIndex = src.orderIndex;
            dst.stepIndex = src.stepIndex;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>(5);
        fieldNames.add("label");
        fieldNames.add("type");
        fieldNames.add("required");
        fieldNames.add("order");
        fieldNames.add("step");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    private CFColumnInfo columnInfo;
    private ProxyState<com.geogreenapps.apps.indukaan.classes.CF> proxyState;

    CFRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (CFColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.geogreenapps.apps.indukaan.classes.CF>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$label() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.labelIndex);
    }

    @Override
    public void realmSet$label(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.labelIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.labelIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.labelIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.labelIndex, value);
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
    @SuppressWarnings("cast")
    public int realmGet$required() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.requiredIndex);
    }

    @Override
    public void realmSet$required(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.requiredIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.requiredIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$order() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.orderIndex);
    }

    @Override
    public void realmSet$order(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.orderIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.orderIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$step() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.stepIndex);
    }

    @Override
    public void realmSet$step(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.stepIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.stepIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("CF", 5, 0);
        builder.addPersistedProperty("label", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("type", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("required", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("order", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("step", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static CFColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new CFColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "CF";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.geogreenapps.apps.indukaan.classes.CF createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.geogreenapps.apps.indukaan.classes.CF obj = realm.createObjectInternal(com.geogreenapps.apps.indukaan.classes.CF.class, true, excludeFields);

        final CFRealmProxyInterface objProxy = (CFRealmProxyInterface) obj;
        if (json.has("label")) {
            if (json.isNull("label")) {
                objProxy.realmSet$label(null);
            } else {
                objProxy.realmSet$label((String) json.getString("label"));
            }
        }
        if (json.has("type")) {
            if (json.isNull("type")) {
                objProxy.realmSet$type(null);
            } else {
                objProxy.realmSet$type((String) json.getString("type"));
            }
        }
        if (json.has("required")) {
            if (json.isNull("required")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'required' to null.");
            } else {
                objProxy.realmSet$required((int) json.getInt("required"));
            }
        }
        if (json.has("order")) {
            if (json.isNull("order")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'order' to null.");
            } else {
                objProxy.realmSet$order((int) json.getInt("order"));
            }
        }
        if (json.has("step")) {
            if (json.isNull("step")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'step' to null.");
            } else {
                objProxy.realmSet$step((int) json.getInt("step"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.geogreenapps.apps.indukaan.classes.CF createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        final com.geogreenapps.apps.indukaan.classes.CF obj = new com.geogreenapps.apps.indukaan.classes.CF();
        final CFRealmProxyInterface objProxy = (CFRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("label")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$label((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$label(null);
                }
            } else if (name.equals("type")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$type((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$type(null);
                }
            } else if (name.equals("required")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$required((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'required' to null.");
                }
            } else if (name.equals("order")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$order((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'order' to null.");
                }
            } else if (name.equals("step")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$step((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'step' to null.");
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return realm.copyToRealm(obj);
    }

    public static com.geogreenapps.apps.indukaan.classes.CF copyOrUpdate(Realm realm, com.geogreenapps.apps.indukaan.classes.CF object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
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
            return (com.geogreenapps.apps.indukaan.classes.CF) cachedRealmObject;
        }

        return copy(realm, object, update, cache);
    }

    public static com.geogreenapps.apps.indukaan.classes.CF copy(Realm realm, com.geogreenapps.apps.indukaan.classes.CF newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.geogreenapps.apps.indukaan.classes.CF) cachedRealmObject;
        }

        // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
        com.geogreenapps.apps.indukaan.classes.CF realmObject = realm.createObjectInternal(com.geogreenapps.apps.indukaan.classes.CF.class, false, Collections.<String>emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);

        CFRealmProxyInterface realmObjectSource = (CFRealmProxyInterface) newObject;
        CFRealmProxyInterface realmObjectCopy = (CFRealmProxyInterface) realmObject;

        realmObjectCopy.realmSet$label(realmObjectSource.realmGet$label());
        realmObjectCopy.realmSet$type(realmObjectSource.realmGet$type());
        realmObjectCopy.realmSet$required(realmObjectSource.realmGet$required());
        realmObjectCopy.realmSet$order(realmObjectSource.realmGet$order());
        realmObjectCopy.realmSet$step(realmObjectSource.realmGet$step());
        return realmObject;
    }

    public static long insert(Realm realm, com.geogreenapps.apps.indukaan.classes.CF object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.CF.class);
        long tableNativePtr = table.getNativePtr();
        CFColumnInfo columnInfo = (CFColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.CF.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, rowIndex);
        String realmGet$label = ((CFRealmProxyInterface) object).realmGet$label();
        if (realmGet$label != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.labelIndex, rowIndex, realmGet$label, false);
        }
        String realmGet$type = ((CFRealmProxyInterface) object).realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.requiredIndex, rowIndex, ((CFRealmProxyInterface) object).realmGet$required(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.orderIndex, rowIndex, ((CFRealmProxyInterface) object).realmGet$order(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.stepIndex, rowIndex, ((CFRealmProxyInterface) object).realmGet$step(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.CF.class);
        long tableNativePtr = table.getNativePtr();
        CFColumnInfo columnInfo = (CFColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.CF.class);
        com.geogreenapps.apps.indukaan.classes.CF object = null;
        while (objects.hasNext()) {
            object = (com.geogreenapps.apps.indukaan.classes.CF) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = OsObject.createRow(table);
            cache.put(object, rowIndex);
            String realmGet$label = ((CFRealmProxyInterface) object).realmGet$label();
            if (realmGet$label != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.labelIndex, rowIndex, realmGet$label, false);
            }
            String realmGet$type = ((CFRealmProxyInterface) object).realmGet$type();
            if (realmGet$type != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.requiredIndex, rowIndex, ((CFRealmProxyInterface) object).realmGet$required(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.orderIndex, rowIndex, ((CFRealmProxyInterface) object).realmGet$order(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.stepIndex, rowIndex, ((CFRealmProxyInterface) object).realmGet$step(), false);
        }
    }

    public static long insertOrUpdate(Realm realm, com.geogreenapps.apps.indukaan.classes.CF object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.CF.class);
        long tableNativePtr = table.getNativePtr();
        CFColumnInfo columnInfo = (CFColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.CF.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, rowIndex);
        String realmGet$label = ((CFRealmProxyInterface) object).realmGet$label();
        if (realmGet$label != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.labelIndex, rowIndex, realmGet$label, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.labelIndex, rowIndex, false);
        }
        String realmGet$type = ((CFRealmProxyInterface) object).realmGet$type();
        if (realmGet$type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.typeIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.requiredIndex, rowIndex, ((CFRealmProxyInterface) object).realmGet$required(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.orderIndex, rowIndex, ((CFRealmProxyInterface) object).realmGet$order(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.stepIndex, rowIndex, ((CFRealmProxyInterface) object).realmGet$step(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.CF.class);
        long tableNativePtr = table.getNativePtr();
        CFColumnInfo columnInfo = (CFColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.CF.class);
        com.geogreenapps.apps.indukaan.classes.CF object = null;
        while (objects.hasNext()) {
            object = (com.geogreenapps.apps.indukaan.classes.CF) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = OsObject.createRow(table);
            cache.put(object, rowIndex);
            String realmGet$label = ((CFRealmProxyInterface) object).realmGet$label();
            if (realmGet$label != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.labelIndex, rowIndex, realmGet$label, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.labelIndex, rowIndex, false);
            }
            String realmGet$type = ((CFRealmProxyInterface) object).realmGet$type();
            if (realmGet$type != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.typeIndex, rowIndex, realmGet$type, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.typeIndex, rowIndex, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.requiredIndex, rowIndex, ((CFRealmProxyInterface) object).realmGet$required(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.orderIndex, rowIndex, ((CFRealmProxyInterface) object).realmGet$order(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.stepIndex, rowIndex, ((CFRealmProxyInterface) object).realmGet$step(), false);
        }
    }

    public static com.geogreenapps.apps.indukaan.classes.CF createDetachedCopy(com.geogreenapps.apps.indukaan.classes.CF realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.geogreenapps.apps.indukaan.classes.CF unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.geogreenapps.apps.indukaan.classes.CF();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.geogreenapps.apps.indukaan.classes.CF) cachedObject.object;
            }
            unmanagedObject = (com.geogreenapps.apps.indukaan.classes.CF) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        CFRealmProxyInterface unmanagedCopy = (CFRealmProxyInterface) unmanagedObject;
        CFRealmProxyInterface realmSource = (CFRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$label(realmSource.realmGet$label());
        unmanagedCopy.realmSet$type(realmSource.realmGet$type());
        unmanagedCopy.realmSet$required(realmSource.realmGet$required());
        unmanagedCopy.realmSet$order(realmSource.realmGet$order());
        unmanagedCopy.realmSet$step(realmSource.realmGet$step());

        return unmanagedObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("CF = proxy[");
        stringBuilder.append("{label:");
        stringBuilder.append(realmGet$label() != null ? realmGet$label() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{type:");
        stringBuilder.append(realmGet$type() != null ? realmGet$type() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{required:");
        stringBuilder.append(realmGet$required());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{order:");
        stringBuilder.append(realmGet$order());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{step:");
        stringBuilder.append(realmGet$step());
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
        CFRealmProxy aCF = (CFRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aCF.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aCF.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aCF.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
