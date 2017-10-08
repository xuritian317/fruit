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
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmailActivity extends BaseActivity {


    @BindView(R.id.iv_email_back)
    ImageView ivEmailBack;
    @OnClick(R.id.iv_email_back)
    public void back(){
        finish();
    }
    @BindView(R.id.pet_eamil)
    PowerfulEditText petEamil;
    @BindView(R.id.btn_changeEmail)
    Button btnChangeEmail;
    @OnClick(R.id.btn_changeEmail)
    public void saveEmail(){
        getEmail();
    }

    private SPUtil util;
    @Override
    public void setPresenter() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_email;
    }

    @Override
    public void initData() {
        util = new SPUtil(EmailActivity.this);
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
                        String nickName = response.getString("email");
                        if (TextUtils.equals(nickName, "null")) {
                            petEamil.setText("");
                            return;
                        }
                        petEamil.setText(nickName);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                petEamil.setText("");
            }
        });
    }

    @Override
    public void setToolbar() {

    }

    private void getEmail() {
        String key = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
        String email = petEamil.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            ToastUtils.showToast(this, "邮箱不能为空");
            return;
        }
        Pattern pattern = Pattern.compile(key);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            //修改昵称数据返回
            Intent intent = new Intent();
            intent.putExtra("email", email);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            ToastUtils.showToast(this, "邮箱格式不正确");
        }
    }
}
