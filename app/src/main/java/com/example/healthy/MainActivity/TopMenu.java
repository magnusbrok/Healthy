package com.example.healthy.MainActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.healthy.ProfilePage;
import com.example.healthy.R;
import com.example.healthy.SettingsPage;

public class TopMenu extends Fragment implements View.OnClickListener {

    private ImageView profileTab, settingsTab;

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