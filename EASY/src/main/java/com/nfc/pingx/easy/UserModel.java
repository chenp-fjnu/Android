package com.nfc.pingx.easy;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import com.nfc.pingx.common.model.IModel;

import java.util.Collection;

/**
 * Created by chenp_fjnu on 2017/3/11.
 */

@DatabaseTable(tableName = "tb_user")
public class UserModel implements IModel {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "name")
    private String name;

    @ForeignCollectionField
    private Collection<EASYProgramModel> easyPrograms;

    public Collection<EASYProgramModel> getEasyProgram()
    {
        return easyPrograms;
    }

    public void setEASYProgram(Collection<EASYProgramModel> easyPrograms)
    {
        this.easyPrograms = easyPrograms;
    }

    public UserModel()
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
        return "UserModel [id=" + id + ", name=" + name + ", easyPrograms=" + easyPrograms
                + "]";
    }
}
