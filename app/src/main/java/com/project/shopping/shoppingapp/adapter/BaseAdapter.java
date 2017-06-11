package com.project.shopping.shoppingapp.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by mohan on 21/05/17.
 */

public abstract class BaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public abstract  void updateList(List listItem);




}
