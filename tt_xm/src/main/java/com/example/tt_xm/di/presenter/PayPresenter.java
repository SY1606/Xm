package com.example.tt_xm.di.presenter;

import com.example.tt_xm.di.contract.PayContract;
import com.example.tt_xm.di.model.PayModel;

import java.lang.ref.SoftReference;

public class PayPresenter implements PayContract.PayPresenter<PayContract.PayView> {

    PayContract.PayModel payModel;
    PayContract.PayView payView;
    private SoftReference<PayContract.PayView> softReference;

    @Override
    public void attahView(PayContract.PayView payView) {
        this.payView = payView;
        softReference = new SoftReference<>(payView);
        payModel = new PayModel();
    }

    @Override
    public void deachView(PayContract.PayView payView) {
        softReference.clear();
    }

    @Override
    public void requestData(String orderId, int payType) {
        payModel.containData(orderId, payType, new PayContract.PayModel.CallBack() {
            @Override
            public void onCallBack(String requestData) {
                payView.showData(requestData);
            }
        });
    }
}
