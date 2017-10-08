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
import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.R;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.JsonResponseHandler;
import com.example.xu.myapplication.httpRequest.response.RawResponseHandler;
import com.example.xu.myapplication.moduleMy.fragment.bean.OrdersBean;
import com.example.xu.myapplication.moduleMy.fragment.child_frag.PagerChildFragment;
import com.example.xu.myapplication.util.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 发现Discover里的子Fragment  Adapter
 */

public class PagerAdapter extends BaseAdapter {

    private List<OrdersBean> objects = new ArrayList<OrdersBean>();
    private Context context;
    private PagerChildFragment fragment;
    private LayoutInflater layoutInflater;
    private int mForm;

    public PagerAdapter(PagerChildFragment fragment,Context context, int mForm) {
        this.fragment=fragment;
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

    private void initializeViews(final OrdersBean object, ViewHolder holder) {
        //TODO implement
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
                        holder.btnOrders1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                deleteOrders(object.getId());
                            }
                        });
                    } else {
                        holder.btnOrders0.setVisibility(View.VISIBLE);
                        holder.btnOrders0.setBackground(context.getDrawable(R.drawable
                                .btn_style_gray));
                        holder.btnOrders0.setTextColor(context.getResources().getColor(R.color
                                .gray));
                        holder.btnOrders0.setText("删除订单");
                        holder.btnOrders0.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                deleteOrders(object.getId());
                            }
                        });
                    }
                    holder.tvOrdersState.setText("已收货");
                } else if (object.getOrderState() == 1) {
                    holder.btnOrders0.setVisibility(View.VISIBLE);
                    holder.btnOrders1.setVisibility(View.VISIBLE);
                    holder.btnOrders1.setText("退款");
                    holder.btnOrders0.setText("确认收货");
                    holder.tvOrdersState.setText("已发货");

                    holder.btnOrders1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            updateOrders(object.getId(),object.getOrderNumber(),object.getOrderPay()
                            ,object.getGoodsCount(),2,0,object.getReceiveTime(),object.getReceiveAddress().getId()
                            ,object.getUser().getId(),object.getGoods().getId());
                        }
                    });

                    holder.btnOrders0.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            updateOrders(object.getId(),object.getOrderNumber(),object.getOrderPay()
                                    ,object.getGoodsCount(),0,1,object.getReceiveTime(),object.getReceiveAddress().getId()
                                    ,object.getUser().getId(),object.getGoods().getId());
                        }
                    });
                } else if (object.getOrderState() == 2) {
                    holder.btnOrders0.setVisibility(View.VISIBLE);
                    holder.btnOrders0.setBackground(context.getDrawable(R.drawable
                            .btn_style_gray));
                    holder.btnOrders0.setTextColor(context.getResources().getColor(R.color
                            .gray));
                    holder.btnOrders0.setText("删除订单");
                    holder.tvOrdersState.setText("已取消");
                    holder.btnOrders0.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            deleteOrders(object.getId());
                        }
                    });
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
                    holder.btnOrders1.setText("退款");
                    holder.btnOrders0.setText("确认收货");

                    holder.btnOrders1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            updateOrders(object.getId(),object.getOrderNumber(),object.getOrderPay()
                                    ,object.getGoodsCount(),2,0,object.getReceiveTime(),object.getReceiveAddress().getId()
                                    ,object.getUser().getId(),object.getGoods().getId());
                        }
                    });

                    holder.btnOrders0.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            updateOrders(object.getId(),object.getOrderNumber(),object.getOrderPay()
                                    ,object.getGoodsCount(),0,1,object.getReceiveTime(),object.getReceiveAddress().getId()
                                    ,object.getUser().getId(),object.getGoods().getId());
                        }
                    });
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
                        holder.btnOrders1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                deleteOrders(object.getId());
                            }
                        });
                    } else {
                        holder.btnOrders0.setVisibility(View.VISIBLE);
                        holder.btnOrders0.setBackground(context.getDrawable(R.drawable
                                .btn_style_gray));
                        holder.btnOrders0.setTextColor(context.getResources().getColor(R.color
                                .gray));
                        holder.btnOrders0.setText("删除订单");
                        holder.btnOrders0.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                deleteOrders(object.getId());
                            }
                        });
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

                        holder.btnOrders1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                deleteOrders(object.getId());
                            }
                        });
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
                    holder.btnOrders0.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            deleteOrders(object.getId());
                        }
                    });
                }
                break;
        }

    }

    /*
    修改订单
     */
    private void updateOrders(int id, String orderNumber, String orderPay, int goodsCount,
                              int orderState, int reviewState, String receiveTime,
                              int receiveAddressId, int userId, int goodsId) {
        JSONObject jo = new JSONObject();
        try {
            jo.put("id", id);
            jo.put("orderNumber", orderNumber);
            jo.put("orderPay", orderPay);
            jo.put("goodsCount", goodsCount);
            jo.put("orderState", orderState);
            jo.put("reviewState", reviewState);
            jo.put("receiveTime", receiveTime);
            jo.put("receiveAddressId", receiveAddressId);
            jo.put("userId", userId);
            jo.put("goodsId", goodsId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MyOkHttp.newInstance().postJson(context, Common.URL_UPDATE_ORDERS, jo, new
                JsonResponseHandler() {
            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                if (statusCode == 200) {
                    fragment.getOrders();
                }
            }

            @Override
            public void onFailure(int statusCode, String error_msg) {

            }
        });
    }

    /*
    删除订单
     */
    private void deleteOrders(int id){
        JSONObject jo = new JSONObject();
        try {
            jo.put("id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MyOkHttp.newInstance().postJson(context, Common.URL_DELETE_ORDERS + String.valueOf(id),
                jo, new RawResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, String response) {
                        if (statusCode==200){
                            fragment.getOrders();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        if (statusCode!=200){
                            ToastUtils.showToast(context,"删除失败");
                        }
                    }
                });
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
