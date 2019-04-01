package com.example.tt_xm.di.contract;

import com.example.tt_xm.data.bean.Details;

public interface DetailContract {
    public interface DetailsView{
        public void showData(String message);

    }

    public interface DetailsPresenter<DetailsView>{
        public void attachView(DetailsView detailsView);
        public void detachView(DetailsView detailsView);

        public void requestData(int comid,int userId,String sessionId);
    }

    public interface DetailsModel{
        public void containData(int comid,int userId,String sessionId,CallBack callBack);
        public interface CallBack{
            public void onCallBack(String message);
        }
    }

}
