package com.example.healthy.Activity;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.healthy.R;
import com.example.healthy.TimeMenu;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityPageFragment extends Fragment{

    FragmentTransaction fragmentTransaction;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_activity_page, container, false);


        if (savedInstanceState == null){
            fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.FrameLayout, new DayActivities())
                    .commit();

            Bundle bundle = new Bundle();
            bundle.putString("Page", "AP");

            Fragment fragment = new TimeMenu();
            fragment.setArguments(bundle);

            fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.TimeFrame, fragment)
                    .commit();
        }
        return root;
    }
}
