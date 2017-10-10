package com.example.xu.myapplication.moduleMy.activity.setting;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.example.xu.myapplication.R;
import com.example.xu.myapplication.base.BaseActivity;
import com.example.xu.myapplication.moduleMy.bean.JsonBean;
import com.example.xu.myapplication.moduleMy.presenter.AddAddressPresenter;
import com.example.xu.myapplication.moduleMy.viewInterface.IAddAddress;
import com.example.xu.myapplication.util.GetJsonDataUtil;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class AddAddressActivity extends BaseActivity<AddAddressPresenter> implements IAddAddress {

    @BindView(R.id.iv_addAddress_back)
    ImageView ivAddAddressBack;

    @OnClick(R.id.iv_addAddress_back)
    public void back() {
        finish();
    }

    @BindView(R.id.et_add_name)
    EditText etAddName;
    @BindView(R.id.et_add_phone)
    EditText etAddPhone;
    @BindView(R.id.tv_add_shengshi)
    TextView tvAddShengshi;

    @OnClick(R.id.tv_add_shengshi)
    public void shengSHi() {
        if (isLoaded) {
            ShowPickerView();
        } else {
            Toast.makeText(AddAddressActivity.this, "正在获取城市信息，请重试！", Toast.LENGTH_SHORT).show();
        }
    }

    @BindView(R.id.et_add_xiangxi)
    EditText etAddXiangxi;
    @BindView(R.id.btn_saveAddress)
    Button btnSaveAddress;

    @OnClick(R.id.btn_saveAddress)
    public void saveAddress() {
        presenter.saveAddress(id,etAddName, etAddPhone, tvAddShengshi, etAddXiangxi);
    }

    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;

    private int id = 0;

    private boolean isLoaded = false;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 写子线程中的操作,解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;

                case MSG_LOAD_SUCCESS:
                    isLoaded = true;
                    break;
                case MSG_LOAD_FAILED:
                    Toast.makeText(AddAddressActivity.this, "城市信息解析失败", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };

    @Override
    public void setPresenter() {
        presenter = new AddAddressPresenter(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_add_address;
    }

    @Override
    public void initData() {
        id = getIntent().getIntExtra("id", 0);
        Log.e("id", id + "");
        presenter.getAddress(id, etAddName, etAddPhone, tvAddShengshi, etAddXiangxi);

        mHandler.sendEmptyMessage(MSG_LOAD_DATA);
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void setToolbar() {

    }

    private void ShowPickerView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView
                .OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);
                tvAddShengshi.setText(tx);
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    private void initJsonData() {//解析数据
        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");
        //获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {

                    for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getArea().size();
                         d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }


    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }


    @Override
    public Context getCon() {
        return AddAddressActivity.this;
    }

    @Override
    public Activity getAct() {
        return AddAddressActivity.this;
    }
}
