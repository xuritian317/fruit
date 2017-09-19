package com.example.xu.myapplication.moduleMy.fragment.activity.orders;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseActivity;
import com.example.xu.myapplication.moduleMy.fragment.adapter.FrgmentAdapter;
import com.example.xu.myapplication.moduleMy.fragment.presenter.OrdersPresenter;
import com.example.xu.myapplication.moduleMy.fragment.viewInterface.IOrders;
import com.example.xu.myapplication.util.Logger;

import butterknife.BindView;

public class MyOrdersActivity extends BaseActivity<OrdersPresenter> implements IOrders {

    @BindView(R.id.tab_orders)
    TabLayout tabOrders;
    @BindView(R.id.vp_orders)
    ViewPager vpOrders;

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
        tabOrders.addTab(tabOrders.newTab().setText(R.string.tuikuan));

        vpOrders.setAdapter(new FrgmentAdapter(getSupportFragmentManager(),
                getString(R.string.dingdan), getString(R.string.daishouhuo),
                getString(R.string.yiwancheng), getString(R.string.tuikuan)));
        tabOrders.setupWithViewPager(vpOrders);

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        index = this.getIntent().getIntExtra("order", 0);
        Logger.e("index", index + "");

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
