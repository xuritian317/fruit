package com.example.xu.myapplication.moduleHome.dao;

import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.GsonResponseHandler;
import com.example.xu.myapplication.model.Fruit;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by xu on 2017/10/8.
 */

public class GoodsLikeDao {

    private CallBackGoodsLike callback;

    private static GoodsLikeDao instance;

    private GoodsLikeDao(CallBackGoodsLike callback) {
        this.callback = callback;
    }

    public static GoodsLikeDao newInstance(CallBackGoodsLike callback) {
        if (instance == null) {
            synchronized (GoodsLikeDao.class) {
                if (instance == null) {
                    instance = new GoodsLikeDao(callback);
                }
            }
        }
        return instance;
    }

    public interface CallBackGoodsLike {
        void onSuccess(ArrayList<Fruit.FruitDetail> response);

        void onFailure(String message);
    }

    public void getGoodsLike(String fruitName) {

        try {
            MyOkHttp.newInstance().postJson(Common.URL_GOODS_LIKE, new JSONObject().put("goodsName", fruitName), new GsonResponseHandler<ArrayList<Fruit.FruitDetail>>() {
                @Override
                public void onSuccess(int statusCode, ArrayList<Fruit.FruitDetail> response) {
                    callback.onSuccess(response);
                }

                @Override
                public void onFailure(int statusCode, String error_msg) {
                    callback.onFailure(statusCode + "\t" + error_msg);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
