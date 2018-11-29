package com.example.yangyang.lifehelper.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.yangyang.lifehelper.R;
import com.example.yangyang.lifehelper.adapter.GirlAdapter;
import com.example.yangyang.lifehelper.entity.Girl;
import com.example.yangyang.lifehelper.util.L;
import com.example.yangyang.lifehelper.util.PicassoUtils;
import com.example.yangyang.lifehelper.util.StaticClass;
import com.example.yangyang.lifehelper.view.CustomDialog;
import com.example.yangyang.lifehelper.view.GirlDialog;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class GirlFragment extends Fragment {
    private GridView gridView ;
    private ArrayList<Girl> mList = new ArrayList<>();
    private GirlAdapter adapter ;
    private GirlDialog dialog ;
    private ImageView iv_dialog;
    private String url ;
    private PhotoViewAttacher attacher;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_girl, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        gridView = view.findViewById(R.id.grid_view);
        dialog = new GirlDialog(getContext());
        // 请求数据
        RxVolley.get(StaticClass.GIRL_URL, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                L.d(t);
                // 解析
                parseJSON(t);
            }
        });
        // item 点击事件
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.show();
                iv_dialog = dialog.findViewById(R.id.iv_girl_dialog);
                url = mList.get(position).getUrl();
                L.d("url:"+url);
                // 加载大图
                PicassoUtils.loadImageSize(getContext(),url,iv_dialog,900,900);
                // 缩放功能
                attacher = new PhotoViewAttacher(iv_dialog);
                attacher.update();
            }
        });
    }

    private void parseJSON(String t) {
        try {
            JSONObject object = new JSONObject(t);
            JSONArray array = object.getJSONArray("results");
            for(int i = 0;i<array.length();i++){
                JSONObject obj = (JSONObject) array.get(i);
                Girl girl = new Girl();
                girl.setUrl(obj.getString("url"));
                mList.add(girl);
            }
            adapter = new GirlAdapter(getContext(),mList);
            gridView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
