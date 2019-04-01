package com.example.tt_xm.di.presenter;

import com.example.tt_xm.di.contract.MyCircleContract;
import com.example.tt_xm.di.model.MyCircleModel;

import java.lang.ref.SoftReference;

public class MyCirclePresenter implements MyCircleContract.MyCirclePresenter<MyCircleContract.MyCircleView> {

    MyCircleContract.MyCircleView myCircleView;
    MyCircleContract.MyCircleModel myCircleModel;
    private SoftReference<MyCircleContract.MyCircleView> softReference;

    @Override
    public void attacheView(MyCircleContract.MyCircleView myCircleView) {
        this.myCircleView = myCircleView;
        softReference = new SoftReference<>(myCircleView);
        myCircleModel = new MyCircleModel();
    }

    @Override
    public void deteachView(MyCircleContract.MyCircleView myCircleView) {
        softReference.clear();
    }

    @Override
    public void requestDatas(int page, int count) {
        myCircleModel.containDatas(page, count, new MyCircleContract.MyCircleModel.CallBack() {
            @Override
            public void onCallBack(String message) {
                myCircleView.showDatass(message);
            }
        });
    }
}
