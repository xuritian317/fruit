package com.example.xu.myapplication.moduleHome.dao;

import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.GsonResponseHandler;
import com.example.xu.myapplication.moduleHome.bean.BargainBean;

import java.util.ArrayList;

/**
 * Created by 逝 on 2017/09/30.
 */

public class BargainDao {
    private CallBackBargainBean mCallBack;
    private static BargainDao dao;

    public BargainDao(CallBackBargainBean mCallBack) {
        this.mCallBack = mCallBack;
    }

    public static BargainDao newInstance(CallBackBargainBean mCallBack){
        if (dao==null){
            dao=new BargainDao(mCallBack);
        }
        return dao;
    }

    public void getBargain() {
        MyOkHttp.newInstance().get(Common.URL_BARGAIN, null,
                new GsonResponseHandler<ArrayList<BargainBean>>() {
                    @Override
                    public void onSuccess(int statusCode, ArrayList<BargainBean> response) {
                        mCallBack.onSuccess(response);
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });
    }

    public interface CallBackBargainBean {
        void onSuccess(ArrayList<BargainBean> response);

        void onFailure(String message);
    }
}
