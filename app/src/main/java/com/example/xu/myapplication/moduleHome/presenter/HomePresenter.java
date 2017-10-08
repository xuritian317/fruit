package com.example.xu.myapplication.moduleHome.presenter;

import android.support.v7.widget.RecyclerView;

import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.moduleHome.adapter.BargainAdapter;
import com.example.xu.myapplication.moduleHome.adapter.RecommendsAdapter_0;
import com.example.xu.myapplication.moduleHome.adapter.RecommendsAdapter_1;
import com.example.xu.myapplication.moduleHome.bean.BargainBean;
import com.example.xu.myapplication.moduleHome.bean.RecommendsBean_0;
import com.example.xu.myapplication.moduleHome.bean.RecommendsBean_1;
import com.example.xu.myapplication.moduleHome.dao.BargainDao;
import com.example.xu.myapplication.moduleHome.dao.HomeDao;
import com.example.xu.myapplication.moduleHome.dao.RecommendsDao_0;
import com.example.xu.myapplication.moduleHome.dao.RecommendsDao_1;
import com.example.xu.myapplication.moduleHome.loader.GlideImageLoader;
import com.example.xu.myapplication.moduleHome.viewInterface.IHome;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 逝 on 2017/09/30.
 */

public class HomePresenter extends BasePresenter {
    private IHome view;

    public HomePresenter(IHome view) {
        this.view = view;
    }

    /**
     * 设置广告位
     * @param banner  广告位控件
     */
    public void getImgs(final Banner banner) {
        final List<String> imgs = new ArrayList<String>();
        HomeDao.newInstance(new HomeDao.CallBackHomeDao() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONArray array=new JSONArray(response);
                    JSONObject jo;
                    for (int i = 0; i <array.length() ; i++) {
                        jo=array.getJSONObject(i);
                        imgs.add(jo.getString("image"));
                    }
                    banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);//设置Banner样式
                    banner.isAutoPlay(true);//设置自动轮播
                    banner.setImageLoader(new GlideImageLoader());//设置图片加载器
                    banner.setImages(imgs);//设置图片集合
                    banner.setDelayTime(1500);//设置轮播时间
                    banner.setIndicatorGravity(BannerConfig.CENTER);//设置指示器的位置
                    banner.start();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String message) {

            }
        }).getAdvertise();
    }

    /**
     * 限时特价
     * @param adapter
     * @param rvHomeBargain
     */
    public void getBargain(final BargainAdapter adapter, final RecyclerView rvHomeBargain){
        BargainDao.newInstance(new BargainDao.CallBackBargainBean() {
            @Override
            public void onSuccess(ArrayList<BargainBean> response) {
                adapter.setData(response);
                rvHomeBargain.setAdapter(adapter);
            }
            @Override
            public void onFailure(String message) {

            }
        }).getBargain();
    }

    /**
     * 获取鲜果优惠模块
     * @param adapter0
     */
    public void getRecommends0(final RecommendsAdapter_0 adapter0){
        RecommendsDao_0.newInstance(new RecommendsDao_0.CallBackRecommendsBean() {
            @Override
            public void onSuccess(ArrayList<RecommendsBean_0> response) {
                adapter0.setData(response);
            }

            @Override
            public void onFailure(String message) {

            }
        }).getRecommends0();
    }

    public void getRecommends1(final RecommendsAdapter_1 adapter0){
        RecommendsDao_1.newInstance(new RecommendsDao_1.CallBackRecommendsBean() {
            @Override
            public void onSuccess(ArrayList<RecommendsBean_1> response) {
                adapter0.setData(response);
            }

            @Override
            public void onFailure(String message) {

            }
        }).getRecommends1();
    }

}
