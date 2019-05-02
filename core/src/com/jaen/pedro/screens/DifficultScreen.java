package com.jaen.pedro.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.jaen.pedro.PlatformNesGame;
import com.jaen.pedro.utils.Assets;
import com.jaen.pedro.utils.Constants;
import com.jaen.pedro.utils.Enums;
import com.jaen.pedro.utils.Utils;

public class DifficultScreen extends InputAdapter implements Screen {
    PlatformNesGame game;
    SpriteBatch batch;
    FitViewport viewport;
    BitmapFont font;

    public DifficultScreen(PlatformNesGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch=new SpriteBatch();
        viewport=new FitViewport(Constants.WORLD_SIZE,Constants.WORLD_SIZE);

        Gdx.input.setInputProcessor(this);

        font=new BitmapFont();
        font = new BitmapFont(Gdx.files.internal(Constants.FONT_FILE));
        font.getData().setScale(Constants.FONT_SCALE);
    }

    @Override
    public void render(float delta) {
        viewport.apply();

        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r,
                Constants.BACKGROUND_COLOR.g,
                Constants.BACKGROUND_COLOR.b,
                Constants.BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //pintamos las letras
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        //facil
        Utils.drawTextureRegion(batch,
                Assets.instance.onscreenControlsAssets.easy,
                Constants.FACIL_LOGO.x,
                Constants.FACIL_LOGO.y);
        font.draw(batch,
                Constants.FACIL,
                Constants.FACIL_POSITION.x,
                Constants.FACIL_POSITION.y,
                0, Align.left,false);

        //medio
        Utils.drawTextureRegion(batch,
                Assets.instance.onscreenControlsAssets.medium,
                Constants.MEDIO_LOGO.x,
                Constants.MEDIO_LOGO.y);
        font.draw(batch,
                Constants.MEDIO,
                Constants.MEDIO_POSITION.x,
                Constants.MEDIO_POSITION.y,
                0, Align.center,false);

        //dificil
        Utils.drawTextureRegion(batch,
                Assets.instance.onscreenControlsAssets.hard,
                Constants.DIFICIL_LOGO.x,
                Constants.DIFICIL_LOGO.y);
        font.draw(batch,
                Constants.DIFICIL,
                Constants.DIFICIL_POSITION.x,
                Constants.DIFICIL_POSITION.y,
                0, Align.right,false);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 touch=viewport.unproject(new Vector2(screenX,screenY));

        if(touch.dst(Constants.FACIL_LOGO_CENTER)<Constants.BUTTON_RADIUS){
            game.setGameScreen(Enums.Difficulty.EASY);
        }else if(touch.dst(Constants.MEDIO_LOGO_CENTER)<Constants.BUTTON_RADIUS){
            game.setGameScreen(Enums.Difficulty.MEDIUM);
        }else if(touch.dst(Constants.DIFICIL_LOGO_CENTER)<Constants.BUTTON_RADIUS){
            game.setGameScreen(Enums.Difficulty.HARD);
        }

        return true;
    }
}
