package com.example.healthy;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
public class SettingsPage extends AppCompatActivity implements View.OnClickListener {

    ImageView profileTab;
    Button backToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings_page);

        profileTab = findViewById(R.id.profileTab);
        profileTab.setOnClickListener(this);
        backToMain = findViewById(R.id.tilbageKnap2);
        backToMain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == profileTab) {
            Intent intent = new Intent(this, ProfilePage.class);
            startActivity(intent);
        } else if (v == backToMain) {
            finish();
        }
    }
}