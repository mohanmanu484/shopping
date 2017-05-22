package com.project.shopping.shoppingapp.Utility;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.project.shopping.shoppingapp.R;
import com.project.shopping.shoppingapp.adapter.BaseAdapter;
import com.project.shopping.shoppingapp.adapter.CartAdapter;
import com.project.shopping.shoppingapp.adapter.HomeAdapter;
import com.project.shopping.shoppingapp.custumviews.SpacesItemDecoration;
import com.project.shopping.shoppingapp.viewcallbacks.MainViewModel;

import java.util.ArrayList;

/**
 * Created by mohan on 21/05/17.
 */

public class BindingUtil {



    @BindingAdapter({"listItem","handler"})
    public static <T>void setAdapter(RecyclerView recyclerView, ArrayList<T> listItems, MainViewModel mainViewModel){

        BaseAdapter adapter= (BaseAdapter) recyclerView.getAdapter();
        if (adapter==null){
            GridLayoutManager manager = new GridLayoutManager(recyclerView.getContext(),2);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(manager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.addItemDecoration(new SpacesItemDecoration(16));
            recyclerView.setAdapter(new HomeAdapter(listItems,mainViewModel));
        }else {

            adapter.updateList(listItems);
            adapter.notifyDataSetChanged();
        }



    }

    @BindingAdapter({"cartItem","handler"})
    public static <T>void setCartAdapter(RecyclerView recyclerView, ArrayList<T> listItems, MainViewModel mainViewModel){

        BaseAdapter adapter= (BaseAdapter) recyclerView.getAdapter();
        if (adapter==null){
            LinearLayoutManager manager = new LinearLayoutManager(recyclerView.getContext());
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(manager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(new CartAdapter(listItems,mainViewModel));
        }else {

            adapter.updateList(listItems);
            adapter.notifyDataSetChanged();
        }



    }




    @BindingAdapter("imageUrl")
    public static void setImage(ImageView imageView,String url){

        Context context = imageView.getContext();
        Resources res = context.getResources();
        Drawable drawable = res.getDrawable(R.drawable.ic_image_placeholder_wrapper);
        Glide.with(context).load(url).placeholder(drawable).centerCrop()
                .into(imageView);

    }



}
