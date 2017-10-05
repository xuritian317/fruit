package com.example.xu.myapplication.moduleMy.fragment.presenter;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.GsonResponseHandler;
import com.example.xu.myapplication.moduleMy.fragment.adapter.ReceiveAddressAdapter;
import com.example.xu.myapplication.moduleMy.fragment.bean.ReceiveAddressBean;
import com.example.xu.myapplication.moduleMy.fragment.viewInterface.IQueryAddress;

import java.util.ArrayList;

/**
 * Created by 逝 on 2017/10/03.
 */

public class QueryAddressPresenter extends BasePresenter {
    private IQueryAddress view;

    public QueryAddressPresenter(IQueryAddress view) {
        this.view = view;
    }

    public void startIntent(Class<?> cls0) {
        view.getAct().startActivity(new Intent(view.getCon(), cls0));
    }

    public void getAddress(final ReceiveAddressAdapter adapter, final ListView lvAddress, final
    TextView tvAddress) {
        MyOkHttp.newInstance().get(Common.URL_GET_ADDRESS, null,
                new GsonResponseHandler<ArrayList<ReceiveAddressBean>>() {
                    @Override
                    public void onSuccess(int statusCode, ArrayList<ReceiveAddressBean> response) {
                        if (response.size() == 0) {
                            tvAddress.setVisibility(View.VISIBLE);
                            lvAddress.setVisibility(View.GONE);
                        } else {
                            tvAddress.setVisibility(View.GONE);
                            lvAddress.setVisibility(View.VISIBLE);
                            adapter.setData(response);
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });
    }
}
