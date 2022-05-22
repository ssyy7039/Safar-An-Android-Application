package com.example.transport;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
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

public class MyVehicales2 extends AppCompatActivity {
    TextView Type,model,number,ownername,contact,location,Rc,seats;
    ImageView imageView;
    String key="";
    String imageUrl="";
    Button delete,update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_vehicales2);
      //  update=findViewById(R.id.button14);
        ownername=(TextView)findViewById(R.id.textView30);
        contact=(TextView)findViewById(R.id.textView28);
        Type=(TextView)findViewById(R.id.textView18);
        model=(TextView)findViewById(R.id.textView20);
        number=(TextView)findViewById(R.id.textView23);
        location=(TextView)findViewById(R.id.textView25);
        Rc=(TextView)findViewById(R.id.textView63);
        seats=(TextView)findViewById(R.id.textView65);
        imageView=(ImageView)findViewById(R.id.Image);

        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            Type.setText("Type::"+bundle.getString("Type"));
            key=bundle.getString("KeyValue");
            imageUrl=bundle.getString("Image");
            model.setText("Model::"+bundle.getString("Model"));
            number.setText("Number::"+bundle.getString("VNumber"));
            location.setText("Location::"+bundle.getString("Location"));
            Rc.setText("RCNo.::"+bundle.getString("Rcbook"));
            ownername.setText("OwnerName::"+bundle.getString("Ownername"));
            contact.setText("Contact::"+bundle.getString("Contact"));
            seats.setText("Seats::"+bundle.getString("Seats"));

            Glide.with(this).
                    load(bundle.getString("Image")).into(imageView);



        }

        delete=findViewById(R.id.button12);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     Dialog();
            }
        });
//        update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MyVehicales2.this,UpdateVehicale.class)
//                        .putExtra("OwName",ownername.getText().toString())
//                        .putExtra("Model",model.getText().toString())
//                        .putExtra("Number",number.getText().toString())
//                        .putExtra("Type",Type.getText().toString())
//                        .putExtra("Contact",contact.getText().toString())
//                        .putExtra("Location",location.getText().toString())
//                        .putExtra("RC",Rc.getText().toString())
//                        .putExtra("Seats",seats.getText().toString())
//                        .putExtra("Imageurl",imageUrl)
//                        .putExtra("key",key)
//                );
//            }
//        });

    }
    public void Dialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Delete");
        builder.setMessage("Are you sure You want to Delete the vehicle?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final ProgressDialog progressDialog=new ProgressDialog(MyVehicales2.this);
                progressDialog.setMessage("Deleting Vehicale......");

                progressDialog.show();
                FirebaseAuth  firebaseAuth= FirebaseAuth.getInstance();
                FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                if(firebaseUser!=null){
                    //startActivity(new Intent(Registeration.this,Home.class));
                }

                String uid=firebaseAuth.getCurrentUser().getUid();

                final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Added_vehicales");
                final DatabaseReference databaseReference1=FirebaseDatabase.getInstance().getReference("Added_Vehicals");
                databaseReference1.child(key).removeValue();
                //  FirebaseStorage storage2=FirebaseStorage.getInstance();
                // StorageReference storageReference2=storage2.getReference(imageUrl);
                FirebaseStorage storage=FirebaseStorage.getInstance();
                StorageReference storageReference=storage.getReferenceFromUrl(imageUrl);
                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        databaseReference.child(key).removeValue();

                        progressDialog.dismiss();
                        Toast.makeText(MyVehicales2.this, "Your Vehicale Successfully Deleted ", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MyVehicales.class));
                        finish();
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

}
