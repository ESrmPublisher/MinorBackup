package com.example.minorbackup;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class facultyjournal extends AppCompatActivity {
    EditText fnameedt,authpos,coauth,title,journalname,pubyear,isbn;
    Button upload,submit;
    DatabaseReference db;
    fjourndata fj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facultyjournal);
        fnameedt = findViewById(R.id.fnameedt);
        authpos = findViewById(R.id.ntrain);
        coauth = findViewById(R.id.ttrain);
        title = findViewById(R.id.aff);
        journalname = findViewById(R.id.fdate);
        pubyear = findViewById(R.id.tdate);
        isbn = findViewById(R.id.isbn);
        upload = findViewById(R.id.upload);
        submit = findViewById(R.id.work);
        fj = new fjourndata();
        db = FirebaseDatabase.getInstance().getReference().child("fjourndata");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fj.setFnameedt(fnameedt.getText().toString().trim());
                fj.setAuthpos(authpos.getText().toString().trim());
                fj.setCoauth(coauth.getText().toString().trim());
                fj.setTitle(title.getText().toString().trim());
                fj.setJournalname(journalname.getText().toString().trim());
                fj.setPubyear(pubyear.getText().toString().trim());
                fj.setIsbn(isbn.getText().toString().trim());
                db.push().setValue(fj);
                Toast.makeText(facultyjournal.this,"Data inserted",Toast.LENGTH_LONG).show();
            }
        });
    }
}
