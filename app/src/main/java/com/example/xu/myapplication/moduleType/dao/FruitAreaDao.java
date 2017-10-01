package com.example.xu.myapplication.moduleType.dao;

import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.GsonResponseHandler;
import com.example.xu.myapplication.moduleType.entity.Fruit;

import java.util.ArrayList;

/**
 * Created by xu on 2017/10/1.
 */

public class FruitAreaDao {
    private CallBackFruitArea callback;

    private static FruitAreaDao instance;

    private FruitAreaDao(CallBackFruitArea callback) {
        this.callback = callback;
    }

    public static FruitAreaDao newInstance(CallBackFruitArea callback) {
        if (instance == null) {
            instance = new FruitAreaDao(callback);
        }
        return instance;
    }

    public interface CallBackFruitArea {
        void onSuccess(ArrayList<Fruit> response);

        void onFailure(String message);
    }

    public void getFruitArea() {

        MyOkHttp.newInstance().get(Common.URL_FRUIT_AREA, null, new GsonResponseHandler<ArrayList<Fruit>>() {
            @Override
            public void onSuccess(int statusCode, ArrayList<Fruit> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                callback.onFailure(statusCode + "\t" + error_msg);
            }
        });

    }
}
