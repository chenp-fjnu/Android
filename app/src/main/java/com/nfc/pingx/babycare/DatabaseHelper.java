package com.nfc.pingx.babycare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

/**
 * Created by chenp_fjnu on 2017/3/5.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String COST_TITLE = "cost_title";
    public static final String BABYCARE = "babycare";
    public static final String COST_DATE = "cost_date";
    public static final String COST_MONEY = "cost_money";

    public DatabaseHelper(Context context){
        super(context, BABYCARE,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists "+ BABYCARE +"(" +
                        "id integer primary key," +
                        COST_TITLE +" varchar," +
                        COST_DATE + " varchar," +
                        COST_MONEY+ " varchar)");
    }
    public  void insertCost(CostBean costBean){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COST_TITLE, costBean.costTitle);
        cv.put(COST_DATE, costBean.costDate);
        cv.put(COST_MONEY, costBean.costMoney);
        database.insert(BABYCARE,null,cv);
    }
    public void deleteAllData(){
        SQLiteDatabase database = getWritableDatabase();
        database.delete(BABYCARE,null,null);
    }
    public Cursor getAllCostData(){
        SQLiteDatabase database = getWritableDatabase();
        return database.query(BABYCARE,null, null,null,null,null, COST_DATE +" DESC");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
