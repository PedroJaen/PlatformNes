package com.jaen.pedro.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jaen.pedro.PlatformNesGame;
import com.jaen.pedro.objects.Level;
import com.jaen.pedro.overlays.HudOverlay;
import com.jaen.pedro.overlays.OnScreensControls;
import com.jaen.pedro.utils.Constants;
import com.jaen.pedro.utils.Enums;
import com.jaen.pedro.utils.WorldCreator;

public class GameScreen  extends ScreenAdapter {
    SpriteBatch batch;
    Enums.Difficulty difficulty;
    PlatformNesGame game;
    HudOverlay hud;
    Music music;
    OnScreensControls onScreensControls;
    Level level;
    int currentLevel;
    private int score;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private Viewport viewport;


    public GameScreen(PlatformNesGame game, Enums.Difficulty difficulty) {
        this.game=game;
        this.difficulty = difficulty;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        hud=new HudOverlay(batch,this);
        camera=new OrthographicCamera();
        viewport=new ExtendViewport(Constants.LVL_SIZE,Constants.LVL_SIZE,camera);
        score=0;

        if(!game.isMute() && game.getMusic().isPlaying()){
            game.getMusic().stop();
        }

        //controles de pantalla
        onScreensControls = new OnScreensControls();
        if (onMobile()) {
            Gdx.input.setInputProcessor(onScreensControls);
        }

        currentLevel=0;
        startNewLevel(currentLevel);
    }

    private void startNewLevel(int lvl){
        //cargamos el fondo del nivel
        mapLoader=new TmxMapLoader();
        map=mapLoader.load(Constants.LEVELS[lvl]);
        renderer=new OrthogonalTiledMapRenderer(map,1);

        //obtenemos el nivel
        WorldCreator wc=new WorldCreator(map);
        level=wc.worldCreator();
        onScreensControls.hero=level.getHero();

        //colocamos la musica del nivel
        if(!game.isMute()){
            game.suenaMusica(Constants.MUSICA_LVLS[lvl]);
        }

    }

    private boolean onMobile() {
        return Gdx.app.getType() == Application.ApplicationType.Android || Gdx.app.getType() == Application.ApplicationType.iOS;
    }

    public void update(float delta){
        handleInput(delta);

        camera.position.set(level.getHero().getPosition(),0);
        renderer.setView(camera);

        level.update(delta);
    }

    //metodo para movernos por le mapa y comprobar q va bien
    private void handleInput(float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            camera.position.y+=delta*100;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            camera.position.x+=delta*100;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            camera.position.x-=delta*100;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            camera.position.y-=delta*100;
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

    public void levelComplete() {
        if(!game.isMute()){
            game.getMusic().play();
        }

        game.getPreferencias().addPuntuacion(1);
        game.getPreferencias().guardarDatos();
        game.setMenuScreen();
    }

    public void levelFailed() {
        if(!game.isMute()){
            game.getMusic().play();
        }

        game.getPreferencias().addPuntuacion(0);
        game.getPreferencias().guardarDatos();
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

    public void setDifficulty(Enums.Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public PlatformNesGame getGame() {
        return game;
    }

    public void setGame(PlatformNesGame game) {
        this.game = game;
    }

    public HudOverlay getHud() {
        return hud;
    }

    public void setHud(HudOverlay hud) {
        this.hud = hud;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public OnScreensControls getOnScreensControls() {
        return onScreensControls;
    }

    public void setOnScreensControls(OnScreensControls onScreensControls) {
        this.onScreensControls = onScreensControls;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public TmxMapLoader getMapLoader() {
        return mapLoader;
    }

    public void setMapLoader(TmxMapLoader mapLoader) {
        this.mapLoader = mapLoader;
    }

    public TiledMap getMap() {
        return map;
    }

    public void setMap(TiledMap map) {
        this.map = map;
    }

    public OrthogonalTiledMapRenderer getRenderer() {
        return renderer;
    }

    public void setRenderer(OrthogonalTiledMapRenderer renderer) {
        this.renderer = renderer;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
