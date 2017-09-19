package com.example.xu.myapplication.moduleShopping.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.moduleShopping.fragment.Bean.FruitBean;
import com.example.xu.myapplication.moduleShopping.fragment.ShoppingFragment;
import com.jmf.addsubutils.AddSubUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 逝 on 2017/09/14.
 */

public class ShoppingCarAdater extends BaseAdapter {

    private List<FruitBean> objects = new ArrayList<FruitBean>();
    public Map<Integer, Boolean> map_cb = null;

    private Context context;
    private LayoutInflater layoutInflater;

    private ShoppingFragment fragment;


    public ShoppingCarAdater(ShoppingFragment fragment,Context context) {
        this.fragment=fragment;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setData(List<FruitBean> objects) {
        map_cb = new HashMap<Integer, Boolean>();
        this.objects = objects;
        //初始化Map
        init();
        notifyDataSetChanged();
    }

    private void init() {
        for (int i = 0; i < objects.size(); i++) {
            map_cb.put(i, false);
        }
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public FruitBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.fragment_item_shopping, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((FruitBean) getItem(position), (ViewHolder) convertView.getTag(), position);
        return convertView;
    }

    private void initializeViews(final FruitBean object, ViewHolder holder, final int index) {
        //TODO implement
        holder.tvShoppingItemFruit.setText(object.getFruit());
        holder.tvShoppingItemPrice.setText("￥" + object.getPrice());
        holder.cbShoppingItemSelect.setOnCheckedChangeListener(new CompoundButton
                .OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //是否选择 存入map
                map_cb.put(index, isChecked);
                fragment.UpView(isChecked,object.getPrice(),object.getNumber());
            }
        });
        //设置cbShoppingItemSelect选择状态 从map中获得  避免CheckBox在listview中错乱
        holder.cbShoppingItemSelect.setChecked(map_cb.get(index));


        holder.shoppingItemAddSub.setBuyMax(20)//最大购买数
                .setBuyMin(1)//最小购买数，默认是1
                .setStep(1)//购买步长
                .setInventory(50)//库存量
                .setPosition(index)// 传入当前位置，一定要传，不然数据会错乱
                .setCurrentNumber(object.getNumber())//设置当前数
                .setOnChangeValueListener(new AddSubUtils
                        .OnChangeValueListener() {
                    @Override
                    public void onChangeValue(int value, int position) {
                        // 使用传回来的position设置数据  避免数据错乱
                        objects.get(position).setNumber(value);
                        fragment.UpView(map_cb.get(index),object.getPrice(),object.getNumber());
                    }
                });

    }

    protected class ViewHolder {
        private CheckBox cbShoppingItemSelect;
        private TextView tvShoppingItemFruit;
        private TextView tvShoppingItemPrice;
        private AddSubUtils shoppingItemAddSub;

        public ViewHolder(View view) {
            cbShoppingItemSelect = (CheckBox) view.findViewById(R.id.cb_shopping_itemSelect);
            tvShoppingItemFruit = (TextView) view.findViewById(R.id.tv_shopping_itemFruit);
            tvShoppingItemPrice = (TextView) view.findViewById(R.id.tv_shopping_itemPrice);
            shoppingItemAddSub = (AddSubUtils) view.findViewById(R.id.shopping_itemAddSub);
        }
    }

    public interface CheckInterface {
        void isChecked();
    }
}