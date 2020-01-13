package com.example.healthy;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class profileDialog extends DialogFragment {

    private Button saveEdits;
    EditText name, age, email, school, year;
    String updatedName, updatedAge, updatedEmail, updatedSchool, updatedYear;

    FirebaseFirestore db;

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

                ((ProfilePage) getActivity()).name.setText("Navn: " + navn);
                ((ProfilePage) getActivity()).age.setText("Alder: " + alder);
                ((ProfilePage) getActivity()).email.setText("E-mail: " + mail);
                ((ProfilePage) getActivity()).year.setText("Årgang: " + årgang);
                ((ProfilePage) getActivity()).school.setText("Skole: " + skole);

                updatedName = name.getText().toString();
                updatedAge = age.getText().toString();
                updatedEmail = email.getText().toString();
                updatedYear = year.getText().toString();
                updatedSchool = school.getText().toString();

                updateDatabase();

            }
        });
        return view;
    }

    public void updateDatabase() {
        db = FirebaseFirestore.getInstance();

        // Updating user
        Map<String, Object> updateUser = new HashMap<>();
        updateUser.put("Name", updatedName);
        updateUser.put("Age", updatedAge);
        updateUser.put("Email", updatedEmail);
        updateUser.put("Year", updatedYear);
        updateUser.put("School", updatedSchool);

        db.collection("Brugere med point").document("1").collection("Rediger profil").document("1") // This is the ID of the document in the db. (Could be nothing - then it generates a random and unique ID)
                .set(updateUser)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getActivity(), "Dine redigeringer er blevet gemt", Toast.LENGTH_LONG).show();
                        getDialog().dismiss();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("FEJL - redigeringerne blev ikke gemt", e.getMessage());
                        e.printStackTrace();
                        Toast.makeText(getActivity(), "FEJL - redigeringerne blev ikke gemt: "+e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        getDialog().dismiss();
                    }
                });
    }
}