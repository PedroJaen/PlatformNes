package com.jaen.pedro.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.jaen.pedro.utils.Assets;
import com.jaen.pedro.utils.Constants;
import com.jaen.pedro.utils.Enums;
import com.jaen.pedro.utils.Utils;

public class Enemy {
    private Floor floor;
    private TextureRegion region;
    private Vector2 position;
    private long startTime;
    private int lives;
    private float width;
    private Enums.Facing facing;

    public Enemy(Floor floor) {
        this.floor = floor;
        lives= Constants.ENEMY_LIVES;
        facing= Enums.Facing.RIGHT;

        position=new Vector2(floor.getLeft(),floor.getTop());
        startTime = TimeUtils.nanoTime();
        width=0;
    }

    public void update(float delta){
        switch (facing){
            case LEFT:
                position.x -= Constants.ENEMY_MOVEMENT_SPEED * delta;
                break;
            case RIGHT:
                position.x += Constants.ENEMY_MOVEMENT_SPEED * delta;
                break;
        }

        if(floor.getRectangle().getWidth()>width){
            if (position.x < floor.getLeft()) {
                position.x = floor.getLeft();
                facing = Enums.Facing.RIGHT;
            } else if (position.x + width> floor.getRight()) {
                position.x = floor.getRight()-width;
                facing = Enums.Facing.LEFT;
            }
        }else{
            facing= Enums.Facing.LEFT;
            position.x=floor.getLeft();
        }
    }

    public void render(SpriteBatch batch){
        float elapsedTime = Utils.secondsSince(startTime);
        region= (TextureRegion) Assets.instance.enemigoAssets.caracol.getKeyFrame(elapsedTime,true);
        width=region.getRegionWidth();

        switch(facing){
            case RIGHT:
                Utils.drawTextureRegionflipedX(batch,region,position.x,position.y);
                break;
            case LEFT:
                Utils.drawTextureRegion(batch,region,position.x,position.y);
                break;
        }
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public TextureRegion getRegion() {
        return region;
    }

    public void setRegion(TextureRegion region) {
        this.region = region;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public Enums.Facing getFacing() {
        return facing;
    }

    public void setFacing(Enums.Facing facing) {
        this.facing = facing;
    }
}
