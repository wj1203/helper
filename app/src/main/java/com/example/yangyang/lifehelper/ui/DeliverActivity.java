package com.example.yangyang.lifehelper.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yangyang.lifehelper.R;
import com.example.yangyang.lifehelper.adapter.DeliveryAdapter;
import com.example.yangyang.lifehelper.entity.Delivery;
import com.example.yangyang.lifehelper.util.L;
import com.example.yangyang.lifehelper.util.StaticClass;
import com.example.yangyang.lifehelper.util.UtilTools;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.http.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;

public class DeliverActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_com;
    private EditText et_no;
    private Button btn_deliver_query;
    private ListView listView;
    private ArrayList<Delivery> mList;
    private DeliveryAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver);
        initView();
    }

    private void initView() {
        et_com = findViewById(R.id.et_com);
        et_no = findViewById(R.id.et_no);
        btn_deliver_query = findViewById(R.id.btn_deliver_query);
        btn_deliver_query.setOnClickListener(this);
        listView = findViewById(R.id.list_view);
        mList = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_deliver_query :
                /*
                * 1 获取输入框内容
                * 2 判断是否为空
                * 3 请求
                * 4 解析
                * 5 listview适配
                * 6 实体类
                * 7 显示
                * */
                query();
                break;
        }
    }

    private void query() {

        // 1 获取输入框内容
        String com = et_com.getText().toString().trim();
        String no = et_no.getText().toString().trim();
        // 2 判断是否为空
        if(!TextUtils.isEmpty(com)&!TextUtils.isEmpty(no)){
            // 3 请求
            String uri = "http://v.juhe.cn/exp/index?key="+ StaticClass.COURIER_KEY
                    +"&com="+com+"&no="+no;
            L.d("3 请求");
            RxVolley.get(uri, new HttpCallback() {
                @Override
                public void onSuccess(String t) {
                    // 4 解析
                    L.d("success"+t);
                    parseJSON(t);
                    listView.setAdapter(adapter);
                }
            });


        }else {
            Toast.makeText(this,"输入框不能为空",Toast.LENGTH_SHORT);
        }
    }
    // 解析json
    private void parseJSON(String t) {
        try {
            // 将字符串转为jsonObject
            JSONObject jsonObject = new JSONObject(t);
            // 取出json中的result
            JSONObject result = jsonObject.getJSONObject("result");
            // 取出result中的list
            JSONArray array = result.getJSONArray("list");
            for(int i = 0;i<array.length();i++){
                JSONObject obj = (JSONObject) array.get(i);
                Delivery delivery = new Delivery();
                delivery.setData(obj.getString("datetime"));
                delivery.setZone(obj.getString("zone"));
                delivery.setRemark(obj.getString("remark"));
                mList.add(delivery);
            }
            Collections.reverse(mList);
            adapter = new DeliveryAdapter(this,mList);
            listView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
