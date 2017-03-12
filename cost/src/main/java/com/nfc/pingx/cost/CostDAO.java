package com.nfc.pingx.cost;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.nfc.pingx.common.DAO.IDAO;
import com.nfc.pingx.common.db.SQLiteHelper;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by chenp_fjnu on 2017/3/11.
 */

public class CostDAO implements IDAO {
    private Context context;
    private Dao<CostModel, Integer> daoOpe;
    private SQLiteHelper helper;

    public CostDAO(Context context)
    {
        this.context = context;
        try
        {
            helper = CostSQLiteHelper.getHelper(context);
            daoOpe = helper.getDao(CostModel.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 增加一个CostItem
     *
     * @param costModel
     * @throws SQLException
     */
    public void create(CostModel costModel)
    {
        try
        {
            daoOpe.create(costModel);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void delete(CostModel costModel){
        try
        {
            daoOpe.delete(costModel);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public CostModel get(int id)
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
    public List<CostModel> getAll()
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
