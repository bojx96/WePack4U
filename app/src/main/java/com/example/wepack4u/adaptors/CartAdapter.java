package com.example.wepack4u.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wepack4u.utilities.CartListener;
import com.example.wepack4u.utilities.FoodItem;
import com.example.wepack4u.R;
import com.example.wepack4u.utilities.PaymentHolder;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<PaymentHolder> {
    private final Context context;
    private final List<FoodItem> cart;
    private final ArrayList<String> stalls;
    private final boolean isPayment;
    private final CartListener listener;

    public CartAdapter(Context context, List<FoodItem> cart, ArrayList<String> stalls, boolean isPayment) {
        this.context = context;
        this.cart = cart;
        this.stalls = stalls;
        this.isPayment = isPayment;
    }

    @NonNull
    @Override
    public PaymentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        int layoutId;
        if (isPayment) { layoutId = R.layout.cart_display; }
        else { layoutId = R.layout.receipt; }

        return new PaymentHolder(parent, inflater, listener, layoutId, isPayment);

        //View view;
        //if (isPayment) { view = inflater.inflate(R.layout.cart_display, parent, false); }
        //else { view = inflater.inflate(R.layout.receipt, parent, false); }
        //return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentHolder holder, int position) {
        String stall = stalls.get(position);
        holder.Bind(stall, cart, context);

        /*holder.stall.setText(stall);

        ArrayList<FoodItem> stallCart = new ArrayList<>();
        for (FoodItem f : cart) { if (f.getStall().equals(stall)) { stallCart.add(f); } }

        Compost compost = new Compost(holder.child.getContext(), stallCart, isPayment);
        holder.child.setAdapter(compost);
        holder.child.setLayoutManager(new LinearLayoutManager(context) {
            @Override
            public boolean canScrollVertically() { return false; }
        });

        if (stallCart.size() == 0) {
            int pos = holder.getAdapterPosition();
            stalls.remove(pos);
            notifyItemRemoved(pos);
            notifyItemRangeChanged(pos, getItemCount());
        }*/
    }

    @Override
    public int getItemCount() {
        return stalls.size();
    }

    /*public class ViewHolder extends RecyclerView.ViewHolder {
        TextView stall;
        RecyclerView child;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            stall = itemView.findViewById(R.id.stall_name);
            if (isPayment) { child = itemView.findViewById(R.id.row_a); }
            else { child = itemView.findViewById(R.id.row_b); }
        }
    }*/
}
