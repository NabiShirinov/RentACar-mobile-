package com.example.rentalcarmobile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentalcarmobile.R;
import com.example.rentalcarmobile.interfaces.RecyclerViewInterface;
import com.example.rentalcarmobile.Service.MySharedPreferences;
import com.example.rentalcarmobile.dto.RentalResponse;
import com.example.rentalcarmobile.entities.Rental;

import java.util.ArrayList;
import java.util.List;

public class All_Rentals_RecycleViewAdapter extends RecyclerView.Adapter<All_Rentals_RecycleViewAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    List<Rental> rentals;


    public All_Rentals_RecycleViewAdapter(Context context,RentalResponse rentalResponse,RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        if (rentalResponse != null && rentalResponse.getData() != null) {
            this.rentals = rentalResponse.getData();
            saveRentalsListToSharedPreferences();

        } else {
            this.rentals = new ArrayList<>(); // or handle it based on your logic
        }
        this.recyclerViewInterface = recyclerViewInterface;
    }

    private void saveRentalsListToSharedPreferences() {
        // Save the rentals list to SharedPreferences using MySharedPreferences class
        MySharedPreferences mySharedPreferences = new MySharedPreferences(context);
        mySharedPreferences.saveRentalsList(rentals);;
    }
    @NonNull
    @Override
    public All_Rentals_RecycleViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycle_view_row, parent, false);

        return new All_Rentals_RecycleViewAdapter.MyViewHolder(view,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull All_Rentals_RecycleViewAdapter.MyViewHolder holder, int position) {
        Rental rental = rentals.get(position);

        holder.tvName.setText(rental.getCarName());
        holder.tvModel.setText(rental.getCarModel());
        holder.tvPrice.setText(String.valueOf(rental.getPrice()));

        if (holder.tvContact != null && rental.getUserEmail() != null) {
            holder.tvContact.setText(rental.getUserEmail());
        }

        holder.imageView.setImageResource(R.drawable.maruti_suzuki_fronx_splendid_silver_with_bluish_black);


    }

    @Override
    public int getItemCount() {
        return rentals.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvName, tvModel, tvPrice,tvContact;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView2);
            tvName = itemView.findViewById(R.id.carName);
            tvModel = itemView.findViewById(R.id.carModel);
            tvPrice = itemView.findViewById(R.id.carPrice);
            tvContact = itemView.findViewById(R.id.contactAdress);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface != null){
                        int pos = getAbsoluteAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}

