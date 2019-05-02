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
    TiledMap map;
    Rectangle rectangle;
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


    public Hero(TiledMap map, Rectangle rectangle,Level level) {
        this.map = map;
        this.rectangle = rectangle;
        this.level=level;
        this.spawnPosition=new Vector2(rectangle.getX(),rectangle.getY());
        this.position=new Vector2();
        this.lastFramePosition=new Vector2();
        this.velocity=new Vector2();
        init();
    }

    private void init(){
        lives= Constants.INITIAL_LIVES;
        ammo=Constants.INITIAL_AMMO;

        //starting sprite
        region= Assets.instance.heroeAssets.stand;

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

        // Land on/fall off platforms
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
        if(jumpState!=JumpState.GROUNDED){
            float jumpTimeSeconds=Utils.secondsSince(jumpStartTime);
            region= (TextureRegion) Assets.instance.heroeAssets.salta.getKeyFrame(jumpTimeSeconds);
        }else if(walkState==WalkState.STANDING){
            region=Assets.instance.heroeAssets.stand;
        }else if(walkState==WalkState.WALKING){
            float walkTimeSeconds = Utils.secondsSince(walkStartTime);
            region=(TextureRegion) Assets.instance.heroeAssets.andar.getKeyFrame(walkTimeSeconds);
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

    private void recoilFromEnemy(Facing direction) {

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

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public boolean isJumpButtonPressed() {
        return jumpButtonPressed;
    }

    public void setJumpButtonPressed(boolean jumpButtonPressed) {
        this.jumpButtonPressed = jumpButtonPressed;
    }

    public boolean isLeftButtonPressed() {
        return leftButtonPressed;
    }

    public void setLeftButtonPressed(boolean leftButtonPressed) {
        this.leftButtonPressed = leftButtonPressed;
    }

    public boolean isRightButtonPressed() {
        return rightButtonPressed;
    }

    public void setRightButtonPressed(boolean rightButtonPressed) {
        this.rightButtonPressed = rightButtonPressed;
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

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getSpawnPosition() {
        return spawnPosition;
    }

    public void setSpawnPosition(Vector2 spawnPosition) {
        this.spawnPosition = spawnPosition;
    }

    public Vector2 getLastFramePosition() {
        return lastFramePosition;
    }

    public void setLastFramePosition(Vector2 lastFramePosition) {
        this.lastFramePosition = lastFramePosition;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public Facing getFacing() {
        return facing;
    }

    public void setFacing(Facing facing) {
        this.facing = facing;
    }

    public JumpState getJumpState() {
        return jumpState;
    }

    public void setJumpState(JumpState jumpState) {
        this.jumpState = jumpState;
    }

    public WalkState getWalkState() {
        return walkState;
    }

    public void setWalkState(WalkState walkState) {
        this.walkState = walkState;
    }

    public TextureRegion getRegion() {
        return region;
    }

    public void setRegion(TextureRegion region) {
        this.region = region;
    }

    public long getWalkStartTime() {
        return walkStartTime;
    }

    public void setWalkStartTime(long walkStartTime) {
        this.walkStartTime = walkStartTime;
    }

    public long getJumpStartTime() {
        return jumpStartTime;
    }

    public void setJumpStartTime(long jumpStartTime) {
        this.jumpStartTime = jumpStartTime;
    }

    public Array<Death> getDeaths() {
        return deaths;
    }

    public void setDeaths(Array<Death> deaths) {
        this.deaths = deaths;
    }
}
