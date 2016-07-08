package com.example.administrator.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.administrator.vo.Content;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/28.
 */
public class HttpConnUtil {

    //下载json字符串
    public static String DownJson(String path) {

        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        HttpURLConnection conn = null;

        try {
            URL url = new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            int code = conn.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                InputStream is = conn.getInputStream();
                br = new BufferedReader(new InputStreamReader(is));
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                return sb.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != br) {
                    br.close();
                }
                if (null != conn) {
                    conn.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    //解析json字符串
    public static List<Content> parserJson(String json){

        List<Content> list = new ArrayList<Content>();

        try {
            JSONObject obj = new JSONObject(json);

            JSONArray array = obj.getJSONArray("data");
            for (int i = 0; i < array.length(); i++) {
                JSONObject js = array.getJSONObject(i);
                Content content = new Content();
                content.setTitle(js.getString("title"));
                content.setSource(js.getString("source"));
                content.setWap_thumb(js.getString("wap_thumb"));
                content.setCreate_time(js.getString("create_time"));
                content.setNickname(js.getString("nickname"));
                content.setDescription(js.getString("description"));
                list.add(content);
            }
            return list;

        } catch (JSONException e) {
            e.printStackTrace();
        }
            return null;
    }

    //下载图片的方法
    public static Bitmap getPic(String path){
        Bitmap bt = null;
        InputStream is = null;
        HttpURLConnection conn = null;

        try {
            URL url = new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            int code = conn.getResponseCode();
            if(code == HttpURLConnection.HTTP_OK){
                is = conn.getInputStream();
                bt = BitmapFactory.decodeStream(is);
            }
            return bt;

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                if(null != is){
                    is.close();
                }
                if(null != conn){
                    conn.disconnect();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;

    }
}
