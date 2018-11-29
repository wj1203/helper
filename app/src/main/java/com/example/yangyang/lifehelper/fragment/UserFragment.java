package com.example.yangyang.lifehelper.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yangyang.lifehelper.R;
import com.example.yangyang.lifehelper.entity.MyUser;
import com.example.yangyang.lifehelper.ui.DeliverActivity;
import com.example.yangyang.lifehelper.ui.LoginActivity;
import com.example.yangyang.lifehelper.ui.PhoneActivity;
import com.example.yangyang.lifehelper.util.L;
import com.example.yangyang.lifehelper.view.ChangeDialog;

import java.io.File;
import java.io.IOException;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserFragment extends Fragment implements View.OnClickListener {
    private Button btn_logout;

    private EditText et_name;
    private EditText et_age;
    private EditText et_sex;
    private EditText et_desc;

    private TextView tv_edit;
    private Button btn_confirm_modify ;

    private CircleImageView profile_image;
    private ChangeDialog changeDialog;
    private Button btn_camera;
    private Button btn_photo;
    private Button btn_release;
    private TextView tv_deliver_query;
    private TextView tv_phone_query;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        btn_logout = view.findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(this);

        et_name = view.findViewById(R.id.et_name);
        et_age = view.findViewById(R.id.et_age);
        et_desc = view.findViewById(R.id.et_desc);
        et_sex = view.findViewById(R.id.et_sex);

        tv_edit = view.findViewById(R.id.tv_edit);
        tv_edit.setOnClickListener(this);
        btn_confirm_modify = view.findViewById(R.id.btn_comfirm_modify);
        btn_confirm_modify.setOnClickListener(this);
        profile_image = view.findViewById(R.id.profile_image);
        profile_image.setOnClickListener(this);
        // 设置为不可修改
        setEnable(false);
        // 初始化user数据
        MyUser userInfo = BmobUser.getCurrentUser(MyUser.class);
        et_name.setText(userInfo.getUsername());
        et_age.setText(userInfo.getAge()+" ");
        et_sex.setText(userInfo.isSex()?"男":"女");
        et_desc.setText(userInfo.getDesc());

        tv_deliver_query = view.findViewById(R.id.tv_deliver_query);
        tv_deliver_query.setOnClickListener(this);

        tv_phone_query = view.findViewById(R.id.tv_phone_query);
        tv_phone_query.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            // 退出登录
            case R.id.btn_logout :
                logout();
                break;
            // 编辑资料
            case R.id.tv_edit :
                // 1 设置为可编辑，显示确认按钮
                setEnable(true);
                btn_confirm_modify.setVisibility(View.VISIBLE);
                break;
            // 确认编辑
            case R.id.btn_comfirm_modify :
                editFile();
                break;
            // 修改头像
            case R.id.profile_image :
               editPic();
                break;
            // 调用相机
            case R.id.btn_camera :
                toCamera();
                break;
            // 调用图片
            case R.id.btn_photo:
                toPhoto();
                break;
            // 取消
            case R.id.btn_release:
                changeDialog.dismiss();
                break;
            // 查询物流
            case R.id.tv_deliver_query :
                Intent intent_to_delivery = new Intent(getActivity(),DeliverActivity.class);
                startActivity(intent_to_delivery);
                break;
            // 归属地查询
            case R.id.tv_phone_query:
                Intent intent_to_phone = new Intent(getActivity(),PhoneActivity.class);
                startActivity(intent_to_phone);
                break;
        }
    }
    public static String PHOTO_IMAGE_FILE_NAME = "fileImg.jbg";
    public static int CAMERA_REQUEST_CODE = 100;
    private void toCamera() {
        // new File(Environment.getExternalStorageDirectory(),PHOTO_IMAGE_FILE_NAME);
        File file = null;

        try {
            file = new File(Environment.getExternalStorageDirectory(),PHOTO_IMAGE_FILE_NAME);
              file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Uri uri = FileProvider.getUriForFile(getContext(),"com.example.cameraalbumtest.fileprovider",file);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        startActivityForResult(intent,CAMERA_REQUEST_CODE);
        changeDialog.dismiss();
    }

    private void toPhoto() {

    }

    private void editPic() {
        changeDialog =  new ChangeDialog(getContext());
        changeDialog.show();
        // 设置三个按钮的点击
        btn_camera = changeDialog.findViewById(R.id.btn_camera);
        btn_photo = changeDialog.findViewById(R.id.btn_photo);
        btn_release = changeDialog.findViewById(R.id.btn_release);
        btn_photo.setOnClickListener(this);
        btn_camera.setOnClickListener(this);
        btn_release.setOnClickListener(this);

    }

    private void editFile() {
        // 2 判断不为空获取用户输入的值
        String name = et_name.getText().toString().trim();
        String age = et_age.getText().toString().trim();
        String desc = et_desc.getText().toString().trim();
        String sex = et_sex.getText().toString().trim();
        if(!TextUtils.isEmpty(name)&!TextUtils.isEmpty(age)&!TextUtils.isEmpty(sex)){
            // 3 向后台更新
            MyUser newUser = new MyUser();
            newUser.setUsername(name);
            newUser.setAge(Integer.parseInt(age));
            if(sex.equals("男")){
                newUser.setSex(true);
            }else {
                newUser.setSex(false);
            }
            if(!TextUtils.isEmpty(desc)){
                newUser.setDesc(desc);
            }else {
                newUser.setDesc("这个人是个懒逼，什么都没有");
            }
            BmobUser bmobUser = BmobUser.getCurrentUser();
            newUser.update(bmobUser.getObjectId(),new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if(e==null){
                        Toast.makeText(getContext(),"修改成功",Toast.LENGTH_SHORT).show();
                        // 设为不可见
                        setEnable(false);
                        btn_confirm_modify.setVisibility(View.GONE);
                    }else{
                        Toast.makeText(getContext(),"修改失败",Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }else {
            Toast.makeText(getContext(),"输入不能为空",Toast.LENGTH_SHORT).show();
        }


    }

    private void logout() {
        BmobUser.logOut();   //清除缓存用户对象
        BmobUser currentUser = BmobUser.getCurrentUser(); // 现在的currentUser是null了
        if(currentUser==null){
            Toast.makeText(getContext(),"登出成功",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), LoginActivity.class));
            getActivity().finish();
        }
    }

    private void setEnable(boolean is) {
        et_name.setEnabled(is);
        et_age.setEnabled(is);
        et_desc.setEnabled(is);
        et_sex.setEnabled(is);
    }
}
