package com.example.wepack4u.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wepack4u.utilities.FoodItem;
import com.example.wepack4u.R;
import com.example.wepack4u.utilities.ThreeColumnTable;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class CartRecycler extends RecyclerView.Adapter<CartRecycler.ViewHolder> {
    private final Context context;
    private final List<FoodItem> cart;
    private final ArrayList<String> stalls;
    private final boolean isPayment;

    public CartRecycler(Context context, List<FoodItem> cart, ArrayList<String> stalls, boolean isPayment) {
        this.context = context;
        this.cart = cart;
        this.stalls = stalls;
        this.isPayment = isPayment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cart_display, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String stall = stalls.get(position);
        holder.stall.setText(stall);

        ArrayList<FoodItem> stallCart = new ArrayList<>();
        for (FoodItem f : cart) {
            if (f.getStall().equals(stall)) { stallCart.add(f); }
        }

        Compost compost = new Compost(holder.child.getContext(), stallCart, isPayment);
        holder.child.setAdapter(compost);
        holder.child.setLayoutManager(new LinearLayoutManager(context) {
            @Override
            public boolean canScrollVertically() { return false; }
        });
    }

    @Override
    public int getItemCount() {
        return stalls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView stall;
        RecyclerView child;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            stall = itemView.findViewById(R.id.stall_name);

            if (isPayment) {
                child = itemView.findViewById(R.id.row_a);
            }
            else {
                child = itemView.findViewById(R.id.row_b);
            }
        }
    }
}
