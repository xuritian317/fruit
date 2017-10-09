package com.example.xu.myapplication.moduleShopping.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseFragment;
import com.example.xu.myapplication.moduleActivity.main.eventbus.TabSelectedEvent;
import com.example.xu.myapplication.moduleShopping.fragment.activity.ShoppingPayActivity;
import com.example.xu.myapplication.moduleShopping.fragment.adapter.ShoppingCarAdapter;
import com.example.xu.myapplication.moduleShopping.fragment.bean.FruitBean;
import com.example.xu.myapplication.moduleShopping.fragment.presenter.ShoppingPresenter;
import com.example.xu.myapplication.moduleShopping.fragment.viewInterface.IShopping;
import com.example.xu.myapplication.util.SPUtil;
import com.example.xu.myapplication.util.ToastUtils;

import net.lemonsoft.lemonhello.LemonHello;
import net.lemonsoft.lemonhello.LemonHelloAction;
import net.lemonsoft.lemonhello.LemonHelloInfo;
import net.lemonsoft.lemonhello.LemonHelloView;
import net.lemonsoft.lemonhello.interfaces.LemonHelloActionDelegate;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingContentFragment extends BaseFragment<ShoppingPresenter> implements IShopping, SwipeRefreshLayout.OnRefreshListener {

    private static ShoppingContentFragment instance;

    public static ShoppingContentFragment newInstance() {
        if (instance == null)
            instance = new ShoppingContentFragment();
        return instance;
    }

    private ShoppingCarAdapter adapter;
    @BindView(R.id.tv_shoppingCart)
    TextView tvShoppingCart;
    @BindView(R.id.cb_editor)
    CheckBox cbEditor;
    @BindView(R.id.refresh_shoppingCar)
    SwipeRefreshLayout refreshShoppingCar;
    @BindView(R.id.lv_shopping)
    ListView lvShopping;
    @BindView(R.id.cb_selectAll)
    CheckBox cbSelectAll;
    @BindView(R.id.tv_shopingMoney)
    TextView tvShopingMoney;
    @BindView(R.id.btn_shopingAccounts)
    Button btnShopingAccounts;

    @OnClick(R.id.btn_shopingAccounts)
    public void toPay() {
        presenter.toActivity(ShoppingPayActivity.class, tvShopingMoney);
    }

    @BindView(R.id.linear_shopping1)
    LinearLayout linearShopping1;
    @BindView(R.id.btn_shoppingDelete)
    Button btnShoppingDelete;

    @OnClick(R.id.btn_shoppingDelete)
    public void shoppingDelete() {
        dialog_delete();
    }

    @BindView(R.id.linear_shopping2)
    LinearLayout linearShopping2;

    @Override
    public int getLayout() {
        return R.layout.fragment_shopping_main;
    }

    @Override
    public void setPresenter() {
        presenter = new ShoppingPresenter(this);
    }

    @Override
    public void initData() {
        adapter = new ShoppingCarAdapter(ShoppingContentFragment.this, getActivity(), new SPUtil(getActivity()).getString
                (SPUtil.USER_ID, ""));
        lvShopping.setAdapter(adapter);

        EventBus.getDefault().register(this);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        refreshShoppingCar.setOnRefreshListener(this);

        cbEditor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                presenter.cbEditorChanged(cbEditor, cbSelectAll, isChecked, linearShopping1,
                        linearShopping2);
            }
        });

        cbSelectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                presenter.cbSelectAllChanged(isChecked);
                adapter.notifyDataSetChanged();
            }
        });

//        refreshShoppingCar.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//            }
//        });

    }

    @Override
    public void setToolbar() {

    }

    /**
     * 计算选购商品总价格
     */
    public void UpView() {
        presenter.UpdataSum(tvShopingMoney, cbSelectAll);
        adapter.notifyDataSetChanged();

        if (cbEditor.isChecked()) {
            tvShopingMoney.setText("￥0.00");
        }
    }

    /**
     * 删除界面Dialog显示
     */
    private void dialog_delete() {
        if (presenter.getGoodsNum() == 0) {
            ToastUtils.showToast(getActivity(), "需要选择商品哦");
            return;
        }
        LemonHello.getErrorHello(null, "确定删除这" + presenter.getGoodsNum() + "种商品吗？")
                .setContentFontSize(18)
                .setWidth(300)
                .addAction(new LemonHelloAction("取消", new LemonHelloActionDelegate() {
                    @Override
                    public void onClick(LemonHelloView helloView, LemonHelloInfo
                            helloInfo, LemonHelloAction helloAction) {
                        //dialog隐藏
                        helloView.hide();
                    }
                }))
                .addAction(new LemonHelloAction("确定", new LemonHelloActionDelegate() {
                    @Override
                    public void onClick(LemonHelloView helloView, LemonHelloInfo
                            helloInfo, LemonHelloAction helloAction) {
                        //删除并更新列表
                        presenter.deleteGoods(adapter, refreshShoppingCar, tvShoppingCart,
                                cbSelectAll, tvShopingMoney);
                        //dialog隐藏

                        helloView.hide();
                    }
                }))
                .show(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.addList(adapter, refreshShoppingCar, tvShoppingCart, cbSelectAll, tvShopingMoney);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public Context getCon() {
        return getActivity();
    }

    @Override
    public Activity getAct() {
        return getActivity();
    }

    @Override
    public void onRefresh() {
        presenter.addList(adapter, refreshShoppingCar, tvShoppingCart, cbSelectAll, tvShopingMoney);
    }

    /**
     * 选择tab事件
     */
    @Subscribe
    public void onTabSelectedEvent(TabSelectedEvent event) {
        if (event.position != 2) return;
        refreshShoppingCar.setRefreshing(true);
        onRefresh();
    }
}
