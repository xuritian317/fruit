package com.example.xu.myapplication.moduleType.presenter;

import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.moduleType.viewInterface.ITypeContent;

/**
 * Created by xu on 2017/9/13.
 */

public class TypeContentPresenter extends BasePresenter {
    private ITypeContent view;

    public TypeContentPresenter(ITypeContent view) {
        this.view = view;
    }
}
