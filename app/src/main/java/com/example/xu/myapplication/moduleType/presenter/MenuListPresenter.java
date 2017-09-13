package com.example.xu.myapplication.moduleType.presenter;

import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.moduleType.viewInterface.IMenuList;

/**
 * Created by xu on 2017/9/13.
 */

public class MenuListPresenter extends BasePresenter {
    private IMenuList view;

    public MenuListPresenter(IMenuList view) {
        this.view = view;
    }
}
