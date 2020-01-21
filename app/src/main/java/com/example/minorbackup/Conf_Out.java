package com.example.minorbackup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

public class Conf_Out extends AppCompatActivity {
    TextView fnamedt,confname,venue,aff,confdate,natint,title;
    DatabaseReference mref;
    fconfdata f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conf__out);
        fnamedt = findViewById(R.id.fnamedt);
        confname = findViewById(R.id.confname);
        venue = findViewById(R.id.venue);
        aff = findViewById(R.id.aff);
        confdate = findViewById(R.id.confdate);
        natint = findViewById(R.id.natint);
        title = findViewById(R.id.title);
        Bundle b = getIntent().getExtras();
        final String f = b.getString("fna");
        mref = FirebaseDatabase.getInstance().getReference("Conference Data");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    String ch = ds.getKey();
                    String fname = dataSnapshot.child(ch).child("fnameedt").getValue().toString();
                    String confnam = dataSnapshot.child(ch).child("confname").getValue().toString();
                    String venu = dataSnapshot.child(ch).child("venue").getValue().toString();
                    String af = dataSnapshot.child(ch).child("aff").getValue().toString();
                    String confdat = dataSnapshot.child(ch).child("confdate").getValue().toString();
                    String natin = dataSnapshot.child(ch).child("natint").getValue().toString();
                    String titl = dataSnapshot.child(ch).child("title").getValue().toString();
                    if(fname.equals(f)) {
                        fnamedt.setText(fname);
                        confname.setText(confnam);
                        venue.setText(venu);
                        aff.setText(af);
                        confdate.setText(confdat);
                        natint.setText(natin);
                        title.setText(titl);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
