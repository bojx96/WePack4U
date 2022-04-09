package com.example.wepack4u;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FoodDetailAdaptor extends RecyclerView.Adapter<FoodDetailAdaptor.FoodDetailViewHolder>{
    String options[];
    Context context;

    public FoodDetailAdaptor(Context ct,String opt[]){
        context = ct;
        options = opt;
    }


    @NonNull
    @Override
    public FoodDetailAdaptor.FoodDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_food_detail_adaptor,parent,false);
        return new FoodDetailAdaptor.FoodDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodDetailAdaptor.FoodDetailViewHolder holder, int position) {
        holder.OptionCheckBox.setText(options[position]);
    }

    @Override
    public int getItemCount() {
        return options.length;
    }

    public class FoodDetailViewHolder extends RecyclerView.ViewHolder{
        CheckBox OptionCheckBox;

        public FoodDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            OptionCheckBox = itemView.findViewById(R.id.OptionCheckBox);

        }
    }
}