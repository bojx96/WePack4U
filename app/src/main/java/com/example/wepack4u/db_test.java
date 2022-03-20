package com.example.wepack4u;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class db_test {
    //for database
    private DatabaseReference databaseReference;

    public db_test(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Pokemon.class.getSimpleName()); //not sure why get simplename
    }

    public Task<Void> add(Pokemon poke_details){ //good practice to do try-catch here so they don't pass in null poke_name
//        return databaseReference.push().setValue(poke_name);
        return databaseReference.child(poke_details.getName()).setValue(poke_details);
    }

//    public Task<Void> update(String key, HashMap<String, Object> hashMap){
//        return databaseReference.child(key).updateChildren(hashMap);
//    }
    public Task<Void> remove_pokemon(String name){
        return databaseReference.child(name).removeValue();
    }
}
