package com.bitdata.heatclift.lavsv02;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Heatclift on 28/02/2018.
 */

public class payment_sched_fragment extends Fragment
{
    View view;
    FragmentTransaction trans;
    Fragment frag;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.payment_sched,container,false);
        DatabaseHelper help = new DatabaseHelper(getActivity());
        SQLiteDatabase db = help.getReadableDatabase();
        ListView listsched = (ListView)view.findViewById(R.id.lstsched);

        List<String[]> data = new ArrayList<>();

        if (!store_class.lid.equals("")){
            Cursor cur = help.retrieveSoa(db);
            while(cur.moveToNext())
            {
                data.add(new String[]{cur.getString(2),cur.getString(6),cur.getString(5)});
            }
            cur.close();
            final mycustomadapter adapter= new mycustomadapter(data,getActivity());
            listsched.setAdapter(adapter);
            return view;
        }
        else {

            Toast.makeText(getActivity(),"no loan",Toast.LENGTH_LONG).show();
            return view;
        }

    }
}
