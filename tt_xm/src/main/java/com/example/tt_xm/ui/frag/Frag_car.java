package com.example.tt_xm.ui.frag;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tt_xm.R;
import com.example.tt_xm.data.bean.ShopCar;
import com.example.tt_xm.di.contract.ShopCarContract;
import com.google.gson.Gson;

import java.util.List;

public class Frag_car extends Fragment implements ShopCarContract.ShopCarView {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_car,container,false);
        return view;
    }

    @Override
    public void showData(String message) {
        Gson gson = new Gson();
        ShopCar shopCar = gson.fromJson(message,ShopCar.class);
        List<ShopCar.ResultBean> resultBeans = shopCar.getResult();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);

    }
}
