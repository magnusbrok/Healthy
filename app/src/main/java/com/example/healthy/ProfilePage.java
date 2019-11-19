package com.example.healthy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfilePage extends AppCompatActivity implements View.OnClickListener {

    ImageView settingsTab;
    TextView
    Button editProfile;
    Dialog profileDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        profileDialog = new Dialog (this);

        settingsTab = findViewById(R.id.settingsTab);
        settingsTab.setOnClickListener(this);

        if (savedInstanceState == null) {
            final BottomMenu fragment = new BottomMenu();
            getSupportFragmentManager().beginTransaction().add(R.id.menuFragment, fragment).commit();
        }
    }
    public void showPopUp (View v) {
        TextView lukPopUp;
        EditText redigerNavn, redigerEmail, redigerArgang, redigerAlder, redigerSkole;
        Button gemRedigeringer;
        profileDialog.setContentView(R.layout.activity_edit_profile_page);
        lukPopUp = profileDialog.findViewById(R.id.lukPopUp);
        gemRedigeringer = profileDialog.findViewById(R.id.gemRedigering);
        lukPopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            profileDialog.dismiss();
            }
        });
        profileDialog.show();
    }

    @Override
    public void onClick(View v) {
        if (v == settingsTab) {
            Intent intent = new Intent(this, SettingsPage.class);
            startActivity(intent);
        }
    }
}