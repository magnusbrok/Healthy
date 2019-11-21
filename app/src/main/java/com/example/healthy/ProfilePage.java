package com.example.healthy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfilePage extends AppCompatActivity implements View.OnClickListener {

    ImageView settingsTab;
    Button editProfile;
    public TextView name1, age1, school1, email1, year1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        settingsTab = findViewById(R.id.settingsTab);
        settingsTab.setOnClickListener(this);

        editProfile = findViewById(R.id.redigerProfil);

        name1 = findViewById(R.id.textNavn);
        age1 = findViewById(R.id.textAlder);
        school1 = findViewById(R.id.textSkole);
        year1 = findViewById(R.id.textArgang);
        email1 = findViewById(R.id.textEmail);

        if (savedInstanceState == null) {
            final BottomMenu fragment = new BottomMenu();
            getSupportFragmentManager().beginTransaction().add(R.id.menuFragment, fragment).commit();
        }

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileDialog dialog = new profileDialog();
                dialog.show(getSupportFragmentManager(), "profileDialog");
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == settingsTab) {
            Intent intent = new Intent(this, SettingsPage.class);
            startActivity(intent);
        }
    }
}