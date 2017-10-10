package com.example.xu.myapplication.moduleShopping.adapter;

/**
 * Created by 逝 on 2017/10/07.
 */

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xu.myapplication.R;
import com.example.xu.myapplication.moduleShopping.bean.FruitBean;

public class PayAdapter extends BaseAdapter {

    private List<FruitBean> objects = new ArrayList<FruitBean>();

    private Context context;
    private LayoutInflater layoutInflater;

    public PayAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setData(List<FruitBean> objects){
        this.objects=objects;
        notifyDataSetChanged();
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
            convertView = layoutInflater.inflate(R.layout.item_pay, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((FruitBean)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(FruitBean object, ViewHolder holder) {
        //TODO implement
        holder.tvPayItemFruit.setText(object.getFruit());
        holder.tvPayItemPrice.setText("￥"+object.getPrice());
        holder.tvPayItemCount.setText("×"+object.getNumber());
        Glide.with(context).load(object.getFruit_img()).into(holder.ivPayItemImg);
    }

    protected class ViewHolder {
        private ImageView ivPayItemImg;
        private TextView tvPayItemFruit;
        private TextView tvPayItemPrice;
        private TextView tvPayItemCount;

        public ViewHolder(View view) {
            ivPayItemImg = (ImageView) view.findViewById(R.id.iv_pay_itemImg);
            tvPayItemFruit = (TextView) view.findViewById(R.id.tv_pay_itemFruit);
            tvPayItemPrice = (TextView) view.findViewById(R.id.tv_pay_itemPrice);
            tvPayItemCount = (TextView) view.findViewById(R.id.tv_pay_itemCount);
        }
    }
}
