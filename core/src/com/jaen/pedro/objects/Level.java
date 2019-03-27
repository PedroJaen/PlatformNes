package com.jaen.pedro.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;

public class Level {
    private Hero hero;
    private Key key;
    private DelayedRemovalArray<Fruit> fruits;
    private Array<Floor> floors;
    private Exit exit;
    private Array<Death> deaths;

    public Level() {
    }

    public void update(float delta){

    }

    public void render(SpriteBatch batch){

    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public DelayedRemovalArray<Fruit> getFruits() {
        return fruits;
    }

    public void setFruits(DelayedRemovalArray<Fruit> fruits) {
        this.fruits = fruits;
    }

    public Array<Floor> getFloors() {
        return floors;
    }

    public void setFloors(Array<Floor> floors) {
        this.floors = floors;
    }

    public Exit getExit() {
        return exit;
    }

    public void setExit(Exit exit) {
        this.exit = exit;
    }

    public Array<Death> getDeaths() {
        return deaths;
    }

    public void setDeaths(Array<Death> deaths) {
        this.deaths = deaths;
    }
}
