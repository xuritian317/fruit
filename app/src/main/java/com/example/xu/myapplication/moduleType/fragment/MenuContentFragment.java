package com.example.xu.myapplication.moduleType.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseFragment;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.JsonResponseHandler;
import com.example.xu.myapplication.httpRequest.response.RawResponseHandler;
import com.example.xu.myapplication.moduleType.adapter.ContentAdapter;
import com.example.xu.myapplication.moduleType.entity.FruitType;
import com.example.xu.myapplication.moduleType.listener.OnItemClickListener;
import com.example.xu.myapplication.util.Logger;
import com.example.xu.myapplication.util.ToastUtils;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuContentFragment extends BaseFragment {

    public static MenuContentFragment newInstance(ArrayList<FruitType.GoodsBean> beanList) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_MENU, beanList);
        MenuContentFragment instance = new MenuContentFragment();
        instance.setArguments(args);
        return instance;
    }

    private static final String ARG_MENU = "arg_menu";

    private ArrayList<FruitType.GoodsBean> goodsList = new ArrayList<>();
    private ContentAdapter adapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            goodsList = args.getParcelableArrayList(ARG_MENU);
            Logger.e("goodsList", goodsList.size() + "\t" + goodsList.toString());
        }
    }

    @BindView(R.id.recycler_menu_content)
    RecyclerView recyclerView;

    @Override
    public int getLayout() {
        return R.layout.fragment_menu_content;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        GridLayoutManager manager = new GridLayoutManager(_mActivity,3);
        recyclerView.setLayoutManager(manager);
        adapter = new ContentAdapter(_mActivity);
        recyclerView.setAdapter(adapter);
        adapter.setData(goodsList);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {
                ToastUtils.showToast(_mActivity,"click "+position);
            }
        });

    }

    @Override
    public void setToolbar() {

    }

    @Override
    public boolean onBackPressedSupport() {
        return super.onBackPressedSupport();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
