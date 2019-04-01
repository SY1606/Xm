package com.example.tt_xm.di.contract;

public interface WaitPayContract {
    public interface WaitPayView{
        public void showData(String requestData);
    }


    public interface WaitPayPresenter<WaitPayView>{
        public void attahView(WaitPayView waitPayView);

        public void deachView(WaitPayView waitPayView);

        public void requestData();
    }


    public interface WaitPayModel{
        public void containData(CallBack callBack);

        public interface CallBack{
            public void onCallBack(String requestData);
        }
    }
}
