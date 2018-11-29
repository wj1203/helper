package com.example.yangyang.lifehelper.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.yangyang.lifehelper.MainActivity;
import com.example.yangyang.lifehelper.R;
import com.example.yangyang.lifehelper.util.ShareUtils;
import com.example.yangyang.lifehelper.util.StaticClass;
import com.example.yangyang.lifehelper.util.UtilTools;


/*
* 延时2000ms
* 判断是否第一次运行
* 自定义字体
* 全屏主题
* */
public class SplashActivity extends AppCompatActivity {
    private TextView tv_splash;
    private Handler handler = new Handler()  {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case StaticClass.HANDLER_SPLASH :
                    if(isFirst()){
                        startActivity(new Intent(SplashActivity.this,GuideActivity.class));
                    }else {
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    }
                    finish();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }

    //初始化view
    private void initView() {
        // handler发送延迟空消息（数据）到msg.what
        handler.sendEmptyMessageDelayed(StaticClass.HANDLER_SPLASH,2000);
        tv_splash = findViewById(R.id.tv_splash);
        // 设置字体
        UtilTools.setFont(this,tv_splash);

    }
    private boolean isFirst() {
        boolean isFirst = ShareUtils.getBoolean(this,StaticClass.Share_isFirst,true);
        if(isFirst){
            ShareUtils.putBoolean(this,StaticClass.Share_isFirst,false);
            return true;
        }else {
            return false;
        }
    }
    // 禁止返回
    @Override
    public void onBackPressed() {
 //       super.onBackPressed();
    }
}
