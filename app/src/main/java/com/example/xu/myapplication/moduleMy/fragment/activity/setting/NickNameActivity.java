package com.example.xu.myapplication.moduleMy.fragment.activity.setting;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.chaychan.viewlib.PowerfulEditText;
import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseActivity;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.JsonResponseHandler;
import com.example.xu.myapplication.util.SPUtil;
import com.example.xu.myapplication.util.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

public class NickNameActivity extends BaseActivity {


    @BindView(R.id.iv_nick_back)
    ImageView ivNickBack;

    @OnClick(R.id.iv_nick_back)
    public void back() {
        finish();
    }

    @BindView(R.id.pet_nickName)
    PowerfulEditText petNickName;

    @BindView(R.id.btn_changeNick)
    Button btnChangeNick;

    @OnClick(R.id.btn_changeNick)
    public void saveNick() {
        getNickName();
    }

    private SPUtil util;

    @Override
    public void setPresenter() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_nick_name;
    }

    @Override
    public void initData() {
        util = new SPUtil(NickNameActivity.this);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        JSONObject jo = new JSONObject();
        try {
            jo.put("phoneNumber", util.getString(SPUtil.IS_USER, ""));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MyOkHttp.newInstance().postJson(Common.URL_GET_USER, jo, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                if (statusCode == 200) {
                    try {
                        String nickName = response.getString("nickName");
                        if (TextUtils.equals(nickName, "null")) {
                            petNickName.setText("");
                            return;
                        }
                        petNickName.setText(nickName);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                petNickName.setText("");
            }
        });
    }

    @Override
    public void setToolbar() {

    }

    private void getNickName() {
        String key = "[A-Za-z0-9_\\u4e00-\\u9fa5]{3,16}";
        String nickName = petNickName.getText().toString().trim();
        if (TextUtils.isEmpty(nickName)) {
            ToastUtils.showToast(this, "昵称不能为空");
            return;
        }
        Pattern pattern = Pattern.compile(key);
        Matcher matcher = pattern.matcher(nickName);
        if (matcher.matches()) {
            //修改昵称数据返回
            Intent intent = new Intent();
            intent.putExtra("nickName", nickName);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            ToastUtils.showToast(this, "昵称格式不正确");
        }
    }

}
