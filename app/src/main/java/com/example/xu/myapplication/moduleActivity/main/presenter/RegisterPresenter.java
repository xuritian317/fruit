package com.example.xu.myapplication.moduleActivity.main.presenter;

import android.text.TextUtils;
import android.widget.TextView;

import com.chaychan.viewlib.PowerfulEditText;
import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.JsonResponseHandler;
import com.example.xu.myapplication.httpRequest.response.RawResponseHandler;
import com.example.xu.myapplication.moduleActivity.main.viewInterface.IRegister;
import com.example.xu.myapplication.util.Logger;
import com.example.xu.myapplication.util.ToastUtils;
import com.vondear.rxtools.RxTool;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 逝 on 2017/10/02.
 */

public class RegisterPresenter extends BasePresenter {
    private IRegister view;
    private String phone_code = "";

    public RegisterPresenter(IRegister view) {
        this.view = view;
    }

    /**
     * 短信验证码倒计时
     *
     * @param tvRegisterPhoneCode 显示倒计时的TextView
     * @param petRegisterPhone    判断手机号输入是否正确
     */
    public void countDown(final TextView tvRegisterPhoneCode, PowerfulEditText petRegisterPhone) {
        String key = "0?(13|14|15|18|17)[0-9]{9}";
        String phone = petRegisterPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.showToast(view.getCon(), "手机号不能为空！");
            return;
        }

        if (!isMatches(key,phone)){
            ToastUtils.showToast(view.getCon(), "请输入正确的手机号！");
            return;
        }

        //倒计时
        RxTool.countDown(tvRegisterPhoneCode, 60000, 1000, "获取验证码");

        JSONObject jo = new JSONObject();
        try {
            jo.put("phoneNumber", phone);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MyOkHttp.newInstance().postJson(Common.URL_GET_PHONE_CODE, jo, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Logger.e("phone_code", response);
                phone_code = response;
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                Logger.e("error_msg", "response");
            }
        });

    }

    /**
     * 点击注册按钮事件
     *
     * @param petRegisterPhone 手机号
     * @param petPhoneCode     验证码
     * @param petRegisterPwd1  第1次输入密码
     * @param petRegisterPwd2  第2次输入密码
     */
    public void registerUser(PowerfulEditText petRegisterPhone, PowerfulEditText petPhoneCode,
                             PowerfulEditText petRegisterPwd1, PowerfulEditText petRegisterPwd2) {
        final String phone = petRegisterPhone.getText().toString().trim();
        final String code = petPhoneCode.getText().toString().trim();
        final String pwd1 = petRegisterPwd1.getText().toString().trim();
        final String pwd2 = petRegisterPwd2.getText().toString().trim();

        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(code) || TextUtils.isEmpty(pwd1) ||
                TextUtils.isEmpty(pwd2)) {
            ToastUtils.showToast(view.getCon(), "不能为空！");
            return;
        }
        String regex="[A-Za-z0-9]{6,12}";
        if (!isMatches(regex,pwd1)){
            ToastUtils.showToast(view.getCon(),"密码格式不符合规范");
            return;
        }

        JSONObject jo = new JSONObject();
        try {
            jo.put("phoneNumber", phone);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MyOkHttp.newInstance().postJson(Common.URL_GET_USER, jo, new RawResponseHandler() {
            @Override
            public void onSuccess(int statusCode, String response) {
                Logger.e("response", response);
                if (!TextUtils.equals("", response)) {
                    ToastUtils.showToast(view.getCon(), "该手机已经注册，请直接登录");
                    return;
                }

                if (!TextUtils.equals(code, phone_code)) {
                    ToastUtils.showToast(view.getCon(), "验证码输入错误");
                    return;
                }
                if (!TextUtils.equals(pwd1, pwd2)) {
                    ToastUtils.showToast(view.getCon(), "两次密码输入不一致");
                    return;
                }

                JSONObject object = new JSONObject();
                try {
                    object.put("phoneNumber", phone);
                    object.put("password", pwd1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                MyOkHttp.newInstance().postJson(Common.URL_CREATE_USER, object, new
                        JsonResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, JSONObject response) {
                        if (statusCode == 201) {
                            ToastUtils.showToast(view.getCon(), "用户注册成功");
                            view.getAct().finish();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        ToastUtils.showToast(view.getCon(), "注册失败，请稍后重试");
                    }
                });

            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                Logger.e("error_msg", "response");
            }
        });
    }

    private boolean isMatches(String regex,String str){
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(str);
        return matcher.matches();
    }
}
