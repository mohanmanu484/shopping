package com.project.shopping.shoppingapp.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.shopping.shoppingapp.R;
import com.project.shopping.shoppingapp.Utility.Utility;
import com.project.shopping.shoppingapp.fragments.CartFragment;
import com.project.shopping.shoppingapp.fragments.PaymentSelectFragment;
import com.project.shopping.shoppingapp.fragments.PaymentSuccessFragment;
import com.project.shopping.shoppingapp.model.CartItem;
import com.project.shopping.shoppingapp.model.CheckoutObj;
import com.project.shopping.shoppingapp.model.Order;
import com.project.shopping.shoppingapp.model.Product;
import com.razorpay.PaymentResultListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by mohan on 22/05/17.
 */

public class CheckoutActivity extends AppCompatActivity implements PaymentResultListener{

    public static final String TAG="CheckoutActivity";
    private static final int PERMISSION_REQUEST_SEND_SMS =300 ;

    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

    ObservableList<CartItem> cartObservableArrayList=new ObservableArrayList<>();
    ObservableInt totalPayableAmount=new ObservableInt();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        requestReadSmsPermission(this);
        fetchCartItems();
        try {
            if(getIntent().getExtras()!=null){
               CartItem cartItem= getIntent().getExtras().getParcelable(CartFragment.CART_ITEM);
                Utility.attachFragmentToActivity(getFragmentManager(),R.id.container,CartFragment.getInstance(cartItem),"cart",Utility.ADD);
                return;
            }

        }catch (Exception e){

        }



        Utility.attachFragmentToActivity(getFragmentManager(),R.id.container,new CartFragment(),"cart",Utility.ADD);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public  boolean requestReadSmsPermission(final Context context) {
        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            String readSmsPermission = Manifest.permission.SEND_SMS;
            int hasPermission = ContextCompat.checkSelfPermission(context, readSmsPermission);
            final String[] permissions = new String[]{readSmsPermission};
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) context, permissions,PERMISSION_REQUEST_SEND_SMS);
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    public void showPaymentSelectFragment(CheckoutObj checkoutObj) {
        Utility.attachFragmentToActivity(getFragmentManager(),R.id.container, PaymentSelectFragment.getInstance(checkoutObj),"paymentSelect",Utility.REPLACE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_SEND_SMS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //showFileChooser();
                } else {
                    // MyLog.showToastAlways(getActivity(), );
                    Toast.makeText(this, "Go to settings and enable sms permissions", Toast.LENGTH_SHORT).show();
                   // Utility.showSnackBar(, getActivityContext(), getString(R.string.goto_settings));
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    /**
     * The name of the function has to be
     * onPaymentSuccess
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */




    public void onPaymentSuccess(){




    }


    @SuppressWarnings("unused")
    @Override
    public void onPaymentSuccess(String cashOnDel) {

        String orderId=firebaseDatabase.getReference("orders").push().getKey();
        List<Product> products=new ArrayList<>();

        StringBuilder message=new StringBuilder("");


        if(getIntent().getExtras()!=null) {
            CartItem cartItem = getIntent().getExtras().getParcelable(CartFragment.CART_ITEM);

            products.add(cartItem.getProduct());
            firebaseDatabase.getReference("orders").child(orderId).setValue(new Order(orderId,products,cartItem.getTotalPrice()));
            message.append("Your order for "+cartItem.getProduct().getName()
                    +" (Order no: "+orderId+
                    ") is out for delivery and is expected to be delivered today. ");
            if(cashOnDel.equalsIgnoreCase("cashOndel")){
                message.append(" You can pay Rs "+cartItem.getTotalPrice()+" by cash or credit/debit card.");
            }

        }else {


            StringBuilder items=new StringBuilder("");

            for (CartItem cartItem:cartObservableArrayList) {
                products.add(cartItem.getProduct());
                items.append(cartItem.getProduct().getName()+",");
            }
            items.deleteCharAt(items.length()-1);

            message.append("Your order for "+items
                    +" (Order no: "+orderId+
                    ") is out for delivery and is expected to be delivered today. ");
            if(cashOnDel.equalsIgnoreCase("cashOndel")){
                message.append("You can pay Rs "+totalPayableAmount.get()+" by cash or credit/debit card.");
            }

            firebaseDatabase.getReference("orders").child(orderId).setValue(new Order(orderId,products,totalPayableAmount.get()));

        }






        sendSMS("9483730666",message.toString());

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


    public void sendSMS(String phoneNo, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            Toast.makeText(getApplicationContext(), "Message Sent",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }


    public void fetchCartItems() {


        firebaseDatabase.getReference("users").child(firebaseUser.getUid()).child("userCart").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                cartObservableArrayList.clear();

                Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
                Iterator<DataSnapshot> productIterator = iterable.iterator();
                int payAmount = 0;
                while (productIterator.hasNext()) {
                    CartItem cartItem = productIterator.next().getValue(CartItem.class);
                    payAmount = payAmount + cartItem.getTotalPrice();
                    cartObservableArrayList.add(cartItem);
                }
                totalPayableAmount.set(payAmount);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


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
