package com.jaen.pedro.overlays;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jaen.pedro.screens.GameScreen;
import com.jaen.pedro.utils.Constants;
import com.jaen.pedro.utils.Enums;

public class HudOverlay implements Disposable {
    //Scene2D.ui Stage and its own Viewport for HUD
    public Stage stage;
    private Viewport viewport;

    //score/time Tracking Variables
    private Integer worldTimer;
    private boolean timeUp; // true when the world timer reaches 0
    private float timeCount;
    private int score;
    private Enums.Difficulty difficulty;
    private GameScreen gameScreen;

    //Scene2D widgets
    private Label countdownLabel;
    private static Label scoreLabel;
    private Label timeLabel;
    private Label levelLabel;
    private Label worldLabel;
    private Label difficultyLabel;

    public HudOverlay(SpriteBatch sb, GameScreen gameScreen) {
        //define our tracking variables
        worldTimer = 300;
        timeCount = 0;
        score = gameScreen.getScore();
        difficulty=gameScreen.getDifficulty();


        //setup the HUD viewport using a new camera seperate from our gamecam
        //define our stage using that viewport and our games spritebatch
        viewport = new FitViewport(Constants.WORLD_SIZE,Constants.WORLD_SIZE, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        //define a table used to organize our hud's labels
        Table table = new Table();
        //Top-Align table
        table.top();
        //make the table fill the entire stage
        table.setFillParent(true);

        //define our labels using the String, and a Label style consisting of a font and color
        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel =new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label(gameScreen.getCurrentLevel()+1+"", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel = new Label(Constants.LEVEL, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        difficultyLabel = new Label(difficulty+" ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        //add our labels to our table, padding the top, and giving them all equal width with expandX
        table.add(difficultyLabel).expandX().padTop(Constants.HUD_MARGIN);
        table.add(worldLabel).expandX().padTop(Constants.HUD_MARGIN);
        table.add(timeLabel).expandX().padTop(Constants.HUD_MARGIN);
        //add a second row to our table
        table.row();
        table.add(scoreLabel).expandX().padTop(Constants.HUD_MARGIN/2);
        table.add(levelLabel).expandX().padTop(Constants.HUD_MARGIN/2);
        table.add(countdownLabel).expandX().padTop(Constants.HUD_MARGIN/2);

        //add our table to the stage
        stage.addActor(table);
    }



    @Override
    public void dispose() {
        stage.dispose();
    }
}
