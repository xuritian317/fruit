package com.example.xu.myapplication.modelGoodsInfo.dao;

import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.RawResponseHandler;
import com.example.xu.myapplication.util.SPUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xu on 2017/10/6.
 */

public class ShoppingCarDao {
    private CallBackShopping callback;

    private static ShoppingCarDao instance;

    private ShoppingCarDao(CallBackShopping callback) {
        this.callback = callback;
    }

    public static ShoppingCarDao newInstance(CallBackShopping callback) {
        // if already inited, no need to get lock everytime
        if (instance == null) {
            synchronized (ShoppingCarDao.class) {
                if (instance == null) {
                    instance = new ShoppingCarDao(callback);
                }
            }
        }
        return instance;
    }

    public interface CallBackShopping {
        void onSuccess(String message);

        void onFailure(String message);
    }

    public void postToShoppingCar(int userId, int count, int id) {
        try {
            MyOkHttp.newInstance().postJson(Common.URL_POST_SHOPPING, new JSONObject().put("goodsId", id).put("goodsCount", count).put("userId", userId), new RawResponseHandler() {
                @Override
                public void onSuccess(int statusCode, String response) {
                    callback.onSuccess(statusCode + "\t" + response);
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
