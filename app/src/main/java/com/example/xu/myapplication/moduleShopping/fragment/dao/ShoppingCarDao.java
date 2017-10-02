package com.example.xu.myapplication.moduleShopping.fragment.dao;

import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.RawResponseHandler;

/**
 * Created by 逝 on 2017/09/29.
 */

public class ShoppingCarDao {
    private CallBackShoppingCarDao callback;
    private static ShoppingCarDao dao;

    public ShoppingCarDao(CallBackShoppingCarDao callback) {
        this.callback = callback;
    }

    public static ShoppingCarDao newInstance(CallBackShoppingCarDao callback) {
        if (dao == null) {
            dao = new ShoppingCarDao(callback);
        }
        return dao;
    }

    public void getFruit() {

        MyOkHttp.newInstance().get(Common.URL_SHOPPING_CAR, null, new
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

    public interface CallBackShoppingCarDao {
        void onSuccess(String response);

        void onFailure(String message);
    }
}
