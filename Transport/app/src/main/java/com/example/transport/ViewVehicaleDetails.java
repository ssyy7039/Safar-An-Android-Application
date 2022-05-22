package com.example.transport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.security.Key;

public class ViewVehicaleDetails extends AppCompatActivity {
    TextView Type,model,number,ownername,contact,location,Rc,seats;
    ImageView imageView;
    String key="";
    String imageUrl="";
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vehicale_details);
        button=findViewById(R.id.button12);

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
            Type.setText("Type::  "+bundle.getString("Type"));
            key=bundle.getString("KeyValue");
            imageUrl=bundle.getString("Image");
            model.setText("Model::  "+bundle.getString("Model"));
            number.setText("VehicaleNo:: "+bundle.getString("VNumber"));
            location.setText("Location::  "+bundle.getString("Location"));
            Rc.setText("RCBookNo::  "+bundle.getString("Rcbook"));
            ownername.setText("OwnerName::  "+bundle.getString("Ownername"));
            contact.setText("ContactNo::  "+bundle.getString("Contact"));
            seats.setText("Available Seats::  "+bundle.getString("Seats"));

            Glide.with(this).
                    load(bundle.getString("Image")).into(imageView);



        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewVehicaleDetails.this,Book_Vehicale.class)
                        .putExtra("Type",Type.getText().toString())
                        .putExtra("Imageurl",imageUrl)
                        .putExtra("Key",key)
                        .putExtra("Model",model.getText().toString())
                        .putExtra("VNumber",number.getText().toString())
                        .putExtra("Location",location.getText().toString())
                        .putExtra("Rcbook",Rc.getText().toString())
                        .putExtra("OwName",ownername.getText().toString())
                        .putExtra("Contact",contact.getText().toString())
                        .putExtra("Seats",seats.getText().toString())

                );
            }
        });
    }
}
