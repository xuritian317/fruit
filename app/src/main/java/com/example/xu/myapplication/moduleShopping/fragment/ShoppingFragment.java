package com.example.xu.myapplication.moduleShopping.fragment;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseMainFragment;
import com.example.xu.myapplication.moduleShopping.fragment.Bean.FruitBean;
import com.example.xu.myapplication.moduleShopping.fragment.activity.ShoppingPayActivity;
import com.example.xu.myapplication.moduleShopping.fragment.adapter.ShoppingCarAdater;
import com.example.xu.myapplication.moduleShopping.fragment.presenter.ShoppingPresenter;
import com.example.xu.myapplication.moduleShopping.fragment.viewInterface.IShopping;

import java.util.ArrayList;
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

    private List<FruitBean> lists;
    private ShoppingCarAdater adater;

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
    public void toPay(){
        presenter.toActivity(ShoppingPayActivity.class);
    }

    @BindView(R.id.linear_shopping1)
    public LinearLayout linearShopping1;
    @BindView(R.id.btn_shoppingDelete)
    public Button btnShoppingDelete;
    @BindView(R.id.linear_shopping2)
    public LinearLayout linearShopping2;

    private int a = 0;

    @Override
    public int getLayout() {
        return R.layout.fragment_shopping_main;
    }

    @Override
    public void setPresenter() {
        presenter=new ShoppingPresenter(this);
    }

    @Override
    public void initData() {
        cbEditor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (cbSelectAll.isChecked()) {
                        cbSelectAll.setChecked(false);
                    }
                    cbEditor.setText(R.string.finish);
                    linearShopping1.setVisibility(View.GONE);
                    linearShopping2.setVisibility(View.VISIBLE);
                } else {
                    if (cbSelectAll.isChecked()) {
                        cbSelectAll.setChecked(false);
                    }
                    cbEditor.setText(R.string.editor);
                    linearShopping1.setVisibility(View.VISIBLE);
                    linearShopping2.setVisibility(View.GONE);
                }
            }
        });

        cbSelectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    for (int i = 0; i < adater.map_cb.size(); i++) {
                        adater.map_cb.put(i, true);
                    }
                } else {
                    if (a==1){
                        for (int i = 0; i < adater.map_cb.size(); i++) {
                            adater.map_cb.put(i, false);
                        }
                    }
                }
                adater.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        lists = new ArrayList<FruitBean>();
        adater = new ShoppingCarAdater(this, getActivity());
        lvShopping.setAdapter(adater);
        lists.add(0, new FruitBean("苹果", 2.0, 2));
        lists.add(1, new FruitBean("香蕉", 3.0, 3));
        lists.add(2, new FruitBean("西瓜", 4.0, 1));
        lists.add(3, new FruitBean("葡萄", 3.0, 2));
        lists.add(4, new FruitBean("哈密瓜", 2.0, 1));
        lists.add(5, new FruitBean("荔枝", 5.0, 5));
        lists.add(6, new FruitBean("梨", 3.0, 2));
        lists.add(7, new FruitBean("火龙果", 4.0, 2));
        adater.setData(lists);
    }

    @Override
    public void setToolbar() {

    }

    /**
     * 计算选购商品总价格
     * @param isChecked 是否选中
     * @param price 商品单价
     * @param number 商品数量
     */
    public void UpView(boolean isChecked, double price, int number) {
        double sum = 0;
        int size = 0;
        for (int i = 0; i < adater.map_cb.size(); i++) {
            if (adater.map_cb.get(i)) {
                size++;
                sum += lists.get(i).getPrice() * lists.get(i).getNumber();
            }
        }
        adater.notifyDataSetChanged();

        tvShopingMoney.setText("￥" + sum);

        if (size == adater.map_cb.size()) {
            cbSelectAll.setChecked(true);
            a=1;
        } else {
            a=0;
            cbSelectAll.setChecked(false);
        }
    }

    @Override
    public Context getCon() {
        return getActivity();
    }

    @Override
    public void toStartActivity(Intent intent) {
        startActivity(intent);
    }
}
