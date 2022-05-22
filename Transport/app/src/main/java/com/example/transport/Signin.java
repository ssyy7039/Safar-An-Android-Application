package com.example.transport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signin extends AppCompatActivity {
    private Button button;
    private EditText edid,edpass;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private TextView forgot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        forgot=findViewById(R.id.textView2);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signin.this,Forgotpassword.class));
            }
        });
        button = (Button) findViewById(R.id.button);
        edid=(EditText)findViewById(R.id.editText);
        edpass=(EditText)findViewById(R.id.editText19);
       // String uid=firebaseAuth.getCurrentUser().getUid();
       // databaseReference= FirebaseDatabase.getInstance().getReference("Users").child(uid).child("profile");
        firebaseAuth=FirebaseAuth.getInstance();
        mAuthStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                if (firebaseUser!=null){
                   // Toast.makeText(Signin.this,"please enter your valid EmailID and Password",Toast.LENGTH_SHORT).show();

                }


            }
        };
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=edid.getText().toString().trim();
                String password=edpass.getText().toString().trim();
                final ProgressDialog progressDialog=new ProgressDialog(Signin.this);
                progressDialog.setMessage("Signing.....");
                progressDialog.setCancelable(false);
                if(email.isEmpty()) {
                    edid.setError("Please enter your Emailid");
                    edid.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    edpass.setError("Please enter your Password");
                    edpass.requestFocus();
                    return;
                }


                progressDialog.show();
                if (!(email.isEmpty() && password.isEmpty())){
                    firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(Signin.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                progressDialog.dismiss();
                                Toast.makeText(Signin.this,"Please enter your valid EmailId & Password",Toast.LENGTH_SHORT).show();
                            }
                            else {

                                Intent i2=new Intent(Signin.this,Home.class);
                                startActivity(i2);
                                progressDialog.dismiss();
                                Toast.makeText(Signin.this, "You are Signed In Successfully", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });
                }


            }
        });


    }
    @Override
    protected void onStart(){
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthStateListener);
    }

}
