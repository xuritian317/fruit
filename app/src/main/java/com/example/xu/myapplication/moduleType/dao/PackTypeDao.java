package com.example.xu.myapplication.moduleType.dao;

import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.GsonResponseHandler;
import com.example.xu.myapplication.model.Fruit;

import java.util.ArrayList;

/**
 * Created by xu on 2017/10/1.
 */

public class PackTypeDao {
    private CallBackPackType callback;

    private static PackTypeDao instance;

    private PackTypeDao(CallBackPackType callback) {
        this.callback = callback;
    }

    public static PackTypeDao newInstance(CallBackPackType callback) {
        // if already inited, no need to get lock everytime
        if (instance == null) {
            synchronized (PackTypeDao.class) {
                if (instance == null) {
                    instance = new PackTypeDao(callback);
                }
            }
        }
        return instance;
    }

    public interface CallBackPackType {
        void onSuccess(ArrayList<Fruit> response);

        void onFailure(String message);
    }

    public void getPackType() {

        MyOkHttp.newInstance().get(Common.URL_PACK_TYPE, null, new GsonResponseHandler<ArrayList<Fruit>>() {
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
