package com.example.minorbackup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class coordinated_events extends AppCompatActivity {
    Spinner ntrain;
    EditText fnameedt,venue,date,event;
    coordata coordata;
    Button fsubmit,upload;
    DatabaseReference mref;
    StorageReference str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinated_events);
        ArrayAdapter<String> myadap = new ArrayAdapter<String>(coordinated_events.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.nature));
        myadap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ntrain = findViewById(R.id.ntrain);
        ntrain.setAdapter(myadap);
        fnameedt = findViewById(R.id.fnamedt);
        venue = findViewById(R.id.ttrain);
        date = findViewById(R.id.aff);
        coordata = new coordata();
        ntrain.setAdapter(myadap);
        fsubmit = findViewById(R.id.fsubmit);
        event = findViewById(R.id.event);
        upload = findViewById(R.id.upload);
        str = FirebaseStorage.getInstance().getReference();
        mref = FirebaseDatabase.getInstance().getReference("Events Co-ordinated");
        fsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coordata.setFnameedt(fnameedt.getText().toString());
                coordata.setEvent(event.getText().toString());
                coordata.setNtrain(ntrain.getSelectedItem().toString());
                coordata.setVenue(venue.getText().toString());
                coordata.setDate(date.getText().toString());
                mref.push().setValue(coordata);
                Toast.makeText(getApplicationContext(),"Data Inserted",Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(coordinated_events.this,"Uploaded Successfully!!!!!!!",Toast.LENGTH_SHORT);
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
