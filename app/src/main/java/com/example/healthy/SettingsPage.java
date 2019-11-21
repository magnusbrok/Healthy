package com.example.healthy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SettingsPage extends AppCompatActivity implements View.OnClickListener {

    ImageView profileTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        profileTab = findViewById(R.id.profileTab);
        profileTab.setOnClickListener(this);

        /*if (savedInstanceState == null) {
            final BottomMenu fragment = new BottomMenu();
            getSupportFragmentManager().beginTransaction().add(R.id.menuFragment, fragment).commit();
        }*/
    }

    @Override
    public void onClick(View v) {
        if (v == profileTab) {
            Intent intent = new Intent(this, ProfilePage.class);
            startActivity(intent);
        }
    }
}