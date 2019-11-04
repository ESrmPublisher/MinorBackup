package com.example.minorbackup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class table extends AppCompatActivity {

    RecyclerView recyclerView;
    public static RecyclerView.Adapter<AssignUser.ViewHolder> adapter;
    // public static List<Users> lstusr;

    ArrayList<Users> list;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        //   lstusr = new ArrayList<Users>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(table.this));

        loadstudentlist();
    }

    private void loadstudentlist() {


        reference = FirebaseDatabase.getInstance().getReference("Member");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<Users>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Users p = dataSnapshot1.getValue(Users.class);
                    list.add(p);
                }
                adapter = new AssignUser(table.this,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(table.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
