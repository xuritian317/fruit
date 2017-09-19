package com.example.xu.myapplication.moduleMy.fragment.child_frag;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseFragment;
import com.example.xu.myapplication.moduleMy.fragment.adapter.PagerAdapter;
import com.example.xu.myapplication.moduleMy.fragment.listener.OnItemClickListener;
import com.example.xu.myapplication.moduleMy.fragment.presenter.PagerChildPresenter;
import com.example.xu.myapplication.moduleMy.fragment.presenter.PersonalPresenter;
import com.example.xu.myapplication.moduleMy.fragment.viewInterface.IMyPersonal;
import com.example.xu.myapplication.moduleMy.fragment.viewInterface.IPagerChild;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 逝 on 2017/09/16.
 */

public class PagerChildFragment extends BaseFragment<PagerChildPresenter> implements IPagerChild {
    private static final String ARG_FROM = "arg_from";
    @BindView(R.id.recy_orders)
    RecyclerView recyOrders;
    private int mFrom;

    private PagerAdapter mAdapter;

    public static PagerChildFragment newInstance(int from) {
        Bundle args = new Bundle();
        args.putInt(ARG_FROM, from);
        PagerChildFragment fragment = new PagerChildFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_child_pager;
    }

    @Override
    public void setPresenter() {
        presenter=new PagerChildPresenter(this);
    }

    @Override
    public void initData() {
        Bundle args = getArguments();
        if (args != null) {
            mFrom = args.getInt(ARG_FROM);
        }
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mAdapter=new PagerAdapter(getActivity());
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyOrders.setLayoutManager(manager);
        recyOrders.setAdapter(mAdapter);

        //列表item点击事件
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {

            }
        });

        recyOrders.post(new Runnable() {
            @Override
            public void run() {
                List<String> items=new ArrayList<String>();
                for (int i = 0; i <10 ; i++) {
                    String item="";
                    if (mFrom==0){
                        item=getString(R.string.dingdan)+""+i;
                    }else if (mFrom==1){
                        item=getString(R.string.daishouhuo)+""+i;
                    }else if (mFrom==2){
                        item=getString(R.string.yiwancheng) +""+i;
                    }else if (mFrom==3){
                        item=getString(R.string.tuikuan)+""+i;
                    }
                    items.add(item);
                }
                mAdapter.setDatas(items);
            }
        });

    }

    @Override
    public void setToolbar() {

    }

    @Override
    public Context getCon() {
        return getActivity();
    }

    @Override
    public void toStartActivity(Intent intent) {
        startActivity(intent);
    }
}
