package com.example.tt_xm.di.contract;

public interface QuorderContract {

    public interface QuorderView{
        public void showData(String requestData);
    }


    public interface QuorderPresenter<QuorderView>{
        public void attahView(QuorderView quorderView);

        public void deachView(QuorderView quorderView);

        public void requestData();
    }


    public interface QuorderModel{
        public void containData( CallBack callBack);

        public interface CallBack{
            public void onCallBack(String requestData);
        }
    }
}
