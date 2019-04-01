package com.example.tt_xm.di.presenter;

import com.example.tt_xm.di.contract.AddressContract;
import com.example.tt_xm.di.model.AddressModelImpl;

import java.lang.ref.SoftReference;

public class AddressPresenterImpl implements AddressContract.AddressPresenter<AddressContract.AddressView> {

    AddressContract.AddressModel addressModel;
    AddressContract.AddressView addressView;
    private SoftReference<AddressContract.AddressView> softReference;

    @Override
    public void attacheView(AddressContract.AddressView addressView) {
        this.addressView = addressView;
        softReference = new SoftReference<>(addressView);
        addressModel = new AddressModelImpl();
    }

    @Override
    public void deteachView(AddressContract.AddressView addressView) {
        softReference.clear();
    }

    @Override
    public void requestData() {
      addressModel.containData(new AddressContract.AddressModel.CallBack() {
          @Override
          public void CallBackss(String message) {
              addressView.showDatass(message);
          }
      });
    }
}
