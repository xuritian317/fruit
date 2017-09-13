package com.example.xu.myapplication.moduleType.presenter;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.moduleType.fragment.TypeContentFragment;
import com.example.xu.myapplication.moduleType.viewInterface.IType;

/**
 * Created by xu on 2017/9/13.
 */

public class TypePresenter extends BasePresenter {
    private IType view;

    public TypePresenter(IType view) {
        this.view = view;
    }

    public void loadRootFrag(Class contentClass) {
        if (view.findFragmentIn(contentClass) == null)
            view.loadRootFrag(R.id.container_type_main, TypeContentFragment.newInstance());
    }
}
