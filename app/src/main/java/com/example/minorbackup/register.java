package com.example.minorbackup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {
    EditText fnameedt,lnameedt,emailedt,regedt,passedt,cpassedt;
    Button submitbtn;
    DatabaseReference data;
    FirebaseAuth fauth;
    Member member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        fnameedt = findViewById(R.id.fnameedt);
        lnameedt = findViewById(R.id.lnameedt);
        emailedt = findViewById(R.id.emailedt);
        regedt = findViewById(R.id.regedt);
        passedt= findViewById(R.id.passedt);
        cpassedt = findViewById(R.id.cpassedt);
        submitbtn = findViewById(R.id.submitbtn);
        member = new Member();
        data = FirebaseDatabase.getInstance().getReference().child("Member");
        fauth = FirebaseAuth.getInstance();
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fname = fnameedt.getText().toString().trim();
                final String lname = lnameedt.getText().toString().trim();
                final String email = emailedt.getText().toString().trim();
                final String reg = regedt.getText().toString().trim();
                final String pass = passedt.getText().toString().trim();
                if(TextUtils.isEmpty(fname)){
                    Toast.makeText(register.this,"Enter First Name!!!!",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(lname)){
                    Toast.makeText(register.this,"Enter Last Name!!!!",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(email)){
                    Toast.makeText(register.this,"Enter Email ID!!!!",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(reg)){
                    Toast.makeText(register.this,"Enter Registration Number!!!!",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(pass)){
                    Toast.makeText(register.this,"Enter Password!!!!",Toast.LENGTH_SHORT).show();
                }
                else {
                member.setFname(fnameedt.getText().toString().trim());
                member.setLname(lnameedt.getText().toString().trim());
                member.setEmailid(emailedt.getText().toString().trim());
                member.setRegno(regedt.getText().toString().trim());
                member.setPass(passedt.getText().toString().trim());
                data.push().setValue(member);
                Toast.makeText(register.this,"Data inserted",Toast.LENGTH_LONG).show();}/*
                final String fname = fnameedt.getText().toString().trim();
                final String lname = lnameedt.getText().toString().trim();
                final String email = emailedt.getText().toString().trim();
                final String reg = regedt.getText().toString().trim();
                final String pass = passedt.getText().toString().trim();

                if(TextUtils.isEmpty(fname)){
                    Toast.makeText(register.this,"Enter First Name!!!!",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(lname)){
                    Toast.makeText(register.this,"Enter Last Name!!!!",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(register.this,"Enter Email ID!!!!",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(reg)){
                    Toast.makeText(register.this,"Enter Registration Number!!!!",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(register.this,"Enter Password!!!!",Toast.LENGTH_SHORT).show();
                }


                fauth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Users info = new Users(
                                            fname,
                                            lname,
                                            email,
                                            reg,
                                            pass
                                    );

                                    FirebaseDatabase.getInstance().getReference("Member")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            Toast.makeText(register.this,"Registration Completed",Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(),StudentListActivity.class));
                                        }
                                    });


                                } else {

                                }

                                // ...
                            }
                        });*/
            }
        });
    }
}
