package com.example.yangyang.lifehelper.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.yangyang.lifehelper.MainActivity;
import com.example.yangyang.lifehelper.R;
import com.example.yangyang.lifehelper.util.L;

import java.util.ArrayList;

import static com.example.yangyang.lifehelper.R.drawable.point_on;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager mViewPager;
    private ArrayList<View> mList ;
    private View view1,view2,view3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
    }

    private void initView() {
        mViewPager = findViewById(R.id.mViewPager);
        final ImageView point_one = findViewById(R.id.point_one);
        final ImageView point_two = findViewById(R.id.point_two);
        final ImageView point_three = findViewById(R.id.point_three);
        point_one.setBackgroundResource(R.drawable.point_on);
        point_two.setBackgroundResource(R.drawable.point_off);
        point_three.setBackgroundResource(R.drawable.point_off);
        LayoutInflater inflater = LayoutInflater.from(this);
        view1 = inflater.inflate(R.layout.page_item_one,null);
        view2 = inflater.inflate(R.layout.page_item_two,null);
        view3 = inflater.inflate(R.layout.page_item_three,null);
        view3.findViewById(R.id.but).setOnClickListener(this);
        mList = new ArrayList<>();
        mList.add(view1);
        mList.add(view2);
        mList.add(view3);
        L.d("三个view加载成功");

        // viewPager的适配操作
        PagerAdapter adapter = new PagerAdapter() {
            @Override
            public int getCount() {
                L.d("getCount");
                return mList.size();
            }

            @Override
            public boolean isViewFromObject( View view,  Object object) {
                L.d("isViewFromObject");
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem( ViewGroup container, int position) {
                container.addView(mList.get(position));
                L.d("instantiateItem");
                return  mList.get(position);
            }

            @Override
            public void destroyItem( ViewGroup container, int position,  Object object) {
                L.d("destroyItem");
                container.removeView(mList.get(position));
            }
        };
        L.d("适配器准备完成");
        mViewPager.setAdapter(adapter);
        L.d("加载适配器");

        //  监听viewPager的滑动
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        point_one.setBackgroundResource(R.drawable.point_on);
                        point_two.setBackgroundResource(R.drawable.point_off);
                        point_three.setBackgroundResource(R.drawable.point_off);
                        break;
                    case 1:
                        point_one.setBackgroundResource(R.drawable.point_off);
                        point_two.setBackgroundResource(R.drawable.point_on);
                        point_three.setBackgroundResource(R.drawable.point_off);
                        break;
                    case 2:
                        point_one.setBackgroundResource(R.drawable.point_off);
                        point_two.setBackgroundResource(R.drawable.point_off);
                        point_three.setBackgroundResource(R.drawable.point_on);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.but:
            startActivity(new Intent(this,LoginActivity.class));
            finish();
                break;
        }
    }
}
