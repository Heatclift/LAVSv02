package com.bitdata.heatclift.lavsv02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Heatclift on 28/02/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String dbname="loans.db";
    public static final int version=2;
//TBL LOANS
    public static final String tbl_LOANS = "loans";
    public static final String tbl_LOANS_ID = "ID";
    public static final String tbl_LOANS_CLIID= "CLIID";
    public static final String tbl_LOANS_LOAN_AM = "LOAN_AM";
    public static final String tbl_LOANS_TERM = "TERM";
    public static final String tbl_LOANS_CURBAL = "CURBAL";
    public static final String tbl_LOANS_INTEREST = "INTEREST";
    public static final String tbl_LOANS_APPDATE = "APPL_DATE";
    public static final String tbl_LOANS_STATE = "STATE";
    public static final String tbl_LOANS_DUEDATE = "DUE_DATE";
    public static final String tbl_LOANS_DAYS = "DAYS";

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
    public static final String tbl_soa_stored_date = "STORED_DATE";
    public  static final String tbl_soa_loan ="LOAN";
    public  static final String tbl_soa_amount ="AMOUNT";
    public static final  String tbl_soa_state = "STATE";

    //end soa
    public DatabaseHelper(Context context) {
        super(context, dbname, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+tbl_LOANS+" ("+ tbl_LOANS_ID +" integer primary key autoincrement,"+
                tbl_LOANS_CLIID +" String,"+ tbl_LOANS_LOAN_AM +" String,"+ tbl_LOANS_TERM +" String,"+ tbl_LOANS_CURBAL +" String,"+ tbl_LOANS_INTEREST +" String,"+ tbl_LOANS_APPDATE +" String,"+ tbl_LOANS_STATE +" String,"+ tbl_LOANS_DUEDATE +" text,"+ tbl_LOANS_DAYS +" String"+")");
        db.execSQL("CREATE TABLE "+tbl_CLIENTS+" ("+tbl_CLIENTS_ID+" integer primary key autoincrement,"+
                tbl_CLIENTS_USERNAME+" String,"+tbl_CLIENTS_PASSWOD+" String,"+tbl_CLIENTS_FULLNAME+" String,"+tbl_CLIENTS_BIRTHDATE+" String,"+tbl_CLIENTS_CONNO+" String,"+tbl_CLIENTS_EMAIL+" String,"+tbl_CLIENTS_ADDRESS+" String"+")");
        db.execSQL("CREATE TABLE "+tbl_soa+" ("+tbl_soa_id+" integer primary key autoincrement,"+
                tbl_CLIENTS_USERNAME+" String,"+tbl_soa_date+" Date,"+tbl_soa_stored_date+" int,"+tbl_soa_loan+" String,"+tbl_soa_state+" String,"+tbl_soa_amount+" double"+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+tbl_LOANS);
        db.execSQL("DROP TABLE IF EXISTS "+tbl_CLIENTS);
        db.execSQL("DROP TABLE IF EXISTS "+tbl_soa);
        onCreate(db);
    }

    public void generatesoa(int num_of_days,int interest,int loanval,SQLiteDatabase db){
        Formulas formu = new Formulas();


        int term = num_of_days / 31;
        double dailypayment = formu.calcPayment(loanval,term,interest);

        Calendar cal  = Calendar.getInstance();

        for(int i =1;i <= num_of_days;i++){
            Calendar c = Calendar.getInstance();//generate date for soa
            c.add(Calendar.DATE,i);
            Date cdate = c.getTime();
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            SimpleDateFormat stored_date = new SimpleDateFormat("yyyyMMdd");
            String date =df.format(cdate) ;
            String datenow = date;
            ContentValues val = new ContentValues();
            val.put(tbl_soa_date,datenow);
            val.put(tbl_soa_loan,store_class.lid);
            val.put(tbl_soa_state,"unpaid");
            val.put(tbl_soa_stored_date,stored_date.format(cdate));
            val.put(tbl_soa_amount,dailypayment);
            Log.e("info",String.valueOf(dailypayment)+" "+String.valueOf(num_of_days));
            Log.i("info",String.valueOf(db.insert(tbl_soa,null,val)));

        }
    }
    public double getpastdue (String datenow,SQLiteDatabase db){
        Cursor cur = db.rawQuery("select sum("+tbl_soa_amount+") from "+tbl_soa+" where "+tbl_soa_stored_date+ " < "+datenow+" and "+tbl_soa_loan+" = "+store_class.lid,null);
        Log.e("stored date",datenow);

        double pastdue = 0;
        if (cur.moveToNext()){
            pastdue = cur.getDouble(0);
        }
        return pastdue;
    }
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
        val.put(tbl_LOANS_CLIID,cliid);
        val.put(tbl_LOANS_LOAN_AM,loan_am);
        val.put(tbl_LOANS_TERM,term);
        val.put(tbl_LOANS_CURBAL,curbal);
        val.put(tbl_LOANS_INTEREST,interest);
        val.put(tbl_LOANS_APPDATE,appl_date);
        val.put(tbl_LOANS_STATE,state);
        val.put(tbl_LOANS_DUEDATE,due_date);
        val.put(tbl_LOANS_DAYS,days);

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
        val.put(tbl_LOANS_CLIID,data);
        db.update(tbl_LOANS,val, tbl_LOANS_ID +"=?",new String[]{key});
    }
    public void updatestate(String id,String data,SQLiteDatabase db)
    {
        ContentValues val = new ContentValues();
        val.put(tbl_LOANS_STATE,data);
        db.update(tbl_LOANS,val, tbl_LOANS_ID +"=?",new String[]{id});
    }
    public Cursor retrieve(SQLiteDatabase db)
    {
        Cursor cur = db.rawQuery("select * from "+tbl_LOANS+" where "+ tbl_LOANS_CLIID +" = "+store_class.uid,null);
        if(cur.moveToFirst()){
            store_class.lid = cur.getString(1);
            Log.i("loanid",cur.getString(1));
        }
        return cur;
    }
    public Cursor retrieveSoa(SQLiteDatabase db){
        Cursor cur = db.rawQuery("select * from "+tbl_soa+" where "+tbl_soa_loan+" = "+store_class.lid,null);
        return cur;

    }
    public Cursor getcurloan(SQLiteDatabase db){
        Cursor cur = db.rawQuery("select * from "+ tbl_LOANS+" where "+ tbl_LOANS_CLIID +" = "+store_class.uid,null);
        return cur;

    }


}
