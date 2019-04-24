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

    public Bullet(Vector2 position, Enums.Facing facing,Level level) {
        this.position = position;
        this.facing = facing;
        this.level=level;
        active=true;

        shootStartTime= TimeUtils.nanoTime();
        region= Assets.instance.ataqueAssets.hacha;
        rectangle=new Rectangle(position.x,position.y,region.getRegionWidth(),region.getRegionHeight());
    }

    public void update(float delta){
        switch(facing){
            case RIGHT:
                position.x+=delta* Constants.BULLET_MOVEMENT_SPEED;
                break;
            case LEFT:
                position.x-=delta* Constants.BULLET_MOVEMENT_SPEED;
                break;
        }
        rectangle.setPosition(position);

        //si le doy a enemigo
        for(Enemy e:level.getEnemies()){
            if(rectangle.overlaps(e.getRectangle())){
                e.setLives(e.getLives()-1);
                active=false;
            }
        }

        final float worldWidth = level.getViewport().getWorldWidth();
        final float cameraX = level.getViewport().getCamera().position.x;

        if (position.x < cameraX - worldWidth / 2 || position.x > cameraX + worldWidth / 2) {
            active = false;
        }
    }

    public void render(SpriteBatch batch){
        float shootTimeSeconds = Utils.secondsSince(shootStartTime);
        region= (TextureRegion) Assets.instance.ataqueAssets.hachaThrow.getKeyFrame(shootTimeSeconds);

        switch (facing){
            case RIGHT:
                Utils.drawTextureRegion(batch,region,position.x,position.y);
                break;
            case LEFT:
                Utils.drawTextureRegionflipedX(batch,region,position.x,position.y);
                break;
        }
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Enums.Facing getFacing() {
        return facing;
    }

    public void setFacing(Enums.Facing facing) {
        this.facing = facing;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
