package com.example.xu.myapplication.moduleHome.fragment.dao;

import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.GsonResponseHandler;
import com.example.xu.myapplication.moduleHome.fragment.bean.BargainBean;
import com.example.xu.myapplication.moduleHome.fragment.bean.RecommendsBean_0;

import java.util.ArrayList;

/**
 * Created by 逝 on 2017/09/30.
 */

public class RecommendsDao_0 {
    private CallBackRecommendsBean mCallBack;
    private static RecommendsDao_0 dao;

    public RecommendsDao_0(CallBackRecommendsBean mCallBack) {
        this.mCallBack = mCallBack;
    }

    public static RecommendsDao_0 newInstance(CallBackRecommendsBean mCallBack){
        if (dao==null){
            dao=new RecommendsDao_0(mCallBack);
        }
        return dao;
    }

    public void getRecommends0() {
        MyOkHttp.newInstance().get("http://www.mobilebooks.cn/api/t-home-recommends-0", null,
                new GsonResponseHandler<ArrayList<RecommendsBean_0>>() {
                    @Override
                    public void onSuccess(int statusCode, ArrayList<RecommendsBean_0> response) {
                        mCallBack.onSuccess(response);
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });
    }

    public interface CallBackRecommendsBean {
        void onSuccess(ArrayList<RecommendsBean_0> response);
        void onFailure(String message);
    }
}
