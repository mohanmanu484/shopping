package com.project.shopping.shoppingapp.ViewModel;

import android.databinding.ObservableArrayList;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.shopping.shoppingapp.model.Product;
import com.project.shopping.shoppingapp.viewcallbacks.BaseViewCallback;
import com.project.shopping.shoppingapp.viewcallbacks.DeliveryViewCallback;
import com.project.shopping.shoppingapp.viewcallbacks.MainViewModel;

/**
 * Created by mohan on 21/05/17.
 */

public class DeliveryViewModel implements MainViewModel{


    FirebaseDatabase mDatabase= FirebaseDatabase.getInstance();
    FirebaseAuth mAuth=FirebaseAuth.getInstance();
    private DeliveryViewCallback.View view;

    //public ObservableField<List<Product>> observableFieldProductList=new ObservableField<List<Product>>(new ArrayList<Product>());
    public ObservableArrayList<Product> productObservableArrayList=new ObservableArrayList<>();


    @Override
    public void onAtttach(BaseViewCallback baseViewCallback) {
        view= (DeliveryViewCallback.View) baseViewCallback;

    }

    @Override
    public void onDestroy() {
        view=null;
    }


    public void showAddress() {
        if(mAuth!=null && mAuth.getCurrentUser()!=null){

            FirebaseUser user=mAuth.getCurrentUser();
            mDatabase.getReference("users").child(user.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }



    }
}
