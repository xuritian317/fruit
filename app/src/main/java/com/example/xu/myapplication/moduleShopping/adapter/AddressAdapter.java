package com.example.xu.myapplication.moduleShopping.adapter;

/**
 * Created by 逝 on 2017/10/03.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.xu.myapplication.R;
import com.example.xu.myapplication.moduleMy.bean.ReceiveAddressBean;

import java.util.ArrayList;
import java.util.List;

public class AddressAdapter extends BaseAdapter {

    private List<ReceiveAddressBean> objects = new ArrayList<ReceiveAddressBean>();

    private Context context;
    private LayoutInflater layoutInflater;

    public AddressAdapter(Context context) {
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
            convertView = layoutInflater.inflate(R.layout.item_address, null);
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
    }

    protected class ViewHolder {
        private TextView tvAddressName;
        private TextView tvAddressPhone;
        private TextView tvAddressShouhuo;

        public ViewHolder(View view) {
            tvAddressName = (TextView) view.findViewById(R.id.tv_address_name);
            tvAddressPhone = (TextView) view.findViewById(R.id.tv_address_phone);
            tvAddressShouhuo = (TextView) view.findViewById(R.id.tv_address_shouhuo);
        }
    }
}
