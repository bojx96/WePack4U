package com.example.wepack4u;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Draw_Data_test {
    //for database
    private DatabaseReference databaseReference;

    public Draw_Data_test(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Pokemon.class);
    }

}
