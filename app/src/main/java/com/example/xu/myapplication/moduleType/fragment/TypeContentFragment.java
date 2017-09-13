package com.example.xu.myapplication.moduleType.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toolbar;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseFragment;
import com.example.xu.myapplication.moduleType.presenter.TypeContentPresenter;
import com.example.xu.myapplication.moduleType.viewInterface.ITypeContent;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TypeContentFragment extends BaseFragment<TypeContentPresenter> implements ITypeContent {
    private static TypeContentFragment instance;

    public static TypeContentFragment newInstance() {
        if (instance == null)
            instance = new TypeContentFragment();
        return instance;
    }

    @BindView(R.id.container_list_type)
    FrameLayout container_list;

    @BindView(R.id.container_content_type)
    FrameLayout container_content;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public int getLayout() {
        return R.layout.fragment_type_content;
    }

    @Override
    public void setPresenter() {
        presenter = new TypeContentPresenter(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        toolbar.setTitle(R.string.type);


    }

    @Override
    public void setToolbar() {

    }

}
