package com.example.wepack4u;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String auth_uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private EditText editFirstName, editLastName, editCampus, editEmail, editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        mAuth = FirebaseAuth.getInstance();

        this.editFirstName = findViewById(R.id.editfirst_name);
        this.editLastName = findViewById(R.id.editlast_name);
        this.editCampus = findViewById(R.id.editcampus);
        this.editEmail = findViewById(R.id.editemail);
        this.editPassword = findViewById(R.id.editpassword);

        Button submit_button = findViewById(R.id.submit_button);
        submit_button.setOnClickListener(this);

    }

    @Override
    public void onClick (View v){
        switch (v.getId()){
            case R.id.submit_button:
                validateInput();
                break;
        }
    }

    public void validateInput(){
        String first_name = editFirstName.getText().toString();
        String last_name = editLastName.getText().toString();
        String campus = editCampus.getText().toString();
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();
        if(first_name.isEmpty()){
            editFirstName.setError("First name is required");
            editFirstName.requestFocus();
            return;
        }
        if(last_name.isEmpty()){
            editLastName.setError("Last name is required");
            editLastName.requestFocus();
            return;
        }
        if(campus.isEmpty()){
            editCampus.setError("Campus is required");
            editCampus.requestFocus();
            return;
        }
        if(email.isEmpty()){
            editEmail.setError("Email is required");
            editEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editEmail.setError("Valid Email is required");
            editEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editPassword.setError("Password is required");
            editPassword.requestFocus();
            return;
        }
        if(password.length() < 6){
            editPassword.setError("Requires a minimum length of 6");
            editPassword.requestFocus();
            return;
        }

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