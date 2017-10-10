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
import com.example.xu.myapplication.moduleActivity.main.presenter.LoginPresenter;
import com.example.xu.myapplication.moduleActivity.main.viewInterface.ILogin;
import com.example.xu.myapplication.util.ToastUtils;
import com.vondear.rxtools.view.dialog.RxDialogSure;

import butterknife.BindView;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity<LoginPresenter> implements ILogin {

    @BindView(R.id.iv_login_back)
    ImageView ivLoginBack;
    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.pet_login_phone)
    PowerfulEditText petLoginPhone;
    @BindView(R.id.pet_login_pwd)
    PowerfulEditText petLoginPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_regist)
    TextView tvRegist;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.tv_about_us)
    TextView tvAboutUs;

    @OnClick({R.id.iv_login_back, R.id.btn_login, R.id.tv_regist,
            R.id.tv_forget_password, R.id.tv_about_us})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.btn_login:
                presenter.getUser(petLoginPhone,petLoginPwd);
                break;
            case R.id.tv_regist:
                presenter.toActivity(RegisterActivity.class);
                break;
            case R.id.tv_forget_password:
                ToastUtils.showToast(LoginActivity.this,"该功能暂未开启");
                break;
            case R.id.tv_about_us:
                showAboutUs();
                break;
        }
    }

    private void showAboutUs() {
        final RxDialogSure dialog=new RxDialogSure(this);
        dialog.getContentView().setText(this.getResources().getString(R.string.about_us));
        dialog.getLogoView().setImageResource(R.mipmap.logo);
        dialog.getTitleView().setText("蔬果配送");
        dialog.getTitleView().setTextColor(getResources().getColor(R.color.colorPrimary));
        dialog.getSureView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    @Override
    public void setPresenter() {
        presenter=new LoginPresenter(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
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
        return LoginActivity.this;
    }

    @Override
    public Activity getAct() {
        return LoginActivity.this;
    }
}
