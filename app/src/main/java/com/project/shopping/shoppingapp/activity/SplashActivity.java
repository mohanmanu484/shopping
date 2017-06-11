package com.project.shopping.shoppingapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.project.shopping.shoppingapp.Utility.ModuleMaster;

public class SplashActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if(firebaseAuth.getCurrentUser()!=null){
            ModuleMaster.navigateToHomeScreen(this);
        }else {
            ModuleMaster.navigateToPreloginActivity(this);
        }

        finish();


    }
}