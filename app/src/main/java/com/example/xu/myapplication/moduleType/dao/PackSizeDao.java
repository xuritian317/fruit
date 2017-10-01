package com.example.xu.myapplication.moduleType.dao;

import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.GsonResponseHandler;
import com.example.xu.myapplication.moduleType.entity.Fruit;

import java.util.ArrayList;

/**
 * Created by xu on 2017/10/1.
 */

public class PackSizeDao {
    private CallBackPackSize callback;

    private static PackSizeDao instance;

    private PackSizeDao(CallBackPackSize callback) {
        this.callback = callback;
    }

    public static PackSizeDao newInstance(CallBackPackSize callback) {
        if (instance == null) {
            instance = new PackSizeDao(callback);
        }
        return instance;
    }

    public interface CallBackPackSize{
        void onSuccess(ArrayList<Fruit> response);

        void onFailure(String message);
    }

    public void getPackType() {

        MyOkHttp.newInstance().get(Common.URL_PACK_SIZE, null, new GsonResponseHandler<ArrayList<Fruit>>() {
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
