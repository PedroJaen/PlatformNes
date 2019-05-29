package com.jaen.pedro.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.jaen.pedro.utils.Assets;
import com.jaen.pedro.utils.Constants;
import com.jaen.pedro.utils.Enums;
import com.jaen.pedro.utils.Utils;

public class Bullet {
    private Level level;
    private Vector2 position;
    private Enums.Facing facing;
    private boolean active;
    private Rectangle rectangle;
    private TextureRegion region;
    private long shootStartTime;
    private boolean enemigo;
    private int lvlCounter;
    private String nombre;

    public Bullet(Vector2 position, Enums.Facing facing,Level level,boolean enemigo,int lvlCounter,String nombre) {
        this.position = position;
        this.facing = facing;
        this.level=level;
        this.enemigo=enemigo;
        active=activeOrNot();
        this.lvlCounter=lvlCounter;
        this.nombre=nombre;

        shootStartTime= TimeUtils.nanoTime();

        if(enemigo){
            if(nombre.contains("firedemon")){
                region= (TextureRegion) Assets.instance.ataqueAssets.fire.getKeyFrame(0);
            }else if(nombre.contains("icedemon")){
                region= (TextureRegion) Assets.instance.ataqueAssets.icing.getKeyFrame(0);
            }else if(nombre.contains("tazon")){
                region= Assets.instance.ataqueAssets.flecha;
            }else if(nombre.contains("vasija")){
                region= (TextureRegion) Assets.instance.ataqueAssets.nunchaku.getKeyFrame(0);
            }else {
                region=Assets.instance.ataqueAssets.fireball;
            }
        }else{
            switch (lvlCounter){
                case 0:
                    region= Assets.instance.ataqueAssets.hacha;
                    break;
                case 2:
                    region= Assets.instance.ataqueAssets.hayujen;
                    break;
                default:
                    region=Assets.instance.ataqueAssets.fireball;
                    break;
            }
        }

        this.position.y-=region.getRegionHeight()/2;

        rectangle=new Rectangle(this.position.x,this.position.y,region.getRegionWidth(),region.getRegionHeight());
    }

    public void update(float delta){
        active=activeOrNot();

        switch(facing){
            case RIGHT:
                position.x+=delta* Constants.BULLET_MOVEMENT_SPEED;
                break;
            case LEFT:
                position.x-=delta* Constants.BULLET_MOVEMENT_SPEED;
                break;
        }
        rectangle.setPosition(position);

        if(enemigo){
            //si le doy al heroe
            Hero hero=level.getHero();
            if(rectangle.overlaps(hero.getRectangle())){
                hero.setLives(hero.getLives()-1);
                hero.recoilFromEnemy(facing);
                active=false;
            }
        }else{
            //si le doy a enemigo
            for(Enemy e:level.getEnemies()){
                if(rectangle.overlaps(e.getRectangle())){
                    e.setLives(e.getLives()-1);
                    active=false;
                }
            }
        }

    }

    public void render(SpriteBatch batch){
        float shootTimeSeconds = Utils.secondsSince(shootStartTime);
        if(!enemigo && lvlCounter==0){
            region= (TextureRegion) Assets.instance.ataqueAssets.hachaThrow.getKeyFrame(shootTimeSeconds);
        }else if(nombre.contains("firedemon")){
            region= (TextureRegion) Assets.instance.ataqueAssets.fire.getKeyFrame(shootTimeSeconds);
        }else if(nombre.contains("icedemon")){
            region= (TextureRegion) Assets.instance.ataqueAssets.icing.getKeyFrame(shootTimeSeconds);
        }else if(nombre.contains("vasija")){
            region= (TextureRegion) Assets.instance.ataqueAssets.nunchaku.getKeyFrame(shootTimeSeconds);
        }

        switch (facing){
            case RIGHT:
                Utils.drawTextureRegion(batch,region,position.x,position.y);
                break;
            case LEFT:
                Utils.drawTextureRegionflipedX(batch,region,position.x,position.y);
                break;
        }
    }

    private boolean activeOrNot(){
        float worldWidth = level.getViewport().getWorldWidth();
        float cameraX = level.getViewport().getCamera().position.x;

        if (position.x < cameraX - worldWidth / 2 || position.x > cameraX + worldWidth / 2) {
            return false;
        }else{
            return true;
        }
    }

    public boolean isActive() {
        return active;
    }

}
