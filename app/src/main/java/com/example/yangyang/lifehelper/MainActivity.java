package com.example.yangyang.lifehelper;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import com.example.yangyang.lifehelper.fragment.ButlerFragment;
import com.example.yangyang.lifehelper.fragment.GirlFragment;
import com.example.yangyang.lifehelper.fragment.UserFragment;
import com.example.yangyang.lifehelper.fragment.WechatFragment;
import com.example.yangyang.lifehelper.ui.BaseAcitivity;
import com.example.yangyang.lifehelper.ui.SettingActivity;
import com.example.yangyang.lifehelper.util.L;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.ArrayList;

public class MainActivity extends BaseAcitivity implements View.OnClickListener {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    // tablayout的四个标题
    private ArrayList<String> mTitle;
    // viewpager的四个fragment
    private ArrayList<Fragment> mFragment;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        L.d("MainActivity:onCeate");
    }
    //  初始化数据
    private void initData() {
        mTitle = new ArrayList<>();
        mTitle.add("聊天管家");
        mTitle.add("微信文章");
        mTitle.add("美女图片");
        mTitle.add("个人中心");
        mFragment = new ArrayList<>();
        mFragment.add(new ButlerFragment());
        mFragment.add(new WechatFragment());
        mFragment.add(new GirlFragment());
        mFragment.add(new UserFragment());
        // crash测试
//      CrashReport.testJavaCrash();
    }
    // 初始化view
    private void initView() {
        fab = findViewById(R.id.fab);
        mTabLayout = findViewById(R.id.mTabLayout);
        mViewPager = findViewById(R.id.mViewPager);
        // viewpager的滑动监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(0==position){
                    fab.setVisibility(View.GONE);
                }else {
                    fab.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        // 设置viewpager适配器(以为viewpager放的fragment所以用FragmentPagerAdapter传入manager参数)
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            // 返回选中的fragment
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }
            // 个数
            @Override
            public int getCount() {
                return mFragment.size();
            }
            // 设置标题
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }
        });

        // tablayout绑定viewpager
        mTabLayout.setupWithViewPager(mViewPager);
        // fab的事件监听
        fab.setOnClickListener(this);
        // fab的初始化
        fab.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab :
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                break;
        }
    }
}





















