package com.example.wepack4u;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView logo = findViewById(R.id.Logo_image);
        EditText email = findViewById(R.id.editemail);
        EditText password = findViewById(R.id.editpassword);
        Button login = findViewById(R.id.login_button);
        mAuth = FirebaseAuth.getInstance();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_input = email.getText().toString();
                String password_input = password.getText().toString();
                password.setText("");
                if (email_input.isEmpty()){
                    email.setError("Email cannot be empty!");
                    email.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email_input).matches()){
                    email.setError("Email is Invalid");
                    email.requestFocus();
                    return;
                }

                if(password_input.isEmpty()){
                    password.setError("Password cannot be empty!");
                    password.requestFocus();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email_input,password_input)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(MainActivity.this, StorePage.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Failed to login, Please check password", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });


    }
    @Override
    public void onBackPressed() { } //override to disable onBackpress

    public void register_onClick(View view){
        Intent intent = new Intent(MainActivity.this, RegisterUser.class);
        startActivity(intent);
    }
}