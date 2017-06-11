package com.project.shopping.shoppingapp.custumviews;


import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;


public class MyEditText extends AppCompatEditText {

    public MyEditText(Context context) {
        super(context);
        CustomFontManager.setCustomFont(this, context,null);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        CustomFontManager.setCustomFont(this, context, attrs);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        CustomFontManager.setCustomFont(this, context, attrs);
    }
}
