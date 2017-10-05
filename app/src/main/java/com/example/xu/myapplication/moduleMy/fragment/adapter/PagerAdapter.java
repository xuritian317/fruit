package com.example.xu.myapplication.moduleMy.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.xu.myapplication.R;
import com.example.xu.myapplication.moduleMy.fragment.bean.OrdersBean;
import com.example.xu.myapplication.util.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 发现Discover里的子Fragment  Adapter
 */

public class PagerAdapter extends BaseAdapter {

    private List<OrdersBean> objects = new ArrayList<OrdersBean>();
    private Context context;
    private LayoutInflater layoutInflater;
    private int mForm;

    public PagerAdapter(Context context, int mForm) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.mForm = mForm;
    }

    public void setDatas(List<OrdersBean> objects) {
        this.objects = objects;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public OrdersBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_orders, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((OrdersBean) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(OrdersBean object, ViewHolder holder) {
        //TODO implement
        Logger.e("adapter", mForm + "");
        switch (mForm) {
            case 0:
                Glide.with(context).load(object.getGoods().getGoodsImage()).into(holder
                        .ivOrdersItemImg);
                holder.tvOrdersItemFruit.setText(object.getGoods().getGoodsName());
                holder.tvOrdersItemPrice.setText("￥" + object.getGoods().getGoodsPrice());
                holder.tvOrdersItemCount.setText("×" + object.getGoodsCount());
                holder.tvOrdersZong.setText("合计" + object.getGoodsCount() + "件商品，实付款:￥" +
                        object.getOrderPay());
                if (object.getOrderState() == 0) {
                    if (object.getReviewState() == 1) {
                        holder.btnOrders0.setVisibility(View.VISIBLE);
                        holder.btnOrders1.setVisibility(View.VISIBLE);
                        holder.btnOrders1.setText("删除订单");
                        holder.btnOrders0.setText("评价");
                    } else {
                        holder.btnOrders0.setVisibility(View.VISIBLE);
                        holder.btnOrders0.setBackground(context.getDrawable(R.drawable
                                .btn_style_gray));
                        holder.btnOrders0.setTextColor(context.getResources().getColor(R.color
                                .gray));
                        holder.btnOrders0.setText("删除订单");
                    }
                    holder.tvOrdersState.setText("已收货");
                } else if (object.getOrderState() == 1) {
                    holder.btnOrders0.setVisibility(View.VISIBLE);
                    holder.btnOrders1.setVisibility(View.VISIBLE);
                    holder.btnOrders1.setText("查看物流");
                    holder.btnOrders0.setText("确认收货");
                    holder.tvOrdersState.setText("已发货");
                } else if (object.getOrderState() == 2) {
                    holder.btnOrders0.setVisibility(View.VISIBLE);
                    holder.btnOrders0.setBackground(context.getDrawable(R.drawable
                            .btn_style_gray));
                    holder.btnOrders0.setTextColor(context.getResources().getColor(R.color
                            .gray));
                    holder.btnOrders0.setText("删除订单");
                    holder.tvOrdersState.setText("已取消");
                }
                break;
            case 1:
                Glide.with(context).load(object.getGoods().getGoodsImage()).into(holder
                        .ivOrdersItemImg);
                holder.tvOrdersItemFruit.setText(object.getGoods().getGoodsName());
                holder.tvOrdersItemPrice.setText("￥" + object.getGoods().getGoodsPrice());
                holder.tvOrdersItemCount.setText("×" + object.getGoodsCount());
                holder.tvOrdersZong.setText("合计" + object.getGoodsCount() + "件商品，实付款:￥" +
                        object.getOrderPay());
                holder.tvOrdersState.setText("已发货");
                if (object.getOrderState() == 1) {
                    holder.btnOrders0.setVisibility(View.VISIBLE);
                    holder.btnOrders1.setVisibility(View.VISIBLE);
                    holder.btnOrders1.setText("查看物流");
                    holder.btnOrders0.setText("确认收货");
                }
                break;
            case 2:
                Glide.with(context).load(object.getGoods().getGoodsImage()).into(holder
                        .ivOrdersItemImg);
                holder.tvOrdersItemFruit.setText(object.getGoods().getGoodsName());
                holder.tvOrdersItemPrice.setText("￥" + object.getGoods().getGoodsPrice());
                holder.tvOrdersItemCount.setText("×" + object.getGoodsCount());
                holder.tvOrdersZong.setText("合计" + object.getGoodsCount() + "件商品，实付款:￥" +
                        object.getOrderPay());
                holder.tvOrdersState.setText("已收货");
                if (object.getOrderState() == 0) {
                    if (object.getReviewState() == 1) {
                        holder.btnOrders0.setVisibility(View.VISIBLE);
                        holder.btnOrders1.setVisibility(View.VISIBLE);
                        holder.btnOrders1.setText("删除订单");
                        holder.btnOrders0.setText("评价");
                    } else {
                        holder.btnOrders0.setVisibility(View.VISIBLE);
                        holder.btnOrders0.setBackground(context.getDrawable(R.drawable
                                .btn_style_gray));
                        holder.btnOrders0.setTextColor(context.getResources().getColor(R.color
                                .gray));
                        holder.btnOrders0.setText("删除订单");
                    }

                }
                break;
            case 3:
                Glide.with(context).load(object.getGoods().getGoodsImage()).into(holder
                        .ivOrdersItemImg);
                holder.tvOrdersItemFruit.setText(object.getGoods().getGoodsName());
                holder.tvOrdersItemPrice.setText("￥" + object.getGoods().getGoodsPrice());
                holder.tvOrdersItemCount.setText("×" + object.getGoodsCount());
                holder.tvOrdersZong.setText("合计" + object.getGoodsCount() + "件商品，实付款:￥" +
                        object.getOrderPay());
                holder.tvOrdersState.setText("已收货");
                if (object.getOrderState() == 0) {
                    if (object.getReviewState() == 1) {
                        holder.btnOrders0.setVisibility(View.VISIBLE);
                        holder.btnOrders1.setVisibility(View.VISIBLE);
                        holder.btnOrders1.setText("删除订单");
                        holder.btnOrders0.setText("评价");
                    }
                }
                break;
            case 4:
                Glide.with(context).load(object.getGoods().getGoodsImage()).into(holder
                        .ivOrdersItemImg);
                holder.tvOrdersItemFruit.setText(object.getGoods().getGoodsName());
                holder.tvOrdersItemPrice.setText("￥" + object.getGoods().getGoodsPrice());
                holder.tvOrdersItemCount.setText("×" + object.getGoodsCount());
                holder.tvOrdersZong.setText("合计" + object.getGoodsCount() + "件商品，实付款:￥" +
                        object.getOrderPay());
                holder.tvOrdersState.setText("已取消");
                if (object.getOrderState() == 2) {
                    holder.btnOrders0.setVisibility(View.VISIBLE);
                    holder.btnOrders0.setBackground(context.getDrawable(R.drawable
                            .btn_style_gray));
                    holder.btnOrders0.setTextColor(context.getResources().getColor(R.color
                            .gray));
                    holder.btnOrders0.setText("删除订单");
                }
                break;
        }

    }

    protected class ViewHolder {
        private LinearLayout linearOrdersItem;
        private ImageView ivOrdersItemImg;
        private TextView tvOrdersItemFruit;
        private TextView tvOrdersItemPrice;
        private TextView tvOrdersItemCount;
        private TextView tvOrdersState;
        private TextView tvOrdersZong;
        private Button btnOrders2;
        private Button btnOrders1;
        private Button btnOrders0;

        public ViewHolder(View view) {
            linearOrdersItem = (LinearLayout) view.findViewById(R.id.linear_orders_item);
            ivOrdersItemImg = (ImageView) view.findViewById(R.id.iv_orders_itemImg);
            tvOrdersItemFruit = (TextView) view.findViewById(R.id.tv_orders_itemFruit);
            tvOrdersItemPrice = (TextView) view.findViewById(R.id.tv_orders_itemPrice);
            tvOrdersItemCount = (TextView) view.findViewById(R.id.tv_orders_itemCount);
            tvOrdersState = (TextView) view.findViewById(R.id.tv_orders_state);
            tvOrdersZong = (TextView) view.findViewById(R.id.tv_orders_zong);
            btnOrders2 = (Button) view.findViewById(R.id.btn_orders_2);
            btnOrders1 = (Button) view.findViewById(R.id.btn_orders_1);
            btnOrders0 = (Button) view.findViewById(R.id.btn_orders_0);
        }
    }
}
