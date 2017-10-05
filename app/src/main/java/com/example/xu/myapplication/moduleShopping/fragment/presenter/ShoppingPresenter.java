package com.example.xu.myapplication.moduleShopping.fragment.presenter;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.JsonResponseHandler;
import com.example.xu.myapplication.httpRequest.response.RawResponseHandler;
import com.example.xu.myapplication.moduleMy.fragment.view.CircleImageView;
import com.example.xu.myapplication.moduleShopping.fragment.bean.FruitBean;
import com.example.xu.myapplication.moduleShopping.fragment.adapter.ShoppingCarAdapter;
import com.example.xu.myapplication.moduleShopping.fragment.dao.ShoppingCarDao;
import com.example.xu.myapplication.moduleShopping.fragment.viewInterface.IShopping;
import com.example.xu.myapplication.util.Logger;
import com.example.xu.myapplication.util.SPUtil;
import com.example.xu.myapplication.util.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 逝 on 2017/09/18.
 */

public class ShoppingPresenter extends BasePresenter {
    private IShopping view;
    private List<FruitBean> lists;
    private int a = 0;

    private SPUtil util;


    public ShoppingPresenter(IShopping view) {
        this.view = view;
        util=new SPUtil(this.view.getCon());
    }

    /**
     * 跳转Activity
     */
    public void toActivity(Class<?> cls) {
        int size = 0;
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).isChecked()) {
                size++;
            }
        }
        if (size == 0) {
            ToastUtils.showToast(view.getCon(), "请选择商品哦！");
            return;
        }
        Intent intent = new Intent(view.getCon(), cls);
        view.getAct().startActivity(intent);
    }

    public void addList(final ShoppingCarAdapter adapter, final TextView tvShopingCart) {
        lists = new ArrayList<FruitBean>();
        String phone = util.getString(SPUtil.IS_USER, "");
        if (TextUtils.equals(util.getString(SPUtil.IS_USER, ""), "")) {
            return;
        }
        JSONObject jo = new JSONObject();
        try {
            jo.put("phoneNumber", phone);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MyOkHttp.newInstance().postJson(Common.URL_GET_USER, jo, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                try {
                    JSONArray array=new JSONArray(response.getString("shoppingCars"));
                    FruitBean bean=null;
                    JSONObject object;
                    JSONObject json;
                    for (int i = 0; i < array.length(); i++) {
                        object=array.getJSONObject(i);
                        int count=object.getInt("goodsCount");
                        String goods=object.getString("goods");
                        json=new JSONObject(goods);
                        String goodsName=json.getString("goodsName");
                        double goodsPrice=json.getDouble("goodsPrice");
                        String goodsImage=json.getString("goodsImage");
                        bean=new FruitBean(goodsName,goodsPrice,count,goodsImage);
                        lists.add(bean);
                    }

                    tvShopingCart.setText("购物车("+array.length()+")");
                    adapter.setData(lists);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {

            }
        });
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
     */
    public void cbSelectAllChanged(boolean isChecked) {
        if (isChecked) {
            for (int i = 0; i < lists.size(); i++) {
                lists.get(i).setChecked(true);
            }
        } else {
            if (a == 1) {
                for (int i = 0; i < lists.size(); i++) {
                    lists.get(i).setChecked(false);
                }
            }
        }
    }

    /**
     * 计算购物车列表中商品价格总和
     *
     * @param tvShopingMoney 显示总价的TextView控件
     * @param cbSelectAll    全选控件
     */
    public void UpdataSum(TextView tvShopingMoney, CheckBox cbSelectAll) {
        double sum = 0;
        int size = 0;
        BigDecimal bd;
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).isChecked()) {
                size++;
                /**
                 * 解决 double 进行运算时，经常出现精度丢失的问题
                 */
                bd = new BigDecimal(Double.toString(sum));
                sum = bd.add(new BigDecimal(Double.toString(lists.get(i).getPrice()))
                        .multiply(new BigDecimal(Double.toString(lists.get(i).getNumber()))))
                        .doubleValue();
            }
        }
        tvShopingMoney.setText("￥" + sum);
        if (size == lists.size()) {
            cbSelectAll.setChecked(true);
            a = 1;
        } else {
            a = 0;
            cbSelectAll.setChecked(false);
        }
    }

    /**
     * 获取选中商品数量
     *
     * @return 返回选中的数量
     */
    public int getGoodsNum() {
        int size = 0;
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).isChecked()) {
                size++;
            }
        }
        return size;
    }

    /**
     * 删除商品操作
     *
     * @param adapter
     * @param cbSelectAll
     */
    public void deleteGoods(ShoppingCarAdapter adapter, CheckBox cbSelectAll) {
        for (int i = 0; i < adapter.strs.size(); i++) {
            for (int j = 0; j < lists.size(); j++) {
                if (TextUtils.equals(adapter.strs.get(i), lists.get(j).getFruit())) {
                    lists.remove(j);
                }
            }
        }

        if (cbSelectAll.isChecked()) {
            lists.clear();
            cbSelectAll.setChecked(false);
        }
        adapter.setData(lists);
        Log.e("lists", lists.size() + "");
    }
}
