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
import com.example.healthy.logic.Items.Item;
import com.example.healthy.logic.Items.Reward;

import java.util.ArrayList;


public class CustomAdapter extends ArrayAdapter<Item> {


    public CustomAdapter(@NonNull Context context, ArrayList<Item> items) {
        super(context, R.layout.fragment_your_prize_page, items);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater lf = LayoutInflater.from(getContext());
        View customPrize = lf.inflate(R.layout.custom_list_item, parent,false);

        String prizeText = getItem(position).getName();
        TextView singlePrizeText =customPrize.findViewById(R.id.List_prizeText);
        ImageView singleImage = customPrize.findViewById(R.id.list_image);
        singlePrizeText.setText(prizeText);
        singleImage.setImageResource(getItem(position).getResID());
        return customPrize;
    }
}

