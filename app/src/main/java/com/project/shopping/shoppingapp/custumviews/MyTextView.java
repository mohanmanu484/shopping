package com.project.shopping.shoppingapp.custumviews;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;


public class MyTextView extends AppCompatTextView {

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        CustomFontManager.setCustomFont(this, context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        CustomFontManager.setCustomFont(this, context, attrs);
    }

    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);
    }
}
