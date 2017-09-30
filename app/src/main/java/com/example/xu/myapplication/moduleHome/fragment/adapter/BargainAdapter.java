package com.example.xu.myapplication.moduleHome.fragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xu.myapplication.R;
import com.example.xu.myapplication.moduleHome.fragment.bean.BargainBean;
import com.example.xu.myapplication.moduleHome.fragment.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 逝 on 2017/09/30.
 */

public class BargainAdapter extends RecyclerView.Adapter<BargainAdapter.ViewHolder>{

    private Context context;
    private ArrayList<BargainBean> lists;
    private OnItemClickListener mClickListener;

    public BargainAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<BargainBean> lists){
        this.lists=lists;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bargain,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (mClickListener!=null){
                    mClickListener.onItemClick(position, v, holder);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BargainBean bean=lists.get(position);
        holder.tvBargainName.setText(bean.getBargainName());
        holder.tvBargainPrice.setText("￥"+bean.getBargainPrice()+"/"+bean.getBargainCount()+"个");
        Glide.with(context).load(bean.getBargainImage()).into(holder.ivBargainImg);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }


    protected class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivBargainImg;
        private TextView tvBargainName;
        private TextView tvBargainPrice;

        public ViewHolder(View view) {
            super(view);
            ivBargainImg = (ImageView) view.findViewById(R.id.iv_bargainImg);
            tvBargainName = (TextView) view.findViewById(R.id.tv_bargainName);
            tvBargainPrice = (TextView) view.findViewById(R.id.tv_bargainPrice);
        }
    }
}
