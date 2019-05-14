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

    public Bullet(Vector2 position, Enums.Facing facing,Level level,boolean enemigo,int lvlCounter) {
        this.position = position;
        this.facing = facing;
        this.level=level;
        this.enemigo=enemigo;
        active=activeOrNot();
        this.lvlCounter=lvlCounter;

        shootStartTime= TimeUtils.nanoTime();

        if(enemigo){
            region=Assets.instance.ataqueAssets.fireball;
        }else{
            switch (lvlCounter){
                case 0:
                    region= Assets.instance.ataqueAssets.hacha;
                    break;
                default:
                    region=Assets.instance.ataqueAssets.fireball;
                    break;
            }
        }

        rectangle=new Rectangle(position.x,position.y,region.getRegionWidth(),region.getRegionHeight());
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
        if(!enemigo && lvlCounter==0){
            float shootTimeSeconds = Utils.secondsSince(shootStartTime);
            region= (TextureRegion) Assets.instance.ataqueAssets.hachaThrow.getKeyFrame(shootTimeSeconds);
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
