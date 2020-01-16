package com.example.healthy.logic.Items;

public abstract class Item {

    private String name;
    private int resID;

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
}
