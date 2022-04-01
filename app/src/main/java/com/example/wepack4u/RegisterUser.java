package com.example.wepack4u;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.HashMap;
import java.util.Map;

public class RegisterUser extends AppCompatActivity {
    private FirebaseAuth mAuth;

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String auth_uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private String campus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        mAuth = FirebaseAuth.getInstance();

        EditText editfirst_name = findViewById(R.id.editfirst_name);
        EditText editlast_name = findViewById(R.id.editlast_name);
        EditText editcampus = findViewById(R.id.editcampus);
        EditText editemail = findViewById(R.id.editemail);
        EditText editpassword = findViewById(R.id.editpassword);
        Button submit_button = findViewById(R.id.submit_button);

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String first_name = editfirst_name.getText().toString();
                String last_name = editlast_name.getText().toString();
                String campus = editcampus.getText().toString();
                String email = editemail.getText().toString();
                String password = editpassword.getText().toString();
                if(first_name.isEmpty()){
                    editfirst_name.setError("First name is required");
                    editfirst_name.requestFocus();
                }
                if(last_name.isEmpty()){
                    editlast_name.setError("Last name is required");
                    editlast_name.requestFocus();
                }
                if(campus.isEmpty()){
                    editcampus.setError("Campus is required");
                    editcampus.requestFocus();
                }
                if(email.isEmpty()){
                    editemail.setError("Email is required");
                    editemail.requestFocus();
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    editemail.setError("Valid Email is required");
                    editemail.requestFocus();
                }
                if(password.isEmpty()){
                    editpassword.setError("Password is required");
                    editemail.requestFocus();
                }
                if(password.length() < 6){
                    editpassword.setError("Requires a minimum length of 6");
                    editemail.requestFocus();
                }
                else {
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Map<String, Object> user_details = new HashMap<>();
                                user_details.put("first_name", first_name);
                                user_details.put("last_name", last_name);
                                user_details.put("campus", campus);
                                user_details.put("email", email);
                                db.collection("users").document(auth_uid)
                                        .set(user_details).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(RegisterUser.this, "Successfully created user", Toast.LENGTH_LONG).show();
                                            finish();
                                        }
                                        else{
                                            Toast.makeText(RegisterUser.this, "Unsuccessful in User Creation",Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });
    }
}