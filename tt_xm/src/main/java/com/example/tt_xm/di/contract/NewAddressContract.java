package com.example.tt_xm.di.contract;

public interface NewAddressContract {
    public interface NewAddressView{
        public void showData(String message);
    }

    public interface NewAddressPresenter<NewAddressView>{
        public void attachView(NewAddressView newAddressView);
        public void detachView(NewAddressView newAddressView);

        public void requestData(String name, String phone, String addresss, String youzheng);
    }

    public interface NewAddressModel{
        public void containData(String name, String phone, String addresss, String youzheng, CallBack callBack);

        public interface CallBack{
            public void OnCallBack(String message);
        }
    }
}
