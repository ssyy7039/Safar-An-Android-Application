package com.example.transport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyRenters extends AppCompatActivity {
    RecyclerView mrecyclerView2;
    private List<Bookingdata> bookingdataList;


    private DatabaseReference mdatabaseReference;
    private ValueEventListener eventListener2;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    EditText txt_Search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_renters);
        txt_Search=(EditText)findViewById(R.id.txt_searchtext);
        mrecyclerView2=(RecyclerView)findViewById(R.id.recyclerView5);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(MyRenters.this,1);
        mrecyclerView2.setLayoutManager(gridLayoutManager);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Renters...");

        bookingdataList=new ArrayList<>();
        final RenterAdapter myAdapter2 = new RenterAdapter(MyRenters.this,bookingdataList);
        mrecyclerView2.setAdapter(myAdapter2);

       // String uid=firebaseAuth.getCurrentUser().getUid();
        mdatabaseReference = FirebaseDatabase.getInstance().getReference("Renter Details");
        progressDialog.show();
        eventListener2 = mdatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                bookingdataList.clear();

                for(DataSnapshot itemSnapshot: dataSnapshot.getChildren()){

                    Bookingdata bookingdata = itemSnapshot.getValue(Bookingdata.class);
                    bookingdata.setKey(itemSnapshot.getKey());
                    bookingdataList.add(bookingdata);


                }

                myAdapter2.notifyDataSetChanged();
                progressDialog.dismiss();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();


            }
        });
        txt_Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }

            private void filter(String text) {
                ArrayList<Bookingdata> filterList=new ArrayList<>();
                for (Bookingdata item: bookingdataList){
                    if(item.getVehicaleNo().toLowerCase().contains(text.toLowerCase())){
                        filterList.add(item);
                    }

                }
                myAdapter2.filteredList(filterList);

            }
        });


    }
}
