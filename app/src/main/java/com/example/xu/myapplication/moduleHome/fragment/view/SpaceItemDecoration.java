package com.example.xu.myapplication.moduleHome.fragment.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 逝 on 2017/06/19.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration{

    private int space;
    private int left;

    public SpaceItemDecoration(int space, int left) {
        this.space = space;
        this.left=left;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        outRect.left = left;
        outRect.right = left;
        outRect.bottom = space;
        if(parent.getChildPosition(view) != 0)
            outRect.top = space;
    }
}