package com.example.xu.myapplication.moduleMy;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseMainFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends BaseMainFragment {
    private static MyFragment instance;

    public static MyFragment newInstance() {
        if (instance == null)
            instance = new MyFragment();
        return instance;
    }


    @Override
    public int getLayout() {
        return R.layout.fragment_my_content;
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
        if (findFragment(MyContentFragment.class)==null){
            loadRootFragment(R.id.frame_my_main,MyContentFragment.newInstance());
        }
    }
}
