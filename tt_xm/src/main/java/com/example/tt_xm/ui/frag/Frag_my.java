package com.example.tt_xm.ui.frag;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tt_xm.R;
import com.example.tt_xm.ui.activity_two.MyAddressActivity;
import com.example.tt_xm.ui.activity_two.MyCircleActivity;
import com.example.tt_xm.ui.activity_two.MyDatasActivity;
import com.example.tt_xm.ui.activity_two.MyWalletActivity;
import com.example.tt_xm.ui.activity_two.MyTracksActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Frag_my extends Fragment {
    @BindView(R.id.image1)
    SimpleDraweeView image1;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.ziliao)
    TextView ziliao;
    @BindView(R.id.quanzi)
    TextView quanzi;
    @BindView(R.id.zuji)
    TextView zuji;
    @BindView(R.id.qianbao)
    TextView qianbao;
    @BindView(R.id.dizhi)
    TextView dizhi;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_my, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ziliao, R.id.quanzi, R.id.zuji, R.id.qianbao, R.id.dizhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ziliao:
                Intent intent = new Intent(getActivity(),MyDatasActivity.class);
                startActivity(intent);

                break;
            case R.id.quanzi:
                Intent intent2 = new Intent(getActivity(),MyCircleActivity.class);
                startActivity(intent2);
                break;
            case R.id.zuji:
                Intent intent3= new Intent(getActivity(),MyTracksActivity.class);
                startActivity(intent3);

                break;
            case R.id.qianbao:
                Intent intent4 = new Intent(getActivity(),MyWalletActivity.class);
                startActivity(intent4);
                break;
            case R.id.dizhi:
                Intent intent5 = new Intent(getActivity(),MyAddressActivity.class);
                startActivity(intent5);
                break;
        }
    }
}
