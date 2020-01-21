package com.example.healthy;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.healthy.MainActivity.MainActivity;
import com.example.healthy.ObserverPattern.Observer;
import com.example.healthy.logic.AppDAO;
import com.example.healthy.logic.AppLogic;
import com.example.healthy.logic.User;

public class ProfilePage extends AppCompatActivity implements View.OnClickListener, Observer {

    AppLogic appLogic = AppLogic.getInstance();
    AppDAO appDAO = AppDAO.getInstance();
    ImageView settingsTab;
    Button editProfile, backToMain, addEmail;
    public TextView name, age, school, email, year;
    User user = appLogic.getUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        appLogic.attachObserverToUser(this);

        appDAO.getUser();


        settingsTab = findViewById(R.id.settingsTab);
        settingsTab.setOnClickListener(this);
        editProfile = findViewById(R.id.redigerProfil);
        backToMain = findViewById(R.id.tilbageKnap);
        backToMain.setOnClickListener(this);
        addEmail = findViewById(R.id.tilfojForaldre);

        name = findViewById(R.id.textNavn);
        age = findViewById(R.id.textAlder);
        school = findViewById(R.id.textSkole);
        year = findViewById(R.id.textArgang);
        email = findViewById(R.id.textEmail);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileDialog dialog = new profileDialog();
                dialog.show(getSupportFragmentManager(), "profileDialog");
            }
        });

        updateView();
    }

    @Override
    public void onClick(View v) {
        if (v == settingsTab) {
            Intent intent = new Intent(this, SettingsPage.class);
            startActivity(intent);
        } else if (v == backToMain) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    @Override
    public void updateView() {
        user = appLogic.getUser();
        name.setText("Navn: " + user.getName());
        age.setText("Alder: "+user.getAge());
        email.setText("Email: "+user.getMail());
        year.setText("År: "+user.getYear());
        school.setText("Skole: "+user.getSchool());
    }
}