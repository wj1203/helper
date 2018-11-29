package com.example.yangyang.lifehelper.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yangyang.lifehelper.MainActivity;
import com.example.yangyang.lifehelper.R;
import com.example.yangyang.lifehelper.entity.MyUser;
import com.example.yangyang.lifehelper.util.L;
import com.example.yangyang.lifehelper.util.ShareUtils;
import com.example.yangyang.lifehelper.util.StaticClass;
import com.example.yangyang.lifehelper.view.CustomDialog;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_registered;
    private Button btn_login;
    private TextView et_name;
    private TextView et_password;
    private CheckBox checkBox_remember;
    private TextView tv_forget_password;
    private CustomDialog customDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        btn_registered = findViewById(R.id.btn_registered);
        btn_login = findViewById(R.id.btn_login);
        et_name = findViewById(R.id.et_name);
        et_password = findViewById(R.id.et_password);
        checkBox_remember =findViewById(R.id.checkbox_remember);
        tv_forget_password = findViewById(R.id.tv_forget_password);
        customDialog = new CustomDialog(LoginActivity.this);
        tv_forget_password.setOnClickListener(this);
        btn_registered.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        //  设置选中状态
        boolean isChecked = ShareUtils.getBoolean(this,"isChecked_key",false);
        checkBox_remember.setChecked(isChecked);
        if(isChecked){  // 被选中
            et_name.setText(ShareUtils.getString(this,StaticClass.ISCHECKED_NAME_KEY,""));
            et_password.setText(ShareUtils.getString(this,StaticClass.ISCHECKED_PASSWORD_KEY,""));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_registered :
                startActivity(new Intent(this,RegisteredActivity.class));
                break;
            case R.id.btn_login :
                login();
                customDialog.dismiss();
                break;
            case R.id.tv_forget_password :
                startActivity(new Intent(this,ForgetPasswordActivity.class));
                break;
        }
    }

    private void login() {
        String name = et_name.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        if(checkBox_remember.isChecked()){
            ShareUtils.putBoolean(this,"isChecked_key",true);
            ShareUtils.putString(this, StaticClass.ISCHECKED_NAME_KEY,name);
            ShareUtils.putString(this,StaticClass.ISCHECKED_PASSWORD_KEY,password);
        }else {
            ShareUtils.putBoolean(this,"isChecked_key",false);
            ShareUtils.deleShare(this,StaticClass.ISCHECKED_NAME_KEY);
            ShareUtils.deleShare(this,StaticClass.ISCHECKED_PASSWORD_KEY);
        }

        // 判断是否为空
        if(!TextUtils.isEmpty(name)&!TextUtils.isEmpty(password)){
            customDialog.show();
            // 登陆
            BmobUser.loginByAccount(name, password, new LogInListener<MyUser>() {
                @Override
                public void done(MyUser user, BmobException e) {
                    // 账号密码验证成功
                    if(user!=null){
                        // 判断邮箱是否验证
                        if (user.getEmailVerified()){
                            L.i("用户登陆成功");
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }else{
                            Toast.makeText(LoginActivity.this,"邮箱未验证，请前往邮箱验证",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(LoginActivity.this,"账号或密码错误，登陆失败"+e.getErrorCode(),Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }else {
            Toast.makeText(this,"不能为空",Toast.LENGTH_SHORT).show();
        }
    }
}













