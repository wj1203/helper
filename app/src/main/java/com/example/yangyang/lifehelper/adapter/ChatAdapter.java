package com.example.yangyang.lifehelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.yangyang.lifehelper.R;
import com.example.yangyang.lifehelper.entity.Chat;

import java.util.ArrayList;

/**
 * Created by yangyang on 2018/11/11.
 * 项目名：LifeHelper
 * 包名 ： com.example.yangyang.lifehelper.adapter
 * 作用：
 */

public class ChatAdapter extends BaseAdapter {
    public static final int VALUE_LEFT_TEXT = 1;
    public static final int VALUE_RIGHT_TEXT = 2;

    private Context mContext;
    private ArrayList<Chat> mList;
    private LayoutInflater inflater ;
    private Chat chat;
    public ChatAdapter(Context context,ArrayList<Chat> list){
        mContext = context;
        mList = list;
        inflater = LayoutInflater.from(context);
       // inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        LeftViewHolder leftViewHolder = null;
        RightViewHolder rightViewHolder = null;
        chat = mList.get(position);
        // 取出当前item的type判断加载哪个layout
        int type = chat.getType();
        if(convertView == null){
            switch (type){
                case VALUE_LEFT_TEXT :
                    leftViewHolder = new LeftViewHolder();
                    convertView = inflater.inflate(R.layout.chat_left_item,null);
                    leftViewHolder.tv_left = convertView.findViewById(R.id.tv__left);
                    convertView.setTag(leftViewHolder);
                    break;
                case VALUE_RIGHT_TEXT :
                    rightViewHolder = new RightViewHolder();
                    convertView = inflater.inflate(R.layout.chat_right_item,null);
                    rightViewHolder.tv_right = convertView.findViewById(R.id.tv_right);
                    convertView.setTag(rightViewHolder);
                    break;
            }
        }else {
            switch (type){
            case VALUE_LEFT_TEXT :
                leftViewHolder = (LeftViewHolder) convertView.getTag();
                break;
            case VALUE_RIGHT_TEXT :
                rightViewHolder = (RightViewHolder) convertView.getTag();
                break;
            }
        }
        // 赋值
        switch (type){
            case VALUE_LEFT_TEXT :
                leftViewHolder.tv_left.setText(chat.getText());
                break;
            case VALUE_RIGHT_TEXT :
                rightViewHolder.tv_right.setText(chat.getText());
                break;
        }
        return convertView;
    }

    @Override // 获取item的type
    public int getItemViewType(int position) {
        Chat chat = mList.get(position);
        return chat.getType();
    }

    @Override // 获取layout的数量
    public int getViewTypeCount() {
        return 3;
    }

    class LeftViewHolder{
        private TextView tv_left;
    }
    class RightViewHolder{
        private TextView tv_right;
    }
}
