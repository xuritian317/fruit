package com.example.xu.myapplication.moduleType.presenter;

import android.os.Bundle;

import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.moduleType.viewInterface.IMenuList;

/**
 * Created by xu on 2017/9/13.
 */

public class MenuListPresenter extends BasePresenter {
    private static final String SAVE_STATE_POSITION = "save_state_position";
    private IMenuList view;

    public MenuListPresenter(IMenuList view) {
        this.view = view;
    }

    public void isSave(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            int mCurrentPosition = savedInstanceState.getInt(SAVE_STATE_POSITION);
            view.setCurrentPosition(mCurrentPosition);
            view.setAdapterCheck(mCurrentPosition);
        } else {
            view.setCurrentPosition(0);
            view.setAdapterCheck(0);
        }
    }
}
