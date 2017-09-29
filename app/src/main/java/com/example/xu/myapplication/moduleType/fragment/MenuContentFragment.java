package com.example.xu.myapplication.moduleType.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.TextView;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseFragment;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.JsonResponseHandler;
import com.example.xu.myapplication.httpRequest.response.RawResponseHandler;
import com.example.xu.myapplication.util.Logger;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuContentFragment extends BaseFragment {

    public static MenuContentFragment newInstance(String menu) {
        Bundle args = new Bundle();
        args.putString(ARG_MENU, menu);
        MenuContentFragment instance = new MenuContentFragment();
        instance.setArguments(args);
        return instance;
    }

    private static final String ARG_MENU = "arg_menu";

    @BindView(R.id.tv_menu_content)
    TextView tvContent;

    private String menuStr;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null)
            menuStr = args.getString(ARG_MENU);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_menu_content;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        tvContent.setText(menuStr);
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
}
