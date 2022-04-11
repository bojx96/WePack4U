package com.example.wepack4u.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;

import com.example.wepack4u.R;
import com.example.wepack4u.adaptors.StoreAdapter;
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

public class StorePageFragment extends Fragment{

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String auth_uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private String campus;
    private List<FoodStore> foodStores;
    RecyclerView recyclerView;

    public StorePageFragment() {
        // Required empty public constructor
    }

    public static StorePageFragment newInstance(String param1, String param2) {
        StorePageFragment fragment = new StorePageFragment();
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
        return inflater.inflate(R.layout.fragment_store_page, container, false);
    }

    public void getStoreList(){
        DocumentReference userRef = db.collection("users").document(auth_uid);
        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot userDoc = task.getResult();
                    if (userDoc.exists()){
                        campus = (userDoc.getString("campus"));
                        storeList(campus);
                    }
                    else{
                        Log.d("get_campus", "does not exist");
                    }
                }
            }
        });
    }

    public void storeList(String campus_name) {
        db.collection("Campus").whereEqualTo("name", campus_name).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String school_id = document.getId();
                        db.collection("Campus").document(school_id).collection("food_stores").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()){
                                    QuerySnapshot querySnapshot = task.getResult();
                                    foodStores = querySnapshot.toObjects(FoodStore.class);
                                    StoreAdapter storeAdapter = new StoreAdapter(getContext(), foodStores);
                                    recyclerView.setAdapter(storeAdapter);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                    System.out.println(foodStores.get(0).store_name);
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerviewstorefragment);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Bundle storenamebundle = new Bundle();
                        storenamebundle.putString("storeName",foodStores.get(position).store_name);

                        Fragment nextFragment = new FoodDisplayFragment();
                        nextFragment.setArguments(storenamebundle);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,nextFragment).addToBackStack("StorePageStack").commit();
                    }


                    @Override public void onLongItemClick(View view, int position) { }
                })
        );
        getStoreList();
    }
}