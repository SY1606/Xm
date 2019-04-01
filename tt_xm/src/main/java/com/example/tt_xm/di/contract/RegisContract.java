package com.example.tt_xm.di.contract;

public interface RegisContract {

    public interface RegisView{
        public void regisData(String responseData);
    }

    public interface RegisPresenter<RegisView>{

        public void attachView(RegisView regisView);
        public void detachView(RegisView regisView);

        public void requestRegisData(String mobile,String password);
    }

    public interface RegisModel {

        public void containRegisData(String mobile, String password, DataCall dataCall);

        public interface DataCall {
            public void responseData(String reponseData);
        }
    }
}
