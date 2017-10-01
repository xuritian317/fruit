package com.example.xu.myapplication.moduleType.viewInterface;

import com.example.xu.myapplication.base.BaseViewInterface;
import com.example.xu.myapplication.moduleType.fragment.MenuContentFragment;

/**
 * Created by xu on 2017/9/13.
 */

public interface IMenuList extends BaseViewInterface {
    void setCurrentPosition(int position);
    void setAdapterCheck(int position);
    int getCurrentPosition();
    void switchFragment(MenuContentFragment fragment);
}
