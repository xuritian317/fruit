package com.example.xu.myapplication.moduleType.dao;

import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.GsonResponseHandler;
import com.example.xu.myapplication.moduleType.entity.Fruit;

import java.util.ArrayList;

/**
 * Created by xu on 2017/10/2.
 */

public class FruitAllDao {
    private CallBackFruitAll callback;

    private static FruitAllDao instance;

    private FruitAllDao(CallBackFruitAll callback) {
        this.callback = callback;
    }

    public static FruitAllDao newInstance(CallBackFruitAll callback) {
        if (instance == null) {
            instance = new FruitAllDao(callback);
        }
        return instance;
    }

    public interface CallBackFruitAll {
        void onSuccess(ArrayList<Fruit.FruitDetail> response);

        void onFailure(String message);
    }

    public void getFruitType() {

        MyOkHttp.newInstance().get(Common.URL_GOODS_ALL, null, new GsonResponseHandler<ArrayList<Fruit.FruitDetail>>() {
            @Override
            public void onSuccess(int statusCode, ArrayList<Fruit.FruitDetail> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                callback.onFailure(statusCode + "\t" + error_msg);
            }
        });

    }
}
