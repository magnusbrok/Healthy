package com.example.healthy.logic.Items;

public class Item {

    private String name;
    private int resID;

    public Item() {

    }

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
}