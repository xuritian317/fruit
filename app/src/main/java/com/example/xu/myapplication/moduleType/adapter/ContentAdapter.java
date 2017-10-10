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
import com.example.xu.myapplication.model.Fruit;
import com.example.xu.myapplication.moduleType.listener.OnItemClickListener;

import java.util.ArrayList;

/**
 * Created by xu on 2017/9/30.
 */

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {
    private OnItemClickListener mClickListener;
    private OnItemClickListener mAddClickListener;
    private LayoutInflater mInflater;
    private Context mContext;
    private ArrayList<Fruit.FruitDetail> goodsData = new ArrayList<>();
    private boolean flag = false;

    public ContentAdapter(Context mContext) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setData(ArrayList<Fruit.FruitDetail> data, boolean flag) {
        goodsData.clear();
        goodsData.addAll(data);
        this.flag = flag;
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
        holder.img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (mAddClickListener != null) {
                    mAddClickListener.onItemClick(position, v, holder);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit.FruitDetail bean = goodsData.get(position);
        GlideApp.with(mContext).asBitmap().load(bean.getGoodsImage()).diskCacheStrategy(DiskCacheStrategy.ALL).circleCrop().error(R.mipmap.ic_launcher_round).into(holder.imgFruit);
        holder.tvName.setText(bean.getGoodsName());
        if (flag) {
            holder.img_add.setVisibility(View.VISIBLE);
        } else {
            holder.img_add.setVisibility(View.GONE);
        }
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
        ImageView img_add;

        ViewHolder(View itemView) {
            super(itemView);
            imgFruit = (ImageView) itemView.findViewById(R.id.img_fruit);
            tvName = (TextView) itemView.findViewById(R.id.tv_fruit_name);
            img_add = (ImageView) itemView.findViewById(R.id.fBtn_add);
        }
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public void setOnAddClickListener(OnItemClickListener itemClickListener) {
        this.mAddClickListener = itemClickListener;
    }
}
