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
public class OrderRealmProxy extends com.geogreenapps.apps.indukaan.classes.Order
    implements RealmObjectProxy, OrderRealmProxyInterface {

    static final class OrderColumnInfo extends ColumnInfo {
        long idIndex;
        long user_idIndex;
        long module_idIndex;
        long id_storeIndex;
        long nameIndex;
        long req_cf_idIndex;
        long delivery_statusIndex;
        long delivery_idIndex;
        long status_idIndex;
        long statusIndex;
        long moduleIndex;
        long cartIndex;
        long req_cf_dataIndex;
        long updated_atIndex;
        long created_atIndex;
        long payment_statusIndex;
        long commissionIndex;
        long payment_status_dataIndex;
        long itemsIndex;
        long timeLinesIndex;
        long amountIndex;
        long variantsIndex;
        long extrasIndex;

        OrderColumnInfo(OsSchemaInfo schemaInfo) {
            super(23);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Order");
            this.idIndex = addColumnDetails("id", objectSchemaInfo);
            this.user_idIndex = addColumnDetails("user_id", objectSchemaInfo);
            this.module_idIndex = addColumnDetails("module_id", objectSchemaInfo);
            this.id_storeIndex = addColumnDetails("id_store", objectSchemaInfo);
            this.nameIndex = addColumnDetails("name", objectSchemaInfo);
            this.req_cf_idIndex = addColumnDetails("req_cf_id", objectSchemaInfo);
            this.delivery_statusIndex = addColumnDetails("delivery_status", objectSchemaInfo);
            this.delivery_idIndex = addColumnDetails("delivery_id", objectSchemaInfo);
            this.status_idIndex = addColumnDetails("status_id", objectSchemaInfo);
            this.statusIndex = addColumnDetails("status", objectSchemaInfo);
            this.moduleIndex = addColumnDetails("module", objectSchemaInfo);
            this.cartIndex = addColumnDetails("cart", objectSchemaInfo);
            this.req_cf_dataIndex = addColumnDetails("req_cf_data", objectSchemaInfo);
            this.updated_atIndex = addColumnDetails("updated_at", objectSchemaInfo);
            this.created_atIndex = addColumnDetails("created_at", objectSchemaInfo);
            this.payment_statusIndex = addColumnDetails("payment_status", objectSchemaInfo);
            this.commissionIndex = addColumnDetails("commission", objectSchemaInfo);
            this.payment_status_dataIndex = addColumnDetails("payment_status_data", objectSchemaInfo);
            this.itemsIndex = addColumnDetails("items", objectSchemaInfo);
            this.timeLinesIndex = addColumnDetails("timeLines", objectSchemaInfo);
            this.amountIndex = addColumnDetails("amount", objectSchemaInfo);
            this.variantsIndex = addColumnDetails("variants", objectSchemaInfo);
            this.extrasIndex = addColumnDetails("extras", objectSchemaInfo);
        }

        OrderColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new OrderColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final OrderColumnInfo src = (OrderColumnInfo) rawSrc;
            final OrderColumnInfo dst = (OrderColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.user_idIndex = src.user_idIndex;
            dst.module_idIndex = src.module_idIndex;
            dst.id_storeIndex = src.id_storeIndex;
            dst.nameIndex = src.nameIndex;
            dst.req_cf_idIndex = src.req_cf_idIndex;
            dst.delivery_statusIndex = src.delivery_statusIndex;
            dst.delivery_idIndex = src.delivery_idIndex;
            dst.status_idIndex = src.status_idIndex;
            dst.statusIndex = src.statusIndex;
            dst.moduleIndex = src.moduleIndex;
            dst.cartIndex = src.cartIndex;
            dst.req_cf_dataIndex = src.req_cf_dataIndex;
            dst.updated_atIndex = src.updated_atIndex;
            dst.created_atIndex = src.created_atIndex;
            dst.payment_statusIndex = src.payment_statusIndex;
            dst.commissionIndex = src.commissionIndex;
            dst.payment_status_dataIndex = src.payment_status_dataIndex;
            dst.itemsIndex = src.itemsIndex;
            dst.timeLinesIndex = src.timeLinesIndex;
            dst.amountIndex = src.amountIndex;
            dst.variantsIndex = src.variantsIndex;
            dst.extrasIndex = src.extrasIndex;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>(23);
        fieldNames.add("id");
        fieldNames.add("user_id");
        fieldNames.add("module_id");
        fieldNames.add("id_store");
        fieldNames.add("name");
        fieldNames.add("req_cf_id");
        fieldNames.add("delivery_status");
        fieldNames.add("delivery_id");
        fieldNames.add("status_id");
        fieldNames.add("status");
        fieldNames.add("module");
        fieldNames.add("cart");
        fieldNames.add("req_cf_data");
        fieldNames.add("updated_at");
        fieldNames.add("created_at");
        fieldNames.add("payment_status");
        fieldNames.add("commission");
        fieldNames.add("payment_status_data");
        fieldNames.add("items");
        fieldNames.add("timeLines");
        fieldNames.add("amount");
        fieldNames.add("variants");
        fieldNames.add("extras");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    private OrderColumnInfo columnInfo;
    private ProxyState<com.geogreenapps.apps.indukaan.classes.Order> proxyState;
    private RealmList<com.geogreenapps.apps.indukaan.classes.Item> itemsRealmList;
    private RealmList<com.geogreenapps.apps.indukaan.classes.TimeLine> timeLinesRealmList;
    private RealmList<com.geogreenapps.apps.indukaan.classes.Variant> variantsRealmList;

    OrderRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (OrderColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.geogreenapps.apps.indukaan.classes.Order>(this);
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
    public int realmGet$id_store() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.id_storeIndex);
    }

    @Override
    public void realmSet$id_store(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.id_storeIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.id_storeIndex, value);
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
    public int realmGet$req_cf_id() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.req_cf_idIndex);
    }

    @Override
    public void realmSet$req_cf_id(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.req_cf_idIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.req_cf_idIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$delivery_status() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.delivery_statusIndex);
    }

    @Override
    public void realmSet$delivery_status(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.delivery_statusIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.delivery_statusIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$delivery_id() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.delivery_idIndex);
    }

    @Override
    public void realmSet$delivery_id(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.delivery_idIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.delivery_idIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$status_id() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.status_idIndex);
    }

    @Override
    public void realmSet$status_id(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.status_idIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.status_idIndex, value);
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
    public String realmGet$cart() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.cartIndex);
    }

    @Override
    public void realmSet$cart(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.cartIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.cartIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.cartIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.cartIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$req_cf_data() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.req_cf_dataIndex);
    }

    @Override
    public void realmSet$req_cf_data(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.req_cf_dataIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.req_cf_dataIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.req_cf_dataIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.req_cf_dataIndex, value);
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
    public String realmGet$payment_status() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.payment_statusIndex);
    }

    @Override
    public void realmSet$payment_status(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.payment_statusIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.payment_statusIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.payment_statusIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.payment_statusIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$commission() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.commissionIndex);
    }

    @Override
    public void realmSet$commission(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.commissionIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.commissionIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.commissionIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.commissionIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$payment_status_data() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.payment_status_dataIndex);
    }

    @Override
    public void realmSet$payment_status_data(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.payment_status_dataIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.payment_status_dataIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.payment_status_dataIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.payment_status_dataIndex, value);
    }

    @Override
    public RealmList<com.geogreenapps.apps.indukaan.classes.Item> realmGet$items() {
        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (itemsRealmList != null) {
            return itemsRealmList;
        } else {
            OsList osList = proxyState.getRow$realm().getModelList(columnInfo.itemsIndex);
            itemsRealmList = new RealmList<com.geogreenapps.apps.indukaan.classes.Item>(com.geogreenapps.apps.indukaan.classes.Item.class, osList, proxyState.getRealm$realm());
            return itemsRealmList;
        }
    }

    @Override
    public void realmSet$items(RealmList<com.geogreenapps.apps.indukaan.classes.Item> value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("items")) {
                return;
            }
            // if the list contains unmanaged RealmObjects, convert them to managed.
            if (value != null && !value.isManaged()) {
                final Realm realm = (Realm) proxyState.getRealm$realm();
                final RealmList<com.geogreenapps.apps.indukaan.classes.Item> original = value;
                value = new RealmList<com.geogreenapps.apps.indukaan.classes.Item>();
                for (com.geogreenapps.apps.indukaan.classes.Item item : original) {
                    if (item == null || RealmObject.isManaged(item)) {
                        value.add(item);
                    } else {
                        value.add(realm.copyToRealm(item));
                    }
                }
            }
        }

        proxyState.getRealm$realm().checkIfValid();
        OsList osList = proxyState.getRow$realm().getModelList(columnInfo.itemsIndex);
        // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
        if (value != null && value.size() == osList.size()) {
            int objects = value.size();
            for (int i = 0; i < objects; i++) {
                com.geogreenapps.apps.indukaan.classes.Item linkedObject = value.get(i);
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
                com.geogreenapps.apps.indukaan.classes.Item linkedObject = value.get(i);
                proxyState.checkValidObject(linkedObject);
                osList.addRow(((RealmObjectProxy) linkedObject).realmGet$proxyState().getRow$realm().getIndex());
            }
        }
    }

    @Override
    public RealmList<com.geogreenapps.apps.indukaan.classes.TimeLine> realmGet$timeLines() {
        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (timeLinesRealmList != null) {
            return timeLinesRealmList;
        } else {
            OsList osList = proxyState.getRow$realm().getModelList(columnInfo.timeLinesIndex);
            timeLinesRealmList = new RealmList<com.geogreenapps.apps.indukaan.classes.TimeLine>(com.geogreenapps.apps.indukaan.classes.TimeLine.class, osList, proxyState.getRealm$realm());
            return timeLinesRealmList;
        }
    }

    @Override
    public void realmSet$timeLines(RealmList<com.geogreenapps.apps.indukaan.classes.TimeLine> value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("timeLines")) {
                return;
            }
            // if the list contains unmanaged RealmObjects, convert them to managed.
            if (value != null && !value.isManaged()) {
                final Realm realm = (Realm) proxyState.getRealm$realm();
                final RealmList<com.geogreenapps.apps.indukaan.classes.TimeLine> original = value;
                value = new RealmList<com.geogreenapps.apps.indukaan.classes.TimeLine>();
                for (com.geogreenapps.apps.indukaan.classes.TimeLine item : original) {
                    if (item == null || RealmObject.isManaged(item)) {
                        value.add(item);
                    } else {
                        value.add(realm.copyToRealm(item));
                    }
                }
            }
        }

        proxyState.getRealm$realm().checkIfValid();
        OsList osList = proxyState.getRow$realm().getModelList(columnInfo.timeLinesIndex);
        // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
        if (value != null && value.size() == osList.size()) {
            int objects = value.size();
            for (int i = 0; i < objects; i++) {
                com.geogreenapps.apps.indukaan.classes.TimeLine linkedObject = value.get(i);
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
                com.geogreenapps.apps.indukaan.classes.TimeLine linkedObject = value.get(i);
                proxyState.checkValidObject(linkedObject);
                osList.addRow(((RealmObjectProxy) linkedObject).realmGet$proxyState().getRow$realm().getIndex());
            }
        }
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
    public String realmGet$extras() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.extrasIndex);
    }

    @Override
    public void realmSet$extras(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.extrasIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.extrasIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.extrasIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.extrasIndex, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("Order", 23, 0);
        builder.addPersistedProperty("id", RealmFieldType.INTEGER, Property.PRIMARY_KEY, Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("user_id", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("module_id", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("id_store", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("name", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("req_cf_id", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("delivery_status", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("delivery_id", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("status_id", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedProperty("status", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("module", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("cart", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("req_cf_data", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("updated_at", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("created_at", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("payment_status", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("commission", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("payment_status_data", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedLinkProperty("items", RealmFieldType.LIST, "Item");
        builder.addPersistedLinkProperty("timeLines", RealmFieldType.LIST, "TimeLine");
        builder.addPersistedProperty("amount", RealmFieldType.DOUBLE, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
        builder.addPersistedLinkProperty("variants", RealmFieldType.LIST, "Variant");
        builder.addPersistedProperty("extras", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static OrderColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new OrderColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "Order";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.geogreenapps.apps.indukaan.classes.Order createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = new ArrayList<String>(3);
        com.geogreenapps.apps.indukaan.classes.Order obj = null;
        if (update) {
            Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Order.class);
            OrderColumnInfo columnInfo = (OrderColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Order.class);
            long pkColumnIndex = columnInfo.idIndex;
            long rowIndex = Table.NO_MATCH;
            if (!json.isNull("id")) {
                rowIndex = table.findFirstLong(pkColumnIndex, json.getLong("id"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Order.class), false, Collections.<String> emptyList());
                    obj = new io.realm.OrderRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("items")) {
                excludeFields.add("items");
            }
            if (json.has("timeLines")) {
                excludeFields.add("timeLines");
            }
            if (json.has("variants")) {
                excludeFields.add("variants");
            }
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = (io.realm.OrderRealmProxy) realm.createObjectInternal(com.geogreenapps.apps.indukaan.classes.Order.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.OrderRealmProxy) realm.createObjectInternal(com.geogreenapps.apps.indukaan.classes.Order.class, json.getInt("id"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'id'.");
            }
        }

        final OrderRealmProxyInterface objProxy = (OrderRealmProxyInterface) obj;
        if (json.has("user_id")) {
            if (json.isNull("user_id")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'user_id' to null.");
            } else {
                objProxy.realmSet$user_id((int) json.getInt("user_id"));
            }
        }
        if (json.has("module_id")) {
            if (json.isNull("module_id")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'module_id' to null.");
            } else {
                objProxy.realmSet$module_id((int) json.getInt("module_id"));
            }
        }
        if (json.has("id_store")) {
            if (json.isNull("id_store")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'id_store' to null.");
            } else {
                objProxy.realmSet$id_store((int) json.getInt("id_store"));
            }
        }
        if (json.has("name")) {
            if (json.isNull("name")) {
                objProxy.realmSet$name(null);
            } else {
                objProxy.realmSet$name((String) json.getString("name"));
            }
        }
        if (json.has("req_cf_id")) {
            if (json.isNull("req_cf_id")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'req_cf_id' to null.");
            } else {
                objProxy.realmSet$req_cf_id((int) json.getInt("req_cf_id"));
            }
        }
        if (json.has("delivery_status")) {
            if (json.isNull("delivery_status")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'delivery_status' to null.");
            } else {
                objProxy.realmSet$delivery_status((int) json.getInt("delivery_status"));
            }
        }
        if (json.has("delivery_id")) {
            if (json.isNull("delivery_id")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'delivery_id' to null.");
            } else {
                objProxy.realmSet$delivery_id((int) json.getInt("delivery_id"));
            }
        }
        if (json.has("status_id")) {
            if (json.isNull("status_id")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'status_id' to null.");
            } else {
                objProxy.realmSet$status_id((int) json.getInt("status_id"));
            }
        }
        if (json.has("status")) {
            if (json.isNull("status")) {
                objProxy.realmSet$status(null);
            } else {
                objProxy.realmSet$status((String) json.getString("status"));
            }
        }
        if (json.has("module")) {
            if (json.isNull("module")) {
                objProxy.realmSet$module(null);
            } else {
                objProxy.realmSet$module((String) json.getString("module"));
            }
        }
        if (json.has("cart")) {
            if (json.isNull("cart")) {
                objProxy.realmSet$cart(null);
            } else {
                objProxy.realmSet$cart((String) json.getString("cart"));
            }
        }
        if (json.has("req_cf_data")) {
            if (json.isNull("req_cf_data")) {
                objProxy.realmSet$req_cf_data(null);
            } else {
                objProxy.realmSet$req_cf_data((String) json.getString("req_cf_data"));
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
        if (json.has("payment_status")) {
            if (json.isNull("payment_status")) {
                objProxy.realmSet$payment_status(null);
            } else {
                objProxy.realmSet$payment_status((String) json.getString("payment_status"));
            }
        }
        if (json.has("commission")) {
            if (json.isNull("commission")) {
                objProxy.realmSet$commission(null);
            } else {
                objProxy.realmSet$commission((String) json.getString("commission"));
            }
        }
        if (json.has("payment_status_data")) {
            if (json.isNull("payment_status_data")) {
                objProxy.realmSet$payment_status_data(null);
            } else {
                objProxy.realmSet$payment_status_data((String) json.getString("payment_status_data"));
            }
        }
        if (json.has("items")) {
            if (json.isNull("items")) {
                objProxy.realmSet$items(null);
            } else {
                objProxy.realmGet$items().clear();
                JSONArray array = json.getJSONArray("items");
                for (int i = 0; i < array.length(); i++) {
                    com.geogreenapps.apps.indukaan.classes.Item item = ItemRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    objProxy.realmGet$items().add(item);
                }
            }
        }
        if (json.has("timeLines")) {
            if (json.isNull("timeLines")) {
                objProxy.realmSet$timeLines(null);
            } else {
                objProxy.realmGet$timeLines().clear();
                JSONArray array = json.getJSONArray("timeLines");
                for (int i = 0; i < array.length(); i++) {
                    com.geogreenapps.apps.indukaan.classes.TimeLine item = TimeLineRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    objProxy.realmGet$timeLines().add(item);
                }
            }
        }
        if (json.has("amount")) {
            if (json.isNull("amount")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'amount' to null.");
            } else {
                objProxy.realmSet$amount((double) json.getDouble("amount"));
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
        if (json.has("extras")) {
            if (json.isNull("extras")) {
                objProxy.realmSet$extras(null);
            } else {
                objProxy.realmSet$extras((String) json.getString("extras"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.geogreenapps.apps.indukaan.classes.Order createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.geogreenapps.apps.indukaan.classes.Order obj = new com.geogreenapps.apps.indukaan.classes.Order();
        final OrderRealmProxyInterface objProxy = (OrderRealmProxyInterface) obj;
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
            } else if (name.equals("user_id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$user_id((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'user_id' to null.");
                }
            } else if (name.equals("module_id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$module_id((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'module_id' to null.");
                }
            } else if (name.equals("id_store")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$id_store((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'id_store' to null.");
                }
            } else if (name.equals("name")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$name((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$name(null);
                }
            } else if (name.equals("req_cf_id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$req_cf_id((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'req_cf_id' to null.");
                }
            } else if (name.equals("delivery_status")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$delivery_status((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'delivery_status' to null.");
                }
            } else if (name.equals("delivery_id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$delivery_id((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'delivery_id' to null.");
                }
            } else if (name.equals("status_id")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$status_id((int) reader.nextInt());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'status_id' to null.");
                }
            } else if (name.equals("status")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$status((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$status(null);
                }
            } else if (name.equals("module")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$module((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$module(null);
                }
            } else if (name.equals("cart")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$cart((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$cart(null);
                }
            } else if (name.equals("req_cf_data")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$req_cf_data((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$req_cf_data(null);
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
            } else if (name.equals("payment_status")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$payment_status((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$payment_status(null);
                }
            } else if (name.equals("commission")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$commission((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$commission(null);
                }
            } else if (name.equals("payment_status_data")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$payment_status_data((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$payment_status_data(null);
                }
            } else if (name.equals("items")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$items(null);
                } else {
                    objProxy.realmSet$items(new RealmList<com.geogreenapps.apps.indukaan.classes.Item>());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.geogreenapps.apps.indukaan.classes.Item item = ItemRealmProxy.createUsingJsonStream(realm, reader);
                        objProxy.realmGet$items().add(item);
                    }
                    reader.endArray();
                }
            } else if (name.equals("timeLines")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    objProxy.realmSet$timeLines(null);
                } else {
                    objProxy.realmSet$timeLines(new RealmList<com.geogreenapps.apps.indukaan.classes.TimeLine>());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.geogreenapps.apps.indukaan.classes.TimeLine item = TimeLineRealmProxy.createUsingJsonStream(realm, reader);
                        objProxy.realmGet$timeLines().add(item);
                    }
                    reader.endArray();
                }
            } else if (name.equals("amount")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$amount((double) reader.nextDouble());
                } else {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'amount' to null.");
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
            } else if (name.equals("extras")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$extras((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$extras(null);
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

    public static com.geogreenapps.apps.indukaan.classes.Order copyOrUpdate(Realm realm, com.geogreenapps.apps.indukaan.classes.Order object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
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
            return (com.geogreenapps.apps.indukaan.classes.Order) cachedRealmObject;
        }

        com.geogreenapps.apps.indukaan.classes.Order realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Order.class);
            OrderColumnInfo columnInfo = (OrderColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Order.class);
            long pkColumnIndex = columnInfo.idIndex;
            long rowIndex = table.findFirstLong(pkColumnIndex, ((OrderRealmProxyInterface) object).realmGet$id());
            if (rowIndex == Table.NO_MATCH) {
                canUpdate = false;
            } else {
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Order.class), false, Collections.<String> emptyList());
                    realmObject = new io.realm.OrderRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, realmObject, object, cache) : copy(realm, object, update, cache);
    }

    public static com.geogreenapps.apps.indukaan.classes.Order copy(Realm realm, com.geogreenapps.apps.indukaan.classes.Order newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.geogreenapps.apps.indukaan.classes.Order) cachedRealmObject;
        }

        // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
        com.geogreenapps.apps.indukaan.classes.Order realmObject = realm.createObjectInternal(com.geogreenapps.apps.indukaan.classes.Order.class, ((OrderRealmProxyInterface) newObject).realmGet$id(), false, Collections.<String>emptyList());
        cache.put(newObject, (RealmObjectProxy) realmObject);

        OrderRealmProxyInterface realmObjectSource = (OrderRealmProxyInterface) newObject;
        OrderRealmProxyInterface realmObjectCopy = (OrderRealmProxyInterface) realmObject;

        realmObjectCopy.realmSet$user_id(realmObjectSource.realmGet$user_id());
        realmObjectCopy.realmSet$module_id(realmObjectSource.realmGet$module_id());
        realmObjectCopy.realmSet$id_store(realmObjectSource.realmGet$id_store());
        realmObjectCopy.realmSet$name(realmObjectSource.realmGet$name());
        realmObjectCopy.realmSet$req_cf_id(realmObjectSource.realmGet$req_cf_id());
        realmObjectCopy.realmSet$delivery_status(realmObjectSource.realmGet$delivery_status());
        realmObjectCopy.realmSet$delivery_id(realmObjectSource.realmGet$delivery_id());
        realmObjectCopy.realmSet$status_id(realmObjectSource.realmGet$status_id());
        realmObjectCopy.realmSet$status(realmObjectSource.realmGet$status());
        realmObjectCopy.realmSet$module(realmObjectSource.realmGet$module());
        realmObjectCopy.realmSet$cart(realmObjectSource.realmGet$cart());
        realmObjectCopy.realmSet$req_cf_data(realmObjectSource.realmGet$req_cf_data());
        realmObjectCopy.realmSet$updated_at(realmObjectSource.realmGet$updated_at());
        realmObjectCopy.realmSet$created_at(realmObjectSource.realmGet$created_at());
        realmObjectCopy.realmSet$payment_status(realmObjectSource.realmGet$payment_status());
        realmObjectCopy.realmSet$commission(realmObjectSource.realmGet$commission());
        realmObjectCopy.realmSet$payment_status_data(realmObjectSource.realmGet$payment_status_data());

        RealmList<com.geogreenapps.apps.indukaan.classes.Item> itemsList = realmObjectSource.realmGet$items();
        if (itemsList != null) {
            RealmList<com.geogreenapps.apps.indukaan.classes.Item> itemsRealmList = realmObjectCopy.realmGet$items();
            itemsRealmList.clear();
            for (int i = 0; i < itemsList.size(); i++) {
                com.geogreenapps.apps.indukaan.classes.Item itemsItem = itemsList.get(i);
                com.geogreenapps.apps.indukaan.classes.Item cacheitems = (com.geogreenapps.apps.indukaan.classes.Item) cache.get(itemsItem);
                if (cacheitems != null) {
                    itemsRealmList.add(cacheitems);
                } else {
                    itemsRealmList.add(ItemRealmProxy.copyOrUpdate(realm, itemsItem, update, cache));
                }
            }
        }


        RealmList<com.geogreenapps.apps.indukaan.classes.TimeLine> timeLinesList = realmObjectSource.realmGet$timeLines();
        if (timeLinesList != null) {
            RealmList<com.geogreenapps.apps.indukaan.classes.TimeLine> timeLinesRealmList = realmObjectCopy.realmGet$timeLines();
            timeLinesRealmList.clear();
            for (int i = 0; i < timeLinesList.size(); i++) {
                com.geogreenapps.apps.indukaan.classes.TimeLine timeLinesItem = timeLinesList.get(i);
                com.geogreenapps.apps.indukaan.classes.TimeLine cachetimeLines = (com.geogreenapps.apps.indukaan.classes.TimeLine) cache.get(timeLinesItem);
                if (cachetimeLines != null) {
                    timeLinesRealmList.add(cachetimeLines);
                } else {
                    timeLinesRealmList.add(TimeLineRealmProxy.copyOrUpdate(realm, timeLinesItem, update, cache));
                }
            }
        }

        realmObjectCopy.realmSet$amount(realmObjectSource.realmGet$amount());

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

        realmObjectCopy.realmSet$extras(realmObjectSource.realmGet$extras());
        return realmObject;
    }

    public static long insert(Realm realm, com.geogreenapps.apps.indukaan.classes.Order object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Order.class);
        long tableNativePtr = table.getNativePtr();
        OrderColumnInfo columnInfo = (OrderColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Order.class);
        long pkColumnIndex = columnInfo.idIndex;
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((OrderRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((OrderRealmProxyInterface) object).realmGet$id());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((OrderRealmProxyInterface) object).realmGet$id());
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.user_idIndex, rowIndex, ((OrderRealmProxyInterface) object).realmGet$user_id(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.module_idIndex, rowIndex, ((OrderRealmProxyInterface) object).realmGet$module_id(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.id_storeIndex, rowIndex, ((OrderRealmProxyInterface) object).realmGet$id_store(), false);
        String realmGet$name = ((OrderRealmProxyInterface) object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.req_cf_idIndex, rowIndex, ((OrderRealmProxyInterface) object).realmGet$req_cf_id(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.delivery_statusIndex, rowIndex, ((OrderRealmProxyInterface) object).realmGet$delivery_status(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.delivery_idIndex, rowIndex, ((OrderRealmProxyInterface) object).realmGet$delivery_id(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.status_idIndex, rowIndex, ((OrderRealmProxyInterface) object).realmGet$status_id(), false);
        String realmGet$status = ((OrderRealmProxyInterface) object).realmGet$status();
        if (realmGet$status != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.statusIndex, rowIndex, realmGet$status, false);
        }
        String realmGet$module = ((OrderRealmProxyInterface) object).realmGet$module();
        if (realmGet$module != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.moduleIndex, rowIndex, realmGet$module, false);
        }
        String realmGet$cart = ((OrderRealmProxyInterface) object).realmGet$cart();
        if (realmGet$cart != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.cartIndex, rowIndex, realmGet$cart, false);
        }
        String realmGet$req_cf_data = ((OrderRealmProxyInterface) object).realmGet$req_cf_data();
        if (realmGet$req_cf_data != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.req_cf_dataIndex, rowIndex, realmGet$req_cf_data, false);
        }
        String realmGet$updated_at = ((OrderRealmProxyInterface) object).realmGet$updated_at();
        if (realmGet$updated_at != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.updated_atIndex, rowIndex, realmGet$updated_at, false);
        }
        String realmGet$created_at = ((OrderRealmProxyInterface) object).realmGet$created_at();
        if (realmGet$created_at != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.created_atIndex, rowIndex, realmGet$created_at, false);
        }
        String realmGet$payment_status = ((OrderRealmProxyInterface) object).realmGet$payment_status();
        if (realmGet$payment_status != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.payment_statusIndex, rowIndex, realmGet$payment_status, false);
        }
        String realmGet$commission = ((OrderRealmProxyInterface) object).realmGet$commission();
        if (realmGet$commission != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.commissionIndex, rowIndex, realmGet$commission, false);
        }
        String realmGet$payment_status_data = ((OrderRealmProxyInterface) object).realmGet$payment_status_data();
        if (realmGet$payment_status_data != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.payment_status_dataIndex, rowIndex, realmGet$payment_status_data, false);
        }

        RealmList<com.geogreenapps.apps.indukaan.classes.Item> itemsList = ((OrderRealmProxyInterface) object).realmGet$items();
        if (itemsList != null) {
            OsList itemsOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.itemsIndex);
            for (com.geogreenapps.apps.indukaan.classes.Item itemsItem : itemsList) {
                Long cacheItemIndexitems = cache.get(itemsItem);
                if (cacheItemIndexitems == null) {
                    cacheItemIndexitems = ItemRealmProxy.insert(realm, itemsItem, cache);
                }
                itemsOsList.addRow(cacheItemIndexitems);
            }
        }

        RealmList<com.geogreenapps.apps.indukaan.classes.TimeLine> timeLinesList = ((OrderRealmProxyInterface) object).realmGet$timeLines();
        if (timeLinesList != null) {
            OsList timeLinesOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.timeLinesIndex);
            for (com.geogreenapps.apps.indukaan.classes.TimeLine timeLinesItem : timeLinesList) {
                Long cacheItemIndextimeLines = cache.get(timeLinesItem);
                if (cacheItemIndextimeLines == null) {
                    cacheItemIndextimeLines = TimeLineRealmProxy.insert(realm, timeLinesItem, cache);
                }
                timeLinesOsList.addRow(cacheItemIndextimeLines);
            }
        }
        Table.nativeSetDouble(tableNativePtr, columnInfo.amountIndex, rowIndex, ((OrderRealmProxyInterface) object).realmGet$amount(), false);

        RealmList<com.geogreenapps.apps.indukaan.classes.Variant> variantsList = ((OrderRealmProxyInterface) object).realmGet$variants();
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
        String realmGet$extras = ((OrderRealmProxyInterface) object).realmGet$extras();
        if (realmGet$extras != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.extrasIndex, rowIndex, realmGet$extras, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Order.class);
        long tableNativePtr = table.getNativePtr();
        OrderColumnInfo columnInfo = (OrderColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Order.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.geogreenapps.apps.indukaan.classes.Order object = null;
        while (objects.hasNext()) {
            object = (com.geogreenapps.apps.indukaan.classes.Order) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((OrderRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((OrderRealmProxyInterface) object).realmGet$id());
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((OrderRealmProxyInterface) object).realmGet$id());
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.user_idIndex, rowIndex, ((OrderRealmProxyInterface) object).realmGet$user_id(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.module_idIndex, rowIndex, ((OrderRealmProxyInterface) object).realmGet$module_id(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.id_storeIndex, rowIndex, ((OrderRealmProxyInterface) object).realmGet$id_store(), false);
            String realmGet$name = ((OrderRealmProxyInterface) object).realmGet$name();
            if (realmGet$name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.req_cf_idIndex, rowIndex, ((OrderRealmProxyInterface) object).realmGet$req_cf_id(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.delivery_statusIndex, rowIndex, ((OrderRealmProxyInterface) object).realmGet$delivery_status(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.delivery_idIndex, rowIndex, ((OrderRealmProxyInterface) object).realmGet$delivery_id(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.status_idIndex, rowIndex, ((OrderRealmProxyInterface) object).realmGet$status_id(), false);
            String realmGet$status = ((OrderRealmProxyInterface) object).realmGet$status();
            if (realmGet$status != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.statusIndex, rowIndex, realmGet$status, false);
            }
            String realmGet$module = ((OrderRealmProxyInterface) object).realmGet$module();
            if (realmGet$module != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.moduleIndex, rowIndex, realmGet$module, false);
            }
            String realmGet$cart = ((OrderRealmProxyInterface) object).realmGet$cart();
            if (realmGet$cart != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.cartIndex, rowIndex, realmGet$cart, false);
            }
            String realmGet$req_cf_data = ((OrderRealmProxyInterface) object).realmGet$req_cf_data();
            if (realmGet$req_cf_data != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.req_cf_dataIndex, rowIndex, realmGet$req_cf_data, false);
            }
            String realmGet$updated_at = ((OrderRealmProxyInterface) object).realmGet$updated_at();
            if (realmGet$updated_at != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.updated_atIndex, rowIndex, realmGet$updated_at, false);
            }
            String realmGet$created_at = ((OrderRealmProxyInterface) object).realmGet$created_at();
            if (realmGet$created_at != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.created_atIndex, rowIndex, realmGet$created_at, false);
            }
            String realmGet$payment_status = ((OrderRealmProxyInterface) object).realmGet$payment_status();
            if (realmGet$payment_status != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.payment_statusIndex, rowIndex, realmGet$payment_status, false);
            }
            String realmGet$commission = ((OrderRealmProxyInterface) object).realmGet$commission();
            if (realmGet$commission != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.commissionIndex, rowIndex, realmGet$commission, false);
            }
            String realmGet$payment_status_data = ((OrderRealmProxyInterface) object).realmGet$payment_status_data();
            if (realmGet$payment_status_data != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.payment_status_dataIndex, rowIndex, realmGet$payment_status_data, false);
            }

            RealmList<com.geogreenapps.apps.indukaan.classes.Item> itemsList = ((OrderRealmProxyInterface) object).realmGet$items();
            if (itemsList != null) {
                OsList itemsOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.itemsIndex);
                for (com.geogreenapps.apps.indukaan.classes.Item itemsItem : itemsList) {
                    Long cacheItemIndexitems = cache.get(itemsItem);
                    if (cacheItemIndexitems == null) {
                        cacheItemIndexitems = ItemRealmProxy.insert(realm, itemsItem, cache);
                    }
                    itemsOsList.addRow(cacheItemIndexitems);
                }
            }

            RealmList<com.geogreenapps.apps.indukaan.classes.TimeLine> timeLinesList = ((OrderRealmProxyInterface) object).realmGet$timeLines();
            if (timeLinesList != null) {
                OsList timeLinesOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.timeLinesIndex);
                for (com.geogreenapps.apps.indukaan.classes.TimeLine timeLinesItem : timeLinesList) {
                    Long cacheItemIndextimeLines = cache.get(timeLinesItem);
                    if (cacheItemIndextimeLines == null) {
                        cacheItemIndextimeLines = TimeLineRealmProxy.insert(realm, timeLinesItem, cache);
                    }
                    timeLinesOsList.addRow(cacheItemIndextimeLines);
                }
            }
            Table.nativeSetDouble(tableNativePtr, columnInfo.amountIndex, rowIndex, ((OrderRealmProxyInterface) object).realmGet$amount(), false);

            RealmList<com.geogreenapps.apps.indukaan.classes.Variant> variantsList = ((OrderRealmProxyInterface) object).realmGet$variants();
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
            String realmGet$extras = ((OrderRealmProxyInterface) object).realmGet$extras();
            if (realmGet$extras != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.extrasIndex, rowIndex, realmGet$extras, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.geogreenapps.apps.indukaan.classes.Order object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Order.class);
        long tableNativePtr = table.getNativePtr();
        OrderColumnInfo columnInfo = (OrderColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Order.class);
        long pkColumnIndex = columnInfo.idIndex;
        long rowIndex = Table.NO_MATCH;
        Object primaryKeyValue = ((OrderRealmProxyInterface) object).realmGet$id();
        if (primaryKeyValue != null) {
            rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((OrderRealmProxyInterface) object).realmGet$id());
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((OrderRealmProxyInterface) object).realmGet$id());
        }
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.user_idIndex, rowIndex, ((OrderRealmProxyInterface) object).realmGet$user_id(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.module_idIndex, rowIndex, ((OrderRealmProxyInterface) object).realmGet$module_id(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.id_storeIndex, rowIndex, ((OrderRealmProxyInterface) object).realmGet$id_store(), false);
        String realmGet$name = ((OrderRealmProxyInterface) object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.req_cf_idIndex, rowIndex, ((OrderRealmProxyInterface) object).realmGet$req_cf_id(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.delivery_statusIndex, rowIndex, ((OrderRealmProxyInterface) object).realmGet$delivery_status(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.delivery_idIndex, rowIndex, ((OrderRealmProxyInterface) object).realmGet$delivery_id(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.status_idIndex, rowIndex, ((OrderRealmProxyInterface) object).realmGet$status_id(), false);
        String realmGet$status = ((OrderRealmProxyInterface) object).realmGet$status();
        if (realmGet$status != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.statusIndex, rowIndex, realmGet$status, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.statusIndex, rowIndex, false);
        }
        String realmGet$module = ((OrderRealmProxyInterface) object).realmGet$module();
        if (realmGet$module != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.moduleIndex, rowIndex, realmGet$module, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.moduleIndex, rowIndex, false);
        }
        String realmGet$cart = ((OrderRealmProxyInterface) object).realmGet$cart();
        if (realmGet$cart != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.cartIndex, rowIndex, realmGet$cart, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.cartIndex, rowIndex, false);
        }
        String realmGet$req_cf_data = ((OrderRealmProxyInterface) object).realmGet$req_cf_data();
        if (realmGet$req_cf_data != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.req_cf_dataIndex, rowIndex, realmGet$req_cf_data, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.req_cf_dataIndex, rowIndex, false);
        }
        String realmGet$updated_at = ((OrderRealmProxyInterface) object).realmGet$updated_at();
        if (realmGet$updated_at != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.updated_atIndex, rowIndex, realmGet$updated_at, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.updated_atIndex, rowIndex, false);
        }
        String realmGet$created_at = ((OrderRealmProxyInterface) object).realmGet$created_at();
        if (realmGet$created_at != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.created_atIndex, rowIndex, realmGet$created_at, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.created_atIndex, rowIndex, false);
        }
        String realmGet$payment_status = ((OrderRealmProxyInterface) object).realmGet$payment_status();
        if (realmGet$payment_status != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.payment_statusIndex, rowIndex, realmGet$payment_status, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.payment_statusIndex, rowIndex, false);
        }
        String realmGet$commission = ((OrderRealmProxyInterface) object).realmGet$commission();
        if (realmGet$commission != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.commissionIndex, rowIndex, realmGet$commission, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.commissionIndex, rowIndex, false);
        }
        String realmGet$payment_status_data = ((OrderRealmProxyInterface) object).realmGet$payment_status_data();
        if (realmGet$payment_status_data != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.payment_status_dataIndex, rowIndex, realmGet$payment_status_data, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.payment_status_dataIndex, rowIndex, false);
        }

        OsList itemsOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.itemsIndex);
        RealmList<com.geogreenapps.apps.indukaan.classes.Item> itemsList = ((OrderRealmProxyInterface) object).realmGet$items();
        if (itemsList != null && itemsList.size() == itemsOsList.size()) {
            // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
            int objects = itemsList.size();
            for (int i = 0; i < objects; i++) {
                com.geogreenapps.apps.indukaan.classes.Item itemsItem = itemsList.get(i);
                Long cacheItemIndexitems = cache.get(itemsItem);
                if (cacheItemIndexitems == null) {
                    cacheItemIndexitems = ItemRealmProxy.insertOrUpdate(realm, itemsItem, cache);
                }
                itemsOsList.setRow(i, cacheItemIndexitems);
            }
        } else {
            itemsOsList.removeAll();
            if (itemsList != null) {
                for (com.geogreenapps.apps.indukaan.classes.Item itemsItem : itemsList) {
                    Long cacheItemIndexitems = cache.get(itemsItem);
                    if (cacheItemIndexitems == null) {
                        cacheItemIndexitems = ItemRealmProxy.insertOrUpdate(realm, itemsItem, cache);
                    }
                    itemsOsList.addRow(cacheItemIndexitems);
                }
            }
        }


        OsList timeLinesOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.timeLinesIndex);
        RealmList<com.geogreenapps.apps.indukaan.classes.TimeLine> timeLinesList = ((OrderRealmProxyInterface) object).realmGet$timeLines();
        if (timeLinesList != null && timeLinesList.size() == timeLinesOsList.size()) {
            // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
            int objects = timeLinesList.size();
            for (int i = 0; i < objects; i++) {
                com.geogreenapps.apps.indukaan.classes.TimeLine timeLinesItem = timeLinesList.get(i);
                Long cacheItemIndextimeLines = cache.get(timeLinesItem);
                if (cacheItemIndextimeLines == null) {
                    cacheItemIndextimeLines = TimeLineRealmProxy.insertOrUpdate(realm, timeLinesItem, cache);
                }
                timeLinesOsList.setRow(i, cacheItemIndextimeLines);
            }
        } else {
            timeLinesOsList.removeAll();
            if (timeLinesList != null) {
                for (com.geogreenapps.apps.indukaan.classes.TimeLine timeLinesItem : timeLinesList) {
                    Long cacheItemIndextimeLines = cache.get(timeLinesItem);
                    if (cacheItemIndextimeLines == null) {
                        cacheItemIndextimeLines = TimeLineRealmProxy.insertOrUpdate(realm, timeLinesItem, cache);
                    }
                    timeLinesOsList.addRow(cacheItemIndextimeLines);
                }
            }
        }

        Table.nativeSetDouble(tableNativePtr, columnInfo.amountIndex, rowIndex, ((OrderRealmProxyInterface) object).realmGet$amount(), false);

        OsList variantsOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.variantsIndex);
        RealmList<com.geogreenapps.apps.indukaan.classes.Variant> variantsList = ((OrderRealmProxyInterface) object).realmGet$variants();
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

        String realmGet$extras = ((OrderRealmProxyInterface) object).realmGet$extras();
        if (realmGet$extras != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.extrasIndex, rowIndex, realmGet$extras, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.extrasIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.geogreenapps.apps.indukaan.classes.Order.class);
        long tableNativePtr = table.getNativePtr();
        OrderColumnInfo columnInfo = (OrderColumnInfo) realm.getSchema().getColumnInfo(com.geogreenapps.apps.indukaan.classes.Order.class);
        long pkColumnIndex = columnInfo.idIndex;
        com.geogreenapps.apps.indukaan.classes.Order object = null;
        while (objects.hasNext()) {
            object = (com.geogreenapps.apps.indukaan.classes.Order) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            long rowIndex = Table.NO_MATCH;
            Object primaryKeyValue = ((OrderRealmProxyInterface) object).realmGet$id();
            if (primaryKeyValue != null) {
                rowIndex = Table.nativeFindFirstInt(tableNativePtr, pkColumnIndex, ((OrderRealmProxyInterface) object).realmGet$id());
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, ((OrderRealmProxyInterface) object).realmGet$id());
            }
            cache.put(object, rowIndex);
            Table.nativeSetLong(tableNativePtr, columnInfo.user_idIndex, rowIndex, ((OrderRealmProxyInterface) object).realmGet$user_id(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.module_idIndex, rowIndex, ((OrderRealmProxyInterface) object).realmGet$module_id(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.id_storeIndex, rowIndex, ((OrderRealmProxyInterface) object).realmGet$id_store(), false);
            String realmGet$name = ((OrderRealmProxyInterface) object).realmGet$name();
            if (realmGet$name != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
            }
            Table.nativeSetLong(tableNativePtr, columnInfo.req_cf_idIndex, rowIndex, ((OrderRealmProxyInterface) object).realmGet$req_cf_id(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.delivery_statusIndex, rowIndex, ((OrderRealmProxyInterface) object).realmGet$delivery_status(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.delivery_idIndex, rowIndex, ((OrderRealmProxyInterface) object).realmGet$delivery_id(), false);
            Table.nativeSetLong(tableNativePtr, columnInfo.status_idIndex, rowIndex, ((OrderRealmProxyInterface) object).realmGet$status_id(), false);
            String realmGet$status = ((OrderRealmProxyInterface) object).realmGet$status();
            if (realmGet$status != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.statusIndex, rowIndex, realmGet$status, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.statusIndex, rowIndex, false);
            }
            String realmGet$module = ((OrderRealmProxyInterface) object).realmGet$module();
            if (realmGet$module != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.moduleIndex, rowIndex, realmGet$module, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.moduleIndex, rowIndex, false);
            }
            String realmGet$cart = ((OrderRealmProxyInterface) object).realmGet$cart();
            if (realmGet$cart != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.cartIndex, rowIndex, realmGet$cart, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.cartIndex, rowIndex, false);
            }
            String realmGet$req_cf_data = ((OrderRealmProxyInterface) object).realmGet$req_cf_data();
            if (realmGet$req_cf_data != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.req_cf_dataIndex, rowIndex, realmGet$req_cf_data, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.req_cf_dataIndex, rowIndex, false);
            }
            String realmGet$updated_at = ((OrderRealmProxyInterface) object).realmGet$updated_at();
            if (realmGet$updated_at != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.updated_atIndex, rowIndex, realmGet$updated_at, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.updated_atIndex, rowIndex, false);
            }
            String realmGet$created_at = ((OrderRealmProxyInterface) object).realmGet$created_at();
            if (realmGet$created_at != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.created_atIndex, rowIndex, realmGet$created_at, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.created_atIndex, rowIndex, false);
            }
            String realmGet$payment_status = ((OrderRealmProxyInterface) object).realmGet$payment_status();
            if (realmGet$payment_status != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.payment_statusIndex, rowIndex, realmGet$payment_status, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.payment_statusIndex, rowIndex, false);
            }
            String realmGet$commission = ((OrderRealmProxyInterface) object).realmGet$commission();
            if (realmGet$commission != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.commissionIndex, rowIndex, realmGet$commission, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.commissionIndex, rowIndex, false);
            }
            String realmGet$payment_status_data = ((OrderRealmProxyInterface) object).realmGet$payment_status_data();
            if (realmGet$payment_status_data != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.payment_status_dataIndex, rowIndex, realmGet$payment_status_data, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.payment_status_dataIndex, rowIndex, false);
            }

            OsList itemsOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.itemsIndex);
            RealmList<com.geogreenapps.apps.indukaan.classes.Item> itemsList = ((OrderRealmProxyInterface) object).realmGet$items();
            if (itemsList != null && itemsList.size() == itemsOsList.size()) {
                // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
                int objectCount = itemsList.size();
                for (int i = 0; i < objectCount; i++) {
                    com.geogreenapps.apps.indukaan.classes.Item itemsItem = itemsList.get(i);
                    Long cacheItemIndexitems = cache.get(itemsItem);
                    if (cacheItemIndexitems == null) {
                        cacheItemIndexitems = ItemRealmProxy.insertOrUpdate(realm, itemsItem, cache);
                    }
                    itemsOsList.setRow(i, cacheItemIndexitems);
                }
            } else {
                itemsOsList.removeAll();
                if (itemsList != null) {
                    for (com.geogreenapps.apps.indukaan.classes.Item itemsItem : itemsList) {
                        Long cacheItemIndexitems = cache.get(itemsItem);
                        if (cacheItemIndexitems == null) {
                            cacheItemIndexitems = ItemRealmProxy.insertOrUpdate(realm, itemsItem, cache);
                        }
                        itemsOsList.addRow(cacheItemIndexitems);
                    }
                }
            }


            OsList timeLinesOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.timeLinesIndex);
            RealmList<com.geogreenapps.apps.indukaan.classes.TimeLine> timeLinesList = ((OrderRealmProxyInterface) object).realmGet$timeLines();
            if (timeLinesList != null && timeLinesList.size() == timeLinesOsList.size()) {
                // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
                int objectCount = timeLinesList.size();
                for (int i = 0; i < objectCount; i++) {
                    com.geogreenapps.apps.indukaan.classes.TimeLine timeLinesItem = timeLinesList.get(i);
                    Long cacheItemIndextimeLines = cache.get(timeLinesItem);
                    if (cacheItemIndextimeLines == null) {
                        cacheItemIndextimeLines = TimeLineRealmProxy.insertOrUpdate(realm, timeLinesItem, cache);
                    }
                    timeLinesOsList.setRow(i, cacheItemIndextimeLines);
                }
            } else {
                timeLinesOsList.removeAll();
                if (timeLinesList != null) {
                    for (com.geogreenapps.apps.indukaan.classes.TimeLine timeLinesItem : timeLinesList) {
                        Long cacheItemIndextimeLines = cache.get(timeLinesItem);
                        if (cacheItemIndextimeLines == null) {
                            cacheItemIndextimeLines = TimeLineRealmProxy.insertOrUpdate(realm, timeLinesItem, cache);
                        }
                        timeLinesOsList.addRow(cacheItemIndextimeLines);
                    }
                }
            }

            Table.nativeSetDouble(tableNativePtr, columnInfo.amountIndex, rowIndex, ((OrderRealmProxyInterface) object).realmGet$amount(), false);

            OsList variantsOsList = new OsList(table.getUncheckedRow(rowIndex), columnInfo.variantsIndex);
            RealmList<com.geogreenapps.apps.indukaan.classes.Variant> variantsList = ((OrderRealmProxyInterface) object).realmGet$variants();
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

            String realmGet$extras = ((OrderRealmProxyInterface) object).realmGet$extras();
            if (realmGet$extras != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.extrasIndex, rowIndex, realmGet$extras, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.extrasIndex, rowIndex, false);
            }
        }
    }

    public static com.geogreenapps.apps.indukaan.classes.Order createDetachedCopy(com.geogreenapps.apps.indukaan.classes.Order realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.geogreenapps.apps.indukaan.classes.Order unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.geogreenapps.apps.indukaan.classes.Order();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.geogreenapps.apps.indukaan.classes.Order) cachedObject.object;
            }
            unmanagedObject = (com.geogreenapps.apps.indukaan.classes.Order) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        OrderRealmProxyInterface unmanagedCopy = (OrderRealmProxyInterface) unmanagedObject;
        OrderRealmProxyInterface realmSource = (OrderRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$id(realmSource.realmGet$id());
        unmanagedCopy.realmSet$user_id(realmSource.realmGet$user_id());
        unmanagedCopy.realmSet$module_id(realmSource.realmGet$module_id());
        unmanagedCopy.realmSet$id_store(realmSource.realmGet$id_store());
        unmanagedCopy.realmSet$name(realmSource.realmGet$name());
        unmanagedCopy.realmSet$req_cf_id(realmSource.realmGet$req_cf_id());
        unmanagedCopy.realmSet$delivery_status(realmSource.realmGet$delivery_status());
        unmanagedCopy.realmSet$delivery_id(realmSource.realmGet$delivery_id());
        unmanagedCopy.realmSet$status_id(realmSource.realmGet$status_id());
        unmanagedCopy.realmSet$status(realmSource.realmGet$status());
        unmanagedCopy.realmSet$module(realmSource.realmGet$module());
        unmanagedCopy.realmSet$cart(realmSource.realmGet$cart());
        unmanagedCopy.realmSet$req_cf_data(realmSource.realmGet$req_cf_data());
        unmanagedCopy.realmSet$updated_at(realmSource.realmGet$updated_at());
        unmanagedCopy.realmSet$created_at(realmSource.realmGet$created_at());
        unmanagedCopy.realmSet$payment_status(realmSource.realmGet$payment_status());
        unmanagedCopy.realmSet$commission(realmSource.realmGet$commission());
        unmanagedCopy.realmSet$payment_status_data(realmSource.realmGet$payment_status_data());

        // Deep copy of items
        if (currentDepth == maxDepth) {
            unmanagedCopy.realmSet$items(null);
        } else {
            RealmList<com.geogreenapps.apps.indukaan.classes.Item> manageditemsList = realmSource.realmGet$items();
            RealmList<com.geogreenapps.apps.indukaan.classes.Item> unmanageditemsList = new RealmList<com.geogreenapps.apps.indukaan.classes.Item>();
            unmanagedCopy.realmSet$items(unmanageditemsList);
            int nextDepth = currentDepth + 1;
            int size = manageditemsList.size();
            for (int i = 0; i < size; i++) {
                com.geogreenapps.apps.indukaan.classes.Item item = ItemRealmProxy.createDetachedCopy(manageditemsList.get(i), nextDepth, maxDepth, cache);
                unmanageditemsList.add(item);
            }
        }

        // Deep copy of timeLines
        if (currentDepth == maxDepth) {
            unmanagedCopy.realmSet$timeLines(null);
        } else {
            RealmList<com.geogreenapps.apps.indukaan.classes.TimeLine> managedtimeLinesList = realmSource.realmGet$timeLines();
            RealmList<com.geogreenapps.apps.indukaan.classes.TimeLine> unmanagedtimeLinesList = new RealmList<com.geogreenapps.apps.indukaan.classes.TimeLine>();
            unmanagedCopy.realmSet$timeLines(unmanagedtimeLinesList);
            int nextDepth = currentDepth + 1;
            int size = managedtimeLinesList.size();
            for (int i = 0; i < size; i++) {
                com.geogreenapps.apps.indukaan.classes.TimeLine item = TimeLineRealmProxy.createDetachedCopy(managedtimeLinesList.get(i), nextDepth, maxDepth, cache);
                unmanagedtimeLinesList.add(item);
            }
        }
        unmanagedCopy.realmSet$amount(realmSource.realmGet$amount());

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
        unmanagedCopy.realmSet$extras(realmSource.realmGet$extras());

        return unmanagedObject;
    }

    static com.geogreenapps.apps.indukaan.classes.Order update(Realm realm, com.geogreenapps.apps.indukaan.classes.Order realmObject, com.geogreenapps.apps.indukaan.classes.Order newObject, Map<RealmModel, RealmObjectProxy> cache) {
        OrderRealmProxyInterface realmObjectTarget = (OrderRealmProxyInterface) realmObject;
        OrderRealmProxyInterface realmObjectSource = (OrderRealmProxyInterface) newObject;
        realmObjectTarget.realmSet$user_id(realmObjectSource.realmGet$user_id());
        realmObjectTarget.realmSet$module_id(realmObjectSource.realmGet$module_id());
        realmObjectTarget.realmSet$id_store(realmObjectSource.realmGet$id_store());
        realmObjectTarget.realmSet$name(realmObjectSource.realmGet$name());
        realmObjectTarget.realmSet$req_cf_id(realmObjectSource.realmGet$req_cf_id());
        realmObjectTarget.realmSet$delivery_status(realmObjectSource.realmGet$delivery_status());
        realmObjectTarget.realmSet$delivery_id(realmObjectSource.realmGet$delivery_id());
        realmObjectTarget.realmSet$status_id(realmObjectSource.realmGet$status_id());
        realmObjectTarget.realmSet$status(realmObjectSource.realmGet$status());
        realmObjectTarget.realmSet$module(realmObjectSource.realmGet$module());
        realmObjectTarget.realmSet$cart(realmObjectSource.realmGet$cart());
        realmObjectTarget.realmSet$req_cf_data(realmObjectSource.realmGet$req_cf_data());
        realmObjectTarget.realmSet$updated_at(realmObjectSource.realmGet$updated_at());
        realmObjectTarget.realmSet$created_at(realmObjectSource.realmGet$created_at());
        realmObjectTarget.realmSet$payment_status(realmObjectSource.realmGet$payment_status());
        realmObjectTarget.realmSet$commission(realmObjectSource.realmGet$commission());
        realmObjectTarget.realmSet$payment_status_data(realmObjectSource.realmGet$payment_status_data());
        RealmList<com.geogreenapps.apps.indukaan.classes.Item> itemsList = realmObjectSource.realmGet$items();
        RealmList<com.geogreenapps.apps.indukaan.classes.Item> itemsRealmList = realmObjectTarget.realmGet$items();
        if (itemsList != null && itemsList.size() == itemsRealmList.size()) {
            // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
            int objects = itemsList.size();
            for (int i = 0; i < objects; i++) {
                com.geogreenapps.apps.indukaan.classes.Item itemsItem = itemsList.get(i);
                com.geogreenapps.apps.indukaan.classes.Item cacheitems = (com.geogreenapps.apps.indukaan.classes.Item) cache.get(itemsItem);
                if (cacheitems != null) {
                    itemsRealmList.set(i, cacheitems);
                } else {
                    itemsRealmList.set(i, ItemRealmProxy.copyOrUpdate(realm, itemsItem, true, cache));
                }
            }
        } else {
            itemsRealmList.clear();
            if (itemsList != null) {
                for (int i = 0; i < itemsList.size(); i++) {
                    com.geogreenapps.apps.indukaan.classes.Item itemsItem = itemsList.get(i);
                    com.geogreenapps.apps.indukaan.classes.Item cacheitems = (com.geogreenapps.apps.indukaan.classes.Item) cache.get(itemsItem);
                    if (cacheitems != null) {
                        itemsRealmList.add(cacheitems);
                    } else {
                        itemsRealmList.add(ItemRealmProxy.copyOrUpdate(realm, itemsItem, true, cache));
                    }
                }
            }
        }
        RealmList<com.geogreenapps.apps.indukaan.classes.TimeLine> timeLinesList = realmObjectSource.realmGet$timeLines();
        RealmList<com.geogreenapps.apps.indukaan.classes.TimeLine> timeLinesRealmList = realmObjectTarget.realmGet$timeLines();
        if (timeLinesList != null && timeLinesList.size() == timeLinesRealmList.size()) {
            // For lists of equal lengths, we need to set each element directly as clearing the receiver list can be wrong if the input and target list are the same.
            int objects = timeLinesList.size();
            for (int i = 0; i < objects; i++) {
                com.geogreenapps.apps.indukaan.classes.TimeLine timeLinesItem = timeLinesList.get(i);
                com.geogreenapps.apps.indukaan.classes.TimeLine cachetimeLines = (com.geogreenapps.apps.indukaan.classes.TimeLine) cache.get(timeLinesItem);
                if (cachetimeLines != null) {
                    timeLinesRealmList.set(i, cachetimeLines);
                } else {
                    timeLinesRealmList.set(i, TimeLineRealmProxy.copyOrUpdate(realm, timeLinesItem, true, cache));
                }
            }
        } else {
            timeLinesRealmList.clear();
            if (timeLinesList != null) {
                for (int i = 0; i < timeLinesList.size(); i++) {
                    com.geogreenapps.apps.indukaan.classes.TimeLine timeLinesItem = timeLinesList.get(i);
                    com.geogreenapps.apps.indukaan.classes.TimeLine cachetimeLines = (com.geogreenapps.apps.indukaan.classes.TimeLine) cache.get(timeLinesItem);
                    if (cachetimeLines != null) {
                        timeLinesRealmList.add(cachetimeLines);
                    } else {
                        timeLinesRealmList.add(TimeLineRealmProxy.copyOrUpdate(realm, timeLinesItem, true, cache));
                    }
                }
            }
        }
        realmObjectTarget.realmSet$amount(realmObjectSource.realmGet$amount());
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
        realmObjectTarget.realmSet$extras(realmObjectSource.realmGet$extras());
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Order = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{user_id:");
        stringBuilder.append(realmGet$user_id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{module_id:");
        stringBuilder.append(realmGet$module_id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{id_store:");
        stringBuilder.append(realmGet$id_store());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name() != null ? realmGet$name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{req_cf_id:");
        stringBuilder.append(realmGet$req_cf_id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{delivery_status:");
        stringBuilder.append(realmGet$delivery_status());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{delivery_id:");
        stringBuilder.append(realmGet$delivery_id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{status_id:");
        stringBuilder.append(realmGet$status_id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{status:");
        stringBuilder.append(realmGet$status() != null ? realmGet$status() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{module:");
        stringBuilder.append(realmGet$module() != null ? realmGet$module() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{cart:");
        stringBuilder.append(realmGet$cart() != null ? realmGet$cart() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{req_cf_data:");
        stringBuilder.append(realmGet$req_cf_data() != null ? realmGet$req_cf_data() : "null");
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
        stringBuilder.append("{payment_status:");
        stringBuilder.append(realmGet$payment_status() != null ? realmGet$payment_status() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{commission:");
        stringBuilder.append(realmGet$commission() != null ? realmGet$commission() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{payment_status_data:");
        stringBuilder.append(realmGet$payment_status_data() != null ? realmGet$payment_status_data() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{items:");
        stringBuilder.append("RealmList<Item>[").append(realmGet$items().size()).append("]");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{timeLines:");
        stringBuilder.append("RealmList<TimeLine>[").append(realmGet$timeLines().size()).append("]");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{amount:");
        stringBuilder.append(realmGet$amount());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{variants:");
        stringBuilder.append("RealmList<Variant>[").append(realmGet$variants().size()).append("]");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{extras:");
        stringBuilder.append(realmGet$extras() != null ? realmGet$extras() : "null");
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
        OrderRealmProxy aOrder = (OrderRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aOrder.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aOrder.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aOrder.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
