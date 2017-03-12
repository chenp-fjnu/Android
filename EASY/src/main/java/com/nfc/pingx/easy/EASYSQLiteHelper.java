package com.nfc.pingx.easy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.nfc.pingx.common.db.SQLiteHelper;

import java.sql.SQLException;

/**
 * Created by chenp_fjnu on 2017/3/11.
 */

public class EASYSQLiteHelper extends SQLiteHelper
{

    private EASYSQLiteHelper(Context context)
    {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase database,
                         ConnectionSource connectionSource)
    {
        try
        {
            TableUtils.createTableIfNotExists(connectionSource, EASYProgramModel.class);
            TableUtils.createTableIfNotExists(connectionSource, UserModel.class);
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
            TableUtils.dropTable(connectionSource, EASYProgramModel.class, true);
            TableUtils.dropTable(connectionSource, UserModel.class, true);
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
            synchronized (EASYSQLiteHelper.class) {
                if (instance == null) {
                    instance = new EASYSQLiteHelper(context);
                }
            }
        }

        return instance;
    }
}
