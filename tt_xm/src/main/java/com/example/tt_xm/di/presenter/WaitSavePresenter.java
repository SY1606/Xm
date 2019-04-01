package com.example.tt_xm.di.presenter;

import com.example.tt_xm.di.contract.WaitSaveContract;
import com.example.tt_xm.di.model.WaitSaveModel;

import java.lang.ref.SoftReference;

public class WaitSavePresenter implements WaitSaveContract.WaitSavePresenter<WaitSaveContract.WaitSaveView> {

    WaitSaveContract.WaitSaveView waitSaveView;
    WaitSaveContract.WaitSaveModel waitSaveModel;
    private SoftReference<WaitSaveContract.WaitSaveView> softReference;

    @Override
    public void attahView(WaitSaveContract.WaitSaveView waitSaveView) {
        this.waitSaveView = waitSaveView;
        softReference = new SoftReference<>(waitSaveView);
        waitSaveModel = new WaitSaveModel();
    }

    @Override
    public void deachView(WaitSaveContract.WaitSaveView waitSaveView) {
        softReference.clear();
    }

    @Override
    public void requestData() {
        waitSaveModel.containData(new WaitSaveContract.WaitSaveModel.CallBack() {
            @Override
            public void onCallBack(String requestData) {
                waitSaveView.showData(requestData);
            }
        });
    }
}
