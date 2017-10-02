package com.example.xu.myapplication.moduleMy.fragment.presenter;

import android.content.Intent;

import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.moduleMy.fragment.activity.orders.MyOrdersActivity;
import com.example.xu.myapplication.moduleMy.fragment.viewInterface.IMy;
import com.example.xu.myapplication.util.SPUtil;

/**
 * Created by 逝 on 2017/09/18.
 */

public class MyPresenter extends BasePresenter {
    private IMy view;
    private SPUtil util;

    public MyPresenter(IMy view) {
        this.view = view;
        util = new SPUtil(this.view.getCon());
    }

    /**
     * 跳转到 MyOrdersActivity
     *
     * @param value 对应MyOrdersActivity中第几个fragment
     */
    public void toMyOrdersActivity(int value) {
        Intent intent = new Intent(view.getCon(), MyOrdersActivity.class);
        intent.putExtra("order", value);
        view.getAct().startActivity(intent);
    }

    /**
     * 跳转Activity
     */
    public void toActivity(Class<?> cls0, Class<?> cls1) {
        int id;
        if (cls1 == null) {
            view.getAct().startActivity(new Intent(view.getCon(), cls0));
        } else {
            id=util.getInt("isUser",0);
            switch (id){
                case 0:
                    view.getAct().startActivity(new Intent(view.getCon(), cls0));
                    break;
                case 1:
                    view.getAct().startActivity(new Intent(view.getCon(), cls1));
                    break;
            }

        }
    }
}
