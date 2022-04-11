package com.example.wepack4u.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wepack4u.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AboutActivity extends AppCompatActivity {
    FirebaseFirestore db;
    private FirebaseUser user;
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private String campus;

    EditText editFirstName,editLastName;
    AutoCompleteTextView editText;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        editFirstName= findViewById(R.id.editTextFirstName);
        editLastName= findViewById(R.id.editTextLastName);
        submitButton = findViewById(R.id.submitButton);
        //catch UID
        user = FirebaseAuth.getInstance().getCurrentUser();

        String[] universities = getResources().getStringArray(R.array.university_array);

        this.editText = findViewById(R.id.autoCompleteTextViewUniversityName);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.dropdown, R.id.editTextUniversityName, universities);
        editText.setAdapter(adapter);

        editText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                System.out.println("here");
                campus =adapterView.getItemAtPosition(position).toString();
                System.out.println("campus"+campus);

            }
        });


        Log.i("AboutActivity", "onCreate: " + user.getUid());
        db = FirebaseFirestore.getInstance();
        db.collection("users").document(user.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.i("TAG",documentSnapshot.toString() );
                if (documentSnapshot.exists()){
                    String first_name = documentSnapshot.getString(FIRST_NAME);
                    String last_name = documentSnapshot.getString(LAST_NAME);
                    editFirstName.setText(first_name);
                    editLastName.setText(last_name);
                }
                else {
                    Toast.makeText(AboutActivity.this, "Document doesn't exist",Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AboutActivity.this,"Error occured",Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onFailure: " + e.toString());
            }
        });


        submitButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                      openNextPage();
                        String firstName = editFirstName.getText().toString();
                        String lastName = editLastName.getText().toString();
                        switch (view.getId()){
                            case R.id.submitButton:
                                validateInput();
                                break;
                        }
                    }
                });
    }

    public void validateInput(){

        if(campus==null){
            System.out.println("worked");
            editText.setError("Campus is required");
            editText.requestFocus();
            return;
        }
    }
    @Override
    public void onBackPressed() {
        System.exit(0);
    }


}