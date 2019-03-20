package com.jaen.pedro.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jaen.pedro.PlatformNesGame;
import com.jaen.pedro.overlays.HudOverlay;
import com.jaen.pedro.utils.Enums;

public class GameScreen  extends ScreenAdapter {
    SpriteBatch batch;
    Texture img;
    Enums.Difficulty difficulty;
    PlatformNesGame game;
    HudOverlay hud;

    public GameScreen(PlatformNesGame game, Enums.Difficulty difficulty) {
        this.game=game;
        this.difficulty = difficulty;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        hud=new HudOverlay(batch,difficulty);
    }

    public void update(float delta){

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();

        batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        hud.dispose();
    }

}
