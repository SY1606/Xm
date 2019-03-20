package com.example.tt_xm.data.net;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkUtil {

    public static OkUtil okUtil;
    private final OkHttpClient okHttpClient;



    private OkUtil(){
        okHttpClient = new OkHttpClient.Builder().build();
    }

    public static OkUtil getInstance(){
        if (okUtil==null){
            synchronized (OkUtil.class){
                if (okUtil==null){
                    okUtil = new OkUtil();
                }
            }
        }
        return okUtil;
    }

    public void get(String urlString, Callback callback){
        Request request = new Request.Builder().url(urlString).build();
        okHttpClient.newCall(request).enqueue(callback);
    }


    public void cancelAllTask(){
        if (okHttpClient!=null){
            okHttpClient.dispatcher().cancelAll();
        }
    }
}
