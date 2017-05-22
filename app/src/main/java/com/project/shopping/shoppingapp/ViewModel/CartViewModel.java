package com.project.shopping.shoppingapp.ViewModel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.shopping.shoppingapp.Utility.ModuleMaster;
import com.project.shopping.shoppingapp.model.Address;
import com.project.shopping.shoppingapp.model.CartItem;
import com.project.shopping.shoppingapp.model.User;
import com.project.shopping.shoppingapp.viewcallbacks.BaseViewCallback;
import com.project.shopping.shoppingapp.viewcallbacks.CartViewCallback;
import com.project.shopping.shoppingapp.viewcallbacks.MainViewModel;

import java.util.Iterator;

/**
 * Created by mohan on 21/05/17.
 */

public class CartViewModel implements MainViewModel{


    FirebaseDatabase mDatabase= FirebaseDatabase.getInstance();
    FirebaseAuth mAuth=FirebaseAuth.getInstance();
    private CartViewCallback.View view;

    public ObservableBoolean addressPresent=new ObservableBoolean(false);
    public ObservableArrayList<CartItem> cartObservableArrayList=new ObservableArrayList<>();



    @Override
    public void onAtttach(BaseViewCallback baseViewCallback) {
        view= (CartViewCallback.View) baseViewCallback;
        fetchAddressInfo();
        fetchCartItems();

    }

    private void fetchCartItems() {

        FirebaseUser firebaseUser=mAuth.getCurrentUser();
        if(firebaseUser==null){
            mAuth.signOut();
            ModuleMaster.navigateToPreloginActivity(view.getActivityContext());
            return;
        }

        mDatabase.getReference("users").child(firebaseUser.getUid()).child("userCart").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                cartObservableArrayList.clear();

                Iterable<DataSnapshot> iterable=dataSnapshot.getChildren();
                Iterator<DataSnapshot> productIterator=iterable.iterator();
                while (productIterator.hasNext()){
                    cartObservableArrayList.add(productIterator.next().getValue(CartItem.class));
                }

                if(view!=null){
                    view.hideProgressBar();
                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }


    public void fetchAddressInfo(){
        FirebaseUser firebaseUser=mAuth.getCurrentUser();
        if(firebaseUser==null){
            mAuth.signOut();
            ModuleMaster.navigateToPreloginActivity(view.getActivityContext());
            return;
        }

        mDatabase.getReference("users").child(firebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                User user=dataSnapshot.getValue(User.class);
                if(user.getAddress()==null){
                    addressPresent.set(false);
                }else {
                    addressPresent.set(true);
                    if(view!=null){
                        view.setAddress(user.getAddress());
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                if(view!=null){

                    view.showMessage(databaseError.getMessage());
                }


            }
        });


    }

    public void onEditAddressClick(Address address){
        view.showAddressActivity(address);
    }

    public void onAddAddressClick(Address address){
        view.showAddressActivity(address);
    }

    public void onRemoveFromCartClick(CartItem cartItem){

        String id=cartItem.getId();
        mDatabase.getReference("users").child(mAuth.getCurrentUser().getUid()).child("userCart").child(id).setValue(null);
        cartObservableArrayList.remove(cartItem);
    }




    @Override
    public void onDestroy() {
        view=null;
    }


}
