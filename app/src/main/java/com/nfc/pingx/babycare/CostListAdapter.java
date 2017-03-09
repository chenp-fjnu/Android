package com.nfc.pingx.babycare;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by chenp_fjnu on 2017/3/5.
 */

public class CostListAdapter extends Adapter<CostListAdapter.CostViewHolder> {
    public List<CostBean> mList;
    public CostListAdapter(List<CostBean> list){
        mList = list;
    }

    @Override
    public CostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new CostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CostViewHolder holder, int position) {
        CostBean model = mList.get(position);
        holder.mTvCostTitle.setText(model.costTitle);
        holder.mTvCostDate.setText(model.costDate);
        holder.mTvCostMoney.setText(model.costMoney);
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }
    public List<CostBean> getList() {
        return this.mList;
    }
    public void addItem(CostBean content, int position) {
        mList.add(position, content);
        notifyItemInserted(position);
    }
    public void removeItem(CostBean model) {
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
