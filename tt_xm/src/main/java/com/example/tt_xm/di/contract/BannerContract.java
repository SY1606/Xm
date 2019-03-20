package com.example.tt_xm.di.contract;

public interface BannerContract {
    public interface BannerView{
        public void showData(String responseData);

    }

    public interface BannerPresenter<BannerView>{
        public void attachView(BannerView bannerView);
        public void deteachView(BannerView bannerView);

        public void requestData();
    }

    public interface BannerModel{
        public void containData(CallBack callBack);
        public interface CallBack{
            public void onCallBack(String responseData);
        }
    }
}
