package com.project.shopping.shoppingapp.custumviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.project.shopping.shoppingapp.R;

import java.lang.reflect.Field;
import java.util.Hashtable;

public class CustomFontManager {

    private static final String TAG = CustomFontManager.class.getCanonicalName();

    private static String font_opensans_regular = "fonts/Karla-Regular.ttf";

    private static Hashtable<String, Typeface> fontCache = new Hashtable<>();

    public static Typeface get(String name, Context context) {
        Typeface tf = fontCache.get(name);
        if(tf == null) {
            try {
                tf = Typeface.createFromAsset(context.getAssets(), name);
            }
            catch (Exception e) {
                return null;
            }
            fontCache.put(name, tf);
        }
        return tf;
    }

    /**
     * Sets a font on a textview based on the custom com.my.package:font attribute
     * If the custom font attribute isn't found in the attributes nothing happens
     *
     * @param textview to apply font
     * @param context required to apply font
     * @param attrs custom attributes of font path
     */
    public static void setCustomFont(TextView textview, Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomTypeFace);
        String font;
        if (a == null) {
            font = font_opensans_regular;
        } else {
            font = a.getString(R.styleable.CustomTypeFace_typeFace);
            if (font == null) {
                font = font_opensans_regular;
            }
        }
        setCustomFont(textview, font, context);
        assert a != null;
        a.recycle();
    }


    public static void setInputLayoutFont(TextInputLayout inputLayout, Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomTypeFace);
        String font;
        if (a == null) {
            font = font_opensans_regular;
        } else {
            font = a.getString(R.styleable.CustomTypeFace_typeFace);
            if (font == null) {
                font = font_opensans_regular;
            }
        }
        setInputLayoutFont(inputLayout, font, context);
        assert a != null;
        a.recycle();
    }

    /**
     * Sets a font on a textview
     *
     * @param textview
     * @param font
     * @param context
     */
    private static void setCustomFont(TextView textview, String font, Context context) {
        if (font == null) {
            return;
        }

        Typeface tf = get(font, context);
        if (tf != null) {
            textview.setTypeface(tf);
        }
    }




    private static void setInputLayoutFont(TextInputLayout inputLayout, String font, Context context){
        if (font == null) {
            return;
        }
        final Typeface tf = get(font, context);
        if(tf==null) return;
            inputLayout.getEditText().setTypeface(tf);
        try {
            final Field collapsingTextHelperField = inputLayout.getClass().getDeclaredField("mCollapsingTextHelper");
            collapsingTextHelperField.setAccessible(true);
            final Object collapsingTextHelper = collapsingTextHelperField.get(inputLayout);
            final Field tpf = collapsingTextHelper.getClass().getDeclaredField("mTextPaint");
            tpf.setAccessible(true);
            // Apply your Typeface to the CollapsingTextHelper TextPaint
            ((TextPaint) tpf.get(collapsingTextHelper)).setTypeface(tf);
        } catch (Exception ignored) {
            Log.e(TAG,ignored.getLocalizedMessage());
        }
    }

    public static void clearCache(){
        if(fontCache != null){
            fontCache.clear();
        }
    }
}
