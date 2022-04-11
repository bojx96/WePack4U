package com.example.wepack4u.fragments;

import android.content.Intent;
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
import android.widget.TextView;

import com.example.wepack4u.activities.FoodDetail;
import com.example.wepack4u.utilities.FoodMenu;
import com.example.wepack4u.utilities.FoodStore;
import com.example.wepack4u.R;
import com.example.wepack4u.utilities.RecyclerItemClickListener;
import com.example.wepack4u.adaptors.FoodDisplayAdaptor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;


public class FoodDisplayFragment extends Fragment {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String auth_uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private String campus,storeName;
    private List<FoodMenu> foodMenu;
    TextView foodstallname;
    RecyclerView recyclerView;


    public FoodDisplayFragment() {
        // Required empty public constructor
    }


    public static FoodDisplayFragment newInstance(String param1, String param2) {
        FoodDisplayFragment fragment = new FoodDisplayFragment();
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
        return inflater.inflate(R.layout.fragment_food_display, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerViewfragment);
        storeName = getArguments().getString("storeName");
        System.out.println(storeName);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        /*Intent intent = new Intent(getContext(), FoodDetail.class);
                        intent.putExtra("foodName", foodMenu.get(position).name);
                        intent.putExtra("storeName",storeName);
                        startActivity(intent);*/
                        Bundle foodnamebundle = new Bundle();
                        Bundle storenamebundle = new Bundle();
                        storenamebundle.putString("storeName",storeName);
                        storenamebundle.putString("foodName",foodMenu.get(position).name);

                        Fragment nextFragment = new FoodDetailFragment();
                        nextFragment.setArguments(foodnamebundle);
                        nextFragment.setArguments(storenamebundle);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,nextFragment).addToBackStack("FoodDisplayStack").commit();

                    }

                    @Override public void onLongItemClick(View view, int position) { }
                })
        );
        getFoodList();

    }
    public void toFoodDetail(View v){
        startActivity(new Intent (getContext(), FoodDetail.class));
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
                                        String STORENAME = document.get("store_name").toString();
                                        System.out.println("store_name in display is = "+STORENAME);
                                        foodstallname = getView().findViewById(R.id.foodstallname);
                                        foodstallname.setText(STORENAME);
                                        db.collection("Campus").document(school_id).collection("food_stores")
                                                .document(store_id).collection("menu").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()){
                                                    QuerySnapshot querySnapshot = task.getResult();
                                                    foodMenu = querySnapshot.toObjects(FoodMenu.class);
                                                    FoodDisplayAdaptor foodDisplayAdaptor = new FoodDisplayAdaptor(getContext(), foodMenu);
                                                    recyclerView.setAdapter(foodDisplayAdaptor);
                                                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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