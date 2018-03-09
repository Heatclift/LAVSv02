package com.bitdata.heatclift.lavsv02;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



public class apploan extends Fragment{

    View view;
    FragmentTransaction trans;
    Fragment frag;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.apploan,container,false);
        Button btn_appl =(Button)view.findViewById(R.id.btnappl);
        final EditText rdt=(EditText)view.findViewById(R.id.edtloanam);
        final Spinner spnterm = (Spinner)view.findViewById(R.id.spnterm);
        final DatabaseHelper db = new DatabaseHelper(getActivity());
        final SQLiteDatabase rdb = db.getReadableDatabase();
        final AlertDialog.Builder die = new AlertDialog.Builder(getActivity());
        final String[] loan_amm = new String[1];
        final String[] term = {"1"};
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
        final String date = df.format(c);
        final String[] datenaow = {date};
        final String duedate =String.valueOf(date.indexOf(2,2)+ Integer.valueOf(term[0]));
        final String numdays =String.valueOf((Integer.valueOf(term[0]))*31);
        die.setMessage("Are you sure?");
        final Formulas formu = new Formulas();
        die.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), datenaow[0], Toast.LENGTH_SHORT).show();

                db.insertDataInloan(store_class.uid, loan_amm[0], term[0], String.valueOf(formu.gross(Double.valueOf(loan_amm[0]),Integer.valueOf(term[0]),5)),"5", datenaow[0],"pending",duedate,numdays,rdb);///unfinished
                frag = new loanpending();
                FragmentManager frogman = getFragmentManager();
                trans = frogman.beginTransaction();
                trans.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                trans.replace(R.id.content,frag);
                trans.commit();
                dialog.dismiss();
            }
        });
        die.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

            }
        });
        btn_appl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loan_amm[0] = rdt.getText().toString();
                term[0] = spnterm.getSelectedItem().toString();
                datenaow[0] =date;
                die.show();
            }
        });
        return view;
    }
}
