package com.example.tt_xm.di.presenter;

import com.example.tt_xm.di.contract.PersonContract;
import com.example.tt_xm.di.model.PersonModel;

import java.lang.ref.SoftReference;

public class PersonPresenter implements PersonContract.PersonDataPresenter<PersonContract.PersonDataView> {

    PersonContract.PersonDataView personDataView;
    PersonContract.PersonDataModel  personDataModel;
    private SoftReference<PersonContract.PersonDataView> softReference;

    @Override
    public void attchPersonDataView(PersonContract.PersonDataView personDataView) {
        this.personDataView = personDataView;
        softReference = new SoftReference<>(personDataView);
        personDataModel = new PersonModel();
    }

    @Override
    public void deachPersonDataView(PersonContract.PersonDataView personDataView) {
        softReference.clear();
    }

    @Override
    public void requestPersonDataData() {
        personDataModel.containData(new PersonContract.PersonDataModel.CallBack() {
            @Override
            public void onCallBacks(String responseData) {
                personDataView.getPersonDataData(responseData);
            }
        });
    }
}
