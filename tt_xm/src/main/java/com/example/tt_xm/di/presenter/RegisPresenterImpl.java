package com.example.tt_xm.di.presenter;

import com.example.tt_xm.di.contract.RegisContract;
import com.example.tt_xm.di.model.RegisModelImpl;

import java.lang.ref.SoftReference;

public class RegisPresenterImpl implements RegisContract.RegisPresenter<RegisContract.RegisView> {

    RegisContract.RegisModel regisModel;
    RegisContract.RegisView regisView;
    private SoftReference<RegisContract.RegisView> softReference;


    @Override
    public void attachView(RegisContract.RegisView regisView) {
        this.regisView = regisView;
        softReference = new SoftReference<>(regisView);
        regisModel = new RegisModelImpl();
    }

    @Override
    public void detachView(RegisContract.RegisView regisView) {
        softReference.clear();
    }



    @Override
    public void requestRegisData(String mobile, String password) {
       regisModel.containRegisData(mobile, password, new RegisContract.RegisModel.DataCall() {
           @Override
           public void responseData(String reponseData) {
               regisView.regisData(reponseData);
           }
       });
    }
}
