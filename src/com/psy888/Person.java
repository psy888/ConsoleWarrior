package com.psy888;

public class Person {
    private int healthPoints;
    private String name;

    public Person(String name){
        this.name = name;
        this.healthPoints = 100;
    }

    public void heal(int points){
        this.healthPoints+=points;
    }

    public void damage(int points){
        this.healthPoints-=points;
    }

    public int getHP(){
        return this.healthPoints;
    }
    public void setHP(int hp){
        this.healthPoints = hp;
    }
    public String getName(){
        return name;
    }
    @Override
    public String toString() {
        return "Игрок :" + this.name + " | Здоровье : " + this.healthPoints + "%";
    }
}
