package com.example.tt_xm.di.presenter;

import com.example.tt_xm.di.contract.NewAddressContract;
import com.example.tt_xm.di.model.NewAddressModelmpl;

import java.lang.ref.SoftReference;

public class NewAddressPresenterImpl implements NewAddressContract.NewAddressPresenter<NewAddressContract.NewAddressView> {

    NewAddressContract.NewAddressView newAddressView;
    NewAddressContract.NewAddressModel newAddressModel;
    private SoftReference<NewAddressContract.NewAddressView> softReference;

    @Override
    public void attachView(NewAddressContract.NewAddressView newAddressView) {
        this.newAddressView = newAddressView;
        softReference = new SoftReference<>(newAddressView);
      newAddressModel = new NewAddressModelmpl();
    }

    @Override
    public void detachView(NewAddressContract.NewAddressView newAddressView) {
        softReference.clear();
    }

    @Override
    public void requestData(String name, String phone, String addresss, String youzheng) {
        newAddressModel.containData(name, phone, addresss, youzheng, new NewAddressContract.NewAddressModel.CallBack() {
            @Override
            public void OnCallBack(String message) {
                newAddressView.showData(message);
            }
        });
    }
}
