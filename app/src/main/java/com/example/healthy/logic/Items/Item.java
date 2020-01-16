package com.example.healthy.logic.Items;

public abstract class Item {

    private String name;
    private int resID;

    public Item(String name, int resID){
        this.name = name;
        this.resID = resID;
    }

    public String getName() {
        return name;
    }

    public int getResID() {
        return resID;
    }
}
