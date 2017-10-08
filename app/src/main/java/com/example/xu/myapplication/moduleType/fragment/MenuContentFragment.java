package com.example.xu.myapplication.moduleType.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseFragment;
import com.example.xu.myapplication.modelGoodsInfo.fragment.GoodsInfoFragment;
import com.example.xu.myapplication.moduleType.adapter.ContentAdapter;
import com.example.xu.myapplication.moduleType.entity.Fruit;
import com.example.xu.myapplication.moduleType.listener.OnItemClickListener;
import com.example.xu.myapplication.moduleType.presenter.MenuContentPresenter;
import com.example.xu.myapplication.moduleType.viewInterface.IMenuContent;
import com.example.xu.myapplication.util.Logger;
import com.example.xu.myapplication.util.ToastUtils;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuContentFragment extends BaseFragment<MenuContentPresenter> implements IMenuContent {

    public static MenuContentFragment newInstance(ArrayList<Fruit> goodsList, boolean flag) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_MENU, goodsList);
        args.putBoolean(ARG_FLAG, flag);
        MenuContentFragment instance = new MenuContentFragment();
        instance.setArguments(args);
        return instance;
    }

    private static final String ARG_MENU = "arg_menu";
    private static final String ARG_FLAG = "arg_flag";

    private ArrayList<Fruit> goodsList = new ArrayList<>();
    private ContentAdapter adapter;
    private int index = 0;
    private ArrayList<Fruit.FruitDetail> addList = new ArrayList<>();
    private boolean flag = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            goodsList = args.getParcelableArrayList(ARG_MENU);
            flag = args.getBoolean(ARG_FLAG);
        }

    }

    @BindView(R.id.recycler_menu_content)
    RecyclerView recyclerView;
    @BindView(R.id.linear_btn_type)
    LinearLayout linear_btnType;
    @BindView(R.id.btn_type1)
    Button btnType1;

    @OnClick(R.id.btn_type1)
    public void clickBtn1() {
        if (spinnerType.getSelectedIndex() == 0)
            return;
        spinnerType.setSelectedIndex(0);
        adapter.updateData(goodsList.get(0).getGoods());
        index = 0;
    }

    @BindView(R.id.btn_type2)
    Button btnType2;

    @OnClick(R.id.btn_type2)
    public void clickBtn2() {
        if (spinnerType.getSelectedIndex() == 1)
            return;
        spinnerType.setSelectedIndex(1);
        adapter.updateData(goodsList.get(1).getGoods());
        index = 1;
    }

    @BindView(R.id.spinner_type)
    MaterialSpinner spinnerType;
    @BindView(R.id.fBtn_pack)
    FloatingActionButton fBtn_pack;

    @OnClick(R.id.fBtn_pack)
    public void addPack() {
        presenter.showDialog(_mActivity, addList);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_menu_content;
    }

    @Override
    public void setPresenter() {
        presenter = new MenuContentPresenter(this);
    }

    @Override
    public void initData() {
        if (goodsList.size() == 1 && TextUtils.equals(goodsList.get(0).getClassifyName(), Common.NAME_FRUIT_TYPE)) {
            linear_btnType.setVisibility(View.GONE);
            return;
        }
        linear_btnType.setVisibility(View.VISIBLE);
        List<String> typeList = new ArrayList<>();
        for (int i = 0; i < goodsList.size(); i++) {
            typeList.add(goodsList.get(i).getClassifyName());
        }
        if (goodsList.size() != 1) {
            btnType1.setText(typeList.get(0));
            btnType2.setText(typeList.get(1));
        }
        spinnerType.setItems(typeList);
        spinnerType.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                adapter.updateData(goodsList.get(position).getGoods());
                index = position;
            }
        });

    }

    @Override
    public void initView(Bundle savedInstanceState) {

        GridLayoutManager manager = new GridLayoutManager(_mActivity, 3);
        recyclerView.setLayoutManager(manager);
        adapter = new ContentAdapter(_mActivity);
        recyclerView.setAdapter(adapter);
        adapter.setData(goodsList.get(0).getGoods(), flag);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {
                ((TypeContentFragment) getParentFragment()).start(GoodsInfoFragment.newInstance(goodsList.get(index).getGoods().get(position), 0, 1));
            }
        });
        adapter.setOnAddClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {
                Fruit.FruitDetail fruit = goodsList.get(index).getGoods().get(position);
                ToastUtils.showToast(_mActivity, fruit.getGoodsName() + "已加入");
                addList.add(fruit);
            }
        });
        if (flag) {
            fBtn_pack.setVisibility(View.VISIBLE);
        } else {
            fBtn_pack.setVisibility(View.GONE);
        }
    }

    @Override
    public void setToolbar() {

    }

    @Override
    public boolean onBackPressedSupport() {
        return super.onBackPressedSupport();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showToast(_mActivity, msg);
    }
}
