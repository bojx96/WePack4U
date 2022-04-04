package com.example.wepack4u;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class FoodDisplayAdaptor extends RecyclerView.Adapter<FoodDisplayAdaptor.FoodDisplayViewHolder>{
    Context context;
    List<FoodStore> foodStores;
    //Brandon's Edit
    public FoodDisplayAdaptor(Context ct, List<FoodStore> foodStores_input){
        context = ct;
        foodStores = foodStores_input;
    }
    @NonNull
    @Override
    public FoodDisplayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_food_display_adaptor,parent,false);
        return new FoodDisplayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodDisplayViewHolder holder, int position) {
// Brandon's Edit
        holder.FoodName.setText(foodStores.get(position).store_name);

    }

    @Override
    public int getItemCount() {
        return foodStores.size();
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
