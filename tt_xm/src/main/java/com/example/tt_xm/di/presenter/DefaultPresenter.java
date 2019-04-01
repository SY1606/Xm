package com.example.tt_xm.di.presenter;

import com.example.tt_xm.di.contract.DefaultContract;
import com.example.tt_xm.di.model.DefaultModel;

import java.lang.ref.SoftReference;

public class DefaultPresenter implements DefaultContract.DefaultPresenter<DefaultContract.DefaultView> {

    DefaultContract.DefalutModel defalutModel;
    DefaultContract.DefaultView defaultView;
    private SoftReference<DefaultContract.DefaultView> softReference;

    @Override
    public void attachView(DefaultContract.DefaultView defaultView) {
        this.defaultView = defaultView;
        softReference = new SoftReference<>(defaultView);
        defalutModel = new DefaultModel();
    }

    @Override
    public void detachView(DefaultContract.DefaultView defaultView) {
        softReference.clear();
    }

    @Override
    public void requestData(int id) {
        defalutModel.containData(id, new DefaultContract.DefalutModel.CallBack() {
            @Override
            public void OnCallback(String requestData) {
                defaultView.showData(requestData);
            }
        });
    }

}
