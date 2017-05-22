package com.project.shopping.shoppingapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.project.shopping.shoppingapp.R;
import com.project.shopping.shoppingapp.Utility.Utility;
import com.project.shopping.shoppingapp.fragments.CartFragment;

/**
 * Created by mohan on 22/05/17.
 */

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);



        Utility.attachFragmentToActivity(getFragmentManager(),R.id.container,new CartFragment(),"cart",Utility.ADD);
    }

}
