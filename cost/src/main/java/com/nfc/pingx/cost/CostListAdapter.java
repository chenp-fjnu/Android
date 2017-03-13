package com.nfc.pingx.cost;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenp_fjnu on 2017/3/5.
 */

public class CostListAdapter extends Adapter<CostViewHolder> {
    public List<CostModel> mList = new ArrayList<>();
    public CostListAdapter(List<CostModel> list){
        mList = list;
    }

    @Override
    public CostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.list_item, parent, false);
        return new CostViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CostViewHolder holder, int position) {
        holder.getBinding().setVariable(BR.costModel, get(position));
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public CostModel get(int position) {
        return mList.get(position);
    }
}
