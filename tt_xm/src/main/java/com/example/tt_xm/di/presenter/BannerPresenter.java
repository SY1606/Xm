package com.example.tt_xm.di.presenter;

import com.example.tt_xm.di.contract.BannerContract;
import com.example.tt_xm.di.model.BannerModel;

import java.lang.ref.SoftReference;

public class BannerPresenter implements BannerContract.BannerPresenter<BannerContract.BannerView> {

    BannerContract.BannerView bannerView;
    BannerContract.BannerModel bannerModel;
    private SoftReference<BannerContract.BannerView> softReference;

    @Override
    public void attachView(BannerContract.BannerView bannerView) {
        this.bannerView = bannerView;
        softReference = new SoftReference<>(bannerView);
        bannerModel = new BannerModel();
    }

    @Override
    public void deteachView(BannerContract.BannerView bannerView) {
        softReference.clear();
    }

    @Override
    public void requestData() {
        bannerModel.containData(new BannerContract.BannerModel.CallBack() {
            @Override
            public void onCallBack(String responseData) {
                bannerView.showData(responseData);
            }
        });
    }
}
