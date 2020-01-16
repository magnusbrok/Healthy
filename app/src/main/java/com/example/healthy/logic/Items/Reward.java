package com.example.healthy.logic.Items;

public class Reward extends Item {
    private int price;

    public Reward(String name){
        super(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    public String name;

    public int amount;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
