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

public class JournOut extends AppCompatActivity {
    TextView fname,ntrain,ttrain,aff,fdate,tdate,isbn;
    DatabaseReference mref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journ_out);
        fname = findViewById(R.id.fnamedt);
        ntrain = findViewById(R.id.ntrain);
        ttrain = findViewById(R.id.ttrain);
        fdate = findViewById(R.id.fdate);
        aff = findViewById(R.id.aff);
        tdate = findViewById(R.id.tdate01);
        isbn = findViewById(R.id.isbn01);
        Bundle b = getIntent().getExtras();
        final String f = b.getString("jnam");
        mref = FirebaseDatabase.getInstance().getReference("Journal Data");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    String ch = ds.getKey();
                    String fnam = dataSnapshot.child(ch).child("fnameedt").getValue().toString();
                    String ntrai = dataSnapshot.child(ch).child("authpos").getValue().toString();
                    String ttrai = dataSnapshot.child(ch).child("coauth").getValue().toString();
                    String af = dataSnapshot.child(ch).child("title").getValue().toString();
                    String tdat = dataSnapshot.child(ch).child("pubyear").getValue().toString();
                    String isb = dataSnapshot.child(ch).child("isbn").getValue().toString();
                    if(fnam.equals(f)) {
                        fname.setText(fnam);
                        ntrain.setText(ntrai);
                        ttrain.setText(ttrai);
                        aff.setText(af);
                        tdate.setText(tdat);
                        isbn.setText(isb);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
