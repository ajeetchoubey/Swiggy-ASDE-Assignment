package org.example;

public class Player{
    private int id;
    private String name;
    private int health;
    private int strength;
    private int attack;

    public Player(int id, String name, int health, int strength, int attack){
        this.id = id;
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.attack = attack;
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public int getAttack() {
        return attack;
    }
}