package com.example.wepack4u.utilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wepack4u.R;
import com.example.wepack4u.adaptors.Compost;

import java.util.ArrayList;
import java.util.List;

public class PaymentHolder extends RecyclerView.ViewHolder {
    private final CartListener listener;
    private final boolean isPayment;

    public PaymentHolder(ViewGroup parent, LayoutInflater inflater, CartListener listener,
                         int layoutId, boolean isPayment) {
        super(inflater.inflate(layoutId, parent, false));
        this.listener = listener;
        this.isPayment = isPayment;
    }

    public void Bind(String stallName, List<FoodItem> cart, Context context) {
        TextView stall = itemView.findViewById(R.id.stall_name);
        stall.setText(stallName);

        RecyclerView child;
        if (isPayment) { child = itemView.findViewById(R.id.row_a); }
        else { child = itemView.findViewById(R.id.row_b); }

        ArrayList<FoodItem> stallCart = new ArrayList<>();
        for (FoodItem f : cart) { if (f.getStall().equals(stallName)) { stallCart.add(f); } }

        Compost compost = new Compost(child.getContext(), stallCart, isPayment, listener);
        child.setAdapter(compost);
        child.setLayoutManager(new LinearLayoutManager(context) {
            @Override
            public boolean canScrollVertically() { return false; }
        });
    }
}
