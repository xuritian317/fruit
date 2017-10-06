package com.example.xu.myapplication.modelGoodsInfo.dao;

import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.RawResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xu on 2017/10/6.
 */

public class CollectionDao {
    private CallBackCollection callback;

    private static CollectionDao instance;

    private CollectionDao(CallBackCollection callback) {
        this.callback = callback;
    }

    public static CollectionDao newInstance(CallBackCollection callback) {
        if (instance == null) {
            synchronized (CollectionDao.class) {
                if (instance == null) {
                    instance = new CollectionDao(callback);
                }
            }
        }
        return instance;
    }

    public interface CallBackCollection {
        void onSuccess(String message);

        void onFailure(String message);
    }

    public void postToCollection(int userId, int id) {
        try {
            MyOkHttp.newInstance().postJson(Common.URL_POST_COLLECTION, new JSONObject().put("goodsId", id).put("userId", userId), new RawResponseHandler() {
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
