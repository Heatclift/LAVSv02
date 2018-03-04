package com.bitdata.heatclift.lavsv02;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Heatclift on 01/03/2018.
 */

public class no_loan extends Fragment
{
    View view;
    FragmentTransaction trans;
    Fragment frag;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.no_loan,container,false);
        TextView txttap = (TextView)view.findViewById(R.id.txttaphere);
        txttap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frag = new apploan();
                FragmentManager frogman = getFragmentManager();

                trans = frogman.beginTransaction();
                trans.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                trans.replace(R.id.content,frag);
                trans.commit();
            }
        });
        return view;
    }
}
