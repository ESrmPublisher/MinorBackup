package com.example.minorbackup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class facultyworkshop extends AppCompatActivity{
    EditText fnameedt,ttrain,aff,fdate,tdate,duration,organization;
    Button upload,fsubmit;
    Spinner ntrain;
    DatabaseReference db;
    StorageReference str;
    fworkdata fw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facultywork);
        ArrayAdapter<String> myadap = new ArrayAdapter<String>(facultyworkshop.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.nature));
        myadap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fnameedt = findViewById(R.id.fnameedt);
        ntrain = findViewById(R.id.ntrain);
        ttrain = findViewById(R.id.ttrain);
        aff = findViewById(R.id.aff);
        fdate = findViewById(R.id.fdate);
        tdate = findViewById(R.id.tdate);
        duration = findViewById(R.id.duration);
        organization = findViewById(R.id.organization);
        upload = findViewById(R.id.upload);
        fsubmit = findViewById(R.id.fsubmit);
        fw = new fworkdata();
        db = FirebaseDatabase.getInstance().getReference().child("fworkdata");
        str = FirebaseStorage.getInstance().getReference();
        ntrain.setAdapter(myadap);
        fsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fw.setFnameedt(fnameedt.getText().toString().trim());
                fw.setNtrain(ntrain.getSelectedItem().toString().trim());
                fw.setTtrain(ttrain.getText().toString().trim());
                fw.setAff(aff.getText().toString().trim());
                fw.setFdate(fdate.getText().toString().trim());
                fw.setTdate(tdate.getText().toString().trim());
                fw.setDuration((duration.getText().toString().trim()));
                fw.setOrganization(organization.getText().toString().trim());
                db.push().setValue(fw);
                Toast.makeText(facultyworkshop.this,"Data inserted",Toast.LENGTH_LONG).show();
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
                        Toast.makeText(facultyworkshop.this,"Uploaded Successfully!!!!!!!",Toast.LENGTH_SHORT);
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
