package com.example.xu.myapplication.moduleHome;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseFragment;
import com.example.xu.myapplication.modelGoodsInfo.fragment.GoodsInfoFragment;
import com.example.xu.myapplication.moduleHome.adapter.RecyclerSearchAdapter;
import com.example.xu.myapplication.moduleHome.dao.GoodsLikeDao;
import com.example.xu.myapplication.moduleType.entity.Fruit;
import com.example.xu.myapplication.moduleType.listener.OnItemClickListener;
import com.example.xu.myapplication.util.Logger;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeSearchFragment extends BaseFragment {
    public static HomeSearchFragment newInstance() {
        HomeSearchFragment instance = new HomeSearchFragment();
        return instance;
    }

    @BindView(R.id.floating_search_view)
    FloatingSearchView searchView;
    @BindView(R.id.recycler_search)
    RecyclerView recyclerView;

    private RecyclerSearchAdapter adapter;
    private LinearLayoutManager manager;
    private ArrayList<Fruit.FruitDetail> fruitList = new ArrayList<>();
    private static final String ARG_DATA = "arg_data";

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            ArrayList<Fruit.FruitDetail> goods = bundle.getParcelableArrayList(ARG_DATA);
            fruitList.addAll(goods);

        }
    };

    @Override
    public int getLayout() {
        return R.layout.fragment_home_search;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void initData() {
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        manager = new LinearLayoutManager(_mActivity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new RecyclerSearchAdapter(_mActivity);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {
                start(GoodsInfoFragment.newInstance(fruitList.get(position)));
            }
        });

        searchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                Logger.e("searchView onSearchTextChanged", oldQuery + "\t" + newQuery);

            }
        });

        searchView.setOnHomeActionClickListener(new FloatingSearchView.OnHomeActionClickListener() {
            @Override
            public void onHomeClicked() {
                _mActivity.onBackPressed();
                Logger.e("searchView", "onHomeClicked");
            }
        });

        searchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
                Logger.e("searchView", "onSuggestionClicked");
            }

            @Override
            public void onSearchAction(String currentQuery) {
                Logger.e("searchView", currentQuery);
                GoodsLikeDao.newInstance(new GoodsLikeDao.CallBackGoodsLike() {
                    @Override
                    public void onSuccess(ArrayList<Fruit.FruitDetail> response) {
                        Logger.e("onSearchAction", response.size() + "\t" + response.toString());
                        adapter.setData(response);
                        adapter.notifyDataSetChanged();
                        recyclerView.invalidate();

                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList(ARG_DATA, response);
                        Message msg = new Message();
                        msg.setData(bundle);
                        handler.sendMessage(msg);
                    }

                    @Override
                    public void onFailure(String message) {

                    }
                }).getGoodsLike(currentQuery);

            }
        });
        searchView.setOnFocusChangeListener(new FloatingSearchView.OnFocusChangeListener() {
            @Override
            public void onFocus() {

                Logger.e("searchView", "onFocus");
            }

            @Override
            public void onFocusCleared() {
                Logger.e("searchView", "onFocusCleared");
            }
        });
    }

    @Override
    public void setToolbar() {

    }
}
