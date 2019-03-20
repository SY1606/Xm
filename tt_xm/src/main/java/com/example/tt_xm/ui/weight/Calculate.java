package com.example.tt_xm.ui.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tt_xm.R;

public class Calculate extends LinearLayout implements View.OnClickListener {

    private final Button btn_add;
    private final TextView tv_number;
    private final Button btn_jian;

    public Calculate(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.item_calculate,this);
        btn_jian = view.findViewById(R.id.btn_jian);
        tv_number = view.findViewById(R.id.tv_number);
        btn_add = view.findViewById(R.id.btn_add);
    }

    @Override
    public void onClick(View v) {
        String numberString = tv_number.getText().toString();
        int number = Integer.parseInt(numberString);
        switch (v.getId()){
            case R.id.btn_jian:
                number--;
                if (number<0){
                    number=0;
                    tv_number.setText(String.valueOf(number));
                }
                tv_number.setText(String.valueOf(number));
                onNumberItemClickListener.jian(number);
                break;
            case R.id.btn_add:
                number++;
                tv_number.setText(String.valueOf(number));
                onNumberItemClickListener.jia(number);
                break;
        }
    }

    //创建接口
    OnNumberItemClickListener onNumberItemClickListener;

    public void setOnNumberItemClickListener(OnNumberItemClickListener onNumberItemClickListener){
        this.onNumberItemClickListener = onNumberItemClickListener;
    }

    public interface OnNumberItemClickListener{
        public void jian(int number);
        public void jia(int number);
    }
    public void setNum(int count){
        tv_number.setText(count+"");
    }
}
