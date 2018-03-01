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
//TBL LOANS
    public static final String tbl_LOANS = "table1";
    public static final String tbl_LOANS_COL1 = "ID";
    public static final String tbl_LOANS_COL2= "CLIID";
    public static final String tbl_LOANS_COL3= "LOAN_AM";
    public static final String tbl_LOANS_COL4= "TERM";
    public static final String tbl_LOANS_COL5= "CURBAL";
    public static final String tbl_LOANS_COL6= "INTEREST";
    public static final String tbl_LOANS_COL7= "APPL_DATE";
    public static final String tbl_LOANS_COL8= "STATE";
    public static final String tbl_LOANS_COL9= "DUE_DATE";
    public static final String tbl_LOANS_COL10= "DAYS";

    //END OF TBL LOANS

    //tbl client
    public static final String tbl_CLIENTS = "table1";
    public static final String tbl_CLIENTS_ID = "ID";
    public static final String tbl_CLIENTS_USERNAME = "USERNAME";
    public static final String tbl_CLIENTS_PASSWOD= "PASWORD";
    public static final String tbl_CLIENTS_FULLNAME= "FULLNAME";
    public static final String tbl_CLIENTS_BIRTHDATE= "BDATE";
    public static final String tbl_CLIENTS_CONNO= "CONNO";
    public static final String tbl_CLIENTS_EMAIL= "EMAIL";
    public static final String tbl_CLIENTS_ADDRESS= "ADDR";

    //end tbl client

    public DatabaseHelper(Context context) {
        super(context, dbname, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+tbl_LOANS+" ("+tbl_LOANS_COL1+" integer primary key autoincrement,"+
                tbl_LOANS_COL2+" String,"+tbl_LOANS_COL3+" String,"+tbl_LOANS_COL4+" String,"+tbl_LOANS_COL5+" String,"+tbl_LOANS_COL6+" String,"+tbl_LOANS_COL7+" String,"+tbl_LOANS_COL8+" String,"+tbl_LOANS_COL9+" String,"+tbl_LOANS_COL10+" String"+")");
        db.execSQL("CREATE TABLE "+tbl_CLIENTS+" ("+tbl_CLIENTS_ID+" integer primary key autoincrement,"+
                tbl_CLIENTS_USERNAME+" String,"+tbl_CLIENTS_PASSWOD+" String,"+tbl_CLIENTS_FULLNAME+" String,"+tbl_CLIENTS_BIRTHDATE+" String,"+tbl_CLIENTS_CONNO+" String,"+tbl_CLIENTS_EMAIL+" String,"+tbl_CLIENTS_ADDRESS+" String"+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+tbl_LOANS);
        onCreate(db);
    }

    public void insertDataInloan(String cliid,String loan_am,String term,String curbal,String interest,String appl_date,String state,String due_date,String days,SQLiteDatabase db)
    {
        ContentValues val = new ContentValues();
        val.put(tbl_LOANS_COL2,cliid);
        val.put(tbl_LOANS_COL3,loan_am);
        val.put(tbl_LOANS_COL4,term);
        val.put(tbl_LOANS_COL5,curbal);
        val.put(tbl_LOANS_COL6,interest);
        val.put(tbl_LOANS_COL7,appl_date);
        val.put(tbl_LOANS_COL8,state);
        val.put(tbl_LOANS_COL9,due_date);
        val.put(tbl_LOANS_COL10,days);
        db.insert(tbl_LOANS,null,val);
    }
    public void insertDataInclients(String cliid,String loan_am,String term,String curbal,String interest,String appl_date,String state,String due_date,String days,SQLiteDatabase db)
    {
        ContentValues val = new ContentValues();
        val.put(tbl_CLIENTS_USERNAME,cliid);
        val.put(tbl_CLIENTS_PASSWOD,loan_am);
        val.put(tbl_CLIENTS_FULLNAME,term);
        val.put(tbl_CLIENTS_BIRTHDATE,curbal);
        val.put(tbl_CLIENTS_CONNO,interest);
        val.put(tbl_CLIENTS_EMAIL,appl_date);
        val.put(tbl_CLIENTS_ADDRESS,state);

        db.insert(tbl_LOANS,null,val);
    }

    public void update(String key,String data,SQLiteDatabase db)
    {
        ContentValues val = new ContentValues();
        val.put(tbl_LOANS_COL2,data);
        db.update(tbl_LOANS,val,tbl_LOANS_COL1+"=?",new String[]{key});
    }
    public void updatestate(String key,String data,SQLiteDatabase db)
    {
        ContentValues val = new ContentValues();
        val.put(tbl_LOANS_COL8,data);
        db.update(tbl_LOANS,val,tbl_LOANS_COL1+"=?",new String[]{key});
    }
    public Cursor retrieve(SQLiteDatabase db)
    {
        Cursor cur = db.rawQuery("select * from "+tbl_LOANS,null);
        return cur;
    }
}
