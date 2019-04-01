package com.example.tt_xm.di.contract;

public interface PayContract {

    public interface PayView{
        public void showData(String requestData);
    }


    public interface PayPresenter<PayView>{
        public void attahView(PayView payView);

        public void deachView(PayView payView);

        public void requestData(String orderId, int payType);
    }


    public interface PayModel{
        public void containData(String orderId, int payType, CallBack callBack);

        public interface CallBack{
            public void onCallBack(String requestData);
        }
    }

}
