package com.example.wepack4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        Button login = findViewById(R.id.login_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //put login logic here, for now we hardcode
                String username_input = username.getText().toString();
                String password_input = password.getText().toString();
                password.setText("");
                if( (username_input.equals("admin") ) && (password_input.equals("123")) ){
                    finish();
                }
            }
        });

    }
    @Override
    public void onBackPressed() { } //override to disable onBackpress

    public void login(){ //ignore this
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}