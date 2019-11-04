package com.example.minorbackup;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class studentlogin extends AppCompatActivity {
    Button loginbtn,register;
    EditText username,pass;
    TextView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_login);
        loginbtn = findViewById(R.id.loginbtn);
        username = findViewById(R.id.username);
        pass = findViewById(R.id.pass);
        register = findViewById(R.id.register);
        back = findViewById(R.id.back);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernam = username.getText().toString().trim();
                String passw = pass.getText().toString().trim();
                if(TextUtils.isEmpty(usernam)){
                    Toast.makeText(studentlogin.this,"Username cannot be empty",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(passw))
                {
                    Toast.makeText(studentlogin.this,"Password cannot be empty",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(studentlogin.this, dashboard_student.class);
                    startActivity(intent);
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(studentlogin.this,register.class);
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
