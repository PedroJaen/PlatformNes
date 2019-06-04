package com.jaen.pedro.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jaen.pedro.PlatformNesGame;
import com.jaen.pedro.objects.Level;
import com.jaen.pedro.objects.Score;
import com.jaen.pedro.overlays.HudOverlay;
import com.jaen.pedro.overlays.OnScreensControls;
import com.jaen.pedro.utils.Constants;
import com.jaen.pedro.utils.Enums;
import com.jaen.pedro.utils.Utils;
import com.jaen.pedro.utils.WorldCreator;

public class GameScreen  extends ScreenAdapter {
    SpriteBatch batch;
    Enums.Difficulty difficulty;
    PlatformNesGame game;
    HudOverlay hud;
    OnScreensControls onScreensControls;
    Level level;
    int currentLevel;
    private Score score;
    private int ammo;
    private int lives;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private Viewport viewport;
    private long levelEndOverlayStartTime;

    public GameScreen(PlatformNesGame game, Enums.Difficulty difficulty) {
        this.game=game;
        this.difficulty = difficulty;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        score=new Score();
        hud=new HudOverlay(batch,this);
        camera=new OrthographicCamera();
        viewport=new ExtendViewport(Constants.LVL_SIZE,Constants.LVL_SIZE,camera);
        ammo=Constants.INITIAL_AMMO;
        lives= Constants.INITIAL_LIVES;

        if(!game.isMute() && game.getMusic().isPlaying()){
            game.getMusic().stop();
        }

        //controles de pantalla
        onScreensControls = new OnScreensControls();
        if (onMobile()) {
            Gdx.input.setInputProcessor(onScreensControls);
        }

        currentLevel=Constants.LVL_1;
        //currentLevel=2;
        startNewLevel(currentLevel);
    }

    private void startNewLevel(int lvl){
        //cargamos el fondo del nivel
        mapLoader=new TmxMapLoader();
        map=mapLoader.load(Constants.LEVELS[lvl]);
        renderer=new OrthogonalTiledMapRenderer(map,1);

        //obtenemos el nivel
        WorldCreator wc=new WorldCreator(map,difficulty,lvl,ammo,lives);
        level=wc.worldCreator();
        onScreensControls.hero=level.getHero();
        level.setScore(score);
        level.setHud(hud);
        level.setViewport(viewport);
        level.setMute(game.isMute());
        level.setGetKey(false);

        score.setHeroe(level.getHero());

        //colocamos la musica del nivel
        if(!game.isMute()){
            game.suenaMusica(Constants.MUSICA_LVLS[lvl]);
        }

    }

    private boolean onMobile() {
        return Gdx.app.getType() == Application.ApplicationType.Android || Gdx.app.getType() == Application.ApplicationType.iOS;
    }

    public void update(float delta){
        camera.position.set(level.getHero().getPosition(),0);
        renderer.setView(camera);

        level.update(delta);
        if(!level.isVictory()){
            hud.update(delta,level.getHero().getLives(),level.getHero().getAmmo(),level.isGetKey());
        }
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r,
                Constants.BACKGROUND_COLOR.g,
                Constants.BACKGROUND_COLOR.b,
                Constants.BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //pintamos el fondo del nivel
        renderer.render();

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        level.render(batch);

        renderLevelEndOverlays(batch);

        batch.end();

        //pintamos el hud
        hud.stage.draw();

        if (onMobile()) {
            onScreensControls.render(batch);
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height,true);
        onScreensControls.viewport.update(width, height,true);
        onScreensControls.recalculateButtonPositions();
    }

    @Override
    public void dispose() {
        batch.dispose();
        hud.dispose();
        renderer.dispose();
        map.dispose();
    }

    private void renderLevelEndOverlays(SpriteBatch batch) {
        if (level.isGameOver()) {

            if (levelEndOverlayStartTime == 0) {
                levelEndOverlayStartTime = TimeUtils.nanoTime();
                if(!game.isMute()){
                    game.suenaMusicaFinal(Constants.MUSICA_GAME_OVER);
                }
            }

            //gameOverOverlay.render(batch);
            if (Utils.secondsSince(levelEndOverlayStartTime) > Constants.LEVEL_END_DURATION) {
                levelEndOverlayStartTime = 0;
                levelFailed();
            }


        } else if (level.isVictory()) {

            if(currentLevel+1==Constants.LEVELS.length){
                if (levelEndOverlayStartTime == 0) {
                    levelEndOverlayStartTime = TimeUtils.nanoTime();
                    if(!game.isMute()){
                        game.suenaMusicaFinal(Constants.MUSICA_GAME_WIN);
                    }
                }

                if (Utils.secondsSince(levelEndOverlayStartTime) > Constants.LEVEL_END_DURATION) {
                    levelEndOverlayStartTime = 0;
                    levelComplete();
                }
            }else{
                if (levelEndOverlayStartTime == 0) {
                    levelEndOverlayStartTime = TimeUtils.nanoTime();
                    if(!game.isMute()){
                        game.suenaMusicaFinal(Constants.MUSICA_LVL_NEXT);
                    }
                }

                if (Utils.secondsSince(levelEndOverlayStartTime) > Constants.LEVEL_NEXT) {
                    levelEndOverlayStartTime = 0;
                    /*score.increAseScore(Constants.SCORE_KILL*level.getHero().getLives()
                            +level.getHero().getAmmo()
                            +hud.getWorldTimer());*/

                    hud.setWorldTimer(Constants.LEVEL_TIMER);
                    currentLevel++;
                    ammo=level.getHero().getAmmo();
                    lives=level.getHero().getLives();
                    if(!game.isMute()){
                        game.getMusic().stop();
                    }
                    startNewLevel(currentLevel);

                }
            }
        }
    }

    public void levelComplete() {
        if(!game.isMute()){
            game.getMusic().stop();
            game.suenaMusica(Constants.MUSICA_INICIO);
        }

        score.increAseScore(Constants.SCORE_KILL*level.getHero().getLives()
                +level.getHero().getAmmo()
                +hud.getWorldTimer());

        game.setVictoryScreen(score.getScore());
    }

    public void levelFailed() {
        if(!game.isMute()){
            game.getMusic().stop();
            game.suenaMusica(Constants.MUSICA_INICIO);
        }

        game.setMenuScreen();

    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public Enums.Difficulty getDifficulty() {
        return difficulty;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public TiledMap getMap() {
        return map;
    }

    public void setMap(TiledMap map) {
        this.map = map;
    }

    public Score getScore() {
        return score;
    }
}
