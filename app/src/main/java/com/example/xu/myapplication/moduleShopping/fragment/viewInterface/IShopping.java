package com.example.xu.myapplication.moduleShopping.fragment.viewInterface;

import android.content.Context;
import android.content.Intent;

import com.example.xu.myapplication.base.BaseViewInterface;
import com.example.xu.myapplication.moduleShopping.fragment.Bean.FruitBean;

import java.util.List;

/**
 * Created by 逝 on 2017/09/18.
 */

public interface IShopping extends BaseViewInterface {
    /**
     *
     */
//    List<FruitBean> getList();

    Context getCon();
    void toStartActivity(Intent intent);
}
