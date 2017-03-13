package com.nfc.pingx.cost;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by chenp_fjnu on 2017/3/13.
 */

public  class CostViewHolder extends RecyclerView.ViewHolder{
    private ViewDataBinding binding;
    public CostViewHolder(ViewDataBinding viewDataBinding) {
        super(viewDataBinding.getRoot());
        binding = viewDataBinding;
        binding.executePendingBindings();
    }
    public ViewDataBinding getBinding() {
        return this.binding;
    }
}
