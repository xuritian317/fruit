package com.example.xu.myapplication.moduleShopping.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseMainFragment;
import com.example.xu.myapplication.moduleShopping.fragment.activity.ShoppingPayActivity;
import com.example.xu.myapplication.moduleShopping.fragment.adapter.ShoppingCarAdapter;
import com.example.xu.myapplication.moduleShopping.fragment.bean.FruitBean;
import com.example.xu.myapplication.moduleShopping.fragment.presenter.ShoppingPresenter;
import com.example.xu.myapplication.moduleShopping.fragment.viewInterface.IShopping;
import com.example.xu.myapplication.util.SPUtil;
import com.example.xu.myapplication.util.ToastUtils;

import net.lemonsoft.lemonhello.LemonHello;
import net.lemonsoft.lemonhello.LemonHelloAction;
import net.lemonsoft.lemonhello.LemonHelloInfo;
import net.lemonsoft.lemonhello.LemonHelloView;
import net.lemonsoft.lemonhello.interfaces.LemonHelloActionDelegate;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingFragment extends BaseMainFragment<ShoppingPresenter> implements IShopping {

    private static ShoppingFragment instance;

    public static ShoppingFragment newInstance() {
        if (instance == null)
            instance = new ShoppingFragment();
        return instance;
    }

    @Override
    public Context getCon() {
        return null;
    }

    @Override
    public Activity getAct() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_shopping_content;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void setToolbar() {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (findChildFragment(ShoppingContentFragment.class) == null) {
            loadRootFragment(R.id.frame_shopping, ShoppingContentFragment.newInstance());
        }
    }
}
