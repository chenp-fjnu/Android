package com.nfc.pingx.cost;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenp_fjnu on 2017/3/5.
 */

public class CostListAdapter extends Adapter<CostListAdapter.CostViewHolder> {
    public List<CostModel> mList = new ArrayList<>();
    public CostListAdapter(List<CostModel> list){
        mList = list;
    }

    @Override
    public CostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new CostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CostViewHolder holder, int position) {
        CostModel model = mList.get(position);
        holder.mTvCostTitle.setText(model.costTitle);
        holder.mTvCostDate.setText(model.costDate);
        holder.mTvCostMoney.setText(model.costMoney);
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }
    public List<CostModel> getList() {
        return this.mList;
    }
    public void addItem(CostModel content, int position) {
        mList.add(position, content);
        notifyItemInserted(position);
    }
    public void removeItem(CostModel model) {
        int position = mList.indexOf(model);
        mList.remove(position);
        notifyItemRemoved(position);
    }
    public  class CostViewHolder extends RecyclerView.ViewHolder{
        public TextView mTvCostTitle;
        public TextView mTvCostDate;
        public TextView mTvCostMoney;

        public CostViewHolder(View itemView) {
            super(itemView);
            mTvCostTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mTvCostDate = (TextView) itemView.findViewById(R.id.tv_date);
            mTvCostMoney = (TextView) itemView.findViewById(R.id.tv_cost);
        }
    }
}
