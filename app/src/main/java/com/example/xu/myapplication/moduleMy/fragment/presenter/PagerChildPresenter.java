package com.example.xu.myapplication.moduleMy.fragment.presenter;

import android.content.Intent;

import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.GsonResponseHandler;
import com.example.xu.myapplication.moduleMy.fragment.activity.orders.MyOrdersActivity;
import com.example.xu.myapplication.moduleMy.fragment.adapter.PagerAdapter;
import com.example.xu.myapplication.moduleMy.fragment.bean.OrdersBean;
import com.example.xu.myapplication.moduleMy.fragment.viewInterface.IPagerChild;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 逝 on 2017/09/18.
 */

public class PagerChildPresenter extends BasePresenter {
    private IPagerChild view;

    private List<OrdersBean> lists = new ArrayList<OrdersBean>();

    public PagerChildPresenter(IPagerChild view) {
        this.view = view;
    }

    /**
     * 跳转到 MyOrdersActivity
     *
     * @param value 对应MyOrdersActivity中第几个fragment
     */
    public void toMyOrdersActivity(int value) {
        Intent intent = new Intent(view.getCon(), MyOrdersActivity.class);
        intent.putExtra("order", value);
        view.toStartActivity(intent);
    }

    /**
     * 跳转Activity
     */
    public void toActivity(Class<?> cls) {
        Intent intent = new Intent(view.getCon(), cls);
        view.toStartActivity(intent);
    }

    /**
     * 获取订单数据
     * @param adapter 填充数据 adapter
     * @param mFrom  根据没mFrom的值 分类订单
     */
    public void getOrders(final PagerAdapter adapter, final int mFrom) {

        MyOkHttp.newInstance().get(Common.URL_GET_ORDERS, null,
                new GsonResponseHandler<ArrayList<OrdersBean>>() {

                    @Override
                    public void onSuccess(int statusCode, ArrayList<OrdersBean> objects) {
                        lists.clear();
                        switch (mFrom) {
                            case 0:
                                adapter.setDatas(objects);
                                break;
                            case 1:
                                for (int i = 0; i < objects.size(); i++) {
                                    if (objects.get(i).getOrderState() == 1) {
                                        lists.add(objects.get(i));
                                    }
                                }
                                adapter.setDatas(lists);
                                break;
                            case 2:
                                for (int i = 0; i < objects.size(); i++) {
                                    if (objects.get(i).getOrderState() == 0) {
                                        lists.add(objects.get(i));
                                    }
                                }
                                adapter.setDatas(lists);
                                break;
                            case 3:
                                for (int i = 0; i < objects.size(); i++) {
                                    if (objects.get(i).getOrderState() == 0 && objects.get(i)
                                            .getReviewState() ==
                                            1) {
                                        lists.add(objects.get(i));
                                    }
                                }
                                adapter.setDatas(lists);
                                break;
                            case 4:
                                for (int i = 0; i < objects.size(); i++) {
                                    if (objects.get(i).getOrderState() == 2) {
                                        lists.add(objects.get(i));
                                    }
                                }
                                adapter.setDatas(lists);
                                break;
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });
    }
}
