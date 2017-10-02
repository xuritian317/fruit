package com.example.xu.myapplication.moduleActivity.main.presenter;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.widget.TextView;

import com.chaychan.viewlib.PowerfulEditText;
import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.moduleActivity.main.viewInterface.IRegister;
import com.example.xu.myapplication.util.ToastUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 逝 on 2017/10/02.
 */

public class RegisterPresenter extends BasePresenter {
    private IRegister view;

    public RegisterPresenter(IRegister view) {
        this.view = view;
    }

    /**
     * 短信验证码倒计时
     *
     * @param tvRegisterPhoneCode 显示倒计时的TextView
     * @param petRegisterPhone    判断手机号输入是否正确
     */
    public void countDown(final TextView tvRegisterPhoneCode, PowerfulEditText petRegisterPhone) {
        String key = "0?(13|14|15|18|17)[0-9]{9}";
        String phone = petRegisterPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.showToast(view.getCon(), "手机号不能为空！");
            return;
        }
        Pattern pattern = Pattern.compile(key);
        Matcher matcher = pattern.matcher(phone);
        if (!matcher.matches()) {
            ToastUtils.showToast(view.getCon(), "请输入正确的手机号！");
            return;
        }
        tvRegisterPhoneCode.setEnabled(false);
        new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvRegisterPhoneCode.setText(millisUntilFinished / 1000L + "s后重试");
            }

            @Override
            public void onFinish() {
                tvRegisterPhoneCode.setEnabled(true);
                tvRegisterPhoneCode.setText("获取验证码");
            }
        }.start();
    }

    /**
     * 点击注册按钮事件
     *
     * @param petRegisterPhone 手机号
     * @param petPhoneCode     验证码
     * @param petRegisterPwd1  第1次输入密码
     * @param petRegisterPwd2  第2次输入密码
     */
    public void registerUser(PowerfulEditText petRegisterPhone, PowerfulEditText petPhoneCode,
                             PowerfulEditText petRegisterPwd1, PowerfulEditText petRegisterPwd2) {
        String phone = petRegisterPhone.getText().toString().trim();
        String code = petPhoneCode.getText().toString().trim();
        String pwd1 = petRegisterPwd1.getText().toString().trim();
        String pwd2 = petRegisterPwd2.getText().toString().trim();

        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(code) || TextUtils.isEmpty(pwd1) ||
                TextUtils.isEmpty(pwd2))
            ToastUtils.showToast(view.getCon(), "不能为空！");
        return;
    }
}
