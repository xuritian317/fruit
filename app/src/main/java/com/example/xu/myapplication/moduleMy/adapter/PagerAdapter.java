package com.example.xu.myapplication.moduleMy.adapter;

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
import com.example.xu.myapplication.moduleMy.bean.OrdersBean;
import com.example.xu.myapplication.moduleMy.child_frag.PagerChildFragment;
import com.example.xu.myapplication.util.ToastUtils;

import net.lemonsoft.lemonhello.LemonHello;
import net.lemonsoft.lemonhello.LemonHelloAction;
import net.lemonsoft.lemonhello.LemonHelloInfo;
import net.lemonsoft.lemonhello.LemonHelloView;
import net.lemonsoft.lemonhello.interfaces.LemonHelloActionDelegate;

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

    public PagerAdapter(PagerChildFragment fragment, Context context, int mForm) {
        this.fragment = fragment;
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
        Glide.with(context).load(object.getGoods().getGoodsImage()).into(holder
                .ivOrdersItemImg);
        holder.tvOrdersItemFruit.setText(object.getGoods().getGoodsName());
        holder.tvOrdersItemPrice.setText("￥" + object.getGoods().getGoodsPrice());
        holder.tvOrdersItemCount.setText("×" + object.getGoodsCount());
        holder.tvOrdersZong.setText("合计" + object.getGoodsCount() + "件商品，实付款:￥" +
                object.getOrderPay());
        String time = object.getReceiveTime().substring(0, 16);
        holder.tvOrdersReceiveTime.setText(time.replace("T", " "));
        switch (mForm) {
            case 0:
                if (object.getOrderState() == 0) {
                    if (object.getReviewState() == 1) {
                        holder.btnOrders0.setVisibility(View.VISIBLE);
                        holder.btnOrders1.setVisibility(View.VISIBLE);
                        holder.btnOrders1.setText("删除订单");
                        holder.btnOrders0.setText("评价");
                        holder.btnOrders1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                deleteOrder(object.getId(),null,"确定要删除订单吗？");
                            }
                        });
                        holder.btnOrders0.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtils.showToast(context, "该功能暂未开启");
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
                                deleteOrder(object.getId(),null,"确定要删除订单吗？");
                            }
                        });
                    }
                    holder.tvOrdersState.setText("已收货");
                } else if (object.getOrderState() == 1) {
                    holder.btnOrders0.setVisibility(View.VISIBLE);
                    holder.btnOrders1.setVisibility(View.VISIBLE);
                    holder.btnOrders1.setText("退款");
                    holder.btnOrders0.setText("确认收货");
                    holder.tvOrdersState.setText("待收货");

                    holder.btnOrders1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            updateOrders(object.getId(), object.getOrderNumber(),
                                    object.getOrderPay(), object.getGoodsCount(), 2, 0,
                                    object.getReceiveTime(), object.getReceiveAddress().getId()
                                    , object.getUser().getId(), object.getGoods().getId(),
                                    "确定要退款吗？");
                        }
                    });

                    holder.btnOrders0.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            updateOrders(object.getId(), object.getOrderNumber(),
                                    object.getOrderPay(), object.getGoodsCount(), 0, 1,
                                    object.getReceiveTime(), object.getReceiveAddress().getId()
                                    , object.getUser().getId(), object.getGoods().getId(),
                                    "是否已收到物品？");
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
                            deleteOrder(object.getId(),null,"确定要删除订单吗？");
                        }
                    });
                }
                break;
            case 1:
                holder.tvOrdersState.setText("待收货");
                holder.btnOrders0.setVisibility(View.VISIBLE);
                holder.btnOrders1.setVisibility(View.VISIBLE);
                holder.btnOrders1.setText("退款");
                holder.btnOrders0.setText("确认收货");
                holder.btnOrders1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateOrders(object.getId(), object.getOrderNumber(), object.getOrderPay()
                                , object.getGoodsCount(), 2, 0, object.getReceiveTime(),
                                object.getReceiveAddress().getId(), object.getUser().getId(),
                                object.getGoods().getId(), "确定要退款吗？");
                    }
                });
                holder.btnOrders0.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateOrders(object.getId(), object.getOrderNumber(), object.getOrderPay()
                                , object.getGoodsCount(), 0, 1, object.getReceiveTime(),
                                object.getReceiveAddress().getId(), object.getUser().getId(),
                                object.getGoods().getId(), "是否已收到物品？");
                    }
                });
                break;
            case 2:
                holder.tvOrdersState.setText("特价出售");
                holder.btnOrders0.setVisibility(View.VISIBLE);
                holder.btnOrders1.setVisibility(View.VISIBLE);
                holder.btnOrders0.setText("继续收货");
                holder.btnOrders1.setText("放弃收货");

                holder.btnOrders1.setBackground(context.getDrawable(R.drawable
                        .btn_style_red));
                holder.btnOrders1.setTextColor(context.getResources().getColor(R.color
                        .color_Red));

                holder.btnOrders0.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        updateOrders(object.getId(),object.getOrderNumber(),object.getOrderPay(),
//                                object.getGoodsCount(),1,0,object.getReceiveTime(),
//                                object.getReceiveAddress().getId(),object.getUser().getId(),
//                                object.getGoods().getId(),"确定要继续收货吗？");
                        LemonHello.getInformationHello("确定要继续收货吗", "请在两小时内取货，否则商品自动特价处理")
                                .setContentFontSize(13)
                                .setWidth(300)
                                .addAction(new LemonHelloAction("取消", new LemonHelloActionDelegate() {
                                    @Override
                                    public void onClick(LemonHelloView lemonHelloView, LemonHelloInfo
                                            lemonHelloInfo, LemonHelloAction lemonHelloAction) {
                                        lemonHelloView.hide();
                                    }
                                }))
                                .addAction(new LemonHelloAction("确定", new LemonHelloActionDelegate() {
                                    @Override
                                    public void onClick(final LemonHelloView lemonHelloView, LemonHelloInfo
                                            lemonHelloInfo, LemonHelloAction lemonHelloAction) {
                                        lemonHelloView.hide();
                                    }
                                })).show(context);
                    }
                });
                holder.btnOrders1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        deleteOrder(object.getId(),"确定要放弃收货吗？",
                                "放弃收货后，该商品会自动特价处理，余额会自动转入您的账户");
                    }
                });
                break;
            case 3:
                holder.tvOrdersState.setText("已收货");
                if (object.getReviewState() == 1) {
                    holder.btnOrders0.setVisibility(View.VISIBLE);
                    holder.btnOrders1.setVisibility(View.VISIBLE);
                    holder.btnOrders1.setText("删除订单");
                    holder.btnOrders0.setText("评价");
                    holder.btnOrders1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            deleteOrder(object.getId(),null,"确定要删除订单吗？");
                        }
                    });
                    holder.btnOrders0.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ToastUtils.showToast(context, "该功能暂未开启");
                        }
                    });
                } else {
                    holder.btnOrders0.setVisibility(View.VISIBLE);
                    holder.btnOrders0.setBackground(context.getDrawable(R.drawable.btn_style_gray));
                    holder.btnOrders0.setTextColor(context.getResources().getColor(R.color.gray));
                    holder.btnOrders0.setText("删除订单");
                    holder.btnOrders0.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            deleteOrder(object.getId(),null,"确定要删除订单吗？");
                        }
                    });
                }
                break;
            case 4:
                holder.tvOrdersState.setText("已收货");
                holder.btnOrders0.setVisibility(View.VISIBLE);
                holder.btnOrders1.setVisibility(View.VISIBLE);
                holder.btnOrders1.setText("删除订单");
                holder.btnOrders0.setText("评价");

                holder.btnOrders1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteOrder(object.getId(),null,"确定要删除订单吗？");
                    }
                });
                holder.btnOrders0.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showToast(context, "该功能暂未开启");
                    }
                });

                break;
            case 5:
                holder.tvOrdersState.setText("已取消");
                holder.btnOrders0.setVisibility(View.VISIBLE);
                holder.btnOrders0.setBackground(context.getDrawable(R.drawable.btn_style_gray));
                holder.btnOrders0.setTextColor(context.getResources().getColor(R.color.gray));
                holder.btnOrders0.setText("删除订单");
                holder.btnOrders0.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteOrder(object.getId(),null,"确定要删除订单吗？");
                    }
                });

                break;
        }

    }

    /*
    修改订单
     */
    private void updateOrders(final int id, final String orderNumber, final String orderPay,
                              final int goodsCount, final int orderState, final int reviewState,
                              final String receiveTime, final int receiveAddressId,
                              final int userId, final int goodsId, String str) {
        LemonHello.getInformationHello(null, str)
                .setContentFontSize(18)
                .setWidth(300)
                .addAction(new LemonHelloAction("取消", new LemonHelloActionDelegate() {
                    @Override
                    public void onClick(LemonHelloView lemonHelloView, LemonHelloInfo
                            lemonHelloInfo, LemonHelloAction lemonHelloAction) {
                        lemonHelloView.hide();
                    }
                }))
                .addAction(new LemonHelloAction("确定", new LemonHelloActionDelegate() {
                    @Override
                    public void onClick(final LemonHelloView lemonHelloView, LemonHelloInfo
                            lemonHelloInfo, LemonHelloAction lemonHelloAction) {

                        JSONObject jo = new JSONObject();
                        try {
                            jo.put("id", id);
                            jo.put("orderNumber", orderNumber);
                            jo.put("orderPay", orderPay);
                            jo.put("goodsCount", goodsCount);
                            jo.put("orderState", orderState);//3
                            jo.put("reviewState", reviewState);//0
                            jo.put("receiveTime", receiveTime);//
                            jo.put("receiveAddressId", receiveAddressId);//
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
                                            lemonHelloView.hide();
                                        }
                                    }

                                    @Override
                                    public void onFailure(int statusCode, String error_msg) {
                                        ToastUtils.showToast(context, "操作执行失败");
                                        lemonHelloView.hide();
                                    }
                                });

                    }
                })).show(context);
    }

    /*
    删除订单
     */
    private void deleteOrder(final int id,String str1,String str2) {
        LemonHello.getErrorHello(str1, str2)
                .setContentFontSize(18)
                .setWidth(300)
                .addAction(new LemonHelloAction("取消", new LemonHelloActionDelegate() {
                    @Override
                    public void onClick(LemonHelloView helloView, LemonHelloInfo
                            helloInfo, LemonHelloAction helloAction) {
                        //dialog隐藏
                        helloView.hide();
                    }
                }))
                .addAction(new LemonHelloAction("确定", new LemonHelloActionDelegate() {
                    @Override
                    public void onClick(final LemonHelloView helloView, LemonHelloInfo
                            helloInfo, LemonHelloAction helloAction) {
                        //删除并更新列表
                        JSONObject jo = new JSONObject();
                        try {
                            jo.put("id", id);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        MyOkHttp.newInstance().postJson(context, Common.URL_DELETE_ORDERS +
                                        String.valueOf(id),
                                jo, new RawResponseHandler() {
                                    @Override
                                    public void onSuccess(int statusCode, String response) {
                                        if (statusCode == 200) {
                                            fragment.getOrders();
                                            //dialog隐藏
                                            helloView.hide();
                                        }
                                    }

                                    @Override
                                    public void onFailure(int statusCode, String error_msg) {
                                        if (statusCode != 200) {
                                            ToastUtils.showToast(context, "删除失败");
                                            //dialog隐藏
                                            helloView.hide();
                                        }
                                    }
                                });

                    }
                }))
                .show(context);

    }


    protected class ViewHolder {
        private LinearLayout linearOrdersItem;
        private ImageView ivOrdersItemImg;
        private TextView tvOrdersItemFruit;
        private TextView tvOrdersItemPrice;
        private TextView tvOrdersItemCount;
        private TextView tvOrdersReceiveTime;
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
            tvOrdersReceiveTime = (TextView) view.findViewById(R.id.tv_orders_receiveTime);
            tvOrdersState = (TextView) view.findViewById(R.id.tv_orders_state);
            tvOrdersZong = (TextView) view.findViewById(R.id.tv_orders_zong);
            btnOrders2 = (Button) view.findViewById(R.id.btn_orders_2);
            btnOrders1 = (Button) view.findViewById(R.id.btn_orders_1);
            btnOrders0 = (Button) view.findViewById(R.id.btn_orders_0);
        }
    }
}
