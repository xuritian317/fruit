package com.example.xu.myapplication.moduleMy.fragment.adapter;

/**
 * Created by 逝 on 2017/10/08.
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
import com.example.xu.myapplication.moduleMy.fragment.bean.CollectBean;

public class CollectAdapter extends BaseAdapter {

    private List<CollectBean> objects = new ArrayList<CollectBean>();

    private Context context;
    private LayoutInflater layoutInflater;

    public CollectAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setDatas(List<CollectBean> objects ){
        this.objects=objects;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public CollectBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_lv_recommends1, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((CollectBean)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(CollectBean object, ViewHolder holder) {
        //TODO implement
        holder.tvRecoGoodName.setText(object.getGoods().getGoodsName());
        holder.tvRecoGoodPrice.setText(object.getGoods().getGoodsPrice());
        holder.tvRecoGoodsIntroduction.setText(object.getGoods().getGoodsIntroduction());
        Glide.with(context).load(object.getGoods().getGoodsImage()).into(holder.ivRecoGoodImage);
    }

    protected class ViewHolder {
        private ImageView ivRecoGoodImage;
        private TextView tvRecoGoodName;
        private TextView tvRecoGoodPrice;
        private TextView tvRecoGoodsIntroduction;

        public ViewHolder(View view) {
            ivRecoGoodImage = (ImageView) view.findViewById(R.id.iv_reco_goodImage);
            tvRecoGoodName = (TextView) view.findViewById(R.id.tv_reco_goodName);
            tvRecoGoodPrice = (TextView) view.findViewById(R.id.tv_reco_goodPrice);
            tvRecoGoodsIntroduction = (TextView) view.findViewById(R.id.tv_reco_goodsIntroduction);
        }
    }
}
