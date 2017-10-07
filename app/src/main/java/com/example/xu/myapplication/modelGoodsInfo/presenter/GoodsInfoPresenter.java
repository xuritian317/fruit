package com.example.xu.myapplication.modelGoodsInfo.presenter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;

import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.modelGoodsInfo.dao.CollectionDao;
import com.example.xu.myapplication.modelGoodsInfo.dao.RecommendDao;
import com.example.xu.myapplication.modelGoodsInfo.dao.ShoppingCarDao;
import com.example.xu.myapplication.modelGoodsInfo.viewInterface.IGoodsInfo;
import com.example.xu.myapplication.moduleType.adapter.ContentAdapter;
import com.example.xu.myapplication.moduleType.entity.Fruit;
import com.example.xu.myapplication.util.Logger;
import com.example.xu.myapplication.util.SPUtil;

import java.util.ArrayList;

/**
 * Created by xu on 2017/10/6.
 */

public class GoodsInfoPresenter extends BasePresenter {
    private IGoodsInfo view;

    public GoodsInfoPresenter(IGoodsInfo view) {
        this.view = view;
    }

    public void postToShopping(Context context, int id, int count) {
        SPUtil util = new SPUtil(context);
        int userId = Integer.parseInt(util.getString(SPUtil.USER_ID, "-1"));
        if (userId == -1) {
            view.onSuccessToast("请先登录");
            return;
        }
        ShoppingCarDao.newInstance(new ShoppingCarDao.CallBackShopping() {
            @Override
            public void onSuccess(String message) {
                Logger.e("onSuccess", message);
                view.onSuccessToast("加入购物车成功");
            }

            @Override
            public void onFailure(String message) {
                Logger.e("onFailure", message);
                view.onSuccessToast("加入购物车失败");
            }
        }).postToShoppingCar(userId, count, id);
    }

    public void postToCollection(Context context, int id) {
        SPUtil util = new SPUtil(context);
        int userId = Integer.parseInt(util.getString(SPUtil.USER_ID, "-1"));
        if (userId == -1) {
            view.onSuccessToast("请先登录");
            return;
        }
        CollectionDao.newInstance(new CollectionDao.CallBackCollection() {
            @Override
            public void onSuccess(String message) {
                Logger.e("onSuccess", message);
                view.onSuccessToast("收藏成功");
            }

            @Override
            public void onFailure(String message) {
                Logger.e("onFailure", message);
                view.onSuccessToast("收藏失败");
            }
        }).postToCollection(userId, id);
    }


    public void getRecycleData(int id) {
        RecommendDao.newInstance(new RecommendDao.CallBackRecommend() {
            @Override
            public void onSuccess(ArrayList<Fruit.FruitDetail> fruitDetails) {
                Logger.e("getRecycleData onSuccess", "fruitDetails"+fruitDetails.size()+fruitDetails.toString());
            }

            @Override
            public void onFailure(String message) {
                Logger.e("getRecycleData onFailure", message);
            }
        }).getRecommend(id);
    }
}
