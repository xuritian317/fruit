package com.example.xu.myapplication.moduleShopping.fragment;


import android.os.Bundle;
import android.app.Fragment;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseMainFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingFragment extends BaseMainFragment {
    private static ShoppingFragment instance;

    public static ShoppingFragment newInstance() {
        if (instance == null)
            instance = new ShoppingFragment();
        return instance;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_shopping_main;
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
