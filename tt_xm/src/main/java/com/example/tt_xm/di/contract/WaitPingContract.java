package com.example.tt_xm.di.contract;

public interface WaitPingContract  {

    public interface WaitPingView{
        public void showData(String requestData);
    }


    public interface WaitPingPresenter<WaitPingView>{
        public void attahView(WaitPingView waitPingView);

        public void deachView(WaitPingView waitPingView);

        public void requestData();
    }


    public interface WaitPingModel{
        public void containData(CallBack callBack);

        public interface CallBack{
            public void onCallBack(String requestData);
        }
    }
}
