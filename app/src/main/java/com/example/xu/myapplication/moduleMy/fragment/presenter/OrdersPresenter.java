package com.example.xu.myapplication.moduleMy.fragment.presenter;

import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.moduleMy.fragment.viewInterface.IOrders;

/**
 * Created by 逝 on 2017/09/19.
 */

public class OrdersPresenter extends BasePresenter {
    private IOrders iOrders;

    public OrdersPresenter(IOrders iOrders) {
        this.iOrders = iOrders;
    }
}
