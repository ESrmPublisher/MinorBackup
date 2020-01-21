package com.example.minorbackup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Workshop_output extends AppCompatActivity {
    TextView title,ntrain,aff,fdate,tdate,duration,organization;
    DatabaseReference mref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshop_output);
        title = findViewById(R.id.titles);
        ntrain = findViewById(R.id.ntrain);
        aff = findViewById(R.id.aff);
        fdate = findViewById(R.id.fdate);
        tdate = findViewById(R.id.tdate);
        duration = findViewById(R.id.duration);
        organization = findViewById(R.id.organization);
        Bundle b = getIntent().getExtras();
        final String tt = b.getString("fnamew");
        mref = FirebaseDatabase.getInstance().getReference("Workshop Data");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    String ch = ds.getKey();
                    String titl = dataSnapshot.child(ch).child("ttrain").getValue().toString();
                    String ntr = dataSnapshot.child(ch).child("ntrain").getValue().toString();
                    String af = dataSnapshot.child(ch).child("aff").getValue().toString();
                    String fdat = dataSnapshot.child(ch).child("fdate").getValue().toString();
                    String tdat = dataSnapshot.child(ch).child("tdate").getValue().toString();
                    String duratio = dataSnapshot.child(ch).child("duration").getValue().toString();
                    String org = dataSnapshot.child(ch).child("organization").getValue().toString();
                    String nam = dataSnapshot.child(ch).child("fnameedt").getValue().toString();
                    if(nam.equals(tt)) {
                        title.setText(titl);
                        ntrain.setText(ntr);
                        aff.setText(af);
                        fdate.setText(fdat);
                        tdate.setText(tdat);
                        duration.setText(duratio);
                        organization.setText(org);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
