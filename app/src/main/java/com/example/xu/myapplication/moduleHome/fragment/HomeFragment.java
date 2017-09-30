package com.example.xu.myapplication.moduleHome.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseMainFragment;
import com.example.xu.myapplication.moduleHome.fragment.adapter.BargainAdapter;
import com.example.xu.myapplication.moduleHome.fragment.bean.BargainBean;
import com.example.xu.myapplication.moduleHome.fragment.dao.BargainDao;
import com.example.xu.myapplication.moduleHome.fragment.presenter.HomePresenter;
import com.example.xu.myapplication.moduleHome.fragment.view.SpaceItemDecoration;
import com.example.xu.myapplication.moduleHome.fragment.viewInterface.IHome;
import com.example.xu.myapplication.util.Logger;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseMainFragment<HomePresenter> implements IHome {
    private static HomeFragment instance;

    public static HomeFragment newInstance() {
        if (instance == null)
            instance = new HomeFragment();
        return instance;
    }

    private BargainAdapter adapter;

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.rv_home_bargain)
    RecyclerView rvHomeBargain;
    @BindView(R.id.sr_home)
    SwipeRefreshLayout srHome;

    @Override
    public int getLayout() {
        return R.layout.fragment_home_main;
    }

    @Override
    public void setPresenter() {
        presenter = new HomePresenter(this);
    }

    @Override
    public void initData() {
        //设置布局管理器
        adapter=new BargainAdapter(getActivity());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvHomeBargain.setLayoutManager(linearLayoutManager);
        //设置水平RecyclerView的item之间的间距
        rvHomeBargain.addItemDecoration(new SpaceItemDecoration(0,20));


//        presenter.getBargain(adapter);
        BargainDao.newInstance(new BargainDao.CallBackBargainBean() {
            @Override
            public void onSuccess(ArrayList<BargainBean> response) {
                Logger.e("response","response"+response.size());
                adapter.setData(response);
                rvHomeBargain.setAdapter(adapter);
            }
            @Override
            public void onFailure(String message) {

            }
        }).getBargain();

        presenter.getImgs(banner);
        //banner设置点击事件，下标是从0开始
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Logger.e("position", position + "");
            }
        });



    }


    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void setToolbar() {

    }

    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }

    @Override
    public Context getCon() {
        return getActivity();
    }

    @Override
    public Activity getAct() {
        return getActivity();
    }
}
