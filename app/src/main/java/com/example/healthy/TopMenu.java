package com.example.healthy;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class TopMenu extends Fragment implements View.OnClickListener {

    ImageView profileTab, settingsTab;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_top_menu, container, false);

        profileTab = root.findViewById(R.id.profileTab);
        settingsTab = root.findViewById(R.id.settingsTab);
        profileTab.setOnClickListener(this);
        settingsTab.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        if (v == profileTab) {
            Intent intent = new Intent(getActivity(), ProfilePage.class);
            startActivity(intent);
        } else if (v == settingsTab) {
            Intent intent = new Intent(getActivity(), SettingsPage.class);
            startActivity(intent);
        }
    }
}

/*

Hvis vi skal gå tilbage til det grønne logo:

 <ImageView
 android:id="@+id/logoHealthy"
 android:layout_width="120dp"
 android:layout_height="50dp"
 android:layout_marginEnd="82dp"
 android:layout_marginRight="82dp"
 android:src="@drawable/healthy_icon"
 app:layout_constraintBottom_toBottomOf="@+id/toolbar"
 app:layout_constraintEnd_toStartOf="@+id/settingsTab"
 app:layout_constraintStart_toEndOf="@+id/profileTab"
 app:layout_constraintTop_toTopOf="parent" />

 */