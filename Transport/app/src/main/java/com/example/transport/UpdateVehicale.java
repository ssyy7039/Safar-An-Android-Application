package com.example.transport;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class    UpdateVehicale extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView vmodel,vnumber,Rbook,seats,location,name,contact;
    Button update;
    ImageButton camera;
    ImageView vimage;
    Uri uri;
    String url,url1;
    String Model,Number,Location,RCBook,Vseats,category,OwnerName,Contact;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    StorageReference storageReference;
    FirebaseStorage firebaseStorage;
    String key;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){

            uri = data.getData();
            vimage.setImageURI(uri);

        }
        else Toast.makeText(this, "You haven't picked image", Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_vehicale);
        final Spinner spinner=findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        vimage=findViewById(R.id.imageView7);
        vmodel=findViewById(R.id.editText4);
        vnumber=findViewById(R.id.editText5);
        Rbook=findViewById(R.id.editText31);
        seats=findViewById(R.id.editText2);
        location=findViewById(R.id.editText32);
        name=findViewById(R.id.editText6);
        contact=findViewById(R.id.editText7);
        update=findViewById(R.id.button16);
        camera=findViewById(R.id.imageButton17);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();

        Bundle bundle= getIntent().getExtras();
        if (bundle!=null){
            Glide.with(UpdateVehicale.this)
                    .load(bundle.getString("Imageurl"))
                    .into(vimage);
            vmodel.setText(bundle.getString("Model"));
            vnumber.setText(bundle.getString("Number"));
            Rbook.setText(bundle.getString("RC"));
            seats.setText(bundle.getString("Seats"));
            location.setText(bundle.getString("Location"));
            name.setText(bundle.getString("OwName"));
            contact.setText(bundle.getString("Contact"));
          category=bundle.getString("Type");
            key=bundle.getString("key");
           url=bundle.getString("Imageurl");

        }
//        final DatabaseReference databaseReference=firebaseDatabase.getReference("Users").child(uid).child("Added_Vehicales");
//        final DatabaseReference databaseReference1=firebaseDatabase.getReference("Added_Vehicals");

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photopicker=new Intent(Intent.ACTION_PICK);
                photopicker.setType("image/*");
                startActivityForResult(photopicker,1);
            }
        });

update.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Model=vmodel.getText().toString().trim();
        Number=vnumber.getText().toString().trim();
        OwnerName=name.getText().toString().trim();
        Contact=contact.getText().toString().trim();
        RCBook=Rbook.getText().toString().trim();
        Location=location.getText().toString().trim();
        Vseats=seats.getText().toString().trim();
        final ProgressDialog progressDialog=new ProgressDialog(UpdateVehicale.this);
        progressDialog.setMessage("Vehicale Updating.....");
        progressDialog.show();
        storageReference=FirebaseStorage.getInstance()
                .getReference().child("Vehicale Image").child(uri.getLastPathSegment());
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri>  uriTask=taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlImage=uriTask.getResult();
                url=urlImage.toString();
                uploadvehicale();
                uploadvehicale2();

                progressDialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
           progressDialog.dismiss();
            }
        });
    }
});
    }
    public  void uploadvehicale(){

        VehicaleData vehicaleData=new VehicaleData(
                category,
                Model,
                Number,
                RCBook,Vseats,Location,url,OwnerName,Contact);
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Added_Vehicals").child(key);
        databaseReference.setValue(vehicaleData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                StorageReference storageReference1=FirebaseStorage.getInstance().getReferenceFromUrl(url);
                storageReference1.delete();

                Toast.makeText(UpdateVehicale.this, "Vehicale Data Updated", Toast.LENGTH_SHORT).show();

            }
        });


    }
    public  void uploadvehicale2(){

        VehicaleData2 vehicaleData2=new VehicaleData2(
                category,
                Model,
                Number,
                RCBook,Vseats,Location,url,OwnerName,Contact);
        String uid=firebaseAuth.getCurrentUser().getUid();
        DatabaseReference databaseReference2=FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Added_vehicales").child(key);
        databaseReference2.setValue(vehicaleData2).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                StorageReference storageReference1=FirebaseStorage.getInstance().getReferenceFromUrl(url);
                storageReference1.delete();
                Toast.makeText(UpdateVehicale.this, "Vehicale Data Updated", Toast.LENGTH_SHORT).show();

            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        category=parent.getItemAtPosition(position).toString();
        if(parent.getItemAtPosition(position).equals("Select your vehicale Type")){
            Toast.makeText(this, "please select your desired vehicale", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
