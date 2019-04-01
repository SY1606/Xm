package com.example.tt_xm.di.presenter;

import com.example.tt_xm.di.contract.CorderContract;
import com.example.tt_xm.di.model.CorderModel;

import java.lang.ref.SoftReference;
import java.util.HashMap;

public class CorderPresenter implements CorderContract.CorderPresenter<CorderContract.CorderView> {

    CorderContract.CorderModel corderModel;
    CorderContract.CorderView corderView;
    private SoftReference<CorderContract.CorderView> softReference;

    @Override
    public void attachView(CorderContract.CorderView corderView) {
        this.corderView = corderView;
        softReference = new SoftReference<>(corderView);
        corderModel = new CorderModel();
    }

    @Override
    public void detachView(CorderContract.CorderView corderView) {
        softReference.clear();
    }


    @Override
    public void requestData(HashMap<String, String> map) {
        corderModel.containData(map, new CorderContract.CorderModel.CallBack() {
            @Override
            public void onCallBack(String message) {
                corderView.showData(message);
            }
        });
    }
}
