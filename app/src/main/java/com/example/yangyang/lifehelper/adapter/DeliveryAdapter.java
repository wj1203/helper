package com.example.yangyang.lifehelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.yangyang.lifehelper.R;
import com.example.yangyang.lifehelper.entity.Delivery;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangyang on 2018/11/6.
 * 项目名：LifeHelper
 * 包名 ： com.example.yangyang.lifehelper.adapter
 * 作用：
 */

public class DeliveryAdapter extends BaseAdapter{
    private Context mContext;
    private ArrayList<Delivery> mList = new ArrayList<>();
    private LayoutInflater inflater;

    public DeliveryAdapter(Context context, ArrayList<Delivery> list){
        mContext = context;
        mList = list;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Delivery delivery = mList.get(position);
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.delivery_item,parent,false);
            viewHolder.tv_zone = convertView.findViewById(R.id.tv_zone);
            viewHolder.tv_data = convertView.findViewById(R.id.tv_data);
            viewHolder.tv_remark = convertView.findViewById(R.id.tv_remark);
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_remark.setText(delivery.getRemark());
        viewHolder.tv_data.setText(delivery.getData());
        viewHolder.tv_zone.setText(delivery.getZone());
        return convertView;
    }
    class ViewHolder{  // 省去findview的步骤
        private TextView tv_data;
        private TextView tv_remark;
        private TextView tv_zone;
    }
}
