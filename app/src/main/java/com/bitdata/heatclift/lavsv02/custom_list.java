package com.bitdata.heatclift.lavsv02;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Heatclift on 28/02/2018.
 */

public class custom_list extends Fragment
{
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1,container,false);
        DatabaseHelper helper = new DatabaseHelper(getActivity());
        SQLiteDatabase db= helper.getWritableDatabase();
        helper.update("1","sample",db);
        Cursor cur = helper.retrieve(db);
        ListView lv = view.findViewById(R.id.lv);
        List<String[]> data = new ArrayList<>();
        while(cur.moveToNext())
        {
            data.add(new String[]{cur.getString(0),cur.getString(1)});
        }
        cur.close();

        final mycustomadapter adapter= new mycustomadapter(data,getActivity());
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), ((String[])adapter.getItem(position))[1], Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
