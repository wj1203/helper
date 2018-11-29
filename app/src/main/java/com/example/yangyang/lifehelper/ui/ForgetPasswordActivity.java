package com.example.yangyang.lifehelper.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yangyang.lifehelper.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText ed_email;
    private Button btn_forget_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        initView();
    }

    private void initView() {
        ed_email = findViewById(R.id.ed_email);
        btn_forget_pass = findViewById(R.id.btn_forget_pass);
        btn_forget_pass.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_forget_pass :
                findPass();
                break;

        }
    }

    private void findPass() {
        String email = ed_email.getText().toString().trim();
        if(!TextUtils.isEmpty(email)){
            BmobUser.resetPasswordByEmail(email, new UpdateListener() {

                @Override
                public void done(BmobException e) {
                    if(e==null){
                        Toast.makeText(ForgetPasswordActivity.this,"重置密码请求成功,请到邮箱进行密码重置操作",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(ForgetPasswordActivity.this,"失败:" + e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }else {
            Toast.makeText(this,"邮箱不能为空",Toast.LENGTH_SHORT).show();
        }
    }
}
