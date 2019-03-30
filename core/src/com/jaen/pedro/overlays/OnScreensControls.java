package com.jaen.pedro.overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jaen.pedro.objects.Hero;
import com.jaen.pedro.utils.Assets;
import com.jaen.pedro.utils.Constants;
import com.jaen.pedro.utils.Utils;

public class OnScreensControls extends InputAdapter {
    public Viewport viewport;
    public Hero hero;
    private Vector2 moveLeftCenter;
    private Vector2 moveRightCenter;
    private Vector2 shootCenter;
    private Vector2 jumpCenter;
    private int moveLeftPointer;
    private int moveRightPointer;
    private int jumpPointer;

    public OnScreensControls() {
        this.viewport = new ExtendViewport(
                Constants.ONSCREEN_CONTROLS_VIEWPORT_SIZE,
                Constants.ONSCREEN_CONTROLS_VIEWPORT_SIZE);

        moveLeftCenter = new Vector2();
        moveRightCenter = new Vector2();
        shootCenter = new Vector2();
        jumpCenter = new Vector2();

        recalculateButtonPositions();
    }

    public void recalculateButtonPositions() {
        moveLeftCenter.set(Constants.BUTTON_RADIUS * 3 / 4, Constants.BUTTON_RADIUS * 3 / 4);
        moveRightCenter.set(Constants.BUTTON_RADIUS * 2, Constants.BUTTON_RADIUS * 3 / 4);
        shootCenter.set(
                viewport.getWorldWidth() - Constants.BUTTON_RADIUS * 2f,
                Constants.BUTTON_RADIUS * 3 / 4
        );
        jumpCenter.set(
                viewport.getWorldWidth() - Constants.BUTTON_RADIUS * 3 / 4,
                Constants.BUTTON_RADIUS * 3 / 4
        );
    }

    public void render(SpriteBatch batch){
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        if (!Gdx.input.isTouched(jumpPointer)) {
            hero.jumpButtonPressed = false;
            jumpPointer = 0;
        }

        if (!Gdx.input.isTouched(moveLeftPointer)) {
            hero.leftButtonPressed = false;
            moveLeftPointer = 0;
        }

        if (!Gdx.input.isTouched(moveRightPointer)) {
            hero.rightButtonPressed = false;
            moveRightPointer = 0;
        }

        Utils.drawTextureRegion(
                batch,
                Assets.instance.onscreenControlsAssets.moveLeft,
                moveLeftCenter,
                Constants.BUTTON_CENTER,
                0.5f
        );

        Utils.drawTextureRegion(
                batch,
                Assets.instance.onscreenControlsAssets.moveRight,
                moveRightCenter,
                Constants.BUTTON_CENTER,
                0.5f
        );

        Utils.drawTextureRegion(
                batch,
                Assets.instance.onscreenControlsAssets.shoot,
                shootCenter,
                Constants.BUTTON_CENTER,
                0.5f
        );

        Utils.drawTextureRegion(
                batch,
                Assets.instance.onscreenControlsAssets.jump,
                jumpCenter,
                Constants.BUTTON_CENTER,
                0.5f
        );

        batch.end();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 viewportPosition = viewport.unproject(new Vector2(screenX, screenY));

        if (viewportPosition.dst(shootCenter) < Constants.BUTTON_RADIUS) {

            hero.shoot();

        } else if (viewportPosition.dst(jumpCenter) < Constants.BUTTON_RADIUS) {

            jumpPointer = pointer;
            hero.jumpButtonPressed = true;

        } else if (viewportPosition.dst(moveLeftCenter) < Constants.BUTTON_RADIUS) {

            moveLeftPointer = pointer;
            hero.leftButtonPressed = true;

        } else if (viewportPosition.dst(moveRightCenter) < Constants.BUTTON_RADIUS) {

            moveRightPointer = pointer;
            hero.rightButtonPressed = true;

        }

        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector2 viewportPosition = viewport.unproject(new Vector2(screenX, screenY));

        if (pointer == moveLeftPointer && viewportPosition.dst(moveRightCenter) < Constants.BUTTON_RADIUS) {

            hero.leftButtonPressed = false;

            hero.rightButtonPressed = true;

            moveLeftPointer = 0;

            moveRightPointer = pointer;

        }

        if (pointer == moveRightPointer && viewportPosition.dst(moveLeftCenter) < Constants.BUTTON_RADIUS) {

            hero.rightButtonPressed = false;
            hero.leftButtonPressed = true;
            moveRightPointer = 0;
            moveLeftPointer = pointer;

        }

        return super.touchDragged(screenX, screenY, pointer);
    }
}
