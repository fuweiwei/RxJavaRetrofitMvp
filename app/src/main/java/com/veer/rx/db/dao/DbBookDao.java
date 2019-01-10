package com.veer.rx.db.dao;

import com.veer.rx.db.entity.Book;
import com.veer.rx.db.greendao.BookDao;

/**
 * 书本信息表 操作
 * @author Veer
 * @email 276412667@qq.com
 * @date 18/8/16
 */

public class DbBookDao extends DbDao {
    private volatile static DbBookDao mDbBookDao;
    private BookDao mBookDao;

    private DbBookDao(){
        super();
        mBookDao = getDaoSession().getBookDao();

    }
    public static DbBookDao getInstance(){
        if(mDbBookDao == null){
            synchronized (DbBookDao.class){
                if(mDbBookDao == null){
                    mDbBookDao = new DbBookDao();
                }
            }

        }
        return mDbBookDao;
    }
    /**
     * 添加书本信息
     * @param book
     */
    public void addBook(Book book){
        mBookDao.insert(book);
    }
}
