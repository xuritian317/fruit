package com.example.xu.myapplication.moduleMy.fragment;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseMainFragment;
import com.example.xu.myapplication.moduleMy.fragment.activity.setting.AccountSettingActivity;
import com.example.xu.myapplication.moduleMy.fragment.activity.setting.MyPersonalActivity;
import com.example.xu.myapplication.moduleMy.fragment.presenter.MyPresenter;
import com.example.xu.myapplication.moduleMy.fragment.view.CircleImageView;
import com.example.xu.myapplication.moduleMy.fragment.viewInterface.IMy;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends BaseMainFragment<MyPresenter> implements IMy {
    private static MyFragment instance;

    public static MyFragment newInstance() {
        if (instance == null)
            instance = new MyFragment();
        return instance;
    }

    @BindView(R.id.iv_MyHead)
    CircleImageView ivMyHead;
    @OnClick(R.id.iv_MyHead)
    public void toPersonActivity(){
        presenter.toActivity(MyPersonalActivity.class);
    }
    @BindView(R.id.tv_MyUserName)
    TextView tvMyUserName;
    @BindView(R.id.iv_MySetting)
    ImageView ivMySetting;
    @OnClick(R.id.iv_MySetting)
    public void toSettingActivity(){
        presenter.toActivity(AccountSettingActivity.class);
    }

    @BindView(R.id.tv_dingdan)
    TextView tvDingdan;

    @OnClick(R.id.tv_dingdan)
    public void dingdan() {
        toMyOrdersActivity(0);
    }

    @BindView(R.id.tv_daishou)
    TextView tvDaishou;

    @OnClick(R.id.tv_daishou)
    public void daishu() {
        toMyOrdersActivity(1);
    }

    @BindView(R.id.tv_wancheng)
    TextView tvWancheng;

    @OnClick(R.id.tv_wancheng)
    public void wancheng() {
        toMyOrdersActivity(2);
    }

    @BindView(R.id.tv_tuikuan)
    TextView tvTuikuan;

    @OnClick(R.id.tv_tuikuan)
    public void tuikuan() {
        toMyOrdersActivity(3);
    }

    @BindView(R.id.linear_xiaoxi)
    LinearLayout linearXiaoxi;
    @BindView(R.id.linear_shoucang)
    LinearLayout linearShoucang;
    @BindView(R.id.linear_jifen)
    LinearLayout linearJifen;

    @Override
    public int getLayout() {
        return R.layout.fragment_my_main;
    }

    @Override
    public void setPresenter() {
        presenter = new MyPresenter(this);
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

    public void toMyOrdersActivity(int value) {
        presenter.toMyOrdersActivity(value);
    }

    @Override
    public Context getCon() {
        return getActivity();
    }

    @Override
    public void toStartActivity(Intent intent) {
        startActivity(intent);
    }
}
