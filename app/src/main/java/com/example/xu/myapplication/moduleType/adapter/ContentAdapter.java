package com.example.xu.myapplication.moduleType.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xu.myapplication.R;
import com.example.xu.myapplication.moduleType.entity.FruitType;
import com.example.xu.myapplication.moduleType.listener.OnItemClickListener;

import java.util.ArrayList;

/**
 * Created by xu on 2017/9/30.
 */

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder>{
    private OnItemClickListener mClickListener;
    private LayoutInflater mInflater;
    private Context mContext;
    private ArrayList<FruitType.GoodsBean> goodsData = new ArrayList<>();

    public ContentAdapter(Context mContext) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }
    public void setData(ArrayList<FruitType.GoodsBean> data){
        goodsData.clear();
        goodsData.addAll(data);
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
        FruitType.GoodsBean bean = goodsData.get(position);
        Glide.with(mContext).load(bean.getGoodsImage()).into(holder.imgFruit);
        holder.imgFruit.setImageResource(R.mipmap.ic_launcher);
        holder.tvName.setText(bean.getGoodsName());
    }

    @Override
    public int getItemCount() {
        return goodsData.size();
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
