package com.example.yangyang.lifehelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.example.yangyang.lifehelper.R;
import com.example.yangyang.lifehelper.entity.Girl;
import com.example.yangyang.lifehelper.util.PicassoUtils;
import java.util.ArrayList;

/**
 * Created by yangyang on 2018/11/8.
 * 项目名：LifeHelper
 * 包名 ： com.example.yangyang.lifehelper.adapter
 * 作用：
 */

public class GirlAdapter extends BaseAdapter{
    private Context mContext;
    private ArrayList<Girl> mList;
    private LayoutInflater inflater;
    private WindowManager manager;
    private int width;
    public GirlAdapter(Context context,ArrayList<Girl> list){
        mContext = context;
        mList = list;
        inflater = LayoutInflater.from(mContext);
        manager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        width = manager.getDefaultDisplay().getWidth();
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
            if (convertView == null){
                // 1先实例化两个空对象
                viewHolder = new ViewHolder();
                convertView = inflater.inflate(R.layout.girl_item,parent,false);
                // 2控件存放到viewHolder中
                viewHolder.imageView = convertView.findViewById(R.id.iv_girl_item);
                // 3 保存viewHolder
                convertView.setTag(viewHolder);
            }else {
                // 4 取出viewHolder
                viewHolder = (ViewHolder) convertView.getTag();
            }
            // 5 取出当前实例对象
        Girl girl = mList.get(position);
            // 6 赋值+return
        PicassoUtils.loadImageSize(mContext,girl.getUrl(),viewHolder.imageView,width/2,400);
        return convertView;
    }
    class ViewHolder{
        ImageView imageView;
    }
}







