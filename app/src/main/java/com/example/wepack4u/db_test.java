package com.example.wepack4u;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class db_test {

    private String first_name;
    private String last_name;
    private FirebaseFirestore db;

    public db_test(){
        db = FirebaseFirestore.getInstance();
        db.collection("users").get();
    }

}
