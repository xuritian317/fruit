package com.example.xu.myapplication.moduleMy.fragment.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.xu.myapplication.moduleMy.fragment.child_frag.PagerChildFragment;

/**
 * Created by 逝 on 2017/09/16.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private String[] mTitles;

    public FragmentAdapter(FragmentManager fm, String... titles) {
        super(fm);
        this.mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return PagerChildFragment.newInstance(0);
        } else if (position == 1) {
            return PagerChildFragment.newInstance(1);
        } else if (position == 2) {
            return PagerChildFragment.newInstance(2);
        } else if (position == 3) {
            return PagerChildFragment.newInstance(3);
        } else if (position == 4) {
            return PagerChildFragment.newInstance(4);
        } else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
