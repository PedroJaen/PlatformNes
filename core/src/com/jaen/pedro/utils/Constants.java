package com.jaen.pedro.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Constants {
    //common
    public static final float WORLD_SIZE = 460;
    public static final Color BACKGROUND_COLOR = Color.SKY;
    public static final String FONT_FILE = "font/header.fnt";
    public static final String TITLE = "PlatformNes";
    public static final float FONT_SCALE = 1f;
    public static final float BUTTON_RADIUS = 32;

    //menuscreen
    public static final String MENU_JUGAR="PLAY";
    public static final Vector2 JUGAR_BUTTON = new Vector2(WORLD_SIZE*0.25f,WORLD_SIZE/2);
    public static final String MENU_SCORES="SCORES";
    public static final Vector2 SCORES_BUTTON = new Vector2(WORLD_SIZE*0.75f,WORLD_SIZE/2);

    //scorescreen
    public static final float FONT_LIL_SCALE = 0.5f;
    public static final String SCORES="HIGH SCORES!";
    public static final Vector2 SCORES_POSITION = new Vector2(WORLD_SIZE/2,WORLD_SIZE-20);

    //difficultscreen
    public static final Vector2 FACIL_POSITION=new Vector2(0,WORLD_SIZE/2);
    public static final String FACIL = "EASY";
    public static final int FACIL_INC=1;
    public static final Vector2 MEDIO_POSITION=new Vector2(WORLD_SIZE/2,WORLD_SIZE/2);
    public static final String MEDIO = "MEDIUM";
    public static final int MEDIO_INC=2;
    public static final Vector2 DIFICIL_POSITION=new Vector2(WORLD_SIZE,WORLD_SIZE/2);
    public static final String DIFICIL = "HARD";
    public static final int DIFICIL_INC=3;

    //hud
    public static final String LEVEL = "LEVEL";
    public static final String DIFFICULTY = "DIFFICULTY";
}
