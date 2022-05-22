package com.example.transport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class Book_Vehicale extends AppCompatActivity {
    private EditText pickup,time,pancard,aadharcard,driving,name,contact;
    TextView vehicaleNo;
    private Button book;
    String Pickup,Time,Pancard,Aadharcard,Driving,Name,Contact,VehicaleNo;
    String key="";
    String imageurl="";
    String Type,Model,Number,OContact,Location,Rcbook,Owname,Seats;
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book__vehicale);
        name=(EditText)findViewById(R.id.editText17);
        contact=(EditText)findViewById(R.id.editText23);
        vehicaleNo=findViewById(R.id.textView31);
        pickup=(EditText)findViewById(R.id.editText26);
        time=(EditText)findViewById(R.id.editText10);
        pancard=(EditText)findViewById(R.id.editText8);
        aadharcard=(EditText)findViewById(R.id.editText7);
        driving=(EditText)findViewById(R.id.editText12);
        book=(Button)findViewById(R.id.button4);
        firebaseAuth=FirebaseAuth.getInstance();
        Bundle bundle1=getIntent().getExtras();
        final ProgressDialog progressDialog=new ProgressDialog(Book_Vehicale.this);
        progressDialog.setMessage("Booking...");
        if (bundle1!=null){
            vehicaleNo.setText(bundle1.getString("VNumber"));

        }


            book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pickup=pickup.getText().toString().trim();
                Time=time.getText().toString().trim();
                Pancard=pancard.getText().toString().trim();
                Aadharcard=aadharcard.getText().toString().trim();
                Driving=driving.getText().toString().trim();
                Name=name.getText().toString().trim();
                Contact=contact.getText().toString().trim();
                VehicaleNo=vehicaleNo.getText().toString().trim();

                if (Pickup.isEmpty() || pickup.length()<15){
                    pickup.setError("please enter your valid pickup Point.");
                    pickup.requestFocus();
                    return;
                }
                if (Time.isEmpty()){
                    time.setError("Enter the Duration(Time) of Vehicale");
                    time.requestFocus();
                    return;
                }
                if (Time.equals("0")){
                    time.setError("Booked your vehicle maximum for one day");
                    time.requestFocus();
                    return;
                }

                if (Pancard.isEmpty() || !(Pancard.length()==10)){
                    pancard.setError("Enter your valid PANCARD Number.");
                    pancard.requestFocus();
                    return;
                }

                if (Aadharcard.isEmpty() || !(Aadharcard.length()==12)){
                    aadharcard.setError("Enter your Valid Aadharcard Number");
                    aadharcard.requestFocus();
                    return;
                }

                if (Driving.isEmpty() || !(Driving.length()==15)){
                    driving.setError("Enter your Valid Driving Liscence Number");
                    driving.requestFocus();
                    return;
                }
                if (Name.isEmpty()){
                    name.setError("please enter your name");
                    name.requestFocus();
                    return;
                }
                if (Contact.isEmpty() || Contact.length()<10){
                    contact.setError("please enter your valid Contact Number");
                    contact.requestFocus();
                    return;
                }


                progressDialog.show();
                if(!(Driving.isEmpty())){
                    BookingData();
                    bookdata();
                    progressDialog.dismiss();
                    Toast.makeText(Book_Vehicale.this, "You Successfully Booked a vehicale", Toast.LENGTH_SHORT).show();
                }else {
                    progressDialog.dismiss();
                    Toast.makeText(Book_Vehicale.this, "Please check your details", Toast.LENGTH_SHORT).show();
                }





            }
        });
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            //startActivity(new Intent(Registeration.this,Home.class));
        }


    }
    public void BookingData(){

        Bookingdata bookingdata = new Bookingdata(Pickup,Time,Pancard,Aadharcard,Driving,Name,Contact,VehicaleNo);

       String myCurrentDateTime = DateFormat.getDateTimeInstance()
               .format(Calendar.getInstance().getTime());
       //String uid=firebaseAuth.getCurrentUser().getUid();
        FirebaseDatabase.getInstance().getReference("Renter Details").child(myCurrentDateTime).setValue(bookingdata).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                   // Toast.makeText(Book_Vehicale.this, "your vehicale Booking is Successfully Done", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Book_Vehicale.this, "Vehicale Booking failed", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void bookdata()
    {
        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            key=bundle.getString("Key");
            imageurl=bundle.getString("Imageurl");
            Type=bundle.getString("Type");
            Location=bundle.getString("Location");
            Owname=bundle.getString("OwName");
            OContact=bundle.getString("Contact");
         //   vehicaleNo.setText(bundle.getString("VNumber"));
            Number=bundle.getString("VNumber");
            Model=bundle.getString("Model");
            Rcbook=bundle.getString("Rcbook");
            Seats=bundle.getString("Seats");

        }
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            //startActivity(new Intent(Registeration.this,Home.class));
        }

        BookedVehicales bookedVehicales=new BookedVehicales(Type,Model,Location,Owname,OContact,Number,Rcbook,Seats,imageurl);
        String myCurrentDateTime = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        String uid=firebaseAuth.getCurrentUser().getUid();
        FirebaseDatabase.getInstance().getReference("Users").child(uid)
                .child("Booking_Details").child(myCurrentDateTime).setValue(bookedVehicales).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                   // Toast.makeText(Book_Vehicale.this, "Vehicale Uploaded", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
              //  Toast.makeText(Book_Vehicale.this, "uploading of the vehicale is Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
