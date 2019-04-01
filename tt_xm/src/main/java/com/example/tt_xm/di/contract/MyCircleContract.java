package com.example.tt_xm.di.contract;

public interface MyCircleContract {
    public interface MyCircleView{

        public void showDatass(String message);
    }

    public interface MyCirclePresenter<MyCircleView>{

        public void attacheView(MyCircleView myCircleView);
        public void deteachView(MyCircleView myCircleView);
        public void requestDatas(int page, int count);
    }

    public interface MyCircleModel{
        public void containDatas(int page, int count,CallBack callBack);
        public interface CallBack{
            public void onCallBack(String message);
        }

    }

}
