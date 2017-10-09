package com.example.xu.myapplication.moduleMy.fragment.activity.setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseActivity;
import com.example.xu.myapplication.moduleActivity.main.activity.LoginActivity;
import com.example.xu.myapplication.moduleMy.fragment.presenter.AccountSettingPresenter;
import com.example.xu.myapplication.moduleMy.fragment.view.CircleImageView;
import com.example.xu.myapplication.moduleMy.fragment.viewInterface.IAccountSetting;
import com.example.xu.myapplication.util.SPUtil;
import com.example.xu.myapplication.util.ToastUtils;
import com.vondear.rxtools.view.dialog.RxDialogSureCancel;

import butterknife.BindView;
import butterknife.OnClick;

public class AccountSettingActivity extends BaseActivity<AccountSettingPresenter> implements
        IAccountSetting {

    @BindView(R.id.iv_setting_back)
    ImageView ivSettingBack;
    @BindView(R.id.tv_setting_nick)
    TextView tvSettingNick;
    @BindView(R.id.iv_setting_head)
    CircleImageView ivSettingHead;
    @BindView(R.id.rela_address)
    RelativeLayout relaAddress;
    @BindView(R.id.rela_account_safe)
    RelativeLayout relaAccountSafe;
    @BindView(R.id.rela_setting_head)
    RelativeLayout relaSettingHead;
    @BindView(R.id.tv_logout)
    TextView tvLogout;

    @OnClick({R.id.iv_setting_back, R.id.rela_setting_head, R.id.rela_address,
            R.id.rela_account_safe,R.id.tv_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_setting_back:
                finish();
                break;
            case R.id.rela_setting_head:
                presenter.startIntent(LoginActivity.class, MyPersonalActivity.class);
                break;
            case R.id.rela_address:
                presenter.startIntent(QueryAddressActivity.class, null);
                break;
            case R.id.rela_account_safe:
                ToastUtils.showToast(AccountSettingActivity.this,"功能暂未开启");
                break;
            case R.id.tv_logout:
                rxLogout();
                break;
        }
    }

    private SPUtil util;

    private void rxLogout() {
        if (TextUtils.equals("",util.getString(SPUtil.IS_USER,""))){
            tvLogout.setEnabled(false);
            ToastUtils.showToast(AccountSettingActivity.this,"不能再退出登录了哦");
            return;
        }else {
            tvLogout.setEnabled(false);
        }

        final RxDialogSureCancel rxDialogSureCancel = new RxDialogSureCancel(AccountSettingActivity.this);//提示弹窗
        rxDialogSureCancel.getTitleView().setBackgroundResource(R.drawable.logo);
        rxDialogSureCancel.getSureView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                util.putString(SPUtil.IS_USER,"");
                util.putString(SPUtil.USER_ID,"-1");
                startActivity(new Intent(AccountSettingActivity.this,LoginActivity.class));
                rxDialogSureCancel.cancel();
                finish();
            }
        });
        rxDialogSureCancel.getCancelView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxDialogSureCancel.cancel();
            }
        });
        rxDialogSureCancel.show();
    }

    @Override
    public void setPresenter() {
        presenter = new AccountSettingPresenter(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_account_setting;
    }

    @Override
    public void initData() {
        util=new SPUtil(AccountSettingActivity.this);

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
    protected void onResume() {
        super.onResume();
        presenter.getUser(ivSettingHead, tvSettingNick);
    }

    @Override
    public Context getCon() {
        return AccountSettingActivity.this;
    }

    @Override
    public Activity getAct() {
        return AccountSettingActivity.this;
    }

}
