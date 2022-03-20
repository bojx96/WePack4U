package com.example.wepack4u;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class db_test {
    //for database
    private DatabaseReference databaseReference;

    public db_test(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Pokemon.class.getSimpleName()); //not sure why get simplename
    }

    public Task<Void> add(Pokemon poke_name){ //good practice to do try-catch here so they don't pass in null poke_name
        return databaseReference.push().setValue(poke_name);
    }

}
