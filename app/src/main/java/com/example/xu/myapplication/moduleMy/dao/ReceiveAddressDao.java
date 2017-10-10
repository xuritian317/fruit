package com.example.xu.myapplication.moduleMy.dao;

import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.GsonResponseHandler;
import com.example.xu.myapplication.moduleMy.bean.ReceiveAddressBean;

import java.util.ArrayList;

/**
 * Created by 逝 on 2017/10/03.
 */

public class ReceiveAddressDao {
    private CallBackAddressBean mCallBack;
    private static ReceiveAddressDao dao;

    public ReceiveAddressDao(CallBackAddressBean mCallBack) {
        this.mCallBack = mCallBack;
    }

    public static ReceiveAddressDao newInstance(CallBackAddressBean mCallBack){
        if (dao==null){
            dao=new ReceiveAddressDao(mCallBack);
        }
        return dao;
    }

    public void getAddress() {
        MyOkHttp.newInstance().get(Common.URL_GET_ADDRESS, null,
                new GsonResponseHandler<ArrayList<ReceiveAddressBean>>() {
                    @Override
                    public void onSuccess(int statusCode, ArrayList<ReceiveAddressBean> response) {
                        mCallBack.onSuccess(response);
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });
    }

    public interface CallBackAddressBean {
        void onSuccess(ArrayList<ReceiveAddressBean> response);

        void onFailure(String message);
    }
}
