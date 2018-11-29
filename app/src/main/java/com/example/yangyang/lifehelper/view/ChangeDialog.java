package com.example.yangyang.lifehelper.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.yangyang.lifehelper.R;

/**
 * Created by yangyang on 2018/11/2.
 * 项目名：LifeHelper
 * 包名 ： com.example.yangyang.lifehelper.view
 * 作用：
 */

public class ChangeDialog extends Dialog {
    public ChangeDialog( Context context) {
        super(context,R.style.DialogTheme);
        Window window = this.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        window.setGravity(Gravity.BOTTOM);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_change);
        // 点击空白处不能取消dialog
        setCanceledOnTouchOutside(false);
    }
}
