package com.example.transport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class Contactus extends AppCompatActivity {
    Button Email,Instagram,Facebook,Submit;
    EditText query;
    String Query;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);
        Email=findViewById(R.id.button3);
        Facebook=findViewById(R.id.button5);
        Instagram=findViewById(R.id.button6);
        Submit=findViewById(R.id.button7);
        query=findViewById(R.id.editText3);
        Email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://mail.google.com/mail/mu/mp/938/#co")));
            }
        });
        Facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.facebook.com/profile.php?id=100014196195756")));
            }
        });

        Instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/official_.shivam/")));
            }
        });
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Query=query.getText().toString().trim();
                final ProgressDialog progressDialog=new ProgressDialog(Contactus.this);
                progressDialog.setMessage("Submiting...");
                progressDialog.show();
                if (Query.isEmpty()){
                    query.setText("please give your Query");
                    query.requestFocus();
                }
                if (!Query.isEmpty()){
                    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
                    String uid=firebaseAuth.getCurrentUser().getUid();
                    Query query1=new Query(Query);
                    String myCurrentDateTime = DateFormat.getDateTimeInstance()
                            .format(Calendar.getInstance().getTime());

                    FirebaseDatabase.getInstance().getReference("Users Query").child(myCurrentDateTime)
                            .setValue(query1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                progressDialog.dismiss();
                                Toast.makeText(Contactus.this, "Your Query is submitted", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(Contactus.this, "submission of Query is failed", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });



    }
}
