package com.example.transport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.text.DateFormat;
import java.util.Calendar;

public class Profile2 extends AppCompatActivity {
    private TextView Name,Email,Contact,Password,Address;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;
    String key="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);
        findViewById(R.id.button18).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile2.this,Updateprofile.class));
            }
        });
        Name=findViewById(R.id.textView10);
        Email=findViewById(R.id.textView9);
        Contact=findViewById(R.id.textView8);
        Password=findViewById(R.id.textView24);
        Address=findViewById(R.id.textView16);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        String uid=firebaseAuth.getCurrentUser().getUid();
        DatabaseReference databaseReference=firebaseDatabase.getReference("Users").child(uid).child("profile");
        // StorageReference storageReference=firebaseStorage.getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot itemSnapshot:dataSnapshot.getChildren())
                {
                    RegisteredData registeredData = dataSnapshot.getValue(RegisteredData.class);
                    Name.setText("Name::"+registeredData.getName());
                    Email.setText("EmailId::"+registeredData.getEmail());
                    Contact.setText("ContactNo::"+registeredData.getContact());
                    Password.setText("Password::"+registeredData.getPassword());
                    Address.setText("Address::"+registeredData.getAddress());

                    //Toast.makeText(Profile2.this, "Entered.......", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Profile2.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
