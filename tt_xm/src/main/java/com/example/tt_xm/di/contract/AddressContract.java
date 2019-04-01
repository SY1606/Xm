package com.example.tt_xm.di.contract;


public interface AddressContract {
    public interface AddressView{

        public void showDatass(String message);
    }

    public interface AddressPresenter<AddressView>{

        public void attacheView(AddressView addressView);
        public void deteachView(AddressView addressView);
        public void requestData();
    }

    public interface AddressModel{
        public void containData(CallBack callBack);
        public interface CallBack{
            public void CallBackss(String message);
        }
    }

}
