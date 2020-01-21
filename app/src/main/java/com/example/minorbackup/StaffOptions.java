package com.example.minorbackup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StaffOptions extends AppCompatActivity {
    TextView name;
    Button work,conf,coord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_options);
        name = findViewById(R.id.name);
        work = findViewById(R.id.workshop);
        conf = findViewById(R.id.conference);
        coord = findViewById(R.id.journ);
        final Bundle bundle = getIntent().getExtras();
        final String fn = bundle.getString("fname");
        name.setText(fn);
        work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StaffOptions.this,WorkList.class);
                intent.putExtra("wname",fn);
                startActivity(intent);
            }
        });
        coord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StaffOptions.this,CordList.class);
                intent.putExtra("cdname",fn);
                startActivity(intent);
            }
        });
        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StaffOptions.this,JournList.class);
                intent.putExtra("journame",fn);
                startActivity(intent);
            }
        });
    }
}
