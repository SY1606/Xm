package com.example.tt_xm.di.presenter;

import com.example.tt_xm.di.contract.ShouContract;
import com.example.tt_xm.di.contract.SyncContract;
import com.example.tt_xm.di.model.ShouModel;

import java.lang.ref.SoftReference;

public class ShouPresenter implements ShouContract.ShouPresenter<ShouContract.ShouView> {

   ShouContract.ShouView shouView;
   ShouContract.ShouModel shouModel;
    private SoftReference<ShouContract.ShouView> softReference;


    @Override
    public void attachView(ShouContract.ShouView shouView) {
        this.shouView = shouView;
        softReference = new SoftReference<>(shouView);
        shouModel = new ShouModel();
    }

    @Override
    public void detachView(ShouContract.ShouView shouView) {
        softReference.clear();
    }

    @Override
    public void requestData(String orderId) {
      shouModel.reponseData(orderId, new ShouContract.ShouModel.CallBack() {
          @Override
          public void onCallBack(String message) {
              shouView.showData(message);
          }
      });
    }
}
