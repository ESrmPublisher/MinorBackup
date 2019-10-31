package com.example.minorbackup;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class facpublic extends Activity {
    EditText scopus,journ,scoppub,pappub,total;
    Button submit;
    facpubhelp fh;
    DatabaseReference dbref;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faculty_publication);
        scopus = findViewById(R.id.fnamedt);
        journ = findViewById(R.id.ntrain);
        scoppub = findViewById(R.id.ttrain);
        pappub = findViewById(R.id.aff);
        total = findViewById(R.id.fdate);
        submit = findViewById(R.id.submitbtn);
        fh = new facpubhelp();
        dbref = FirebaseDatabase.getInstance().getReference("Faculty Publications");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fh.setJourn(journ.getText().toString().trim());
                fh.setPappub(pappub.getText().toString().trim());
                fh.setScoppub(scoppub.getText().toString().trim());
                fh.setScopus(scopus.getText().toString().trim());
                fh.setTotal(total.getText().toString().trim());
                dbref.push().setValue(fh);
                Toast.makeText(facpublic.this,"Data Inserted",Toast.LENGTH_LONG);
            }
        });
    }
}
