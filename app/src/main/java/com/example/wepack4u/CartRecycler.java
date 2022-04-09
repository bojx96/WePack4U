package com.example.wepack4u;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartRecycler extends RecyclerView.Adapter<CartRecycler.ViewHolder> {
    private final Context context;
    private final String[] stores;
    private final int[] orders;
    private final ArrayList<ArrayList<FoodItem>> carts;
    private final boolean isPaymentPage;

    public CartRecycler(Context context, String[] stores, int[] orders, ArrayList<ArrayList<FoodItem>> carts, boolean isPaymentPage) {
        this.context = context;
        this.stores = stores;
        this.orders = orders;
        this.carts = carts;
        this.isPaymentPage = isPaymentPage;
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
        holder.store.setText(stores[position]);
        if (!isPaymentPage) {
            String orderNum = "Order No. " + orders[position];
            holder.order.setText(orderNum);
        }

        ThreeColumnTable table = new ThreeColumnTable(holder.cartA, holder.cartB, holder.cartC,
                carts.get(position), context);
        table.createTable();
    }

    @Override
    public int getItemCount() {
        return stores.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView store;
        TextView order;
        TableLayout cartA;
        TableLayout cartB;
        TableLayout cartC;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            store = itemView.findViewById(R.id.stall_name);
            order = itemView.findViewById(R.id.order_num);
            cartA = itemView.findViewById(R.id.cart_a);
            cartB = itemView.findViewById(R.id.cart_b);
            cartC = itemView.findViewById(R.id.cart_c);
        }
    }
}
