package com.example.xu.myapplication.moduleType.viewInterface;

import android.content.Context;

import com.example.xu.myapplication.base.BaseViewInterface;
import com.example.xu.myapplication.model.Fruit;

import java.util.ArrayList;

/**
 * Created by xu on 2017/10/7.
 */

public interface IMenuContent extends BaseViewInterface {
    void showToast(String msg);
    void setTvCount(int count);
    boolean isEmptyAddList();
    int getAddListCount();
    void setAddListItem(int position, Fruit.FruitDetail fruitDetail);
    void removeAddListItem(int position);
    Fruit.FruitDetail getAddListItem(int position);
    Context getMenuContext();
    ArrayList<Fruit.FruitDetail> getAddList();
    void addItem(Fruit.FruitDetail fruitDetail);
}
