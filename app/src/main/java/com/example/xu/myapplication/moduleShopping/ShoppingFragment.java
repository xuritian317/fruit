package com.example.xu.myapplication.moduleShopping;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseMainFragment;
import com.example.xu.myapplication.moduleShopping.presenter.ShoppingPresenter;
import com.example.xu.myapplication.moduleShopping.viewInterface.IShopping;

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
        return R.layout.fragment_shopping_content;
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

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (findChildFragment(ShoppingContentFragment.class) == null) {
            loadRootFragment(R.id.frame_shopping, ShoppingContentFragment.newInstance());
        }
    }
}
