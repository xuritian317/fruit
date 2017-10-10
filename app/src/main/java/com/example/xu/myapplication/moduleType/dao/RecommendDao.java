package com.example.xu.myapplication.moduleType.dao;

import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.GsonResponseHandler;
import com.example.xu.myapplication.modelGoodsInfo.entity.RecommendBean;
import com.example.xu.myapplication.model.Fruit;

import java.util.ArrayList;

/**
 * Created by xu on 2017/10/7.
 */

public class RecommendDao {
    private CallBackRecommend callback;

    private static RecommendDao instance;

    private RecommendDao(CallBackRecommend callback) {
        this.callback = callback;
    }

    public static RecommendDao newInstance(CallBackRecommend callback) {
        if (instance == null) {
            synchronized (RecommendDao.class) {
                if (instance == null) {
                    instance = new RecommendDao(callback);
                }
            }
        }
        return instance;
    }

    public interface CallBackRecommend {
        void onSuccess(ArrayList<Fruit.FruitDetail> fruitDetails);

        void onFailure(String message);
    }

    public void getRecommend(int id) {
        MyOkHttp.newInstance().get(Common.URL_GET_RECOMMEND + id, null, new GsonResponseHandler<ArrayList<RecommendBean>>() {
            @Override
            public void onSuccess(int statusCode, ArrayList<RecommendBean> response) {
                ArrayList<Fruit.FruitDetail> fruitList = new ArrayList<Fruit.FruitDetail>();
                for (RecommendBean bean : response) {
                    fruitList.add(new Fruit.FruitDetail(bean.getGoods()));
                }
                callback.onSuccess(fruitList);
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {
                callback.onFailure(statusCode + "\t" + error_msg);
            }
        });
    }

}
