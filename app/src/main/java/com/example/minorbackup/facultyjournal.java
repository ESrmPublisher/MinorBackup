package com.example.minorbackup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class facultyjournal extends AppCompatActivity {
    EditText fnameedt,authpos,coauth,title,journalname,pubyear,isbn;
    Button upload,submit,uploadscop;
    DatabaseReference db;
    StorageReference str;
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
        uploadscop = findViewById(R.id.uploadscop);
        fj = new fjourndata();
        db = FirebaseDatabase.getInstance().getReference("fjourndata");
        str = FirebaseStorage.getInstance().getReference();
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
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPDFfile();
            }
        });
        uploadscop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPDFfile();
            }
        });
    }

    private void selectPDFfile() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Pdf File"),1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            uploadPDFfile(data.getData());
        }
    }

    private void uploadPDFfile(Uri data) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading......");
        progressDialog.show();
        StorageReference storageReference = str.child("uploads/"+System.currentTimeMillis()+".pdf");
        storageReference.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(facultyjournal.this,"Uploaded Successfully!!!!!!!",Toast.LENGTH_SHORT);
                        progressDialog.dismiss();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0*taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                progressDialog.setMessage("Uploaded: "+ (int)progress + "%");
            }
        });
    }
}
