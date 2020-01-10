package com.example.healthy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfilePage extends AppCompatActivity implements View.OnClickListener {

    ImageView settingsTab;
    Button editProfile, backToMain, addEmail;
    public TextView name, age, school, email, year;

    FirebaseFirestore db;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        readUser();

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
    }

    @Override
    public void onClick(View v) {
        if (v == settingsTab) {
            Intent intent = new Intent(this, SettingsPage.class);
            startActivity(intent);
        } else if (v == backToMain) {
            finish();
        }
    }

    // Method to read data from FireStore (DON'T DELETE)
    private void readUser () {
        db = FirebaseFirestore.getInstance();
        DocumentReference user = db.collection("Brugere med point").document("1");
        user.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    DocumentSnapshot doc = task.getResult();
                    StringBuilder navn = new StringBuilder();
                    StringBuilder alder = new StringBuilder();
                    StringBuilder mail = new StringBuilder();
                    StringBuilder årgang = new StringBuilder();
                    StringBuilder skole = new StringBuilder();
                    navn.append("Navn: ").append(doc.getString("Name"));
                    alder.append("Alder: ").append(doc.get("Age"));
                    mail.append("Email: ").append(doc.get("Email"));
                    årgang.append("Årgang: ").append(doc.get("Year"));
                    skole.append("Skole: ").append(doc.get("School"));
                    name.setText(navn.toString());
                    age.setText(alder.toString());
                    email.setText(mail.toString());
                    year.setText(årgang.toString());
                    school.setText(skole.toString());
                }
            }
        });
    }
}