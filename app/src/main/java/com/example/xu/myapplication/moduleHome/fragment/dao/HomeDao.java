package com.example.xu.myapplication.moduleHome.fragment.dao;

import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.GsonResponseHandler;
import com.example.xu.myapplication.httpRequest.response.RawResponseHandler;
import com.example.xu.myapplication.moduleHome.fragment.bean.BargainBean;

import java.util.ArrayList;

/**
 * Created by 逝 on 2017/09/30.
 */

public class HomeDao {
    private CallBackHomeDao callback;
    private static HomeDao dao;

    public HomeDao(CallBackHomeDao callback) {
        this.callback = callback;
    }

    public static HomeDao newInstance(CallBackHomeDao callback) {
        if (dao == null) {
            dao = new HomeDao(callback);
        }
        return dao;
    }

    public void getAdvertise() {
        MyOkHttp.newInstance().get("http://www.mobilebooks.cn/api/t-advertisements", null, new
                RawResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, String response) {
                        callback.onSuccess(response);
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        callback.onFailure(statusCode + "\t" + error_msg);
                    }
                });
    }

    public interface CallBackHomeDao {
        void onSuccess(String response);

        void onFailure(String message);
    }
}
