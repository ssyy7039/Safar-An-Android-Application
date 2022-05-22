package com.example.transport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Updateprofile extends AppCompatActivity {
    private EditText name,email,password,contact,address;
    private Button save;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateprofile);
        save=findViewById(R.id.button19);
        name= findViewById(R.id.editText36);
        email = findViewById(R.id.editText37);
        contact= findViewById(R.id.editText38);
        password = findViewById(R.id.editText39);
        address = findViewById(R.id.editText40);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        String uid=firebaseAuth.getCurrentUser().getUid();
        final DatabaseReference databaseReference = firebaseDatabase.getReference("Users").child(uid).child("profile");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                RegisteredData userProfile = dataSnapshot.getValue(RegisteredData.class);
                name.setText(userProfile.getName());
              email.setText(userProfile.getEmail());
                contact.setText(userProfile.getContact());
                password.setText(userProfile.getPassword());
                address.setText(userProfile.getAddress());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Updateprofile.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name=name.getText().toString().trim();
                String Email=email.getText().toString().trim();
                String Contact=contact.getText().toString().trim();
              //  String Password=password.getText().toString().trim();
                String newPassword=password.getText().toString().trim();
                firebaseUser.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                       if (task.isSuccessful()){
                           Toast.makeText(Updateprofile.this, "Password changed", Toast.LENGTH_SHORT).show();
                       finish();
                       }else {
                           Toast.makeText(Updateprofile.this, "Password Update Failed", Toast.LENGTH_SHORT).show();
                       }
                    }
                });
                firebaseUser.updateEmail(Email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Updateprofile.this, "Email is Changed", Toast.LENGTH_SHORT).show();
                      finish();
                        }else{
                            Toast.makeText(Updateprofile.this, "Email Update Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                String Address=address.getText().toString().trim();
                RegisteredData registeredData=new RegisteredData(Name,Email,Contact,newPassword,Address);
                databaseReference.setValue(registeredData);

            finish();
            }
        });



    }
}
