package com.jaen.pedro.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.jaen.pedro.utils.Assets;
import com.jaen.pedro.utils.Constants;
import com.jaen.pedro.utils.Enums;
import com.jaen.pedro.utils.Enums.*;
import com.jaen.pedro.utils.Utils;

public class Hero {
    private TiledMap map;
    private Rectangle rectangle;
    private int lvlCounter;
    public int lives;
    public int ammo;
    public boolean jumpButtonPressed;
    public boolean leftButtonPressed;
    public boolean rightButtonPressed;
    private Level level;
    private Vector2 position;
    private Vector2 spawnPosition;
    private Vector2 lastFramePosition;
    private Vector2 velocity;
    private Facing facing;
    private JumpState jumpState;
    private WalkState walkState;
    private TextureRegion region;
    private long walkStartTime;
    private long jumpStartTime;
    private Array<Death> deaths;


    public Hero(TiledMap map, Rectangle rectangle,Level level,int lvlCounter) {
        this.map = map;
        this.rectangle = rectangle;
        this.level=level;
        this.lvlCounter=lvlCounter;
        this.spawnPosition=new Vector2(rectangle.getX(),rectangle.getY());
        this.position=new Vector2();
        this.lastFramePosition=new Vector2();
        this.velocity=new Vector2();
        init();
    }

    private void init(){
        //starting sprite
        region= Assets.instance.heroeAssets.stands[lvlCounter];

        respawn();
    }

    private void respawn(){
        position.set(spawnPosition);
        lastFramePosition.set(spawnPosition);
        velocity.setZero();
        facing=Facing.RIGHT;
        jumpState=JumpState.FALLING;
        walkState=WalkState.STANDING;
    }

    public void update(float delta, Array<Floor> floors){
        lastFramePosition.set(position);
        velocity.y -= Constants.GRAVITY;
        position.mulAdd(velocity,delta);
        rectangle.setPosition(position);

        for(Death d:deaths){
            if (rectangle.overlaps(d.getRectangle())) {
                lives--;
                if (lives > 0) {
                    respawn();
                }
            }
        }

        // Land on/fall off platforms and walls
        if (jumpState != Enums.JumpState.JUMPING) {
            if (jumpState != JumpState.RECOILING) {
                jumpState = Enums.JumpState.FALLING;
            }

            for (Floor floor : floors) {
                if (landedOnPlatform(floor)) {
                    jumpState = JumpState.GROUNDED;
                    velocity.y = 0;
                    velocity.x = 0;
                    position.y = floor.getTop();
                }
            }
        }

        // Move left/right
        if (jumpState != JumpState.RECOILING) {
            boolean left = Gdx.input.isKeyPressed(Input.Keys.LEFT) || leftButtonPressed;
            boolean right = Gdx.input.isKeyPressed(Input.Keys.RIGHT) || rightButtonPressed;

            if (left && !right) {
                moveLeft(delta);
            } else if (right && !left) {
                moveRight(delta);
            } else {
                walkState = WalkState.STANDING;
            }
        }

        // Jump
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || jumpButtonPressed) {
            switch (jumpState) {
                case GROUNDED:
                    startJump();
                    break;
                case JUMPING:
                    continueJump();
            }
        } else {
            endJump();
        }

