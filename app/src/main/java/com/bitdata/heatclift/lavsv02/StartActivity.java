package com.bitdata.heatclift.lavsv02;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db= helper.getWritableDatabase();



        Button btnlogin=(Button)findViewById(R.id.btnlogin);
        final TextView copywrite = (TextView)findViewById(R.id.txtcopywrite);
        final RelativeLayout rl = (RelativeLayout)findViewById(R.id.rlcontrols);
        Animation slide = AnimationUtils.loadAnimation(this,R.anim.slidein);

        final Animation slideside = AnimationUtils.loadAnimation(this,R.anim.slideinside);
        rl.startAnimation(slide);
        slide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                copywrite.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                copywrite.startAnimation(slideside);
                copywrite.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(StartActivity.this, DashBoardActivity.class);
                startActivity(i);
            }
        });
        /*Cursor cur = helper.retrieve(db);
        while(cur.moveToNext())
        {
            Log.i("Sample Data",cur.getString(1));
        }
        cur.close();
        */
    }
}
