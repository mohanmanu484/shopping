package com.project.shopping.shoppingapp.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.project.shopping.shoppingapp.model.Address;
import com.project.shopping.shoppingapp.viewcallbacks.AddressCallback;
import com.project.shopping.shoppingapp.viewcallbacks.BaseViewCallback;
import com.project.shopping.shoppingapp.viewcallbacks.MainViewModel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mohan on 21/05/17.
 */

public class AddressViewModel implements MainViewModel{


    FirebaseDatabase mDatabase= FirebaseDatabase.getInstance();
    FirebaseAuth mAuth=FirebaseAuth.getInstance();
    private AddressCallback.View view;

    @Override
    public void onAtttach(BaseViewCallback baseViewCallback) {
        view= (AddressCallback.View) baseViewCallback;

    }

    @Override
    public void onDestroy() {
        view=null;
    }

    public void onSaveClick(Address address){

        if(!enableSave(address)){
            view.showMessage("Please fill the addresss completly");
            return;
        }

        FirebaseUser firebaseUser=mAuth.getCurrentUser();
        Map<String,Address> map=new HashMap<String,Address>();
        map.put("address",address);
        mDatabase.getReference("users").child(firebaseUser.getUid()).setValue(map);
        if(view!=null) {
            view.showAddress(address);
        }

    }

    public boolean enableSave(Address address){

      return   address.getName().length()>0 && address.getPhone().length()==10 && address.getArea().length()>0 && address.getCity().length()>0 && address.getPincode().length()>0 && address.getState().length()>0;

    }


}
