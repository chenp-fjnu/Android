package com.nfc.pingx.easy;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.nfc.pingx.common.model.IModel;

/**
 * Created by chenp_fjnu on 2017/3/11.
 */
@DatabaseTable(tableName = "tb_easy_program")
public class EASYProgramModel implements IModel {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String type;
    @DatabaseField
    private String programItem;
    @DatabaseField
    private String programFromTime;
    @DatabaseField
    private String programEndTime;
    @DatabaseField(canBeNull = true, foreign = true, columnName = "user_id", foreignAutoRefresh = true)
    private UserModel userModel;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
    public String getProgramItem()
    {
        return programItem;
    }

    public void setProgramItem(String programItem)
    {
        this.programItem = programItem;
    }
    public String getProgramFromTime()
    {
        return programFromTime;
    }

    public void setProgramFromTime(String programFromTime)
    {
        this.programFromTime = programFromTime;
    }
    public String getProgramEndTime()
    {
        return programEndTime;
    }

    public void setProgramEndTime(String programEndTime)
    {
        this.programEndTime = programEndTime;
    }

    public UserModel getUserModel()
    {
        return userModel;
    }

    public void setUserModel(UserModel userModel)
    {
        this.userModel = userModel;
    }

    @Override
    public String toString()
    {
        return "EASYProgramModel [id=" + id + ", type=" + type + ", userModel=" + userModel
                + "]";
    }
}
