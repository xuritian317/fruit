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
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.RawResponseHandler;
import com.example.xu.myapplication.moduleShopping.fragment.bean.FruitBean;
import com.example.xu.myapplication.moduleShopping.fragment.adapter.ShoppingCarAdapter;
import com.example.xu.myapplication.moduleShopping.fragment.dao.ShoppingCarDao;
import com.example.xu.myapplication.moduleShopping.fragment.viewInterface.IShopping;
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

    public ShoppingPresenter(IShopping view) {
        this.view = view;
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

    public List<FruitBean> addList(final TextView tvShopingCart) {
        lists = new ArrayList<FruitBean>();
        MyOkHttp.newInstance().get("http://www.mobilebooks.cn/api/t-shopping-cars", null, new
                RawResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, String response) {
                        Log.e("shopping", statusCode+"");
                        try {
                            JSONArray array = new JSONArray(response);
                            Log.e("array", array.length() + "");
                            JSONObject jo = null;
                            JSONObject jo_goods = null;
                            for (int i = 0; i < array.length(); i++) {
                                jo = array.getJSONObject(i);
                                FruitBean info = new FruitBean();
                                info.setNumber(jo.getInt("goodsCount"));
                                String good = jo.getString("goods");
                                jo_goods = new JSONObject(good);
                                info.setFruit(jo_goods.getString("goodsName"));
                                info.setPrice(jo_goods.getDouble("goodsPrice"));
                                info.setFruit_img(jo_goods.getString("goodsImage"));
                                info.setChecked(false);
                                lists.add(info);
                            }
                            tvShopingCart.setText("购物车(" + lists.size() + ")");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });
//        ShoppingCarDao.newInstance(new ShoppingCarDao.CallBackShoppingCarDao() {
//            @Override
//            public void onSuccess(String response) {
//                try {
//                    JSONArray array = new JSONArray(response);
//                    Log.e("array", array.length() + "");
//                    JSONObject jo = null;
//                    JSONObject jo_goods = null;
//                    for (int i = 0; i < array.length(); i++) {
//                        jo = array.getJSONObject(i);
//                        FruitBean info = new FruitBean();
//                        info.setNumber(jo.getInt("goodsCount"));
//                        String good = jo.getString("goods");
//                        jo_goods = new JSONObject(good);
//                        info.setFruit(jo_goods.getString("goodsName"));
//                        info.setPrice(jo_goods.getDouble("goodsPrice"));
//                        info.setFruit_img(jo_goods.getString("goodsImage"));
//                        info.setChecked(false);
//                        lists.add(info);
//                    }
//                    tvShopingCart.setText("购物车(" + lists.size() + ")");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(String message) {
//
//            }
//        }).getFruit();
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
