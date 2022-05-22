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

public class MyBookings2 extends AppCompatActivity {
    TextView Type,model,number,ownername,contact,location,Rc,seats;
    ImageView imageView;
    String key="";
    String imageUrl="";
    Button delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings2);
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
            Type.setText(bundle.getString("Type"));
            key=bundle.getString("KeyValue");
            imageUrl=bundle.getString("Image");
            model.setText(bundle.getString("Model"));
            number.setText(bundle.getString("VNumber"));
            location.setText(bundle.getString("Location"));
            Rc.setText(bundle.getString("Rcbook"));
            ownername.setText(bundle.getString("Ownername"));
            contact.setText(bundle.getString("Contact"));
            seats.setText(bundle.getString("Seats"));

            Glide.with(this).
                    load(bundle.getString("Image")).into(imageView);



        }
        delete=findViewById(R.id.button12);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MyBookings2.this);
                builder.setTitle("Delete");
                builder.setMessage("Are you sure You want to delete the bookings?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
                        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                        if(firebaseUser!=null){
                            //startActivity(new Intent(Registeration.this,Home.class));
                        }

                        String uid=firebaseAuth.getCurrentUser().getUid();

                        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Booking_Details");
                        databaseReference.child(key).removeValue();
                        Toast.makeText(MyBookings2.this, "You Successfully Deleted the Bookings ", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MyBookings.class));
                        finish();



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
