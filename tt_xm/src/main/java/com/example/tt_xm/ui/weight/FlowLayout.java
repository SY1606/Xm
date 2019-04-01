package com.example.tt_xm.ui.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tt_xm.R;

import io.reactivex.annotations.Nullable;

public class FlowLayout extends FrameLayout {
    //水平间距是20
    private final static int H_DISTANCE = 20;
    //竖直间距是20
    private final static int V_DISTANCE = 20;

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context,AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void addTextView(String keys) {

        //加载xml布局的方法
        TextView textView = (TextView) View.inflate(getContext(), R.layout.flow_item, null);

        textView.setText(keys);

        //布局宽高自适应
        LayoutParams params = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //控件设置上布局参数
        textView.setLayoutParams(params);
        addView(textView);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        int width = getWidth();
        int row = 0;

        int disWidth = H_DISTANCE;
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            //获取本控件的宽度，用于计算换行
            int viewWidth = view.getWidth();
            int viewHeight = view.getHeight();
            Log.i("dt", "textHeight:" + viewHeight);

            if (disWidth + viewWidth > width) {
                row++;
                disWidth = H_DISTANCE;
            }
            int viewTop = row * viewHeight + row * V_DISTANCE;

            //子控件布局
            view.layout(disWidth, viewTop,
                    disWidth + viewWidth, viewTop + viewHeight);

            //记录下一次子控件左边的坐标
            disWidth += (viewWidth + H_DISTANCE);
        }

    }
}
