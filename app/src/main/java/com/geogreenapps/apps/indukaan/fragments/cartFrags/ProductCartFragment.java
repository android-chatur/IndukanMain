package com.geogreenapps.apps.indukaan.fragments.cartFrags;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.activities.OrderCheckoutActivity;
import com.geogreenapps.apps.indukaan.adapter.lists.CartItemsAdapter;
import com.geogreenapps.apps.indukaan.classes.Cart;
import com.geogreenapps.apps.indukaan.controllers.cart.CartController;
import com.geogreenapps.apps.indukaan.controllers.sessions.SessionsController;
import com.geogreenapps.apps.indukaan.load_manager.ViewManager;
import com.geogreenapps.apps.indukaan.utils.BadgeNotificationUtils;
import com.geogreenapps.apps.indukaan.utils.ProductUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;


public class ProductCartFragment extends Fragment implements CartItemsAdapter.ClickListener, SwipeRefreshLayout.OnRefreshListener, ViewManager.CustomView {


    @BindView(R.id.list)
    RecyclerView list;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.layout_custom_order)
    LinearLayout layout_custom_order;

    @BindView(R.id.product_type)
    TextView product_type;
    @BindView(R.id.product_value)
    TextView product_value;


    @BindView(R.id.btn_custom_order)
    AppCompatButton btn_custom_order;

    @OnClick(R.id.btn_custom_order)
    public void submit(View view) {
        Intent intent = new Intent(new Intent(getActivity(), OrderCheckoutActivity.class));
        intent.putExtra("fromCart", "1");
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.lefttoright_enter, R.anim.lefttoright_exit);
    }


    private Context mContext;
    private CartItemsAdapter adapter;
    private ViewManager mViewManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_cart_items, container, false);
        mContext = root.getContext();
        ButterKnife.bind(this, root);

        setupViewManager(root);

        setupSwipeToRefresh();

        setupAdapter();


        return root;

    }

    @Override
    public void onStart() {

        getCartFromDatabase();

        //update badge counter
        updateBadges();


        super.onStart();
    }


    private void setupAdapter() {
        list.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        list.setLayoutManager(mLayoutManager);

        adapter = new CartItemsAdapter(mContext, new ArrayList<>());

        list.setAdapter(adapter);

        adapter.setClickListener(this);

    }

    public List<Cart> getCartFromDatabase() {

        List<Cart> results = new ArrayList<>();
        List<Cart> listCart = null;

        if (SessionsController.isLogged()) {
            listCart = Realm.getDefaultInstance().copyFromRealm(CartController.listProducts(SessionsController.getSession().getUser().getId()));
        }

        adapter.removeAll();
        if (listCart != null && listCart.size() > 0) {
            adapter.addAll(listCart);
            mViewManager.showResult();
            layout_custom_order.setVisibility(View.VISIBLE);
        } else {
            mViewManager.empty();
            layout_custom_order.setVisibility(View.GONE);
        }

        product_value.setText(String.format(ProductUtils.parseCurrencyFormat(
                (float) adapter.getTotalPrice(),
                adapter.getCurrency())));

        refresh.setRefreshing(false);


        return results;
    }

    private void setupSwipeToRefresh() {
        refresh.setOnRefreshListener(this);

        refresh.setColorSchemeResources(
                R.color.colorAccent,
                R.color.colorAccent,
                R.color.colorAccent,
                R.color.colorAccent
        );
    }

    @Override
    public void onDeleteItemClick(View view, int pos) {
        refresh.setRefreshing(true);
        if (adapter.removeItem(pos)) {
            if (adapter.getItemCount() == 0)
                mViewManager.empty();

            refresh.setRefreshing(false);

        }

    }

    private void updateBadges() {
        BadgeNotificationUtils.updateCartItemsBadge(getActivity());
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }


    @Override
    public void onRefresh() {
        getCartFromDatabase();
    }


    private void setupViewManager(View view) {
        mViewManager = new ViewManager(mContext);
        mViewManager.setLoadingLayout(view.findViewById(R.id.loading));
        mViewManager.setResultLayout(view.findViewById(R.id.container));
        mViewManager.setErrorLayout(view.findViewById(R.id.error));
        mViewManager.setEmpty(view.findViewById(R.id.empty));
        mViewManager.setCustumizeView(this);
    }

    @Override
    public void customErrorView(View v) {

        Button retry = v.findViewById(R.id.btn);

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewManager.loading();
                getCartFromDatabase();
            }
        });

    }

    @Override
    public void customLoadingView(View v) {
    }

    @Override
    public void customEmptyView(View v) {

        v.findViewById(R.id.NO_RESULT).setVisibility(View.GONE);
        TextView text = v.findViewById(R.id.brief);
        text.setText(getString(R.string.not_item_on_cart));

        Button btn = v.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCartFromDatabase();
            }
        });

    }
}
