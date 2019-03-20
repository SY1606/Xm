package com.example.tt_xm.di.presenter;

import com.example.tt_xm.di.contract.ShopCarContract;
import com.example.tt_xm.di.model.ShopCarModel;

import java.lang.ref.SoftReference;

public class ShopCarPresenterImpl implements ShopCarContract.ShopCarPresenter<ShopCarContract.ShopCarView> {

    ShopCarContract.ShopCarView shopCarView;
    ShopCarContract.ShopCarModel shopCarModel;
    private SoftReference<ShopCarContract.ShopCarView> softReference;

    @Override
    public void attachView(ShopCarContract.ShopCarView shopCarView) {
        this.shopCarView = shopCarView;
        softReference = new SoftReference<>(shopCarView);
        shopCarModel = new ShopCarModel();
    }

    @Override
    public void detachView(ShopCarContract.ShopCarView shopCarView) {
        softReference.clear();
    }

    @Override
    public void requestData() {
        shopCarModel.containData(new ShopCarContract.CallBack() {
            @Override
            public void onCallBack(String message) {
                shopCarView.showData(message);
            }
        });
    }
}
