package com.example.tt_xm.di.model;

import com.example.tt_xm.data.Constant;
import com.example.tt_xm.di.contract.LoginContract;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginModel implements LoginContract.LoginModel {

    @Override
    public void containLoginData(String name, String password, final CallBack callBack) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        FormBody formBody = new FormBody.Builder().build();
        Request request = new Request.Builder()
                .method("POST",formBody)
                .url(Constant.Login+"?phone="+name+"&pwd="+password)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String responseData = e.getMessage();
                callBack.responseData(responseData);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                callBack.responseData(responseData);
            }
        });
    }
}
