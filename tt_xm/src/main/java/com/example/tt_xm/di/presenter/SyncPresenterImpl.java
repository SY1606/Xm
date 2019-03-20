package com.example.tt_xm.di.presenter;

import com.example.tt_xm.data.Constant;
import com.example.tt_xm.data.bean.Sync;
import com.example.tt_xm.di.contract.SyncContract;
import com.example.tt_xm.di.model.SyncModelImpl;

import java.lang.ref.SoftReference;

public class SyncPresenterImpl implements SyncContract.SyncPresenter<SyncContract.SyncView> {

    SyncContract.SyncView syncView;
    SyncContract.SyncModel syncModel;
    private SoftReference<SyncContract.SyncView> softReference;


    @Override
    public void attachView(SyncContract.SyncView syncView) {
        this.syncView = syncView;
        softReference = new SoftReference<>(syncView);
        syncModel = new SyncModelImpl();
    }

    @Override
    public void detachView(SyncContract.SyncView syncView) {
        softReference.clear();
    }

    @Override
    public void requestData(int commodityId1, int count) {
        syncModel.containLoginData(commodityId1, count, new SyncContract.SyncModel.CallBack() {
            @Override
            public void responseData(String message) {
                syncView.showDatas(message);
            }
        });
    }
}
