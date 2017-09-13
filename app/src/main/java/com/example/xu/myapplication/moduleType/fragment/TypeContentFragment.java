package com.example.xu.myapplication.moduleType.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class TypeContentFragment extends BaseFragment {
    private static TypeContentFragment instance;

    public static TypeContentFragment newInstance() {
        if (instance == null)
            instance = new TypeContentFragment();
        return instance;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_type_content;
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
