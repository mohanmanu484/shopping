package com.project.shopping.shoppingapp.fragments;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.shopping.shoppingapp.R;
import com.project.shopping.shoppingapp.Utility.ModuleMaster;
import com.project.shopping.shoppingapp.ViewModel.HomeViewModel;
import com.project.shopping.shoppingapp.activity.HomeActivity;
import com.project.shopping.shoppingapp.databinding.ActivityNavDrawBinding;
import com.project.shopping.shoppingapp.model.Product;
import com.project.shopping.shoppingapp.model.User;
import com.project.shopping.shoppingapp.viewcallbacks.HomeViewCallback;
import com.project.shopping.shoppingapp.viewcallbacks.MainViewModel;

/**
 * Created by mohan on 21/05/17.
 */

public class HomeFragment extends BaseFragment implements HomeViewCallback.View, NavigationView.OnNavigationItemSelectedListener {


    ActivityNavDrawBinding activityNavDrawBinding;
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        activityNavDrawBinding = DataBindingUtil.inflate(inflater, R.layout.activity_nav_draw, container, false);
        activityNavDrawBinding.setModel(homeViewModel);

        HomeActivity homeActivity = (HomeActivity) getActivity();
        homeActivity.setSupportActionBar(activityNavDrawBinding.includeLayout.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), activityNavDrawBinding.drawerLayout, activityNavDrawBinding.includeLayout.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        activityNavDrawBinding.drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        activityNavDrawBinding.navView.setNavigationItemSelectedListener(this);
        setheaderData();
        return activityNavDrawBinding.getRoot();
    }

    private void setheaderData() {
        View header = activityNavDrawBinding.navView.getHeaderView(0);
        final TextView name = (TextView) header.findViewById(R.id.userName);
        TextView email = (TextView) header.findViewById(R.id.userEmail);
        email.setText(firebaseUser.getEmail());
        firebaseDatabase.getReference("users").child(firebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {
                    User user = dataSnapshot.getValue(User.class);
                    name.setText(user.getName());
                } catch (Exception e) {

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    HomeViewModel homeViewModel = new HomeViewModel();

    @Override
    public Context getActivityContext() {
        return getActivity();
    }

    @Override
    public Activity getActivityInstance() {
        return getActivity();
    }

    @Override
    public MainViewModel getViewModel() {
        return homeViewModel;
    }

    @Override
    public void showDetailsFragment(Product product) {
        HomeActivity homeActivity = (HomeActivity) getActivity();
        homeActivity.showDetailsFragment(product);
    }

    @Override
    public void finish() {
        getActivity().finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_cart) {
            // Handle the camera action
            ModuleMaster.navigateToCart(getActivity());
        } else if(id==R.id.signOut){
            onSignOut();
        }
        activityNavDrawBinding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onSignOut(){

        FirebaseAuth.getInstance().signOut();
        ModuleMaster.navigateToPreloginActivity(getActivity());
        finish();

    }
}
