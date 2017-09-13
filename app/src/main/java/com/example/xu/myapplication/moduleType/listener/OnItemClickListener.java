package com.example.xu.myapplication.moduleType.listener;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by xu on 2017/9/13.
 */

public interface OnItemClickListener {
    void onItemClick(int position, View view, RecyclerView.ViewHolder vh);
}
