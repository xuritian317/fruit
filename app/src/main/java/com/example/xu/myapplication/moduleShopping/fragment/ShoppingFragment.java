package com.example.xu.myapplication.moduleShopping.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseMainFragment;
import com.example.xu.myapplication.moduleShopping.fragment.bean.FruitBean;
import com.example.xu.myapplication.moduleShopping.fragment.activity.ShoppingPayActivity;
import com.example.xu.myapplication.moduleShopping.fragment.adapter.ShoppingCarAdapter;
import com.example.xu.myapplication.moduleShopping.fragment.presenter.ShoppingPresenter;
import com.example.xu.myapplication.moduleShopping.fragment.viewInterface.IShopping;

import net.lemonsoft.lemonhello.LemonHello;
import net.lemonsoft.lemonhello.LemonHelloAction;
import net.lemonsoft.lemonhello.LemonHelloInfo;
import net.lemonsoft.lemonhello.LemonHelloView;
import net.lemonsoft.lemonhello.interfaces.LemonHelloActionDelegate;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingFragment extends BaseMainFragment<ShoppingPresenter> implements IShopping {

    private static ShoppingFragment instance;

    public static ShoppingFragment newInstance() {
        if (instance == null)
            instance = new ShoppingFragment();
        return instance;
    }

    private ShoppingCarAdapter adapter;
    private List<FruitBean> lists;

    @BindView(R.id.tv_shopingCart)
    TextView tvShopingCart;
    @BindView(R.id.cb_editor)
    public CheckBox cbEditor;
    @BindView(R.id.lv_shopping)
    public ListView lvShopping;
    @BindView(R.id.cb_selectAll)
    public CheckBox cbSelectAll;
    @BindView(R.id.tv_shopingMoney)
    public TextView tvShopingMoney;
    @BindView(R.id.btn_shopingAccounts)
    public Button btnShopingAccounts;

    @OnClick(R.id.btn_shopingAccounts)
    public void toPay() {
        presenter.toActivity(ShoppingPayActivity.class);
    }

    @BindView(R.id.linear_shopping1)
    public LinearLayout linearShopping1;
    @BindView(R.id.btn_shoppingDelete)
    public Button btnShoppingDelete;

    @OnClick(R.id.btn_shoppingDelete)
    public void shoppingDelete() {
        dialog_delete();
    }

    @BindView(R.id.linear_shopping2)
    public LinearLayout linearShopping2;

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
        adapter = new ShoppingCarAdapter(this, getActivity());
        lvShopping.setAdapter(adapter);
        adapter.setData(presenter.addList(tvShopingCart));
    }

    @Override
    public void initView(Bundle savedInstanceState) {

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
                        presenter.deleteGoods(adapter, cbSelectAll);
                        //dialog隐藏
                        helloView.hide();
                    }
                }))

                .show(getActivity());
    }

    @Override
    public Context getCon() {
        return getActivity();
    }

    @Override
    public Activity getAct() {
        return getActivity();
    }
}
