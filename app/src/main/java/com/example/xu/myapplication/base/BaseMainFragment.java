package com.example.xu.myapplication.base;

import android.content.Context;

import com.example.xu.myapplication.moduleHome.fragment.HomeFragment;

/**
 * 懒加载
 * Created by YoKeyword on 16/6/5.
 */
public abstract class BaseMainFragment<T extends BasePresenter> extends BaseFragment {
    public T presenter;

    protected OnBackToFirstListener _mBackToFirstListener;

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
                _mActivity.finish();
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
