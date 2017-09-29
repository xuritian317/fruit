package com.example.xu.myapplication.moduleType.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseFragment;
import com.example.xu.myapplication.moduleType.presenter.TypeContentPresenter;
import com.example.xu.myapplication.moduleType.viewInterface.ITypeContent;

import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class TypeContentFragment extends BaseFragment<TypeContentPresenter> implements ITypeContent {


    public static TypeContentFragment newInstance() {
        TypeContentFragment instance = new TypeContentFragment();
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
        presenter.init(MenuListFragment.class);
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


    @Override
    public boolean onBackPressedSupport() {
        return false;
    }

    @Override
    public <T extends ISupportFragment> T findFragmentIn(Class contentClass) {
        return (T) findFragment(contentClass);
    }

    @Override
    public void loadRootFrag(int id, ISupportFragment fragment) {
        loadRootFragment(id, fragment);
    }

    @Override
    public void loadRootFrag(int id, ISupportFragment fragment, boolean backFlag, boolean animFlag) {
        loadRootFragment(id, fragment, backFlag, animFlag);
    }

}
