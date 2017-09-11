package com.example.xu.myapplication.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * 所有子fragment都应继承此类
 * Created by YoKeyword on 16/2/3.
 */
public abstract class BaseFragment<T extends BasePresenter> extends SupportFragment implements BaseViewInterface {
    public T presenter;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        setPresenter();
        setToolbar();
        initData();
        initView(savedInstanceState);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    /**
     * 返回界面layout
     */
    @LayoutRes
    public abstract int getLayout();

    /**
     * 设置presenter
     */
    public abstract void setPresenter();

    /**
     * 初始化界面
     */
    public abstract void initData();

    /**
     * 初始化view
     */
    @Override
    public abstract void initView(Bundle savedInstanceState);


    /**
     * 设置toolbar
     */
    public abstract void setToolbar();
}
