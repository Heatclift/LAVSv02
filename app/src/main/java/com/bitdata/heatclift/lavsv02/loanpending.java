package com.bitdata.heatclift.lavsv02;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Heatclift on 04/03/2018.
 */

public class loanpending extends android.support.v4.app.Fragment {
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.pendingloan,container,false);
        DatabaseHelper helper = new DatabaseHelper(getActivity());
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cur = helper.retrieve(db);
        store_class.lid = cur.getString(0);
        return view;
    }
}
