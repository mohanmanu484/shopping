package com.project.shopping.shoppingapp.ViewModel;

import android.app.Activity;
import android.support.v7.widget.AppCompatRadioButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.project.shopping.shoppingapp.R;
import com.project.shopping.shoppingapp.activity.CheckoutActivity;
import com.project.shopping.shoppingapp.model.CheckoutObj;
import com.project.shopping.shoppingapp.viewcallbacks.BaseViewCallback;
import com.project.shopping.shoppingapp.viewcallbacks.MainViewModel;
import com.project.shopping.shoppingapp.viewcallbacks.PaymentSelectCallback;
import com.razorpay.Checkout;

import org.json.JSONObject;

/**
 * Created by mohan on 21/05/17.
 */

public class PaymentSelectViewModel implements MainViewModel {

    public static final String TAG="PaymentSelectViewModel";


    FirebaseDatabase mDatabase= FirebaseDatabase.getInstance();
    FirebaseAuth mAuth=FirebaseAuth.getInstance();
    private PaymentSelectCallback.View view;

    @Override
    public void onAtttach(BaseViewCallback baseViewCallback) {
        view= (PaymentSelectCallback.View) baseViewCallback;
        AppCompatRadioButton rr= (AppCompatRadioButton) view.getPaymentGruop().getChildAt(0);
        rr.setChecked(true);

    }

    @Override
    public void onDestroy() {
        view=null;
    }

    public void onPlaceOrderClick(CheckoutObj checkoutObj){

        if(view!=null){
            if(view.getPaymentGruop().getCheckedRadioButtonId()== R.id.cashOnDel){

               CheckoutActivity checkoutActivity= (CheckoutActivity) view.getActivityInstance();
                checkoutActivity.onPaymentSuccess("cashOnDel");
                return;

            }

        }




        startPayment(view.getActivityInstance(),checkoutObj);

    }

    public void startPayment(Activity activity,CheckoutObj checkoutObj) {


        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Razorpay Corp");
            options.put("description", "Demoing Charges");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://rzp-mobile.s3.amazonaws.com/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", checkoutObj.getAmount()*100);

            /*JSONObject preFill = new JSONObject();
            preFill.put("email", checkoutObj.getUser().getEmail());
            preFill.put("contact", checkoutObj.getUser().getAddress().getPhone());

            options.put("prefill", preFill);*/

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }






}
