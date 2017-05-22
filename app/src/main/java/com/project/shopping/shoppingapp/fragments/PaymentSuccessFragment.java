package com.project.shopping.shoppingapp.fragments;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.shopping.shoppingapp.R;
import com.project.shopping.shoppingapp.activity.CheckoutActivity;
import com.project.shopping.shoppingapp.databinding.PaymentSuccessFragmentBinding;
import com.project.shopping.shoppingapp.model.User;

/**
 * Created by mohan on 22/05/17.
 */

public class PaymentSuccessFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final PaymentSuccessFragmentBinding paymentSuccessFragment=DataBindingUtil.inflate(inflater, R.layout.payment_success_fragment,container,false);
        paymentSuccessFragment.setModel(this);
        FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        //firebaseUser
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseDatabase.getReference("users").child(firebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                User user=dataSnapshot.getValue(User.class);
                paymentSuccessFragment.setName(user.getName());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

       // paymentSuccessFragment.setName(FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).);
        return paymentSuccessFragment.getRoot();
    }

    public void onOkayClick(){

        CheckoutActivity checkoutActivity= (CheckoutActivity) getActivity();
        checkoutActivity.onOkayClick();

    }
}
