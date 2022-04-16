package com.example.wepack4u.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.wepack4u.R;
import com.example.wepack4u.fragments.AboutYouFragment;
import com.example.wepack4u.fragments.CartFragment;
import com.example.wepack4u.fragments.ConfirmationFragment;
import com.example.wepack4u.fragments.StorePageFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class BottomNavMenu extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final String auth_uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private Fragment newFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav_menu);

        bottomNavigationView=findViewById(R.id.bottomNav);

        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,new StorePageFragment()).commit();
    }

    //Bottom Nav Bar navigates to where if any of the item is selected
    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {

                    Fragment fragment=null;

                    switch (menuitem.getItemId())
                    {
                        case R.id.storepage:
                            fragment = new StorePageFragment();
                            break;

                        case R.id.cart:
                            db.collection("users").document(auth_uid).collection("cart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful() && task.getResult().size()>0) {
                                        newFragment = new ConfirmationFragment();
                                    }
                                    else { newFragment = new CartFragment(); }
                                }
                            });
                            fragment = newFragment;
                            break;

                        case R.id.aboutyou:
                            fragment = new AboutYouFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).addToBackStack(null).commit();

                    return true;
                }
            };
    @Override
    public void onBackPressed(){
        //clear existing fragments in the stacks
        int fragmentcount = getSupportFragmentManager().getBackStackEntryCount();
        for (int i = 0; i < fragmentcount; i++){
            getSupportFragmentManager().popBackStack();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new StorePageFragment()).commit();
    }
}