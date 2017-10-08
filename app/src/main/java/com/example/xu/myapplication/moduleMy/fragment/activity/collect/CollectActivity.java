package com.example.xu.myapplication.moduleMy.fragment.activity.collect;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseActivity;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.JsonResponseHandler;
import com.example.xu.myapplication.moduleMy.fragment.adapter.CollectAdapter;
import com.example.xu.myapplication.moduleMy.fragment.bean.CollectBean;
import com.example.xu.myapplication.util.SPUtil;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CollectActivity extends BaseActivity {

    @BindView(R.id.iv_collect_back)
    ImageView ivCollectBack;

    @OnClick(R.id.iv_collect_back)
    public void back(){
        finish();
    }
    @BindView(R.id.lv_collect)
    ListView lvCollect;

    private SPUtil util;

    private List<CollectBean> lists;
    private CollectAdapter adapter;

    @Override
    public void setPresenter() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_collect;
    }

    @Override
    public void initData() {
        util=new SPUtil(CollectActivity.this);
        lists=new ArrayList<CollectBean>();
        adapter=new CollectAdapter(CollectActivity.this);
        lvCollect.setAdapter(adapter);
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
        getCollections();
    }

    private void getCollections(){
        JSONObject jo = new JSONObject();
        try {
            jo.put("phoneNumber", util.getString(SPUtil.IS_USER,""));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MyOkHttp.newInstance().postJson(Common.URL_GET_USER, jo, new JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                try {
                    String collect=response.getString("collections");
                    Gson gson=new Gson();
                    JSONArray array=new JSONArray(collect);
                    for (int i = 0; i < array.length(); i++) {
                        CollectBean bean=gson.fromJson(array.getJSONObject(i).toString(),CollectBean.class);
                        lists.add(bean);
                    }

                    adapter.setDatas(lists);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {

            }
        });
    }
}
