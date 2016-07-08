package com.example.administrator.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.tablayout.R;
import com.example.administrator.task.GetPicTask;
import com.example.administrator.util.GetImgTools;
import com.example.administrator.vo.Content;

import java.util.List;

/**
 * Created by Administrator on 2016/6/28.
 */
public class TouTiaoAdapter extends BaseAdapter {

    private List<Content> list;
    private Context mContext;

    public TouTiaoAdapter(Context mContext, List<Content> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh;
        if(convertView == null){
            convertView = View.inflate(mContext,R.layout.tou_tiao_layout,null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        Content content = list.get(position);

        String title = content.getTitle();
        String source = content.getSource();
        String description = content.getDescription();
        String create_time = content.getCreate_time();
        String nickname = content.getNickname();
        String wap_thumb = content.getWap_thumb();

        StringBuilder sb = new StringBuilder();
        sb.append(source+"\t");
        sb.append(nickname+"\t");
        sb.append(create_time+"\t");

        vh.title.setText(title);
        vh.description.setText(description);
        vh.getall.setText(sb);

        if(GetImgTools.map.containsKey("wap_thumb")){
            vh.pic.setImageBitmap(GetImgTools.map.get("wap_thumb"));
        }else{
            new GetPicTask(vh.pic).execute(wap_thumb);
        }
        return convertView;
    }
    class ViewHolder{
        private TextView title,description,getall;
        private ImageView pic;

        public ViewHolder(View view){
            this.title = (TextView) view.findViewById(R.id.title);
            this.description = (TextView) view.findViewById(R.id.description);
            this.getall = (TextView) view.findViewById(R.id.getall);
            this.pic = (ImageView) view.findViewById(R.id.pic);
        }

    }
}
