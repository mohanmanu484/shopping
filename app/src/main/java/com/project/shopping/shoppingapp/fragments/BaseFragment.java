package com.project.shopping.shoppingapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.project.shopping.shoppingapp.Utility.Utility;
import com.project.shopping.shoppingapp.viewcallbacks.BaseViewCallback;
import com.project.shopping.shoppingapp.viewcallbacks.MainViewModel;

/**
 * Created by mohan on 21/05/17.
 */

public  abstract class BaseFragment extends Fragment implements BaseViewCallback{


    @Override
    public void showProgressBar() {
        if(isAdded())
           Utility.showProgressDialog(getActivity());
    }

    @Override
    public void hideProgressBar() {

        if(isAdded())
            Utility.hideProgressDialog();
    }

    @Override
    public void showMessage(String message) {
        if(isAdded())
            Utility.showSnackBar(getView(),getActivity(),message);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModel().onAtttach(this);
        setHasOptionsMenu(true);
    }

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getViewModel().onDestroy();
    }

    public abstract MainViewModel getViewModel();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            getActivity().onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    /*public void showProgressDialog(){

  }

  public void hodeProgressDialog(){

  }

  public void showMessage(String message){

  }*/
}
