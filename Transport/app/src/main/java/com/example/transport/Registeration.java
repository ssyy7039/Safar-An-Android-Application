package com.example.transport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class Registeration extends AppCompatActivity {
    private Button button9;
    private EditText editname,editemail,editcontact,editpassword,editconfirmpassword,editaddress;
    private FirebaseAuth firebaseAuth;
    String Name,Email,Contact,Address,Password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        editname=(EditText)findViewById(R.id.editText9);
        editemail=(EditText)findViewById(R.id.editText11);
        editcontact=(EditText)findViewById(R.id.editText16);
        editpassword=(EditText)findViewById(R.id.editText13);
        editconfirmpassword=(EditText)findViewById(R.id.editText14);
        editaddress=(EditText)findViewById(R.id.editText15);
        button9 = (Button) findViewById(R.id.button9);
        firebaseAuth= FirebaseAuth.getInstance();
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name=editname.getText().toString().trim();
                Email=editemail.getText().toString().trim();
                Contact=editcontact.getText().toString().trim();
                Password=editpassword.getText().toString().trim();
                String Confirmpassword=editconfirmpassword.getText().toString().trim();
                Address=editaddress.getText().toString().trim();
                final ProgressDialog progressDialog=new ProgressDialog(Registeration.this);
                progressDialog.setMessage("Registering.....");
                progressDialog.setCancelable(false);
                if (Name.isEmpty()){
                    editname.setError("please enter your name.");
                    editname.requestFocus();
                    return;
                }
                if (Email.isEmpty()){
                    editemail.setError("please enter your valid EmailId.");
                    editemail.requestFocus();
                    return;
                }
                if (Contact.isEmpty() || (Contact.length()<10)){
                    editcontact.setError("please enter your valid ContactNo.");
                    editcontact.requestFocus();
                    return;
                }
                if (Password.isEmpty()){
                    editpassword.setError("please enter your Password.");
                    editpassword.requestFocus();
                    return;
                }
                if (Password.length()<6){
                    editpassword.setError("Password character must be greater than 6");
                    editpassword.requestFocus();
                    return;
                }
                if (Confirmpassword.isEmpty()){
                    editconfirmpassword.setError("please confirm your Password.");
                    editconfirmpassword.requestFocus();
                    return;
                }
                if (Address.isEmpty()){
                    editaddress.setError("please enter your address");
                    editaddress.requestFocus();
                    return;
                }
                progressDialog.show();
                if(Password.equals(Confirmpassword)){
                    firebaseAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(Registeration.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                sendUserData1();
                                progressDialog.dismiss();

                                Intent i=new Intent(Registeration.this,Signin.class);
                              //  i.putExtra("mobile", Contact);
                                startActivity(i);


                                Toast.makeText(Registeration.this, "You are successfully Registered", Toast.LENGTH_SHORT).show();

                            }
                            else{
                                progressDialog.dismiss();
                                Toast.makeText(Registeration.this,"please check your entered data",Toast.LENGTH_SHORT).show();


                            }

                        }
                    });

                }

            }
        });
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            //startActivity(new Intent(Registeration.this,Home.class));
        }





    }
    public void sendUserData1(){

        RegisteredData registeredData = new RegisteredData(Name,Email,Contact,Password,Address);

        String uid=firebaseAuth.getCurrentUser().getUid();
        FirebaseDatabase.getInstance().getReference("Users")
                .child(uid).child("profile").setValue(registeredData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Registeration.this, "Registeration failed", Toast.LENGTH_SHORT).show();
            }
        });


    }
}

