package com.example.xu.myapplication.moduleMy.fragment.presenter;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.JsonResponseHandler;
import com.example.xu.myapplication.moduleMy.fragment.view.CircleImageView;
import com.example.xu.myapplication.moduleMy.fragment.viewInterface.IAccountSetting;
import com.example.xu.myapplication.util.BitmapUtil;
import com.example.xu.myapplication.util.Logger;
import com.example.xu.myapplication.util.SPUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 逝 on 2017/09/18.
 */

public class AccountSettingPresenter extends BasePresenter {

    private IAccountSetting view;
    private SPUtil util;

    public AccountSettingPresenter(IAccountSetting view) {
        this.view = view;
        util = new SPUtil(this.view.getCon());
    }

    public void startIntent(Class<?> cls0, Class<?> cls1) {
        if (cls1 == null) {
            view.getAct().startActivity(new Intent(view.getCon(), cls0));
            return;
        }

        if (TextUtils.equals(util.getString(SPUtil.IS_USER,""),"")) {
            view.getAct().startActivity(new Intent(view.getCon(), cls0));
        } else {
            view.getAct().startActivity(new Intent(view.getCon(), cls1));
        }
    }

    public void getUser(final CircleImageView ivSettingHead, final TextView tvSettingNick) {
        String phone = util.getString(SPUtil.IS_USER, "");
        if (TextUtils.equals(util.getString(SPUtil.IS_USER, ""), "")) {
            ivSettingHead.setImageDrawable(view.getCon().getResources().getDrawable(R
                    .mipmap.img_head));
            tvSettingNick.setText("登录/注册");
            return;
        }
        Logger.e("phone", phone + "");
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
                        tvSettingNick.setVisibility(View.GONE);
                    } else {
                        tvSettingNick.setVisibility(View.VISIBLE);
                        tvSettingNick.setText(nickName);
                    }

                    //头像
                    if (TextUtils.equals(headImage, "null")) {
                        ivSettingHead.setImageDrawable(view.getCon().getResources().getDrawable(R
                                .mipmap.img_head));
                    } else {
                        ivSettingHead.setImageBitmap(BitmapUtil.getBitmapFromBase64(headImage));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {

            }
        });
    }
}
