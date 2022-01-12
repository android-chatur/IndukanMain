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
public class CartRealmProxy extends com.geogreenapps.apps.indukaan.classes.Cart
    implements RealmObjectProxy, CartRealmProxyInterface {

    static final class CartColumnInfo extends ColumnInfo {
        long idIndex;
        long moduleIndex;
        long module_idIndex;
        long amountIndex;
        long productIndex;
        long offerIndex;
        long variantsIndex;
        long qteIndex;
        long statusIndex;
        long user_idIndex;
        long parent_idIndex;

        CartColumnInfo(OsSchemaInfo schemaInfo) {
            super(11);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Cart");
            this.idIndex = addColumnDetails("id", objectSchemaInfo);
            this.moduleIndex = addColumnDetails("module", objectSchemaInfo);
            this.module_idIndex = addColumnDetails("module_id", objectSchemaInfo);
            this.amountIndex = addColumnDetails("amount", objectSchemaInfo);
            this.productIndex = addColumnDetails("product", objectSchemaInfo);
            this.offerIndex = addColumnDetails("offer", objectSchemaInfo);
            this.variantsIndex = addColumnDetails("variants", objectSchemaInfo);
            this.qteIndex = addColumnDetails("qte", objectSchemaInfo);
            this.statusIndex = addColumnDetails("status", objectSchemaInfo);
            this.user_idIndex = addColumnDetails("user_id", objectSchemaInfo);
            this.parent_idIndex = addColumnDetails("parent_id", objectSchemaInfo);
        }

        CartColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new CartColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final CartColumnInfo src = (CartColumnInfo) rawSrc;
            final CartColumnInfo dst = (CartColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.moduleIndex = src.moduleIndex;
            dst.module_idIndex = src.module_idIndex;
            dst.amountIndex = src.amountIndex;
            dst.productIndex = src.productIndex;
            dst.offerIndex = src.offerIndex;
            dst.variantsIndex = src.variantsIndex;
            dst.qteIndex = src.qteIndex;
            dst.statusIndex = src.statusIndex;
            dst.user_idIndex = src.user_idIndex;
            dst.parent_idIndex = src.parent_idIndex;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>(11);
        fieldNames.add("id");
        fieldNames.add("module");
        fieldNames.add("module_id");
        fieldNames.add("amount");
        fieldNames.add("product");
        fieldNames.add("offer");
        fieldNames.add("variants");
        fieldNames.add("qte");
        fieldNames.add("status");
        fieldNames.add("user_id");
        fieldNames.add("parent_id");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    private CartColumnInfo columnInfo;
    private ProxyState<com.geogreenapps.apps.indukaan.classes.Cart> proxyState;
    private RealmList<com.geogreenapps.apps.indukaan.classes.Variant> variantsRealmList;

    CartRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (CartColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.geogreenapps.apps.indukaan.classes.Cart>(this);
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
    public int realmGet$module_id() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.module_idIndex);
    }

    @Override
    public void realmSet$module_id(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.module_idIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.module_idIndex, value);
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
    public com.geogreenapps.apps.indukaan.classes.Product realmGet$product() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNullLink(columnInfo.productIndex)) {
            return null;
        }
        return proxyState.getRealm$realm().get(com.geogreenapps.apps.indukaan.classes.Product.class, proxyState.getRow$realm().getLink(columnInfo.productIndex), false, Collections.<String>emptyList());
    }

    @Override
    public void realmSet$product(com.geogreenapps.apps.indukaan.classes.Product value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("product")) {
                return;
            }
            if (value != null && !RealmObject.isManaged(value)) {
                value = ((Realm) proxyState.getRealm$realm()).copyToRealm(value);
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                // Table#nullifyLink() does not support default value. Just using Row.
                row.nullifyLink(columnInfo.productIndex);
                return;
            }
            proxyState.checkValidObject(value);
            row.getTable().setLink(columnInfo.productIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().nullifyLink(columnInfo.productIndex);
            return;
        }
        proxyState.checkValidObject(value);
        proxyState.getRow$realm().setLink(columnInfo.productIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
    }

    @Override
    public com.geogreenapps.apps.indukaan.classes.Offer realmGet$offer() {
        proxyState.getRealm$realm().checkIfValid();
        if (proxyState.getRow$realm().isNullLink(columnInfo.offerIndex)) {
            return null;
        }
        return proxyState.getRealm$realm().get(com.geogreenapps.apps.indukaan.classes.Offer.class, proxyState.getRow$realm().getLink(columnInfo.offerIndex), false, Collections.<String>emptyList());
    }

    @Override
    public void realmSet$offer(com.geogreenapps.apps.indukaan.classes.Offer value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("offer")) {
                return;
            }
            if (value != null && !RealmObject.isManaged(value)) {
                value = ((Realm) proxyState.getRealm$realm()).copyToRealm(value);
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                // Table#nullifyLink() does not support default value. Just using Row.
                row.nullifyLink(columnInfo.offerIndex);
                return;
            }
            proxyState.checkValidObject(value);
            row.getTable().setLink(columnInfo.offerIndex, row.getIndex(), ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex(), true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().nullifyLink(columnInfo.offerIndex);
            return;
        }
        proxyState.checkValidObject(value);
        proxyState.getRow$realm().setLink(columnInfo.offerIndex, ((RealmObjectProxy) value).realmGet$proxyState().getRow$realm().getIndex());
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

    @Override
    @SuppressWarnings("cast")
    public int realmGet$qte() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.qteIndex);
    }

    @Override
    public void realmSet$qte(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.qteIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.qteIndex, value);
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

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("Cart", 11, 0);
        builder.addPersistedProperty("id", RealmFieldType.INTEGER, Property.PRIMARY_KEY, Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("module", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("module_id", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("amount", RealmFieldType.DOUBLE, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedLinkProperty("product", RealmFieldType.OBJECT, "Product");
        builder.addPersistedLinkProperty("offer", RealmFieldType.OBJECT, "Offer");
        builder.addPersistedLinkProperty("variants", RealmFieldType.LIST, "Variant");
        builder.addPersistedProperty("qte", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("status", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("user_id", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("parent_id", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static CartColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new CartColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "Cart";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.geogreenapps.apps.indukaan.classes.Cart createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = new ArrayList<String>(3);
        com.geogreenapps.apps.indukaan.classes.Cart obj = null;
        if (update) {
            Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Cart.class);
            CartColumnInfo columnInfo = (CartColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Cart.class);
            long pkColumnIndex = columnInfo.idIndex;
            long rowIndex = Table.NO_MATCH;
            if (!json.isNull("id")) {
                rowIndex = table.findFirstLong(pkColumnIndex, json.getLong("id"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Cart.class), false, Collections.<String> emptyList());
                    obj = new io.realm.CartRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("product")) {
                excludeFields.add("product");
            }
            if (json.has("offer")) {
                excludeFields.add("offer");
            }
            if (json.has("variants")) {
                excludeFields.add("variants");
            }
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.CartRealmProxy) realm.createObjectInternal(com.geogreenapps.apps.indukaan.classes.Cart.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.CartRealmProxy) realm.createObjectInternal(com.geogreenapps.apps.indukaan.classes.Cart.class, json.getInt("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }

        final CartRealmProxyInterface objProxy = (CartRealmProxyInterface) obj;
        if (json.has("module")) {
            if (json.isNull("module")) {
                objProxy.realmSet$module(null);
            } else {
                objProxy.realmSet$module((String) json.getString("module"));
            }
        }
        if (json.has("module_id")) {
            if (json.isNull("module_id")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'module_id' to null.");
            } else {
                objProxy.realmSet$module_id((int) json.getInt("module_id"));
            }
        }
        if (json.has("amount")) {
            if (json.isNull("amount")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'amount' to null.");
            } else {
                objProxy.realmSet$amount((double) json.getDouble("amount"));
            }
        }
        if (json.has("product")) {
            if (json.isNull("product")) {
                objProxy.realmSet$product(null);
            } else {
                com.geogreenapps.apps.indukaan.classes.Product productObj = ProductRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("product"), update);
                objProxy.realmSet$product(productObj);
            }
        }
        if (json.has("offer")) {
            if (json.isNull("offer")) {
                objProxy.realmSet$offer(null);
            } else {
                com.geogreenapps.apps.indukaan.classes.Offer offerObj = OfferRealmProxy.createOrUpdateUsingJsonObject(realm, json.getJSONObject("offer"), update);
                objProxy.realmSet$offer(offerObj);
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
        if (json.has("qte")) {
            if (json.isNull("qte")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'qte' to null.");
            } else {
                objProxy.realmSet$qte((int) json.getInt("qte"));
            }
        }
        if (json.has("status")) {
            if (json.isNull("status")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'status' to null.");
            } else {
                objProxy.realmSet$status((int) json.getInt("status"));
            }
        }
        if (json.has("user_id")) {
            if (json.isNull("user_id")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'user_id' to null.");
            } else {
                objProxy.realmSet$user_id((int) json.getInt("user_id"));
            }
        }
        if (json.has("parent_id")) {
            if (json.isNull("parent_id")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'parent_id' to null.");
            } else {
                objProxy.realmSet$parent_id((int) json.getInt("parent_id"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.geogreenapps.apps.indukaan.classes.Cart createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.geogreenapps.apps.indukaan.classes.Cart obj = new com.geogreenapps.apps.indukaan.classes.Cart();
        final CartRealmProxyInterface objProxy = (CartRealmProxyInterface) obj;
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
            } else if (name.equals("module")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$module((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$module(null);
                }
            } else if (name.equals("module_id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$module_id((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'module_id' to null.");
                }
            } else if (name.equals("amount")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$amount((double) reader.nextDouble());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'amount' to null.");
                }
            } else if (name.equals("product")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$product(null);
                } else {
                    com.geogreenapps.apps.indukaan.classes.Product productObj = ProductRealmProxy.createUsingJsonStream(realm, reader);
                    objProxy.realmSet$product(productObj);
                }
            } else if (name.equals("offer")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$offer(null);
                } else {
                    com.geogreenapps.apps.indukaan.classes.Offer offerObj = OfferRealmProxy.createUsingJsonStream(realm, reader);
                    objProxy.realmSet$offer(offerObj);
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
            } else if (name.equals("qte")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$qte((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'qte' to null.");
                }
            } else if (name.equals("status")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$status((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'status' to null.");
                }
            } else if (name.equals("user_id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$user_id((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'user_id' to null.");
                }
            } else if (name.equals("parent_id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$parent_id((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'parent_id' to null.");
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

    public static com.geogreenapps.apps.indukaan.classes.Cart copyOrUpdate(Realm realm, com.geogreenapps.apps.indukaan.classes.Cart object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
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
            return (com.geogreenapps.apps.indukaan.classes.Cart) cachedRealmObject;
        }

        com.geogreenapps.apps.indukaan.classes.Cart realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Cart.class);
            CartColumnInfo columnInfo = (CartColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Cart.class);
            long pkColumnIndex = columnInfo.idIndex;
            long rowIndex = table.findFirstLong(pkColumnIndex, ((CartRealmProxyInterface) object).realmGet$id());
            if (rowIndex == Table.NO_MATCH) {
                canUpdate = false;
            } else {
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Cart.class), false, Collections.<String> emptyList());
                    realmObject = new io.realm.CartRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, realmObject, object, cache) : copy(realm, object, update, cache);
    }

    public static com.geogreenapps.apps.indukaan.classes.Cart copy(Realm realm, com.geogreenapps.apps.indukaan.classes.Cart newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.geogreenapps.apps.indukaan.classes.Cart) cachedRealmObject;
        }

        // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
        com.geogreenapps.apps.indukaan.classes.Cart realmObject = realm.createObjectInternal(com.geogreenapps.apps.indukaan.classes.Cart.class, ((CartRealmProxyInterface) newObject).realmGet$id(), false, Collections.<String>emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);

        CartRealmProxyInterface realmObjectSource = (CartRealmProxyInterface) newObject;
        CartRealmProxyInterface realmObjectCopy = (CartRealmProxyInterface) realmObject;

        realmObjectCopy.realmSet$module(realmObjectSource.realmGet$module());
        realmObjectCopy.realmSet$module_id(realmObjectSource.realmGet$module_id());
        realmObjectCopy.realmSet$amount(realmObjectSource.realmGet$amount());

        com.geogreenapps.apps.indukaan.classes.Product productObj = realmObjectSource.realmGet$product();
        if (productObj == null) {
            realmObjectCopy.realmSet$product(null);
        } else {
            com.geogreenapps.apps.indukaan.classes.Product cacheproduct = (com.geogreenapps.apps.indukaan.classes.Product) cache.get(productObj);
            if (cacheproduct != null) {
                realmObjectCopy.realmSet$product(cacheproduct);
            } else {
                realmObjectCopy.realmSet$product(ProductRealmProxy.copyOrUpdate(realm, productObj, update, cache));
            }
        }

        com.geogreenapps.apps.indukaan.classes.Offer offerObj = realmObjectSource.realmGet$offer();
        if (offerObj == null) {
            realmObjectCopy.realmSet$offer(null);
        } else {
            com.geogreenapps.apps.indukaan.classes.Offer cacheoffer = (com.geogreenapps.apps.indukaan.classes.Offer) cache.get(offerObj);
            if (cacheoffer != null) {
                realmObjectCopy.realmSet$offer(cacheoffer);
            } else {
                realmObjectCopy.realmSet$offer(OfferRealmProxy.copyOrUpdate(realm, offerObj, update, cache));
            }
        }

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

        realmObjectCopy.realmSet$qte(realmObjectSource.realmGet$qte());
        realmObjectCopy.realmSet$status(realmObjectSource.realmGet$status());
        realmObjectCopy.realmSet$user_id(realmObjectSource.realmGet$user_id());
        realmObjectCopy.realmSet$parent_id(realmObjectSource.realmGet$parent_id());
        return realmObject;
    }

    public static long insert(Realm realm, com.geogreenapps.apps.indukaan.classes.Cart object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Cart.class);
        long tableNativePtr = table.getNativePtr();
        CartColumnInfo columnInfo = (CartColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Cart.class);
        long pkColumnIndex = columnInfo.idIndex;
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((CartRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((CartRealmProxyInterface) object).realmGet$id());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((CartRealmProxyInterface) object).realmGet$id());
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$module = ((CartRealmProxyInterface) object).realmGet$module();
        if (realmGet$module != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.moduleIndex, rowIndex, realmGet$module, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.module_idIndex, rowIndex, ((CartRealmProxyInterface) object).realmGet$module_id(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.amountIndex, rowIndex, ((CartRealmProxyInterface) object).realmGet$amount(), false);

        com.geogreenapps.apps.indukaan.classes.Product productObj = ((CartRealmProxyInterface) object).realmGet$product();
        if (productObj != null) {
            Long cacheproduct = cache.get(productObj);
            if (cacheproduct == null) {
                cacheproduct = ProductRealmProxy.insert(realm, productObj, cache);
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.productIndex, rowIndex, cacheproduct, false);
        }

        com.geogreenapps.apps.indukaan.classes.Offer offerObj = ((CartRealmProxyInterface) object).realmGet$offer();
        if (offerObj != null) {
            Long cacheoffer = cache.get(offerObj);
            if (cacheoffer == null) {
                cacheoffer = OfferRealmProxy.insert(realm, offerObj, cache);
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.offerIndex, rowIndex, cacheoffer, false);
        }

        RealmList<com.geogreenapps.apps.indukaan.classes.Variant> variantsList = ((CartRealmProxyInterface) object).realmGet$variants();
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
        Table.nativeSetLong(tableNativePtr, columnInfo.qteIndex, rowIndex, ((CartRealmProxyInterface) object).realmGet$qte(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.statusIndex, rowIndex, ((CartRealmProxyInterface) object).realmGet$status(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.user_idIndex, rowIndex, ((CartRealmProxyInterface) object).realmGet$user_id(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.parent_idIndex, rowIndex, ((CartRealmProxyInterface) object).realmGet$parent_id(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Cart.class);
        long tableNativePtr = table.getNativePtr();
        CartColumnInfo columnInfo = (CartColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Cart.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.geogreenapps.apps.indukaan.classes.Cart object = null;
        while (objects.hasNext()) {
            object = (com.geogreenapps.apps.indukaan.classes.Cart) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((CartRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((CartRealmProxyInterface) object).realmGet$id());
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((CartRealmProxyInterface) object).realmGet$id());
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, rowIndex);
            String realmGet$module = ((CartRealmProxyInterface) object).realmGet$module();
            if (realmGet$module != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.moduleIndex, rowIndex, realmGet$module, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.module_idIndex, rowIndex, ((CartRealmProxyInterface) object).realmGet$module_id(), false);
            Table.nativeSetDouble(tableNativePtr, columnInfo.amountIndex, rowIndex, ((CartRealmProxyInterface) object).realmGet$amount(), false);

            com.geogreenapps.apps.indukaan.classes.Product productObj = ((CartRealmProxyInterface) object).realmGet$product();
            if (productObj != null) {
                Long cacheproduct = cache.get(productObj);
                if (cacheproduct == null) {
                    cacheproduct = ProductRealmProxy.insert(realm, productObj, cache);
                }
                table.setLink(columnInfo.productIndex, rowIndex, cacheproduct, false);
            }

            com.geogreenapps.apps.indukaan.classes.Offer offerObj = ((CartRealmProxyInterface) object).realmGet$offer();
            if (offerObj != null) {
                Long cacheoffer = cache.get(offerObj);
                if (cacheoffer == null) {
                    cacheoffer = OfferRealmProxy.insert(realm, offerObj, cache);
                }
                table.setLink(columnInfo.offerIndex, rowIndex, cacheoffer, false);
            }

            RealmList<com.geogreenapps.apps.indukaan.classes.Variant> variantsList = ((CartRealmProxyInterface) object).realmGet$variants();
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
            Table.nativeSetLong(tableNativePtr, columnInfo.qteIndex, rowIndex, ((CartRealmProxyInterface) object).realmGet$qte(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.statusIndex, rowIndex, ((CartRealmProxyInterface) object).realmGet$status(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.user_idIndex, rowIndex, ((CartRealmProxyInterface) object).realmGet$user_id(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.parent_idIndex, rowIndex, ((CartRealmProxyInterface) object).realmGet$parent_id(), false);
        }
    }

    public static long insertOrUpdate(Realm realm, com.geogreenapps.apps.indukaan.classes.Cart object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Cart.class);
        long tableNativePtr = table.getNativePtr();
        CartColumnInfo columnInfo = (CartColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Cart.class);
        long pkColumnIndex = columnInfo.idIndex;
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((CartRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((CartRealmProxyInterface) object).realmGet$id());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((CartRealmProxyInterface) object).realmGet$id());
        }
        cache.put(object, rowIndex);
        String realmGet$module = ((CartRealmProxyInterface) object).realmGet$module();
        if (realmGet$module != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.moduleIndex, rowIndex, realmGet$module, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.moduleIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.module_idIndex, rowIndex, ((CartRealmProxyInterface) object).realmGet$module_id(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.amountIndex, rowIndex, ((CartRealmProxyInterface) object).realmGet$amount(), false);

        com.geogreenapps.apps.indukaan.classes.Product productObj = ((CartRealmProxyInterface) object).realmGet$product();
        if (productObj != null) {
            Long cacheproduct = cache.get(productObj);
            if (cacheproduct == null) {
                cacheproduct = ProductRealmProxy.insertOrUpdate(realm, productObj, cache);
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.productIndex, rowIndex, cacheproduct, false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.productIndex, rowIndex);
        }

        com.geogreenapps.apps.indukaan.classes.Offer offerObj = ((CartRealmProxyInterface) object).realmGet$offer();
        if (offerObj != null) {
            Long cacheoffer = cache.get(offerObj);
            if (cacheoffer == null) {
                cacheoffer = OfferRealmProxy.insertOrUpdate(realm, offerObj, cache);
            }
            Table.nativeSetLink(tableNativePtr, columnInfo.offerIndex, rowIndex, cacheoffer, false);
        } else {
            Table.nativeNullifyLink(tableNativePtr, columnInfo.offerIndex, rowIndex);
        }

        OsList variantsOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.variantsIndex);
        RealmList<com.geogreenapps.apps.indukaan.classes.Variant> variantsList = ((CartRealmProxyInterface) object).realmGet$variants();
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

        Table.nativeSetLong(tableNativePtr, columnInfo.qteIndex, rowIndex, ((CartRealmProxyInterface) object).realmGet$qte(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.statusIndex, rowIndex, ((CartRealmProxyInterface) object).realmGet$status(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.user_idIndex, rowIndex, ((CartRealmProxyInterface) object).realmGet$user_id(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.parent_idIndex, rowIndex, ((CartRealmProxyInterface) object).realmGet$parent_id(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Cart.class);
        long tableNativePtr = table.getNativePtr();
        CartColumnInfo columnInfo = (CartColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Cart.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.geogreenapps.apps.indukaan.classes.Cart object = null;
        while (objects.hasNext()) {
            object = (com.geogreenapps.apps.indukaan.classes.Cart) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((CartRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((CartRealmProxyInterface) object).realmGet$id());
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((CartRealmProxyInterface) object).realmGet$id());
            }
            cache.put(object, rowIndex);
            String realmGet$module = ((CartRealmProxyInterface) object).realmGet$module();
            if (realmGet$module != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.moduleIndex, rowIndex, realmGet$module, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.moduleIndex, rowIndex, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.module_idIndex, rowIndex, ((CartRealmProxyInterface) object).realmGet$module_id(), false);
            Table.nativeSetDouble(tableNativePtr, columnInfo.amountIndex, rowIndex, ((CartRealmProxyInterface) object).realmGet$amount(), false);

            com.geogreenapps.apps.indukaan.classes.Product productObj = ((CartRealmProxyInterface) object).realmGet$product();
            if (productObj != null) {
                Long cacheproduct = cache.get(productObj);
                if (cacheproduct == null) {
                    cacheproduct = ProductRealmProxy.insertOrUpdate(realm, productObj, cache);
                }
                Table.nativeSetLink(tableNativePtr, columnInfo.productIndex, rowIndex, cacheproduct, false);
            } else {
                Table.nativeNullifyLink(tableNativePtr, columnInfo.productIndex, rowIndex);
            }

            com.geogreenapps.apps.indukaan.classes.Offer offerObj = ((CartRealmProxyInterface) object).realmGet$offer();
            if (offerObj != null) {
                Long cacheoffer = cache.get(offerObj);
                if (cacheoffer == null) {
                    cacheoffer = OfferRealmProxy.insertOrUpdate(realm, offerObj, cache);
                }
                Table.nativeSetLink(tableNativePtr, columnInfo.offerIndex, rowIndex, cacheoffer, false);
            } else {
                Table.nativeNullifyLink(tableNativePtr, columnInfo.offerIndex, rowIndex);
            }

            OsList variantsOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.variantsIndex);
            RealmList<com.geogreenapps.apps.indukaan.classes.Variant> variantsList = ((CartRealmProxyInterface) object).realmGet$variants();
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

            Table.nativeSetLong(tableNativePtr, columnInfo.qteIndex, rowIndex, ((CartRealmProxyInterface) object).realmGet$qte(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.statusIndex, rowIndex, ((CartRealmProxyInterface) object).realmGet$status(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.user_idIndex, rowIndex, ((CartRealmProxyInterface) object).realmGet$user_id(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.parent_idIndex, rowIndex, ((CartRealmProxyInterface) object).realmGet$parent_id(), false);
        }
    }

    public static com.geogreenapps.apps.indukaan.classes.Cart createDetachedCopy(com.geogreenapps.apps.indukaan.classes.Cart realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.geogreenapps.apps.indukaan.classes.Cart unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.geogreenapps.apps.indukaan.classes.Cart();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.geogreenapps.apps.indukaan.classes.Cart) cachedObject.object;
            }
            unmanagedObject = (com.geogreenapps.apps.indukaan.classes.Cart) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        CartRealmProxyInterface unmanagedCopy = (CartRealmProxyInterface) unmanagedObject;
        CartRealmProxyInterface realmSource = (CartRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$module(realmSource.realmGet$module());
        unmanagedCopy.realmSet$module_id(realmSource.realmGet$module_id());
        unmanagedCopy.realmSet$amount(realmSource.realmGet$amount());

        // Deep copy of product
        unmanagedCopy.realmSet$product(ProductRealmProxy.createDetachedCopy(realmSource.realmGet$product(), currentDepth + 1, maxDepth, cache));

        // Deep copy of offer
        unmanagedCopy.realmSet$offer(OfferRealmProxy.createDetachedCopy(realmSource.realmGet$offer(), currentDepth + 1, maxDepth, cache));

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
        unmanagedCopy.realmSet$qte(realmSource.realmGet$qte());
        unmanagedCopy.realmSet$status(realmSource.realmGet$status());
        unmanagedCopy.realmSet$user_id(realmSource.realmGet$user_id());
        unmanagedCopy.realmSet$parent_id(realmSource.realmGet$parent_id());

        return unmanagedObject;
    }

    static com.geogreenapps.apps.indukaan.classes.Cart update(Realm realm, com.geogreenapps.apps.indukaan.classes.Cart realmObject, com.geogreenapps.apps.indukaan.classes.Cart newObject, Map<RealmModel, RealmObjectProxy> cache) {
        CartRealmProxyInterface realmObjectTarget = (CartRealmProxyInterface) realmObject;
        CartRealmProxyInterface realmObjectSource = (CartRealmProxyInterface) newObject;
        realmObjectTarget.realmSet$module(realmObjectSource.realmGet$module());
        realmObjectTarget.realmSet$module_id(realmObjectSource.realmGet$module_id());
        realmObjectTarget.realmSet$amount(realmObjectSource.realmGet$amount());
        com.geogreenapps.apps.indukaan.classes.Product productObj = realmObjectSource.realmGet$product();
        if (productObj == null) {
            realmObjectTarget.realmSet$product(null);
        } else {
            com.geogreenapps.apps.indukaan.classes.Product cacheproduct = (com.geogreenapps.apps.indukaan.classes.Product) cache.get(productObj);
            if (cacheproduct != null) {
                realmObjectTarget.realmSet$product(cacheproduct);
            } else {
                realmObjectTarget.realmSet$product(ProductRealmProxy.copyOrUpdate(realm, productObj, true, cache));
            }
        }
        com.geogreenapps.apps.indukaan.classes.Offer offerObj = realmObjectSource.realmGet$offer();
        if (offerObj == null) {
            realmObjectTarget.realmSet$offer(null);
        } else {
            com.geogreenapps.apps.indukaan.classes.Offer cacheoffer = (com.geogreenapps.apps.indukaan.classes.Offer) cache.get(offerObj);
            if (cacheoffer != null) {
                realmObjectTarget.realmSet$offer(cacheoffer);
            } else {
                realmObjectTarget.realmSet$offer(OfferRealmProxy.copyOrUpdate(realm, offerObj, true, cache));
            }
        }
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
        realmObjectTarget.realmSet$qte(realmObjectSource.realmGet$qte());
        realmObjectTarget.realmSet$status(realmObjectSource.realmGet$status());
        realmObjectTarget.realmSet$user_id(realmObjectSource.realmGet$user_id());
        realmObjectTarget.realmSet$parent_id(realmObjectSource.realmGet$parent_id());
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Cart = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{module:");
        stringBuilder.append(realmGet$module() != null ? realmGet$module() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{module_id:");
        stringBuilder.append(realmGet$module_id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{amount:");
        stringBuilder.append(realmGet$amount());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{product:");
        stringBuilder.append(realmGet$product() != null ? "Product" : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{offer:");
        stringBuilder.append(realmGet$offer() != null ? "Offer" : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{variants:");
        stringBuilder.append("RealmList<Variant>[").append(realmGet$variants().size()).append("]");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{qte:");
        stringBuilder.append(realmGet$qte());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{status:");
        stringBuilder.append(realmGet$status());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{user_id:");
        stringBuilder.append(realmGet$user_id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{parent_id:");
        stringBuilder.append(realmGet$parent_id());
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
        CartRealmProxy aCart = (CartRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aCart.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aCart.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aCart.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
