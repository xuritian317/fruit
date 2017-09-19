package com.example.xu.myapplication.moduleShopping.fragment.presenter;

import android.content.Intent;

import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.moduleShopping.fragment.Bean.FruitBean;
import com.example.xu.myapplication.moduleShopping.fragment.viewInterface.IShopping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 逝 on 2017/09/18.
 */

public class ShoppingPresenter extends BasePresenter {
    private IShopping view;

    public ShoppingPresenter(IShopping view) {
        this.view = view;
    }

    public void setLists(List<FruitBean> lists){
        lists =new ArrayList<FruitBean>();
        lists.add(0, new FruitBean("苹果", 2.0, 2));
        lists.add(1, new FruitBean("香蕉", 3.0, 3));
        lists.add(2, new FruitBean("西瓜", 4.0, 1));
        lists.add(3, new FruitBean("葡萄", 3.0, 2));
        lists.add(4, new FruitBean("哈密瓜", 2.0, 1));
        lists.add(5, new FruitBean("荔枝", 5.0, 5));
        lists.add(6, new FruitBean("梨", 3.0, 2));
        lists.add(7, new FruitBean("火龙果", 4.0, 2));
    }

    /**
     *跳转Activity
     */
    public void toActivity(Class<?> cls){
        Intent intent = new Intent(view.getCon(), cls);
        view.toStartActivity(intent);
    }
}
