package com.example.yangyang.lifehelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yangyang.lifehelper.R;
import com.example.yangyang.lifehelper.entity.Passage;
import com.example.yangyang.lifehelper.util.PicassoUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by yangyang on 2018/11/7.
 * 项目名：LifeHelper
 * 包名 ： com.example.yangyang.lifehelper.adapter
 * 作用：
 */

public class PassageAdapter extends BaseAdapter{
    private Context mContext;
    private ArrayList<Passage> mList;
    private LayoutInflater inflater ;
    public PassageAdapter(Context context,ArrayList<Passage> list){
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
        ViewHolder viewHolder = null;
        if(convertView == null){
            // 1先实例化两个空对象
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.passage_item,parent,false);
            // 2控件存放到viewHolder中
            viewHolder.imageView = convertView.findViewById(R.id.iv_img);
            viewHolder.tv_title = convertView.findViewById(R.id.tv_title);
            viewHolder.tv_source = convertView.findViewById(R.id.tv_source);
            // 3 保存viewHolder
            convertView.setTag(viewHolder);
        }else {
            // 4 取出viewHolder
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // 5 取出当前实例对象
        Passage passage = mList.get(position);
        // 6 赋值+return
        viewHolder.tv_title.setText(passage.getTitle());
        viewHolder.tv_source.setText(passage.getSource());
        // 加载图片 api接口没有返回img的url
        //Picasso.with(mContext).load(passage.getImgUrl()).into(viewHolder.imageView);
        PicassoUtils.loadImageHolder(mContext,passage.getImgUrl(),viewHolder.imageView,
                R.drawable.ic_launcher_background,R.mipmap.ic_launcher);
        return convertView;
    }
    class ViewHolder{
        ImageView imageView;
        TextView tv_title;
        TextView tv_source;
    }
}
