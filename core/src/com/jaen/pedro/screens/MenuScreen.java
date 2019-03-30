package com.jaen.pedro.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.jaen.pedro.PlatformNesGame;
import com.jaen.pedro.utils.Assets;
import com.jaen.pedro.utils.Constants;
import com.jaen.pedro.utils.Utils;

public class MenuScreen extends InputAdapter implements Screen {
    PlatformNesGame game;
    FitViewport viewport;
    BitmapFont font;
    SpriteBatch batch;

    public MenuScreen(PlatformNesGame game) {
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

        font.draw(batch,
                Constants.MENU_JUGAR,
                Constants.JUGAR_BUTTON.x,
                Constants.JUGAR_BUTTON.y,
                0, Align.center,false);

        font.draw(batch,
                Constants.MENU_SCORES,
                Constants.SCORES_BUTTON.x,
                Constants.SCORES_BUTTON.y,
                0, Align.center,false);

        pintaBotonMusica(batch);

        batch.end();
    }

    private void pintaBotonMusica(SpriteBatch batch) {
        //pintamos el boton de musica
        TextureRegion region = Assets.instance.onscreenControlsAssets.music_on;
        if(game.isMute()){
            region = Assets.instance.onscreenControlsAssets.music_off;
        }
        Utils.drawTextureRegion(batch,
                region,
                Constants.MUSIC_BUTTON_CENTER.x-Constants.BUTTON_RADIUS,
                Constants.MUSIC_BUTTON_CENTER.y-Constants.BUTTON_RADIUS);
    }

    private void suenaMusica() {
        if(game.isMute()){//esta muteado
            if(game.getMusic().isPlaying()){//si suena, paramos
                game.getMusic().stop();
            }
        }else{//puede sonar
            if(game.getMusic()==null){
                game.suenaMusica(Constants.MUSICA_INICIO);
            }else if(!game.getMusic().isPlaying()){//si no suena, iniciamos
                game.getMusic().play();
            }
        }
        game.getPreferencias().setMute(game.isMute());
        game.getPreferencias().guardarDatos();
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

        if(touch.dst(Constants.JUGAR_BUTTON)<Constants.BUTTON_RADIUS){
            game.setDifficultyScreen();
        }else if(touch.dst(Constants.SCORES_BUTTON)<Constants.BUTTON_RADIUS){
            game.setScoreScreen();
        }else if(touch.dst(Constants.MUSIC_BUTTON_CENTER)<Constants.BUTTON_RADIUS){
            game.setMute(!game.isMute());
            suenaMusica();
        }

        return true;
    }
}
