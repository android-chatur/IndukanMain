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
public class OptionRealmProxy extends com.geogreenapps.apps.indukaan.classes.Option
    implements RealmObjectProxy, OptionRealmProxyInterface {

    static final class OptionColumnInfo extends ColumnInfo {
        long idIndex;
        long product_idIndex;
        long labelIndex;
        long valueIndex;
        long parent_idIndex;
        long _orderIndex;
        long option_typeIndex;
        long updated_atIndex;
        long created_atIndex;
        long parsed_valueIndex;

        OptionColumnInfo(OsSchemaInfo schemaInfo) {
            super(10);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Option");
            this.idIndex = addColumnDetails("id", objectSchemaInfo);
            this.product_idIndex = addColumnDetails("product_id", objectSchemaInfo);
            this.labelIndex = addColumnDetails("label", objectSchemaInfo);
            this.valueIndex = addColumnDetails("value", objectSchemaInfo);
            this.parent_idIndex = addColumnDetails("parent_id", objectSchemaInfo);
            this._orderIndex = addColumnDetails("_order", objectSchemaInfo);
            this.option_typeIndex = addColumnDetails("option_type", objectSchemaInfo);
            this.updated_atIndex = addColumnDetails("updated_at", objectSchemaInfo);
            this.created_atIndex = addColumnDetails("created_at", objectSchemaInfo);
            this.parsed_valueIndex = addColumnDetails("parsed_value", objectSchemaInfo);
        }

        OptionColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new OptionColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final OptionColumnInfo src = (OptionColumnInfo) rawSrc;
            final OptionColumnInfo dst = (OptionColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.product_idIndex = src.product_idIndex;
            dst.labelIndex = src.labelIndex;
            dst.valueIndex = src.valueIndex;
            dst.parent_idIndex = src.parent_idIndex;
            dst._orderIndex = src._orderIndex;
            dst.option_typeIndex = src.option_typeIndex;
            dst.updated_atIndex = src.updated_atIndex;
            dst.created_atIndex = src.created_atIndex;
            dst.parsed_valueIndex = src.parsed_valueIndex;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>(10);
        fieldNames.add("id");
        fieldNames.add("product_id");
        fieldNames.add("label");
        fieldNames.add("value");
        fieldNames.add("parent_id");
        fieldNames.add("_order");
        fieldNames.add("option_type");
        fieldNames.add("updated_at");
        fieldNames.add("created_at");
        fieldNames.add("parsed_value");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    private OptionColumnInfo columnInfo;
    private ProxyState<com.geogreenapps.apps.indukaan.classes.Option> proxyState;

    OptionRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (OptionColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.geogreenapps.apps.indukaan.classes.Option>(this);
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
    public int realmGet$product_id() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.product_idIndex);
    }

    @Override
    public void realmSet$product_id(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.product_idIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.product_idIndex, value);
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
    public Double realmGet$value() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNull(columnInfo.valueIndex)) {
            return null;
        }
        return (double) proxyState.getRow$realm().getDouble(columnInfo.valueIndex);
    }

    @Override
    public void realmSet$value(Double value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.valueIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setDouble(columnInfo.valueIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.valueIndex);
            return;
        }
        proxyState.getRow$realm().setDouble(columnInfo.valueIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$parent_id() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.parent_idIndex);
    }

    @Override
    public void realmSet$parent_id(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.parent_idIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.parent_idIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$_order() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo._orderIndex);
    }

    @Override
    public void realmSet$_order(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo._orderIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo._orderIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$option_type() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.option_typeIndex);
    }

    @Override
    public void realmSet$option_type(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.option_typeIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.option_typeIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.option_typeIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.option_typeIndex, value);
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

    @Override
    @SuppressWarnings("cast")
    public String realmGet$parsed_value() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.parsed_valueIndex);
    }

    @Override
    public void realmSet$parsed_value(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.parsed_valueIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.parsed_valueIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.parsed_valueIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.parsed_valueIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("Option", 10, 0);
        builder.addPersistedProperty("id", RealmFieldType.INTEGER, Property.PRIMARY_KEY, Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("product_id", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("label", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("value", RealmFieldType.DOUBLE, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("parent_id", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("_order", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("option_type", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("updated_at", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("created_at", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("parsed_value", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static OptionColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new OptionColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "Option";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.geogreenapps.apps.indukaan.classes.Option createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.geogreenapps.apps.indukaan.classes.Option obj = null;
        if (update) {
            Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Option.class);
            OptionColumnInfo columnInfo = (OptionColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Option.class);
            long pkColumnIndex = columnInfo.idIndex;
            long rowIndex = Table.NO_MATCH;
            if (!json.isNull("id")) {
                rowIndex = table.findFirstLong(pkColumnIndex, json.getLong("id"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Option.class), false, Collections.<String> emptyList());
                    obj = new io.realm.OptionRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.OptionRealmProxy) realm.createObjectInternal(com.geogreenapps.apps.indukaan.classes.Option.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.OptionRealmProxy) realm.createObjectInternal(com.geogreenapps.apps.indukaan.classes.Option.class, json.getInt("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }

        final OptionRealmProxyInterface objProxy = (OptionRealmProxyInterface) obj;
        if (json.has("product_id")) {
            if (json.isNull("product_id")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'product_id' to null.");
            } else {
                objProxy.realmSet$product_id((int) json.getInt("product_id"));
            }
        }
        if (json.has("label")) {
            if (json.isNull("label")) {
                objProxy.realmSet$label(null);
            } else {
                objProxy.realmSet$label((String) json.getString("label"));
            }
        }
        if (json.has("value")) {
            if (json.isNull("value")) {
                objProxy.realmSet$value(null);
            } else {
                objProxy.realmSet$value((double) json.getDouble("value"));
            }
        }
        if (json.has("parent_id")) {
            if (json.isNull("parent_id")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'parent_id' to null.");
            } else {
                objProxy.realmSet$parent_id((int) json.getInt("parent_id"));
            }
        }
        if (json.has("_order")) {
            if (json.isNull("_order")) {
                throw new IllegalArgumentException("Trying to set non-nullable field '_order' to null.");
            } else {
                objProxy.realmSet$_order((int) json.getInt("_order"));
            }
        }
        if (json.has("option_type")) {
            if (json.isNull("option_type")) {
                objProxy.realmSet$option_type(null);
            } else {
                objProxy.realmSet$option_type((String) json.getString("option_type"));
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
        if (json.has("parsed_value")) {
            if (json.isNull("parsed_value")) {
                objProxy.realmSet$parsed_value(null);
            } else {
                objProxy.realmSet$parsed_value((String) json.getString("parsed_value"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.geogreenapps.apps.indukaan.classes.Option createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.geogreenapps.apps.indukaan.classes.Option obj = new com.geogreenapps.apps.indukaan.classes.Option();
        final OptionRealmProxyInterface objProxy = (OptionRealmProxyInterface) obj;
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
            } else if (name.equals("product_id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$product_id((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'product_id' to null.");
                }
            } else if (name.equals("label")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$label((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$label(null);
                }
            } else if (name.equals("value")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$value((double) reader.nextDouble());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$value(null);
                }
            } else if (name.equals("parent_id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$parent_id((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'parent_id' to null.");
                }
            } else if (name.equals("_order")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$_order((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field '_order' to null.");
                }
            } else if (name.equals("option_type")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$option_type((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$option_type(null);
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
            } else if (name.equals("parsed_value")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$parsed_value((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$parsed_value(null);
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

    public static com.geogreenapps.apps.indukaan.classes.Option copyOrUpdate(Realm realm, com.geogreenapps.apps.indukaan.classes.Option object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
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
            return (com.geogreenapps.apps.indukaan.classes.Option) cachedRealmObject;
        }

        com.geogreenapps.apps.indukaan.classes.Option realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Option.class);
            OptionColumnInfo columnInfo = (OptionColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Option.class);
            long pkColumnIndex = columnInfo.idIndex;
            long rowIndex = table.findFirstLong(pkColumnIndex, ((OptionRealmProxyInterface) object).realmGet$id());
            if (rowIndex == Table.NO_MATCH) {
                canUpdate = false;
            } else {
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Option.class), false, Collections.<String> emptyList());
                    realmObject = new io.realm.OptionRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, realmObject, object, cache) : copy(realm, object, update, cache);
    }

    public static com.geogreenapps.apps.indukaan.classes.Option copy(Realm realm, com.geogreenapps.apps.indukaan.classes.Option newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.geogreenapps.apps.indukaan.classes.Option) cachedRealmObject;
        }

        // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
        com.geogreenapps.apps.indukaan.classes.Option realmObject = realm.createObjectInternal(com.geogreenapps.apps.indukaan.classes.Option.class, ((OptionRealmProxyInterface) newObject).realmGet$id(), false, Collections.<String>emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);

        OptionRealmProxyInterface realmObjectSource = (OptionRealmProxyInterface) newObject;
        OptionRealmProxyInterface realmObjectCopy = (OptionRealmProxyInterface) realmObject;

        realmObjectCopy.realmSet$product_id(realmObjectSource.realmGet$product_id());
        realmObjectCopy.realmSet$label(realmObjectSource.realmGet$label());
        realmObjectCopy.realmSet$value(realmObjectSource.realmGet$value());
        realmObjectCopy.realmSet$parent_id(realmObjectSource.realmGet$parent_id());
        realmObjectCopy.realmSet$_order(realmObjectSource.realmGet$_order());
        realmObjectCopy.realmSet$option_type(realmObjectSource.realmGet$option_type());
        realmObjectCopy.realmSet$updated_at(realmObjectSource.realmGet$updated_at());
        realmObjectCopy.realmSet$created_at(realmObjectSource.realmGet$created_at());
        realmObjectCopy.realmSet$parsed_value(realmObjectSource.realmGet$parsed_value());
        return realmObject;
    }

    public static long insert(Realm realm, com.geogreenapps.apps.indukaan.classes.Option object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Option.class);
        long tableNativePtr = table.getNativePtr();
        OptionColumnInfo columnInfo = (OptionColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Option.class);
        long pkColumnIndex = columnInfo.idIndex;
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((OptionRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((OptionRealmProxyInterface) object).realmGet$id());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((OptionRealmProxyInterface) object).realmGet$id());
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.product_idIndex, rowIndex, ((OptionRealmProxyInterface) object).realmGet$product_id(), false);
        String realmGet$label = ((OptionRealmProxyInterface) object).realmGet$label();
        if (realmGet$label != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.labelIndex, rowIndex, realmGet$label, false);
        }
        Double realmGet$value = ((OptionRealmProxyInterface) object).realmGet$value();
        if (realmGet$value != null) {
            Table.nativeSetDouble(tableNativePtr, columnInfo.valueIndex, rowIndex, realmGet$value, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.parent_idIndex, rowIndex, ((OptionRealmProxyInterface) object).realmGet$parent_id(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo._orderIndex, rowIndex, ((OptionRealmProxyInterface) object).realmGet$_order(), false);
        String realmGet$option_type = ((OptionRealmProxyInterface) object).realmGet$option_type();
        if (realmGet$option_type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.option_typeIndex, rowIndex, realmGet$option_type, false);
        }
        String realmGet$updated_at = ((OptionRealmProxyInterface) object).realmGet$updated_at();
        if (realmGet$updated_at != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.updated_atIndex, rowIndex, realmGet$updated_at, false);
        }
        String realmGet$created_at = ((OptionRealmProxyInterface) object).realmGet$created_at();
        if (realmGet$created_at != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.created_atIndex, rowIndex, realmGet$created_at, false);
        }
        String realmGet$parsed_value = ((OptionRealmProxyInterface) object).realmGet$parsed_value();
        if (realmGet$parsed_value != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.parsed_valueIndex, rowIndex, realmGet$parsed_value, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Option.class);
        long tableNativePtr = table.getNativePtr();
        OptionColumnInfo columnInfo = (OptionColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Option.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.geogreenapps.apps.indukaan.classes.Option object = null;
        while (objects.hasNext()) {
            object = (com.geogreenapps.apps.indukaan.classes.Option) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((OptionRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((OptionRealmProxyInterface) object).realmGet$id());
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((OptionRealmProxyInterface) object).realmGet$id());
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.product_idIndex, rowIndex, ((OptionRealmProxyInterface) object).realmGet$product_id(), false);
            String realmGet$label = ((OptionRealmProxyInterface) object).realmGet$label();
            if (realmGet$label != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.labelIndex, rowIndex, realmGet$label, false);
            }
            Double realmGet$value = ((OptionRealmProxyInterface) object).realmGet$value();
            if (realmGet$value != null) {
                Table.nativeSetDouble(tableNativePtr, columnInfo.valueIndex, rowIndex, realmGet$value, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.parent_idIndex, rowIndex, ((OptionRealmProxyInterface) object).realmGet$parent_id(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo._orderIndex, rowIndex, ((OptionRealmProxyInterface) object).realmGet$_order(), false);
            String realmGet$option_type = ((OptionRealmProxyInterface) object).realmGet$option_type();
            if (realmGet$option_type != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.option_typeIndex, rowIndex, realmGet$option_type, false);
            }
            String realmGet$updated_at = ((OptionRealmProxyInterface) object).realmGet$updated_at();
            if (realmGet$updated_at != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.updated_atIndex, rowIndex, realmGet$updated_at, false);
            }
            String realmGet$created_at = ((OptionRealmProxyInterface) object).realmGet$created_at();
            if (realmGet$created_at != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.created_atIndex, rowIndex, realmGet$created_at, false);
            }
            String realmGet$parsed_value = ((OptionRealmProxyInterface) object).realmGet$parsed_value();
            if (realmGet$parsed_value != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.parsed_valueIndex, rowIndex, realmGet$parsed_value, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.geogreenapps.apps.indukaan.classes.Option object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Option.class);
        long tableNativePtr = table.getNativePtr();
        OptionColumnInfo columnInfo = (OptionColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Option.class);
        long pkColumnIndex = columnInfo.idIndex;
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((OptionRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((OptionRealmProxyInterface) object).realmGet$id());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((OptionRealmProxyInterface) object).realmGet$id());
        }
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.product_idIndex, rowIndex, ((OptionRealmProxyInterface) object).realmGet$product_id(), false);
        String realmGet$label = ((OptionRealmProxyInterface) object).realmGet$label();
        if (realmGet$label != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.labelIndex, rowIndex, realmGet$label, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.labelIndex, rowIndex, false);
        }
        Double realmGet$value = ((OptionRealmProxyInterface) object).realmGet$value();
        if (realmGet$value != null) {
            Table.nativeSetDouble(tableNativePtr, columnInfo.valueIndex, rowIndex, realmGet$value, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.valueIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.parent_idIndex, rowIndex, ((OptionRealmProxyInterface) object).realmGet$parent_id(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo._orderIndex, rowIndex, ((OptionRealmProxyInterface) object).realmGet$_order(), false);
        String realmGet$option_type = ((OptionRealmProxyInterface) object).realmGet$option_type();
        if (realmGet$option_type != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.option_typeIndex, rowIndex, realmGet$option_type, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.option_typeIndex, rowIndex, false);
        }
        String realmGet$updated_at = ((OptionRealmProxyInterface) object).realmGet$updated_at();
        if (realmGet$updated_at != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.updated_atIndex, rowIndex, realmGet$updated_at, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.updated_atIndex, rowIndex, false);
        }
        String realmGet$created_at = ((OptionRealmProxyInterface) object).realmGet$created_at();
        if (realmGet$created_at != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.created_atIndex, rowIndex, realmGet$created_at, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.created_atIndex, rowIndex, false);
        }
        String realmGet$parsed_value = ((OptionRealmProxyInterface) object).realmGet$parsed_value();
        if (realmGet$parsed_value != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.parsed_valueIndex, rowIndex, realmGet$parsed_value, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.parsed_valueIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Option.class);
        long tableNativePtr = table.getNativePtr();
        OptionColumnInfo columnInfo = (OptionColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Option.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.geogreenapps.apps.indukaan.classes.Option object = null;
        while (objects.hasNext()) {
            object = (com.geogreenapps.apps.indukaan.classes.Option) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((OptionRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((OptionRealmProxyInterface) object).realmGet$id());
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((OptionRealmProxyInterface) object).realmGet$id());
            }
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.product_idIndex, rowIndex, ((OptionRealmProxyInterface) object).realmGet$product_id(), false);
            String realmGet$label = ((OptionRealmProxyInterface) object).realmGet$label();
            if (realmGet$label != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.labelIndex, rowIndex, realmGet$label, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.labelIndex, rowIndex, false);
            }
            Double realmGet$value = ((OptionRealmProxyInterface) object).realmGet$value();
            if (realmGet$value != null) {
                Table.nativeSetDouble(tableNativePtr, columnInfo.valueIndex, rowIndex, realmGet$value, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.valueIndex, rowIndex, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.parent_idIndex, rowIndex, ((OptionRealmProxyInterface) object).realmGet$parent_id(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo._orderIndex, rowIndex, ((OptionRealmProxyInterface) object).realmGet$_order(), false);
            String realmGet$option_type = ((OptionRealmProxyInterface) object).realmGet$option_type();
            if (realmGet$option_type != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.option_typeIndex, rowIndex, realmGet$option_type, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.option_typeIndex, rowIndex, false);
            }
            String realmGet$updated_at = ((OptionRealmProxyInterface) object).realmGet$updated_at();
            if (realmGet$updated_at != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.updated_atIndex, rowIndex, realmGet$updated_at, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.updated_atIndex, rowIndex, false);
            }
            String realmGet$created_at = ((OptionRealmProxyInterface) object).realmGet$created_at();
            if (realmGet$created_at != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.created_atIndex, rowIndex, realmGet$created_at, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.created_atIndex, rowIndex, false);
            }
            String realmGet$parsed_value = ((OptionRealmProxyInterface) object).realmGet$parsed_value();
            if (realmGet$parsed_value != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.parsed_valueIndex, rowIndex, realmGet$parsed_value, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.parsed_valueIndex, rowIndex, false);
            }
        }
    }

    public static com.geogreenapps.apps.indukaan.classes.Option createDetachedCopy(com.geogreenapps.apps.indukaan.classes.Option realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.geogreenapps.apps.indukaan.classes.Option unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.geogreenapps.apps.indukaan.classes.Option();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.geogreenapps.apps.indukaan.classes.Option) cachedObject.object;
            }
            unmanagedObject = (com.geogreenapps.apps.indukaan.classes.Option) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        OptionRealmProxyInterface unmanagedCopy = (OptionRealmProxyInterface) unmanagedObject;
        OptionRealmProxyInterface realmSource = (OptionRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$product_id(realmSource.realmGet$product_id());
        unmanagedCopy.realmSet$label(realmSource.realmGet$label());
        unmanagedCopy.realmSet$value(realmSource.realmGet$value());
        unmanagedCopy.realmSet$parent_id(realmSource.realmGet$parent_id());
        unmanagedCopy.realmSet$_order(realmSource.realmGet$_order());
        unmanagedCopy.realmSet$option_type(realmSource.realmGet$option_type());
        unmanagedCopy.realmSet$updated_at(realmSource.realmGet$updated_at());
        unmanagedCopy.realmSet$created_at(realmSource.realmGet$created_at());
        unmanagedCopy.realmSet$parsed_value(realmSource.realmGet$parsed_value());

        return unmanagedObject;
    }

    static com.geogreenapps.apps.indukaan.classes.Option update(Realm realm, com.geogreenapps.apps.indukaan.classes.Option realmObject, com.geogreenapps.apps.indukaan.classes.Option newObject, Map<RealmModel, RealmObjectProxy> cache) {
        OptionRealmProxyInterface realmObjectTarget = (OptionRealmProxyInterface) realmObject;
        OptionRealmProxyInterface realmObjectSource = (OptionRealmProxyInterface) newObject;
        realmObjectTarget.realmSet$product_id(realmObjectSource.realmGet$product_id());
        realmObjectTarget.realmSet$label(realmObjectSource.realmGet$label());
        realmObjectTarget.realmSet$value(realmObjectSource.realmGet$value());
        realmObjectTarget.realmSet$parent_id(realmObjectSource.realmGet$parent_id());
        realmObjectTarget.realmSet$_order(realmObjectSource.realmGet$_order());
        realmObjectTarget.realmSet$option_type(realmObjectSource.realmGet$option_type());
        realmObjectTarget.realmSet$updated_at(realmObjectSource.realmGet$updated_at());
        realmObjectTarget.realmSet$created_at(realmObjectSource.realmGet$created_at());
        realmObjectTarget.realmSet$parsed_value(realmObjectSource.realmGet$parsed_value());
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Option = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{product_id:");
        stringBuilder.append(realmGet$product_id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{label:");
        stringBuilder.append(realmGet$label() != null ? realmGet$label() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{value:");
        stringBuilder.append(realmGet$value() != null ? realmGet$value() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{parent_id:");
        stringBuilder.append(realmGet$parent_id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{_order:");
        stringBuilder.append(realmGet$_order());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{option_type:");
        stringBuilder.append(realmGet$option_type() != null ? realmGet$option_type() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{updated_at:");
        stringBuilder.append(realmGet$updated_at() != null ? realmGet$updated_at() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{created_at:");
        stringBuilder.append(realmGet$created_at() != null ? realmGet$created_at() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{parsed_value:");
        stringBuilder.append(realmGet$parsed_value() != null ? realmGet$parsed_value() : "null");
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
        OptionRealmProxy aOption = (OptionRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aOption.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aOption.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aOption.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
