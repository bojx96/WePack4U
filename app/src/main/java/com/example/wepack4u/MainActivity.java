package com.example.wepack4u;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText edit_name = findViewById(R.id.edit_name);
        final EditText edit_type = findViewById(R.id.edit_type);
        final EditText remove_name = findViewById(R.id.remove_name);
        Button button = findViewById(R.id.btn_submit);
        Button remove_button = findViewById(R.id.remove_button);
        db_test db_poke = new db_test();
        button.setOnClickListener(v -> { //when button is click, do this
            //design logic
            Pokemon pokemon = new Pokemon(edit_name.getText().toString(),edit_type.getText().toString());
            db_poke.add(pokemon).addOnSuccessListener(success ->{
                Toast.makeText(this, "Record is inserted", Toast.LENGTH_SHORT).show(); //if successful, do this
            }).addOnFailureListener(error -> {
                Toast.makeText(this, ""+ error.getMessage(), Toast.LENGTH_SHORT).show(); //if error, do this
            });
        });

        remove_button.setOnClickListener(v -> {
            if (remove_name.getText().toString().isEmpty()){
                Toast.makeText(this,"Remove Field cannot be empty", Toast.LENGTH_SHORT).show();
            }
            else{
                db_poke.remove_pokemon(remove_name.getText().toString());
            }
        });


    }
}