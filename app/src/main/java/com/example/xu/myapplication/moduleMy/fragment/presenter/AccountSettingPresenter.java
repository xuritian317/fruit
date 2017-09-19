package com.example.xu.myapplication.moduleMy.fragment.presenter;

import android.content.Intent;

import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.moduleMy.fragment.viewInterface.IAccountSetting;

/**
 * Created by 逝 on 2017/09/18.
 */

public class AccountSettingPresenter extends BasePresenter {

    private IAccountSetting view;

    public AccountSettingPresenter(IAccountSetting view) {
        this.view = view;
    }

    public void startIntent(Class<?> cls){
        Intent intent=new Intent(view.getCon(),cls);
        view.getAct().startActivity(intent);
    }

}
