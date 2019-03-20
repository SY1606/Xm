package com.example.tt_xm.di.contract;

public interface GoodsContract {
    public interface GoodView{
        public void showDatas(String message);
    }

    public interface GoodPresenter<GoodView>{
        public void attachView(GoodView goodView);
        public void detachView(GoodView goodView);

        public void requestDatas();
    }

    public interface GoodModel{
        public void containDatas(CallBack callBack);

        public interface CallBack{
            public void CallBacks(String message);
        }
    }
}
