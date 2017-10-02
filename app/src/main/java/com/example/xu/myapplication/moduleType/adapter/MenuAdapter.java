package com.example.xu.myapplication.moduleType.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.moduleType.listener.OnItemClickListener;
import com.example.xu.myapplication.util.Logger;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by YoKeyword on 16/2/10.
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;

    private List<String> dataList = new ArrayList<>();
    private SparseBooleanArray mBooleanArray;
    private OnItemClickListener mClickListener;
    private int mLastCheckedPosition = -1;

    public MenuAdapter(Context context, List<String> items) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        dataList.clear();
        dataList.addAll(items);

        mBooleanArray = new SparseBooleanArray(dataList.size());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_menu, parent, false);
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
        holder.tvName.setText(dataList.get(position));
        if (position == 0 || position == 3 || position == 6 || position == 8) {
            holder.viewLine.setVisibility(View.INVISIBLE);
            holder.itemView.setBackgroundResource(R.color.bg_app);
            holder.tvName.setTextColor(mContext.getResources().getColor(R.color.blue));
            return;
        }
        if (mLastCheckedPosition == 1)
            mBooleanArray.put(1, true);
        if (!mBooleanArray.get(position)) {
            Logger.e("mBooleanArray", "false\t" + position);
            holder.viewLine.setVisibility(View.INVISIBLE);
            holder.itemView.setBackgroundResource(R.color.bg_app);
            holder.tvName.setTextColor(Color.BLACK);
        } else {
            Logger.e("mBooleanArray", "true\t" + position);
            holder.viewLine.setVisibility(View.VISIBLE);
            holder.itemView.setBackgroundColor(Color.WHITE);
            holder.tvName.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void setItemChecked(int position) {
        Logger.e("position", "" + position);
        mBooleanArray.put(position, true);

        if (mLastCheckedPosition > -1) {
            mBooleanArray.put(mLastCheckedPosition, false);
            notifyItemChanged(mLastCheckedPosition);
        }
        notifyDataSetChanged();

        mLastCheckedPosition = position;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        View viewLine;
        TextView tvName;

        public MyViewHolder(View itemView) {
            super(itemView);
            viewLine = itemView.findViewById(R.id.view_line);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
}
