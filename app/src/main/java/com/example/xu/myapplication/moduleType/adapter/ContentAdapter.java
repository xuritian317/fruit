package com.example.xu.myapplication.moduleType.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.xu.myapplication.GlideApp;
import com.example.xu.myapplication.R;
import com.example.xu.myapplication.moduleType.entity.Fruit;
import com.example.xu.myapplication.moduleType.listener.OnItemClickListener;
import com.example.xu.myapplication.util.Logger;

import java.util.ArrayList;

/**
 * Created by xu on 2017/9/30.
 */

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {
    private OnItemClickListener mClickListener;
    private LayoutInflater mInflater;
    private Context mContext;
    private ArrayList<Fruit.FruitDetail> goodsData = new ArrayList<>();

    public ContentAdapter(Context mContext) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setData(ArrayList<Fruit.FruitDetail> data) {
        goodsData.clear();
        goodsData.addAll(data);
        notifyDataSetChanged();
        Logger.e("setData onSuccess", "data"+data.size()+data.toString());
    }

    public void updateData(ArrayList<Fruit.FruitDetail> data) {
        goodsData.clear();
        goodsData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_menu_content, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (mClickListener != null) {
                    mClickListener.onItemClick(position, v, holder);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit.FruitDetail bean = goodsData.get(position);
        GlideApp.with(mContext).asBitmap().load(bean.getGoodsImage()).diskCacheStrategy(DiskCacheStrategy.ALL).circleCrop().into(holder.imgFruit);
        holder.tvName.setText(bean.getGoodsName());
    }

    @Override
    public int getItemCount() {
        return goodsData.size();
    }

    public Fruit.FruitDetail getFruit(int position) {
        return goodsData.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFruit;
        TextView tvName;

        ViewHolder(View itemView) {
            super(itemView);
            imgFruit = (ImageView) itemView.findViewById(R.id.img_fruit);
            tvName = (TextView) itemView.findViewById(R.id.tv_fruit_name);
        }
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
}
