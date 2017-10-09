package com.example.xu.myapplication.moduleType.presenter;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BasePresenter;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.GsonResponseHandler;
import com.example.xu.myapplication.httpRequest.response.RawResponseHandler;
import com.example.xu.myapplication.modelGoodsInfo.entity.RecommendBean;
import com.example.xu.myapplication.moduleType.adapter.ContentAdapter;
import com.example.xu.myapplication.moduleType.adapter.ContentMinusAdapter;
import com.example.xu.myapplication.moduleType.dao.GoodsPutDao;
import com.example.xu.myapplication.moduleType.entity.Fruit;
import com.example.xu.myapplication.moduleType.listener.OnItemClickListener;
import com.example.xu.myapplication.moduleType.viewInterface.IMenuContent;
import com.example.xu.myapplication.util.Logger;
import com.example.xu.myapplication.util.SPUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by xu on 2017/10/7.
 */

public class MenuContentPresenter extends BasePresenter {
    private IMenuContent view;

    public MenuContentPresenter(IMenuContent view) {
        this.view = view;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case TAG1:
                    Bundle bundle = msg.getData();
                    fruitList.clear();
                    fruitList = bundle.getParcelableArrayList(KEY_ARG);
                    break;
                case TAG2:
                    try {
                        Bundle msgBundle = msg.getData();
                        MyOkHttp.newInstance().postJson(Common.URL_POST_SHOPPING, new JSONObject().put("goodsId", msgBundle.getInt("id")).put("goodsCount", 1).put("userId", msgBundle.getInt("userId")), new RawResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, String response) {
                                view.showToast("已加入购物车");
                            }

                            @Override
                            public void onFailure(int statusCode, String error_msg) {
                                view.showToast("加入购物车失败");
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }

        }
    };

    private final String KEY_ARG = "KEY_ARG";
    private final int TAG1 = 0;
    private final int TAG2 = 1;
    private final int TAG3 = 2;

    private ArrayList<Fruit.FruitDetail> fruitList = new ArrayList<>();

    public void showDialog() {
        if (view.isEmptyAddList() || view.getAddListCount() == 0) {
            view.showToast("请先添加商品");
            return;
        }
        final Context context = view.getMenuContext();
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.mipmap.logo);
        builder.setTitle("自定义套餐");
        builder.setCancelable(true);
        View addView = LayoutInflater.from(context).inflate(R.layout.item_menu_content_add, null);
        RecyclerView recycler_add = (RecyclerView) addView.findViewById(R.id.recycler_add);
        final RecyclerView recycler_recommend = (RecyclerView) addView.findViewById(R.id.recycler_recommend);
        final TextView tv_recommend = (TextView) addView.findViewById(R.id.tv_content_recommend);

        GridLayoutManager managerTop = new GridLayoutManager(context, 3);
        recycler_add.setLayoutManager(managerTop);
        final ContentMinusAdapter adapterTop = new ContentMinusAdapter(context);
        recycler_add.setAdapter(adapterTop);
        adapterTop.setData(view.getAddList(), true);

        GridLayoutManager managerBottom = new GridLayoutManager(context, 3);
        recycler_recommend.setLayoutManager(managerBottom);
        final ContentAdapter adapterBottom = new ContentAdapter(context);
        recycler_recommend.setAdapter(adapterBottom);

        adapterTop.setOnAddClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {
                MenuContentPresenter.this.view.removeAddListItem(position);
                adapterTop.updateData(MenuContentPresenter.this.view.getAddList());
                MenuContentPresenter.this.view.setTvCount(MenuContentPresenter.this.view.getAddListCount());
            }
        });
        adapterTop.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {

                MyOkHttp.newInstance().get(Common.URL_GET_RECOMMEND + MenuContentPresenter.this.view.getAddListItem(position).getId(), null, new GsonResponseHandler<ArrayList<RecommendBean>>() {
                    @Override
                    public void onSuccess(int statusCode, ArrayList<RecommendBean> response) {
                        ArrayList<Fruit.FruitDetail> fruitList = new ArrayList<Fruit.FruitDetail>();
                        for (RecommendBean bean : response) {
                            fruitList.add(new Fruit.FruitDetail(bean.getGoods()));
                        }

                        Logger.e("RecommendDao", "fruitDetails\t" + fruitList.size());
                        if (fruitList.isEmpty()) {
                            tv_recommend.setVisibility(View.VISIBLE);
                        } else {
                            tv_recommend.setVisibility(View.GONE);
                        }
                        adapterBottom.setData(fruitList, true);

                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList(KEY_ARG, fruitList);
                        Message msg = new Message();
                        msg.setData(bundle);
                        msg.what = TAG1;
                        handler.sendMessage(msg);
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                    }
                });
            }
        });

        adapterBottom.setOnAddClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {
                MenuContentPresenter.this.view.addItem(fruitList.get(position));
                adapterTop.updateData(MenuContentPresenter.this.view.getAddList());
                MenuContentPresenter.this.view.setTvCount(MenuContentPresenter.this.view.getAddListCount());
            }
        });
        adapterBottom.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {

            }
        });
        builder.setView(addView);

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fruitList = new ArrayList<Fruit.FruitDetail>();
            }
        });

        builder.setPositiveButton("加入购物车", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuilder nameBuilder = new StringBuilder();
                double price = 0;
                for (int i = 0; i < view.getAddListCount(); i++) {
                    Fruit.FruitDetail fruit = view.getAddListItem(i);
                    if (i == view.getAddListCount() - 1) {
                        nameBuilder.append(fruit.getGoodsName());
                    } else {
                        nameBuilder.append(fruit.getGoodsName()).append(",");
                    }
                    price += Double.parseDouble(fruit.getGoodsPrice());
                }

                DecimalFormat df = new DecimalFormat("#.##");

                GoodsPutDao.newInstance(new GoodsPutDao.CallBackGoodsPut() {
                    @Override
                    public void onSuccess(Fruit.FruitDetail msg) {
                        SPUtil util = new SPUtil(context);
                        int userId = Integer.parseInt(util.getString(SPUtil.USER_ID, "-1"));
                        if (userId == -1) {
                            view.showToast("请先登陆");
                            return;
                        }
                        Bundle bundle = new Bundle();
                        bundle.putInt("userId", userId);
                        bundle.putInt("id", msg.getId());
                        Message message = new Message();
                        message.what = TAG2;
                        message.setData(bundle);
                        handler.sendMessage(message);

                    }

                    @Override
                    public void onFailure(String message) {

                    }
                }).goodsPut(nameBuilder.toString(), df.format(price), 2);
            }
        });
        builder.show();
    }
}
