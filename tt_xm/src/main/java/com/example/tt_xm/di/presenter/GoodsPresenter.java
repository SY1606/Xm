package com.example.tt_xm.di.presenter;

import com.example.tt_xm.di.contract.GoodsContract;
import com.example.tt_xm.di.model.GoodsModel;

import java.lang.ref.SoftReference;

public class GoodsPresenter implements GoodsContract.GoodPresenter<GoodsContract.GoodView> {

    GoodsContract.GoodModel goodsModel;
    GoodsContract.GoodView goodsView;
    private SoftReference<GoodsContract.GoodView> softReference;

    @Override
    public void attachView(GoodsContract.GoodView goodView) {
       this.goodsView = goodView;
        softReference = new SoftReference<>(goodsView);
        goodsModel = new GoodsModel();
    }

    @Override
    public void detachView(GoodsContract.GoodView goodView) {
        softReference.clear();
    }

    @Override
    public void requestDatas() {
        goodsModel.containDatas(new GoodsContract.GoodModel.CallBack() {
            @Override
            public void CallBacks(String message) {
                goodsView.showDatas(message);
            }
        });
    }
}
