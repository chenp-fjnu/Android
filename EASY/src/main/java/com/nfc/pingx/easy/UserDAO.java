package com.nfc.pingx.easy;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.nfc.pingx.common.DAO.IDAO;
import com.nfc.pingx.common.db.SQLiteHelper;

import java.sql.SQLException;

/**
 * Created by chenp_fjnu on 2017/3/11.
 */


public class UserDAO implements IDAO {
    private Context context;
    private Dao<UserModel, Integer> daoOpe;
    private SQLiteHelper helper;

    public UserDAO(Context context)
    {
        this.context = context;
        try
        {
            helper = EASYSQLiteHelper.getHelper(context);
            daoOpe = helper.getDao(UserModel.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 增加一个用户
     *
     * @param userModel
     * @throws SQLException
     */
    public void add(UserModel userModel)
    {
        try
        {
            daoOpe.create(userModel);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public UserModel get(int id)
    {
        try
        {
            return daoOpe.queryForId(id);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
