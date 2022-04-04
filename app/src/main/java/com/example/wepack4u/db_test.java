package com.example.wepack4u;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class db_test {
    //hi

    private FirebaseUser user;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String userID;
//    private Button logout;

    private String first_name;
    private String last_name;

    public db_test(){
        user = FirebaseAuth.getInstance().getCurrentUser();
        db.collection("Campus").get();
    }

}
