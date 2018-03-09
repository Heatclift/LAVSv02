package com.bitdata.heatclift.lavsv02;

import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final AlertDialog.Builder die = new AlertDialog.Builder(this);
        die.setMessage("The information collected in this registration is for development and testing purpose only and is not the actual data that should be collected in the final version of the application");
        die.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        final DatabaseHelper help = new DatabaseHelper(this);
        final SQLiteDatabase db = help.getReadableDatabase();
        die.show();
        final EditText name = (EditText)findViewById(R.id.edtname);
        final EditText bdate =(EditText)findViewById(R.id.edtbdate);
        final EditText Email = (EditText)findViewById(R.id.edtemail);
        final EditText conn =(EditText)findViewById(R.id.edtconno);
        final EditText user = (EditText)findViewById(R.id.edtusername);
        final EditText pass =(EditText)findViewById(R.id.edtpass);
        final EditText cnfrmpass= (EditText)findViewById(R.id.edtcnrmpass);
        Button btnreg =(Button)findViewById(R.id.btnregister);
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Pass = pass.getText().toString();
                String Cnfrmpass = cnfrmpass.getText().toString();
                Log.e(Pass,Cnfrmpass);
                if(Pass.equals(Cnfrmpass)){
                    try{
                        help.insertDataInclients(user.getText().toString(),Pass,name.getText().toString(),bdate.getText().toString(),conn.getText().toString(),Email.getText().toString(),db);
                        Toast.makeText(Register.this,"Registered successfully",Toast.LENGTH_LONG).show();
                        finish();
                    }
                    catch (Exception e){
                        Toast.makeText(Register.this,"Fill-up the form properly",Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(Register.this,"Password do not match",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
