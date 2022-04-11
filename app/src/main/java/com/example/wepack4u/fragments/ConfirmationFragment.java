package com.example.wepack4u.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.wepack4u.R;
import com.example.wepack4u.adaptors.CartRecycler;
import com.example.wepack4u.utilities.FoodItem;
import com.example.wepack4u.utilities.Timestamp;
import com.example.wepack4u.utilities.TotalPrice;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class ConfirmationFragment extends Fragment {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final String auth_uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    RecyclerView recyclerView;

    public ConfirmationFragment() {
        // Required empty public constructor
    }


    public static ConfirmationFragment newInstance(String param1, String param2) {
        ConfirmationFragment fragment = new ConfirmationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirmation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);

        // Timestamp
        TextView timestamp = view.findViewById(R.id.timestamp);
        timestamp.setText(new Timestamp().setTimestamp());

        // Firebase-dependent data
        recyclerView = view.findViewById(R.id.cart_recycler_b);
        foodList();

        // Button
        Button done = view.findViewById(R.id.done_button);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(getContext(), StorePage.class);
                startActivity(intent);*/
                Fragment nextFragment = new StorePageFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,nextFragment).commit();

            }
        });
    }
    public void foodList() {
        db.collection("users").document(auth_uid).collection("cart").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            // Receipt
                            QuerySnapshot querySnapshot = task.getResult();
                            List<FoodItem> foodItems = querySnapshot.toObjects(FoodItem.class);
                            ArrayList<String> stalls =  new ArrayList<>();
                            for (FoodItem each: foodItems){
                                if (!stalls.contains(each.getStall())){
                                    stalls.add(each.getStall());
                                }
                            }
                            // dummies
//                    String[] stalls = {"Japanese Korean", "Healthy Soup"};
                            int[] orders = {69, 420};

                            CartRecycler cartRecycler = new CartRecycler(getContext(),
                                    foodItems, stalls, orders);
                            recyclerView.setAdapter(cartRecycler);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                            TextView total = getView().findViewById(R.id.total2);
                            String totalPrice = new TotalPrice(foodItems).getTotal();
                            total.setText(totalPrice);

                            // Text
                            TextView paymentText = getView().findViewById(R.id.payment_text);
                            String text1 = "A payment of " + totalPrice + " has been made.";
                            paymentText.setText(text1);

                            int time = 15; // dummy var
                            TextView waitingTime = getView().findViewById(R.id.waiting_time);
                            String text2 = "Your order will be done in " + time + " minutes.";
                            waitingTime.setText(text2);
                        } else {
                            Log.d("cart_list", "Failed to fetch anything");
                        }
                    }
                });
    }
}