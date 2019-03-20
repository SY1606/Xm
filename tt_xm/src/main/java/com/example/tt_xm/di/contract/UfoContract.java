package com.example.tt_xm.di.contract;

import com.example.tt_xm.data.bean.Ufo;

public interface UfoContract {

    public interface UfoView{
        public void showData(Ufo ufo);

    }

    public interface UfoPresenter<UfoView>{
        public void attachView(UfoView ufoView);
        public void detachView(UfoView ufoView);

        public void requestData(String sessionId,int userId,int page, int count);
    }

    public interface UfoModel{
        public void containData(String sessionId,int userId,int page, int count,CallBack callBack);

        public interface CallBack{
            public void OnCallBack(Ufo ufo);
        }
    }
}
