package com.example.zct11.course.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.zct11.course.bean.Download;
import com.example.zct11.course.bean.History;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zct11 on 2017/11/13.
 */

public class HistoryDBManager {
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private static final String history_name="history";
    private static final String KEY_ROWID="id";

    public HistoryDBManager(Context context) {
        dbHelper=new DBHelper(context);
        db=dbHelper.getWritableDatabase();
    }


    public void insert(History history){
        db.beginTransaction();
        try{
            ContentValues contentValues=new ContentValues();
            contentValues.put("url", history.getUrl());
            contentValues.put("title", history.getTitle());
            contentValues.put("img", history.getImg());
            db.insert(history_name,"id",contentValues);
            db.setTransactionSuccessful();
        }finally {
           db.endTransaction();
        }

    }

    public boolean checked(String path){
        boolean flag=true;
        Cursor c=db.rawQuery("SELECT count(*) FROM history WHERE url='"+path+"'", null);
       if(c.moveToNext()){
          flag=false;
       }
        return flag;
    }


    public List<History> getData(){
        List<History> histories=new ArrayList<>();
        db.beginTransaction();
        try{

            Cursor cursor=db.rawQuery("select * from  history",null);
            for(cursor.moveToFirst();!cursor.isAfterLast(); cursor.moveToNext()){

                History history=new History(cursor.getString(cursor.getColumnIndex("url")),cursor.getString(cursor.getColumnIndex("title")),cursor.getString(cursor.getColumnIndex("img")),cursor.getString(cursor.getColumnIndex("id")));
                histories.add(history);
            }
            db.setTransactionSuccessful();
            return histories;
        }finally {
            db.endTransaction();
        }

    }

    public boolean delete(String id){
        return db.delete(history_name,KEY_ROWID+"="+id,null)>0;
    }
}
