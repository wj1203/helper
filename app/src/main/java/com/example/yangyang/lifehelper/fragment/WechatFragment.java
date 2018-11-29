package com.example.yangyang.lifehelper.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.yangyang.lifehelper.R;
import com.example.yangyang.lifehelper.adapter.PassageAdapter;
import com.example.yangyang.lifehelper.entity.Passage;
import com.example.yangyang.lifehelper.ui.WebActivity;
import com.example.yangyang.lifehelper.util.L;
import com.example.yangyang.lifehelper.util.StaticClass;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class WechatFragment extends Fragment {
    private ListView listView;
    private ArrayList<Passage> mList;
    private ArrayList<String> mList_title = new ArrayList<>();
    private ArrayList<String> mList_uri = new ArrayList<>();
    private PassageAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wechat, container, false);
        initView(view);
        return view;

    }

    private void initView(View view) {
        listView = view.findViewById(R.id.list_view);
        mList = new ArrayList<>();
        // 请求数据
        String url = "http://v.juhe.cn/weixin/query?key="+ StaticClass.PASSAGE_KEY+"&ps=40";
        RxVolley.get(url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                L.d(t);
                // 解析json
                parseJSON(t);
            }
        });
        // listView的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("title",mList_title.get(position));
                intent.putExtra("url",mList_uri.get(position));
                startActivity(intent);
            }
        });
    }

    private void parseJSON(String s) {
        try {
            //转为json
            JSONObject object = new JSONObject(s);
            //获得result
            JSONObject result = object.getJSONObject("result");
            //获得list数组
            JSONArray array = result.getJSONArray("list");
            for(int i = 0;i<array.length();i++){
                JSONObject obj = (JSONObject) array.get(i);
                Passage passage = new Passage();
                String title = obj.getString("title");
                String url = obj.getString("url");
                passage.setTitle(title);
                passage.setSource(obj.getString("source"));
                passage.setImgUrl("1111");
                mList.add(passage);
                mList_title.add(title);
                mList_uri.add(url);
            }
            adapter = new PassageAdapter(getContext(),mList);
            listView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
