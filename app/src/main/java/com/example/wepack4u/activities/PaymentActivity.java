package com.example.wepack4u.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wepack4u.R;
import com.example.wepack4u.utilities.RecyclerItemClickListener;
import com.example.wepack4u.utilities.TotalPrice;
import com.example.wepack4u.adaptors.CartRecycler;
import com.example.wepack4u.utilities.FoodItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PaymentActivity extends AppCompatActivity {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final String auth_uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Checkout section
        recyclerView = findViewById(R.id.cart_recycler_a);
        foodList();

        // Payment section
        RadioGroup payment_method = findViewById(R.id.payment_method);
        Button checkout = findViewById(R.id.checkout_button);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (payment_method.getCheckedRadioButtonId() != -1) {
                    int selected = payment_method.getCheckedRadioButtonId();
                    RadioButton method = findViewById(selected);
                    Intent intent = new Intent(PaymentActivity.this, ConfirmationActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(PaymentActivity.this, R.string.choose_payment, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void foodList() {
        db.collection("users").document(auth_uid).collection("cart").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                ArrayList<String> stalls = new ArrayList<>();
                if (task.isSuccessful()) {
                    QuerySnapshot querySnapshot = task.getResult();
                    List<FoodItem> foodItems = querySnapshot.toObjects(FoodItem.class);
                    for (FoodItem each: foodItems){
                        if (!stalls.contains(each.getStall())){
                            stalls.add(each.getStall());
                        }
                    }
                    CartRecycler cartRecycler = new CartRecycler(PaymentActivity.this, foodItems, stalls, false);
                    recyclerView.setAdapter(cartRecycler);
                    recyclerView.setLayoutManager(new LinearLayoutManager(PaymentActivity.this));

                    TextView total = findViewById(R.id.total);
                    total.setText(new TotalPrice(foodItems).getTotal());
                } else {
                    Log.d("cart_list", "Failed to fetch anything");
                }
            }
        });
    }
}
