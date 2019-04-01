package com.example.tt_xm.ui.frag;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.tt_xm.R;
import com.example.tt_xm.ui.frag.frag_myself.Frag_finish;
import com.example.tt_xm.ui.frag.frag_myself.Frag_order;
import com.example.tt_xm.ui.frag.frag_myself.Frag_pay;
import com.example.tt_xm.ui.frag.frag_myself.Frag_ping;
import com.example.tt_xm.ui.frag.frag_myself.Frag_save;

public class Frag_book extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_book,container,false);
        final FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        final Frag_order frag_order = new Frag_order();
        final Frag_pay frag_pay = new Frag_pay();
        final Frag_save frag_save = new Frag_save();
        final Frag_ping frag_ping = new Frag_ping();
        final Frag_finish frag_finish = new Frag_finish();

        transaction.add(R.id.frag01, frag_order);
        transaction.add(R.id.frag01,frag_pay);
        transaction.add(R.id.frag01,frag_save);
        transaction.add(R.id.frag01,frag_ping);
        transaction.add(R.id.frag01,frag_finish);

        transaction.show(frag_order).hide(frag_pay).hide(frag_save).hide(frag_ping).hide(frag_finish);
        transaction.commit();
        RadioGroup rg = view.findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction1 = manager.beginTransaction();
                switch (checkedId){
                    case R.id.list_icon:
                        transaction1.show(frag_order).hide(frag_pay).hide(frag_save).hide(frag_ping).hide(frag_finish);
                        break;
                    case R.id.dfk_icon:
                        transaction1.show(frag_pay).hide(frag_order).hide(frag_save).hide(frag_ping).hide(frag_finish);
                        break;
                    case R.id.dsh_icon:
                        transaction1.show(frag_save).hide(frag_pay).hide(frag_order).hide(frag_ping).hide(frag_finish);
                        break;
                    case R.id.dpj_icon:
                        transaction1.show(frag_ping).hide(frag_save).hide(frag_pay).hide(frag_order).hide(frag_finish);
                        break;
                    case R.id.ywc_icon:
                        transaction1.show(frag_finish).hide(frag_ping).hide(frag_order).hide(frag_pay).hide(frag_save);
                        break;
                }
                transaction1.commit();
            }
        });
        return view;
    }
}
