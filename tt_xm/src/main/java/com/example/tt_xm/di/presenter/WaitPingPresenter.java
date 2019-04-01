package com.example.tt_xm.di.presenter;

import com.example.tt_xm.di.contract.WaitPingContract;
import com.example.tt_xm.di.model.WaitPingModel;

import java.lang.ref.SoftReference;

public class WaitPingPresenter implements WaitPingContract.WaitPingPresenter<WaitPingContract.WaitPingView> {

    WaitPingContract.WaitPingView waitPingView;
    WaitPingContract.WaitPingModel waitPingModel;
    private SoftReference<WaitPingContract.WaitPingView> softReference;


    @Override
    public void attahView(WaitPingContract.WaitPingView waitPingView) {
        this.waitPingView = waitPingView;
        softReference = new SoftReference<>(waitPingView);
        waitPingModel = new WaitPingModel();
    }

    @Override
    public void deachView(WaitPingContract.WaitPingView waitPingView) {
        softReference.clear();
    }

    @Override
    public void requestData() {
        waitPingModel.containData(new WaitPingContract.WaitPingModel.CallBack() {
            @Override
            public void onCallBack(String requestData) {
                waitPingView.showData(requestData);
            }
        });
    }
}
