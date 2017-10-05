package com.example.xu.myapplication.moduleMy.fragment.activity.setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseActivity;
import com.example.xu.myapplication.moduleMy.fragment.presenter.PersonalPresenter;
import com.example.xu.myapplication.moduleMy.fragment.view.CircleImageView;
import com.example.xu.myapplication.moduleMy.fragment.viewInterface.IMyPersonal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyPersonalActivity extends BaseActivity<PersonalPresenter> implements IMyPersonal {

    @BindView(R.id.iv_person_head)
    CircleImageView ivMyHead;

    @BindView(R.id.iv_person_back)
    ImageView ivPersonBack;

    @OnClick(R.id.iv_person_back)
    public void back() {
        finish();
    }

    @BindView(R.id.rela_person_head)
    RelativeLayout relaPersonHead;

    @OnClick(R.id.rela_person_head)
    public void uploadHeadImage() {
        presenter.uploadHeadImage(MyPersonalActivity.this);
    }

    @BindView(R.id.tv_person_petName)
    TextView tvPersonPetName;

    @BindView(R.id.rela_person_petName)
    RelativeLayout relaPersonPetName;

    @OnClick(R.id.rela_person_petName)
    public void setPetName() {
        presenter.startIntent(NickNameActivity.class);
    }

    @BindView(R.id.tv_person_sex)
    TextView tvPersonSex;

    @BindView(R.id.rela_person_sex)
    RelativeLayout relaPersonSex;

    @OnClick(R.id.rela_person_sex)
    public void setSex() {
        presenter.chooseSex(tvPersonSex);
    }

    @BindView(R.id.tv_person_birth)
    TextView tvPersonBirth;

    @BindView(R.id.rela_person_birth)
    RelativeLayout relaPersonBirth;

    @OnClick(R.id.rela_person_birth)
    public void setBirth() {
        presenter.timePicker(tvPersonBirth);
    }

    @BindView(R.id.tv_person_email)
    TextView tvPersonEmail;

    @BindView(R.id.rela_person_email)
    RelativeLayout relaPersonEmail;

    @OnClick(R.id.rela_person_email)
    public void setEmail() {
        presenter.startIntent(EmailActivity.class);
    }

    @BindView(R.id.btn_personal_save)
    Button btnPersonalSave;
    @Override
    public void setPresenter() {
        presenter = new PersonalPresenter(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_my_personal;
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        presenter.getRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.getActivityResult(requestCode, resultCode, data, ivMyHead);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getUser(ivMyHead,tvPersonPetName,tvPersonSex,tvPersonBirth,tvPersonEmail);
    }

    @Override
    public Context getCon() {
        return MyPersonalActivity.this;
    }

    @Override
    public Activity getAct() {
        return MyPersonalActivity.this;
    }
}
