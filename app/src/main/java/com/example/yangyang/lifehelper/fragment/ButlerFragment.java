package com.example.yangyang.lifehelper.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.yangyang.lifehelper.R;
import com.example.yangyang.lifehelper.adapter.ChatAdapter;
import com.example.yangyang.lifehelper.entity.Chat;

import java.util.ArrayList;


public class ButlerFragment extends Fragment implements View.OnClickListener {
    private ListView listView;
    private Button btn_left;
    private Button btn_right;
    private ArrayList<Chat> mList = new ArrayList<>();
    private ChatAdapter adapter ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_butler, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        listView = view.findViewById(R.id.list_view);
        btn_left = view.findViewById(R.id.btn_left);
        btn_right = view.findViewById(R.id.btn_right);
        btn_right.setOnClickListener(this);
        btn_left.setOnClickListener(this);
        adapter = new ChatAdapter(getContext(),mList);
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_left :
                addLeft("123");
                break;
            case R.id.btn_right :
                addRight("456");
                break;
        }
    }

    private void addRight(String text) {
        Chat chat = new Chat();
        chat.setText(text);
        chat.setType(ChatAdapter.VALUE_RIGHT_TEXT);
        mList.add(chat);
        // 更新
        adapter.notifyDataSetChanged();
        // 滚动到底部
        listView.setSelection(listView.getBottom());
    }

    private void addLeft(String text) {
        Chat chat = new Chat();
        chat.setText(text);
        chat.setType(ChatAdapter.VALUE_LEFT_TEXT);
        mList.add(chat);
        // 更新
        adapter.notifyDataSetChanged();
        // 滚动到底部
        listView.setSelection(listView.getBottom());
    }
}
