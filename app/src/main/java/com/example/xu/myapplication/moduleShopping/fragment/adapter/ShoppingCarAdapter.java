package com.example.xu.myapplication.moduleShopping.fragment.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xu.myapplication.R;
import com.example.xu.myapplication.moduleShopping.fragment.bean.FruitBean;
import com.example.xu.myapplication.moduleShopping.fragment.ShoppingFragment;
import com.jmf.addsubutils.AddSubUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 逝 on 2017/09/14.
 */

public class ShoppingCarAdapter extends BaseAdapter {

    private List<FruitBean> objects = new ArrayList<FruitBean>();
    public List<String> strs = new ArrayList<String>();
    private Context context;
    private LayoutInflater layoutInflater;
    private ShoppingFragment fragment;

    public ShoppingCarAdapter(ShoppingFragment fragment, Context context) {
        this.fragment = fragment;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setData(List<FruitBean> objects) {
        this.objects = objects;
        //初始化Map
        init();
        notifyDataSetChanged();
    }

    private void init() {
        strs.clear();
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

        //获取商品图片
        Glide.with(context).load(object.getFruit_img()).into(holder.ivShoppingItemImg);

        holder.cbShoppingItemSelect.setOnCheckedChangeListener(new CompoundButton
                .OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //是否选择 存入map
                object.setChecked(isChecked);
                fragment.UpView();
            }
        });
        //设置cbShoppingItemSelect选择状态 避免CheckBox在listview中错乱
        holder.cbShoppingItemSelect.setChecked(object.isChecked());

        if (holder.cbShoppingItemSelect.isChecked()) {

            strs.add(object.getFruit());
        } else {
            for (int i = 0; i < strs.size(); i++) {
                if (TextUtils.equals(strs.get(i), object.getFruit())) {
                    strs.remove(i);
                }
            }
        }

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
                        fragment.UpView();
                    }
                });

    }

    /**
     * 构建ViewHolder
     */
    protected class ViewHolder {
        private CheckBox cbShoppingItemSelect;
        private TextView tvShoppingItemFruit;
        private ImageView ivShoppingItemImg;
        private TextView tvShoppingItemPrice;
        private AddSubUtils shoppingItemAddSub;

        public ViewHolder(View view) {
            cbShoppingItemSelect = (CheckBox) view.findViewById(R.id.cb_shopping_itemSelect);
            tvShoppingItemFruit = (TextView) view.findViewById(R.id.tv_shopping_itemFruit);
            ivShoppingItemImg= (ImageView) view.findViewById(R.id.iv_shopping_itemImg);
            tvShoppingItemPrice = (TextView) view.findViewById(R.id.tv_shopping_itemPrice);
            shoppingItemAddSub = (AddSubUtils) view.findViewById(R.id.shopping_itemAddSub);
        }
    }
}