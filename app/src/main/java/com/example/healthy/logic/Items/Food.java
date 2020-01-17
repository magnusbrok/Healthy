package com.example.healthy.logic.Items;

public class Food extends Item {

    public Food(String name, int resID){
        super(name);
        setResID(resID);
    }

    public Food(){
        super();
    }
}