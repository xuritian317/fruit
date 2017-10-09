package com.example.xu.myapplication.moduleShopping.fragment.presenter;

import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.GsonResponseHandler;
import com.example.xu.myapplication.httpRequest.response.RawResponseHandler;
import com.example.xu.myapplication.moduleMy.fragment.bean.ReceiveAddressBean;
import com.example.xu.myapplication.moduleShopping.fragment.bean.FruitBean;
import com.example.xu.myapplication.moduleShopping.fragment.viewInterface.IShoppingPay;
import com.example.xu.myapplication.util.Logger;
import com.example.xu.myapplication.util.SPUtil;
import com.example.xu.myapplication.util.ToastUtils;

import net.lemonsoft.lemonhello.LemonHello;
import net.lemonsoft.lemonhello.LemonHelloAction;
import net.lemonsoft.lemonhello.LemonHelloInfo;
import net.lemonsoft.lemonhello.LemonHelloView;
import net.lemonsoft.lemonhello.interfaces.LemonHelloActionDelegate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;

/**
 * Created by 逝 on 2017/09/18.
 */

public class ShoppingPayPresenter extends BasePresenter {
    private IShoppingPay view;
    private SPUtil util;
    private List<ReceiveAddressBean> lists = new ArrayList<ReceiveAddressBean>();


    public ShoppingPayPresenter(IShoppingPay view) {
        this.view = view;
        util = new SPUtil(this.view.getCon());
    }

    /**
     * 跳转Activity
     */
    public void toActivity(Class<?> cls) {
        if (TextUtils.equals("", util.getString(SPUtil.IS_USER, ""))) {
            ToastUtils.showToast(view.getCon(), "请先登录");
            return;
        }
        if (lists.size() == 0) {
            ToastUtils.showToast(view.getCon(), "请先添加地址哦");
            return;
        }
        Intent intent = new Intent(view.getCon(), cls);
        view.getAct().startActivityForResult(intent, 1);
    }

    /**
     * 获取地址
     *
     * @param tvPayUser    显示收货人 手机号
     * @param tvPayAddress 显示收货地址
     * @param address_id
     */
    public void getAddress(final TextView tvPayUser, final TextView tvPayAddress, final int
            address_id) {
        lists.clear();
        if (TextUtils.equals("", util.getString(SPUtil.IS_USER, ""))) {
            ToastUtils.showToast(view.getCon(), "请先登录");
            return;
        }
        MyOkHttp.newInstance().get(Common.URL_GET_ADDRESS, null,
                new GsonResponseHandler<ArrayList<ReceiveAddressBean>>() {
                    @Override
                    public void onSuccess(int statusCode, ArrayList<ReceiveAddressBean> response) {
                        for (int i = 0; i < response.size(); i++) {
                            if (response.get(i).getUserId() == Integer.valueOf(util.getString
                                    (SPUtil.USER_ID, ""))) {
                                lists.add(response.get(i));
                            }
                        }
                        if (lists.size() == 0) {
                            tvPayUser.setText("要先添加一个收货地址哦");
                            return;
                        }
                        tvPayUser.setText(lists.get(address_id).getReceiveUser() + "\t" + lists
                                .get(address_id).getReceivePhoneNumber());
                        tvPayAddress.setText(lists.get(address_id).getAddress() + "\t" + lists
                                .get(address_id).getStreet());
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        ToastUtils.showToast(view.getCon(), "查询地址失败");
                    }
                });
    }

    /**
     * 选择收货时间
     *
     * @param tvPayTime 显示配送时间的控件
     */
    public void setReceiveTime(final TextView tvPayTime) {
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();
//        Calendar startDate = Calendar.getInstance();
//        startDate.set(1980, 0, 1);//设置起始时间
        Calendar endDate = Calendar.getInstance();
        endDate.set(2017, 11, 31);//设置末时间
        //时间选择器
        TimePickerView pvTime = new TimePickerView.Builder(view.getCon(), new TimePickerView
                .OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                tvPayTime.setText(getTime(date));
            }
        })
                //年、月、日、时、分、秒  的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "")
                .isCenterLabel(true)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(21)
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .setRangDate(selectedDate, endDate)
                .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
                .setDecorView(null)
                .isDialog(true)
                .build();
        pvTime.show();
    }

    //Date转换成String
    private String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public void createOrders(final List<FruitBean> list, TextView tvPayTime, final int address_id) {
        if (TextUtils.isEmpty(tvPayTime.getText().toString())){
            ToastUtils.showToast(view.getCon(),"请选择配送时间");
            return;
        }
        if (lists.size()==0){
            ToastUtils.showToast(view.getCon(),"要先添加一个收货地址哦");
            return;
        }
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            JSONObject jo = new JSONObject();
            BigDecimal b1 = new BigDecimal(Integer.toString(list.get(i).getNumber()));
            BigDecimal b2 = new BigDecimal(Double.toString(list.get(i).getPrice()));
            try {
                jo.put("goodsCount", list.get(i).getNumber());
                jo.put("goodsId", list.get(i).getGoodId());
                jo.put("orderPay",b1.multiply(b2).doubleValue());
                jo.put("receiveAddressId", lists.get(address_id).getId());
                jo.put("receiveTime", tvPayTime.getText().toString()+"T15:59:59.000Z");
                jo.put("userId", util.getString(SPUtil.USER_ID, ""));
            } catch (Exception e) {
                e.printStackTrace();
            }
            array.put(jo);
        }

        try {
            object.put("tOrderDTO", array);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Logger.e("tOrderDTO",object.toString());

        MyOkHttp.newInstance().postJson(Common.URL_CREATE_ORDERS, object, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Logger.e("添加订单成功", response);
                deleteGood(list);
                LemonHello.getSuccessHello("下单成功", "在我的订单中可查看详情")
                        .setContentFontSize(18)
                        .setWidth(300)
                        .addAction(new LemonHelloAction("我知道啦", new LemonHelloActionDelegate() {
                            @Override
                            public void onClick(LemonHelloView lemonHelloView, LemonHelloInfo lemonHelloInfo, LemonHelloAction lemonHelloAction) {
                                lemonHelloView.hide();
                                view.getAct().finish();
                            }
                        }))
                        .show(view.getCon());
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                Logger.e("添加订单失败", error_msg);
            }
        });

    }

    /**
     * 生成订单后  删除原购物车里的物品
     * @param list
     */
    private void deleteGood(List<FruitBean> list) {
        JSONObject j = new JSONObject();
        JSONArray a = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            a.put(list.get(i).getId());
        }
        try {
            j.put("number", a);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MyOkHttp.newInstance().postJson(Common.URL_SHOPPING_CAR_DELETE, j, new
                RawResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, String response) {
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        ToastUtils.showToast(view.getCon(), "删除失败,请重试");
                    }
                });
    }
}
