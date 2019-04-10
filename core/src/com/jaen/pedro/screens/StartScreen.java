package com.jaen.pedro.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.jaen.pedro.PlatformNesGame;
import com.jaen.pedro.utils.Constants;
import com.jaen.pedro.utils.Enums;

public class StartScreen extends InputAdapter implements Screen {
    SpriteBatch batch;
    ExtendViewport viewport;
    BitmapFont font;
    PlatformNesGame game;
    Music music;
    private float timeCount;
    private boolean showed;
    private String start;

    public StartScreen(PlatformNesGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        viewport=new ExtendViewport(Constants.WORLD_SIZE,Constants.WORLD_SIZE);
        batch=new SpriteBatch();

        timeCount = 0;
        showed=false;

        Gdx.input.setInputProcessor(this);

        font=new BitmapFont();
        font = new BitmapFont(Gdx.files.internal(Constants.FONT_FILE));
        font.getData().setScale(Constants.FONT_SCALE);

        if(!game.isMute()){
            game.suenaMusica(Constants.MUSICA_INICIO);
        }

        if(onMobile()){
            start=Constants.TOUCH;
        }else{
            start=Constants.KEYBOARD;
        }
    }

    private void update(float delta){
        timeCount+=delta;

        if(timeCount>=1){
            showed=!showed;
            timeCount=0;
        }
    }

    @Override
    public void render(float delta) {
        update(delta);
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

        font.draw(batch,
                Constants.TITLE,
                viewport.getWorldWidth()/2,
                viewport.getWorldHeight()/2,
                0, Align.center,false);

        if(showed){
            font.draw(batch,
                    start,
                    viewport.getWorldWidth()/2,
                    viewport.getWorldHeight()/3,
                    0, Align.center,false);
        }

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
        game.setMenuScreen();
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        game.setMenuScreen();
        return true;
    }

    private boolean onMobile() {
        return Gdx.app.getType() == Application.ApplicationType.Android || Gdx.app.getType() == Application.ApplicationType.iOS;
    }
}
