package com.example.xu.myapplication.moduleType.dao;

import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.GsonResponseHandler;
import com.example.xu.myapplication.moduleType.entity.Bargain;

import java.util.ArrayList;

/**
 * Created by xu on 2017/10/1.
 */

public class BargainDao {
    private CallBackBargain callback;

    private static BargainDao instance;

    private BargainDao(CallBackBargain callback) {
        this.callback = callback;
    }

    public static BargainDao newInstance(CallBackBargain callback) {
        if (instance == null) {
            instance = new BargainDao(callback);
        }
        return instance;
    }

    public interface CallBackBargain {
        void onSuccess(ArrayList<Bargain> response);

        void onFailure(String message);
    }

    public void getPackType() {

        MyOkHttp.newInstance().get(Common.URL_BARGAIN, null, new GsonResponseHandler<ArrayList<Bargain>>() {
            @Override
            public void onSuccess(int statusCode, ArrayList<Bargain> response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                callback.onFailure(statusCode + "\t" + error_msg);
            }
        });

    }
}
