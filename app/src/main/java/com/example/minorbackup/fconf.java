package com.example.minorbackup;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class fconf extends AppCompatActivity {
    EditText fnameedt,venue,confname,aff,confdate,natint,title;
    Button upload,fsubmit;
    fconfdata fc;
    DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facultyconf);
        fnameedt = findViewById(R.id.fnameedt);
        venue = findViewById(R.id.venue);
        confname = findViewById(R.id.confname);
        aff = findViewById(R.id.aff);
        confdate = findViewById(R.id.confdate);
        natint = findViewById(R.id.natint);
        title = findViewById(R.id.aff);
        upload = findViewById(R.id.upload);
        fsubmit = findViewById(R.id.fsubmit);
        fc = new fconfdata();
        db = FirebaseDatabase.getInstance().getReference().child("fconfdata");
        fsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fc.setFnameedt(fnameedt.getText().toString().trim());
                fc.setVenue(venue.getText().toString().trim());
                fc.setConfname(confname.getText().toString().trim());
                fc.setAff(aff.getText().toString().trim());
                fc.setConfdate(confdate.getText().toString().trim());
                fc.setNatint(natint.getText().toString().trim());
                fc.setTitle(title.getText().toString().trim());
                db.push().setValue(fc);
                Toast.makeText(fconf.this,"Data inserted",Toast.LENGTH_LONG).show();
            }
        });
    }
}
