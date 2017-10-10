package com.example.xu.myapplication.moduleMy.presenter;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.JsonResponseHandler;
import com.example.xu.myapplication.moduleMy.activity.orders.MyOrdersActivity;
import com.example.xu.myapplication.moduleMy.view.CircleImageView;
import com.example.xu.myapplication.moduleMy.viewInterface.IMy;
import com.example.xu.myapplication.util.BitmapUtil;
import com.example.xu.myapplication.util.SPUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


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

        if (TextUtils.equals(util.getString(SPUtil.IS_USER, ""), "")) {
            view.getAct().startActivity(new Intent(view.getCon(), cls0));
        } else {
            view.getAct().startActivity(new Intent(view.getCon(), cls1));
        }
    }
    /**
     * 根据用户手机号 获取用户所有信息
     *
     * @param refreshMy
     * @param ivMyHead       头像控件
     * @param myUserName
     * @param tvDaishou
     * @param tvEvaluate
     * @param tvTuikuan
     * @param tvDaishouHint
     * @param tvEvaluateHint
     */
    public void getUser(final SwipeRefreshLayout refreshMy, final CircleImageView ivMyHead,
                        final TextView myUserName, TextView tvDaishou, TextView tvEvaluate, TextView
                                tvTuikuan, final TextView tvDaishouHint, final TextView
                                tvEvaluateHint, final
                        TextView tvTuikuanHint) {

        String phone = util.getString(SPUtil.IS_USER, "");
        if (!refreshMy.isRefreshing()) {
            refreshMy.setRefreshing(true);
        }
        if (TextUtils.equals(util.getString(SPUtil.IS_USER, ""), "")) {
            ivMyHead.setImageDrawable(view.getCon().getResources().getDrawable(R.mipmap.iv_head));
            myUserName.setText(view.getCon().getResources().getString(R.string.login_register));
            if (refreshMy.isRefreshing()) {
                refreshMy.setRefreshing(false);
            }
            tvDaishouHint.setVisibility(View.GONE);
            tvEvaluateHint.setVisibility(View.GONE);
            tvTuikuanHint.setVisibility(View.GONE);
            return;
        }
        //刷新
//        if (!refreshMy.isRefreshing()){
//            refreshMy.setRefreshing(true);
//        }

        JSONObject jo = new JSONObject();
        try {
            jo.put("phoneNumber", phone);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MyOkHttp.newInstance().postJson(Common.URL_GET_USER, jo, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                try {
                    String nickName = response.getString("nickName");
                    String headImage = response.getString("headImage");
                    String orders = response.getString("orders");
                    //昵称
                    if (TextUtils.equals(nickName, "null")) {
                        myUserName.setVisibility(View.GONE);
                    } else {
                        myUserName.setVisibility(View.VISIBLE);
                        myUserName.setText(nickName);
                    }

                    //头像
                    if (TextUtils.equals(headImage, "null")) {
                        ivMyHead.setImageDrawable(view.getCon().getResources().getDrawable(R
                                .mipmap.iv_head));
                    } else {
                        ivMyHead.setImageBitmap(BitmapUtil.getBitmapFromBase64(headImage));
                    }
                    //订单
                    JSONArray array = new JSONArray(orders);
                    JSONObject jo;
                    int daishou = 0;
                    int pingjia = 0;
                    int tuikuan = 0;
                    for (int i = 0; i < array.length(); i++) {
                        jo = array.getJSONObject(i);
                        if (jo.getInt("orderState") == 1) {
                            daishou++;
                        } else if (jo.getInt("orderState") == 2) {
                            tuikuan++;
                        } else if (jo.getInt("orderState") == 0 && jo.getInt("reviewState") == 1) {
                            pingjia++;
                        }
                    }
                    if (daishou > 0) {
                        tvDaishouHint.setVisibility(View.VISIBLE);
                        tvDaishouHint.setText(String.valueOf(daishou));
                    } else {
                        tvDaishouHint.setVisibility(View.GONE);
                    }
                    if (pingjia > 0) {
                        tvEvaluateHint.setVisibility(View.VISIBLE);
                        tvEvaluateHint.setText(String.valueOf(pingjia));
                    } else {
                        tvEvaluateHint.setVisibility(View.GONE);
                    }
                    if (tuikuan > 0) {
                        tvTuikuanHint.setVisibility(View.VISIBLE);
                        tvTuikuanHint.setText(String.valueOf(tuikuan));
                    } else {
                        tvTuikuanHint.setVisibility(View.GONE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (refreshMy.isRefreshing()) {
                    refreshMy.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {

            }
        });
    }
}
