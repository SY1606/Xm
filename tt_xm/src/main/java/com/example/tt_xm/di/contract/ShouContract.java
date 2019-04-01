package com.example.tt_xm.di.contract;

public interface ShouContract {

    public interface ShouView{
        public void showData(String message);
    }

    public interface ShouPresenter<ShouView>{
        public void attachView(ShouView shouView);

        public void detachView(ShouView shouView);

        public void requestData(String orderId);
    }

    public interface ShouModel{
        public void reponseData(String orderId,CallBack callBack);

        public interface CallBack{
            public void onCallBack(String message);
        }
    }
}
