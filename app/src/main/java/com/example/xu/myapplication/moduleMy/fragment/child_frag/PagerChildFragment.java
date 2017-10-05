package com.example.xu.myapplication.moduleMy.fragment.child_frag;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseFragment;
import com.example.xu.myapplication.moduleMy.fragment.adapter.PagerAdapter;
import com.example.xu.myapplication.moduleMy.fragment.presenter.PagerChildPresenter;
import com.example.xu.myapplication.moduleMy.fragment.viewInterface.IPagerChild;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 逝 on 2017/09/16.
 */

public class PagerChildFragment extends BaseFragment<PagerChildPresenter> implements IPagerChild {
    private static final String ARG_FROM = "arg_from";
    @BindView(R.id.lv_item_orders)
    ListView lvItemOrders;
    @BindView(R.id.tv_child_tishi)
    TextView tvChildTishi;
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
        presenter = new PagerChildPresenter(this);
    }

    @Override
    public void initData() {
        Bundle args = getArguments();
        if (args != null) {
            mFrom = args.getInt(ARG_FROM);
        }
        mAdapter = new PagerAdapter(getActivity(), mFrom);
        lvItemOrders.setAdapter(mAdapter);
        presenter.getOrders(mAdapter, mFrom,lvItemOrders,tvChildTishi);
    }

    @Override
    public void initView(Bundle savedInstanceState) {

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
