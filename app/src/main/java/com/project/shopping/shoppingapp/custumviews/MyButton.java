package com.project.shopping.shoppingapp.custumviews;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;


public class MyButton extends AppCompatButton {

    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        CustomFontManager.setCustomFont(this, context, attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        CustomFontManager.setCustomFont(this, context, attrs);
    }

}
