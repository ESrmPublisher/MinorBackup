package com.example.minorbackup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {
    EditText fnameedt,lnameedt,emailedt,regedt,passedt,cpassedt,role;
    Button submitbtn;
    DatabaseReference data;
    FirebaseAuth fauth;
    RadioButton admin,staff,student;
    Member member;
    String npattern = "[a-zA-Z][^#&<>\\\"~;$^%{}?]{1,20}$";
    String emailpattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    String pwdpattern = ".{8,}";
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
        admin = findViewById(R.id.admin);
        staff = findViewById(R.id.staff);
        student = findViewById(R.id.student);
        role = findViewById(R.id.role);
        member = new Member();
        data = FirebaseDatabase.getInstance().getReference("Member");
        fauth = FirebaseAuth.getInstance();
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                role.setText("Admin");
            }
        });
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                role.setText("Student");
            }
        });
        staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                role.setText("Staff");
            }
        });
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fname = fnameedt.getText().toString().trim();
                final String lname = lnameedt.getText().toString().trim();
                final String email = emailedt.getText().toString().trim();
                final String reg = regedt.getText().toString().trim();
                final String pass = passedt.getText().toString().trim();
                final String cpass = cpassedt.getText().toString().trim();
                final String roles = role.getText().toString().trim();
                if(TextUtils.isEmpty(fname)){
                    Toast.makeText(register.this,"Enter First Name!!!!",Toast.LENGTH_SHORT).show();
                }
                else if(!fname.matches(npattern)){
                    Toast.makeText(register.this,"First Name: only Alphabets allowed",Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(lname)){
                    Toast.makeText(register.this,"Enter Last Name!!!!",Toast.LENGTH_SHORT).show();
                }
                else if(!lname.matches(npattern)){
                    Toast.makeText(register.this,"Last Name: only Alphabets allowed",Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(email)){
                    Toast.makeText(register.this,"Enter Email ID!!!!",Toast.LENGTH_SHORT).show();
                }
                else if(!email.matches(emailpattern)){
                    Toast.makeText(register.this,"Incorrect Email-ID format",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(reg)){
                    Toast.makeText(register.this,"Enter Registration Number!!!!",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(pass)){
                    Toast.makeText(register.this,"Enter Password!!!!",Toast.LENGTH_SHORT).show();
                }
                else if(!pass.matches(pwdpattern)){
                    Toast.makeText(register.this,"Password Must have atleast 8 characters",Toast.LENGTH_LONG).show();
                }
                else if(!cpass.matches(pass)){
                    Toast.makeText(register.this,"Confirm Password does not match Password",Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(roles)){
                    Toast.makeText(register.this,"Choose a Role",Toast.LENGTH_LONG).show();
                }
                else {
                member.setFname(fnameedt.getText().toString().trim());
                member.setLname(lnameedt.getText().toString().trim());
                member.setEmailid(emailedt.getText().toString().trim());
                member.setRegno(regedt.getText().toString().trim());
                member.setPass(passedt.getText().toString().trim());
                member.setRole(role.getText().toString().trim());
                data.push().setValue(member);
                Toast.makeText(register.this,"Data inserted",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(register.this,MainActivity.class);
                startActivity(intent);
                }
            }
        });
    }
}
