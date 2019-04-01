package com.example.tt_xm.di.presenter;

import com.example.tt_xm.di.contract.DeleteOrderContract;
import com.example.tt_xm.di.model.DeleteOrderModel;

import java.lang.ref.SoftReference;

public class DeleteOrderPresenter implements DeleteOrderContract.DeleteOrderDanPresenter<DeleteOrderContract.DeleteOrderView> {

    DeleteOrderContract.DeleteOrderDanModel deleteOrderDanModel;
    DeleteOrderContract.DeleteOrderView deleteOrderView;
    private SoftReference<DeleteOrderContract.DeleteOrderView> softReference;

    @Override
    public void attahView(DeleteOrderContract.DeleteOrderView deleteOrderView) {
        this.deleteOrderView = deleteOrderView;
        softReference = new SoftReference<>(deleteOrderView);
        deleteOrderDanModel = new DeleteOrderModel();
    }

    @Override
    public void deachView(DeleteOrderContract.DeleteOrderView deleteOrderView) {
        softReference.clear();
    }

    @Override
    public void requestData(String orderId) {
        deleteOrderDanModel.containData(orderId, new DeleteOrderContract.DeleteOrderDanModel.CallBack() {
            @Override
            public void onCallBack(String requestData) {
                deleteOrderView.showData(requestData);
            }
        });
    }
}
