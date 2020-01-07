package com.example.healthy;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import static android.content.Context.MODE_PRIVATE;

public class profileDialog extends DialogFragment {

    private Button saveEdits;
    EditText name, age, email, school, year;

    private static final String SHARED_PREFS = "shared_prefs";
    private static final String AGE = "age";
    private static final String EMAIL = "email";
    private static final String YEAR = "year";
    private static final String SCHOOL = "school";
    private static final String NAME = "name";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_edit_profile, container, false);

        saveEdits = view.findViewById(R.id.gemRedigering);
        name = view.findViewById(R.id.redigerNavn);
        age = view.findViewById(R.id.redigerAlder);
        email = view.findViewById(R.id.redigerEmail);
        school = view.findViewById(R.id.redigerSkole);
        year = view.findViewById(R.id.redigerArgang);

        saveEdits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String navn = name.getText().toString();
                String alder = age.getText().toString();
                String mail = email.getText().toString();
                String årgang = year.getText().toString();
                String skole = school.getText().toString();

                ((ProfilePage)getActivity()).name1.setText("Navn: " + navn);
                ((ProfilePage)getActivity()).age1.setText("Alder: " + alder);
                ((ProfilePage)getActivity()).email1.setText("E-mail: " + mail);
                ((ProfilePage)getActivity()).year1.setText("Årgang: " + årgang);
                ((ProfilePage)getActivity()).school1.setText("Skole: " + skole);

                saveProfileEdits();
                getDialog().dismiss();
            }
        });
        return view;
    }

    public void saveProfileEdits() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences (SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(NAME, String.valueOf(name.getText()));
        editor.putString(AGE, String.valueOf(age.getText()));
        editor.putString(EMAIL, String.valueOf(email.getText()));
        editor.putString(SCHOOL, String.valueOf(school.getText()));
        editor.putString(YEAR, String.valueOf(year.getText()));
        editor.apply();
    }
}