package com.example.xu.myapplication.moduleMy.fragment.presenter;

import android.content.Intent;

import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.moduleMy.fragment.viewInterface.IAccountSetting;
import com.example.xu.myapplication.util.SPUtil;

/**
 * Created by 逝 on 2017/09/18.
 */

public class AccountSettingPresenter extends BasePresenter {

    private IAccountSetting view;
    private SPUtil util;

    public AccountSettingPresenter(IAccountSetting view) {
        this.view = view;
        util = new SPUtil(this.view.getCon());
    }

    public void startIntent(Class<?> cls0, Class<?> cls1) {
        if (cls1 == null) {
            view.getAct().startActivity(new Intent(view.getCon(), cls0));
            return;
        }

        if (util.getInt(SPUtil.IS_USER, 0) == 0) {
            view.getAct().startActivity(new Intent(view.getCon(), cls0));
        } else {
            view.getAct().startActivity(new Intent(view.getCon(), cls1));
        }
    }

}
