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
import com.jaen.pedro.objects.Score;
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
    private Score score;
    private int nlives;
    private int currentLvl;
    private Enums.Difficulty difficulty;
    private GameScreen gameScreen;
    private int ammo;
    private boolean keyAdded;

    //Scene2D widgets
    private Label countdownLabel;
    private Label scoreLabel;
    private Label timeLabel;
    private Label levelLabel;
    private Label worldLabel;
    private Label difficultyLabel;
    private Label lives;
    private Label ammos;
    private Label keyGetted;
    private Table table;


    public HudOverlay(SpriteBatch sb, GameScreen gameScreen) {
        //define our tracking variables
        worldTimer = Constants.LEVEL_TIMER;
        timeCount = 0;
        difficulty=gameScreen.getDifficulty();
        score=gameScreen.getScore();
        timeUp=false;
        keyAdded=false;

        //setup the HUD viewport using a new camera seperate from our gamecam
        //define our stage using that viewport and our games spritebatch
        viewport = new FitViewport(Constants.WORLD_SIZE,Constants.WORLD_SIZE, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        //define a table used to organize our hud's labels
        table = new Table();
        //Top-Align table
        table.top();
        //make the table fill the entire stage
        table.setFillParent(true);

        //define our labels using the String, and a Label style consisting of a font and color
        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel =new Label(String.format("%06d", score.getScore()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label(currentLvl+1+"", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel = new Label(Constants.LEVEL, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        difficultyLabel = new Label(difficulty+" ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        lives = new Label(Constants.LIVES+nlives, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        ammos = new Label(Constants.AMMO+ammo, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        keyGetted = new Label(Constants.KEY, new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        //add our labels to our table, padding the top, and giving them all equal width with expandX
        table.add(difficultyLabel).expandX().padTop(Constants.HUD_MARGIN);
        table.add(worldLabel).expandX().padTop(Constants.HUD_MARGIN);
        table.add(lives).expandX().padTop(Constants.HUD_MARGIN);
        table.add(timeLabel).expandX().padTop(Constants.HUD_MARGIN);
        //add a second row to our table
        table.row();
        table.add(scoreLabel).expandX().padTop(Constants.HUD_MARGIN/2);
        table.add(levelLabel).expandX().padTop(Constants.HUD_MARGIN/2);
        table.add(ammos).expandX().padTop(Constants.HUD_MARGIN/2);
        table.add(countdownLabel).expandX().padTop(Constants.HUD_MARGIN/2);

        //add our table to the stage
        stage.addActor(table);
    }

    public void update(float delta, int nlives, int ammo, boolean key,int currentLvl){
        scoreLabel.setText(String.format("%06d", score.getScore()));
        lives.setText(Constants.LIVES+nlives);
        ammos.setText(Constants.AMMO+ammo);
        levelLabel.setText(currentLvl+1+"");
        timeCount+=delta;

        if(timeCount>=1){
            worldTimer--;
            countdownLabel.setText(String.format("%03d", worldTimer));
            timeCount=0;
        }

        if(key){
            addkey();
        }else{
            if(keyAdded){
                keyGetted.remove();
                keyAdded=false;
            }
        }

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    private void addkey(){
        if(!keyAdded){
            keyAdded=true;

            //add a third row to our table
            table.row();
            table.add(keyGetted).expandX().padTop(Constants.HUD_MARGIN/2);

            stage.addActor(table);

        }
    }

    public Integer getWorldTimer() {
        return worldTimer;
    }

    public void setWorldTimer(Integer worldTimer) {
        this.worldTimer = worldTimer;
    }

}
