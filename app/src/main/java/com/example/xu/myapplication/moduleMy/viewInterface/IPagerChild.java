package com.example.xu.myapplication.moduleMy.viewInterface;

import android.content.Context;
import android.content.Intent;

import com.example.xu.myapplication.base.BaseViewInterface;

/**
 * Created by 逝 on 2017/09/18.
 */

public interface IPagerChild extends BaseViewInterface {
    /**
     * 获取activity实例
     * @return activity
     */
    Context getCon();

    /**
     * 获取 startActivi()
     * @param intent
     */
    void toStartActivity(Intent intent);
}
