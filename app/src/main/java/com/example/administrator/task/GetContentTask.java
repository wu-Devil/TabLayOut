package com.example.administrator.task;

import android.os.AsyncTask;

import com.example.administrator.adapter.TouTiaoAdapter;
import com.example.administrator.util.HttpConnUtil;
import com.example.administrator.vo.Content;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/28.
 */
public class GetContentTask extends AsyncTask<String ,Void ,List<Content>>{

    private List<Content> list;
    private TouTiaoAdapter tta;

    public GetContentTask(TouTiaoAdapter tta, List<Content> list) {
        this.tta = tta;
        this.list = list;
    }

    @Override
    protected List<Content> doInBackground(String... params) {

        String path = params[0];
        String json = HttpConnUtil.DownJson(path);
        List<Content> list = new ArrayList<>();
        list = HttpConnUtil.parserJson(json);

        return list;
    }

    @Override
    protected void onPostExecute(List<Content> contents) {
        if(null != contents){
            list.addAll(contents);
            tta.notifyDataSetChanged();
        }
    }
}
