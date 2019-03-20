package com.example.tt_xm.di.contract;

public interface SyncContract {
    public interface SyncView{
        public void showDatas(String message);
    }

    public interface SyncPresenter<SyncView>{

        public void attachView(SyncView syncView);
        public void detachView(SyncView syncView);

        public void requestData(int commodityId1, int count);
    }

    public interface SyncModel{

        public void containLoginData(int commodityId1, int count, CallBack callBack);

        public interface CallBack{
            public void responseData(String message);
        }
    }

}
