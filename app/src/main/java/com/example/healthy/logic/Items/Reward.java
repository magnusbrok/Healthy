package com.example.healthy.logic.Items;

public class Reward extends Item {
    private int price;

    public Reward(String name, int resID, int price){
        super(name, resID);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
