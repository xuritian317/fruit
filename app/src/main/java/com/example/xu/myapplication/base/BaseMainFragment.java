package com.example.xu.myapplication.base;

import android.content.Context;

import com.example.xu.myapplication.moduleHome.HomeFragment;
import com.example.xu.myapplication.util.ToastUtils;

/**
 * 懒加载
 * Created by YoKeyword on 16/6/5.
 */
public abstract class BaseMainFragment<T extends BasePresenter> extends BaseFragment {
    public T presenter;

    protected OnBackToFirstListener _mBackToFirstListener;
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnBackToFirstListener) {
            _mBackToFirstListener = (OnBackToFirstListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnBackToFirstListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        _mBackToFirstListener = null;
    }

    /**
     * 处理回退事件
     *
     * @return
     */
    @Override
    public boolean onBackPressedSupport() {
        if (getChildFragmentManager().getBackStackEntryCount() > 1) {
            popChild();
        } else {
            if (this instanceof HomeFragment) {   // 如果是 第一个Fragment 则退出app
                if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
                    System.exit(0);
                } else {
                    TOUCH_TIME = System.currentTimeMillis();
                    ToastUtils.showToast(_mActivity, "再次点击退出程序");
                }

            } else {                                    // 如果不是,则回到第一个Fragment
                _mBackToFirstListener.onBackToFirstFragment();
            }
        }
        return true;
    }

    public interface OnBackToFirstListener {
        void onBackToFirstFragment();
    }
}
