package com.example.healthy.Social;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.healthy.R;
import com.example.healthy.TopMenu;

public class SocialPage extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_page);


        /*if (savedInstanceState == null) {
            final BottomMenu fragment = new BottomMenu();
            getSupportFragmentManager().beginTransaction().add(R.id.menuFragment, fragment).commit();
        }*/

        if (savedInstanceState == null) {
            final TopMenu fragment = new TopMenu();
            getSupportFragmentManager().beginTransaction().add(R.id.topMenuFragment, fragment).commit();
        }

    }
}
