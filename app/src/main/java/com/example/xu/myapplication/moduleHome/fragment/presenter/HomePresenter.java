package com.example.xu.myapplication.moduleHome.fragment.presenter;

import android.util.Log;

import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.RawResponseHandler;
import com.example.xu.myapplication.moduleHome.fragment.adapter.BargainAdapter;
import com.example.xu.myapplication.moduleHome.fragment.bean.BargainBean;
import com.example.xu.myapplication.moduleHome.fragment.dao.BargainDao;
import com.example.xu.myapplication.moduleHome.fragment.dao.HomeDao;
import com.example.xu.myapplication.moduleHome.fragment.loader.GlideImageLoader;
import com.example.xu.myapplication.moduleHome.fragment.viewInterface.IHome;
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
                    Log.e("imgs", imgs.size()+"");//6
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String message) {

            }
        }).getAdvertise();
    }

    public void getBargain(final BargainAdapter adapter){
        BargainDao.newInstance(new BargainDao.CallBackBargainBean() {
            @Override
            public void onSuccess(ArrayList<BargainBean> response) {
                adapter.setData(response);
            }
            @Override
            public void onFailure(String message) {

            }
        }).getBargain();
    }
}
