package com.example.xu.myapplication.moduleShopping.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseActivity;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.GsonResponseHandler;
import com.example.xu.myapplication.moduleMy.bean.ReceiveAddressBean;
import com.example.xu.myapplication.moduleShopping.adapter.AddressAdapter;
import com.example.xu.myapplication.util.SPUtil;
import com.example.xu.myapplication.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ReceiveAddressActivity extends BaseActivity {

    @BindView(R.id.iv_receive_back)
    ImageView ivReceiveBack;

    @OnClick(R.id.iv_receive_back)
    public void back() {
        finish();
    }

    @BindView(R.id.lv_receive_address)
    ListView lvReceiveAddress;

    private AddressAdapter adapter;
    private List<ReceiveAddressBean> lists;
    private SPUtil util;

    @Override
    public void setPresenter() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_receive_address;
    }

    @Override
    public void initData() {
        lists = new ArrayList<ReceiveAddressBean>();
        adapter = new AddressAdapter(ReceiveAddressActivity.this);
        lvReceiveAddress.setAdapter(adapter);
        util = new SPUtil(this);

        getAddress();

        lvReceiveAddress.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.putExtra("address_id",position);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void setToolbar() {

    }

    private void getAddress() {
        MyOkHttp.newInstance().get(Common.URL_GET_ADDRESS, null,
                new GsonResponseHandler<ArrayList<ReceiveAddressBean>>() {
                    @Override
                    public void onSuccess(int statusCode, ArrayList<ReceiveAddressBean> response) {
                        for (int i = 0; i < response.size(); i++) {
                            if (response.get(i).getUserId() == Integer.valueOf(util.getString
                                    (SPUtil.USER_ID, ""))) {
                                lists.add(response.get(i));
                            }

                            adapter.setData(lists);
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        ToastUtils.showToast(ReceiveAddressActivity.this, "查询地址失败");
                    }
                });
    }
}
