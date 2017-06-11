package com.project.shopping.shoppingapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.project.shopping.shoppingapp.Utility.Utility;
import com.project.shopping.shoppingapp.fragments.SignInFragment;
import com.project.shopping.shoppingapp.fragments.SignUpFragment;

/**
 * Created by mohan on 21/05/17.
 */

public class PreLoginActivity extends AppCompatActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prelogin);
        Utility.attachFragmentToActivity(getFragmentManager(),R.id.container,new SignInFragment(),"signin",Utility.ADD);
    }

    public void showRegisterFragment() {
        Utility.attachFragmentToActivity(getFragmentManager(),R.id.container,new SignUpFragment(),"signup",Utility.REPLACE);
    }

    public void showInFragment(){
        getFragmentManager().popBackStack();
    }
}
