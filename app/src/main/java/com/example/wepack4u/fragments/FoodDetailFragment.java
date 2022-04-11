package com.example.wepack4u.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wepack4u.activities.FoodDisplay;
import com.example.wepack4u.R;
import com.example.wepack4u.adaptors.FoodDetailAdaptor;
import com.example.wepack4u.utilities.FoodStore;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FoodDetailFragment extends Fragment {

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String auth_uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private String campus,foodName,storeName;
    private Map<String, Object> foodDetails = new HashMap<>();
    private TextView FoodDetailName,FoodDetailPrice;
    private ImageView FoodImageHolder;
    private Button addtoCart;
    RecyclerView foodDetailRecyclerView;
    String options[];

    public FoodDetailFragment() {
        // Required empty public constructor
    }

    public static FoodDetailFragment newInstance(String param1, String param2) {
        FoodDetailFragment fragment = new FoodDetailFragment();
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
        return inflater.inflate(R.layout.fragment_food_detail, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        foodDetailRecyclerView = view.findViewById(R.id.foodDetailRecyclerView);
        foodName = getArguments().getString("foodName");
        System.out.println("foodName = " + foodName);
        storeName = getArguments().getString("storeName");
        System.out.println("storeName = " + storeName);
        getFoodDetails();

        options = getResources().getStringArray(R.array.food_options);
        FoodDetailAdaptor foodDetailAdaptor = new FoodDetailAdaptor(getContext(), options);
        foodDetailRecyclerView.setAdapter(foodDetailAdaptor);
        foodDetailRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        addtoCart = view.findViewById(R.id.addtoCart);
        addtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("users").document(auth_uid).collection("cart").document().set(foodDetails);
                //finish();
                getFragmentManager().popBackStack();
                Toast.makeText(getContext(), "Item Added to Cart!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void toFoodDisplay(View view) {
        startActivity(new Intent(getContext(), FoodDisplay.class));
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
                        FoodDetails(campus);
                    }
                    else{
                        Log.d("get_campus", "does not exist");
                    }
                }
            }
        });
    }
    public void FoodDetails(String campus_name){
        CollectionReference colRef = db.collection("Campus");
        colRef.whereEqualTo("name", campus_name).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    String school_id = document.getId();
                    colRef.document(school_id).collection("food_stores")
                            .whereEqualTo("store_name",storeName)
                            .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()){
                                QuerySnapshot querySnapshot = task.getResult();
                                List<FoodStore> foodStores = querySnapshot.toObjects(FoodStore.class);
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String store_id = document.getId();
                                    String STORENAME = document.get("store_name").toString();
                                    colRef.document(school_id).collection("food_stores")
                                            .document(store_id).collection("menu").whereEqualTo("name",foodName).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if (task.isSuccessful()){
                                                for (QueryDocumentSnapshot document : task.getResult()) {
                                                    String food_id = document.getId();
                                                    FoodImageHolder = getView().findViewById(R.id.FoodDetailImage);
                                                    FoodDetailName = getView().findViewById(R.id.FoodDetailName);
                                                    FoodDetailPrice = getView().findViewById(R.id.FoodDetailPrice);
                                                    Picasso.get().load(document.get("img").toString()).into(FoodImageHolder);
                                                    String FOODNAME = document.get("name").toString();
                                                    String FOODPRICE = document.get("price").toString();
                                                    double FOODPRICEFLOAT = Double.parseDouble(FOODPRICE);

                                                    FoodDetailName.setText(FOODNAME);
                                                    FoodDetailPrice.setText(FOODPRICE);
                                                    foodDetails.put("name",FOODNAME);
                                                    foodDetails.put("price",FOODPRICEFLOAT);
                                                    foodDetails.put("stall",STORENAME);
                                                    foodDetails.put("unit",1);
                                                }
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    });
                }
            }
        });
    }
}