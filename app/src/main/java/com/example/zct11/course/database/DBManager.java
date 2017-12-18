package com.example.zct11.course.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.zct11.course.bean.Download;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zct11 on 2017/11/13.
 */

public class DBManager {
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private static final String tbname = "down";
    private static final String KEY_ROWID = "id";

    public DBManager(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }


    public void insert(Download download) {
        db.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("img", download.getImg());
            contentValues.put("path", download.getPath());
            contentValues.put("name", download.getName());
            contentValues.put("size", download.getSize());
            db.insert(tbname, "id", contentValues);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

    }
    public boolean checked(String name){
        boolean flag=true;
        Cursor c=db.rawQuery("SELECT count(*) FROM down WHERE img='"+name+"'", null);
        if(c.moveToNext()){
            flag=false;
        }
        return flag;
    }

    public List<Download> getData() {
        List<Download> downloads = new ArrayList<>();
        db.beginTransaction();
        try {
            Cursor cursor = db.rawQuery("select * from  down", null);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                Download download = new Download(cursor.getString(cursor.getColumnIndex("img")),cursor.getString(cursor.getColumnIndex("path")), cursor.getString(cursor.getColumnIndex("name")), cursor.getString(cursor.getColumnIndex("size")), cursor.getString(cursor.getColumnIndex("id")));
                downloads.add(download);
            }
            db.setTransactionSuccessful();
            return downloads;
        } finally {
            db.endTransaction();
        }

    }

    public boolean delete(String id) {
        return db.delete(tbname, KEY_ROWID + "=" + id, null) > 0;
    }
}
