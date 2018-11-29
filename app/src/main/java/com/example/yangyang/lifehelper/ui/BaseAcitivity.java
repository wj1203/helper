package com.example.yangyang.lifehelper.ui;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by yangyang on 2018/10/29.
 * 项目名：LifeHelper
 * 包名 ： com.example.yangyang.lifehelper.ui
 * 作用： activity的基类
 */

/*
*  统一的属性
*  统一的接口
*  统一的方法
* */
public class BaseAcitivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        // 显示返回键(设置HomeAsUp可见)
        getActionBar().setDisplayHomeAsUpEnabled(true);

    }

    // 设置返回键的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}





















