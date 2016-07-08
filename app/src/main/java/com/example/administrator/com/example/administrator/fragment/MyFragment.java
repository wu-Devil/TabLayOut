package com.example.administrator.com.example.administrator.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;

import com.example.administrator.adapter.TouTiaoAdapter;
import com.example.administrator.task.GetContentTask;
import com.example.administrator.vo.Content;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/28.
 */
public class MyFragment extends ListFragment{


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        Bundle bundle = getArguments();

        String url = bundle.getString("url");
        List<Content> list = new ArrayList<>();

       TouTiaoAdapter tta = new TouTiaoAdapter(getActivity(),list);

        new GetContentTask(tta,list).execute(url);

        setListAdapter(tta);

    }
}
