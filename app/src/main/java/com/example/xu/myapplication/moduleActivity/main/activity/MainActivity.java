package com.example.xu.myapplication.moduleActivity.main.activity;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseActivity;
import com.example.xu.myapplication.base.BaseMainFragment;
import com.example.xu.myapplication.moduleActivity.main.customer.BottomBar;
import com.example.xu.myapplication.moduleActivity.main.customer.BottomBarTab;
import com.example.xu.myapplication.moduleActivity.main.viewInterface.IMain;
import com.example.xu.myapplication.moduleActivity.main.presenter.MainPresenter;
import com.example.xu.myapplication.moduleHome.fragment.HomeContentFragment;
import com.example.xu.myapplication.moduleHome.fragment.HomeFragment;
import com.example.xu.myapplication.moduleMy.fragment.MyFragment;
import com.example.xu.myapplication.moduleMy.fragment.MyContentFragment;
import com.example.xu.myapplication.moduleShopping.fragment.ShoppingContentFragment;
import com.example.xu.myapplication.moduleShopping.fragment.ShoppingFragment;
import com.example.xu.myapplication.moduleType.fragment.TypeContentFragment;
import com.example.xu.myapplication.moduleType.fragment.TypeFragment;
import com.example.xu.myapplication.util.Logger;
import com.example.xu.myapplication.util.SPUtil;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

public class MainActivity extends BaseActivity<MainPresenter> implements IMain, BaseMainFragment.OnBackToFirstListener {

    private SupportFragment[] mFragments;

    @BindView(R.id.bottomBar)
     BottomBar mBottomBar;
    //记录用户首次点击返回键的时间
    private long firstTime=0;


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

        mBottomBar.addItem(new BottomBarTab(this, R.drawable.ic_home_white_24dp))
                .addItem(new BottomBarTab(this, R.drawable.ic_discover_white_24dp))
                .addItem(new BottomBarTab(this, R.drawable.ic_message_white_24dp))
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
//                    EventBus.getDefault().post(new TabSelectedEvent(position));
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
            ActivityCompat.finishAfterTransition(this);
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

    /**
     * 双击回退
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                long secondTime=System.currentTimeMillis();
                if(secondTime-firstTime>2000){
                    Toast.makeText(MainActivity.this,"再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstTime=secondTime;
                    return true;
                }else{
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

}
