package com.example.xu.myapplication.moduleMy;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseFragment;
import com.example.xu.myapplication.moduleActivity.main.activity.LoginActivity;
import com.example.xu.myapplication.moduleMy.activity.collect.CollectActivity;
import com.example.xu.myapplication.moduleMy.activity.setting.AccountSettingActivity;
import com.example.xu.myapplication.moduleMy.activity.setting.MyPersonalActivity;
import com.example.xu.myapplication.moduleMy.presenter.MyPresenter;
import com.example.xu.myapplication.moduleMy.view.CircleImageView;
import com.example.xu.myapplication.moduleMy.viewInterface.IMy;
import com.example.xu.myapplication.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyContentFragment extends BaseFragment<MyPresenter> implements IMy {
    public static MyContentFragment newInstance(){
        MyContentFragment instance = new MyContentFragment();
        return instance;
    }


    @BindView(R.id.refresh_my)
    SwipeRefreshLayout refreshMy;
    @BindView(R.id.iv_MyHead)
    CircleImageView ivMyHead;
    @BindView(R.id.tv_MyUserName)
    TextView tvMyUserName;
    @BindView(R.id.iv_MySetting)
    ImageView ivMySetting;
    @BindView(R.id.tv_dingdan)
    TextView tvDingdan;
    @BindView(R.id.linear_daishou)
    LinearLayout linearDaishou;
    @BindView(R.id.tv_daishou)
    TextView tvDaishou;
    @BindView(R.id.tv_wancheng)
    TextView tvWancheng;
    @BindView(R.id.tv_evaluate)
    TextView tvEvaluate;
    @BindView(R.id.linear_evaluate)
    LinearLayout linearEvaluate;
    @BindView(R.id.linear_tuikuan)
    LinearLayout linearTuikuan;
    @BindView(R.id.tv_tuikuan)
    TextView tvTuikuan;
    @BindView(R.id.linear_xiaoxi)
    LinearLayout linearXiaoxi;
    @BindView(R.id.linear_shoucang)
    LinearLayout linearShoucang;
    @BindView(R.id.linear_jifen)
    LinearLayout linearJifen;
    @BindView(R.id.tv_daishou_hint)
    TextView tvDaishouHint;
    @BindView(R.id.tv_evaluate_hint)
    TextView tvEvaluateHint;
    @BindView(R.id.tv_tuikuan_hint)
    TextView tvTuikuanHint;

    @OnClick({R.id.iv_MyHead, R.id.iv_MySetting, R.id.tv_dingdan, R.id.linear_daishou,
            R.id.tv_wancheng, R.id.linear_evaluate, R.id.linear_tuikuan, R.id.linear_xiaoxi,
            R.id.linear_shoucang, R.id.linear_jifen})
    public void onViewOnClick(View view) {
        switch (view.getId()) {
            case R.id.iv_MyHead:
                presenter.toActivity(LoginActivity.class, MyPersonalActivity.class);
                break;
            case R.id.iv_MySetting:
                presenter.toActivity(AccountSettingActivity.class, null);
                break;
            case R.id.tv_dingdan:
                presenter.toMyOrdersActivity(0);
                break;
            case R.id.linear_daishou:
                presenter.toMyOrdersActivity(1);
                break;
            case R.id.tv_wancheng:
                presenter.toMyOrdersActivity(2);
                break;
            case R.id.linear_evaluate:
                presenter.toMyOrdersActivity(3);
                break;
            case R.id.linear_tuikuan:
                presenter.toMyOrdersActivity(4);
                break;
            case R.id.linear_xiaoxi:
                ToastUtils.showToast(getActivity(), "功能暂未开启");
                break;
            case R.id.linear_shoucang:
                presenter.toActivity(CollectActivity.class, null);
                break;
            case R.id.linear_jifen:
                ToastUtils.showToast(getActivity(), "功能暂未开启");
                break;
        }
    }

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
                presenter.getUser(refreshMy, ivMyHead, tvMyUserName, tvDaishou, tvEvaluate,
                        tvTuikuan,tvDaishouHint,tvEvaluateHint,tvTuikuanHint);
            }
        });
    }

    @Override
    public void setToolbar() {

    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        presenter.getUser(refreshMy, ivMyHead, tvMyUserName, tvDaishou, tvEvaluate,
                tvTuikuan,tvDaishouHint,tvEvaluateHint,tvTuikuanHint);
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
