package com.example.tt_xm.di.contract;

import java.util.HashMap;

public interface CorderContract {

    public interface CorderView{
        public void showData(String message);
    }

    public interface CorderPresenter<CorderView>{
        public void attachView(CorderView corderView);
        public void detachView(CorderView corderView);

        public void requestData(HashMap<String, String> map);
    }

    public interface CorderModel{
        public void containData(HashMap<String, String> map, CallBack callBack);

        public interface CallBack{
            public void onCallBack(String message);
        }
    }
}
