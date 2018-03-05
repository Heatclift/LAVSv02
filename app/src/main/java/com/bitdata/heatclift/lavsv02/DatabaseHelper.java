package com.bitdata.heatclift.lavsv02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;

/**
 * Created by Heatclift on 28/02/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String dbname="test.db";
    public static final int version=1;
//TBL LOANS
    public static final String tbl_LOANS = "loans";
    public static final String tbl_LOANS_COL1 = "ID";
    public static final String tbl_LOANS_COL2= "CLIID";
    public static final String tbl_LOANS_COL3= "LOAN_AM";
    public static final String tbl_LOANS_COL4= "TERM";
    public static final String tbl_LOANS_COL5= "CURBAL";
    public static final String tbl_LOANS_COL6= "INTEREST";
    public static final String tbl_LOANS_COL7= "APPL_DATE";
    public static final String tbl_LOANS_STATE = "STATE";
    public static final String tbl_LOANS_DUEDATE = "DUE_DATE";
    public static final String tbl_LOANS_COL10= "DAYS";

    //END OF TBL LOANS

    //tbl client
    public static final String tbl_CLIENTS = "client";
    public static final String tbl_CLIENTS_ID = "ID";
    public static final String tbl_CLIENTS_USERNAME = "USERNAME";
    public static final String tbl_CLIENTS_PASSWOD= "PASWORD";
    public static final String tbl_CLIENTS_FULLNAME= "FULLNAME";
    public static final String tbl_CLIENTS_BIRTHDATE= "BDATE";
    public static final String tbl_CLIENTS_CONNO= "CONNO";
    public static final String tbl_CLIENTS_EMAIL= "EMAIL";
    public static final String tbl_CLIENTS_ADDRESS= "ADDR";

    //end tbl client

    //tbl soa

    public  static final  String tbl_soa = "soa";
    public static final String tbl_soa_id = "ID";
    public static final String tbl_soa_date = "DATE";
    public  static final String tbl_soa_loan ="LOAN";
    public static final  String tbl_soa_state = "STATE";

    //end soa

    public DatabaseHelper(Context context) {
        super(context, dbname, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+tbl_LOANS+" ("+tbl_LOANS_COL1+" integer primary key autoincrement,"+
                tbl_LOANS_COL2+" String,"+tbl_LOANS_COL3+" String,"+tbl_LOANS_COL4+" String,"+tbl_LOANS_COL5+" String,"+tbl_LOANS_COL6+" String,"+tbl_LOANS_COL7+" String,"+ tbl_LOANS_STATE +" String,"+ tbl_LOANS_DUEDATE +" text,"+tbl_LOANS_COL10+" String"+")");
        db.execSQL("CREATE TABLE "+tbl_CLIENTS+" ("+tbl_CLIENTS_ID+" integer primary key autoincrement,"+
                tbl_CLIENTS_USERNAME+" String,"+tbl_CLIENTS_PASSWOD+" String,"+tbl_CLIENTS_FULLNAME+" String,"+tbl_CLIENTS_BIRTHDATE+" String,"+tbl_CLIENTS_CONNO+" String,"+tbl_CLIENTS_EMAIL+" String,"+tbl_CLIENTS_ADDRESS+" String"+")");
        db.execSQL("CREATE TABLE "+tbl_soa+" ("+tbl_soa_id+" integer primary key autoincrement,"+
                tbl_CLIENTS_USERNAME+" String,"+tbl_soa_date+" String,"+tbl_soa_loan+" String,"+tbl_soa_state+" String"+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+tbl_LOANS);
        db.execSQL("DROP TABLE IF EXISTS "+tbl_CLIENTS);
        db.execSQL("DROP TABLE IF EXISTS "+tbl_soa);
        onCreate(db);
    }
    /*public int pastdue(){

    }*/
    public String log_in(String username,String password, SQLiteDatabase db){/////working with it
        Cursor cur = db.rawQuery("Select * from "+ tbl_CLIENTS + " WHERE " + tbl_CLIENTS_USERNAME + " = '"+username+"' AND " + tbl_CLIENTS_PASSWOD + " = '"+password+"'", null);
        if(cur.moveToFirst())
        {
            return cur.getString(0);
        }
        return "";

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
        val.put(tbl_LOANS_STATE,state);
        val.put(tbl_LOANS_DUEDATE,due_date);
        val.put(tbl_LOANS_COL10,days);

        db.insert(tbl_LOANS,null,val);
    }
    public void insertDataInclients(String user,String pass,String fullname,String bdate,String conno,String email,SQLiteDatabase db)
    {
        ContentValues val = new ContentValues();
        val.put(tbl_CLIENTS_USERNAME,user);
        val.put(tbl_CLIENTS_PASSWOD,pass);
        val.put(tbl_CLIENTS_FULLNAME,fullname);
        val.put(tbl_CLIENTS_BIRTHDATE,bdate);
        val.put(tbl_CLIENTS_CONNO,conno);
        val.put(tbl_CLIENTS_EMAIL,email);
        val.put(tbl_CLIENTS_ADDRESS,"unveri");

        db.insert(tbl_CLIENTS,null,val);
    }

    public void update(String key,String data,SQLiteDatabase db)
    {
        ContentValues val = new ContentValues();
        val.put(tbl_LOANS_COL2,data);
        db.update(tbl_LOANS,val,tbl_LOANS_COL1+"=?",new String[]{key});
    }
    public void updatestate(String id,String data,SQLiteDatabase db)
    {
        ContentValues val = new ContentValues();
        val.put(tbl_LOANS_STATE,data);
        db.update(tbl_LOANS,val,tbl_LOANS_COL1+"=?",new String[]{id});
    }
    public Cursor retrieve(SQLiteDatabase db)
    {
        Cursor cur = db.rawQuery("select * from "+tbl_LOANS+" where "+tbl_LOANS_COL2+" = "+store_class.uid,null);
        if(cur.moveToFirst()){
            store_class.lid = cur.getString(1);
        }
        return cur;
    }

    public Cursor getcurloan(SQLiteDatabase db){
        Cursor cur = db.rawQuery("select "+tbl_LOANS_COL5+", " + tbl_LOANS_COL6 + ", "+ tbl_LOANS_COL4 + "from "+ tbl_LOANS,null);
        return cur;
    }

    public void insertDataInloan(String uid, Editable text, String s, Editable text1, String s1) {
    }

    /**
     * Created by Heatclift on 01/03/2018.
     */

    public static class noloan {
    }
}
