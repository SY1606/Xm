package com.example.tt_xm.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.tt_xm.R;
import com.example.tt_xm.ui.frag.Frag_book;
import com.example.tt_xm.ui.frag.Frag_car;
import com.example.tt_xm.ui.frag.Frag_home;
import com.example.tt_xm.ui.frag.Frag_my;
import com.example.tt_xm.ui.frag.Frag_ufo;

public class ShopActivity extends AppCompatActivity {
    private long firstTime=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        final FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        final Frag_home frag_home = new Frag_home();
        final Frag_ufo frag_ufo = new Frag_ufo();
        final Frag_car frag_car = new Frag_car();
        final Frag_book frag_book = new Frag_book();
        final Frag_my frag_my = new Frag_my();

        transaction.add(R.id.frag,frag_home);
        transaction.add(R.id.frag,frag_ufo);
        transaction.add(R.id.frag,frag_car);
        transaction.add(R.id.frag,frag_book);
        transaction.add(R.id.frag,frag_my);
        transaction.show(frag_home).hide(frag_ufo).hide(frag_car).hide(frag_book).hide(frag_my);
        transaction.commit();

        RadioGroup rg = findViewById(R.id.rg);
        rg.check(R.id.homes);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction1 = manager.beginTransaction();
                switch (checkedId){
                    case R.id.homes:
                        transaction1.show(frag_home).hide(frag_ufo).hide(frag_car).hide(frag_book).hide(frag_my);
                        break;
                    case R.id.ufo:
                        transaction1.show(frag_ufo).hide(frag_home).hide(frag_car).hide(frag_book).hide(frag_my);
                        break;
                    case R.id.car:
                        transaction1.show(frag_car).hide(frag_ufo).hide(frag_home).hide(frag_book).hide(frag_my);
                        break;
                    case R.id.book:
                        transaction1.show(frag_book).hide(frag_ufo).hide(frag_car).hide(frag_home).hide(frag_my);
                        break;
                    case R.id.my:
                        transaction1.show(frag_my).hide(frag_ufo).hide(frag_car).hide(frag_book).hide(frag_home);
                        break;
                }
                transaction1.commit();

            }

        });
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                long secondTime=System.currentTimeMillis();
                if(secondTime-firstTime>2000){
                    Toast.makeText(ShopActivity.this,"再按一次退出程序--->onKeyUp",Toast.LENGTH_SHORT).show();
                    firstTime=secondTime;
                    return true;
                }else{
                    System.exit(0);
                }
                break;
        }

        return super.onKeyUp(keyCode, event);

    }
}
