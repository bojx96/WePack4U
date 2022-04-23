package com.example.wepack4u.fragments;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.wepack4u.R;
import com.example.wepack4u.activities.MainActivity;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import androidx.annotation.NonNull;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;


public class AboutYouFragment extends Fragment{
    FirebaseFirestore db;
    private FirebaseUser user;
    private String campus;
    private static final String CAMPUS = "campus";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    EditText editFirstName,editLastName;
    Button submitButton,logoutButton;
    AutoCompleteTextView editCampus;


    public AboutYouFragment() {
        // Required empty public constructor ..
    }

    public static AboutYouFragment newInstance(String param1, String param2) {
        AboutYouFragment fragment = new AboutYouFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_you, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initVars(view);
        dropMenu(view);
        fetchData();
        setOnClickListeners();

    }

    void initVars(View view){
        user = FirebaseAuth.getInstance().getCurrentUser();
        this.editFirstName= view.findViewById(R.id.editTextFirstName);
        this.editLastName= view.findViewById(R.id.editTextLastName);
        this.submitButton = view.findViewById(R.id.submitButton);
        this.logoutButton = view.findViewById(R.id.logoutButton);
    }

    void dropMenu(View view) {
        String[] universities = getResources().getStringArray(R.array.university_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.dropdown, R.id.editTextUniversityName, universities);
        editCampus = view.findViewById(R.id.autoCompleteTextViewUniversityName);
        editCampus.setAdapter(adapter);

        editCampus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                campus =adapterView.getItemAtPosition(position).toString();
            }
        });
    }

    void fetchData(){
        db = FirebaseFirestore.getInstance();
        DocumentReference userDoc = db.collection("users").document(user.getUid());
        userDoc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.i("TAG",documentSnapshot.toString() );
                if (documentSnapshot.exists()){
                    String first_name = documentSnapshot.getString(FIRST_NAME);
                    String last_name = documentSnapshot.getString(LAST_NAME);
                    String campus = documentSnapshot.getString(CAMPUS);
                    editFirstName.setText(first_name);
                    editLastName.setText(last_name);
                }
                else {
                    Toast.makeText(getContext(), "Document doesn't exist",Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(),"Error occured",Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onFailure: " + e.toString());
            }
        });
    }

    void setOnClickListeners(){
        submitButton.setOnClickListener(submitOnClickListener());
        logoutButton.setOnClickListener(logoutOnClickListener());
    }

    View.OnClickListener submitOnClickListener(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                DocumentReference userDoc = db.collection("users").document(user.getUid());
                String firstName = editFirstName.getText().toString();
                String lastName = editLastName.getText().toString();
                if ( validateInput(firstName, lastName) ){
                    userDoc.update("first_name",firstName);
                    userDoc.update("last_name",lastName);
                    userDoc.update("campus", campus);
                    Toast.makeText(getContext(),"Account Successfully updated!",Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    View.OnClickListener logoutOnClickListener(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        };
    }

    public boolean validateInput(String firstName, String lastName){
        if (campus == null){
            Toast.makeText(getContext(),"Please choose campus!",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(firstName.isEmpty()){
            editFirstName.setError("First Name is required");
            editFirstName.requestFocus();
            return false;
        }
        if(lastName.isEmpty()){
            editLastName.setError("Last Name is required");
            editLastName.requestFocus();
            return false;
        }
        return true;
    }

}