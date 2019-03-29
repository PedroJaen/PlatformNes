package com.jaen.pedro.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;

public class Level {
    private Hero hero;
    private DelayedRemovalArray<Key> key;
    private DelayedRemovalArray<Fruit> fruits;
    private DelayedRemovalArray<Ammo> ammunition;
    private Array<Floor> floors;
    private Exit exit;
    private Array<Death> deaths;

    public Level() {

    }

    public void update(float delta){

    }

    public void render(SpriteBatch batch){

        //pintamos al heroe
        hero.render(batch);

        //pintamos la llave
        if(key.size==1){
            key.get(0).render(batch);
        }

        //pintamos la fruta
        if(fruits.size>0){
            for(Fruit f:fruits){
                f.render(batch);
            }
        }

        //pintamos la municion extra
        if(ammunition.size>0){
            for(Ammo a:ammunition){
                a.render(batch);
            }
        }

    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
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

    public DelayedRemovalArray<Ammo> getAmmunition() {
        return ammunition;
    }

    public void setAmmunition(DelayedRemovalArray<Ammo> ammunition) {
        this.ammunition = ammunition;
    }

    public DelayedRemovalArray<Key> getKey() {
        return key;
    }

    public void setKey(DelayedRemovalArray<Key> key) {
        this.key = key;
    }
}
