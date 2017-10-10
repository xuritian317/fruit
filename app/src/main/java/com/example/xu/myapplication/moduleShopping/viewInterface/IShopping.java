package com.example.xu.myapplication.moduleShopping.viewInterface;

import android.app.Activity;
import android.content.Context;

import com.example.xu.myapplication.base.BaseViewInterface;
import com.example.xu.myapplication.moduleShopping.bean.FruitBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 逝 on 2017/09/18.
 */

public interface IShopping extends BaseViewInterface {
    /**
     * 获取Context实例
     * @return context
     */
    Context getCon();

    /**
     * 获取Activity实例
     * @return activity
     */
    Activity getAct();


    boolean isRefresh();

    void setRefresh(boolean flag);

    void setAdapterData(List<FruitBean> data);

    void setTvShoppingCartText(String text);

    void setCbSelect(boolean flag);

    void setTvShoppingMoneyText(String text);


}
