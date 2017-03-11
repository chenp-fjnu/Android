package com.nfc.ping.common.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.nfc.ping.common.model.Cost;
import com.nfc.ping.common.model.EASYProgram;
import com.nfc.ping.common.model.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenp_fjnu on 2017/3/11.
 */

public class SQLiteHelper extends OrmLiteSqliteOpenHelper
{
    private static final String DB_NAME = "baby_care.db";

    private Map<String, Dao> daos = new HashMap<String, Dao>();

    private SQLiteHelper(Context context)
    {
        super(context, DB_NAME, null, 1);
        getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase database,
                         ConnectionSource connectionSource)
    {
        try
        {
            TableUtils.createTableIfNotExists(connectionSource, Cost.class);
            TableUtils.createTableIfNotExists(connectionSource, User.class);
            TableUtils.createTableIfNotExists(connectionSource, EASYProgram.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          ConnectionSource connectionSource, int oldVersion, int newVersion)
    {
        try
        {
            TableUtils.dropTable(connectionSource, Cost.class, true);
            TableUtils.dropTable(connectionSource, User.class, true);
            TableUtils.dropTable(connectionSource, EASYProgram.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private static SQLiteHelper instance;
    /**
     * 单例获取该Helper
     *
     * @param context
     * @return
     */
    public static synchronized SQLiteHelper getHelper(Context context)
    {
        context = context.getApplicationContext();
        if (instance == null)
        {
            synchronized (SQLiteHelper.class) {
                if (instance == null) {
                    instance = new SQLiteHelper(context);
                }
            }
        }

        return instance;
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
