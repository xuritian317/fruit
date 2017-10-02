package com.example.xu.myapplication.moduleMy.fragment.presenter;

import android.content.Intent;

import com.example.xu.myapplication.R;
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
        util=new SPUtil(this.view.getCon());
    }

    public void startIntent(Class<?> cls0,Class<?> cls1){
        switch (util.getInt("isUser",0)){
            case 0:
                view.getAct().startActivity(new Intent(view.getCon(), cls0));
                break;
            case 1:
                view.getAct().startActivity(new Intent(view.getCon(), cls1));
                break;
        }
    }

}
