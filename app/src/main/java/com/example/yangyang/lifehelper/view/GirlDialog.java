package com.example.yangyang.lifehelper.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.yangyang.lifehelper.R;

/**
 * Created by yangyang on 2018/11/8.
 * 项目名：LifeHelper
 * 包名 ： com.example.yangyang.lifehelper.view
 * 作用：
 */

public class GirlDialog extends Dialog{

    public GirlDialog( Context context) {
        super(context,R.style.DialogTheme);
        Window window = this.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = LinearLayout.LayoutParams.MATCH_PARENT;
        params.height = LinearLayout.LayoutParams.MATCH_PARENT;
        window.setGravity(Gravity.CENTER);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_girl);
    }
}
