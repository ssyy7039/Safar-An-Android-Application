package com.example.transport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyVehicales extends AppCompatActivity {
    RecyclerView mrecyclerView2;
    private List<VehicaleData> vehicaleDataList;


    private DatabaseReference mdatabaseReference;
    private ValueEventListener eventListener2;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_vehicales);
        mrecyclerView2=(RecyclerView)findViewById(R.id.recyclerView2);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(MyVehicales.this,1);
        mrecyclerView2.setLayoutManager(gridLayoutManager);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Vehicales...");

        vehicaleDataList=new ArrayList<>();
        final MyVehicaleAdapter myAdapter2 = new MyVehicaleAdapter(MyVehicales.this,vehicaleDataList);
        mrecyclerView2.setAdapter(myAdapter2);
         FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            //startActivity(new Intent(Registeration.this,Home.class));
        }

        String uid=firebaseAuth.getCurrentUser().getUid();
        mdatabaseReference = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Added_vehicales");
        progressDialog.show();
        eventListener2 = mdatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                vehicaleDataList.clear();

                for(DataSnapshot itemSnapshot: dataSnapshot.getChildren()){

                    VehicaleData mvehicaleData = itemSnapshot.getValue(VehicaleData.class);
                    mvehicaleData.setKey(itemSnapshot.getKey());
                    vehicaleDataList.add(mvehicaleData);


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
