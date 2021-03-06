package com.bitdata.heatclift.lavsv02;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class DashBoardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DatabaseHelper db = new DatabaseHelper(this);

    FragmentTransaction trans;
    Fragment frag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




        ///inflate the first fragment for the first time
        homeload();

    }

     public void homeload(){
        SQLiteDatabase rdb = db.getReadableDatabase();


        if (store_class.lid == ""){
            frag = new no_loan();
            trans = getSupportFragmentManager().beginTransaction();
            trans.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
            trans.replace(R.id.content,frag);
            trans.commit();
        }
        else{
            if (!db.retrieveSoa(rdb).moveToFirst()){
                Cursor cur = db.getcurloan(rdb);
                if (cur.moveToFirst()){
                    //db.generatesoa(31,5,5000,rdb);
                    db.generatesoa(cur.getInt(cur.getColumnIndex(DatabaseHelper.tbl_LOANS_DAYS)),cur.getInt(cur.getColumnIndex(DatabaseHelper.tbl_LOANS_INTEREST)),cur.getInt(cur.getColumnIndex(DatabaseHelper.tbl_LOANS_LOAN_AM)),rdb);
                }
            }

            frag = new clidash();
            trans = getSupportFragmentManager().beginTransaction();
            trans.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
            trans.replace(R.id.content,frag);
            trans.commit();
        }
    }
    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dash_board, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            store_class.uid="";
            store_class.lid="";
            store_class.utype="";
            Intent i = new Intent(DashBoardActivity.this, StartActivity.class);
            startActivity(i);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            homeload();
        } else if (id == R.id.nav_gallery) {
           frag = new payment_sched_fragment();
            trans = getSupportFragmentManager().beginTransaction();
            trans.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
            if(!store_class.lid.equals("")){
            trans.replace(R.id.content,frag);
            trans.commit();
            }else
            {
                AlertDialog.Builder die = new AlertDialog.Builder(this);
                die.setMessage("Must apply a loan first to see access payment schedules");
                die.setPositiveButton("Apply a loan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        frag = new apploan();
                        trans = getSupportFragmentManager().beginTransaction();
                        trans.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                        trans.replace(R.id.content, frag);
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
                die.show();
            }

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            frag = new contact_us_fragment();
            trans = getSupportFragmentManager().beginTransaction();
            trans.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
            trans.replace(R.id.content,frag);
            trans.commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
