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

public class stafflogin extends AppCompatActivity {
    Button loginbtn;
    Button register;
    TextView back;
    EditText username,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_login);
        loginbtn = findViewById(R.id.loginbtn);
        register = findViewById(R.id.register);
        username = findViewById(R.id.username);
        pass = findViewById(R.id.pass);
        back = findViewById(R.id.back);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernam = username.getText().toString().trim();
                String passw = pass.getText().toString().trim();
                if(TextUtils.isEmpty(usernam))
                {
                    Toast.makeText(stafflogin.this,"Username cannot be empty",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(passw)){
                    Toast.makeText(stafflogin.this,"Password cannot be empty",Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(stafflogin.this,dashboard_staff.class);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(stafflogin.this,register.class);
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
