package com.example.tt_xm.di.presenter;

import com.example.tt_xm.data.bean.Ufo;
import com.example.tt_xm.di.contract.UfoContract;
import com.example.tt_xm.di.model.UfoModel;

import java.lang.ref.SoftReference;

public class UfoPresenter implements UfoContract.UfoPresenter<UfoContract.UfoView> {

    UfoContract.UfoModel ufoModel;
    UfoContract.UfoView ufoView;
    private SoftReference<UfoContract.UfoView> softReference;

    @Override
    public void attachView(UfoContract.UfoView ufoView) {
        this.ufoView = ufoView;
        softReference = new SoftReference<>(ufoView);
        ufoModel = new UfoModel();
    }

    @Override
    public void detachView(UfoContract.UfoView ufoView) {
        softReference.clear();
    }

    @Override
    public void requestData(String sessionId,int userId,int page, int count) {
        ufoModel.containData(sessionId,userId,page, count, new UfoContract.UfoModel.CallBack() {
            @Override
            public void OnCallBack(Ufo ufo) {
                ufoView.showData(ufo);
            }
        });
    }
}
