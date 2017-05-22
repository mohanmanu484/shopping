package com.project.shopping.shoppingapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.project.shopping.shoppingapp.R;
import com.project.shopping.shoppingapp.Utility.Utility;
import com.project.shopping.shoppingapp.fragments.DetailsFragment;
import com.project.shopping.shoppingapp.fragments.HomeFragment;
import com.project.shopping.shoppingapp.model.Product;

/**
 * Created by mohan on 21/05/17.
 */

public class HomeActivity extends AppCompatActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Utility.attachFragmentToActivity(getFragmentManager(),R.id.container,new HomeFragment(),"home",Utility.ADD);
    }

    public void showDetailsFragment(Product product){
        Utility.attachFragmentToActivity(getFragmentManager(),R.id.container, DetailsFragment.getInstance(product),"details",Utility.REPLACE);
    }
}
