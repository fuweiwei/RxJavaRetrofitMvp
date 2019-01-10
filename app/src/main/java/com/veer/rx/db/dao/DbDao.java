package com.veer.rx.db.dao;

import android.database.sqlite.SQLiteDatabase;

import com.veer.rx.db.greendao.DaoSession;

/**
 * 数据库操作基类
 * @author Veer
 * @email 276412667@qq.com
 * @date 18/8/16
 */

public class DbDao {
    private volatile DbHelper mDbHelper;

    public DbDao(){
        if(mDbHelper == null){
            synchronized (DbDao.class){
                //双重效验
                if(mDbHelper == null){
                    mDbHelper = new DbHelper();
                }
            }
        }
    }

    /**
     * 获取一个数据库
     */
    protected SQLiteDatabase getDataBase(){
        SQLiteDatabase db = null;
        if (mDbHelper != null) {
            db= mDbHelper.getDb();
        }
        return db;
    }
    /**
     * 获取一个数据库
     */
    protected void closeDb(){
        if (mDbHelper != null) {
           mDbHelper.closeDb();
        }
    }

    /**
     * 获取一个DaoSession
     */
    protected DaoSession getDaoSession(){
        DaoSession daoSession = null;
        if (mDbHelper != null) {
            daoSession= mDbHelper.getDaoSession();
        }
        return daoSession;
    }
}
