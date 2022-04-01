package com.example.wepack4u;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class db_test {
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String auth_uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private String campus;

    public void load_food() {
        String school = campus;
        db.collection("Campus").whereEqualTo("name", school).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String school_id = document.getId();
                        Log.d("Mainactivity_DB", document.getId() + " => " + document.getData());
                        db.collection("Campus").document(school_id).collection("food_stores").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()){
                                    for (QueryDocumentSnapshot docs : task.getResult()){
                                        Log.d("Mainactivity_DB", docs.getId() + " => " + docs.getData());
                                    }
                                }
                            }
                        });
                    }
                }
                else{
                    Log.d("Mainactvity_DB", "Failed to fetch anything");
                }
            }
        });
    }

    public void get_campus(){ ;
        DocumentReference userRef = db.collection("users").document(auth_uid);
        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot userDoc = task.getResult();
                    if (userDoc.exists()){
                        campus =  userDoc.getString("campus");
                        Log.d("get_campus", "onComplete: " + campus);
                    }
                    else{
                        Log.d("get_campus", "doesnt exist");
                    }
                }
            }
        });
    }

}
