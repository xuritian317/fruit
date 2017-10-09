package com.example.xu.myapplication.moduleHome.view;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

/**
 * Created by 逝 on 2017/10/08.
 */

public class MyRefreshLayout extends SwipeRefreshLayout {
    private static final String TAG = MyRefreshLayout.class.getCanonicalName();

    public MyRefreshLayout(Context context) {
        super(context);
    }

    public MyRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean canChildScrollUp() {
        return canChildScrollUp(this);
    }

    public boolean canChildScrollUp(ViewGroup v) {
        for (int i = 0; i < v.getChildCount(); i++) {
            View temp = v.getChildAt(i);
            if (temp instanceof RecyclerView) {
                if (canRecycleViewScroll((RecyclerView) temp)) return true;
            } else if (temp instanceof AbsListView){
                if (ViewCompat.canScrollVertically(temp, -1))return true;
            }else if (temp instanceof ViewGroup) {
                if (canChildScrollUp((ViewGroup) temp)) return true;
            } else if (ViewCompat.canScrollVertically(temp, -1)) return true;
        }
        return false;
    }

    public boolean canRecycleViewScroll(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        return ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition() != 0;

    }

}
