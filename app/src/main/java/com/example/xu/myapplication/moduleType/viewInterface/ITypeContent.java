package com.example.xu.myapplication.moduleType.viewInterface;

import com.example.xu.myapplication.base.BaseViewInterface;
import me.yokeyword.fragmentation.ISupportFragment;

/**
 * Created by xu on 2017/9/13.
 */

public interface ITypeContent extends BaseViewInterface {

    <T extends ISupportFragment> T findFragmentIn(Class contentClass);

    void loadRootFrag(int id ,ISupportFragment fragment);

    void loadRootFrag(int id ,ISupportFragment fragment,boolean backFlag,boolean animFlag);

}
