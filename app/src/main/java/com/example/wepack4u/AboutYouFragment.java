package com.example.wepack4u;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import androidx.annotation.NonNull;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;


public class AboutYouFragment extends Fragment {
    FirebaseFirestore db;
    private FirebaseUser user;
    private static final String CAMPUS = "campus";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    EditText editFirstName,editLastName;
    AutoCompleteTextView editCampus;
    Button submitButton;



    public AboutYouFragment() {
        // Required empty public constructor
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
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        user = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();
        DocumentReference userDoc = db.collection("users").document(user.getUid());


        this.editFirstName= view.findViewById(R.id.editTextFirstName);
        this.editLastName= view.findViewById(R.id.editTextLastName);
        this.editCampus = view.findViewById(R.id.autoCompleteTextViewUniversityName);
        this.submitButton = view.findViewById(R.id.submitButton);

        loadProfile(userDoc);

        String[] universities = getResources().getStringArray(R.array.university_array);
        AutoCompleteTextView editText = view.findViewById(R.id.autoCompleteTextViewUniversityName);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.dropdown, R.id.editTextUniversityName, universities);
        editText.setAdapter(adapter);


        submitButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String firstName = editFirstName.getText().toString();
                        String lastName = editLastName.getText().toString();
                        String campus = editCampus.getText().toString();
                        userDoc.update(FIRST_NAME,firstName);
                        userDoc.update(LAST_NAME,lastName);
                        userDoc.update(CAMPUS,campus);
//                        openNextPage();
                    }
                });

    }
    public void loadProfile(DocumentReference userDoc){
        userDoc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    String first_name = documentSnapshot.getString(FIRST_NAME);
                    String last_name = documentSnapshot.getString(LAST_NAME);
                    String campus = documentSnapshot.getString(CAMPUS);
                    editFirstName.setText(first_name);
                    editLastName.setText(last_name);
                    editCampus.setText(campus);
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
}