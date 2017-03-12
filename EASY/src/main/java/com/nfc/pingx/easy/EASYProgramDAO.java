package com.nfc.pingx.easy;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.nfc.pingx.common.DAO.IDAO;
import com.nfc.pingx.common.db.SQLiteHelper;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by chenp_fjnu on 2017/3/11.
 */

public class EASYProgramDAO implements IDAO {
    private Dao<EASYProgramModel, Integer> daoOpe;
    private SQLiteHelper helper;

    @SuppressWarnings("unchecked")
    public EASYProgramDAO(Context context)
    {
        try
        {
            helper = EASYSQLiteHelper.getHelper(context);
            daoOpe = helper.getDao(EASYProgramModel.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 添加一个easyProgram
     *
     * @param easyProgramModel
     */
    public void add(EASYProgramModel easyProgramModel)
    {
        try
        {
            daoOpe.create(easyProgramModel);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 通过Id得到一个easyProgram
     *
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    public EASYProgramModel getEasyProgramWithUser(int id)
    {
        EASYProgramModel easyProgramModel = null;
        try
        {
            easyProgramModel = daoOpe.queryForId(id);
            helper.getDao(UserModel.class).refresh(easyProgramModel.getUserModel());

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return easyProgramModel;
    }

    /**
     * 通过Id得到一篇Program
     *
     * @param id
     * @return
     */
    public EASYProgramModel get(int id)
    {
        EASYProgramModel easyProgramModel = null;
        try
        {
            easyProgramModel = daoOpe.queryForId(id);

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return easyProgramModel;
    }

    /**
     * 通过UserId获取所有的easyProgram
     *
     * @param userId
     * @return
     */
    public List<EASYProgramModel> listByUserId(int userId)
    {
        try
        {
            return daoOpe.queryBuilder().where().eq("user_id", userId)
                    .query();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
