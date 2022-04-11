package com.example.wepack4u.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wepack4u.utilities.FoodItem;
import com.example.wepack4u.R;
import com.example.wepack4u.utilities.ThreeColumnTable;

import java.util.ArrayList;
import java.util.List;

public class CartRecycler extends RecyclerView.Adapter<CartRecycler.ViewHolder> {
    private final Context context;
    private final List<FoodItem> cart;
    private final ArrayList<String> stalls;
    private final int[] orders;

    public CartRecycler(Context context, List<FoodItem> cart, ArrayList<String> stalls, int[] orders) {
        this.context = context;
        this.cart = cart;
        this.stalls = stalls;
        this.orders = orders;
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
        holder.stall.setText(stalls.get(position));
        if (orders != null) {
            String orderNum = "Order No. " + orders[position];
            holder.order.setText(orderNum);
        }

        ThreeColumnTable table = new ThreeColumnTable(holder.cartA, holder.cartB, holder.cartC,
                cart, stalls.get(position), context);
        table.createTable();
    }

    @Override
    public int getItemCount() {
        return stalls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView stall;
        TextView order;
        TableLayout cartA;
        TableLayout cartB;
        TableLayout cartC;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            stall = itemView.findViewById(R.id.stall_name);
            order = itemView.findViewById(R.id.order_num);
            cartA = itemView.findViewById(R.id.cart_a);
            cartB = itemView.findViewById(R.id.cart_b);
            cartC = itemView.findViewById(R.id.cart_c);
        }
    }
}
