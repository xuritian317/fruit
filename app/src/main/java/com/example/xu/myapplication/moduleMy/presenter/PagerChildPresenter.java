package com.example.xu.myapplication.moduleMy.presenter;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.JsonResponseHandler;
import com.example.xu.myapplication.moduleMy.activity.orders.MyOrdersActivity;
import com.example.xu.myapplication.moduleMy.adapter.PagerAdapter;
import com.example.xu.myapplication.moduleMy.bean.OrdersBean;
import com.example.xu.myapplication.moduleMy.viewInterface.IPagerChild;
import com.example.xu.myapplication.util.SPUtil;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 逝 on 2017/09/18.
 */

public class PagerChildPresenter extends BasePresenter {
    private IPagerChild view;
    private SPUtil util;

    private List<OrdersBean> objects = new ArrayList<OrdersBean>();
    private List<OrdersBean> lists = new ArrayList<OrdersBean>();

    public PagerChildPresenter(IPagerChild view) {
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
     *  @param adapter      填充数据 adapter
     * @param mFrom        根据没mFrom的值 分类订单
     * @param lvItemOrders
     * @param tvChildTishi
     * @param refreshOrders
     */
    public void getOrders(final PagerAdapter adapter, final int mFrom, final ListView lvItemOrders,
                          final TextView tvChildTishi, final SwipeRefreshLayout refreshOrders) {
        objects.clear();
        lists.clear();
        String phone = util.getString(SPUtil.IS_USER, "");
        if (TextUtils.equals(util.getString(SPUtil.IS_USER, ""), "")) {
            if (refreshOrders.isRefreshing()){
                refreshOrders.setRefreshing(false);
            }
            return;
        }

        if (!refreshOrders.isRefreshing()){
            refreshOrders.setRefreshing(true);
        }

        JSONObject jo = new JSONObject();
        try {
            jo.put("phoneNumber", phone);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MyOkHttp.newInstance().postJson(Common.URL_GET_USER, jo, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                Gson gson = new Gson();
                OrdersBean bean = null;
                try {
                    String orders = response.getString("orders");
                    JSONArray array = new JSONArray(orders);
                    for (int i = 0; i < array.length(); i++) {
                        bean = gson.fromJson(array.getJSONObject(i).toString(), OrdersBean.class);
                        objects.add(bean);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                switch (mFrom) {
                    case 0:
                        lists.addAll(objects);
                        break;
                    case 1:
                        for (int i = 0; i < objects.size(); i++) {
                            if (objects.get(i).getOrderState() == 1) {
                                lists.add(objects.get(i));
                            }
                        }
                        break;
                    case 2:
                        for (int i = 0; i < objects.size(); i++) {
                            if (objects.get(i).getOrderState() == 3) {
                                lists.add(objects.get(i));
                            }
                        }
                        break;
                    case 3:
                        for (int i = 0; i < objects.size(); i++) {
                            if (objects.get(i).getOrderState() == 0) {
                                lists.add(objects.get(i));
                            }
                        }
                        break;
                    case 4:
                        for (int i = 0; i < objects.size(); i++) {
                            if (objects.get(i).getOrderState() == 0 && objects.get(i)
                                    .getReviewState() == 1) {
                                lists.add(objects.get(i));
                            }
                        }
                        break;
                    case 5:
                        for (int i = 0; i < objects.size(); i++) {
                            if (objects.get(i).getOrderState() == 2) {
                                lists.add(objects.get(i));
                            }
                        }
                        break;
                }

                if (lists.size() != 0) {
                    lvItemOrders.setVisibility(View.VISIBLE);
                } else {
                    tvChildTishi.setVisibility(View.VISIBLE);
                }
                adapter.setDatas(lists);

                if (refreshOrders.isRefreshing()){
                    refreshOrders.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {

            }
        });

    }
}
