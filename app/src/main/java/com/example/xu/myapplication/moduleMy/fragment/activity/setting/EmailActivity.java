package com.example.xu.myapplication.moduleMy.fragment.activity.setting;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.chaychan.viewlib.PowerfulEditText;
import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseActivity;
import com.example.xu.myapplication.util.ToastUtils;

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

    @Override
    public void setPresenter() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_email;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView(Bundle savedInstanceState) {

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
            ToastUtils.showToast(this, "修改成功");
            //修改昵称数据提交至服务器
            finish();
        } else {
            ToastUtils.showToast(this, "邮箱格式不正确");
        }
    }
}
