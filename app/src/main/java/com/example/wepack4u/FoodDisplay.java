package com.example.wepack4u;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.wepack4u.adaptors.FoodDisplayAdaptor;
import com.example.wepack4u.utilities.FoodMenu;
import com.example.wepack4u.utilities.FoodStore;
import com.example.wepack4u.utilities.RecyclerItemClickListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class FoodDisplay extends AppCompatActivity {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String auth_uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private String campus;
    private List<FoodMenu> foodMenu;
    private String storeName;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_display);
        recyclerView = findViewById(R.id.recyclerView);
        storeName = getIntent().getStringExtra("storeName");
        System.out.println(storeName);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(FoodDisplay.this, FoodDetail.class);
                        intent.putExtra("foodName", foodMenu.get(position).name);
                        intent.putExtra("storeName",storeName);
                        startActivity(intent);
                    }

                    @Override public void onLongItemClick(View view, int position) { }
                })
        );
        getFoodList();

    }
    public void toFoodDetail(View v){
        startActivity(new Intent (FoodDisplay.this,FoodDetail.class));
    }

    public void getFoodList(){
        DocumentReference userRef = db.collection("users").document(auth_uid);
        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot userDoc = task.getResult();
                    if (userDoc.exists()){
                        campus = (userDoc.getString("campus"));
                        foodList(campus);
                    }
                    else{
                        Log.d("get_campus", "does not exist");
                    }
                }
            }
        });
    }

    public void foodList(String campus_name) {
        db.collection("Campus").whereEqualTo("name", campus_name).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String school_id = document.getId();
                        db.collection("Campus").document(school_id).collection("food_stores").whereEqualTo("store_name",storeName).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()){
                                    QuerySnapshot querySnapshot = task.getResult();
                                    List<FoodStore> foodStores = querySnapshot.toObjects(FoodStore.class);
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        String store_id = document.getId();
                                        db.collection("Campus").document(school_id).collection("food_stores")
                                                .document(store_id).collection("menu").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()){
                                                    QuerySnapshot querySnapshot = task.getResult();
                                                    foodMenu = querySnapshot.toObjects(FoodMenu.class);
                                                    FoodDisplayAdaptor foodDisplayAdaptor = new FoodDisplayAdaptor(FoodDisplay.this, foodMenu);
                                                    recyclerView.setAdapter(foodDisplayAdaptor);
                                                    recyclerView.setLayoutManager(new LinearLayoutManager(FoodDisplay.this));
                                                }
                                            }
                                        });
                                    }
                                }
                            }
                        });
                    }
                }
                else{
                    Log.d("storeList", "Failed to fetch anything");
                }
            }
        });
    }
}