package com.jaen.pedro.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Constants {
    //common
    public static final float WORLD_SIZE = 460;
    public static final Color BACKGROUND_COLOR = Color.BLACK;
    public static final String FONT_FILE = "font/header.fnt";
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
    public static final Vector2 JUGAR_BUTTON = new Vector2(WORLD_SIZE/2,WORLD_SIZE*0.75f);
    public static final String MENU_SCORES="SCORES";
    public static final Vector2 SCORES_BUTTON = new Vector2(WORLD_SIZE/2,WORLD_SIZE*0.5f);
    public static final float SCORES_RADIUS = 50;
    public static final String MENU_INSTRUCTION="INSTRUCTIONS";
    public static final Vector2 INSTRUCTIONS_BUTTON = new Vector2(WORLD_SIZE/2,WORLD_SIZE*0.25f);
    public static final float INSTRUCTION_RADIUS = 70;

    //instructionscreen
    public static final String INSTRUCTION_1="* OBTAIN THE KEY TO OPEN THE";
    public static final String INSTRUCTION_2="   CASTLE'S DOOR";
    public static final String INSTRUCTION_3="* ARRIVE TO THE CASTLE WITH THE KEY";
    public static final String INSTRUCTION_4="   TO PASS TO THE NEXT LEVEL";
    public static final String INSTRUCTION_5="* THE NUMBER OF LIVES OF THE ENEMY";
    public static final String INSTRUCTION_6="   DEPENDS ON THE DIFFICULTY";
    public static final String INSTRUCTION_7="* YOU CHOOSE TO KILL ENEMIES OR";
    public static final String INSTRUCTION_8="   AVOID THEM AND RESERVE AMMO";
    public static final String[] INSTRUCTIONS={INSTRUCTION_1,INSTRUCTION_2,INSTRUCTION_3,INSTRUCTION_4,
            INSTRUCTION_5,INSTRUCTION_6,INSTRUCTION_7,INSTRUCTION_8};
    public static final Vector2 INSTRUCTIONS_POSSITION = new Vector2(0,WORLD_SIZE*0.75f);
    public static final Vector2 INSTRUCTIONS_TITLE_POSSITION = new Vector2(WORLD_SIZE/2,WORLD_SIZE*0.9f);

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
    public static final String VICTORY_MESSAGE="YOU WIN!";
    public static final Vector2 VICTORY_POSITION = new Vector2(WORLD_SIZE/2,WORLD_SIZE*9/10);
    public static final String SCORE_MESSAGE="YOUR SCORE: ";
    public static final Vector2 SCORE_POSITION = new Vector2(WORLD_SIZE/2,WORLD_SIZE*8/10);
    public static final float LEVEL_END_DURATION = 4;
    public static final float LEVEL_NEXT = 3;

    //points
    public static final int SCORE_KILL=100;
    public static final int SCORE_FRUIT=50;
    public static final int SCORE_PICK_KEY=200;
    public static final int SCORE_EXIT=300;
    public static final int SCORE_LIVE=10000;

    //sounds
    public static final String MUSICA_INICIO="music/inicio.mp3";
    public static final String MUSICA_LVL1="music/lvl1.mp3";
    public static final String MUSICA_LVL2="music/lvl2.mp3";
    public static final String MUSICA_LVL3="music/lvl3.mp3";
    public static final String MUSICA_LVL_NEXT="music/lvl_next.mp3";
    public static final String MUSICA_SET_SCORE="music/set_score.mp3";
    public static final String[] MUSICA_LVLS={MUSICA_LVL1,MUSICA_LVL2,MUSICA_LVL3};
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

    //victoryscreen
    public static final String[] LETRAS_SCORE={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    public static final Vector2 FIRST_LETTER_UP_CENTER = new Vector2(WORLD_SIZE/4,WORLD_SIZE/2);
    public static final Vector2 FIRST_LETTER_UP_CENTER2 = new Vector2(WORLD_SIZE/4,(WORLD_SIZE/2)+32);
    public static final Vector2 FIRST_LETTER_CENTER = new Vector2(WORLD_SIZE/4,WORLD_SIZE*4.4f/10);
    public static final Vector2 FIRST_LETTER_DOWN_CENTER = new Vector2(WORLD_SIZE/4,WORLD_SIZE/5);
    public static final Vector2 FIRST_LETTER_DOWN_CENTER2 = new Vector2(WORLD_SIZE/4,(WORLD_SIZE/5)+32);
    public static final Vector2 SECOND_LETTER_UP_CENTER = new Vector2(WORLD_SIZE/2,WORLD_SIZE/2);
    public static final Vector2 SECOND_LETTER_UP_CENTER2 = new Vector2(WORLD_SIZE/2,(WORLD_SIZE/2)+32);
    public static final Vector2 SECOND_LETTER_CENTER = new Vector2(WORLD_SIZE/2,WORLD_SIZE*4.4f/10);
    public static final Vector2 SECOND_LETTER_DOWN_CENTER = new Vector2(WORLD_SIZE/2,WORLD_SIZE/5);
    public static final Vector2 SECOND_LETTER_DOWN_CENTER2 = new Vector2(WORLD_SIZE/2,(WORLD_SIZE/5)+32);
    public static final Vector2 THIDR_LETTER_UP_CENTER = new Vector2(WORLD_SIZE*3/4,WORLD_SIZE/2);
    public static final Vector2 THIDR_LETTER_UP_CENTER2 = new Vector2(WORLD_SIZE*3/4,(WORLD_SIZE/2)+32);
    public static final Vector2 THIDR_LETTER_CENTER = new Vector2(WORLD_SIZE*3/4,WORLD_SIZE*4.4f/10);
    public static final Vector2 THIDR_LETTER_DOWN_CENTER = new Vector2(WORLD_SIZE*3/4,WORLD_SIZE/5);
    public static final Vector2 THIDR_LETTER_DOWN_CENTER2 = new Vector2(WORLD_SIZE*3/4,(WORLD_SIZE/5)+32);
    public static final Vector2 OK_CENTER = new Vector2(WORLD_SIZE-30,WORLD_SIZE/10);
    public static final Vector2 OK_CENTER2 = new Vector2(WORLD_SIZE-30,(WORLD_SIZE/10)+32);
    public static final Vector2 OK_OFFSET = new Vector2(30,0);

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
    public static final String LEVEL2="levels/plat_lvl2.tmx";
    public static final String LEVEL3="levels/plat_lvl3.tmx";
    public static final String[] LEVELS={LEVEL1,LEVEL2,LEVEL3};
    public static final int LVL_GROUND=3;
    public static final int LVL_EXIT=4;
    public static final int LVL_DEATH=5;
    public static final int LVL_LLAVE=6;
    public static final int LVL_FRUTA=7;
    public static final int LVL_HEROE=8;
    public static final int LVL_AMMO=9;
    public static final int LVL_ENEMIE=10;
    public static final int LVL_1=0;

    //sprites heroe
    public static final String ANDA1 = "anda1";
    public static final String ANDA2 = "anda2";
    public static final String ANDA4 = "anda4";
    public static final String ANDA5 = "anda5";
    public static final String SALTA1 = "salta1";
    public static final String SALTA2 = "salta2";
    public static final String SALTA3 = "salta3";

    public static final String ANDA1_MAPPY="anda1_mappy";
    public static final String ANDA2_MAPPY="anda2_mappy";
    public static final String SALTA_MAPPY="salta_mappy";

    public static final String ANDA1_JACKIE="anda1_jackie";
    public static final String ANDA2_JACKIE="anda2_jackie";
    public static final String ANDA3_JACKIE="anda3_jackie";
    public static final String ANDA4_JACKIE="anda4_jackie";
    public static final String ANDA5_JACKIE="anda5_jackie";
    public static final String ANDA6_JACKIE="anda6_jackie";
    public static final String SALTA_JACKIE="jump_jackie";
    public static final String STAND_JACKIE="stand_jackie";

    //sprites enemie
    public static final String CARACOL1 = "caracol1";
    public static final String CARACOL2 = "caracol2";
    public static final String AZUL1 = "azul1";
    public static final String AZUL2 = "azul2";
    public static final String AZUL3 = "azul3";
    public static final String CERDO1 = "cerdo1";
    public static final String CERDO2 = "cerdo2";
    public static final String MURCIELAGO1 = "murcielago1";
    public static final String MURCIELAGO2 = "murcielago2";
    public static final String ROJO1 = "rojo1";
    public static final String ROJO2 = "rojo2";
    public static final String ROJO3 = "rojo3";

    public static final String ROJO1_MAPPY = "rojo1_mappy";
    public static final String ROJO2_MAPPY = "rojo2_mappy";
    public static final String ROJO3_MAPPY = "rojo3_mappy";
    public static final String ROSA1_MAPPY = "rosa1_mappy";
    public static final String ROSA2_MAPPY = "rosa2_mappy";
    public static final String ROSA3_MAPPY = "rosa3_mappy";

    public static final String BOOTDEMON1 = "bootdemon1_jackie";
    public static final String BOOTDEMON2 = "bootdemon2_jackie";
    public static final String CALAVERA1 = "calavera1_jackie";
    public static final String CALAVERA2 = "calavera2_jackie";
    public static final String DEMONIO1 = "demonio1_jackie";
    public static final String DEMONIO2 = "demonio2_jackie";
    public static final String DEMONIO3 = "demonio3_jackie";
    public static final String FIREDEMON1 = "firedemon1_jackie";
    public static final String FIREDEMON2 = "firedemon2_jackie";
    public static final String FUTBOLISTA1 = "futbolista1_jackie";
    public static final String FUTBOLISTA2 = "futbolista2_jackie";
    public static final String ICEDEMON1 = "icedemon1_jackie";
    public static final String ICEDEMON2 = "icedemon2_jackie";
    public static final String LUCHADOR1 = "luchador1_jackie";
    public static final String LUCHADOR2 = "luchador2_jackie";
    public static final String LUCHADOR3 = "luchador3_jackie";
    public static final String LUCHADOR4 = "luchador4_jackie";
    public static final String PAJARO2 = "pajaro2_jackie";
    public static final String PAJARO1 = "pajaro1_jackie";
    public static final String PINCHO1 = "pincho1_jackie";
    public static final String PINCHO2 = "pincho2_jackie";
    public static final String PINCHO3 = "pincho3_jackie";
    public static final String PINCHO4 = "pincho4_jackie";
    public static final String SNOWMAN1 = "snowman1_jackie";
    public static final String SNOWMAN2 = "snowman2_jackie";
    public static final String TAZON1 = "tazon1_jackie";
    public static final String TAZON2 = "tazon2_jackie";
    public static final String TIGER1 = "tiger1_jackie";
    public static final String TIGER2 = "tiger2_jackie";
    public static final String TUCAN1 = "tucan1_jackie";
    public static final String TUCAN2 = "tucan2_jackie";
    public static final String VASIJA1 = "vasija1_jackie";
    public static final String VASIJA2 = "vasija2_jackie";

    //sprites items
    public static final String LLAVE = "llave";
    public static final String MANZANA = "manzana";
    public static final String MELON = "melon";
    public static final String PINEAPLE = "pineaple";
    public static final String PLATANO = "platano";

    public static final String CUADRO_MAPPY = "cuadro_mappy";
    public static final String PC_MAPPY = "pc_mappy";
    public static final String RADIO_MAPPY = "radio_mappy";
    public static final String SECURITY_MAPPY = "security_mappy";
    public static final String TV_MAPPY = "tv_mappy";

    public static final String CAMPANA = "campana_jackie";
    public static final String CUENCO = "cuenco_jackie";
    public static final String ESFERA = "esfera_jackie";
    public static final String PELOTA = "pelota_jackie";
    public static final String SNOWBALL = "snowball_jackie";

    //sprites bullets
    public static final String MARTILLO1 = "martillo1";
    public static final String MARTILLO2 = "martillo2";
    public static final String MARTILLO3 = "martillo3";
    public static final String MARTILLO4 = "martillo4";
    public static final String FIREBALL = "fireball";

    public static final String HAYUKEN = "bullet_jackie";
    public static final String FIREBALL_JACKIE1 = "fireball1_jackie";
    public static final String FIREBALL_JACKIE2 = "fireball2_jackie";
    public static final String FLECHA = "flecha_jackie";
    public static final String ICING1 = "icing1_jackie";
    public static final String ICING2 = "icing2_jackie";
    public static final String NUNCHAKU1 = "nunchaku1_jackie";
    public static final String NUNCHAKU2 = "nunchaku2_jackie";

    //sprites buttons
    public static final String MOVE_LEFT_BUTTON = "izquierda";
    public static final String MOVE_RIGHT_BUTTON = "derecha";
    public static final String SHOOT_BUTTON = "disparo";
    public static final String JUMP_BUTTON = "salto";
    public static final String DOWN_BUTTON = "abajo";
    public static final String OK_BUTTON = "ok";
    public static final String MUSIC_ON_BUTTON = "music_on";
    public static final String MUSIC_OFF_BUTTON = "music_off";
    public static final String DIFFICULTY_EASY = "easy";
    public static final String DIFFICULTY_MEDIUM = "medium";
    public static final String DIFFICULTY_HARD = "hard";

}
