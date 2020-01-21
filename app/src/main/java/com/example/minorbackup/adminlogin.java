package com.example.minorbackup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class adminlogin extends AppCompatActivity {
    Button loginbtn,register;
    EditText username,pass;
    TextView back;
    private ArrayList<Users> usera;
    Users users = new Users();
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Accepted Users").child("Admin");
    DatabaseReference mref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);
        username = findViewById(R.id.username);
        loginbtn = findViewById(R.id.loginbtn);
        back = findViewById(R.id.back);
        pass = findViewById(R.id.pass);
        register = findViewById(R.id.register);
        final Users users = new Users();
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String usernam = username.getText().toString().trim();
                final String passw = pass.getText().toString().trim();
                if (TextUtils.isEmpty(usernam)) {
                    Toast.makeText(adminlogin.this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(passw)) {
                    Toast.makeText(adminlogin.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                } else {
                    mref = FirebaseDatabase.getInstance().getReference("Accepted Users").child("Admin");
                    mref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot chld : dataSnapshot.getChildren()){
                                String ch = chld.getKey();
                                String email = dataSnapshot.child(ch).child("emailid").getValue().toString();
                                String pass = dataSnapshot.child(ch).child("pass").getValue().toString();
                                if(usernam.equals(email) && passw.equals(pass)){
                                    Intent intent = new Intent(adminlogin.this, dashboard.class);
                                    intent.putExtra("maila",usernam);
                                    startActivity(intent);
                                    Toast.makeText(adminlogin.this,"Logged in Successfully",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adminlogin.this, register.class);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
