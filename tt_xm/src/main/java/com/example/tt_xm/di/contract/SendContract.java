package com.example.tt_xm.di.contract;

import okhttp3.MultipartBody;

public interface SendContract {
    public interface SendView{
        public void showData(String requestData);
    }


    public interface SendPresenter<SendView>{
        public void attahView(SendView sendView);

        public void deachView(SendView sendView);

        public void requestData(int commodityId, String content, MultipartBody.Part image);
    }


    public interface SendModel{
        public void containData(int commodityId, String content, MultipartBody.Part image, CallBack callBack);

        public interface CallBack{
            public void onCallBack(String requestData);
        }
    }
}