        // Shoot
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            shoot();
        }

        //collide with enemies
        for(Enemy e:level.getEnemies()){
            if(rectangle.overlaps(e.getRectangle())){
                if(position.x>e.getPosition().x){
                    recoilFromEnemy(Facing.LEFT);
                } else {
                    recoilFromEnemy(Facing.RIGHT);
                }
            }
        }

    }

    public void render(SpriteBatch batch){
        switch(lvlCounter){
            case 0:
                if(jumpState!=JumpState.GROUNDED){
                    float jumpTimeSeconds=Utils.secondsSince(jumpStartTime);
                    region= (TextureRegion) Assets.instance.heroeAssets.salta.getKeyFrame(jumpTimeSeconds);
                }else if(walkState==WalkState.STANDING){
                    region=Assets.instance.heroeAssets.stand;
                }else if(walkState==WalkState.WALKING){
                    float walkTimeSeconds = Utils.secondsSince(walkStartTime);
                    region=(TextureRegion) Assets.instance.heroeAssets.andar.getKeyFrame(walkTimeSeconds);
                }
                break;
            case 1:
                if(jumpState!=JumpState.GROUNDED){
                    region=Assets.instance.heroeAssets.salta_mappy;
                }else if(walkState==WalkState.STANDING){
                    region=Assets.instance.heroeAssets.stand_mappy;
                }else if(walkState==WalkState.WALKING){
                    float walkTimeSeconds = Utils.secondsSince(walkStartTime);
                    region=(TextureRegion) Assets.instance.heroeAssets.andar_mappy.getKeyFrame(walkTimeSeconds);
                }
                break;
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

    public void shoot(){
        if(ammo>0){
            ammo--;
            Vector2 bulletPosition;

            if(facing==Facing.RIGHT){
                bulletPosition=new Vector2(
                        position.x+rectangle.getWidth(),
                        position.y+(rectangle.getHeight()/2)
                );
            }else{
                bulletPosition=new Vector2(
                        position.x,
                        position.y+(rectangle.getHeight()/2)
                );
            }

            level.spawnBullet(bulletPosition,facing,false);
        }
    }

    boolean landedOnPlatform(Floor floor) {
        boolean leftFootIn = false;
        boolean rightFootIn = false;
        boolean straddle = false;

        if (lastFramePosition.y >= floor.getTop() &&
                position.y < floor.getTop()) {

            float leftFoot = position.x;
            float rightFoot = position.x + region.getRegionWidth();

            leftFootIn = (floor.getLeft() < leftFoot && floor.getRight() > leftFoot);
            rightFootIn = (floor.getLeft() < rightFoot && floor.getRight() > rightFoot);
            straddle = (floor.getLeft() > leftFoot && floor.getRight() < rightFoot);
        }
        return leftFootIn || rightFootIn || straddle;
    }

    private void moveLeft(float delta) {
        if (jumpState == JumpState.GROUNDED && walkState != WalkState.WALKING) {
            walkStartTime = TimeUtils.nanoTime();
        }
        walkState = WalkState.WALKING;
        facing = Facing.LEFT;
        position.x -= delta * Constants.HERO_MOVE_SPEED;
    }

    private void moveRight(float delta) {
        if (jumpState == JumpState.GROUNDED && walkState != WalkState.WALKING) {
            walkStartTime = TimeUtils.nanoTime();
        }
        walkState = WalkState.WALKING;
        facing = Facing.RIGHT;
        position.x += delta * Constants.HERO_MOVE_SPEED;
    }

    private void startJump() {
        jumpState = JumpState.JUMPING;
        jumpStartTime = TimeUtils.nanoTime();
        level.jump();
        continueJump();
    }

    private void continueJump() {
        if (jumpState == JumpState.JUMPING) {
            if (Utils.secondsSince(jumpStartTime) < Constants.MAX_JUMP_DURATION) {
                velocity.y = Constants.JUMP_SPEED;
            } else {
                endJump();
            }
        }
    }

    private void endJump() {
        if (jumpState == JumpState.JUMPING) {
            jumpState = JumpState.FALLING;
        }
    }

    public void recoilFromEnemy(Facing direction) {

        jumpState = JumpState.RECOILING;
        velocity.y = Constants.KNOCKBACK_VELOCITY.y;

        if (direction == Facing.LEFT) {
            velocity.x = -Constants.KNOCKBACK_VELOCITY.x;
        } else {
            velocity.x = Constants.KNOCKBACK_VELOCITY.x;
        }
    }

    public TiledMap getMap() {
        return map;
    }

    public void setMap(TiledMap map) {
        this.map = map;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setDeaths(Array<Death> deaths) {
        this.deaths = deaths;
    }

}
