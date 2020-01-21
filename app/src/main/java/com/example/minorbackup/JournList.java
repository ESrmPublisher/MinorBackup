package com.example.minorbackup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class JournList extends AppCompatActivity {
    ListView worklist;
    final ArrayList<String> arrayList = new ArrayList<>();
    DatabaseReference mref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journ_list);
        worklist = findViewById(R.id.worklist);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        worklist.setAdapter(arrayAdapter);
        Bundle bundle = getIntent().getExtras();
        final String fna = bundle.getString("journame");
        mref = FirebaseDatabase.getInstance().getReference("Journal Data");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String ch = ds.getKey();
                    String ttr = dataSnapshot.child(ch).child("journalname").getValue().toString();
                    String fname = dataSnapshot.child(ch).child("fnameedt").getValue().toString();
                    if(fname.equals(fna)) {
                        arrayList.add(ttr);
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        worklist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(JournList.this,JournOut.class);
                intent.putExtra("jnam",fna);
                startActivity(intent);
            }
        });
    }
}
