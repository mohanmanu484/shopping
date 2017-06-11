package com.project.shopping.shoppingapp.ViewModel;

import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.shopping.shoppingapp.Utility.ModuleMaster;
import com.project.shopping.shoppingapp.model.Product;
import com.project.shopping.shoppingapp.viewcallbacks.BaseViewCallback;
import com.project.shopping.shoppingapp.viewcallbacks.HomeViewCallback;
import com.project.shopping.shoppingapp.viewcallbacks.MainViewModel;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by mohan on 21/05/17.
 */

public class HomeViewModel implements MainViewModel{


    FirebaseDatabase mDatabase= FirebaseDatabase.getInstance();
    private HomeViewCallback.View view;

    //public ObservableField<List<Product>> observableFieldProductList=new ObservableField<List<Product>>(new ArrayList<Product>());
    public ObservableArrayList<Product> productObservableArrayList=new ObservableArrayList<>();


    @Override
    public void onAtttach(BaseViewCallback baseViewCallback) {
        view= (HomeViewCallback.View) baseViewCallback;
        fetchProducts();
    }

    @Override
    public void onDestroy() {
        view=null;
    }

    public void fetchProducts(){

        if(view!=null){
            view.showProgressBar();
        }



        mDatabase.getReference("products").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                productObservableArrayList.clear();
                Iterable<DataSnapshot> iterable=dataSnapshot.getChildren();
                Iterator<DataSnapshot> productIterator=iterable.iterator();
               while (productIterator.hasNext()){
                   productObservableArrayList.add(productIterator.next().getValue(Product.class));
               }

                if(view!=null){
                    view.hideProgressBar();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                if(view!=null){
                    view.hideProgressBar();
                    view.showMessage(databaseError.getMessage());
                }


            }
        });



    }

    public int getCount(@NonNull ArrayList<Product> products){
        if(products!=null){
            return products.size();
        }
        return 0;
    }

    public void onProductSelect(Product product){

        view.showDetailsFragment(product);

    }

    public void onSignOut(View v){

        FirebaseAuth.getInstance().signOut();
        view.finish();
        ModuleMaster.navigateToPreloginActivity(v.getContext());

    }








}
