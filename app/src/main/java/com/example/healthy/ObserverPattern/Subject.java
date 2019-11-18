package com.example.healthy.ObserverPattern;

import java.util.ArrayList;

public abstract class Subject {

    private ArrayList<Observer> observers = new ArrayList<Observer>();

    final public void attachObserver(Observer observer){
        observers.add(observer);
    }

    final public void detachObserver(Observer observer){
        observers.remove(observer);
    }

    final protected void notifyChangeToObservers(){
        for (Observer observer : observers){
            observer.updateView();
        }
    }
}
