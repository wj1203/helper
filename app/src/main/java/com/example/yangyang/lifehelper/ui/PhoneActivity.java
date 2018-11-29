package com.example.yangyang.lifehelper.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yangyang.lifehelper.R;

public class PhoneActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_number;
    private TextView tv_result;
    private Button btn_phone_query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        initView();
    }

    private void initView() {
        et_number = findViewById(R.id.et_number);
        tv_result = findViewById(R.id.tv_result);
        btn_phone_query = findViewById(R.id.btn_phone_query);
        btn_phone_query.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_phone_query :
                query();
                break;
        }
    }

    private void query() {

    }
}
