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
public class TimeLineRealmProxy extends com.geogreenapps.apps.indukaan.classes.TimeLine
    implements RealmObjectProxy, TimeLineRealmProxyInterface {

    static final class TimeLineColumnInfo extends ColumnInfo {
        long titleIndex;
        long dateIndex;
        long ownerIndex;
        long statusIndex;
        long messageIndex;
        long sidIndex;

        TimeLineColumnInfo(OsSchemaInfo schemaInfo) {
            super(6);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("TimeLine");
            this.titleIndex = addColumnDetails("title", objectSchemaInfo);
            this.dateIndex = addColumnDetails("date", objectSchemaInfo);
            this.ownerIndex = addColumnDetails("owner", objectSchemaInfo);
            this.statusIndex = addColumnDetails("status", objectSchemaInfo);
            this.messageIndex = addColumnDetails("message", objectSchemaInfo);
            this.sidIndex = addColumnDetails("sid", objectSchemaInfo);
        }

        TimeLineColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new TimeLineColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final TimeLineColumnInfo src = (TimeLineColumnInfo) rawSrc;
            final TimeLineColumnInfo dst = (TimeLineColumnInfo) rawDst;
            dst.titleIndex = src.titleIndex;
            dst.dateIndex = src.dateIndex;
            dst.ownerIndex = src.ownerIndex;
            dst.statusIndex = src.statusIndex;
            dst.messageIndex = src.messageIndex;
            dst.sidIndex = src.sidIndex;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>(6);
        fieldNames.add("title");
        fieldNames.add("date");
        fieldNames.add("owner");
        fieldNames.add("status");
        fieldNames.add("message");
        fieldNames.add("sid");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    private TimeLineColumnInfo columnInfo;
    private ProxyState<com.geogreenapps.apps.indukaan.classes.TimeLine> proxyState;

    TimeLineRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (TimeLineColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.geogreenapps.apps.indukaan.classes.TimeLine>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$title() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.titleIndex);
    }

    @Override
    public void realmSet$title(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.titleIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.titleIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.titleIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.titleIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$date() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.dateIndex);
    }

    @Override
    public void realmSet$date(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.dateIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.dateIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.dateIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.dateIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$owner() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.ownerIndex);
    }

    @Override
    public void realmSet$owner(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.ownerIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.ownerIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.ownerIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.ownerIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$status() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.statusIndex);
    }

    @Override
    public void realmSet$status(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.statusIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.statusIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.statusIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.statusIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$message() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.messageIndex);
    }

    @Override
    public void realmSet$message(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.messageIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.messageIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.messageIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.messageIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$sid() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.sidIndex);
    }

    @Override
    public void realmSet$sid(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.sidIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.sidIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("TimeLine", 6, 0);
        builder.addPersistedProperty("title", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("date", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("owner", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("status", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("message", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("sid", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static TimeLineColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new TimeLineColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "TimeLine";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.geogreenapps.apps.indukaan.classes.TimeLine createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.geogreenapps.apps.indukaan.classes.TimeLine obj = realm.createObjectInternal(com.geogreenapps.apps.indukaan.classes.TimeLine.class, true, excludeFields);

        final TimeLineRealmProxyInterface objProxy = (TimeLineRealmProxyInterface) obj;
        if (json.has("title")) {
            if (json.isNull("title")) {
                objProxy.realmSet$title(null);
            } else {
                objProxy.realmSet$title((String) json.getString("title"));
            }
        }
        if (json.has("date")) {
            if (json.isNull("date")) {
                objProxy.realmSet$date(null);
            } else {
                objProxy.realmSet$date((String) json.getString("date"));
            }
        }
        if (json.has("owner")) {
            if (json.isNull("owner")) {
                objProxy.realmSet$owner(null);
            } else {
                objProxy.realmSet$owner((String) json.getString("owner"));
            }
        }
        if (json.has("status")) {
            if (json.isNull("status")) {
                objProxy.realmSet$status(null);
            } else {
                objProxy.realmSet$status((String) json.getString("status"));
            }
        }
        if (json.has("message")) {
            if (json.isNull("message")) {
                objProxy.realmSet$message(null);
            } else {
                objProxy.realmSet$message((String) json.getString("message"));
            }
        }
        if (json.has("sid")) {
            if (json.isNull("sid")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'sid' to null.");
            } else {
                objProxy.realmSet$sid((int) json.getInt("sid"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.geogreenapps.apps.indukaan.classes.TimeLine createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        final com.geogreenapps.apps.indukaan.classes.TimeLine obj = new com.geogreenapps.apps.indukaan.classes.TimeLine();
        final TimeLineRealmProxyInterface objProxy = (TimeLineRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("title")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$title((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$title(null);
                }
            } else if (name.equals("date")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$date((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$date(null);
                }
            } else if (name.equals("owner")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$owner((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$owner(null);
                }
            } else if (name.equals("status")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$status((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$status(null);
                }
            } else if (name.equals("message")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$message((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$message(null);
                }
            } else if (name.equals("sid")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$sid((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'sid' to null.");
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return realm.copyToRealm(obj);
    }

    public static com.geogreenapps.apps.indukaan.classes.TimeLine copyOrUpdate(Realm realm, com.geogreenapps.apps.indukaan.classes.TimeLine object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
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
            return (com.geogreenapps.apps.indukaan.classes.TimeLine) cachedRealmObject;
        }

        return copy(realm, object, update, cache);
    }

    public static com.geogreenapps.apps.indukaan.classes.TimeLine copy(Realm realm, com.geogreenapps.apps.indukaan.classes.TimeLine newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.geogreenapps.apps.indukaan.classes.TimeLine) cachedRealmObject;
        }

        // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
        com.geogreenapps.apps.indukaan.classes.TimeLine realmObject = realm.createObjectInternal(com.geogreenapps.apps.indukaan.classes.TimeLine.class, false, Collections.<String>emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);

        TimeLineRealmProxyInterface realmObjectSource = (TimeLineRealmProxyInterface) newObject;
        TimeLineRealmProxyInterface realmObjectCopy = (TimeLineRealmProxyInterface) realmObject;

        realmObjectCopy.realmSet$title(realmObjectSource.realmGet$title());
        realmObjectCopy.realmSet$date(realmObjectSource.realmGet$date());
        realmObjectCopy.realmSet$owner(realmObjectSource.realmGet$owner());
        realmObjectCopy.realmSet$status(realmObjectSource.realmGet$status());
        realmObjectCopy.realmSet$message(realmObjectSource.realmGet$message());
        realmObjectCopy.realmSet$sid(realmObjectSource.realmGet$sid());
        return realmObject;
    }

    public static long insert(Realm realm, com.geogreenapps.apps.indukaan.classes.TimeLine object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.TimeLine.class);
        long tableNativePtr = table.getNativePtr();
        TimeLineColumnInfo columnInfo = (TimeLineColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.TimeLine.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, rowIndex);
        String realmGet$title = ((TimeLineRealmProxyInterface) object).realmGet$title();
        if (realmGet$title != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
        }
        String realmGet$date = ((TimeLineRealmProxyInterface) object).realmGet$date();
        if (realmGet$date != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.dateIndex, rowIndex, realmGet$date, false);
        }
        String realmGet$owner = ((TimeLineRealmProxyInterface) object).realmGet$owner();
        if (realmGet$owner != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.ownerIndex, rowIndex, realmGet$owner, false);
        }
        String realmGet$status = ((TimeLineRealmProxyInterface) object).realmGet$status();
        if (realmGet$status != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.statusIndex, rowIndex, realmGet$status, false);
        }
        String realmGet$message = ((TimeLineRealmProxyInterface) object).realmGet$message();
        if (realmGet$message != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.messageIndex, rowIndex, realmGet$message, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.sidIndex, rowIndex, ((TimeLineRealmProxyInterface) object).realmGet$sid(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.TimeLine.class);
        long tableNativePtr = table.getNativePtr();
        TimeLineColumnInfo columnInfo = (TimeLineColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.TimeLine.class);
        com.geogreenapps.apps.indukaan.classes.TimeLine object = null;
        while (objects.hasNext()) {
            object = (com.geogreenapps.apps.indukaan.classes.TimeLine) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = OsObject.createRow(table);
            cache.put(object, rowIndex);
            String realmGet$title = ((TimeLineRealmProxyInterface) object).realmGet$title();
            if (realmGet$title != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
            }
            String realmGet$date = ((TimeLineRealmProxyInterface) object).realmGet$date();
            if (realmGet$date != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.dateIndex, rowIndex, realmGet$date, false);
            }
            String realmGet$owner = ((TimeLineRealmProxyInterface) object).realmGet$owner();
            if (realmGet$owner != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.ownerIndex, rowIndex, realmGet$owner, false);
            }
            String realmGet$status = ((TimeLineRealmProxyInterface) object).realmGet$status();
            if (realmGet$status != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.statusIndex, rowIndex, realmGet$status, false);
            }
            String realmGet$message = ((TimeLineRealmProxyInterface) object).realmGet$message();
            if (realmGet$message != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.messageIndex, rowIndex, realmGet$message, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.sidIndex, rowIndex, ((TimeLineRealmProxyInterface) object).realmGet$sid(), false);
        }
    }

    public static long insertOrUpdate(Realm realm, com.geogreenapps.apps.indukaan.classes.TimeLine object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.TimeLine.class);
        long tableNativePtr = table.getNativePtr();
        TimeLineColumnInfo columnInfo = (TimeLineColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.TimeLine.class);
        long rowIndex = OsObject.createRow(table);
        cache.put(object, rowIndex);
        String realmGet$title = ((TimeLineRealmProxyInterface) object).realmGet$title();
        if (realmGet$title != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.titleIndex, rowIndex, false);
        }
        String realmGet$date = ((TimeLineRealmProxyInterface) object).realmGet$date();
        if (realmGet$date != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.dateIndex, rowIndex, realmGet$date, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.dateIndex, rowIndex, false);
        }
        String realmGet$owner = ((TimeLineRealmProxyInterface) object).realmGet$owner();
        if (realmGet$owner != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.ownerIndex, rowIndex, realmGet$owner, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.ownerIndex, rowIndex, false);
        }
        String realmGet$status = ((TimeLineRealmProxyInterface) object).realmGet$status();
        if (realmGet$status != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.statusIndex, rowIndex, realmGet$status, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.statusIndex, rowIndex, false);
        }
        String realmGet$message = ((TimeLineRealmProxyInterface) object).realmGet$message();
        if (realmGet$message != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.messageIndex, rowIndex, realmGet$message, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.messageIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.sidIndex, rowIndex, ((TimeLineRealmProxyInterface) object).realmGet$sid(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.TimeLine.class);
        long tableNativePtr = table.getNativePtr();
        TimeLineColumnInfo columnInfo = (TimeLineColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.TimeLine.class);
        com.geogreenapps.apps.indukaan.classes.TimeLine object = null;
        while (objects.hasNext()) {
            object = (com.geogreenapps.apps.indukaan.classes.TimeLine) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = OsObject.createRow(table);
            cache.put(object, rowIndex);
            String realmGet$title = ((TimeLineRealmProxyInterface) object).realmGet$title();
            if (realmGet$title != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.titleIndex, rowIndex, realmGet$title, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.titleIndex, rowIndex, false);
            }
            String realmGet$date = ((TimeLineRealmProxyInterface) object).realmGet$date();
            if (realmGet$date != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.dateIndex, rowIndex, realmGet$date, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.dateIndex, rowIndex, false);
            }
            String realmGet$owner = ((TimeLineRealmProxyInterface) object).realmGet$owner();
            if (realmGet$owner != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.ownerIndex, rowIndex, realmGet$owner, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.ownerIndex, rowIndex, false);
            }
            String realmGet$status = ((TimeLineRealmProxyInterface) object).realmGet$status();
            if (realmGet$status != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.statusIndex, rowIndex, realmGet$status, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.statusIndex, rowIndex, false);
            }
            String realmGet$message = ((TimeLineRealmProxyInterface) object).realmGet$message();
            if (realmGet$message != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.messageIndex, rowIndex, realmGet$message, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.messageIndex, rowIndex, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.sidIndex, rowIndex, ((TimeLineRealmProxyInterface) object).realmGet$sid(), false);
        }
    }

    public static com.geogreenapps.apps.indukaan.classes.TimeLine createDetachedCopy(com.geogreenapps.apps.indukaan.classes.TimeLine realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.geogreenapps.apps.indukaan.classes.TimeLine unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.geogreenapps.apps.indukaan.classes.TimeLine();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.geogreenapps.apps.indukaan.classes.TimeLine) cachedObject.object;
            }
            unmanagedObject = (com.geogreenapps.apps.indukaan.classes.TimeLine) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        TimeLineRealmProxyInterface unmanagedCopy = (TimeLineRealmProxyInterface) unmanagedObject;
        TimeLineRealmProxyInterface realmSource = (TimeLineRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$title(realmSource.realmGet$title());
        unmanagedCopy.realmSet$date(realmSource.realmGet$date());
        unmanagedCopy.realmSet$owner(realmSource.realmGet$owner());
        unmanagedCopy.realmSet$status(realmSource.realmGet$status());
        unmanagedCopy.realmSet$message(realmSource.realmGet$message());
        unmanagedCopy.realmSet$sid(realmSource.realmGet$sid());

        return unmanagedObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("TimeLine = proxy[");
        stringBuilder.append("{title:");
        stringBuilder.append(realmGet$title() != null ? realmGet$title() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{date:");
        stringBuilder.append(realmGet$date() != null ? realmGet$date() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{owner:");
        stringBuilder.append(realmGet$owner() != null ? realmGet$owner() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{status:");
        stringBuilder.append(realmGet$status() != null ? realmGet$status() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{message:");
        stringBuilder.append(realmGet$message() != null ? realmGet$message() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{sid:");
        stringBuilder.append(realmGet$sid());
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
        TimeLineRealmProxy aTimeLine = (TimeLineRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aTimeLine.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aTimeLine.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aTimeLine.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
