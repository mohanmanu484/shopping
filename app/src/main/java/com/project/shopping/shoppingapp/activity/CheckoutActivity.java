package com.project.shopping.shoppingapp.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.project.shopping.shoppingapp.R;
import com.project.shopping.shoppingapp.Utility.Utility;
import com.project.shopping.shoppingapp.fragments.CartFragment;
import com.project.shopping.shoppingapp.fragments.PaymentSelectFragment;
import com.project.shopping.shoppingapp.fragments.PaymentSuccessFragment;
import com.project.shopping.shoppingapp.model.CheckoutObj;
import com.razorpay.PaymentResultListener;

/**
 * Created by mohan on 22/05/17.
 */

public class CheckoutActivity extends AppCompatActivity implements PaymentResultListener{

    public static final String TAG="CheckoutActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);



        Utility.attachFragmentToActivity(getFragmentManager(),R.id.container,new CartFragment(),"cart",Utility.ADD);
    }

    public void showPaymentSelectFragment(CheckoutObj checkoutObj) {
        Utility.attachFragmentToActivity(getFragmentManager(),R.id.container, PaymentSelectFragment.getInstance(checkoutObj),"paymentSelect",Utility.REPLACE);
    }

    /**
     * The name of the function has to be
     * onPaymentSuccess
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */
    @SuppressWarnings("unused")
    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {

        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        fragmentTransaction.remove(fragmentManager.findFragmentByTag("paymentSelect"));
        fragmentTransaction.remove(fragmentManager.findFragmentByTag("cart"));
        fragmentTransaction.add(R.id.container,new PaymentSuccessFragment());
        fragmentTransaction.commit();


        /*try {
            Toast.makeText(this, "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }*/
    }

    /**
     * The name of the function has to be
     * onPaymentError
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */
    @SuppressWarnings("unused")
    @Override
    public void onPaymentError(int code, String response) {
        try {
            Toast.makeText(this, "Payment failed: " + code + " " + response, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onOkayClick() {
        finish();
    }
}
