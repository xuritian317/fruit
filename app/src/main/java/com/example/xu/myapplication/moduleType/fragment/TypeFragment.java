package com.example.xu.myapplication.moduleType.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseMainFragment;
import com.example.xu.myapplication.moduleType.presenter.TypePresenter;
import com.example.xu.myapplication.moduleType.viewInterface.IType;

import me.yokeyword.fragmentation.ISupportFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class TypeFragment extends BaseMainFragment<TypePresenter> implements IType {
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
        presenter = new TypePresenter(this);
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
        presenter.loadRootFrag(TypeContentFragment.class);
    }

    @Override
    public void loadRootFrag(int id, ISupportFragment fragment) {
        loadRootFragment(id, fragment);
    }

    @Override
    public <T extends ISupportFragment> T findFragmentIn(Class contentClass) {
        return (T) findFragment(contentClass);
    }
}
