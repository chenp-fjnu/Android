package com.nfc.ping.common.DAO;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.nfc.ping.common.db.SQLiteHelper;
import com.nfc.ping.common.model.EASYProgram;
import com.nfc.ping.common.model.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by chenp_fjnu on 2017/3/11.
 */

public class EASYProgramDAO implements IDAO {
    private Dao<EASYProgram, Integer> daoOpe;
    private SQLiteHelper helper;

    @SuppressWarnings("unchecked")
    public EASYProgramDAO(Context context)
    {
        try
        {
            helper = SQLiteHelper.getHelper(context);
            daoOpe = helper.getDao(EASYProgram.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 添加一个easyProgram
     *
     * @param easyProgram
     */
    public void add(EASYProgram easyProgram)
    {
        try
        {
            daoOpe.create(easyProgram);
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
    public EASYProgram getEasyProgramWithUser(int id)
    {
        EASYProgram easyProgram = null;
        try
        {
            easyProgram = daoOpe.queryForId(id);
            helper.getDao(User.class).refresh(easyProgram.getUser());

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return easyProgram;
    }

    /**
     * 通过Id得到一篇Program
     *
     * @param id
     * @return
     */
    public EASYProgram get(int id)
    {
        EASYProgram easyProgram = null;
        try
        {
            easyProgram = daoOpe.queryForId(id);

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return easyProgram;
    }

    /**
     * 通过UserId获取所有的easyProgram
     *
     * @param userId
     * @return
     */
    public List<EASYProgram> listByUserId(int userId)
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
