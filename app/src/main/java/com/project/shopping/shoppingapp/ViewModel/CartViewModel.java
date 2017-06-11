package com.project.shopping.shoppingapp.ViewModel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.shopping.shoppingapp.Utility.ModuleMaster;
import com.project.shopping.shoppingapp.model.Address;
import com.project.shopping.shoppingapp.model.CartItem;
import com.project.shopping.shoppingapp.model.CheckoutObj;
import com.project.shopping.shoppingapp.model.User;
import com.project.shopping.shoppingapp.viewcallbacks.BaseViewCallback;
import com.project.shopping.shoppingapp.viewcallbacks.CartViewCallback;
import com.project.shopping.shoppingapp.viewcallbacks.MainViewModel;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by mohan on 21/05/17.
 */

public class CartViewModel implements MainViewModel {


    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private CartViewCallback.View view;

    public ObservableBoolean addressPresent = new ObservableBoolean(false);
    public ObservableArrayList<CartItem> cartObservableArrayList = new ObservableArrayList<>();

    public ObservableInt totalPayableAmount = new ObservableInt(0);


    @Override
    public void onAtttach(BaseViewCallback baseViewCallback) {
        view = (CartViewCallback.View) baseViewCallback;
        fetchAddressInfo();
        fetchCartItems();

    }

    public void fetchCartItems() {

        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser == null) {
            mAuth.signOut();
            ModuleMaster.navigateToPreloginActivity(view.getActivityContext());
            return;
        }

        if (view.getSingleProduct() != null) {

            CartItem cartItem = view.getSingleProduct();
            cartObservableArrayList.add(cartItem);
            totalPayableAmount.set(cartItem.getTotalPrice());


            return;
        }


        mDatabase.getReference("users").child(firebaseUser.getUid()).child("userCart").addValueEventListener(new ValueEventListener() {
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

                if (view != null) {
                    view.hideProgressBar();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    public void fetchAddressInfo() {
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser == null) {
            mAuth.signOut();
            ModuleMaster.navigateToPreloginActivity(view.getActivityContext());
            return;
        }

        mDatabase.getReference("users").child(firebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {
                    User user = dataSnapshot.getValue(User.class);
                    if (user.getAddress() == null) {
                        addressPresent.set(false);
                    } else {
                        addressPresent.set(true);
                        if (view != null) {
                            view.setAddress(user.getAddress());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                if (view != null) {

                    view.showMessage(databaseError.getMessage());
                }


            }
        });


    }

    public void onEditAddressClick(Address address) {
        view.showAddressActivity(address);
    }

    public void onAddAddressClick(Address address) {
        view.showAddressActivity(address);
    }

    public void onRemoveFromCartClick(CartItem cartItem) {

        String id = cartItem.getId();
        mDatabase.getReference("users").child(mAuth.getCurrentUser().getUid()).child("userCart").child(id).setValue(null);
        cartObservableArrayList.remove(cartItem);
        fetchCartItems();
    }

    public void onContinueClick() {
        CheckoutObj checkoutObj = new CheckoutObj(totalPayableAmount.get(), cartObservableArrayList.size());
        view.showPaymentSelectFragment(checkoutObj);
        //Toast.makeText(view.getActivityContext(), "continue click", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDestroy() {
        view = null;
    }

    public int getItemsCount(ArrayList<CartItem> cartItems) {
        return cartItems.size();
    }

    public boolean enableContinue(int amount, boolean addressPresent) {
        return amount > 0 && addressPresent;
    }


}
