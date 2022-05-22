package com.example.transport;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.security.acl.Owner;
import java.text.DateFormat;
import java.util.Calendar;

public class Addvehicale extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView vmodel,vnumber,Rbook,seats,location,name,contact;
    Button book;
    ImageButton camera;
    ImageView vimage;
    Uri uri;
    String imageUrl,Model,Number,Location,RCBook,Vseats,category,OwnerName,Contact;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addvehicale);
        final Spinner spinner=findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        vimage=findViewById(R.id.imageView7);
        vmodel=findViewById(R.id.editText4);
        vnumber=findViewById(R.id.editText5);
        Rbook=findViewById(R.id.editText31);
        seats=findViewById(R.id.editText2);
        location=findViewById(R.id.editText32);
        name=findViewById(R.id.editText6);
        contact=findViewById(R.id.editText7);
        book=findViewById(R.id.button16);
        camera=findViewById(R.id.imageButton17);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photopicker=new Intent(Intent.ACTION_PICK);
                photopicker.setType("image/*");
                startActivityForResult(photopicker,1);
            }
        });
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model=vmodel.getText().toString().trim();
                Number=vnumber.getText().toString().trim();
                RCBook=Rbook.getText().toString().trim();
                Vseats=seats.getText().toString().trim();
                Location=location.getText().toString().trim();
                OwnerName=name.getText().toString().trim();
                Contact=contact.getText().toString().trim();
                if (OwnerName.isEmpty()){
                    name.setError("Enter your valid name");
                    name.requestFocus();
                    return;
                }
                if (uri==null){
                    Toast.makeText(Addvehicale.this, "Please Upload your vehicle Image", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (category.equals("Select your vehicale Type")){
                    Toast.makeText(Addvehicale.this, "Please select type of vehicle", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Contact.isEmpty() || Contact.length()<10){
                    contact.setError("Enter your valid contact number");
                    contact.requestFocus();
                    return;
                }


                if (Model.isEmpty()){
                    vmodel.setError("Enter model of your vehicale");
                    vmodel.requestFocus();
                    return;
                }
                if (Number.isEmpty() || Number.length()<6){
                    vnumber.setError("Enter the valid number of your vehicale");
                    vnumber.requestFocus();
                    return;
                }
                if (RCBook.isEmpty() || RCBook.length()<14){
                    Rbook.setError("Enter the valid RCBook Number");
                    Rbook.requestFocus();
                    return;
                }
                if (Vseats.isEmpty() || Vseats.length()>3){
                    seats.setError(" Enter available Seats on Your vehicale");
                    seats.requestFocus();
                    return;
                }
                if (Location.isEmpty() || Location.length()<13){
                    location.setError("Enter your current Location");
                    location.requestFocus();
                    return;
                }
                StorageReference storageReference= FirebaseStorage.getInstance()
                        .getReference().child("Vehicale Image").child(uri.getLastPathSegment());
                final ProgressDialog progressDialog=new ProgressDialog(Addvehicale.this);
                progressDialog.setMessage("Vehicale Uploading......");
                progressDialog.show();
                storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while(!uriTask.isComplete());
                        Uri urlImage = uriTask.getResult();
                        imageUrl = urlImage.toString();
                        uploadVehicale();
                        uploadVehicale2();
                        progressDialog.dismiss();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                    }
                });

            }
        });
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            //startActivity(new Intent(Registeration.this,Home.class));
        }


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){

            uri = data.getData();
            vimage.setImageURI(uri);

        }
        else Toast.makeText(this, "You haven't picked image", Toast.LENGTH_SHORT).show();

    }
    public void uploadVehicale(){

        VehicaleData vehicaleData=new VehicaleData(category,Model,Number,RCBook,Vseats,Location,imageUrl,OwnerName,Contact);
       String myCurrentDateTime = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        String uid=firebaseAuth.getCurrentUser().getUid();
        FirebaseDatabase.getInstance().getReference("Users").child(uid)
                .child("Added_vehicales").child(myCurrentDateTime).setValue(vehicaleData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Addvehicale.this, "Vehicale Uploaded", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Addvehicale.this, "uploading of the vehicale is Failed", Toast.LENGTH_SHORT).show();
            }
        });




    }
    public void uploadVehicale2(){

        VehicaleData2 vehicaleData2=new VehicaleData2(category,Model,Number,RCBook,Vseats,Location,imageUrl,OwnerName,Contact);
        String myCurrentDateTime = DateFormat.getDateTimeInstance()
                .format(Calendar.getInstance().getTime());
       // String uid=firebaseAuth.getCurrentUser().getUid();
        FirebaseDatabase.getInstance().getReference("Added_Vehicals")
                .child(myCurrentDateTime).setValue(vehicaleData2).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){

                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(
                        Addvehicale.this, "uploading of the vehicale is Failed", Toast.LENGTH_SHORT).show();
            }
        });




    }


    public boolean isEnabled(int numbers)
    {
        if (numbers==0) {
            return false;
        }
        else {
            return true;
        }
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         category=parent.getItemAtPosition(position).toString();
        if(parent.getItemAtPosition(position).equals("Select your vehicale Type")){
            Toast.makeText(this, "please select your desired vehicale", Toast.LENGTH_SHORT).show();
            return;
        }

    }


    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

