package com.example.xu.myapplication.moduleType.viewInterface;

import com.example.xu.myapplication.base.BaseViewInterface;

import me.yokeyword.fragmentation.ISupportFragment;

/**
 * Created by xu on 2017/9/13.
 */

public interface IType extends BaseViewInterface {

    void loadRootFrag(int id, ISupportFragment fragment);

    <T extends ISupportFragment> T findFragmentIn(Class contentClass);
}
