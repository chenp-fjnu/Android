package com.nfc.ping.common.DAO;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.nfc.ping.common.db.SQLiteHelper;
import com.nfc.ping.common.model.User;

import java.sql.SQLException;

/**
 * Created by chenp_fjnu on 2017/3/11.
 */


public class UserDAO implements IDAO{
    private Context context;
    private Dao<User, Integer> daoOpe;
    private SQLiteHelper helper;

    public UserDAO(Context context)
    {
        this.context = context;
        try
        {
            helper = SQLiteHelper.getHelper(context);
            daoOpe = helper.getDao(User.class);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 增加一个用户
     *
     * @param user
     * @throws SQLException
     */
    public void add(User user)
    {
		/*//事务操作
		TransactionManager.callInTransaction(helper.getConnectionSource(),
				new Callable<Void>()
				{

					@Override
					public Void call() throws Exception
					{
						return null;
					}
				});*/
        try
        {
            daoOpe.create(user);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public User get(int id)
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
