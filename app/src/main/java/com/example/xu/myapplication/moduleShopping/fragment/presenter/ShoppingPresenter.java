package com.example.xu.myapplication.moduleShopping.fragment.presenter;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.moduleShopping.fragment.Bean.FruitBean;
import com.example.xu.myapplication.moduleShopping.fragment.adapter.ShoppingCarAdapter;
import com.example.xu.myapplication.moduleShopping.fragment.viewInterface.IShopping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 逝 on 2017/09/18.
 */

public class ShoppingPresenter extends BasePresenter {
    private IShopping view;
    private List<FruitBean> lists;
    private int a = 0;

    public ShoppingPresenter(IShopping view) {
        this.view = view;
    }

    /**
     * 跳转Activity
     */
    public void toActivity(Class<?> cls) {
        Intent intent = new Intent(view.getCon(), cls);
        view.getAct().startActivity(intent);
    }

    public List<FruitBean> addList() {
        lists = new ArrayList<FruitBean>();
        lists.add(0, new FruitBean("苹果", 2.0, 2));
        lists.add(1, new FruitBean("香蕉", 3.0, 3));
        lists.add(2, new FruitBean("西瓜", 4.0, 1));
        lists.add(3, new FruitBean("葡萄", 3.0, 2));
        lists.add(4, new FruitBean("哈密瓜", 2.0, 1));
        lists.add(5, new FruitBean("荔枝", 5.0, 5));
        lists.add(6, new FruitBean("梨", 3.0, 2));
        lists.add(7, new FruitBean("火龙果", 4.0, 2));
        return lists;
    }

    /**
     * 设置购物车编辑功能
     *
     * @param cbEditor    编辑按键
     * @param cbSelectAll 全选按键
     * @param isChecked   是否被选中
     * @param linear1     第一个LinearLayout布局
     * @param linear2     第二个LinearLayout布局
     */
    public void cbEditorChanged(CheckBox cbEditor, CheckBox cbSelectAll, boolean isChecked,
                                LinearLayout linear1, LinearLayout linear2) {
        if (isChecked) {
            if (cbSelectAll.isChecked()) {
                cbSelectAll.setChecked(false);
            }
            cbEditor.setText(R.string.finish);
            linear1.setVisibility(View.GONE);
            linear2.setVisibility(View.VISIBLE);
        } else {
            if (cbSelectAll.isChecked()) {
                cbSelectAll.setChecked(false);
            }
            cbEditor.setText(R.string.editor);
            linear1.setVisibility(View.VISIBLE);
            linear2.setVisibility(View.GONE);
        }
    }

    /**
     * 设置全选按钮
     *
     * @param isChecked 是否被选中
     * @param map_cb    map
     */
    public void cbSelectAllChanged(boolean isChecked,
                                   Map<Integer, Boolean> map_cb) {
        if (isChecked) {
            for (int i = 0; i < map_cb.size(); i++) {
                map_cb.put(i, true);
            }
        } else {
            if (a == 1) {
                for (int i = 0; i < map_cb.size(); i++) {
                    map_cb.put(i, false);
                }
            }
        }
    }

    /**
     * 计算购物车列表中商品价格总和
     *
     * @param tvShopingMoney 显示总价的TextView控件
     * @param cbSelectAll    全选控件
     * @param map_cb         map
     */
    public void UpdataSum(TextView tvShopingMoney, CheckBox cbSelectAll,
                          Map<Integer, Boolean> map_cb) {
        double sum = 0;
        int size = 0;
        for (int i = 0; i < map_cb.size(); i++) {
            if (map_cb.get(i)) {
                size++;
                sum += lists.get(i).getPrice() * lists.get(i).getNumber();
            }
        }
        tvShopingMoney.setText("￥" + sum);
        if (size == map_cb.size()) {
            cbSelectAll.setChecked(true);
            a = 1;
        } else {
            a = 0;
            cbSelectAll.setChecked(false);
        }
    }

    /**
     * 获取选中商品数量
     * @param map_cb
     * @return 返回选中的数量
     */
    public int getGoodsNum(Map<Integer, Boolean> map_cb) {
        int size = 0;
        for (int i = 0; i < map_cb.size(); i++) {
            if (map_cb.get(i)) {
                size++;
            }
        }
        return size;
    }

    /**
     * 删除商品操作
     * @param adapter
     * @param cbSelectAll
     */
    public void deleteGoods(ShoppingCarAdapter adapter,CheckBox cbSelectAll) {
        for (int i = 0; i < adapter.strs.size(); i++) {
            for (int j = 0; j < lists.size(); j++) {
                if (TextUtils.equals(adapter.strs.get(i), lists.get(j).getFruit())) {
                    lists.remove(j);
                }
            }
        }

        if (cbSelectAll.isChecked()){
            lists.clear();
            cbSelectAll.setChecked(false);
        }
        adapter.setData(lists);
        Log.e("lists", lists.size() + "");
    }
}
