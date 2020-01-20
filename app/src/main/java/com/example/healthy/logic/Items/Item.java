package com.example.healthy.logic.Items;

import androidx.annotation.NonNull;

public class Item {

    private String name;
    private int resID;

//    public Item() {
//
//    }

    public Item(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setResID(int resID) {
        this.resID = resID;
    }

    public int getResID() {
        return resID;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}