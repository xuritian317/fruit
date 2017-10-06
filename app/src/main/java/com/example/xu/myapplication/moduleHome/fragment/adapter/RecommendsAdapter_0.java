package com.example.xu.myapplication.moduleHome.fragment.adapter;

/**
 * Created by 逝 on 2017/10/01.
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
import com.example.xu.myapplication.moduleHome.fragment.bean.RecommendsBean_0;
import com.example.xu.myapplication.moduleType.entity.Fruit;

public class RecommendsAdapter_0 extends BaseAdapter {

    private List<RecommendsBean_0> objects = new ArrayList<RecommendsBean_0>();

    private Context context;
    private LayoutInflater layoutInflater;

    public RecommendsAdapter_0(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<RecommendsBean_0> objects){
        this.objects=objects;
        notifyDataSetChanged();
    }
    public Fruit.FruitDetail getFruit(int position) {
        RecommendsBean_0 bean = objects.get(position);
        return new Fruit.FruitDetail(bean.getGoods());
    }
    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public RecommendsBean_0 getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_bargain, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((RecommendsBean_0)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(RecommendsBean_0 object, ViewHolder holder) {
        //TODO implement
        holder.tvBargainName.setText(object.getGoods().getGoodsName());
        holder.tvBargainPrice.setText("￥"+object.getGoods().getGoodsPrice());
        Glide.with(context).load(object.getGoods().getGoodsImage()).into(holder.ivBargainImg);
    }

    protected class ViewHolder {
        private ImageView ivBargainImg;
        private TextView tvBargainName;
        private TextView tvBargainPrice;

        public ViewHolder(View view) {
            ivBargainImg = (ImageView) view.findViewById(R.id.iv_bargainImg);
            tvBargainName = (TextView) view.findViewById(R.id.tv_bargainName);
            tvBargainPrice = (TextView) view.findViewById(R.id.tv_bargainPrice);
        }
    }
}

