package com.example.tt_xm.di.contract;

public interface DefaultContract {

    public interface DefaultView{
        public void showData(String requestData);
    }

    public interface DefaultPresenter<DefaultView>{

        public void attachView(DefaultView defaultView);
        public void detachView(DefaultView defaultView);

        public void requestData(int id);
    }

    public interface DefalutModel{
        public void containData(int id,CallBack callBack);
        public interface CallBack{
            public void OnCallback(String requestData);
        }
    }
}
