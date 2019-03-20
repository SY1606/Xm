package com.example.tt_xm.di.contract;

public interface ShopCarContract {
    public interface ShopCarView{
        public void showData(String message);
    }

    public interface ShopCarPresenter<ShopCarView>{
        public void attachView(ShopCarView shopCarView);
        public void detachView(ShopCarView shopCarView);

        public void requestData();
    }

    public interface ShopCarModel{
        public void containData(CallBack callBack);
    }
    public interface CallBack{
        public void onCallBack(String message);
    }

}
