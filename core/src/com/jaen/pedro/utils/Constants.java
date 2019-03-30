package com.jaen.pedro.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Constants {
    //common
    public static final float WORLD_SIZE = 460;
    public static final Color BACKGROUND_COLOR = Color.BLACK;
    public static final String FONT_FILE = "font/header.fnt";
    public static final String TITLE = "PlatformNes";
    public static final float FONT_SCALE = 1f;
    public static final String TEXTURE_ATLAS = "images/texture.atlas.pack";
    public static final String PREFERENCES = "preferences";

    //hud
    public static final int HUD_MARGIN=20;
    public static final String LEVEL = "LEVEL";
    public static final String DIFFICULTY = "DIFFICULTY";

    //menuscreen
    public static final String MENU_JUGAR="PLAY";
    public static final Vector2 JUGAR_BUTTON = new Vector2(WORLD_SIZE*0.25f,WORLD_SIZE/2);
    public static final String MENU_SCORES="SCORES";
    public static final Vector2 SCORES_BUTTON = new Vector2(WORLD_SIZE*0.75f,WORLD_SIZE/2);

    //scorescreen
    public static final float FONT_LIL_SCALE = 0.8f;
    public static final String SCORES="HIGH SCORES!";
    public static final Vector2 SCORES_POSITION = new Vector2(WORLD_SIZE/2,WORLD_SIZE-HUD_MARGIN);

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

    //sounds
    public static final String MUSICA_INICIO="music/inicio.mp3";
    public static final String MUSICA_LVL1="music/lvl1.mp3";
    public static final String[] MUSICA_LVLS={MUSICA_LVL1};
    public static final String MUSICA_FIN="music/game_over.mp3";
    public static final String MUSICA_DISPARO1="music/game_over.mp3";
    public static final String MUSICA_DISPARO2="music/game_over.mp3";
    public static final String MUSICA_SALTO="music/game_over.mp3";
    public static final String MUSICA_EXPLOSION="music/game_over.mp3";
    public static final String MUSICA_OBJETO="music/game_over.mp3";

    // Onscreen Controls
    public static final Vector2 BUTTON_CENTER = new Vector2(15, 15);
    public static final float BUTTON_RADIUS = 32;
    public static final float ONSCREEN_CONTROLS_VIEWPORT_SIZE = 200;
    public static final String MOVE_LEFT_BUTTON = "izquierda";
    public static final String MOVE_RIGHT_BUTTON = "derecha";
    public static final String SHOOT_BUTTON = "disparo";
    public static final String JUMP_BUTTON = "salto";
    public static final String MUSIC_ON_BUTTON = "music_on";
    public static final String MUSIC_OFF_BUTTON = "music_off";
    public static final Vector2 MUSIC_BUTTON_CENTER = new Vector2(WORLD_SIZE-BUTTON_RADIUS, WORLD_SIZE-BUTTON_RADIUS);

    //heroe
    public static final float WALK_LOOP_DURATION = 0.25f;

    //lvls
    public static final float LVL_SIZE = 300;
    public static final String LEVEL1="levels/plat_lvl1.tmx";
    public static final String[] LEVELS={LEVEL1};
    public static final int LVL_GROUND=3;
    public static final int LVL_EXIT=4;
    public static final int LVL_DEATH=5;
    public static final int LVL_LLAVE=6;
    public static final int LVL_FRUTA=7;
    public static final int LVL_HEROE=8;
    public static final int LVL_AMMO=9;

    //sprites
    public static final String ANDA1 = "anda1";
    public static final String ANDA2 = "anda2";
    public static final String ANDA3 = "anda3";
    public static final String ANDA4 = "anda4";
    public static final String ANDA5 = "anda5";
    public static final String ANDA6 = "anda6";
    public static final String SALTA1 = "salta1";
    public static final String SALTA2 = "salta2";
    public static final String SALTA3 = "salta3";

    public static final String CARACOL1 = "caracol1";
    public static final String CARACOL2 = "caracol2";

    public static final String FUEGO1 = "fuego1";
    public static final String FUEGO2 = "fuego2";
    public static final String LLAVE = "llave";
    public static final String MANZANA = "manzana";
    public static final String MELON = "melon";
    public static final String PINEAPLE = "pineaple";
    public static final String PLATANO = "platano";

    public static final String MARTILLO1 = "martillo1";
    public static final String MARTILLO2 = "martillo2";
    public static final String MARTILLO3 = "martillo3";
    public static final String MARTILLO4 = "martillo4";

}
