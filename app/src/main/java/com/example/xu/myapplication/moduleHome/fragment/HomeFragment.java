package com.example.xu.myapplication.moduleHome.fragment;


import android.os.Bundle;
import android.app.Fragment;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseMainFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseMainFragment {
    private static HomeFragment instance;

    public static HomeFragment newInstance() {
        if (instance == null)
            instance = new HomeFragment();
        return instance;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_home_main;
    }

    @Override
    public void setPresenter() {

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
}
