package com.example.xu.myapplication.moduleType.fragment;


import android.os.Bundle;
import android.app.Fragment;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseMainFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class TypeFragment extends BaseMainFragment {
    private static TypeFragment instance;

    public static TypeFragment newInstance() {
        if (instance == null)
            instance = new TypeFragment();
        return instance;
    }
    @Override
    public int getLayout() {
        return R.layout.fragment_type_main;
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
