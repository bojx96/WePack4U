package com.example.wepack4u;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FoodDetailAdaptor extends RecyclerView.Adapter<FoodDetailAdaptor.FoodDetailViewHolder>{
    String options[];
    Context context;
    public FoodDetailAdaptor(Context ct, String opt[]){
        context = ct;
        options = opt;
    }


    @NonNull
    @Override
    public FoodDetailAdaptor.FoodDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodDetailAdaptor.FoodDetailViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class FoodDetailViewHolder extends RecyclerView.ViewHolder{
        TextView FoodName, FoodPrice;
        ImageView FoodImage;

        public FoodDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            FoodName = itemView.findViewById(R.id.FoodName);
            FoodPrice = itemView.findViewById(R.id.FoodPrice);
            FoodImage = itemView.findViewById(R.id.FoodImage);
        }
    }
}