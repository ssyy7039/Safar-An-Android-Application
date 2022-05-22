package com.example.transport;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Renterdetails extends AppCompatActivity {
    TextView pickup, duration, pancard, aadharacard, drivivngliscence, contactNo, ownername, vehicaleNo;
    String key = "";
    Button delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renterdetails);
        ownername = (TextView) findViewById(R.id.textView33);
        pickup = (TextView) findViewById(R.id.textView40);
        duration = (TextView) findViewById(R.id.textView34);
        pancard = (TextView) findViewById(R.id.textView35);
        aadharacard = (TextView) findViewById(R.id.textView36);
        drivivngliscence = (TextView) findViewById(R.id.textView37);
        contactNo = (TextView) findViewById(R.id.textView38);
        vehicaleNo = (TextView) findViewById(R.id.textView39);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            ownername.setText("RenterName::  "+bundle.getString("Name"));
            key = bundle.getString("KeyValue");

            pickup.setText("Address::  "+bundle.getString("Address"));
            duration.setText("Duration(Time)::  "+bundle.getString("Duration"));
            pancard.setText("PanCard No.::  "+bundle.getString("PanCardNo"));
            aadharacard.setText("AadharCard No.:: "+bundle.getString("AadharCardNo"));
            drivivngliscence.setText("DrivivngLiscence No.:: "+bundle.getString("DrivingLiscenceNo"));
            contactNo.setText("Contact No.::  "+bundle.getString("Contact"));
            vehicaleNo.setText(bundle.getString("VehicaleNo"));



        }

        delete=findViewById(R.id.button15);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(Renterdetails.this);
                builder.setTitle("Delete");
                builder.setMessage("Are you sure You want to delete your renters?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                        if (firebaseUser != null) {
                            //startActivity(new Intent(Registeration.this,Home.class));
                        }

                        String uid = firebaseAuth.getCurrentUser().getUid();

                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Renter Details");
                        databaseReference.child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                startActivity(new Intent(Renterdetails.this, MyRenters.class));
                                Toast.makeText(Renterdetails.this, "You SuccessFully Deleted the Renters", Toast.LENGTH_SHORT).show();


                            }
                        });

                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });
                AlertDialog dialog=builder.show();


            }
        });
    }
}
