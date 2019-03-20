package com.example.tt_xm.di.model;

import com.example.tt_xm.data.Constant;
import com.example.tt_xm.data.net.OkUtil;
import com.example.tt_xm.di.contract.GoodsContract;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GoodsModel implements GoodsContract.GoodModel {
    @Override
    public void containDatas(final CallBack callBack) {
        OkUtil.getInstance().get(Constant.Goods, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.CallBacks(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.CallBacks(response.body().string());
            }
        });
    }
}
