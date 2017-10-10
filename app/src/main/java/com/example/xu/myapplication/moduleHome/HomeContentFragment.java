package com.example.xu.myapplication.moduleHome;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.INotificationSideChannel;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseMainFragment;
import com.example.xu.myapplication.modelGoodsInfo.fragment.GoodsInfoFragment;
import com.example.xu.myapplication.moduleHome.adapter.BargainAdapter;
import com.example.xu.myapplication.moduleHome.adapter.RecommendsAdapter_0;
import com.example.xu.myapplication.moduleHome.adapter.RecommendsAdapter_1;
import com.example.xu.myapplication.moduleHome.listener.OnItemClickListener;
import com.example.xu.myapplication.moduleHome.presenter.HomePresenter;
import com.example.xu.myapplication.moduleHome.view.MyGridView;
import com.example.xu.myapplication.moduleHome.view.MyListView;
import com.example.xu.myapplication.moduleHome.view.SpaceItemDecoration;
import com.example.xu.myapplication.moduleHome.viewInterface.IHome;
import com.example.xu.myapplication.util.Logger;
import com.example.xu.myapplication.util.ToastUtils;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import butterknife.BindView;
import butterknife.OnClick;

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

    private RecommendsAdapter_0 adapter0;
    private RecommendsAdapter_1 adapter1;

    @BindView(R.id.relative_search)
    RelativeLayout relativeLayout;

    @OnClick(R.id.relative_search)
    public void clickSearch() {
        start(HomeSearchFragment.newInstance());
    }

    @BindView(R.id.banner)
    Banner banner;
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
        adapter0 = new RecommendsAdapter_0(getActivity());
        adapter1 = new RecommendsAdapter_1(getActivity());

        presenter.getRecommends0(adapter0);
        gridView0.setAdapter(adapter0);

        presenter.getRecommends1(adapter1);
        listView1.setAdapter(adapter1);

        presenter.getImgs(banner);
        //banner设置点击事件，下标是从0开始
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Logger.e("position", position + "");
                ToastUtils.showToast(_mActivity, "此活动未开启");
            }
        });
        gridView0.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                start(GoodsInfoFragment.newInstance(adapter0.getFruit(position), 0,1));
            }
        });
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                start(GoodsInfoFragment.newInstance(adapter1.getFruit(position), 0,1));
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
        return false;
    }

}
