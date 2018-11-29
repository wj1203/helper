package com.example.yangyang.lifehelper.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.yangyang.lifehelper.R;

/**
 * Created by yangyang on 2018/11/1.
 * 项目名：LifeHelper
 * 包名 ： com.example.yangyang.lifehelper.view
 * 作用：  自定义dialog
 */

public class CustomDialog extends Dialog {
    public CustomDialog( Context context) {
        super(context, R.style.DialogTheme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loding);
    }
}
