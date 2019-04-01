package com.example.tt_xm.di.presenter;

import com.example.tt_xm.di.contract.SendContract;
import com.example.tt_xm.di.contract.ShopCarContract;
import com.example.tt_xm.di.model.SendModel;

import java.lang.ref.SoftReference;

import okhttp3.MultipartBody;

public class SendPresenter implements SendContract.SendPresenter<SendContract.SendView> {

    SendContract.SendView sendView;
    SendContract.SendModel sendModel;
    private SoftReference<SendContract.SendView> softReference;

    @Override
    public void attahView(SendContract.SendView sendView) {
        this.sendView = sendView;
        softReference = new SoftReference<>(sendView);
        sendModel = new SendModel();
    }

    @Override
    public void deachView(SendContract.SendView sendView) {
        softReference.clear();
    }

    @Override
    public void requestData(final int commodityId, final String content, final MultipartBody.Part image) {
        sendModel.containData(commodityId, content, image, new SendContract.SendModel.CallBack() {
            @Override
            public void onCallBack(String requestData) {
                sendView.showData(requestData);
            }
        });
    }
}
