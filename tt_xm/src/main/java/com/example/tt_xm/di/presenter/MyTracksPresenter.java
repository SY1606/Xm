package com.example.tt_xm.di.presenter;

import com.example.tt_xm.di.contract.MyCircleContract;
import com.example.tt_xm.di.contract.MyTracksContract;
import com.example.tt_xm.di.model.MyCircleModel;
import com.example.tt_xm.di.model.MyTracksModel;

import java.lang.ref.SoftReference;

public class MyTracksPresenter implements MyTracksContract.MyTracksPresenter<MyTracksContract.MyTracksView> {

    MyTracksContract.MyTracksView myTracksView;
    MyTracksContract.MyTracksModel myTracksModel;
    private SoftReference<MyTracksContract.MyTracksView> softReference;



    @Override
    public void attacheView(MyTracksContract.MyTracksView myTracksView) {
        this.myTracksView = myTracksView;
        softReference = new SoftReference<>(myTracksView);
        myTracksModel = new MyTracksModel();
    }

    @Override
    public void deteachView(MyTracksContract.MyTracksView myTracksView) {
        softReference.clear();
    }

    @Override
    public void requestDatas(int page, int count) {
        myTracksModel.containDatas(page, count, new MyTracksContract.MyTracksModel.CallBack() {
            @Override
            public void onCallBack(String message) {
                myTracksView.showDatass(message);
            }
        });
    }
}
