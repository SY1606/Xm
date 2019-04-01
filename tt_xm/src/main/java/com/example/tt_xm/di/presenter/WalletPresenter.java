package com.example.tt_xm.di.presenter;

import com.example.tt_xm.di.contract.WalletContract;
import com.example.tt_xm.di.model.WalletModel;

import java.lang.ref.SoftReference;

public class WalletPresenter implements WalletContract.WalletPresenter<WalletContract.WalletView> {

    WalletContract.WalletModel walletModel;
    WalletContract.WalletView walletView;
    private SoftReference<WalletContract.WalletView> softReference;

    @Override
    public void attachView(WalletContract.WalletView walletView) {
        this.walletView = walletView;
        softReference = new SoftReference<>(walletView);
        walletModel = new WalletModel();
    }

    @Override
    public void detachView(WalletContract.WalletView walletView) {
        softReference.clear();
    }

    @Override
    public void requestData(int page, int count) {
        walletModel.containData(page, count, new WalletContract.WalletModel.CallBack() {
            @Override
            public void OnCallBack(String message) {
                walletView.showData(message);
            }
        });
    }
}
