package com.example.tt_xm.di.contract;

public interface DetailContract {
    public interface DetailsView{
        public void showData(String message);

    }

    public interface DetailsPresenter<DetailsView>{
        public void attachView(DetailsView detailsView);
        public void detachView(DetailsView detailsView);

        public void requestData(int cid);
    }

    public interface DetailsModel{
        public void containData(int cid,CallBack callBack);
        public interface CallBack{
            public void onCallBack(String message);
        }
    }

}
