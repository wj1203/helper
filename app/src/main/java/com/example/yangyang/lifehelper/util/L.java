package com.example.yangyang.lifehelper.util;

import android.util.Log;

/**
 * Created by yangyang on 2018/10/30.
 * 项目名：LifeHelper
 * 包名 ： com.example.yangyang.lifehelper.util
 * 作用：  Log的封装
 */

public class L {
    // 开关
    public static final boolean DEBUGE = true;
    // TAG
    public  static  final  String TAG = "Smartbulter";
    // 五个等级
    public static void i(String text){
        if(DEBUGE){
            Log.i(TAG,text);
        }
    }
    public static void w(String text){
        if(DEBUGE){
            Log.w(TAG,text);
        }
    }
    public static void e(String text){
        if(DEBUGE){
            Log.e(TAG,text);
        }
    }

    public static void d(String text){
        if(DEBUGE){
            Log.d(TAG,text);
        }
    }
}
