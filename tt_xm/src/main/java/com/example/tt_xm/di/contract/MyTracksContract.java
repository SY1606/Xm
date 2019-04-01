package com.example.tt_xm.di.contract;

public interface MyTracksContract {
    public interface MyTracksView{

        public void showDatass(String message);
    }

    public interface MyTracksPresenter<MyTracksView>{

        public void attacheView(MyTracksView myTracksView);
        public void deteachView(MyTracksView myTracksView);
        public void requestDatas(int page, int count);
    }

    public interface MyTracksModel{
        public void containDatas(int page, int count, CallBack callBack);
        public interface CallBack{
            public void onCallBack(String message);
        }

    }

}
