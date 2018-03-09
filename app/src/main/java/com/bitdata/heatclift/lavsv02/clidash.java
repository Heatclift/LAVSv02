package com.bitdata.heatclift.lavsv02;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



public class clidash extends Fragment {
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_clidash,container,false);
        DatabaseHelper db = new DatabaseHelper(getActivity());
        TextView txtcurbal = (TextView)view.findViewById(R.id.txtcubal);
        TextView txtprinamount = (TextView)view.findViewById(R.id.txtprinamaount);
        TextView txtintamaout = (TextView)view.findViewById(R.id.txtintamount);
        TextView txtgross = (TextView)view.findViewById(R.id.txtgrossamount);
        TextView txtduedate = (TextView)view.findViewById(R.id.txtdate);
        TextView txtpastdue = (TextView)view.findViewById(R.id.txtpdue);
        TextView txtcurdue = (TextView)view.findViewById(R.id.txtcdue);
        TextView txtfsettle = (TextView)view.findViewById(R.id.txtfsettlement);
        TextView indec =(TextView)view.findViewById(R.id.txtindec);
        Formulas formu = new Formulas();
        SQLiteDatabase rdb = db.getReadableDatabase();
        Cursor cur = db.retrieve(rdb);
        Cursor cursoa = db.retrieveSoa(rdb);

        Calendar c = Calendar.getInstance();//generate date for soa


        Date cdate = c.getTime();
        c.add(Calendar.DATE,1);
        Date tdate = c.getTime();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat storeddate = new SimpleDateFormat("yyyyMMdd");
        String date =df.format(cdate) ;
        String Tdate =df.format(tdate) ;
        String datenow = date;
        String datetom = Tdate;
        Log.e("datenow",datenow);
        Log.e("datetom",datetom);
        if(cur.moveToFirst()){

            txtcurbal.setText(cur.getString(4));
            txtprinamount.setText(cur.getString(2));
            txtintamaout.setText(String.valueOf(formu.interest(cur.getInt(2),cur.getInt(5),cur.getInt(3))));
            txtgross.setText(cur.getString(4));


        }
        double pastdue = db.getpastdue(storeddate.format(cdate),rdb);
        while (cursoa.moveToNext()){

            if (cursoa.getString(cursoa.getColumnIndex(DatabaseHelper.tbl_soa_date)).equals(datenow)){
                indec.setText("Current Due");
                txtduedate.setText(datenow);
                txtpastdue.setText(String.valueOf(pastdue));
                txtcurdue.setText(String.valueOf(cursoa.getDouble(cursoa.getColumnIndex(DatabaseHelper.tbl_soa_amount))));
                txtfsettle.setText(String.valueOf(cursoa.getDouble(cursoa.getColumnIndex(DatabaseHelper.tbl_soa_amount))+pastdue));
                break;
            }
            else if (cursoa.getString(cursoa.getColumnIndex(DatabaseHelper.tbl_soa_date)).equals(datetom)){
                indec.setText("Tomorrow's Due");
                txtduedate.setText(datetom);
                txtpastdue.setText(String.valueOf(pastdue));
                txtcurdue.setText(String.valueOf(cursoa.getDouble(cursoa.getColumnIndex(DatabaseHelper.tbl_soa_amount))));
                txtfsettle.setText(String.valueOf(cursoa.getDouble(cursoa.getColumnIndex(DatabaseHelper.tbl_soa_amount))+pastdue));
            }
        }
        return view;

    }
}
