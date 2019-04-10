package com.jaen.pedro.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.jaen.pedro.overlays.HudOverlay;
import com.jaen.pedro.utils.Constants;

public class Level {
    public boolean gameOver;
    public boolean victory;
    public int score;
    private Hero hero;
    private DelayedRemovalArray<Key> keys;
    private DelayedRemovalArray<Fruit> fruits;
    private DelayedRemovalArray<Ammo> ammunition;
    private DelayedRemovalArray<Enemy> enemies;
    private Array<Floor> floors;
    private Exit exit;
    private boolean getKey;
    private HudOverlay hud;

    public Level() {
        gameOver=false;
        victory=false;
        score=0;
        getKey=false;
    }

    public void update(float delta){
        if(hero.getLives()<=0 || hud.getWorldTimer()<=0){
            gameOver=true;
        }else if(hero.getRectangle().overlaps(exit.getRectangle()) && getKey){
            score+=Constants.SCORE_EXIT;
            victory=true;
        }

        if(!gameOver && !victory){
            hero.update(delta,floors);

            //si obtenemos fruta
            for(Fruit f:fruits){
                if(hero.getRectangle().overlaps(f.getRectangle())){
                    score+= Constants.SCORE_FRUIT;
                    fruits.removeValue(f,false);
                }
            }

            //si obtenemos ammo
            for(Ammo a:ammunition){
                if(hero.getRectangle().overlaps(a.getRectangle())){
                    score+= Constants.SCORE_FRUIT;
                    ammunition.removeValue(a,false);
                    hero.setAmmo(hero.getAmmo()+Constants.INITIAL_AMMO);
                }
            }

            //si obtenemos la llave
            for(Key k:keys){
                if(hero.getRectangle().overlaps(k.getRectangle())){
                    score+= Constants.SCORE_PICK_KEY;
                    keys.removeValue(k,false);
                    getKey=true;
                }
            }

            //enemigos
            for(Enemy e:enemies){
                e.update(delta);
            }
        }
    }

    public void render(SpriteBatch batch){

        //pintamos al heroe
        hero.render(batch);

        //pintamos la llave
        if(keys.size==1){
            keys.get(0).render(batch);
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

        //pintamos los enemigos
        if(enemies.size>0){
            for(Enemy e:enemies){
                e.render(batch);
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

    public DelayedRemovalArray<Ammo> getAmmunition() {
        return ammunition;
    }

    public void setAmmunition(DelayedRemovalArray<Ammo> ammunition) {
        this.ammunition = ammunition;
    }

    public DelayedRemovalArray<Key> getKey() {
        return keys;
    }

    public void setKey(DelayedRemovalArray<Key> key) {
        this.keys = key;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isVictory() {
        return victory;
    }

    public void setVictory(boolean victory) {
        this.victory = victory;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public DelayedRemovalArray<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(DelayedRemovalArray<Enemy> enemies) {
        this.enemies = enemies;
    }

    public boolean isGetKey() {
        return getKey;
    }

    public void setGetKey(boolean getKey) {
        this.getKey = getKey;
    }

    public HudOverlay getHud() {
        return hud;
    }

    public void setHud(HudOverlay hud) {
        this.hud = hud;
    }
}
