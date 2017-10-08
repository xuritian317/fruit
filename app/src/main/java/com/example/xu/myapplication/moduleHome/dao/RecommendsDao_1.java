package com.example.xu.myapplication.moduleHome.dao;

import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.GsonResponseHandler;
import com.example.xu.myapplication.moduleHome.bean.RecommendsBean_0;
import com.example.xu.myapplication.moduleHome.bean.RecommendsBean_1;

import java.util.ArrayList;

/**
 * Created by 逝 on 2017/09/30.
 */

public class RecommendsDao_1 {
    private CallBackRecommendsBean mCallBack;
    private static RecommendsDao_1 dao;

    public RecommendsDao_1(CallBackRecommendsBean mCallBack) {
        this.mCallBack = mCallBack;
    }

    public static RecommendsDao_1 newInstance(CallBackRecommendsBean mCallBack){
        if (dao==null){
            dao=new RecommendsDao_1(mCallBack);
        }
        return dao;
    }

    public void getRecommends1() {
        MyOkHttp.newInstance().get(Common.URL_RECOMMENDS_1, null,
                new GsonResponseHandler<ArrayList<RecommendsBean_1>>() {
                    @Override
                    public void onSuccess(int statusCode, ArrayList<RecommendsBean_1> response) {
                        mCallBack.onSuccess(response);
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });
    }

    public interface CallBackRecommendsBean {
        void onSuccess(ArrayList<RecommendsBean_1> response);
        void onFailure(String message);
    }
}
