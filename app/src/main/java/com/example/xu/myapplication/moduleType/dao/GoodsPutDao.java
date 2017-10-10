package com.example.xu.myapplication.moduleType.dao;

import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.GsonResponseHandler;
import com.example.xu.myapplication.model.Fruit;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xu on 2017/10/8.
 */

public class GoodsPutDao {
    private CallBackGoodsPut callback;

    private static GoodsPutDao instance;

    private GoodsPutDao(CallBackGoodsPut callback) {
        this.callback = callback;
    }

    public static GoodsPutDao newInstance(CallBackGoodsPut callback) {
        if (instance == null) {
            synchronized (GoodsPutDao.class) {
                if (instance == null) {
                    instance = new GoodsPutDao(callback);
                }
            }
        }
        return instance;
    }

    public interface CallBackGoodsPut {
        void onSuccess(Fruit.FruitDetail msg);

        void onFailure(String message);
    }

    public void goodsPut(String goodsName, String goodsPrice, int hot) {

        try {
            MyOkHttp.newInstance().postJson(Common.URL_GOODS_PUT, new JSONObject().put("goodsName", goodsName).put("goodsPrice", goodsPrice).put("hot", hot).put("goodsImage",Common.URL_PACK_IMG), new GsonResponseHandler<Fruit.FruitDetail>() {
                @Override
                public void onSuccess(int statusCode, Fruit.FruitDetail response) {
                    callback.onSuccess(response);
                }

                @Override
                public void onFailure(int statusCode, String error_msg) {
                    callback.onFailure(error_msg);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
