package com.example.transport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.service.autofill.UserData;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Personaldetails extends AppCompatActivity {
    private TextView Name,Email,Contact,Password,Address;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personaldetails);
        Name= findViewById(R.id.editText18);
        Email=findViewById(R.id.textView12);
        Contact=findViewById(R.id.textView13);
        Password=findViewById(R.id.textView14);
        Address=findViewById(R.id.textView15);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
       // firebaseStorage = FirebaseStorage.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference().child("User_Detail").child(firebaseAuth.getUid());
       // StorageReference storageReference=firebaseStorage.getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                RegisteredData registeredData=dataSnapshot.getValue(RegisteredData.class);
                Name.setText(registeredData.getName());
                Email.setText(registeredData.getEmail());
                Contact.setText(registeredData.getContact());
                Password.setText(registeredData.getPassword());
                Address.setText(registeredData.getAddress());
            }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Personaldetails.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
