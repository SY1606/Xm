package com.example.tt_xm.di.contract;

public interface WaitSaveContract {
    public interface WaitSaveView{
        public void showData(String requestData);
    }


    public interface WaitSavePresenter<WaitSaveView>{
        public void attahView(WaitSaveView waitSaveView);

        public void deachView(WaitSaveView waitSaveView);

        public void requestData();
    }


    public interface WaitSaveModel{
        public void containData(CallBack callBack);

        public interface CallBack{
            public void onCallBack(String requestData);
        }
    }

}
