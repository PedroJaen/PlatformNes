package com.jaen.pedro.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jaen.pedro.PlatformNesGame;
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

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private Viewport viewport;

    public GameScreen(PlatformNesGame game, Enums.Difficulty difficulty) {
        this.game=game;
        this.difficulty = difficulty;

        batch = new SpriteBatch();
        hud=new HudOverlay(batch,difficulty);
        camera=new OrthographicCamera();
        viewport=new FitViewport(Constants.WORLD_SIZE/Constants.PPM,Constants.WORLD_SIZE/Constants.PPM,camera);
        camera.position.set(viewport.getScreenWidth(),viewport.getScreenHeight(),0);

        //cargamos el nivel
        mapLoader=new TmxMapLoader();
        map=mapLoader.load(Constants.LEVEL1);
        renderer=new OrthogonalTiledMapRenderer(map,1/Constants.PPM);

        new WorldCreator(map);

        if(game.getMusic().isPlaying()){
            game.getMusic().stop();
        }

        onScreensControls = new OnScreensControls();
        if (onMobile()) {
            Gdx.input.setInputProcessor(onScreensControls);
        }
    }

    @Override
    public void show() {
    }

    private boolean onMobile() {
        return Gdx.app.getType() == Application.ApplicationType.Android || Gdx.app.getType() == Application.ApplicationType.iOS;
    }

    public void update(float delta){
        handleInput(delta);

        camera.update();
        renderer.setView(camera);
    }

    private void handleInput(float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            camera.position.y+=delta*10;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            camera.position.x+=delta*10;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            camera.position.x-=delta*10;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            camera.position.y-=delta*10;
        }
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //pintamos el nivel
        renderer.render();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();


        batch.end();

        //pintamos el hud
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height,true);
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

}