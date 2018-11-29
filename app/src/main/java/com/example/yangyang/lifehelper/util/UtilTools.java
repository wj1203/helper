package com.example.yangyang.lifehelper.util;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

/**
 * Created by yangyang on 2018/10/29.
 * 项目名：LifeHelper
 * 包名 ： com.example.yangyang.lifehelper.util
 * 作用： 封装工具类
 */

public class UtilTools {
        // 字体方法
    public static void setFont(Context mContext, TextView textView){
        Typeface typeface = Typeface.createFromAsset(mContext.getAssets(),"fonts/char.ttf");
        textView.setTypeface(typeface);
    }
}
