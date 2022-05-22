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

public class BookingAdapter extends RecyclerView.Adapter<BookingViewHolder> {
    Context context;
    List<BookedVehicales> bookedVehicalesList;

    public BookingAdapter(Context context, List<BookedVehicales> bookedVehicalesList) {
        this.context = context;
        this.bookedVehicalesList = bookedVehicalesList;
    }

    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardbook,parent,false);


        return  new BookingViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final BookingViewHolder holder, int position) {

        Glide.with(context)
                .load(bookedVehicalesList.get(position).getImageUrl())
                .into(holder.imageView);
        //holder.imageView.setImageResource(Integer.parseInt(myVehicaleList.get(position).getItemImage()));
        holder.model.setText(bookedVehicalesList.get(position).getModel());
        holder.type.setText(bookedVehicalesList.get(position).getType());
        holder.location.setText(bookedVehicalesList.get(position).getLocation());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,MyBookings2.class);
                intent.putExtra("Image",bookedVehicalesList.get(holder.getAdapterPosition()).getImageUrl());
                intent.putExtra("Type",bookedVehicalesList.get(holder.getAdapterPosition()).getType());
                intent.putExtra("Model",bookedVehicalesList.get(holder.getAdapterPosition()).getModel());
                intent.putExtra("Location",bookedVehicalesList.get(holder.getAdapterPosition()).getLocation());
                intent.putExtra("VNumber",bookedVehicalesList.get(holder.getAdapterPosition()).getVehicale_Number());
                intent.putExtra("Rcbook",bookedVehicalesList.get(holder.getAdapterPosition()).getRcBook_Number());
                intent.putExtra("Seats",bookedVehicalesList.get(holder.getAdapterPosition()).getSeats());
                intent.putExtra("KeyValue",bookedVehicalesList.get(holder.getAdapterPosition()).getKey());
                intent.putExtra("Ownername",bookedVehicalesList.get(holder.getAdapterPosition()).getOwnerName());
                intent.putExtra("Contact",bookedVehicalesList.get(holder.getAdapterPosition()).getContact());
                context.startActivity(intent);



            }
        });


    }

    @Override
    public int getItemCount() {
        return bookedVehicalesList.size();
    }
}
class BookingViewHolder extends RecyclerView.ViewHolder{
    ImageView imageView;
    TextView model,location,type;
    CardView cardView;

    public BookingViewHolder(View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.ivImage);
        model=itemView.findViewById(R.id.model);
        location=itemView.findViewById(R.id.location);
        type=itemView.findViewById(R.id.type);
        cardView=itemView.findViewById(R.id.myCardView);
    }
}
