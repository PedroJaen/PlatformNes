package com.jaen.pedro.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Constants {
    //common
    public static final float WORLD_SIZE = 460;
    public static final Color BACKGROUND_COLOR = Color.BLACK;
    public static final String FONT_FILE = "font/header.fnt";
    public static final String TITLE = "PlatformNes";
    public static final float FONT_SCALE = 1f;
    public static final String TEXTURE_ATLAS = "images/texture.atlas.pack";
    public static final String PREFERENCES = "preferences";
    public static final float GRAVITY = 10;

    //hud
    public static final int HUD_MARGIN=20;
    public static final String LEVEL = "LEVEL";
    public static final String LIVES = "LIVES X ";
    public static final String AMMO = "AMMO X ";
    public static final String KEY = "KEY";
    public static final int LEVEL_TIMER=300;

    //startscreen
    public static final String TOUCH = "TOUCH THE SCREEN";
    public static final String KEYBOARD = "PRESS ANY KEY";

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
    public static final Vector2 FACIL_POSITION=new Vector2(0,(WORLD_SIZE/2)-32);
    public static final Vector2 FACIL_LOGO=new Vector2(0,WORLD_SIZE/2);
    public static final Vector2 FACIL_LOGO_CENTER=new Vector2(32,(WORLD_SIZE/2)+32);
    public static final String FACIL = "EASY";
    public static final int FACIL_INC=1;
    public static final Vector2 MEDIO_POSITION=new Vector2(WORLD_SIZE/2,(WORLD_SIZE/2)-32);
    public static final Vector2 MEDIO_LOGO=new Vector2((WORLD_SIZE/2)-32,WORLD_SIZE/2);
    public static final Vector2 MEDIO_LOGO_CENTER=new Vector2((WORLD_SIZE/2),(WORLD_SIZE/2)+32);
    public static final String MEDIO = "MEDIUM";
    public static final int MEDIO_INC=2;
    public static final Vector2 DIFICIL_POSITION=new Vector2(WORLD_SIZE,(WORLD_SIZE/2)-32);
    public static final Vector2 DIFICIL_LOGO=new Vector2(WORLD_SIZE-64,WORLD_SIZE/2);
    public static final Vector2 DIFICIL_LOGO_CENTER=new Vector2(WORLD_SIZE-32,(WORLD_SIZE/2)+32);
    public static final String DIFICIL = "HARD";
    public static final int DIFICIL_INC=3;

    //overlays
    public static final String GAME_OVER_MESSAGE="GAME OVER";
    public static final String VICTORY_MESSAGE="YOU WIN!";
    public static final float LEVEL_END_DURATION = 4;

    //points
    public static final int SCORE_KILL=100;
    public static final int SCORE_FRUIT=50;
    public static final int SCORE_PICK_KEY=200;
    public static final int SCORE_EXIT=300;

    //sounds
    public static final String MUSICA_INICIO="music/inicio.mp3";
    public static final String MUSICA_LVL1="music/lvl1.mp3";
    public static final String[] MUSICA_LVLS={MUSICA_LVL1};
    public static final String MUSICA_GAME_OVER="music/game_over.mp3";
    public static final String MUSICA_GAME_WIN="music/game_win.mp3";
    public static final String MUSICA_DISPARO1="music/disparo1.wav";
    public static final String MUSICA_DISPARO2="music/disparo2.wav";
    public static final String MUSICA_SALTO="music/salto.wav";
    public static final String MUSICA_EXPLOSION="music/explosion.wav";
    public static final String MUSICA_OBJETO="music/objeto.wav";

    // Onscreen Controls
    public static final Vector2 BUTTON_CENTER = new Vector2(15, 15);
    public static final float BUTTON_RADIUS = 32;
    public static final float ONSCREEN_CONTROLS_VIEWPORT_SIZE = 200;
    public static final Vector2 MUSIC_BUTTON_CENTER = new Vector2(WORLD_SIZE-BUTTON_RADIUS, WORLD_SIZE-BUTTON_RADIUS);

    //heroe
    public static final float WALK_LOOP_DURATION = 0.25f;
    public static final float HERO_MOVE_SPEED = 100;
    public static final int INITIAL_LIVES=3;
    public static final int INITIAL_AMMO=10;
    public static final float MAX_JUMP_DURATION = .15f;
    public static final float JUMP_SPEED = 200;
    public static final Vector2 KNOCKBACK_VELOCITY = new Vector2(200, 200);

    //enemy
    public static final int ENEMY_LIVES=5;
    public static final float ENEMY_MOVEMENT_SPEED = 20;
    public static final int ENEMY_SHOOT_TIME=3;

    //bullet
    public static final float BULLET_MOVEMENT_SPEED = 100;

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
    public static final int LVL_ENEMIE=10;
    public static final int LVL_1=0;

    //sprites
    public static final String ANDA1 = "anda1";
    public static final String ANDA2 = "anda2";
    public static final String ANDA4 = "anda4";
    public static final String ANDA5 = "anda5";
    public static final String SALTA1 = "salta1";
    public static final String SALTA2 = "salta2";
    public static final String SALTA3 = "salta3";

    public static final String CARACOL1 = "caracol1";
    public static final String CARACOL2 = "caracol2";
    public static final String AZUL1 = "azul1";
    public static final String AZUL2 = "azul2";
    public static final String AZUL3 = "azul3";
    public static final String AZUL4 = "azul4";
    public static final String CERDO1 = "cerdo1";
    public static final String CERDO2 = "cerdo2";
    public static final String MURCIELAGO1 = "murcielago1";
    public static final String MURCIELAGO2 = "murcielago2";
    public static final String ROJO1 = "rojo1";
    public static final String ROJO2 = "rojo2";
    public static final String ROJO3 = "rojo3";
    public static final String ROJO4 = "rojo4";

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
    public static final String FIREBALL = "fireball";

    public static final String MOVE_LEFT_BUTTON = "izquierda";
    public static final String MOVE_RIGHT_BUTTON = "derecha";
    public static final String SHOOT_BUTTON = "disparo";
    public static final String JUMP_BUTTON = "salto";
    public static final String MUSIC_ON_BUTTON = "music_on";
    public static final String MUSIC_OFF_BUTTON = "music_off";
    public static final String DIFFICULTY_EASY = "easy";
    public static final String DIFFICULTY_MEDIUM = "medium";
    public static final String DIFFICULTY_HARD = "hard";

}
