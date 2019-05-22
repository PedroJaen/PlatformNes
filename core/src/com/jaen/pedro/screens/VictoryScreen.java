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
import com.jaen.pedro.utils.Utils;

public class VictoryScreen extends InputAdapter implements Screen {
    private PlatformNesGame game;
    private int score;
    private SpriteBatch batch;
    private FitViewport viewport;
    private BitmapFont font;
    private boolean confirm;
    private String firstLetter;
    private String secondLetter;
    private String thirdLetter;
    private int index1;
    private int index2;
    private int index3;

    public VictoryScreen(PlatformNesGame game, int score) {
        this.game = game;
        this.score=score;
    }

    @Override
    public void show() {
        batch=new SpriteBatch();
        viewport=new FitViewport(Constants.WORLD_SIZE,Constants.WORLD_SIZE);

        Gdx.input.setInputProcessor(this);

        font=new BitmapFont();
        font = new BitmapFont(Gdx.files.internal(Constants.FONT_FILE));
        font.getData().setScale(Constants.FONT_SCALE);

        if(!game.isMute() && game.getMusic().isPlaying()){
            game.getMusic().stop();
            game.suenaMusica(Constants.MUSICA_SET_SCORE);
        }

        confirm=false;
        index1=0;
        index2=0;
        index3=0;
        firstLetter=Constants.LETRAS_SCORE[index1];
        secondLetter=Constants.LETRAS_SCORE[index2];
        thirdLetter=Constants.LETRAS_SCORE[index3];
    }

    private void update(){
        firstLetter=Constants.LETRAS_SCORE[index1];
        secondLetter=Constants.LETRAS_SCORE[index2];
        thirdLetter=Constants.LETRAS_SCORE[index3];
    }

    @Override
    public void render(float delta) {
        update();

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

        //title
        font.draw(batch,
                Constants.VICTORY_MESSAGE,
                Constants.VICTORY_POSITION.x,
                Constants.VICTORY_POSITION.y,
                0, Align.center,false);

        //score
        font.draw(batch,
                Constants.SCORE_MESSAGE+score,
                Constants.SCORE_POSITION.x,
                Constants.SCORE_POSITION.y,
                0, Align.center,false);

        //1letter
        Utils.drawTextureRegion(batch,
                Assets.instance.onscreenControlsAssets.jump,
                Constants.FIRST_LETTER_UP_CENTER,
                Constants.OK_OFFSET,
                0.75f);
        font.draw(batch,
                firstLetter,
                Constants.FIRST_LETTER_CENTER.x,
                Constants.FIRST_LETTER_CENTER.y,
                0, Align.center,false);
        Utils.drawTextureRegion(batch,
                Assets.instance.onscreenControlsAssets.down,
                Constants.FIRST_LETTER_DOWN_CENTER,
                Constants.OK_OFFSET,
                0.75f);

        //2letter
        Utils.drawTextureRegion(batch,
                Assets.instance.onscreenControlsAssets.jump,
                Constants.SECOND_LETTER_UP_CENTER,
                Constants.OK_OFFSET,
                0.75f);
        font.draw(batch,
                secondLetter,
                Constants.SECOND_LETTER_CENTER.x,
                Constants.SECOND_LETTER_CENTER.y,
                0, Align.center,false);
        Utils.drawTextureRegion(batch,
                Assets.instance.onscreenControlsAssets.down,
                Constants.SECOND_LETTER_DOWN_CENTER,
                Constants.OK_OFFSET,
                0.75f);

        //3letter
        Utils.drawTextureRegion(batch,
                Assets.instance.onscreenControlsAssets.jump,
                Constants.THIDR_LETTER_UP_CENTER,
                Constants.OK_OFFSET,
                0.75f);
        font.draw(batch,
                thirdLetter,
                Constants.THIDR_LETTER_CENTER.x,
                Constants.THIDR_LETTER_CENTER.y,
                0, Align.center,false);
        Utils.drawTextureRegion(batch,
                Assets.instance.onscreenControlsAssets.down,
                Constants.THIDR_LETTER_DOWN_CENTER,
                Constants.OK_OFFSET,
                0.75f);

        //ok
        Utils.drawTextureRegion(batch,
                Assets.instance.onscreenControlsAssets.ok,
                Constants.OK_CENTER,
                Constants.OK_OFFSET,
                0.75f);

        batch.end();

        if(confirm){
            save();
        }
    }

    private void save() {
        game.getPreferencias().addPuntuacion(score,firstLetter+secondLetter+thirdLetter);
        game.getPreferencias().guardarDatos();
        game.setMenuScreen();

        if(!game.isMute() && game.getMusic().isPlaying()){
            game.getMusic().stop();
            game.suenaMusica(Constants.MUSICA_INICIO);
        }

        game.setMenuScreen();
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
        //return super.touchDown(screenX, screenY, pointer, button);
        Vector2 touch=viewport.unproject(new Vector2(screenX,screenY));

        if(touch.dst(Constants.FIRST_LETTER_UP_CENTER2)<Constants.BUTTON_RADIUS){
            if(index1<Constants.LETRAS_SCORE.length-1){
                index1++;
            }
        }else if(touch.dst(Constants.FIRST_LETTER_DOWN_CENTER2)<Constants.BUTTON_RADIUS){
            if(index1>0){
                index1--;
            }
        }else if(touch.dst(Constants.SECOND_LETTER_UP_CENTER2)<Constants.BUTTON_RADIUS){
            if(index2<Constants.LETRAS_SCORE.length-1){
                index2++;
            }
        }else if(touch.dst(Constants.SECOND_LETTER_DOWN_CENTER2)<Constants.BUTTON_RADIUS){
            if(index2>0){
                index2--;
            }
        }else if(touch.dst(Constants.THIDR_LETTER_UP_CENTER2)<Constants.BUTTON_RADIUS){
            if(index3<Constants.LETRAS_SCORE.length-1){
                index3++;
            }
        }else if(touch.dst(Constants.THIDR_LETTER_DOWN_CENTER2)<Constants.BUTTON_RADIUS){
            if(index3>0){
                index3--;
            }
        }else if(touch.dst(Constants.OK_CENTER2)<Constants.BUTTON_RADIUS){
            save();
        }

        return true;
    }
}
