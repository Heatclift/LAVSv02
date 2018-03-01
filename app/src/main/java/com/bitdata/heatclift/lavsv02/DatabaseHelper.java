package com.bitdata.heatclift.lavsv02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Heatclift on 28/02/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String dbname="test.db";
    public static final int version=1;

    public static final String tbl_name = "table1";
    public static final String tbl_COL1 = "col1";
    public static final String tbl_COL2= "col2";
    public DatabaseHelper(Context context) {
        super(context, dbname, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+tbl_name+" ("+tbl_COL1+" integer primary key autoincrement,"+
                                            tbl_COL2+" String)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+tbl_name);
        onCreate(db);
    }

    public void insertData(String data,SQLiteDatabase db)
    {
        ContentValues val = new ContentValues();
        val.put(tbl_COL2,data);
        db.insert(tbl_name,null,val);
    }

    public void update(String key,String data,SQLiteDatabase db)
    {
        ContentValues val = new ContentValues();
        val.put(tbl_COL2,data);
        db.update(tbl_name,val,tbl_COL1+"=?",new String[]{key});
    }

    public Cursor retrieve(SQLiteDatabase db)
    {
        Cursor cur = db.rawQuery("select * from "+tbl_name,null);
        return cur;
    }
}
