package com.example.xu.myapplication.moduleShopping.fragment.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseActivity;
import com.example.xu.myapplication.moduleHome.view.MyListView;
import com.example.xu.myapplication.moduleShopping.fragment.adapter.PayAdapter;
import com.example.xu.myapplication.moduleShopping.fragment.bean.FruitBean;
import com.example.xu.myapplication.moduleShopping.fragment.presenter.ShoppingPayPresenter;
import com.example.xu.myapplication.moduleShopping.fragment.viewInterface.IShoppingPay;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ShoppingPayActivity extends BaseActivity<ShoppingPayPresenter> implements
        IShoppingPay {

    @BindView(R.id.iv_pay_back)
    ImageView ivPayBack;
    @BindView(R.id.tv_pay_user)
    TextView tvPayUser;
    @BindView(R.id.tv_pay_address)
    TextView tvPayAddress;
    @BindView(R.id.linear_pay_address)
    LinearLayout linearPayAddress;
    @BindView(R.id.lv_pay_orders)
    MyListView lvPayOrders;
    @BindView(R.id.tv_pay_goodsCount)
    TextView tvPayGoodsCount;
    @BindView(R.id.tv_pay_money)
    TextView tvPayMoney;
    @BindView(R.id.btn_pay_orders)
    Button btnPayOrders;
    @BindView(R.id.tv_pay_time)
    TextView tvPayTime;
    @BindView(R.id.linear_pay_time)
    LinearLayout linearPayTime;

    @OnClick({R.id.iv_pay_back, R.id.linear_pay_address, R.id.btn_pay_orders,R.id.linear_pay_time})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.iv_pay_back:
                finish();
                break;
            case R.id.linear_pay_address:
                presenter.toActivity(ReceiveAddressActivity.class);
                break;
            case R.id.btn_pay_orders:
                presenter.createOrders(lists,tvPayTime,address_id);
                break;
            case R.id.linear_pay_time:
                presenter.setReceiveTime(tvPayTime);
                break;
        }
    }

    private List<FruitBean> lists = null;
    private PayAdapter adapter;
    private String money = "";
    private int address_id = 0;

    @Override
    public void setPresenter() {
        presenter = new ShoppingPayPresenter(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_shopping_pay;
    }

    @Override
    public void initData() {
        adapter = new PayAdapter(ShoppingPayActivity.this);
        lvPayOrders.setAdapter(adapter);

        Bundle bundle = this.getIntent().getExtras();
        lists = (List<FruitBean>) bundle.getSerializable("par_orders");
        if (lists != null) {
            adapter.setData(lists);
        }
        money = bundle.getString("money");
        tvPayMoney.setText(money);
        tvPayGoodsCount.setText("共" + lists.size() + "件商品");
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
        presenter.getAddress(tvPayUser, tvPayAddress, address_id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                address_id = data.getExtras().getInt("address_id");
                presenter.getAddress(tvPayUser, tvPayAddress, address_id);
            }
        }
    }

    @Override
    public Context getCon() {
        return ShoppingPayActivity.this;
    }

    @Override
    public Activity getAct() {
        return ShoppingPayActivity.this;
    }
}
