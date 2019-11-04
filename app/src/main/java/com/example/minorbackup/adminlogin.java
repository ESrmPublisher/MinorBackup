package com.example.minorbackup;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class adminlogin extends AppCompatActivity {
    Button loginbtn,register;
    EditText username,pass;
    TextView back;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Member");
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
                String usernam = username.getText().toString().trim();
                String passw = pass.getText().toString().trim();
                if(TextUtils.isEmpty(usernam))
                {
                    Toast.makeText(adminlogin.this,"Username cannot be empty",Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(passw))
                {
                    Toast.makeText(adminlogin.this,"Password cannot be empty",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(adminlogin.this, dashboard.class);
                    startActivity(intent);
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adminlogin.this,register.class);
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
