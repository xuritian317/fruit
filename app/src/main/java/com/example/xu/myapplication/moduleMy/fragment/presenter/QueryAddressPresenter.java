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
import com.example.xu.myapplication.util.SPUtil;
import com.example.xu.myapplication.util.ToastUtils;

import java.util.ArrayList;

/**
 * Created by 逝 on 2017/10/03.
 */

public class QueryAddressPresenter extends BasePresenter {
    private IQueryAddress view;
    private SPUtil util;
    private ArrayList<ReceiveAddressBean> lists = new ArrayList<ReceiveAddressBean>();

    public QueryAddressPresenter(IQueryAddress view) {
        this.view = view;
        util = new SPUtil(this.view.getCon());
    }

    public void startIntent(Class<?> cls0) {
        view.getAct().startActivity(new Intent(view.getCon(), cls0));
    }

    public void getAddress(final ReceiveAddressAdapter adapter, final ListView lvAddress, final
    TextView tvAddress) {
        lists.clear();
        MyOkHttp.newInstance().get(Common.URL_GET_ADDRESS, null,
                new GsonResponseHandler<ArrayList<ReceiveAddressBean>>() {
                    @Override
                    public void onSuccess(int statusCode, ArrayList<ReceiveAddressBean> response) {
                        for (int i = 0; i < response.size(); i++) {
                            if (response.get(i).getUserId() == Integer.valueOf(util.getString
                                    (SPUtil.USER_ID, ""))) {
                                lists.add(response.get(i));
                            }
                        }
                        if (lists.size() == 0) {
                            tvAddress.setVisibility(View.VISIBLE);
                            lvAddress.setVisibility(View.GONE);
                        } else {
                            tvAddress.setVisibility(View.GONE);
                            lvAddress.setVisibility(View.VISIBLE);
                            adapter.setData(lists);
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        ToastUtils.showToast(view.getCon(),"查询地址失败");
                    }
                });
    }
}
