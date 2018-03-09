package com.bitdata.heatclift.lavsv02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Heatclift on 28/02/2018.
 */

public class mycustomadapter extends BaseAdapter {

    List<String[]> data;
    View view;
    Context mcX;
    LayoutInflater inflate;
    //mao ni siya sa pag create ug adapter para sa listview
    public mycustomadapter(List<String[]> data, Context mcX) {
        this.data = data;
        this.mcX = mcX;
        inflate = (LayoutInflater)mcX.getSystemService(Context.LAYOUT_INFLATER_SERVICE);// mao ni siya ang kailangan para makuha si layoutinflater
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        view = inflate.inflate(R.layout.custom_list,null);
        TextView myid = (TextView)view.findViewById(R.id.txtdate);
        TextView mydata = (TextView)view.findViewById(R.id.txtam); // dri nimo iformat imong data sa listview
        TextView state = (TextView)view.findViewById(R.id.txtstate);
        String row[] = data.get(position);
        myid.setText(row[0]);
        mydata.setText(row[1]);
        state.setText(row[2]);
        return view;
    }
}
