package com.example.wepack4u;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class FoodDetail extends AppCompatActivity {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String auth_uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private String campus;
    RecyclerView foodDetailRecyclerView;
    String options[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        foodDetailRecyclerView = findViewById(R.id.foodDetailRecyclerView);

        options = getResources().getStringArray(R.array.food_options);

        FoodDetailAdaptor foodDetailAdaptor = new FoodDetailAdaptor(this, options);
        foodDetailRecyclerView.setAdapter(foodDetailAdaptor);
        foodDetailRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void toFoodDisplay(View view) {
        startActivity(new Intent(FoodDetail.this, FoodDisplay.class));
    }

    public void getFoodDetails(){
        DocumentReference userRef = db.collection("users").document(auth_uid);
        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot userDoc = task.getResult();
                    if (userDoc.exists()){
                        campus = (userDoc.getString("campus"));
//                        foodList(campus);
                    }
                    else{
                        Log.d("get_campus", "does not exist");
                    }
                }
            }
        });
    }

//    public void FoodDetails(String campus_name){
//        db.collection("Campus").whereEqualTo("name", campus_name).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//
//            }
//        })
//    }
}
