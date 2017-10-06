package com.example.xu.myapplication.moduleMy.fragment.presenter;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.GsonResponseHandler;
import com.example.xu.myapplication.httpRequest.response.JsonResponseHandler;
import com.example.xu.myapplication.moduleMy.fragment.bean.ReceiveAddressBean;
import com.example.xu.myapplication.moduleMy.fragment.viewInterface.IAddAddress;
import com.example.xu.myapplication.util.Logger;
import com.example.xu.myapplication.util.SPUtil;
import com.example.xu.myapplication.util.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 逝 on 2017/10/03.
 */

public class AddAddressPresenter extends BasePresenter {
    private IAddAddress view;
    private SPUtil util;

    public AddAddressPresenter(IAddAddress view) {
        this.view = view;
        util = new SPUtil(this.view.getCon());
    }

    /**
     * 保存收货地址
     *
     * @param etAddName
     * @param etAddPhone
     * @param tvAddShengshi
     * @param etAddXiangxi
     */
    public void saveAddress(EditText etAddName, EditText etAddPhone, TextView tvAddShengshi,
                            EditText etAddXiangxi) {
        String regex = "0?(13|14|15|18|17)[0-9]{9}";
        String name = etAddName.getText().toString().trim();
        String phone = etAddPhone.getText().toString().trim();
        String shengshi = tvAddShengshi.getText().toString().trim();
        String xiangxi = etAddXiangxi.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(shengshi) ||
                TextUtils.isEmpty(xiangxi)) {
            ToastUtils.showToast(view.getCon(), "收获地址不能为空哦");
            return;
        }

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        if (!matcher.matches()) {
            ToastUtils.showToast(view.getCon(), "手机号错误了哦");
            return;
        }

        String userId=util.getString(SPUtil.USER_ID,"");

        JSONObject json = new JSONObject();
        try {
            json.put("address", shengshi);
            json.put("receivePhoneNumber", phone);
            json.put("street", xiangxi);
            json.put("receiveUser", name);
            json.put("userId", userId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MyOkHttp.newInstance().postJson(Common.URL_CREATE_ADDRESS, json, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                Logger.e("statusCode", statusCode + "");
                Logger.e("response", response.toString() + "");
                if (statusCode == 201) {
                    ToastUtils.showToast(view.getCon(), "添加成功");
                    view.getAct().finish();
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                Logger.e("statusCode1", statusCode + "");
                Logger.e("error_msg", error_msg + "");
                ToastUtils.showToast(view.getCon(), error_msg);
            }
        });
    }

    public void getAddress(final int id, final EditText etAddName, final EditText etAddPhone,
                           final TextView tvAddShengshi, final EditText etAddXiangxi) {
        MyOkHttp.newInstance().get(Common.URL_GET_ADDRESS, null,
                new GsonResponseHandler<ArrayList<ReceiveAddressBean>>() {
                    @Override
                    public void onSuccess(int statusCode, ArrayList<ReceiveAddressBean> response) {
                        for (int i = 0; i < response.size(); i++) {
                            if (response.get(i).getId() == id) {
                                etAddName.setText(response.get(i).getReceiveUser() + "");
                                etAddPhone.setText(response.get(i).getReceivePhoneNumber() + "");
                                tvAddShengshi.setText(response.get(i).getAddress());
                                etAddXiangxi.setText(response.get(i).getStreet());
                            }
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });
    }
}
