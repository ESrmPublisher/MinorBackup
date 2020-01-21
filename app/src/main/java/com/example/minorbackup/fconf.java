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

public class fconf extends AppCompatActivity {
    EditText fnamedt,venue,confname,aff,confdate,natint,title;
    Button upload,fsubmit;
    fconfdata fc;
    DatabaseReference db;
    StorageReference str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facultyconf);
        fnamedt = findViewById(R.id.fnamedt);
        venue = findViewById(R.id.venue);
        confname = findViewById(R.id.confname);
        aff = findViewById(R.id.aff);
        confdate = findViewById(R.id.confdate);
        natint = findViewById(R.id.natint);
        title = findViewById(R.id.title);
        upload = findViewById(R.id.upload);
        fsubmit = findViewById(R.id.fsubmit);
        str = FirebaseStorage.getInstance().getReference();
        fc = new fconfdata();
        db = FirebaseDatabase.getInstance().getReference("Conference Data");
        fsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fc.setFnameedt(fnamedt.getText().toString().trim());
                fc.setVenue(venue.getText().toString().trim());
                fc.setConfname(confname.getText().toString().trim());
                fc.setAff(aff.getText().toString().trim());
                fc.setConfdate(confdate.getText().toString().trim());
                fc.setNatint(natint.getText().toString().trim());
                fc.setTitle(title.getText().toString().trim());
                db.push().setValue(fc);
                Toast.makeText(fconf.this,"Data inserted",Toast.LENGTH_LONG).show();
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
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
                        Toast.makeText(fconf.this,"Uploaded Successfully!!!!!!!",Toast.LENGTH_SHORT);
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
