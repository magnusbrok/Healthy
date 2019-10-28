package com.example.healthy.Social;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.healthy.BottomMenu;
import com.example.healthy.R;

public class SocialPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_page);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.menuFragment, new BottomMenu()).commit();
        }
    }
}
