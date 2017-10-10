package com.example.xu.myapplication.moduleHome.adapter;

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
import com.example.xu.myapplication.util.Logger;

import java.util.ArrayList;

/**
 * Created by xu on 2017/10/8.
 */

public class RecyclerSearchAdapter extends RecyclerView.Adapter<RecyclerSearchAdapter.MyViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;

    private OnItemClickListener mClickListener;
    private ArrayList<Fruit.FruitDetail> fruitList = new ArrayList<>();


    public RecyclerSearchAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    public void setData(ArrayList<Fruit.FruitDetail> fruit) {
        fruitList.clear();
        fruitList.addAll(fruit);
    }
    public void updateData(ArrayList<Fruit.FruitDetail> fruit){
        fruitList.clear();
        fruitList.addAll(fruit);
        Logger.e("fruitList", fruitList.size() + "");
        notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Logger.e("onCreateViewHolder", "onCreateViewHolder");
        View view = mInflater.inflate(R.layout.item_home_search, parent, false);
        final MyViewHolder holder = new MyViewHolder(view);
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
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Fruit.FruitDetail fruit = fruitList.get(position);
        Logger.e("fruit", fruit.toString() + "");

        holder.tvPrice.setText(fruit.getGoodsPrice() + "");
        holder.tvIntro.setText(fruit.getGoodsIntroduction());
        GlideApp.with(mContext).asBitmap().load(fruit.getGoodsImage()).diskCacheStrategy(DiskCacheStrategy.ALL).circleCrop().error(R.mipmap.ic_launcher_round).into(holder.imgGoods);
        if (fruit.getGoodsName().contains(",")) {
            String goodsName = "";
            goodsName = fruit.getGoodsName().substring(0, 7) + "...";
            holder.tvName.setText(goodsName);
        } else {
            holder.tvName.setText(fruit.getGoodsName());
        }
    }

    @Override
    public int getItemCount() {
        return fruitList.size();
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgGoods;
        TextView tvName;
        TextView tvPrice;
        TextView tvIntro;

        MyViewHolder(View itemView) {
            super(itemView);
            imgGoods = (ImageView) itemView.findViewById(R.id.img_goods_home);
            tvName = (TextView) itemView.findViewById(R.id.tv_goods_name_home);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_goods_price_home);
            tvIntro = (TextView) itemView.findViewById(R.id.tv_goods_intro_home);
        }
    }
}
