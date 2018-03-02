package com.bitdata.heatclift.lavsv02;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
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


        return view;

    }
}
