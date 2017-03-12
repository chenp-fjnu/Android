package com.nfc.pingx.common.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenp_fjnu on 2017/3/11.
 */

public abstract class SQLiteHelper extends OrmLiteSqliteOpenHelper
{
    private static final String DB_NAME = "pingx.db";

    private Map<String, Dao> daos = new HashMap<String, Dao>();

    public SQLiteHelper(Context context)
    {
        super(context, DB_NAME, null, 1);
        getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase database,
                         ConnectionSource connectionSource)
    {

    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          ConnectionSource connectionSource, int oldVersion, int newVersion)
    {

    }



    public synchronized Dao getDao(Class clazz) throws SQLException
    {
        Dao dao = null;
        String className = clazz.getSimpleName();

        if (daos.containsKey(className))
        {
            dao = daos.get(className);
        }
        if (dao == null)
        {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close()
    {
        super.close();

        for (String key : daos.keySet())
        {
            Dao dao = daos.get(key);
            dao = null;
        }
    }

}
