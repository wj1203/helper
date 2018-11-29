package com.example.yangyang.lifehelper.ui;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.yangyang.lifehelper.R;
import com.example.yangyang.lifehelper.entity.MyUser;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class RegisteredActivity extends BaseAcitivity implements View.OnClickListener {
    private TextView et_user;
    private TextView et_age;
    private TextView et_desc;
    private TextView et_pass;
    private TextView et_password;
    private TextView et_email;
    private RadioGroup mRadioGroup;
    private Button btn_register;
    private boolean isBoy = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        initView();
    }

    private void initView() {
        et_user = findViewById(R.id.et_user);
        et_age = findViewById(R.id.et_age);
        et_desc = findViewById(R.id.et_desc);
        et_pass = findViewById(R.id.et_pass);
        et_password = findViewById(R.id.et_password);
        et_email = findViewById(R.id.et_email);
        mRadioGroup = findViewById(R.id.mRadioGroup);
        btn_register = findViewById(R.id.btn_registered_register);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_registered_register :
                register();
                break;
        }
    }

    private void register() {
        //  获取输入框的值
        String name = et_user.getText().toString().trim();
        String age = et_age.getText().toString().trim();
        String desc = et_desc.getText().toString().trim();
        String pass = et_pass.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        final String email = et_email.getText().toString();
        // 判断是否为空
        if(!TextUtils.isEmpty(name) &!TextUtils.isEmpty(age) &!TextUtils.isEmpty(pass)
                &!TextUtils.isEmpty(password)&!TextUtils.isEmpty(email)){
            // 判断密码是否相等
            if(pass.equals(password)){
                //  判断性别
                mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if(checkedId == R.id.rb_boy){
                            isBoy = true;
                        }else if(checkedId == R.id.rb_girl){
                            isBoy = false;
                        }
                    }
                });
                // 判断简介是否为空
                if(TextUtils.isEmpty(desc)){
                    desc = "这个人很懒什么都没有";
                }
                //  创建注册对象
                MyUser user = new MyUser();
                user.setUsername(name);
                user.setEmail(email);
                user.setPassword(password);
                user.setAge(Integer.parseInt(age));
                user.setSex(isBoy);
                user.setDesc(desc);
                // 申请邮箱验证
                BmobUser.requestEmailVerify(email, new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            Toast.makeText(RegisteredActivity.this,"请求验证邮件成功，请到" +email + "邮箱中进行激活。",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(RegisteredActivity.this,"邮箱验证失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                // 提交注册
                user.signUp(new SaveListener<MyUser>() {
                    @Override
                    public void done(MyUser myUser, BmobException e) {
                        if(e == null){
                            Toast.makeText(RegisteredActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(RegisteredActivity.this,"注册失败"+e.getErrorCode(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }else {
                Toast.makeText(this,"密码不一致",Toast.LENGTH_SHORT).show();
            }
            //  输入框为空
        }else {
            Toast.makeText(this,"输入框不能为空",Toast.LENGTH_SHORT).show();
        }
    }
}
