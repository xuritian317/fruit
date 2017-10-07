package com.example.xu.myapplication.moduleType.presenter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.moduleType.adapter.ContentAdapter;
import com.example.xu.myapplication.moduleType.adapter.ContentMinusAdapter;
import com.example.xu.myapplication.moduleType.entity.Fruit;
import com.example.xu.myapplication.moduleType.viewInterface.IMenuContent;

import java.util.ArrayList;

/**
 * Created by xu on 2017/10/7.
 */

public class MenuContentPresenter extends BasePresenter {
    private IMenuContent view;

    public MenuContentPresenter(IMenuContent view) {
        this.view = view;
    }

    public void showDialog(Context context, ArrayList<Fruit.FruitDetail> addList) {
        if (addList.isEmpty() || addList.size() == 0) {
            view.showToast("请先添加商品");
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("自定义套餐");
        builder.setCancelable(true);
        View view = LayoutInflater.from(context).inflate(R.layout.item_menu_content_add, null);
        RecyclerView recycler_add = (RecyclerView) view.findViewById(R.id.recycler_add);
        RecyclerView recycler_recommend = (RecyclerView) view.findViewById(R.id.recycler_recommend);

        GridLayoutManager manager = new GridLayoutManager(context, 3);
        recycler_add.setLayoutManager(manager);
        ContentMinusAdapter adapter = new ContentMinusAdapter(context);
        recycler_add.setAdapter(adapter);
        adapter.setData(addList, true);

        builder.setView(view);

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setPositiveButton("加入购物车", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
}
