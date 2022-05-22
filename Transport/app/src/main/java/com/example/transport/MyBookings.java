package com.example.transport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyBookings extends AppCompatActivity {
    RecyclerView mrecyclerView2;
    private List<BookedVehicales> vehicaleDataList2;


    private DatabaseReference mdatabaseReference;
    private ValueEventListener eventListener2;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);

        mrecyclerView2=(RecyclerView)findViewById(R.id.recyclerView6);
        firebaseAuth= FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            //startActivity(new Intent(Registeration.this,Home.class));
        }
        String uid=firebaseAuth.getCurrentUser().getUid();


        GridLayoutManager gridLayoutManager=new GridLayoutManager(MyBookings.this,1);
        mrecyclerView2.setLayoutManager(gridLayoutManager);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Vehicales...");

        vehicaleDataList2=new ArrayList<>();
        final BookingAdapter myAdapter2 = new BookingAdapter(MyBookings.this,vehicaleDataList2);
        mrecyclerView2.setAdapter(myAdapter2);
        mdatabaseReference = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Booking_Details");
        progressDialog.show();
        eventListener2 = mdatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                vehicaleDataList2.clear();

                for(DataSnapshot itemSnapshot: dataSnapshot.getChildren()){

                    BookedVehicales mvehicaleData = itemSnapshot.getValue(BookedVehicales.class);
                    mvehicaleData.setKey(itemSnapshot.getKey());
                    vehicaleDataList2.add(mvehicaleData);


                }

                myAdapter2.notifyDataSetChanged();
                progressDialog.dismiss();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();


            }
        });
    }
}
