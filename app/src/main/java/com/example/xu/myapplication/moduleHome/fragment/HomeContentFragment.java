package com.example.xu.myapplication.moduleHome.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseFragment;
import com.example.xu.myapplication.base.BaseMainFragment;
import com.example.xu.myapplication.modelGoodsInfo.fragment.GoodsInfoFragment;
import com.example.xu.myapplication.moduleHome.fragment.adapter.BargainAdapter;
import com.example.xu.myapplication.moduleHome.fragment.adapter.RecommendsAdapter_0;
import com.example.xu.myapplication.moduleHome.fragment.adapter.RecommendsAdapter_1;
import com.example.xu.myapplication.moduleHome.fragment.listener.OnItemClickListener;
import com.example.xu.myapplication.moduleHome.fragment.presenter.HomePresenter;
import com.example.xu.myapplication.moduleHome.fragment.view.MyGridView;
import com.example.xu.myapplication.moduleHome.fragment.view.MyListView;
import com.example.xu.myapplication.moduleHome.fragment.view.SpaceItemDecoration;
import com.example.xu.myapplication.moduleHome.fragment.viewInterface.IHome;
import com.example.xu.myapplication.util.Logger;
import com.example.xu.myapplication.util.ToastUtils;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeContentFragment extends BaseMainFragment<HomePresenter> implements IHome {

    private static HomeContentFragment instance;

    public static HomeContentFragment newInstance() {
        if (instance == null)
            instance = new HomeContentFragment();
        return instance;
    }

    private BargainAdapter adapter;
    private RecommendsAdapter_0 adapter0;
    private RecommendsAdapter_1 adapter1;

    @BindView(R.id.relative_search)
    RelativeLayout relativeLayout;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.rv_home_bargain)
    RecyclerView rvHomeBargain;
    @BindView(R.id.sr_home)
    SwipeRefreshLayout srHome;
    @BindView(R.id.gridView_0)
    MyGridView gridView0;
    @BindView(R.id.listView_1)
    MyListView listView1;

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
        adapter = new BargainAdapter(getActivity());
        adapter0 = new RecommendsAdapter_0(getActivity());
        adapter1 = new RecommendsAdapter_1(getActivity());

        presenter.getRecommends0(adapter0);
        gridView0.setAdapter(adapter0);

        presenter.getRecommends1(adapter1);
        listView1.setAdapter(adapter1);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvHomeBargain.setLayoutManager(linearLayoutManager);
        //设置水平RecyclerView的item之间的间距
        rvHomeBargain.addItemDecoration(new SpaceItemDecoration(0, 20));

        presenter.getBargain(adapter, rvHomeBargain);

        presenter.getImgs(banner);
        //banner设置点击事件，下标是从0开始
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Logger.e("position", position + "");
                ToastUtils.showToast(_mActivity, "此活动未开启");
            }
        });


        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {
                start(GoodsInfoFragment.newInstance(adapter.getFruit(position)));
            }
        });
        gridView0.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                start(GoodsInfoFragment.newInstance(adapter0.getFruit(position)));
            }
        });
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                start(GoodsInfoFragment.newInstance(adapter1.getFruit(position)));
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

    @Override
    public boolean onBackPressedSupport() {
        return super.onBackPressedSupport();
    }

}
