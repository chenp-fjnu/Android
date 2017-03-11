package com.nfc.ping.common.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;

/**
 * Created by chenp_fjnu on 2017/3/11.
 */

@DatabaseTable(tableName = "tb_user")
public class User implements IModel {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "name")
    private String name;

    @ForeignCollectionField
    private Collection<EASYProgram> easyPrograms;

    public Collection<EASYProgram> getEasyProgram()
    {
        return easyPrograms;
    }

    public void setEASYProgram(Collection<EASYProgram> easyPrograms)
    {
        this.easyPrograms = easyPrograms;
    }

    public User()
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "User [id=" + id + ", name=" + name + ", easyPrograms=" + easyPrograms
                + "]";
    }
}
