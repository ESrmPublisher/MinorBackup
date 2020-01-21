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

public class activity_coordinated_event_output extends AppCompatActivity {
    TextView name,ntrain,venue,date,event;
    DatabaseReference mref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinated_event_output);
        name = findViewById(R.id.fnamedtoutput);
        ntrain = findViewById(R.id.ntrainoutput);
        event = findViewById(R.id.event);
        venue = findViewById(R.id.ttrainoutput);
        date = findViewById(R.id.aff);
        Bundle b = getIntent().getExtras();
        final String fn = b.getString("fnas");
        mref = FirebaseDatabase.getInstance().getReference("Events Co-ordinated");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot chld:dataSnapshot.getChildren()){
                    String ch = chld.getKey();
                    String nam = dataSnapshot.child(ch).child("fnameedt").getValue().toString();
                    String evnt = dataSnapshot.child(ch).child("event").getValue().toString();
                    String ntrai = dataSnapshot.child(ch).child("ntrain").getValue().toString();
                    String venu = dataSnapshot.child(ch).child("venue").getValue().toString();
                    String dat = dataSnapshot.child(ch).child("date").getValue().toString();
                    if(nam.equals(fn)) {
                        name.setText(nam);
                        ntrain.setText(ntrai);
                        venue.setText(venu);
                        date.setText(dat);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
