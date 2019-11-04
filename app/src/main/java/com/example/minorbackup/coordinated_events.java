package com.example.minorbackup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class coordinated_events extends AppCompatActivity {
    Spinner ntrain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinated_events);
        ArrayAdapter<String> myadap = new ArrayAdapter<String>(coordinated_events.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.nature));
        myadap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ntrain = findViewById(R.id.ntrain);
        ntrain.setAdapter(myadap);
    }
}
