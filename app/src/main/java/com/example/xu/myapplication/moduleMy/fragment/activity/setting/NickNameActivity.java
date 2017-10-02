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

    @Override
    public void setPresenter() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_nick_name;
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
            ToastUtils.showToast(this, "修改成功");
            //修改昵称数据提交至服务器
            finish();
        } else {
            ToastUtils.showToast(this, "昵称格式不正确");
        }
    }

}
