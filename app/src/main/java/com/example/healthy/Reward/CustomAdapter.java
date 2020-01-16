package com.example.healthy.Reward;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.healthy.R;


public class CustomAdapter extends ArrayAdapter<String> {


    public CustomAdapter(@NonNull Context context, String[] namesOfPrizes) {
        super(context, R.layout.fragment_your_prize_page, namesOfPrizes);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater lf = LayoutInflater.from(getContext());
        View customPrize = lf.inflate(R.layout.activity_your_prize_elements, parent,false);

        String prizeText = getItem(position);
        TextView singlePrizeText =customPrize.findViewById(R.id.prizeText);
        ImageView singleImage = customPrize.findViewById(R.id.prizeImage);
        singlePrizeText.setText(prizeText);
        singleImage.setImageResource(R.drawable.activity_guy_final);
        return customPrize;
    }
}

