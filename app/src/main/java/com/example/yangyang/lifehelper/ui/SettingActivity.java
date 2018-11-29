package com.example.yangyang.lifehelper.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yangyang.lifehelper.R;
import com.example.yangyang.lifehelper.service.MessageService;
import com.example.yangyang.lifehelper.util.L;
import com.example.yangyang.lifehelper.util.ShareUtils;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import org.json.JSONException;
import org.json.JSONObject;

public class  SettingActivity extends BaseAcitivity implements View.OnClickListener {
    private Switch sw_message;
    private TextView tv_check_version;
    private int versionCode;
    private String versionName;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    private void initView() {
        sw_message = findViewById(R.id.sw_message);
        sw_message.setOnClickListener(this);
        // 取出switch的状态
        boolean is_message = ShareUtils.getBoolean(this,"is_message",false);
        sw_message.setChecked(is_message);
        if(sw_message.isChecked()){
            startService(new Intent(this,MessageService.class));
        }else {
            stopService(new Intent(this,MessageService.class));
        }
        tv_check_version = findViewById(R.id.tv_check_version);
        tv_check_version.setOnClickListener(this);
        getVersion();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sw_message:
                // switch切换为相反状态
                sw_message.setSelected(!sw_message.isSelected());
                // 保存状态
                ShareUtils.putBoolean(this,"is_message",sw_message.isChecked());
                if(sw_message.isChecked()){
                    startService(new Intent(this,MessageService.class));
                }else {
                    stopService(new Intent(this,MessageService.class));
                }
                break;
            case R.id.tv_check_version :
                /*
                *  1 请求服务器配置文件，拿到versionCode
                *  2 比较
                *  3 dialog提示
                *  4 跳转到跟新界面传入配置文件的url
                * */
                //1 请求服务器配置文件，拿到versionCode
                String url = "http://192.168.2.176:8080/wj/config.json";
                RxVolley.get(url, new HttpCallback() {
                    @Override
                    public void onSuccess(String t) {
                        L.d(t);
                        // 解析json
                        parseJSON(t);
                    }
                });
                break;
        }
    }

    private void parseJSON(String t) {
        try {
            L.d("开始解析json");
            JSONObject object = new JSONObject(t);
            int code = object.getInt("versionCode");
            url = object.getString("url");
            L.d("code="+code);
            if(code>versionCode){
                showUpdateDialog(object.getString("content"));
            }else {
                Toast.makeText(this,"当前为最新版本",Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showUpdateDialog(String content) {
        new AlertDialog
                .Builder(this)
                .setTitle("有新版本了")
                .setMessage(content)
                .setPositiveButton("更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(SettingActivity.this,UpdateVersionActivity.class);
                        intent.putExtra("url",url);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    private void getVersion(){
        try {
            PackageManager manager = getPackageManager();
            PackageInfo info = manager.getPackageInfo(getPackageName(),0);
            versionCode = info.versionCode;
            versionName = info.versionName;
            tv_check_version.setText("检测版本"+versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
