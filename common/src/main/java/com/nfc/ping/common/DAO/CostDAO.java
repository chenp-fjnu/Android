package com.nfc.ping.common.DAO;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.nfc.ping.common.db.SQLiteHelper;
import com.nfc.ping.common.model.Cost;
import com.nfc.ping.common.model.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by chenp_fjnu on 2017/3/11.
 */

public class CostDAO implements IDAO {
    private Context context;
    private Dao<Cost, Integer> daoOpe;
    private SQLiteHelper helper;

    public CostDAO(Context context)
    {
        this.context = context;
        try
        {
            helper = SQLiteHelper.getHelper(context);
            daoOpe = helper.getDao(Cost.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 增加一个CostItem
     *
     * @param cost
     * @throws SQLException
     */
    public void create(Cost cost)
    {
        try
        {
            daoOpe.create(cost);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void delete(Cost cost){
        try
        {
            daoOpe.delete(cost);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public Cost get(int id)
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
    /**
     * 获取所有的cost
     *
     * @return
     */
    public List<Cost> getAll()
    {
        try
        {
            return daoOpe.queryForAll();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
