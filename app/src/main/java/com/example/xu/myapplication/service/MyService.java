package com.example.xu.myapplication.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.IntDef;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;

import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.R;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.GsonResponseHandler;
import com.example.xu.myapplication.httpRequest.response.JsonResponseHandler;
import com.example.xu.myapplication.model.MessageDetail;
import com.example.xu.myapplication.model.UserOrder;
import com.example.xu.myapplication.moduleMy.activity.orders.MyOrdersActivity;
import com.example.xu.myapplication.util.Logger;
import com.example.xu.myapplication.util.SPUtil;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    private static final String TAG = "MessageService";
    private static final String TAG_DETAIL = "messageDetail";

    private static final int HTAG1 = 0;
    private static final int HTAG2 = 1;
    private static final int HTAG3 = 2;
    private static final int HTAG_BEGAIN = 3;
    private int localId = -1;

    private Timer timer;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(final Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case HTAG1:
                    final int orderId = msg.arg1;
                    Logger.e("get orderId", orderId + "");
                    int userId = msg.arg2;
                    MyOkHttp.newInstance().get(Common.URL_USER_ID + userId, null, new JsonResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, JSONObject response) {
                            try {
                                String orders = response.getString("orders");
                                JSONArray array = new JSONArray(orders);
                                Gson gson = new Gson();
                                UserOrder order = null;
                                for (int i = 0; i < array.length(); i++) {
                                    UserOrder userOrder = gson.fromJson(String.valueOf(array.getJSONObject(i)), UserOrder.class);
                                    Logger.e("get UserOrder", userOrder.toString() + "");
                                    if (userOrder.getId() == orderId) {
                                        order = userOrder;
                                        Logger.e("get order", order.toString() + "");
                                    }
                                }
                                if (order == null)
                                    return;
                                Message msg = new Message();
                                Bundle bundle = new Bundle();
                                bundle.putParcelable(TAG_DETAIL, order);
                                msg.setData(bundle);
                                msg.what = HTAG2;
                                handler.sendMessage(msg);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, String error_msg) {
                            Logger.e("get order", error_msg);
                        }
                    });
                    break;
                case HTAG2:
                    Bundle bundle = msg.getData();
                    UserOrder order = bundle.getParcelable(TAG_DETAIL);

                    JSONObject jo = new JSONObject();
                    try {
                        jo.put("id", order.getId());
                        jo.put("orderNumber", order.getOrderNumber());
                        jo.put("orderPay", order.getOrderPay());
                        jo.put("goodsCount", order.getGoodsCount());
                        jo.put("orderState", 3);//3
                        jo.put("reviewState", 0);//0
                        jo.put("receiveTime", order.getReceiveTime());//
                        jo.put("receiveAddressId", order.getReceiveAddress().getId());//
                        jo.put("userId", order.getUser().getId());
                        jo.put("goodsId", order.getGoods().getId());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    MyOkHttp.newInstance().postJson(null, Common.URL_UPDATE_ORDERS, jo, new JsonResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, JSONObject response) {
                            Logger.e("get message", "onSuccess");
                            handler.sendEmptyMessage(HTAG3);
                        }

                        @Override
                        public void onFailure(int statusCode, String error_msg) {
                            Logger.e("get message", error_msg);
                        }
                    });

                    break;
                case HTAG3:
                    sendNotification();
                    break;
                case HTAG_BEGAIN:
                    MyOkHttp.newInstance().get(Common.URL_MESSAGE_USER, null, new GsonResponseHandler<ArrayList<MessageDetail>>() {
                        @Override
                        public void onSuccess(int statusCode, ArrayList<MessageDetail> response) {
                            for (MessageDetail messageDetail : response) {
                                if (messageDetail.getUserId() == localId) {
                                    int orderId = Integer.parseInt(messageDetail.getContent());
                                    int userId = messageDetail.getUserId();
                                    Logger.e("get orderId success", orderId + "");
                                    Message msg = new Message();
                                    msg.what = HTAG1;
                                    msg.arg1 = orderId;
                                    msg.arg2 = userId;
                                    handler.sendMessage(msg);
                                    return;
                                }
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, String error_msg) {
                            Logger.e("get orderId onFailure", error_msg);
                        }
                    });
                    break;
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        localId = Integer.parseInt(new SPUtil(this).getString(SPUtil.USER_ID, "-1"));

        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (localId == -1)
                    return;
                handler.sendEmptyMessage(HTAG_BEGAIN);
            }
        };
        timer.schedule(task, 1000, 5 * 1000);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    public void sendNotification() {
        Intent startIntent = new Intent(this, MyOrdersActivity.class);
        startIntent.putExtra("order", 2);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, startIntent, 0);

        Notification notification = new NotificationCompat.Builder(this)
                .setTicker("过期商品通知")
                .setSmallIcon(R.mipmap.logo)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.logo))
                .setContentText("即将有过期商品")
                .setContentTitle("您的商品即将过期")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(0, notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null)
            timer.cancel();
    }
}
