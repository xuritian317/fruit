package com.example.xu.myapplication.moduleHome.fragment.viewInterface;

import android.app.Activity;
import android.content.Context;

import com.example.xu.myapplication.base.BaseViewInterface;

/**
 * Created by 逝 on 2017/09/30.
 */

public interface IHome extends BaseViewInterface {
    /**
     * 获取activity实例
     * @return activity
     */
    Context getCon();

    /**
     * 获取Activity实例
     * @return activity
     */
    Activity getAct();
}
