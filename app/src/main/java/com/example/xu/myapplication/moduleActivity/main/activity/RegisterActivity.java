package com.example.xu.myapplication.moduleActivity.main.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chaychan.viewlib.PowerfulEditText;
import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseActivity;
import com.example.xu.myapplication.moduleActivity.main.presenter.RegisterPresenter;
import com.example.xu.myapplication.moduleActivity.main.viewInterface.IRegister;
import com.vondear.rxtools.RxBarTool;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements IRegister {

    @BindView(R.id.iv_register_back)
    ImageView ivRegisterBack;
    @BindView(R.id.pet_register_phone)
    PowerfulEditText petRegisterPhone;
    @BindView(R.id.tv_register_phoneCode)
    TextView tvRegisterPhoneCode;
    @BindView(R.id.pet_phoneCode)
    PowerfulEditText petPhoneCode;
    @BindView(R.id.pet_register_pwd1)
    PowerfulEditText petRegisterPwd1;
    @BindView(R.id.pet_register_pwd2)
    PowerfulEditText petRegisterPwd2;
    @BindView(R.id.btn_register)
    Button btnRegister;

    @OnClick({R.id.iv_register_back, R.id.tv_register_phoneCode, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_register_back:
                finish();
                break;
            case R.id.tv_register_phoneCode:
                presenter.countDown(tvRegisterPhoneCode, petRegisterPhone);
                break;
            case R.id.btn_register:
                presenter.registerUser(petRegisterPhone, petPhoneCode, petRegisterPwd1,
                        petRegisterPwd2);
                break;
        }
    }

    @Override
    public void setPresenter() {
        presenter = new RegisterPresenter(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void initData() {
        Window window = getWindow();
        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        window.setStatusBarColor(getResources().getColor(R.color.tab_unSelect));
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void setToolbar() {

    }

    @Override
    public Context getCon() {
        return RegisterActivity.this;
    }

    @Override
    public Activity getAct() {
        return RegisterActivity.this;
    }
}
