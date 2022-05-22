package com.example.transport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    RecyclerView mrecyclerView2;
    private List<VehicaleData2> vehicaleDataList2;


    private DatabaseReference mdatabaseReference;
    private ValueEventListener eventListener2;
    ProgressDialog progressDialog;
    private ImageButton imageButton;
    EditText txt_Search;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
       imageButton=(ImageButton)findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Profile.class));
            }
        });
        FloatingActionButton fab=findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Addvehicale.class));
            }
        });
        txt_Search=(EditText)findViewById(R.id.txt_searchtext);
        mrecyclerView2=(RecyclerView)findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(Home.this,1);
        mrecyclerView2.setLayoutManager(gridLayoutManager);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Vehicales...");

        vehicaleDataList2=new ArrayList<>();
        final MyAdapter myAdapter2 = new MyAdapter(Home.this,vehicaleDataList2);
        mrecyclerView2.setAdapter(myAdapter2);
        mdatabaseReference = FirebaseDatabase.getInstance().getReference("Added_Vehicals");
        progressDialog.show();
        eventListener2 = mdatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                vehicaleDataList2.clear();

                for(DataSnapshot itemSnapshot: dataSnapshot.getChildren()){

                    VehicaleData2 mvehicaleData = itemSnapshot.getValue(VehicaleData2.class);
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
findViewById(R.id.imageButton2).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Dialog();

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
        ArrayList<VehicaleData2> filterList=new ArrayList<>();
        for (VehicaleData2 item: vehicaleDataList2){
            if(item.getCategory().toLowerCase().contains(text.toLowerCase())){
                filterList.add(item);
            }

        }
        myAdapter2.filteredList(filterList);

    }
});


        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            //startActivity(new Intent(Registeration.this,Home.class));
        }




    }
    FirebaseAuth firebaseAuth;
    private void Logout(){
//        String uid=firebaseAuth.getCurrentUser().getUid();
        firebaseAuth.signOut();


    }

    public void Dialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure You want to logout?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Logout();

                startActivity(new Intent(Home.this, MainActivity.class));
                Toast.makeText(Home.this, "You are successfully logged out", Toast.LENGTH_SHORT).show();
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




}
