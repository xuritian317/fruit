package com.example.xu.myapplication.moduleMy.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseMainFragment;
import com.example.xu.myapplication.moduleActivity.main.activity.LoginActivity;
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

    @BindView(R.id.refresh_my)
    SwipeRefreshLayout refreshMy;

    @BindView(R.id.iv_MyHead)
    CircleImageView ivMyHead;

    @OnClick(R.id.iv_MyHead)
    public void toPersonActivity() {
        presenter.toActivity(LoginActivity.class, MyPersonalActivity.class);
    }

    @BindView(R.id.tv_MyUserName)
    TextView tvMyUserName;
    @BindView(R.id.iv_MySetting)
    ImageView ivMySetting;

    @OnClick(R.id.iv_MySetting)
    public void toSettingActivity() {
        presenter.toActivity(AccountSettingActivity.class, null);
    }

    @BindView(R.id.tv_dingdan)
    TextView tvDingdan;

    @OnClick(R.id.tv_dingdan)
    public void dingdan() {
        presenter.toMyOrdersActivity(0);
    }

    @BindView(R.id.linear_daishou)
    LinearLayout linearDaishou;

    @OnClick(R.id.linear_daishou)
    public void daishu() {
        presenter.toMyOrdersActivity(1);
    }

    @BindView(R.id.tv_daishou)
    TextView tvDaishou;

    @BindView(R.id.tv_wancheng)
    TextView tvWancheng;

    @OnClick(R.id.tv_wancheng)
    public void wancheng() {
        presenter.toMyOrdersActivity(2);
    }

    @BindView(R.id.tv_evaluate)
    TextView tvEvaluate;
    @BindView(R.id.linear_evaluate)
    LinearLayout linearEvaluate;

    @OnClick(R.id.linear_evaluate)
    public void evaluate() {
        presenter.toMyOrdersActivity(3);
    }

    @BindView(R.id.linear_tuikuan)
    LinearLayout linearTuikuan;

    @OnClick(R.id.linear_tuikuan)
    public void tuikuan() {
        presenter.toMyOrdersActivity(4);
    }

    @BindView(R.id.tv_tuikuan)
    TextView tvTuikuan;

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
        refreshMy.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getUser(refreshMy,ivMyHead, tvMyUserName, tvDaishou, tvEvaluate, tvTuikuan);
            }
        });
    }

    @Override
    public void setToolbar() {

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getUser(refreshMy,ivMyHead, tvMyUserName, tvDaishou, tvEvaluate, tvTuikuan);
    }

    @Override
    public Context getCon() {
        return getActivity();
    }

    @Override
    public Activity getAct() {
        return getActivity();
    }

}
