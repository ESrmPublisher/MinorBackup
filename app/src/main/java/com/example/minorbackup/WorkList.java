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

public class WorkList extends AppCompatActivity {
    ListView worklist;
    final ArrayList<String> arrayList = new ArrayList<>();
    DatabaseReference mref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_list);
        worklist = findViewById(R.id.worklist);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        worklist.setAdapter(arrayAdapter);
        Bundle b = getIntent().getExtras();
        final String f = b.getString("wname");
        mref = FirebaseDatabase.getInstance().getReference("Workshop Data");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String ch = ds.getKey();
                    String ttr = dataSnapshot.child(ch).child("ttrain").getValue().toString();
                    String fna = dataSnapshot.child(ch).child("fnameedt").getValue().toString();
                    if(fna.equals(f)) {
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
                Intent intent = new Intent(WorkList.this,Workshop_output.class);
                intent.putExtra("fnamew",f);
                startActivity(intent);
            }
        });
    }
}
