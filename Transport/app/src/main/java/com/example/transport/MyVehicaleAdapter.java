package com.example.transport;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyVehicaleAdapter extends  RecyclerView.Adapter<MyVehicaleHolder> {
    private Context context2;
    private List<VehicaleData> vehicaleDataList;

    public MyVehicaleAdapter(Context context2, List<VehicaleData> vehicaleDataList) {
        this.context2 = context2;
        this.vehicaleDataList = vehicaleDataList;
    }

    @NonNull
    @Override
    public MyVehicaleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardbook,parent,false);


        return  new MyVehicaleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyVehicaleHolder holder, int position) {
        Glide.with(context2)
                .load(vehicaleDataList.get(position).getItemImage())
                .into(holder.imageView);
        //holder.imageView.setImageResource(Integer.parseInt(myVehicaleList.get(position).getItemImage()));
        holder.model.setText(vehicaleDataList.get(position).getVehicale_Medel());
        holder.type.setText(vehicaleDataList.get(position).getCategory());
        holder.location.setText(vehicaleDataList.get(position).getVehicale_Location());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context2,MyVehicales2.class);
                intent.putExtra("Image",vehicaleDataList.get(holder.getAdapterPosition()).getItemImage());
                intent.putExtra("Type",vehicaleDataList.get(holder.getAdapterPosition()).getCategory());
                intent.putExtra("Model",vehicaleDataList.get(holder.getAdapterPosition()).getVehicale_Medel());
                intent.putExtra("Location",vehicaleDataList.get(holder.getAdapterPosition()).getVehicale_Location());
                intent.putExtra("VNumber",vehicaleDataList.get(holder.getAdapterPosition()).getVehicale_Number());
                intent.putExtra("Rcbook",vehicaleDataList.get(holder.getAdapterPosition()).getRCBookNumber());
                intent.putExtra("Seats",vehicaleDataList.get(holder.getAdapterPosition()).getAvailable_Seats());
                intent.putExtra("KeyValue",vehicaleDataList.get(holder.getAdapterPosition()).getKey());
                intent.putExtra("Ownername",vehicaleDataList.get(holder.getAdapterPosition()).getOwnername());
                intent.putExtra("Contact",vehicaleDataList.get(holder.getAdapterPosition()).getContact());
                context2.startActivity(intent);



            }
        });


    }

    @Override
    public int getItemCount() {
        return vehicaleDataList.size();

    }
}
class MyVehicaleHolder extends RecyclerView.ViewHolder{
    ImageView imageView;
    TextView model,location,type;
    CardView cardView;

    public MyVehicaleHolder(@NonNull View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.ivImage);
        model=itemView.findViewById(R.id.model);
        location=itemView.findViewById(R.id.location);
        type=itemView.findViewById(R.id.type);
        cardView=itemView.findViewById(R.id.myCardView);

    }
}
