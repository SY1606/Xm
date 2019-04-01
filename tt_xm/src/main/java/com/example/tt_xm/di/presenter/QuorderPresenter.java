package com.example.tt_xm.di.presenter;

import com.example.tt_xm.di.contract.QuorderContract;
import com.example.tt_xm.di.model.QuorderModel;

import java.lang.ref.SoftReference;

public class QuorderPresenter implements QuorderContract.QuorderPresenter<QuorderContract.QuorderView> {

    QuorderContract.QuorderModel quorderModel;
    QuorderContract.QuorderView quorderView;
    private SoftReference<QuorderContract.QuorderView> softReference;

    @Override
    public void attahView(QuorderContract.QuorderView quorderView) {
        this.quorderView = quorderView;
        softReference = new SoftReference<>(quorderView);
        quorderModel  = new QuorderModel();
    }

    @Override
    public void deachView(QuorderContract.QuorderView quorderView) {
        softReference.clear();
    }

    @Override
    public void requestData() {
        quorderModel.containData(new QuorderContract.QuorderModel.CallBack() {
            @Override
            public void onCallBack(String requestData) {
                quorderView.showData(requestData);
            }
        });
    }
}
