package com.example.xu.myapplication.moduleActivity.main.presenter;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.chaychan.viewlib.PowerfulEditText;
import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.JsonResponseHandler;
import com.example.xu.myapplication.moduleActivity.main.viewInterface.ILogin;
import com.example.xu.myapplication.util.Logger;
import com.example.xu.myapplication.util.SPUtil;
import com.example.xu.myapplication.util.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 逝 on 2017/10/05.
 */

public class LoginPresenter extends BasePresenter {
    private ILogin view;
    private SPUtil util;

    public LoginPresenter(ILogin view) {
        this.view = view;
        util = new SPUtil(this.view.getCon());
    }

    /**
     * 跳转Activity
     */
    public void toActivity(Class<?> cls) {
        view.getAct().startActivity(new Intent(view.getCon(), cls));
    }

    public void getUser(PowerfulEditText petLoginPhone,PowerfulEditText petLoginPwd){
        final String phone=petLoginPhone.getText().toString().trim();
        final String pwd=petLoginPwd.getText().toString().trim();
        String key = "0?(13|14|15|18|17)[0-9]{9}";
        if (TextUtils.isEmpty(phone)||TextUtils.isEmpty(pwd)){
            ToastUtils.showToast(view.getCon(),"用户名或密码不能为空");
            return;
        }
        Pattern pattern = Pattern.compile(key);
        Matcher matcher = pattern.matcher(phone);
        if (!matcher.matches()) {
            ToastUtils.showToast(view.getCon(), "请输入正确的手机号！");
            return;
        }
        JSONObject jo=new JSONObject();
        try {
            jo.put("phoneNumber",phone);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MyOkHttp.newInstance().postJson(Common.URL_GET_USER, jo, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                try {
                    String password=response.getString("password");
                    if (TextUtils.equals(pwd,password)){
                        util.putString(SPUtil.IS_USER,phone);
                        util.putString(SPUtil.USER_ID,response.getString("id"));
                        view.getAct().finish();
                        Logger.e("idididididididididiididididid",response.getString("id"));
                    } else {
                        ToastUtils.showToast(view.getCon(),"密码输入错误，请重试");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, String error_msg) {
                if (statusCode==200){
                    ToastUtils.showToast(view.getCon(),"该手机号还未注册哦");
                }
            }
        });
    }
}
