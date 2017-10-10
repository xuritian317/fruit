package com.example.xu.myapplication.moduleMy.activity.orders;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseActivity;
import com.example.xu.myapplication.moduleMy.adapter.FragmentAdapter;
import com.example.xu.myapplication.moduleMy.presenter.OrdersPresenter;
import com.example.xu.myapplication.moduleMy.viewInterface.IOrders;

import butterknife.BindView;
import butterknife.OnClick;

public class MyOrdersActivity extends BaseActivity<OrdersPresenter> implements IOrders {

    @BindView(R.id.tab_orders)
    TabLayout tabOrders;
    @BindView(R.id.vp_orders)
    ViewPager vpOrders;

    @BindView(R.id.iv_orders_back)
    ImageView ivOrdersBack;
    @OnClick(R.id.iv_orders_back)
    public void back(){
        finish();
    }
    private int index;

    @Override
    public void setPresenter() {
    }

    @Override
    public int getLayout() {
        return R.layout.activity_my_orders;
    }

    @Override
    public void initData() {
        tabOrders.addTab(tabOrders.newTab().setText(R.string.dingdan));
        tabOrders.addTab(tabOrders.newTab().setText(R.string.daishouhuo));
        tabOrders.addTab(tabOrders.newTab().setText(R.string.yiwancheng));
        tabOrders.addTab(tabOrders.newTab().setText(R.string.evaluate));
        tabOrders.addTab(tabOrders.newTab().setText(R.string.tuikuan));

        vpOrders.setAdapter(new FragmentAdapter(getSupportFragmentManager(),
                getString(R.string.dingdan), getString(R.string.daishouhuo),
                getString(R.string.yiwancheng), getString(R.string.evaluate),
                getString(R.string.tuikuan)));
        tabOrders.setupWithViewPager(vpOrders);

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        index = this.getIntent().getIntExtra("order", 0);
        //设置第几个tab被选中
        tabOrders.getTabAt(index).select();
    }

    @Override
    public void setToolbar() {

    }

    @Override
    public Context getCon() {
        return MyOrdersActivity.this;
    }

    @Override
    public Activity getAct() {
        return MyOrdersActivity.this;
    }
}
