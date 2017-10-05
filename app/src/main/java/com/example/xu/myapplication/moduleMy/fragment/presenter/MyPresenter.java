package com.example.xu.myapplication.moduleMy.fragment.presenter;

import android.content.Intent;
import android.view.Gravity;
import android.widget.TextView;

import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.GsonResponseHandler;
import com.example.xu.myapplication.moduleMy.fragment.activity.orders.MyOrdersActivity;
import com.example.xu.myapplication.moduleMy.fragment.bean.OrdersBean;
import com.example.xu.myapplication.moduleMy.fragment.viewInterface.IMy;
import com.example.xu.myapplication.util.SPUtil;

import java.util.ArrayList;

import q.rorbin.badgeview.QBadgeView;

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
        if (cls1 == null) {
            view.getAct().startActivity(new Intent(view.getCon(), cls0));
            return;
        }

        if (util.getInt(SPUtil.IS_USER, 0) == 0) {
            view.getAct().startActivity(new Intent(view.getCon(), cls0));
        } else {
            view.getAct().startActivity(new Intent(view.getCon(), cls1));
        }
    }

    /**
     * 获取不同类别订单的数量
     *
     * @param tv1 需要显示的右上角角标的控件
     * @param tv2 ..
     * @param tv3 ..
     */
    public void getOrders(final TextView tv1, final TextView tv2, final TextView tv3) {
        MyOkHttp.newInstance().get(Common.URL_GET_ORDERS, null,
                new GsonResponseHandler<ArrayList<OrdersBean>>() {

                    @Override
                    public void onSuccess(int statusCode, ArrayList<OrdersBean> response) {
                        int daishou = 0;
                        int pingjia = 0;
                        int tuikuan = 0;
                        for (int i = 0; i < response.size(); i++) {
                            if (response.get(i).getOrderState() == 1) {
                                daishou++;
                            } else if (response.get(i).getOrderState() == 2) {
                                tuikuan++;
                            } else if (response.get(i).getOrderState() == 0 && response.get(i)
                                    .getReviewState() == 1) {
                                pingjia++;
                            }
                        }
                        showBadgeView(tv1, daishou);
                        showBadgeView(tv2, pingjia);
                        showBadgeView(tv3, tuikuan);
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });

    }


    /**
     * 显示BadgeView 标记
     *
     * @param tv
     * @param badgeNumber
     */
    private void showBadgeView(TextView tv, int badgeNumber) {
        if (badgeNumber == 0) {
            return;
        }
        QBadgeView badge = new QBadgeView(view.getCon());
        badge.bindTarget(tv);
        badge.setBadgeGravity(Gravity.END | Gravity.TOP);
        badge.setBadgeTextColor(view.getCon().getResources().getColor(R.color.color_White));
        badge.setBadgeBackgroundColor(view.getCon().getResources().getColor(R.color.blue));
        badge.setBadgeNumber(badgeNumber);
        badge.setBadgePadding(1, false);
    }
}
