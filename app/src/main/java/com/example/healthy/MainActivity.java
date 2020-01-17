package com.example.healthy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.healthy.Activity.ActivityPageFragment;
import com.example.healthy.Nutrition.NutritionPageFragment;
import com.example.healthy.Reward.RewardPageFragment;
import com.example.healthy.Social.SocialPageFragment;
import com.example.healthy.logic.AppLogic;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomMenu;
    AppLogic appLogic = AppLogic.getInstance();

    public static final String SHARED_PREFS = "shared_prefs";


    FirebaseFirestore db;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_menu);

        startService(new Intent(this, SensorService.class));

        if (savedInstanceState == null) {
            final HomePageFragment fragment = new HomePageFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.bottom_menu_fragment_View, fragment).addToBackStack(null).commit();
        }

        if (savedInstanceState ==  null) {
            final TopMenu topMenu = new TopMenu();
            getSupportFragmentManager().beginTransaction().add(R.id.TopMenuView, topMenu).commit();
        }


        final BottomNavigationView  bottomMenu = findViewById(R.id.bottom_navigation);
        bottomMenu.setItemIconTintList(null);
        bottomMenu.setSelectedItemId(R.id.homePage);
        bottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.activityPage:
                        if (savedInstanceState == null) {
                            final ActivityPageFragment fragment = new ActivityPageFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.bottom_menu_fragment_View, fragment).addToBackStack(null).commit();
                        }
                        break;
                    case R.id.nutriotionPage:
                        if (savedInstanceState == null) {
                            final NutritionPageFragment fragment = new NutritionPageFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.bottom_menu_fragment_View, fragment).addToBackStack(null).commit();
                        }
                        break;
                    case R.id.homePage:
                        if (savedInstanceState == null) {
                            final HomePageFragment fragment = new HomePageFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.bottom_menu_fragment_View, fragment).addToBackStack(null).commit();
                        }
                        break;
                    case R.id.socialPage:
                        if (savedInstanceState == null) {
                            final SocialPageFragment fragment = new SocialPageFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.bottom_menu_fragment_View, fragment).addToBackStack(null).commit();
                        }
                        break;
                    case R.id.rewardPage:
                        if (savedInstanceState == null) {
                            final RewardPageFragment fragment = new RewardPageFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.bottom_menu_fragment_View, fragment).addToBackStack(null).commit();
                        }
                        break;
                }
                return true;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottom_navigation_main, menu);
        return true;
    }

    public void changeMenu(int itemId) {
        bottomMenu = findViewById(R.id.bottom_navigation);
        bottomMenu.setSelectedItemId(itemId);


    }

    public void updateDatabase() {
        db = FirebaseFirestore.getInstance();

        // Add new user with points
        Map<String, Object> newUser = new HashMap<>();
        newUser.put ("Name", "Magnus");
        newUser.put ("ActivityPoints", appLogic.getActivityPoints());
        newUser.put ("RewardPoints", appLogic.getRewardPoints());
        newUser.put ("NutritionPoints", 100);

        db.collection("Brugere med point").document("1") // This is the ID of the document in the db. (Could be nothing - then it generates a random and unique ID)
                .set(newUser)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this, "Ny bruger er tilføjet", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("FEJL - brugeren blev ikke tilføjet", e.getMessage());
                    }
                });
    }
}