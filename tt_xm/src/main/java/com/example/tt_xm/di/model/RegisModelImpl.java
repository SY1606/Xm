package com.example.tt_xm.di.model;

import com.example.tt_xm.data.Constant;
import com.example.tt_xm.di.contract.RegisContract;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegisModelImpl implements RegisContract.RegisModel {
    @Override
    public void containRegisData(String mobile, String password, DataCall dataCall) {
        requestRegisData(mobile,password,dataCall);
    }

    private void requestRegisData(String mobile, String password, final DataCall dataCall) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        FormBody formBody  = new FormBody.Builder().build();

        Request request = new Request.Builder()
                .method("POST",formBody)
                .url(Constant.Zhuce+"?phone="+mobile+"&pwd="+password)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String reponseData = e.getMessage();
                dataCall.responseData(reponseData);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                dataCall.responseData(responseData);
            }
        });
    }
}
