package com.example.healthy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfilePage extends AppCompatActivity implements View.OnClickListener {

    ImageView settingsTab;
    Button editProfile, backToMain, addEmail;
    public TextView name1, age1, school1, email1, year1;
    String name, age, email, year, school;

    private static final String SHARED_PREFS = "shared_prefs";
    private static final String AGE = "age";
    private static final String EMAIL = "email";
    private static final String YEAR = "year";
    private static final String SCHOOL = "school";
    private static final String NAME = "name";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        name = loadName();
        age = loadAge();
        email = loadEmail();
        year = loadYear();
        school = loadSchool();

        settingsTab = findViewById(R.id.settingsTab);
        settingsTab.setOnClickListener(this);
        editProfile = findViewById(R.id.redigerProfil);
        backToMain = findViewById(R.id.tilbageKnap);
        backToMain.setOnClickListener(this);
        addEmail = findViewById(R.id.tilfojForaldre);

        name1 = findViewById(R.id.textNavn);
        name1.setText("Navn: " + name);

        age1 = findViewById(R.id.textAlder);
        age1.setText("Alder: " + age);

        school1 = findViewById(R.id.textSkole);
        school1.setText("Skole: " + school);

        year1 = findViewById(R.id.textArgang);
        year1.setText("Årgang: " + year);

        email1 = findViewById(R.id.textEmail);
        email1.setText("E-mail: " + email);

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
        } else if (v == backToMain) {
            Intent intent = new Intent (this, MainActivity.class);
            startActivity(intent);
        }
    }

    public String loadName() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String getName = sharedPreferences.getString(NAME, "");
        return getName;
    }

    public String loadAge() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String getAge = sharedPreferences.getString(AGE, "");
        return getAge;
    }

    public String loadEmail() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String getEmail = sharedPreferences.getString(EMAIL, "");
        return getEmail;
    }

    public String loadSchool() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String getSchool = sharedPreferences.getString(SCHOOL, "");
        return getSchool;
    }

    public String loadYear() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String getYear = sharedPreferences.getString(YEAR, "");
        return getYear;
    }
}