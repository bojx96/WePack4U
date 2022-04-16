package com.example.wepack4u.utilities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wepack4u.R;

import java.util.List;

public class StallHolder extends RecyclerView.ViewHolder {
    private CartListener listener;
    private final boolean isPayment;

    public StallHolder(ViewGroup parent, LayoutInflater inflater, CartListener listener,
                       int layoutId, boolean isPayment) {
        super(inflater.inflate(layoutId, parent, false));
        this.listener = listener;
        this.isPayment = isPayment;
    }

    public void Bind(FoodItem food, List<FoodItem> cart) {
        TextView name;
        TextView unit;
        TextView price;
        TextView delete;

        if (isPayment) {
            name = itemView.findViewById(R.id.name_a);
            unit = itemView.findViewById(R.id.unit_a);
            price = itemView.findViewById(R.id.price_a);
            delete = itemView.findViewById(R.id.remove);
        }
        else {
            name = itemView.findViewById(R.id.name_b);
            unit = itemView.findViewById(R.id.unit_b);
            price = itemView.findViewById(R.id.price_b);
            delete = null;
        }

        name.setText(food.getName());
        unit.setText(food.getUnit());
        price.setText(food.getPrice());

        if (isPayment) {
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnRemove(getAdapterPosition());
                }
            });
        }
    }
}
