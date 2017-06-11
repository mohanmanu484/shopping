package com.project.shopping.shoppingapp.viewcallbacks;

import android.app.Activity;
import android.content.Context;

/**
 * Created by mohan on 21/05/17.
 */

public interface BaseViewCallback {
    void showProgressBar();
    void hideProgressBar();
    void showMessage(String message);
    Context getActivityContext();
    Activity getActivityInstance();

}
