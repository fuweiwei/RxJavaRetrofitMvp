package com.veer.rx.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 描述
 *
 * @author Veer
 * @email 276412667@qq.com
 * @date 18/8/16
 */
@Entity
public class Book {
    @Id(autoincrement = true)
    private long id;
    private String name;
    private String author;
    private int count;
    @Generated(hash = 735001580)
    public Book(long id, String name, String author, int count) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.count = count;
    }
    @Generated(hash = 1839243756)
    public Book() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getCount() {
        return this.count;
    }
    public void setCount(int count) {
        this.count = count;
    }

}
