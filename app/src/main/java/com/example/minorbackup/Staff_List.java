package com.example.minorbackup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Staff_List extends AppCompatActivity {
    ListView listView;
    DatabaseReference mref;
    ArrayList<String> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_list);
        listView = findViewById(R.id.stafflist);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
        mref = FirebaseDatabase.getInstance().getReference("Accepted Users").child("Staff");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot chld : dataSnapshot.getChildren()){
                    String ch = chld.getKey();
                    String fname = dataSnapshot.child(ch).child("fname").getValue().toString();
                    String lname = dataSnapshot.child(ch).child("lname").getValue().toString();
                    arrayList.add(fname+" "+lname);
                    arrayAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Staff_List.this,StaffOptions.class);
                intent.putExtra("fname",listView.getItemAtPosition(position).toString());
                startActivity(intent);
            }
        });
    }
}
