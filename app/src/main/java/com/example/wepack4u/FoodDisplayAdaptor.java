package com.example.wepack4u;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FoodDisplayAdaptor extends RecyclerView.Adapter<FoodDisplayAdaptor.FoodDisplayViewHolder>{
    String data1[], data2[];
    int images[];
    Context context;
    //Brandon's Edit
//    List<FoodStore> foodStores;
    //Brandon's Edit
//    public FoodDisplayAdaptor(Context ct, List<FoodStore>){
//        context = ct;
//        foodStores = List<FoodStore>;
//    }
    public FoodDisplayAdaptor(Context ct, String s1[], String s2[], int img[]){
        context = ct;
        data1 = s1;
        data2 = s2;
        images = img;

    }
    @NonNull
    @Override
    public FoodDisplayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_food_display_adaptor,parent,false);
        return new FoodDisplayViewHolder(view);
    }
//    foodStores {store1, store2}
    @Override
    public void onBindViewHolder(@NonNull FoodDisplayViewHolder holder, int position) {
        holder.FoodName.setText(data1[position]);
        holder.FoodPrice.setText(data2[position]);
        holder.FoodImage.setImageResource(images[position]);
// Brandon's Edit
//        holder.FoodName.setText(foodStore.get(position).store_name);

    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class FoodDisplayViewHolder extends RecyclerView.ViewHolder{
        TextView FoodName, FoodPrice;
        ImageView FoodImage;

        public FoodDisplayViewHolder(@NonNull View itemView) {
            super(itemView);
            FoodName = itemView.findViewById(R.id.FoodName);
            FoodPrice = itemView.findViewById(R.id.FoodPrice);
            FoodImage = itemView.findViewById(R.id.FoodImage);
        }
    }
}
