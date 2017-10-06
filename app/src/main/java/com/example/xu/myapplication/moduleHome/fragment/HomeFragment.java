package com.example.xu.myapplication.moduleHome.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseMainFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseMainFragment {

    public static HomeFragment newInstance() {
        HomeFragment instance = new HomeFragment();
        return instance;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_home_content;
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
        if (findChildFragment(HomeContentFragment.class) == null) {
            loadRootFragment(R.id.fl_first_container, HomeContentFragment.newInstance());
        }
    }
}
