package com.example.yangyang.lifehelper.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

/**
 * Created by yangyang on 2018/11/8.
 * 项目名：LifeHelper
 * 包名 ： com.example.yangyang.lifehelper.util
 * 作用：
 */

public class PicassoUtils  {
    // 加载图片
    public static void loadImage(Context context,String url, ImageView imageView){
        Picasso.with(context).load(url).into(imageView);
    }
    // 指定大小加载图片
    public static void loadImageSize(Context context,String url, ImageView imageView,int width,int height){
        Picasso.with(context).load(url)
                .resize(width,height)
                .centerCrop()
                .into(imageView);
    }
    // 带默认图片加载
    public static void loadImageHolder(Context context,String url, ImageView imageView,int imgHolder,int imgError){
        Picasso.with(context).load(url)
                .placeholder(imgHolder)
                .error(imgError)
                .into(imageView);
    }
    // 自定义加载
    public static void loadImageCustom(Context context,String url,ImageView imageView){
        Picasso.with(context).load(url)
                .transform(new CropSquareTransformation())
                .into(imageView);

    }
    public static class CropSquareTransformation implements Transformation {
        @Override public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;
            Bitmap result = Bitmap.createBitmap(source, x, y, size, size);
            if (result != source) {
                source.recycle();
            }
            return result;
        }

        @Override public String key() { return "square()"; }
    }
}











