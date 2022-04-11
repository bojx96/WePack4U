package com.example.wepack4u.adaptors;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wepack4u.R;
import com.example.wepack4u.utilities.FoodStore;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.MyViewHolder>{
    Context context;
    List<FoodStore> foodStores;

    public StoreAdapter(Context ct, List<FoodStore> foodStoresInput){
        context = ct;
        foodStores = foodStoresInput;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.store_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.myText1.setText(foodStores.get(position).store_name);
        Picasso.get().load(foodStores.get(position).store_img_url).into(holder.myImage);
    }

    @Override
    public int getItemCount() {
        return foodStores.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView myText1;
        ImageView myImage;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            //id from the layout xml
            myText1 = itemView.findViewById(R.id.myText1);
            myImage = itemView.findViewById(R.id.myImageView);
        }
    }

}
