package com.example.yangyang.lifehelper.application;

import android.app.Application;
import com.example.yangyang.lifehelper.util.StaticClass;
import com.tencent.bugly.crashreport.CrashReport;
import cn.bmob.v3.Bmob;

/**
 * Created by yangyang on 2018/10/29.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //  初始化bugly
        CrashReport.initCrashReport(getApplicationContext(), StaticClass.BUGLY_APP_ID, true);
        //  初始化bmob
        Bmob.initialize(this, StaticClass.BMOB_APP_ID);

    }
}
