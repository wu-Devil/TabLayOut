package com.example.administrator.task;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.administrator.util.GetImgTools;
import com.example.administrator.util.HttpConnUtil;

public class GetPicTask extends AsyncTask<String,Void,Bitmap> {

    private ImageView pic;

    public GetPicTask(ImageView pic) {
        this.pic = pic;
    }

    @Override
    protected Bitmap doInBackground(String... params) {

        String path = params[0];
        Bitmap bt = HttpConnUtil.getPic(path);
        GetImgTools.map.put(path,bt);
        return bt;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(null != bitmap){
            pic.setImageBitmap(bitmap);
        }
    }
}
