package com.example.xu.myapplication.moduleType.dao;

import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.GsonResponseHandler;
import com.example.xu.myapplication.moduleType.entity.FruitType;

import java.util.ArrayList;

/**
 * Created by xu on 2017/9/29.
 */

public class FruitTypeDao {
    private CallBackFruitType callback;

    private static FruitTypeDao instance;

    private FruitTypeDao(CallBackFruitType callback) {
        this.callback = callback;
    }

    public static FruitTypeDao newInstance(CallBackFruitType callback) {
        if (instance == null) {
            instance = new FruitTypeDao(callback);
        }
        return instance;
    }

    public void getFruitType() {

        MyOkHttp.newInstance().get("http://www.mobilebooks.cn/api/t-classType-0", null, new GsonResponseHandler<ArrayList<FruitType>>() {
            @Override
            public void onSuccess(int statusCode, ArrayList<FruitType> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                callback.onFailure(statusCode + "\t" + error_msg);
            }
        });

    }

    public interface CallBackFruitType {
        void onSuccess(ArrayList<FruitType> response);

        void onFailure(String message);
    }
}
