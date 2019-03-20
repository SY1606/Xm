package com.example.tt_xm.di.model;

import com.example.tt_xm.data.ApiService;
import com.example.tt_xm.data.Constant;
import com.example.tt_xm.di.contract.BannerContract;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BannerModel implements BannerContract.BannerModel {
    @Override
    public void containData(final CallBack callBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.Banner)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<ResponseBody> getbannerdata = apiService.getbannerdata();
        getbannerdata.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String s = response.body().string();
                    callBack.onCallBack(s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        }) ;


    }
}
