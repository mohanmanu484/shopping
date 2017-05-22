package com.project.shopping.shoppingapp.custumviews;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.TextPaint;
import android.util.AttributeSet;

import com.project.shopping.shoppingapp.R;

import java.lang.reflect.Field;

public class MyTextInputLayout extends TextInputLayout {

    private AttributeSet attrs;
    private String fontName;
    private String backgroundTintColor;

    public MyTextInputLayout(Context context) {
        super(context);
        init();
    }

    public MyTextInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.attrs = attrs;
        parseAttrs(attrs);
        //init(attrs);
    }

    public void parseAttrs(AttributeSet attrs)
    {
        try {
            if (attrs != null) {
                TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomTypeFace);
                this.fontName = a.getString(R.styleable.CustomTypeFace_typeFace);
                a.recycle();
                init();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void init(){
        if(backgroundTintColor != null) {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    getEditText().setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(backgroundTintColor)));
                else {
                    Drawable wrappedDrawable = DrawableCompat.wrap(getEditText().getBackground());
                    DrawableCompat.setTint(wrappedDrawable, Color.parseColor(backgroundTintColor));
                    getEditText().setBackgroundDrawable(wrappedDrawable);
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        if (fontName != null)
        {
            Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), fontName);
            //getEditText().setTypeface(myTypeface);

            try {
                // Retrieve the CollapsingTextHelper Field
                final Field cthf = this.getClass().getDeclaredField("mCollapsingTextHelper");
                cthf.setAccessible(true);

                // Retrieve an instance of CollapsingTextHelper and its TextPaint
                final Object cth = cthf.get(this);
                final Field tpf = cth.getClass().getDeclaredField("mTextPaint");
                tpf.setAccessible(true);

                // Apply your Typeface to the CollapsingTextHelper TextPaint
                ((TextPaint) tpf.get(cth)).setTypeface(myTypeface);
                ((TextPaint) tpf.get(cth)).setColor(0xFFFF0000);
            } catch (Exception ignored) {
                // Nothing to do
                ignored.printStackTrace();
            }

        }
    }


}
