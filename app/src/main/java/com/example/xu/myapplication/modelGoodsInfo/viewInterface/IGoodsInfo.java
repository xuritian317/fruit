package com.example.xu.myapplication.modelGoodsInfo.viewInterface;

import com.example.xu.myapplication.base.BaseViewInterface;
import com.example.xu.myapplication.moduleType.entity.Fruit;

import java.util.ArrayList;

/**
 * Created by xu on 2017/10/6.
 */

public interface IGoodsInfo extends BaseViewInterface{
    void onSuccessToast(String msg);
    void setRecyclerData(ArrayList<Fruit.FruitDetail> fruitDetails);
}
