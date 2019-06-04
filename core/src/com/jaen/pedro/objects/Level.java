package com.jaen.pedro.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jaen.pedro.overlays.HudOverlay;
import com.jaen.pedro.utils.Constants;
import com.jaen.pedro.utils.Enums;

public class Level implements Disposable {
    private boolean gameOver;
    private boolean victory;
    private Score score;
    private Enums.Difficulty difficulty;
    private Hero hero;
    private DelayedRemovalArray<Key> keys;
    private DelayedRemovalArray<Fruit> fruits;
    private DelayedRemovalArray<Ammo> ammunition;
    private DelayedRemovalArray<Bullet> bullets;
    private DelayedRemovalArray<Enemy> enemies;
    private Array<Floor> floors;
    private Exit exit;
    private boolean getKey;
    private HudOverlay hud;
    private Viewport viewport;
    private boolean mute;
    private Sound jump;
    private Sound shootHero;
    private Sound shootEnemie;
    private Sound getItem;
    private Sound explosion;
    private int lvlCounter;

    public Level(Enums.Difficulty difficulty,int lvlCounter) {
        this.difficulty=difficulty;
        this.lvlCounter=lvlCounter;
        gameOver=false;
        victory=false;
        getKey=false;

        bullets=new DelayedRemovalArray<Bullet>();

        if(!mute){
            jump= Gdx.audio.newSound(Gdx.files.internal(Constants.MUSICA_SALTO));
            shootHero= Gdx.audio.newSound(Gdx.files.internal(Constants.MUSICA_DISPARO1));
            shootEnemie= Gdx.audio.newSound(Gdx.files.internal(Constants.MUSICA_DISPARO2));
            getItem= Gdx.audio.newSound(Gdx.files.internal(Constants.MUSICA_OBJETO));
            explosion= Gdx.audio.newSound(Gdx.files.internal(Constants.MUSICA_EXPLOSION));
        }

    }

    public void update(float delta){
        if(hero.getLives()<=0 || hud.getWorldTimer()<=0){
            gameOver=true;
        }else if(hero.getRectangle().overlaps(exit.getRectangle()) && getKey){
            increaseScore(Constants.SCORE_EXIT);
            victory=true;
        }

        if(!gameOver && !victory){

            hero.update(delta,floors);

            //si obtenemos fruta
            fruits.begin();
            for(Fruit f:fruits){
                if(hero.getRectangle().overlaps(f.getRectangle())){
                    increaseScore(Constants.SCORE_FRUIT);

                    if(!mute){
                        getItem.play();
                    }
                    fruits.removeValue(f,false);
                }
            }
            fruits.end();

            //si obtenemos ammo
            ammunition.begin();
            for(Ammo a:ammunition){
                if(hero.getRectangle().overlaps(a.getRectangle())){
                    increaseScore(Constants.SCORE_FRUIT);

                    if(!mute){
                        getItem.play();
                    }
                    ammunition.removeValue(a,false);
                    hero.setAmmo(hero.getAmmo()+Constants.INITIAL_AMMO);
                }
            }
            ammunition.end();

            //si hay disparo
            bullets.begin();
            for(Bullet b:bullets){
                b.update(delta);
                if(!b.isActive()){
                    bullets.removeValue(b,false);
                }
            }
            bullets.end();

            //si obtenemos la llave
            keys.begin();
            for(Key k:keys){
                if(hero.getRectangle().overlaps(k.getRectangle())){
                    increaseScore(Constants.SCORE_PICK_KEY);

                    if(!mute){
                        getItem.play();
                    }
                    keys.removeValue(k,false);
                    getKey=true;
                }
            }
            keys.end();

            //enemigos
            enemies.begin();
            for(Enemy e:enemies){
                e.update(delta);
                if(e.getLives()==0){
                    if(!mute){
                        explosion.play();
                    }
                    increaseScore(Constants.SCORE_KILL);

                    enemies.removeValue(e,false);
                }
            }
            enemies.end();
        }
    }


    public void render(SpriteBatch batch){

        //pintamos al heroe
        if(hero.getLives()>0){
            hero.render(batch);
        }

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

        //pintamos las balas disparadas
        if(bullets.size>0){
            for(Bullet b:bullets){
                b.render(batch);
            }
        }

    }

    @Override
    public void dispose() {
        if(!mute){
            jump.dispose();
            shootHero.dispose();
            shootEnemie.dispose();
            getItem.dispose();
            explosion.dispose();
        }

    }

    public void spawnBullet(Vector2 position, Enums.Facing facing, boolean enemigo, String nombre){
        Bullet bullet=new Bullet(position,facing,this,enemigo,lvlCounter,nombre);
        bullets.add(bullet);
        if(bullet.isActive()){

            if(!enemigo && !mute){
                shootHero.play();
            }
            if(enemigo && !mute){
                shootEnemie.play();
            }
        }
    }

    private void increaseScore(int sumar){
        switch(difficulty){
            case EASY:
                score.increAseScore(sumar*Constants.FACIL_INC);
                break;
            case MEDIUM:
                score.increAseScore(sumar*Constants.MEDIO_INC);
                break;
            case HARD:
                score.increAseScore(sumar*Constants.DIFICIL_INC);
                break;
        }
    }

    public void jump(){
        if(!mute){
            jump.play();
        }

    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public void setFruits(DelayedRemovalArray<Fruit> fruits) {
        this.fruits = fruits;
    }

    public void setFloors(Array<Floor> floors) {
        this.floors = floors;
    }

    public void setExit(Exit exit) {
        this.exit = exit;
    }

    public void setAmmunition(DelayedRemovalArray<Ammo> ammunition) {
        this.ammunition = ammunition;
    }

    public void setKey(DelayedRemovalArray<Key> key) {
        this.keys = key;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isVictory() {
        return victory;
    }

    public void setScore(Score score) {
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

    public void setHud(HudOverlay hud) {
        this.hud = hud;
    }

    public void setMute(boolean mute) {
        this.mute = mute;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

}
