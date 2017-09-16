package com.example.xu.myapplication.moduleType.presenter;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.moduleType.fragment.MenuContentFragment;
import com.example.xu.myapplication.moduleType.fragment.MenuListFragment;
import com.example.xu.myapplication.moduleType.viewInterface.ITypeContent;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by xu on 2017/9/13.
 */

public class TypeContentPresenter extends BasePresenter {

    private ITypeContent view;

    public TypeContentPresenter(ITypeContent view) {
        this.view = view;
    }

    public void init(Class contentClass) {
        if (view.findFragmentIn(contentClass) == null) {
            ArrayList<String> listMenus = new ArrayList<>(Arrays.asList(view.getResourceList()));
            MenuListFragment menuListFragment = MenuListFragment.newInstance(listMenus);
            view.loadRootFrag(R.id.container_list_type, menuListFragment);
            // false:  不加入回退栈;  false: 不显示动画
            view.loadRootFrag(R.id.container_content_type, MenuContentFragment.newInstance(listMenus.get(0)), false, false);
        }
    }

}
