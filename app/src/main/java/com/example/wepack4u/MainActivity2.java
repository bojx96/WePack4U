package com.example.wepack4u;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;


public class MainActivity2 extends AppCompatActivity {
    FirebaseFirestore db;
    private FirebaseUser user;
    private static final String EMAIL = "email";
    private static final String CAMPUS = "campus";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Alternatively, below method is much more preferred because Auth can access anywhere as long as auth
        user = FirebaseAuth.getInstance().getCurrentUser();
//        Log.i("FirebaseAuth in Main2", "onCreate: " + user.getUid());
        db = FirebaseFirestore.getInstance();

        Button about_button = findViewById(R.id.about_button);

        about_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this,AboutActivity.class);
                startActivity(intent);
            }
        });

        db.collection("users").document(user.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.i("TAG",documentSnapshot.toString() );
                if (documentSnapshot.exists()){
                    String email = documentSnapshot.getString(EMAIL);
                    String campus = documentSnapshot.getString(CAMPUS);
                    String first_name = documentSnapshot.getString(FIRST_NAME);
                    String last_name = documentSnapshot.getString(LAST_NAME);
                    Log.i("TAG", "onSuccess: " + email +" "+campus+" "+first_name+" "+last_name);
                }
                else {
                    Toast.makeText(MainActivity2.this, "Document doesn't exist",Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity2.this,"Error occured",Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onFailure: " + e.toString());
            }
        });


        EditText edit_name = findViewById(R.id.edit_name);
        EditText edit_type = findViewById(R.id.edit_type);
        EditText remove_name = findViewById(R.id.remove_name);
        Button button = findViewById(R.id.btn_submit);
        Button remove_button = findViewById(R.id.remove_button);
//        db = FirebaseFirestore.getInstance();

        TextView campus_list = findViewById(R.id.campus_list);

        db.collection("Campus").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity2.this,"getting campus collection success", Toast.LENGTH_SHORT).show();
                    for(QueryDocumentSnapshot document : task.getResult()){
                        Log.d("QueryDoc", "onComplete: " + document.getId() + " data = " + document.getData());
                    }
                }
                else{
                    Toast.makeText(MainActivity2.this,"Failure", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button.setOnClickListener(v -> { //when button is click, do this
            String name = edit_name.getText().toString();
            String type = edit_type.getText().toString();
            Map<String, Object> pokemon = new HashMap<>();
            pokemon.put("name", name);
            pokemon.put("type", type);

            db.collection("Pokemon").add(pokemon).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(MainActivity2.this, "Successful",Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity2.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            });
        });

        remove_button.setOnClickListener(v -> {
            String name = remove_name.getText().toString();
            remove_name.setText("");
            DeleteData(name);
        });
    }
    private void DeleteData(String name){
        db.collection("Pokemon")
                .whereEqualTo("name", name)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful() && !task.getResult().isEmpty()){
                    DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                    String documentID = documentSnapshot.getId();
                    db.collection("Pokemon")
                            .document(documentID)
                            .delete()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(MainActivity2.this,"Successfully Deleted",Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {
                            Toast.makeText(MainActivity2.this, "Error Occurred on Delete",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}