package com.example.xu.myapplication.moduleType.presenter;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.moduleType.dao.FruitTypeDao;
import com.example.xu.myapplication.moduleType.entity.FruitType;
import com.example.xu.myapplication.moduleType.fragment.MenuContentFragment;
import com.example.xu.myapplication.moduleType.fragment.MenuListFragment;
import com.example.xu.myapplication.moduleType.viewInterface.ITypeContent;
import com.example.xu.myapplication.util.Logger;

import java.util.ArrayList;

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

            FruitTypeDao.newInstance(new FruitTypeDao.CallBackFruitType() {
                @Override
                public void onSuccess(ArrayList<FruitType> response) {
                    Logger.e("onSuccess",response.size()+"\tsuccess for getting data from internet");

                    MenuListFragment menuListFragment = MenuListFragment.newInstance(response);
                    view.loadRootFrag(R.id.container_list_type, menuListFragment);
                    // false:  不加入回退栈;  false: 不显示动画
                    view.loadRootFrag(R.id.container_content_type, MenuContentFragment.newInstance(response.get(0).getGoods()), false, false);

                }
                @Override
                public void onFailure(String message) {
                    Logger.e("onFailure",message);
                }
            }).getFruitType();

        }
    }

}
