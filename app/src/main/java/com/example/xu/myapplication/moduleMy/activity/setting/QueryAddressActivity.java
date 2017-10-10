package com.example.xu.myapplication.moduleMy.activity.setting;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseActivity;
import com.example.xu.myapplication.moduleMy.adapter.ReceiveAddressAdapter;
import com.example.xu.myapplication.moduleMy.presenter.QueryAddressPresenter;
import com.example.xu.myapplication.moduleMy.viewInterface.IQueryAddress;

import butterknife.BindView;
import butterknife.OnClick;

public class QueryAddressActivity extends BaseActivity<QueryAddressPresenter> implements
        IQueryAddress {

    @BindView(R.id.lv_address)
    ListView lvAddress;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.btn_addAddress)
    Button btnAddAddress;
    @BindView(R.id.iv_queryAddress_back)
    ImageView ivQueryAddressBack;
    @OnClick(R.id.iv_queryAddress_back)
    public void back(){
        finish();
    }

    @OnClick(R.id.btn_addAddress)
    public void addAddress() {
        presenter.startIntent(AddAddressActivity.class);
    }

    private ReceiveAddressAdapter adapter;

    @Override
    public void setPresenter() {
        presenter = new QueryAddressPresenter(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_query_address;
    }

    @Override
    public void initData() {
        adapter=new ReceiveAddressAdapter(this,QueryAddressActivity.this);
        lvAddress.setAdapter(adapter);
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void setToolbar() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        getAddress();
    }

    public void getAddress(){
        presenter.getAddress(adapter,lvAddress,tvAddress);
    }

    @Override
    public Context getCon() {
        return QueryAddressActivity.this;
    }

    @Override
    public Activity getAct() {
        return QueryAddressActivity.this;
    }

}
