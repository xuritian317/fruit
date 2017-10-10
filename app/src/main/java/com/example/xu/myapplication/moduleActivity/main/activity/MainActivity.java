package com.example.xu.myapplication.moduleActivity.main.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseActivity;
import com.example.xu.myapplication.base.BaseMainFragment;
import com.example.xu.myapplication.moduleActivity.main.customer.BottomBar;
import com.example.xu.myapplication.moduleActivity.main.customer.BottomBarTab;
import com.example.xu.myapplication.moduleActivity.main.eventbus.TabSelectedEvent;
import com.example.xu.myapplication.moduleActivity.main.viewInterface.IMain;
import com.example.xu.myapplication.moduleActivity.main.presenter.MainPresenter;
import com.example.xu.myapplication.moduleHome.HomeContentFragment;
import com.example.xu.myapplication.moduleHome.HomeFragment;
import com.example.xu.myapplication.moduleMy.MyFragment;
import com.example.xu.myapplication.moduleMy.MyContentFragment;
import com.example.xu.myapplication.moduleShopping.ShoppingContentFragment;
import com.example.xu.myapplication.moduleShopping.ShoppingFragment;
import com.example.xu.myapplication.moduleType.fragment.TypeContentFragment;
import com.example.xu.myapplication.moduleType.fragment.TypeFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

public class MainActivity extends BaseActivity<MainPresenter> implements IMain, BaseMainFragment.OnBackToFirstListener {

    private SupportFragment[] mFragments;

    @BindView(R.id.bottomBar)
     BottomBar mBottomBar;
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;


    @Override
    public void setPresenter() {
        presenter = new MainPresenter(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        mFragments = new SupportFragment[4];
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        SupportFragment homeFragment = findFragment(HomeFragment.class);

        presenter.loadRootFragment(homeFragment);

        mBottomBar.addItem(new BottomBarTab(this, R.drawable.ic_home))
                .addItem(new BottomBarTab(this, R.drawable.ic_message_white_24dp))
                .addItem(new BottomBarTab(this, R.drawable.ic_shopping))
                .addItem(new BottomBarTab(this, R.drawable.ic_account_circle_white_24dp));

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                presenter.showHideFrag(position,prePosition);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                SupportFragment currentFragment = mFragments[position];
                int count = currentFragment.getChildFragmentManager().getBackStackEntryCount();
                // 如果不在该类别Fragment的主页,则回到主页;
//                presenter.popToChild(count);
                if (count > 1) {
                    if (currentFragment instanceof HomeFragment) {
                        currentFragment.popToChild(HomeContentFragment.class, false);
                    } else if (currentFragment instanceof TypeFragment) {
                        currentFragment.popToChild(TypeContentFragment.class, false);
                    } else if (currentFragment instanceof ShoppingFragment) {
                        currentFragment.popToChild(ShoppingContentFragment.class, false);
                    } else if (currentFragment instanceof MyFragment) {
                        currentFragment.popToChild(MyContentFragment.class, false);
                    }
                    return;
                }

//                // 这里推荐使用EventBus来实现 -> 解耦
                if (count == 1) {
                    // 在FirstPagerFragment中接收, 因为是嵌套的孙子Fragment 所以用EventBus比较方便
                    // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
                    EventBus.getDefault().post(new TabSelectedEvent(position));
                }
            }
        });
    }

    @Override
    public void setToolbar() {

    }

    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
                System.exit(0);
            } else {
                TOUCH_TIME = System.currentTimeMillis();
                Toast.makeText(this, "再次点击退出程序！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackToFirstFragment() {
        mBottomBar.setCurrentItem(0);
    }

    @Override
    public SupportFragment[] getFragCollections() {
        return mFragments;
    }

    @Override
    public void setFragCollections(SupportFragment fragment, int position) {
        mFragments[position] = fragment;
    }

    @Override
    public SupportFragment getFragCollections(int position) {
        return mFragments[position];
    }

    @Override
    public void loadRootFragment(int containerId, int showPosition, SupportFragment... toFragments) {
        loadMultipleRootFragment(containerId,showPosition,toFragments);
    }

    @Override
    public SupportFragment findFragmentIn(Class fragmentClass) {
        return (SupportFragment) findFragment(fragmentClass);
    }

    @Override
    public void showHideFrag(int position, int prePosition) {
        showHideFragment(mFragments[position], mFragments[prePosition]);
    }

}
