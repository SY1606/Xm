package com.example.tt_xm.di.presenter;

import com.example.tt_xm.di.contract.WaitPayContract;
import com.example.tt_xm.di.model.WaitPayModel;

import java.lang.ref.SoftReference;

public class WaitPayPresenter implements WaitPayContract.WaitPayPresenter<WaitPayContract.WaitPayView> {

    WaitPayContract.WaitPayModel waitPayModel;
    WaitPayContract.WaitPayView waitPayView;
    private SoftReference<WaitPayContract.WaitPayView> softReference;

    @Override
    public void attahView(WaitPayContract.WaitPayView waitPayView) {
        this.waitPayView = waitPayView;
        softReference = new SoftReference<>(waitPayView);
        waitPayModel = new WaitPayModel();
    }

    @Override
    public void deachView(WaitPayContract.WaitPayView waitPayView) {
        softReference.clear();
    }

    @Override
    public void requestData() {
        waitPayModel.containData(new WaitPayContract.WaitPayModel.CallBack() {
            @Override
            public void onCallBack(String requestData) {
                waitPayView.showData(requestData);
            }
        });
    }
}
