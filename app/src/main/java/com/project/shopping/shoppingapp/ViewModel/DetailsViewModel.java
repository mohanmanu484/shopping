package com.project.shopping.shoppingapp.ViewModel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableInt;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.shopping.shoppingapp.Utility.ModuleMaster;
import com.project.shopping.shoppingapp.model.CartItem;
import com.project.shopping.shoppingapp.model.Product;
import com.project.shopping.shoppingapp.viewcallbacks.BaseViewCallback;
import com.project.shopping.shoppingapp.viewcallbacks.DetailsViewCallback;
import com.project.shopping.shoppingapp.viewcallbacks.MainViewModel;

/**
 * Created by mohan on 21/05/17.
 */

public class DetailsViewModel implements MainViewModel{


    FirebaseDatabase mDatabase= FirebaseDatabase.getInstance();
    FirebaseAuth mAuth=FirebaseAuth.getInstance();
    public ObservableInt selectedQuantity=new ObservableInt(1);


    private DetailsViewCallback.View viewCallback;

    //public ObservableField<List<Product>> observableFieldProductList=new ObservableField<List<Product>>(new ArrayList<Product>());
    public ObservableArrayList<Product> productObservableArrayList=new ObservableArrayList<>();


    @Override
    public void onAtttach(BaseViewCallback baseViewCallback) {
        viewCallback = (DetailsViewCallback.View) baseViewCallback;

    }

    @Override
    public void onDestroy() {
        viewCallback =null;
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

    public void onPlusClick(){
        selectedQuantity.set(selectedQuantity.get()+1);
    }

    public void onMinusClick(){
        if(selectedQuantity.get()>1) {
            selectedQuantity.set(selectedQuantity.get() - 1);
        }
    }

    public void onBuyNowClick(View view){

        CartItem cartItem=new CartItem(this.viewCallback.getProduct(),selectedQuantity.get(),selectedQuantity.get()*this.viewCallback.getProduct().getPrice());

        ModuleMaster.navigateToCart(view.getContext(),cartItem);
    }

    public void onAddToCartClick(View view){
        viewCallback.showProgressBar();
       // Toast.makeText(viewCallback.getContext(), "Dev in progress", Toast.LENGTH_SHORT).show();
        DatabaseReference dr=mDatabase.getReference("users").child(mAuth.getCurrentUser().getUid()).child("userCart").push();
        CartItem cartItem=new CartItem(this.viewCallback.getProduct(),selectedQuantity.get(),selectedQuantity.get()*this.viewCallback.getProduct().getPrice());
        cartItem.setId(dr.getKey());
        dr.setValue(cartItem);
        viewCallback.hideProgressBar();
        Toast.makeText(view.getContext(), "Item added to cart successfully", Toast.LENGTH_SHORT).show();
       // ModuleMaster.navigateToCart(viewCallback.getContext());
    }


}
