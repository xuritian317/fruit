package com.example.xu.myapplication.moduleMy.fragment.presenter;

import android.content.Intent;

import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.moduleMy.fragment.activity.orders.MyOrdersActivity;
import com.example.xu.myapplication.moduleMy.fragment.viewInterface.IMy;

/**
 * Created by 逝 on 2017/09/18.
 */

public class MyPresenter extends BasePresenter {
    private IMy view;

    public MyPresenter(IMy view) {
        this.view = view;
    }

    /**
     * 跳转到 MyOrdersActivity
     * @param value 对应MyOrdersActivity中第几个fragment
     */
    public void toMyOrdersActivity(int value) {
        Intent intent = new Intent(view.getCon(), MyOrdersActivity.class);
        intent.putExtra("order", value);
        view.toStartActivity(intent);
    }

    /**
     *跳转Activity
     */
    public void toActivity(Class<?> cls){
        Intent intent = new Intent(view.getCon(), cls);
        view.toStartActivity(intent);
    }
}
