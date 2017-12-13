package com.example.zct11.course.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zct11 on 2017/11/13.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String db="course.db";
    private static final String table_name="down";
    private static final String history_name="history";
    private static final int version=2;

    public DBHelper(Context context) {
        super(context, db, null, version);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(new StringBuilder().append("create table if not exists ").append(table_name).append("(id integer primary key,path varchar,name varchar,size varchar)").toString());
        db.execSQL(new StringBuilder().append("create table if not exists ").append(history_name).append("(id integer primary key,url varchar,title varchar,img varchar)").toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     db.execSQL(new StringBuilder().append("drop table if exsits ").append(table_name).toString());
     db.execSQL(new StringBuilder().append("drop table if exsits ").append(history_name).toString());
     this.onCreate(db);
    }
}
