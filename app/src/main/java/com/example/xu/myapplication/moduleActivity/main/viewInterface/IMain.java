package com.example.xu.myapplication.moduleActivity.main.viewInterface;

import com.example.xu.myapplication.base.BaseViewInterface;

import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by xu on 2017/9/11.
 */

public interface IMain extends BaseViewInterface {

    /**
     * @return 返回mFragment集合
     */
    SupportFragment[] getFragCollections();

    /**
     *  设置集合中position位置上元素
     * @param position 集合中位置
     */
    void setFragCollections(SupportFragment fragment, int position);


    /**
     * @param position 集合中位置
     * @return  获取集合中position位置上元素
     */
    SupportFragment getFragCollections(int position);

    /** 
     * 加载多个同级根Fragment
     * @param containerId
     * @param showPosition
     * @param toFragments
     */
    void loadRootFragment(int containerId, int showPosition, SupportFragment... toFragments);
    
    /**
     * 获取栈内的fragment对象
     */
    SupportFragment findFragmentIn(Class fragmentClass);


    void showHideFrag(int position, int prePosition);
}
