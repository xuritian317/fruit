package com.example.xu.myapplication.moduleType.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseFragment;
import com.example.xu.myapplication.moduleType.presenter.TypeContentPresenter;
import com.example.xu.myapplication.moduleType.viewInterface.ITypeContent;
import com.example.xu.myapplication.util.Logger;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

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
        if (findChildFragment(MenuListFragment.class) == null) {
            ArrayList<String> listMenus = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.array_menu)));
            MenuListFragment menuListFragment = MenuListFragment.newInstance(listMenus);
            loadRootFragment(R.id.container_list_type, menuListFragment);
            // false:  不加入回退栈;  false: 不显示动画
            loadRootFragment(R.id.container_content_type, MenuContentFragment.newInstance(listMenus.get(0)), false, false);
        }
    }

    @Override
    public void setToolbar() {
        toolbar.setTitle(R.string.type);

    }

    public void switchContentFragment(MenuContentFragment fragment) {
        SupportFragment contentFragment = findChildFragment(MenuContentFragment.class);
        if (contentFragment != null) {
            contentFragment.replaceFragment(fragment, false);
        }
    }
}
