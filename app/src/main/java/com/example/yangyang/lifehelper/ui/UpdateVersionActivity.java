package com.example.yangyang.lifehelper.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.yangyang.lifehelper.R;
import com.example.yangyang.lifehelper.util.L;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.ProgressListener;
import com.kymjs.rxvolley.http.VolleyError;
import com.kymjs.rxvolley.toolbox.FileUtils;

public class UpdateVersionActivity extends AppCompatActivity {
    private TextView tv_size;
    private String url;
    private String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_version);
        initView();
    }

    private void initView() {
        tv_size = findViewById(R.id.tv_size);
        path = FileUtils.getSDCardPath()+"/"+System.currentTimeMillis()+".apk";
        // 取得url
        Intent intent = getIntent();
        url = "http://bos.pgzs.com/rbreszy/aladdin/8c953cf738df4d86b03a0b5e8d755517/91Assistant_PC_V6_16/%E5%84%BF%E6%AD%8C%E5%A4%9A%E5%A4%9AHD_%E6%9E%81%E9%80%9F%E5%AE%89%E8%A3%85.exe";
        // 下载
        RxVolley.download(path, url, new ProgressListener() {
            @Override
            public void onProgress(long transferredBytes, long totalSize) {
                L.e("transferredBytes"+transferredBytes);
            }
        }, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                L.e(t);
            }

            @Override
            public void onFailure(VolleyError error) {
                L.d("失败");
            }
        });
    }
}
