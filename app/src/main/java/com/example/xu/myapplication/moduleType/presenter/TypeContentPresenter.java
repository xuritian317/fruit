package com.example.xu.myapplication.moduleType.presenter;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.moduleType.entity.Fruit;
import com.example.xu.myapplication.moduleType.fragment.MenuContentFragment;
import com.example.xu.myapplication.moduleType.fragment.MenuListFragment;
import com.example.xu.myapplication.moduleType.viewInterface.ITypeContent;

import java.util.ArrayList;

/**
 * Created by xu on 2017/9/13.
 */

public class TypeContentPresenter extends BasePresenter {

    private ITypeContent view;

    public TypeContentPresenter(ITypeContent view) {
        this.view = view;
    }

    public void init(Class contentClass, final ArrayList<String> menuList) {
        if (view.findFragmentIn(contentClass) == null) {
            MenuListFragment menuListFragment = MenuListFragment.newInstance(menuList);
            view.loadRootFrag(R.id.container_list_type, menuListFragment);
            // false:  不加入回退栈;  false: 不显示动画
            ArrayList<Fruit> goodsList= new ArrayList<>();
            ArrayList<Fruit.FruitDetail> details = new ArrayList<Fruit.FruitDetail>();
            details.add(new Fruit.FruitDetail("","",""));
            goodsList.add(new Fruit("",details));
            view.loadRootFrag(R.id.container_content_type, MenuContentFragment.newInstance(goodsList), false, false);
        }
    }

}
