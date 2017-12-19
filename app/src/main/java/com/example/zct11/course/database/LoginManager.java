package com.example.zct11.course.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.zct11.course.bean.Download;
import com.example.zct11.course.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zct11 on 2017/11/13.
 */

public class LoginManager {
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private static final String tbname = "login";
    private static final String KEY_ROWID = "id";

    public LoginManager(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }


    public void insert(User user) {
        db.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("username", user.getUsername());
            contentValues.put("password", user.getPassword());
            db.insert(tbname, "id", contentValues);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

    }

    public boolean checked(String username, String password) {
        String sql="select * from login where username=? and password=?";
        Cursor cursor=db.rawQuery(sql, new String[]{username,password});
        if(cursor.moveToFirst()==true){
            cursor.close();
            return false;
        }
        return true;
    }


    public boolean delete(String id) {
        return db.delete(tbname, KEY_ROWID + "=" + id, null) > 0;
    }
}
