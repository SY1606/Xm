package com.example.tt_xm.di.presenter;

import com.example.tt_xm.data.bean.Details;
import com.example.tt_xm.di.contract.DetailContract;
import com.example.tt_xm.di.model.DetailsModel;

import java.lang.ref.SoftReference;

public class DetailPresenter implements DetailContract.DetailsPresenter<DetailContract.DetailsView> {


    DetailContract.DetailsModel detailsModel;
    DetailContract.DetailsView detailsView;
    private SoftReference<DetailContract.DetailsView> softReference;


    @Override
    public void attachView(DetailContract.DetailsView detailsView) {
        this.detailsView = detailsView;
        softReference = new SoftReference<>(detailsView);
       detailsModel = new DetailsModel();
    }

    @Override
    public void detachView(DetailContract.DetailsView detailsView) {
        softReference.clear();
    }

    @Override
    public void requestData(int comid, int userId, String sessionId) {
        detailsModel.containData(comid, userId, sessionId, new DetailContract.DetailsModel.CallBack() {
            @Override
            public void onCallBack(String message) {
                detailsView.showData(message);
            }
        });
    }


}
