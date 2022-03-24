package com.example.wepack4u;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Layout;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.rpc.context.AttributeContext;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db;
    protected boolean auth_key = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!auth_key){
            login_page();
        }
        setContentView(R.layout.activity_main);
        EditText edit_name = findViewById(R.id.edit_name);
        EditText edit_type = findViewById(R.id.edit_type);
        EditText remove_name = findViewById(R.id.remove_name);
        Button button = findViewById(R.id.btn_submit);
        Button remove_button = findViewById(R.id.remove_button);
        db = FirebaseFirestore.getInstance();

        TextView campus_list = findViewById(R.id.campus_list);

        db.collection("Campus").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"getting campus collection success", Toast.LENGTH_SHORT).show();
                    for(QueryDocumentSnapshot document : task.getResult()){
                        Log.d("QueryDoc", "onComplete: " + document.getId() + " data = " + document.getData());
                    }
                }
                else{
                    Toast.makeText(MainActivity.this,"Failure", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(MainActivity.this, "Successful",Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(MainActivity.this,"Successfully Deleted",Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {
                            Toast.makeText(MainActivity.this, "Error Occurred on Delete",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
    public void login_page(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}