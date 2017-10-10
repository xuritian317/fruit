package com.example.xu.myapplication.moduleActivity.main.presenter;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.moduleActivity.main.viewInterface.IMain;
import com.example.xu.myapplication.moduleHome.HomeFragment;
import com.example.xu.myapplication.moduleMy.MyFragment;
import com.example.xu.myapplication.moduleShopping.ShoppingFragment;
import com.example.xu.myapplication.moduleType.fragment.TypeFragment;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by xu on 2017/9/11.
 */

public class MainPresenter extends BasePresenter {
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;

    private IMain view;

    public MainPresenter(IMain view) {
        this.view = view;
    }

    public void loadRootFragment(SupportFragment homeFragment) {
        if (homeFragment == null) {
            view.setFragCollections(HomeFragment.newInstance(), FIRST);
            view.setFragCollections(TypeFragment.newInstance(), SECOND);
            view.setFragCollections(ShoppingFragment.newInstance(), THIRD);
            view.setFragCollections(MyFragment.newInstance(), FOURTH);

            view.loadRootFragment(R.id.container_activity_main, FIRST,
                    view.getFragCollections(FIRST),
                    view.getFragCollections(SECOND),
                    view.getFragCollections(THIRD),
                    view.getFragCollections(FOURTH));
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用,也可以通过getSupportFragmentManager.findFragmentByTag()自行进行判断查找(效率更高些),用下面的方法查找更方便些

            view.setFragCollections(homeFragment, FIRST);
            view.setFragCollections(view.findFragmentIn(TypeFragment.class), SECOND);
            view.setFragCollections(view.findFragmentIn(ShoppingFragment.class), THIRD);
            view.setFragCollections(view.findFragmentIn(MyFragment.class), FOURTH);
        }
    }

    public void showHideFrag(int position, int prePosition) {
        view.showHideFrag(position, prePosition);
    }
}
