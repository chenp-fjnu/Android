package com.nfc.ping.common.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by chenp_fjnu on 2017/3/11.
 */
@DatabaseTable(tableName = "tb_easy_program")
public class EASYProgram implements IModel {
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
    private User user;

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

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    @Override
    public String toString()
    {
        return "EASYProgram [id=" + id + ", type=" + type + ", user=" + user
                + "]";
    }
}
