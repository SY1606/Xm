package com.example.tt_xm.di.contract;

public interface WalletContract {
    public interface WalletView{
        public void showData(String message);
    }

    public interface WalletPresenter<WalletView>{
        public void attachView(WalletView walletView);
        public void detachView(WalletView walletView);

        public void requestData(int page, int count);
    }

    public interface WalletModel{
        public void containData(int page, int count, CallBack callBack);

        public interface CallBack{
            public void OnCallBack(String message);
        }
    }
}
