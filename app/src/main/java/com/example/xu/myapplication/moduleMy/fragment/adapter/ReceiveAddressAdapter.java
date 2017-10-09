package com.example.xu.myapplication.moduleMy.fragment.adapter;

/**
 * Created by 逝 on 2017/10/03.
 */

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.xu.myapplication.Common;
import com.example.xu.myapplication.R;
import com.example.xu.myapplication.httpRequest.MyOkHttp;
import com.example.xu.myapplication.httpRequest.response.RawResponseHandler;
import com.example.xu.myapplication.moduleMy.fragment.activity.setting.AddAddressActivity;
import com.example.xu.myapplication.moduleMy.fragment.activity.setting.QueryAddressActivity;
import com.example.xu.myapplication.moduleMy.fragment.bean.ReceiveAddressBean;
import com.example.xu.myapplication.util.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReceiveAddressAdapter extends BaseAdapter {

    private List<ReceiveAddressBean> objects = new ArrayList<ReceiveAddressBean>();

    private Context context;
    private LayoutInflater layoutInflater;
    private QueryAddressActivity activity;

    public ReceiveAddressAdapter(QueryAddressActivity activity, Context context) {
        this.activity = activity;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setData(List<ReceiveAddressBean> objects) {
        this.objects = objects;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public ReceiveAddressBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_shouhuo_address, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((ReceiveAddressBean) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(final ReceiveAddressBean object, ViewHolder holder) {
        //TODO implement
        holder.tvAddressName.setText(object.getReceiveUser());
        holder.tvAddressPhone.setText(object.getReceivePhoneNumber());
        holder.tvAddressShouhuo.setText(object.getAddress() + "\t" + object.getStreet());
        holder.tvAddressEditor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddAddressActivity.class);
                intent.putExtra("id", object.getId());
                context.startActivity(intent);
            }
        });

        holder.tvAddressDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteOrders(object.getId());
            }
        });


    }

    private void deleteOrders(int id) {
        JSONObject jo = new JSONObject();
        try {
            jo.put("id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MyOkHttp.newInstance().postJson(Common.URL_DELETE_ADDRESS_BY_ID + String.valueOf(id), jo,
                new RawResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, String response) {
                        if (statusCode == 200) {
                            activity.getAddress();
                            ToastUtils.showToast(context, "删除成功");
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        if (statusCode == 500) {
                            ToastUtils.showToast(context, "删除失败，该地址可能已使用，请确认订单后删除。");
                        }
                    }
                });

    }

    protected class ViewHolder {
        private TextView tvAddressName;
        private TextView tvAddressPhone;
        private TextView tvAddressShouhuo;
        private CheckBox cbAddressMoren;
        private TextView tvAddressEditor;
        private TextView tvAddressDelete;

        public ViewHolder(View view) {
            tvAddressName = (TextView) view.findViewById(R.id.tv_address_name);
            tvAddressPhone = (TextView) view.findViewById(R.id.tv_address_phone);
            tvAddressShouhuo = (TextView) view.findViewById(R.id.tv_address_shouhuo);
            cbAddressMoren = (CheckBox) view.findViewById(R.id.cb_address_moren);
            tvAddressEditor = (TextView) view.findViewById(R.id.tv_address_editor);
            tvAddressDelete = (TextView) view.findViewById(R.id.tv_address_delete);
        }
    }
}
