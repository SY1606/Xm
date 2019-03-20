package com.example.tt_xm.di.model;

import com.example.tt_xm.data.Constant;
import com.example.tt_xm.data.net.OkUtil;
import com.example.tt_xm.di.contract.DetailContract;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DetailsModel implements DetailContract.DetailsModel {
    @Override
    public void containData(int cid, final CallBack callBack) {
        OkUtil.getInstance().get(Constant.Xq + cid, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onCallBack(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.onCallBack(response.body().string());
            }
        });
    }
}
