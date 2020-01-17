package com.example.healthy;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.healthy.logic.AppDAO;
import com.example.healthy.logic.AppLogic;
import com.example.healthy.logic.User;

public class profileDialog extends DialogFragment implements View.OnClickListener {

    private Button saveEdits;
    EditText name, age, email, school, year;
    AppLogic appLogic = AppLogic.getInstance();
    AppDAO appDAO = AppDAO.getInstance();
    User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_edit_profile, container, false);
        user = appLogic.getUser();

        saveEdits = view.findViewById(R.id.gemRedigering);
        saveEdits.setOnClickListener(this);

        name = view.findViewById(R.id.redigerNavn);
        name.setOnClickListener(this);
        age = view.findViewById(R.id.redigerAlder);
        email = view.findViewById(R.id.redigerEmail);
        school = view.findViewById(R.id.redigerSkole);
        year = view.findViewById(R.id.redigerArgang);

        name.setText("" + user.getName());
        age.setText("" + user.getAge());
        email.setText("" + user.getMail());
        school.setText("" + user.getSchool());
        year.setText("" + user.getYear());

        return view;
    }

    @Override
    public void onClick(View v) {

        if (v == saveEdits) {
            user.setName("" + name.getText());
            user.setSchool("" + school.getText());
            user.setAge("" + age.getText());
            user.setYear("" + year.getText());
            user.setMail("" + email.getText());
            appDAO.updateUser();
            dismiss();
        }
        if (v == name) {
            name.clearComposingText();
        }
    }
}