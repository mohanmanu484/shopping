package com.project.shopping.shoppingapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.project.shopping.shoppingapp.R;
import com.project.shopping.shoppingapp.Utility.Utility;
import com.project.shopping.shoppingapp.fragments.AddressFragment;
import com.project.shopping.shoppingapp.model.Address;

/**
 * Created by mohan on 22/05/17.
 */

public class AddresssActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        Utility.attachFragmentToActivity(getFragmentManager(),R.id.container,new AddressFragment(),"address",Utility.ADD);
    }

    public void finishActivityWithAddress(Address address) {

        Intent intent=new Intent();
        intent.putExtra(AddressFragment.EXTRA_ADDRESS,address);
        setResult(RESULT_OK,intent);
        finish();

    }
}
