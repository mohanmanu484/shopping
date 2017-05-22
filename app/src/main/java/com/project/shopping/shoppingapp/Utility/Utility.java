package com.project.shopping.shoppingapp.Utility;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.project.shopping.shoppingapp.R;

/**
 * Created by mohan on 21/05/17.
 */

public class Utility {


    private static ProgressDialog progressDialog;

    public static final int ADD = 0;
    public static final int REPLACE = 1;


    public static void showProgressDialog(Context context) {
        if(progressDialog!=null){
            progressDialog.dismiss();
            progressDialog=null;
        }


        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
        }
        progressDialog.show();
    }

    public static void hideProgressDialog() {

        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    public static void showSnackBar(View view, Context context, String msg) {
        Snackbar snackbar = Snackbar
                .make(view, msg, Snackbar.LENGTH_LONG)
                .setAction("RETRY", null);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        sbView.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        textView.setTextColor(ContextCompat.getColor(context, R.color.txt_red));
        snackbar.show();
    }

    public static void attachFragmentToActivity(FragmentManager fragmentManager, @IdRes int container, Fragment fragment, String tag, int flag) {
        // android.app.FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (flag == ADD) {
            fragmentTransaction = fragmentTransaction.add(container, fragment, tag);
        } else {
            fragmentTransaction = fragmentTransaction.replace(container, fragment, tag);
            fragmentTransaction.addToBackStack(tag);
        }
        fragmentTransaction.commit();

    }
}
