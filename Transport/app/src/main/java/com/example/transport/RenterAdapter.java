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

import java.util.ArrayList;
import java.util.List;

public class RenterAdapter extends RecyclerView.Adapter<RenterViewHolder> {

    private Context context;
    private List<Bookingdata> bookingdataList;

    public RenterAdapter(Context context, List<Bookingdata> bookingdataList) {
        this.context = context;
        this.bookingdataList = bookingdataList;
    }

    @Override
    public RenterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardrenters,parent,false);


        return  new RenterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RenterViewHolder holder, int position) {
        holder.name.setText(bookingdataList.get(position).getName());
        holder.contactno.setText(bookingdataList.get(position).getContact());
        holder.Vehicale_Number.setText(bookingdataList.get(position).getVehicaleNo());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Renterdetails.class);
                intent.putExtra("KeyValue",bookingdataList.get(holder.getAdapterPosition()).getKey());
                intent.putExtra("Name",bookingdataList.get(holder.getAdapterPosition()).getName());
                intent.putExtra("Contact",bookingdataList.get(holder.getAdapterPosition()).getContact());
                intent.putExtra("Address",bookingdataList.get(holder.getAdapterPosition()).getPickupPoint());
                intent.putExtra("AadharCardNo",bookingdataList.get(holder.getAdapterPosition()).getAadharcardNumber());
                intent.putExtra("PanCardNo",bookingdataList.get(holder.getAdapterPosition()).getPancardNumber());
                intent.putExtra("DrivingLiscenceNo",bookingdataList.get(holder.getAdapterPosition()).getDrivingLiscenceNumber());
                intent.putExtra("Duration",bookingdataList.get(holder.getAdapterPosition()).getDuration());
                intent.putExtra("VehicaleNo",bookingdataList.get(holder.getAdapterPosition()).getVehicaleNo());
                context.startActivity(intent);



            }
        });

    }

    @Override
    public int getItemCount() {
        return bookingdataList.size();
    }
    public void filteredList(ArrayList<Bookingdata> filterList) {
        bookingdataList=filterList;
        notifyDataSetChanged();
    }

}
class RenterViewHolder extends RecyclerView.ViewHolder{

    TextView name,contactno,Vehicale_Number;
    CardView cardView;



    public RenterViewHolder(@NonNull View itemView) {
        super(itemView);

        name=itemView.findViewById(R.id.Name);
        contactno=itemView.findViewById(R.id.Conatact);
        Vehicale_Number=itemView.findViewById(R.id.duration);
        cardView=itemView.findViewById(R.id.myCardView2);

    }
}

