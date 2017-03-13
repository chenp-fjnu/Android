package com.nfc.pingx.cost;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.nfc.pingx.common.model.IModel;

/**
 * Created by chenp_fjnu on 2017/3/11.
 */
@DatabaseTable(tableName = "tb_cost")
public class CostModel extends BaseObservable implements IModel {
    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField
    public String costTitle;
    @DatabaseField
    public String costDate;
    @DatabaseField
    public String costMoney;
    public CostModel()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    @Bindable
    public String getCostTitle()
    {
        return costTitle;
    }

    public void setCostTitle(String costTitle)
    {
        this.costTitle = costTitle;
        notifyPropertyChanged(BR.costTitle);

    }
    @Bindable
    public String getCostDate()
    {
        return costDate;
    }

    public void setCostDate(String costDate)
    {
        this.costDate = costDate;
        notifyPropertyChanged(BR.costDate);
    }
    @Bindable
    public String getCostMoney()
    {
        return costMoney;
    }

    public void setCostMoney(String costMoney)
    {
        this.costMoney = costMoney;
        notifyPropertyChanged(BR.costMoney);
    }
    @Override
    public String toString()
    {
        return "CostModel [id=" + id + ", title=" + costTitle + ", date=" + costDate+ ", money=" + costMoney
                + "]";
    }
}
