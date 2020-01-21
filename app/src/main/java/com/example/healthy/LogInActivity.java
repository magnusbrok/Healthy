package com.example.healthy;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {

    EditText userNameEditText, passwordEditText;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        boolean EMULATOR = Build.PRODUCT.contains("sdk") || Build.MODEL.contains("Android SDK");
        System.out.println(Build.PRODUCT);
        System.out.println(Build.MODEL);
        System.out.println(EMULATOR);
            if (!EMULATOR){Fabric.with(getApplication(), new Crashlytics());}

        userNameEditText = findViewById(R.id.editText_login_username);
        passwordEditText = findViewById(R.id.editText_login_password);
        loginButton = findViewById(R.id.button_login_login);

        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String username = userNameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if(validate(username, password)){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    private boolean validate(String username, String password){

        //method for authentication using the database, this has not been implemented at this release.

        return true;
    }
}