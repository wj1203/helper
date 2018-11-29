package com.example.yangyang.lifehelper.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

import com.example.yangyang.lifehelper.util.L;

public class MessageService extends Service {
    private MessageReceiver receiver ;
    @Override
    public void onCreate() {
        super.onCreate();
        L.d("Service:onCreate");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        L.d("Service:onStartCommand");
        init();
        return super.onStartCommand(intent, flags, startId);
    }
    private void init() {
        // 动态注册广播接收器

        // 2 new一个intentfilter
        IntentFilter intentFilter = new IntentFilter();
        // 3 添加权限
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        intentFilter.setPriority(1000);
        // 1 实例化广播接收器对象
        receiver = new MessageReceiver();
        // 4 注册
        getBaseContext().registerReceiver(receiver,intentFilter);
        L.d("registerReceiver");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        L.d("Service:onDestroy");
        // 注销接收器
        unregisterReceiver(receiver);
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    // 短信广播接收器
    public class MessageReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"收到短信",Toast.LENGTH_LONG).show();
        }
    }
}
