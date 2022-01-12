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
public class PaymentGatewayRealmProxy extends com.geogreenapps.apps.indukaan.classes.PaymentGateway
    implements RealmObjectProxy, PaymentGatewayRealmProxyInterface {

    static final class PaymentGatewayColumnInfo extends ColumnInfo {
        long idIndex;
        long paymentIndex;
        long imageIndex;
        long descriptionIndex;
        long feesIndex;

        PaymentGatewayColumnInfo(OsSchemaInfo schemaInfo) {
            super(5);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("PaymentGateway");
            this.idIndex = addColumnDetails("id", objectSchemaInfo);
            this.paymentIndex = addColumnDetails("payment", objectSchemaInfo);
            this.imageIndex = addColumnDetails("image", objectSchemaInfo);
            this.descriptionIndex = addColumnDetails("description", objectSchemaInfo);
            this.feesIndex = addColumnDetails("fees", objectSchemaInfo);
        }

        PaymentGatewayColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new PaymentGatewayColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final PaymentGatewayColumnInfo src = (PaymentGatewayColumnInfo) rawSrc;
            final PaymentGatewayColumnInfo dst = (PaymentGatewayColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.paymentIndex = src.paymentIndex;
            dst.imageIndex = src.imageIndex;
            dst.descriptionIndex = src.descriptionIndex;
            dst.feesIndex = src.feesIndex;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>(5);
        fieldNames.add("id");
        fieldNames.add("payment");
        fieldNames.add("image");
        fieldNames.add("description");
        fieldNames.add("fees");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    private PaymentGatewayColumnInfo columnInfo;
    private ProxyState<com.geogreenapps.apps.indukaan.classes.PaymentGateway> proxyState;
    private RealmList<com.geogreenapps.apps.indukaan.classes.Fee> feesRealmList;

    PaymentGatewayRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (PaymentGatewayColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.geogreenapps.apps.indukaan.classes.PaymentGateway>(this);
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
    public String realmGet$payment() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.paymentIndex);
    }

    @Override
    public void realmSet$payment(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.paymentIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.paymentIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.paymentIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.paymentIndex, value);
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
    public RealmList<com.geogreenapps.apps.indukaan.classes.Fee> realmGet$fees() {
        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (feesRealmList != null) {
            return feesRealmList;
        } else {
            OsList osList = proxyState.getRow$realm().getModelList(columnInfo.feesIndex);
            feesRealmList = new RealmList<com.geogreenapps.apps.indukaan.classes.Fee>(com.geogreenapps.apps.indukaan.classes.Fee.class, osList, proxyState.getRealm$realm());
            return feesRealmList;
        }
    }

    @Override
    public void realmSet$fees(RealmList<com.geogreenapps.apps.indukaan.classes.Fee> value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("fees")) {
                return;
            }
            // if the list contains unmanaged RealmObjects, convert them to managed.
            if (value != null && !value.isManaged()) {
                final Realm realm = (Realm) proxyState.getRealm$realm();
                final RealmList<com.geogreenapps.apps.indukaan.classes.Fee> original = value;
                value = new RealmList<com.geogreenapps.apps.indukaan.classes.Fee>();
                for (com.geogreenapps.apps.indukaan.classes.Fee item : original) {
                    if (item == null || RealmObject.isManaged(item)) {
                        value.add(item);
                    } else {
                        value.add(realm.copyToRealm(item));
                    }
                }
            }
        }

        proxyState.getRealm$realm().checkIfValid();
        OsList osList = proxyState.getRow$realm().getModelList(columnInfo.feesIndex);
        // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
        if (value != null && value.size() == osList.size()) {
            int objects = value.size();
            for (int i = 0; i < objects; i++) {
                com.geogreenapps.apps.indukaan.classes.Fee linkedObject = value.get(i);
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
                com.geogreenapps.apps.indukaan.classes.Fee linkedObject = value.get(i);
                proxyState.checkValidObject(linkedObject);
                osList.addRow(((RealmObjectProxy) linkedObject).realmGet$proxyState().getRow$realm().getIndex());
            }
        }
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("PaymentGateway", 5, 0);
        builder.addPersistedProperty("id", RealmFieldType.INTEGER, Property.PRIMARY_KEY, Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("payment", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("image", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("description", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedLinkProperty("fees", RealmFieldType.LIST, "Fee");
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static PaymentGatewayColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new PaymentGatewayColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "PaymentGateway";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.geogreenapps.apps.indukaan.classes.PaymentGateway createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = new ArrayList<String>(1);
        com.geogreenapps.apps.indukaan.classes.PaymentGateway obj = null;
        if (update) {
            Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.PaymentGateway.class);
            PaymentGatewayColumnInfo columnInfo = (PaymentGatewayColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.PaymentGateway.class);
            long pkColumnIndex = columnInfo.idIndex;
            long rowIndex = Table.NO_MATCH;
            if (!json.isNull("id")) {
                rowIndex = table.findFirstLong(pkColumnIndex, json.getLong("id"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.PaymentGateway.class), false, Collections.<String> emptyList());
                    obj = new io.realm.PaymentGatewayRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("fees")) {
                excludeFields.add("fees");
            }
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.PaymentGatewayRealmProxy) realm.createObjectInternal(com.geogreenapps.apps.indukaan.classes.PaymentGateway.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.PaymentGatewayRealmProxy) realm.createObjectInternal(com.geogreenapps.apps.indukaan.classes.PaymentGateway.class, json.getInt("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }

        final PaymentGatewayRealmProxyInterface objProxy = (PaymentGatewayRealmProxyInterface) obj;
        if (json.has("payment")) {
            if (json.isNull("payment")) {
                objProxy.realmSet$payment(null);
            } else {
                objProxy.realmSet$payment((String) json.getString("payment"));
            }
        }
        if (json.has("image")) {
            if (json.isNull("image")) {
                objProxy.realmSet$image(null);
            } else {
                objProxy.realmSet$image((String) json.getString("image"));
            }
        }
        if (json.has("description")) {
            if (json.isNull("description")) {
                objProxy.realmSet$description(null);
            } else {
                objProxy.realmSet$description((String) json.getString("description"));
            }
        }
        if (json.has("fees")) {
            if (json.isNull("fees")) {
                objProxy.realmSet$fees(null);
            } else {
                objProxy.realmGet$fees().clear();
                JSONArray array = json.getJSONArray("fees");
                for (int i = 0; i < array.length(); i++) {
                    com.geogreenapps.apps.indukaan.classes.Fee item = FeeRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    objProxy.realmGet$fees().add(item);
                }
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.geogreenapps.apps.indukaan.classes.PaymentGateway createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.geogreenapps.apps.indukaan.classes.PaymentGateway obj = new com.geogreenapps.apps.indukaan.classes.PaymentGateway();
        final PaymentGatewayRealmProxyInterface objProxy = (PaymentGatewayRealmProxyInterface) obj;
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
            } else if (name.equals("payment")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$payment((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$payment(null);
                }
            } else if (name.equals("image")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$image((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$image(null);
                }
            } else if (name.equals("description")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$description((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$description(null);
                }
            } else if (name.equals("fees")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$fees(null);
                } else {
                    objProxy.realmSet$fees(new RealmList<com.geogreenapps.apps.indukaan.classes.Fee>());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.geogreenapps.apps.indukaan.classes.Fee item = FeeRealmProxy.createUsingJsonStream(realm, reader);
                        objProxy.realmGet$fees().add(item);
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

    public static com.geogreenapps.apps.indukaan.classes.PaymentGateway copyOrUpdate(Realm realm, com.geogreenapps.apps.indukaan.classes.PaymentGateway object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
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
            return (com.geogreenapps.apps.indukaan.classes.PaymentGateway) cachedRealmObject;
        }

        com.geogreenapps.apps.indukaan.classes.PaymentGateway realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.PaymentGateway.class);
            PaymentGatewayColumnInfo columnInfo = (PaymentGatewayColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.PaymentGateway.class);
            long pkColumnIndex = columnInfo.idIndex;
            long rowIndex = table.findFirstLong(pkColumnIndex, ((PaymentGatewayRealmProxyInterface) object).realmGet$id());
            if (rowIndex == Table.NO_MATCH) {
                canUpdate = false;
            } else {
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.PaymentGateway.class), false, Collections.<String> emptyList());
                    realmObject = new io.realm.PaymentGatewayRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, realmObject, object, cache) : copy(realm, object, update, cache);
    }

    public static com.geogreenapps.apps.indukaan.classes.PaymentGateway copy(Realm realm, com.geogreenapps.apps.indukaan.classes.PaymentGateway newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.geogreenapps.apps.indukaan.classes.PaymentGateway) cachedRealmObject;
        }

        // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
        com.geogreenapps.apps.indukaan.classes.PaymentGateway realmObject = realm.createObjectInternal(com.geogreenapps.apps.indukaan.classes.PaymentGateway.class, ((PaymentGatewayRealmProxyInterface) newObject).realmGet$id(), false, Collections.<String>emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);

        PaymentGatewayRealmProxyInterface realmObjectSource = (PaymentGatewayRealmProxyInterface) newObject;
        PaymentGatewayRealmProxyInterface realmObjectCopy = (PaymentGatewayRealmProxyInterface) realmObject;

        realmObjectCopy.realmSet$payment(realmObjectSource.realmGet$payment());
        realmObjectCopy.realmSet$image(realmObjectSource.realmGet$image());
        realmObjectCopy.realmSet$description(realmObjectSource.realmGet$description());

        RealmList<com.geogreenapps.apps.indukaan.classes.Fee> feesList = realmObjectSource.realmGet$fees();
        if (feesList != null) {
            RealmList<com.geogreenapps.apps.indukaan.classes.Fee> feesRealmList = realmObjectCopy.realmGet$fees();
            feesRealmList.clear();
            for (int i = 0; i < feesList.size(); i++) {
                com.geogreenapps.apps.indukaan.classes.Fee feesItem = feesList.get(i);
                com.geogreenapps.apps.indukaan.classes.Fee cachefees = (com.geogreenapps.apps.indukaan.classes.Fee) cache.get(feesItem);
                if (cachefees != null) {
                    feesRealmList.add(cachefees);
                } else {
                    feesRealmList.add(FeeRealmProxy.copyOrUpdate(realm, feesItem, update, cache));
                }
            }
        }

        return realmObject;
    }

    public static long insert(Realm realm, com.geogreenapps.apps.indukaan.classes.PaymentGateway object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.PaymentGateway.class);
        long tableNativePtr = table.getNativePtr();
        PaymentGatewayColumnInfo columnInfo = (PaymentGatewayColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.PaymentGateway.class);
        long pkColumnIndex = columnInfo.idIndex;
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((PaymentGatewayRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((PaymentGatewayRealmProxyInterface) object).realmGet$id());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((PaymentGatewayRealmProxyInterface) object).realmGet$id());
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$payment = ((PaymentGatewayRealmProxyInterface) object).realmGet$payment();
        if (realmGet$payment != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.paymentIndex, rowIndex, realmGet$payment, false);
        }
        String realmGet$image = ((PaymentGatewayRealmProxyInterface) object).realmGet$image();
        if (realmGet$image != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.imageIndex, rowIndex, realmGet$image, false);
        }
        String realmGet$description = ((PaymentGatewayRealmProxyInterface) object).realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.descriptionIndex, rowIndex, realmGet$description, false);
        }

        RealmList<com.geogreenapps.apps.indukaan.classes.Fee> feesList = ((PaymentGatewayRealmProxyInterface) object).realmGet$fees();
        if (feesList != null) {
            OsList feesOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.feesIndex);
            for (com.geogreenapps.apps.indukaan.classes.Fee feesItem : feesList) {
                Long cacheItemIndexfees = cache.get(feesItem);
                if (cacheItemIndexfees == null) {
                    cacheItemIndexfees = FeeRealmProxy.insert(realm, feesItem, cache);
                }
                feesOsList.addRow(cacheItemIndexfees);
            }
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.PaymentGateway.class);
        long tableNativePtr = table.getNativePtr();
        PaymentGatewayColumnInfo columnInfo = (PaymentGatewayColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.PaymentGateway.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.geogreenapps.apps.indukaan.classes.PaymentGateway object = null;
        while (objects.hasNext()) {
            object = (com.geogreenapps.apps.indukaan.classes.PaymentGateway) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((PaymentGatewayRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((PaymentGatewayRealmProxyInterface) object).realmGet$id());
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((PaymentGatewayRealmProxyInterface) object).realmGet$id());
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, rowIndex);
            String realmGet$payment = ((PaymentGatewayRealmProxyInterface) object).realmGet$payment();
            if (realmGet$payment != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.paymentIndex, rowIndex, realmGet$payment, false);
            }
            String realmGet$image = ((PaymentGatewayRealmProxyInterface) object).realmGet$image();
            if (realmGet$image != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.imageIndex, rowIndex, realmGet$image, false);
            }
            String realmGet$description = ((PaymentGatewayRealmProxyInterface) object).realmGet$description();
            if (realmGet$description != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.descriptionIndex, rowIndex, realmGet$description, false);
            }

            RealmList<com.geogreenapps.apps.indukaan.classes.Fee> feesList = ((PaymentGatewayRealmProxyInterface) object).realmGet$fees();
            if (feesList != null) {
                OsList feesOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.feesIndex);
                for (com.geogreenapps.apps.indukaan.classes.Fee feesItem : feesList) {
                    Long cacheItemIndexfees = cache.get(feesItem);
                    if (cacheItemIndexfees == null) {
                        cacheItemIndexfees = FeeRealmProxy.insert(realm, feesItem, cache);
                    }
                    feesOsList.addRow(cacheItemIndexfees);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.geogreenapps.apps.indukaan.classes.PaymentGateway object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.PaymentGateway.class);
        long tableNativePtr = table.getNativePtr();
        PaymentGatewayColumnInfo columnInfo = (PaymentGatewayColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.PaymentGateway.class);
        long pkColumnIndex = columnInfo.idIndex;
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((PaymentGatewayRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((PaymentGatewayRealmProxyInterface) object).realmGet$id());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((PaymentGatewayRealmProxyInterface) object).realmGet$id());
        }
        cache.put(object, rowIndex);
        String realmGet$payment = ((PaymentGatewayRealmProxyInterface) object).realmGet$payment();
        if (realmGet$payment != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.paymentIndex, rowIndex, realmGet$payment, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.paymentIndex, rowIndex, false);
        }
        String realmGet$image = ((PaymentGatewayRealmProxyInterface) object).realmGet$image();
        if (realmGet$image != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.imageIndex, rowIndex, realmGet$image, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.imageIndex, rowIndex, false);
        }
        String realmGet$description = ((PaymentGatewayRealmProxyInterface) object).realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.descriptionIndex, rowIndex, realmGet$description, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.descriptionIndex, rowIndex, false);
        }

        OsList feesOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.feesIndex);
        RealmList<com.geogreenapps.apps.indukaan.classes.Fee> feesList = ((PaymentGatewayRealmProxyInterface) object).realmGet$fees();
        if (feesList != null && feesList.size() == feesOsList.size()) {
            // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
            int objects = feesList.size();
            for (int i = 0; i < objects; i++) {
                com.geogreenapps.apps.indukaan.classes.Fee feesItem = feesList.get(i);
                Long cacheItemIndexfees = cache.get(feesItem);
                if (cacheItemIndexfees == null) {
                    cacheItemIndexfees = FeeRealmProxy.insertOrUpdate(realm, feesItem, cache);
                }
                feesOsList.setRow(i, cacheItemIndexfees);
            }
        } else {
            feesOsList.removeAll();
            if (feesList != null) {
                for (com.geogreenapps.apps.indukaan.classes.Fee feesItem : feesList) {
                    Long cacheItemIndexfees = cache.get(feesItem);
                    if (cacheItemIndexfees == null) {
                        cacheItemIndexfees = FeeRealmProxy.insertOrUpdate(realm, feesItem, cache);
                    }
                    feesOsList.addRow(cacheItemIndexfees);
                }
            }
        }

        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.PaymentGateway.class);
        long tableNativePtr = table.getNativePtr();
        PaymentGatewayColumnInfo columnInfo = (PaymentGatewayColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.PaymentGateway.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.geogreenapps.apps.indukaan.classes.PaymentGateway object = null;
        while (objects.hasNext()) {
            object = (com.geogreenapps.apps.indukaan.classes.PaymentGateway) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((PaymentGatewayRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((PaymentGatewayRealmProxyInterface) object).realmGet$id());
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((PaymentGatewayRealmProxyInterface) object).realmGet$id());
            }
            cache.put(object, rowIndex);
            String realmGet$payment = ((PaymentGatewayRealmProxyInterface) object).realmGet$payment();
            if (realmGet$payment != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.paymentIndex, rowIndex, realmGet$payment, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.paymentIndex, rowIndex, false);
            }
            String realmGet$image = ((PaymentGatewayRealmProxyInterface) object).realmGet$image();
            if (realmGet$image != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.imageIndex, rowIndex, realmGet$image, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.imageIndex, rowIndex, false);
            }
            String realmGet$description = ((PaymentGatewayRealmProxyInterface) object).realmGet$description();
            if (realmGet$description != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.descriptionIndex, rowIndex, realmGet$description, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.descriptionIndex, rowIndex, false);
            }

            OsList feesOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.feesIndex);
            RealmList<com.geogreenapps.apps.indukaan.classes.Fee> feesList = ((PaymentGatewayRealmProxyInterface) object).realmGet$fees();
            if (feesList != null && feesList.size() == feesOsList.size()) {
                // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
                int objectCount = feesList.size();
                for (int i = 0; i < objectCount; i++) {
                    com.geogreenapps.apps.indukaan.classes.Fee feesItem = feesList.get(i);
                    Long cacheItemIndexfees = cache.get(feesItem);
                    if (cacheItemIndexfees == null) {
                        cacheItemIndexfees = FeeRealmProxy.insertOrUpdate(realm, feesItem, cache);
                    }
                    feesOsList.setRow(i, cacheItemIndexfees);
                }
            } else {
                feesOsList.removeAll();
                if (feesList != null) {
                    for (com.geogreenapps.apps.indukaan.classes.Fee feesItem : feesList) {
                        Long cacheItemIndexfees = cache.get(feesItem);
                        if (cacheItemIndexfees == null) {
                            cacheItemIndexfees = FeeRealmProxy.insertOrUpdate(realm, feesItem, cache);
                        }
                        feesOsList.addRow(cacheItemIndexfees);
                    }
                }
            }

        }
    }

    public static com.geogreenapps.apps.indukaan.classes.PaymentGateway createDetachedCopy(com.geogreenapps.apps.indukaan.classes.PaymentGateway realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.geogreenapps.apps.indukaan.classes.PaymentGateway unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.geogreenapps.apps.indukaan.classes.PaymentGateway();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.geogreenapps.apps.indukaan.classes.PaymentGateway) cachedObject.object;
            }
            unmanagedObject = (com.geogreenapps.apps.indukaan.classes.PaymentGateway) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        PaymentGatewayRealmProxyInterface unmanagedCopy = (PaymentGatewayRealmProxyInterface) unmanagedObject;
        PaymentGatewayRealmProxyInterface realmSource = (PaymentGatewayRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$payment(realmSource.realmGet$payment());
        unmanagedCopy.realmSet$image(realmSource.realmGet$image());
        unmanagedCopy.realmSet$description(realmSource.realmGet$description());

        // Deep copy of fees
        if (currentDepth == maxDepth) {
            unmanagedCopy.realmSet$fees(null);
        } else {
            RealmList<com.geogreenapps.apps.indukaan.classes.Fee> managedfeesList = realmSource.realmGet$fees();
            RealmList<com.geogreenapps.apps.indukaan.classes.Fee> unmanagedfeesList = new RealmList<com.geogreenapps.apps.indukaan.classes.Fee>();
            unmanagedCopy.realmSet$fees(unmanagedfeesList);
            int nextDepth = currentDepth + 1;
            int size = managedfeesList.size();
            for (int i = 0; i < size; i++) {
                com.geogreenapps.apps.indukaan.classes.Fee item = FeeRealmProxy.createDetachedCopy(managedfeesList.get(i), nextDepth, maxDepth, cache);
                unmanagedfeesList.add(item);
            }
        }

        return unmanagedObject;
    }

    static com.geogreenapps.apps.indukaan.classes.PaymentGateway update(Realm realm, com.geogreenapps.apps.indukaan.classes.PaymentGateway realmObject, com.geogreenapps.apps.indukaan.classes.PaymentGateway newObject, Map<RealmModel, RealmObjectProxy> cache) {
        PaymentGatewayRealmProxyInterface realmObjectTarget = (PaymentGatewayRealmProxyInterface) realmObject;
        PaymentGatewayRealmProxyInterface realmObjectSource = (PaymentGatewayRealmProxyInterface) newObject;
        realmObjectTarget.realmSet$payment(realmObjectSource.realmGet$payment());
        realmObjectTarget.realmSet$image(realmObjectSource.realmGet$image());
        realmObjectTarget.realmSet$description(realmObjectSource.realmGet$description());
        RealmList<com.geogreenapps.apps.indukaan.classes.Fee> feesList = realmObjectSource.realmGet$fees();
        RealmList<com.geogreenapps.apps.indukaan.classes.Fee> feesRealmList = realmObjectTarget.realmGet$fees();
        if (feesList != null && feesList.size() == feesRealmList.size()) {
            // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
            int objects = feesList.size();
            for (int i = 0; i < objects; i++) {
                com.geogreenapps.apps.indukaan.classes.Fee feesItem = feesList.get(i);
                com.geogreenapps.apps.indukaan.classes.Fee cachefees = (com.geogreenapps.apps.indukaan.classes.Fee) cache.get(feesItem);
                if (cachefees != null) {
                    feesRealmList.set(i, cachefees);
                } else {
                    feesRealmList.set(i, FeeRealmProxy.copyOrUpdate(realm, feesItem, true, cache));
                }
            }
        } else {
            feesRealmList.clear();
            if (feesList != null) {
                for (int i = 0; i < feesList.size(); i++) {
                    com.geogreenapps.apps.indukaan.classes.Fee feesItem = feesList.get(i);
                    com.geogreenapps.apps.indukaan.classes.Fee cachefees = (com.geogreenapps.apps.indukaan.classes.Fee) cache.get(feesItem);
                    if (cachefees != null) {
                        feesRealmList.add(cachefees);
                    } else {
                        feesRealmList.add(FeeRealmProxy.copyOrUpdate(realm, feesItem, true, cache));
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
        StringBuilder stringBuilder = new StringBuilder("PaymentGateway = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{payment:");
        stringBuilder.append(realmGet$payment() != null ? realmGet$payment() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{image:");
        stringBuilder.append(realmGet$image() != null ? realmGet$image() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{description:");
        stringBuilder.append(realmGet$description() != null ? realmGet$description() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{fees:");
        stringBuilder.append("RealmList<Fee>[").append(realmGet$fees().size()).append("]");
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
        PaymentGatewayRealmProxy aPaymentGateway = (PaymentGatewayRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aPaymentGateway.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aPaymentGateway.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aPaymentGateway.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
