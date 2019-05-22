package com.jaen.pedro.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable, AssetErrorListener {
    public static final String TAG = Assets.class.getName();
    public static final Assets instance = new Assets();
    private AssetManager assetManager;

    public HeroeAssets heroeAssets;
    public EnemigoAssets enemigoAssets;
    public ItemAssets itemAssets;
    public AtaqueAssets ataqueAssets;
    public OnscreenControlsAssets onscreenControlsAssets;

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load asset: " + asset.fileName, throwable);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }

    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);
        assetManager.load(Constants.TEXTURE_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();
        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS);

        heroeAssets=new HeroeAssets(atlas);
        enemigoAssets=new EnemigoAssets(atlas);
        itemAssets=new ItemAssets(atlas);
        ataqueAssets=new AtaqueAssets(atlas);
        onscreenControlsAssets = new OnscreenControlsAssets(atlas);
    }

    public class HeroeAssets{

        public final Animation andar;
        public final Animation salta;
        public final TextureAtlas.AtlasRegion stand;

        public final Animation andar_mappy;
        public final TextureAtlas.AtlasRegion salta_mappy;
        public final TextureAtlas.AtlasRegion stand_mappy;

        public final Animation andar_jackie;
        public final TextureAtlas.AtlasRegion salta_jackie;
        public final TextureAtlas.AtlasRegion stand_jackie;

        public final TextureAtlas.AtlasRegion[] stands;


        public HeroeAssets(TextureAtlas atlas){
            //adventure island
            Array<TextureAtlas.AtlasRegion> andarFrames = new Array<TextureAtlas.AtlasRegion>();
            andarFrames.add(atlas.findRegion(Constants.ANDA1));
            andarFrames.add(atlas.findRegion(Constants.ANDA5));
            andarFrames.add(atlas.findRegion(Constants.ANDA2));
            andarFrames.add(atlas.findRegion(Constants.ANDA4));
            andar=new Animation(Constants.WALK_LOOP_DURATION,andarFrames,Animation.PlayMode.LOOP);
            Array<TextureAtlas.AtlasRegion> saltaFrames = new Array<TextureAtlas.AtlasRegion>();
            saltaFrames.add(atlas.findRegion(Constants.SALTA1));
            saltaFrames.add(atlas.findRegion(Constants.SALTA2));
            saltaFrames.add(atlas.findRegion(Constants.SALTA3));
            salta=new Animation(Constants.WALK_LOOP_DURATION,saltaFrames,Animation.PlayMode.LOOP);
            stand=atlas.findRegion(Constants.ANDA1);

            //mappy
            Array<TextureAtlas.AtlasRegion> andarFrames_mappy = new Array<TextureAtlas.AtlasRegion>();
            andarFrames_mappy.add(atlas.findRegion(Constants.ANDA1_MAPPY));
            andarFrames_mappy.add(atlas.findRegion(Constants.ANDA2_MAPPY));
            andar_mappy=new Animation(Constants.WALK_LOOP_DURATION,andarFrames_mappy,Animation.PlayMode.LOOP);
            salta_mappy=atlas.findRegion(Constants.SALTA_MAPPY);
            stand_mappy=atlas.findRegion(Constants.ANDA1_MAPPY);

            //jackie
            Array<TextureAtlas.AtlasRegion> andarFrames_jackie = new Array<TextureAtlas.AtlasRegion>();
            andarFrames_jackie.add(atlas.findRegion(Constants.ANDA1_JACKIE));
            andarFrames_jackie.add(atlas.findRegion(Constants.ANDA2_JACKIE));
            andarFrames_jackie.add(atlas.findRegion(Constants.ANDA3_JACKIE));
            andarFrames_jackie.add(atlas.findRegion(Constants.ANDA4_JACKIE));
            andarFrames_jackie.add(atlas.findRegion(Constants.ANDA5_JACKIE));
            andarFrames_jackie.add(atlas.findRegion(Constants.ANDA6_JACKIE));
            andar_jackie=new Animation(Constants.WALK_LOOP_DURATION,andarFrames_jackie,Animation.PlayMode.LOOP);
            salta_jackie=atlas.findRegion(Constants.SALTA_JACKIE);
            stand_jackie=atlas.findRegion(Constants.STAND_JACKIE);

            //arrays
            stands=new TextureAtlas.AtlasRegion[] {stand,stand_mappy,stand_jackie};
        }
    }

    public class EnemigoAssets{
        public final Animation caracol;
        public final Animation azul;
        public final Animation cerdo;
        public final Animation murcielago;
        public final Animation rojo;
        public final Animation[] enemigos;

        public final Animation rojo_mappy;
        public final Animation rosa_mappy;
        public final Animation[] enemigos_mappy;

        public final Animation bootdemon;
        public final Animation calavera;
        public final Animation demonio;
        public final Animation firedemon;
        public final Animation futbolista;
        public final Animation icedemon;
        public final Animation luchador1;
        public final Animation luchador2;
        public final Animation pajaro;
        public final Animation pincho1;
        public final Animation pincho2;
        public final Animation snowman;
        public final Animation tazon;
        public final Animation tiger;
        public final Animation tucan;
        public final Animation vasija;
        public final Animation[] enemigos_jackie;

        public EnemigoAssets(TextureAtlas atlas){
            //adventure island
            Array<TextureAtlas.AtlasRegion> caracolFrames = new Array<TextureAtlas.AtlasRegion>();
            caracolFrames.add(atlas.findRegion(Constants.CARACOL1));
            caracolFrames.add(atlas.findRegion(Constants.CARACOL2));
            caracol=new Animation(Constants.WALK_LOOP_DURATION,caracolFrames,Animation.PlayMode.LOOP);
            Array<TextureAtlas.AtlasRegion> azulFrames = new Array<TextureAtlas.AtlasRegion>();
            azulFrames.add(atlas.findRegion(Constants.AZUL1));
            azulFrames.add(atlas.findRegion(Constants.AZUL2));
            azulFrames.add(atlas.findRegion(Constants.AZUL3));
            azul=new Animation(Constants.WALK_LOOP_DURATION,azulFrames,Animation.PlayMode.LOOP);
            Array<TextureAtlas.AtlasRegion> cerdoFrames = new Array<TextureAtlas.AtlasRegion>();
            cerdoFrames.add(atlas.findRegion(Constants.CERDO1));
            cerdoFrames.add(atlas.findRegion(Constants.CERDO2));
            cerdo=new Animation(Constants.WALK_LOOP_DURATION,cerdoFrames,Animation.PlayMode.LOOP);
            Array<TextureAtlas.AtlasRegion> murcielagoFrames = new Array<TextureAtlas.AtlasRegion>();
            murcielagoFrames.add(atlas.findRegion(Constants.MURCIELAGO1));
            murcielagoFrames.add(atlas.findRegion(Constants.MURCIELAGO2));
            murcielago=new Animation(Constants.WALK_LOOP_DURATION,murcielagoFrames,Animation.PlayMode.LOOP);
            Array<TextureAtlas.AtlasRegion> rojoFrames = new Array<TextureAtlas.AtlasRegion>();
            rojoFrames.add(atlas.findRegion(Constants.ROJO1));
            rojoFrames.add(atlas.findRegion(Constants.ROJO2));
            rojoFrames.add(atlas.findRegion(Constants.ROJO3));
            rojo=new Animation(Constants.WALK_LOOP_DURATION,rojoFrames,Animation.PlayMode.LOOP);

            enemigos= new Animation[]{caracol, azul, cerdo, murcielago, rojo};

            //mappy
            Array<TextureAtlas.AtlasRegion> rojoFrames_mappy = new Array<TextureAtlas.AtlasRegion>();
            rojoFrames_mappy.add(atlas.findRegion(Constants.ROJO1_MAPPY));
            rojoFrames_mappy.add(atlas.findRegion(Constants.ROJO2_MAPPY));
            rojoFrames_mappy.add(atlas.findRegion(Constants.ROJO3_MAPPY));
            rojo_mappy=new Animation(Constants.WALK_LOOP_DURATION,rojoFrames_mappy,Animation.PlayMode.LOOP);
            Array<TextureAtlas.AtlasRegion> rosaFrames_mappy = new Array<TextureAtlas.AtlasRegion>();
            rosaFrames_mappy.add(atlas.findRegion(Constants.ROSA1_MAPPY));
            rosaFrames_mappy.add(atlas.findRegion(Constants.ROSA2_MAPPY));
            rosaFrames_mappy.add(atlas.findRegion(Constants.ROSA3_MAPPY));
            rosa_mappy=new Animation(Constants.WALK_LOOP_DURATION,rosaFrames_mappy,Animation.PlayMode.LOOP);

            enemigos_mappy= new Animation[]{rojo_mappy,rosa_mappy};

            //jackie
            Array<TextureAtlas.AtlasRegion> bootdemonFrames = new Array<TextureAtlas.AtlasRegion>();
            bootdemonFrames.add(atlas.findRegion(Constants.BOOTDEMON1));
            bootdemonFrames.add(atlas.findRegion(Constants.BOOTDEMON2));
            bootdemon=new Animation(Constants.WALK_LOOP_DURATION,bootdemonFrames,Animation.PlayMode.LOOP);
            Array<TextureAtlas.AtlasRegion> calaveraFrames = new Array<TextureAtlas.AtlasRegion>();
            calaveraFrames.add(atlas.findRegion(Constants.CALAVERA1));
            calaveraFrames.add(atlas.findRegion(Constants.CALAVERA2));
            calavera=new Animation(Constants.WALK_LOOP_DURATION,calaveraFrames,Animation.PlayMode.LOOP);
            Array<TextureAtlas.AtlasRegion> demonioFrames = new Array<TextureAtlas.AtlasRegion>();
            demonioFrames.add(atlas.findRegion(Constants.DEMONIO1));
            demonioFrames.add(atlas.findRegion(Constants.DEMONIO2));
            demonioFrames.add(atlas.findRegion(Constants.DEMONIO3));
            demonio=new Animation(Constants.WALK_LOOP_DURATION,demonioFrames,Animation.PlayMode.LOOP);
            Array<TextureAtlas.AtlasRegion> firedemonFrames = new Array<TextureAtlas.AtlasRegion>();
            firedemonFrames.add(atlas.findRegion(Constants.FIREDEMON1));
            firedemonFrames.add(atlas.findRegion(Constants.FIREDEMON2));
            firedemon=new Animation(Constants.WALK_LOOP_DURATION,firedemonFrames,Animation.PlayMode.LOOP);
            Array<TextureAtlas.AtlasRegion> futbolistaFrames = new Array<TextureAtlas.AtlasRegion>();
            futbolistaFrames.add(atlas.findRegion(Constants.FUTBOLISTA1));
            futbolistaFrames.add(atlas.findRegion(Constants.FUTBOLISTA2));
            futbolista=new Animation(Constants.WALK_LOOP_DURATION,futbolistaFrames,Animation.PlayMode.LOOP);
            Array<TextureAtlas.AtlasRegion> icedemonFrames = new Array<TextureAtlas.AtlasRegion>();
            icedemonFrames.add(atlas.findRegion(Constants.ICEDEMON1));
            icedemonFrames.add(atlas.findRegion(Constants.ICEDEMON2));
            icedemon=new Animation(Constants.WALK_LOOP_DURATION,icedemonFrames,Animation.PlayMode.LOOP);
            Array<TextureAtlas.AtlasRegion> luchador1Frames = new Array<TextureAtlas.AtlasRegion>();
            luchador1Frames.add(atlas.findRegion(Constants.LUCHADOR1));
            luchador1Frames.add(atlas.findRegion(Constants.LUCHADOR2));
            luchador1=new Animation(Constants.WALK_LOOP_DURATION,luchador1Frames,Animation.PlayMode.LOOP);
            Array<TextureAtlas.AtlasRegion> luchador2Frames = new Array<TextureAtlas.AtlasRegion>();
            luchador2Frames.add(atlas.findRegion(Constants.LUCHADOR3));
            luchador2Frames.add(atlas.findRegion(Constants.LUCHADOR4));
            luchador2=new Animation(Constants.WALK_LOOP_DURATION,luchador2Frames,Animation.PlayMode.LOOP);
            Array<TextureAtlas.AtlasRegion> pajaroFrames = new Array<TextureAtlas.AtlasRegion>();
            pajaroFrames.add(atlas.findRegion(Constants.PAJARO1));
            pajaroFrames.add(atlas.findRegion(Constants.PAJARO2));
            pajaro=new Animation(Constants.WALK_LOOP_DURATION,pajaroFrames,Animation.PlayMode.LOOP);
            Array<TextureAtlas.AtlasRegion> pincho1Frames = new Array<TextureAtlas.AtlasRegion>();
            pincho1Frames.add(atlas.findRegion(Constants.PINCHO1));
            pincho1Frames.add(atlas.findRegion(Constants.PINCHO2));
            pincho1=new Animation(Constants.WALK_LOOP_DURATION,pincho1Frames,Animation.PlayMode.LOOP);
            Array<TextureAtlas.AtlasRegion> pincho2Frames = new Array<TextureAtlas.AtlasRegion>();
            pincho2Frames.add(atlas.findRegion(Constants.PINCHO3));
            pincho2Frames.add(atlas.findRegion(Constants.PINCHO4));
            pincho2=new Animation(Constants.WALK_LOOP_DURATION,pincho2Frames,Animation.PlayMode.LOOP);
            Array<TextureAtlas.AtlasRegion> snowmanFrames = new Array<TextureAtlas.AtlasRegion>();
            snowmanFrames.add(atlas.findRegion(Constants.SNOWMAN1));
            snowmanFrames.add(atlas.findRegion(Constants.SNOWMAN2));
            snowman=new Animation(Constants.WALK_LOOP_DURATION,snowmanFrames,Animation.PlayMode.LOOP);
            Array<TextureAtlas.AtlasRegion> tazonFrames = new Array<TextureAtlas.AtlasRegion>();
            tazonFrames.add(atlas.findRegion(Constants.TAZON1));
            tazonFrames.add(atlas.findRegion(Constants.TAZON2));
            tazon=new Animation(Constants.WALK_LOOP_DURATION,tazonFrames,Animation.PlayMode.LOOP);
            Array<TextureAtlas.AtlasRegion> tigerFrames = new Array<TextureAtlas.AtlasRegion>();
            tigerFrames.add(atlas.findRegion(Constants.TIGER1));
            tigerFrames.add(atlas.findRegion(Constants.TIGER2));
            tiger=new Animation(Constants.WALK_LOOP_DURATION,tigerFrames,Animation.PlayMode.LOOP);
            Array<TextureAtlas.AtlasRegion> tucanFrames = new Array<TextureAtlas.AtlasRegion>();
            tucanFrames.add(atlas.findRegion(Constants.TUCAN1));
            tucanFrames.add(atlas.findRegion(Constants.TUCAN2));
            tucan=new Animation(Constants.WALK_LOOP_DURATION,tucanFrames,Animation.PlayMode.LOOP);
            Array<TextureAtlas.AtlasRegion> vasijaFrames = new Array<TextureAtlas.AtlasRegion>();
            vasijaFrames.add(atlas.findRegion(Constants.VASIJA1));
            vasijaFrames.add(atlas.findRegion(Constants.VASIJA2));
            vasija=new Animation(Constants.WALK_LOOP_DURATION,vasijaFrames,Animation.PlayMode.LOOP);

            enemigos_jackie= new Animation[]{bootdemon,calavera,demonio,firedemon,futbolista,icedemon,
                    luchador1,luchador2,pajaro,pincho1,pincho2,snowman,tazon,tiger,tucan,vasija};
        }
    }

    public class ItemAssets{
        public final TextureAtlas.AtlasRegion llave;

        public final TextureAtlas.AtlasRegion manzana;
        public final TextureAtlas.AtlasRegion melon;
        public final TextureAtlas.AtlasRegion pineaple;
        public final TextureAtlas.AtlasRegion platano;
        public final TextureRegion[] regionsAI;

        public final TextureAtlas.AtlasRegion cuadro_mappy;
        public final TextureAtlas.AtlasRegion pc_mappy;
        public final TextureAtlas.AtlasRegion radio_mappy;
        public final TextureAtlas.AtlasRegion security_mappy;
        public final TextureAtlas.AtlasRegion tv_mappy;
        public final TextureRegion[] regionsM;

        public final TextureAtlas.AtlasRegion campana;
        public final TextureAtlas.AtlasRegion cuenco;
        public final TextureAtlas.AtlasRegion esfera;
        public final TextureAtlas.AtlasRegion pelota;
        public final TextureAtlas.AtlasRegion snowball;
        public final TextureRegion[] regionsJ;

        public ItemAssets(TextureAtlas atlas){
            llave=atlas.findRegion(Constants.LLAVE);

            //adventure island
            manzana=atlas.findRegion(Constants.MANZANA);
            melon=atlas.findRegion(Constants.MELON);
            pineaple=atlas.findRegion(Constants.PINEAPLE);
            platano=atlas.findRegion(Constants.PLATANO);

            regionsAI=new TextureRegion[]{manzana,melon,pineaple,platano};

            //mappy
            cuadro_mappy=atlas.findRegion(Constants.CUADRO_MAPPY);
            pc_mappy=atlas.findRegion(Constants.PC_MAPPY);
            radio_mappy=atlas.findRegion(Constants.RADIO_MAPPY);
            security_mappy=atlas.findRegion(Constants.SECURITY_MAPPY);
            tv_mappy=atlas.findRegion(Constants.TV_MAPPY);

            regionsM=new TextureRegion[]{cuadro_mappy,pc_mappy,radio_mappy,security_mappy,tv_mappy};

            //jackie
            campana=atlas.findRegion(Constants.CAMPANA);
            cuenco=atlas.findRegion(Constants.CUENCO);
            pelota=atlas.findRegion(Constants.PELOTA);
            esfera=atlas.findRegion(Constants.ESFERA);
            snowball=atlas.findRegion(Constants.SNOWBALL);

            regionsJ=new TextureRegion[]{campana,cuenco,pelota,esfera,snowball};

        }
    }

    public class AtaqueAssets{

        public final TextureAtlas.AtlasRegion hacha;
        public final Animation hachaThrow;
        public final TextureAtlas.AtlasRegion fireball;
        public final TextureAtlas.AtlasRegion hayujen;
        public final TextureAtlas.AtlasRegion flecha;
        public final Animation fire;
        public final Animation icing;
        public final Animation nunchaku;


        public AtaqueAssets(TextureAtlas atlas){
            hacha=atlas.findRegion(Constants.MARTILLO1);
            fireball=atlas.findRegion(Constants.FIREBALL);
            hayujen=atlas.findRegion(Constants.HAYUKEN);
            flecha=atlas.findRegion(Constants.FLECHA);

            Array<TextureAtlas.AtlasRegion> hacha_throwFrames = new Array<TextureAtlas.AtlasRegion>();
            hacha_throwFrames.add(atlas.findRegion(Constants.MARTILLO1));
            hacha_throwFrames.add(atlas.findRegion(Constants.MARTILLO2));
            hacha_throwFrames.add(atlas.findRegion(Constants.MARTILLO3));
            hacha_throwFrames.add(atlas.findRegion(Constants.MARTILLO4));
            hachaThrow=new Animation(Constants.WALK_LOOP_DURATION,hacha_throwFrames,Animation.PlayMode.LOOP);

            Array<TextureAtlas.AtlasRegion> fire_throwFrames = new Array<TextureAtlas.AtlasRegion>();
            fire_throwFrames.add(atlas.findRegion(Constants.FIREBALL_JACKIE1));
            fire_throwFrames.add(atlas.findRegion(Constants.FIREBALL_JACKIE2));
            fire=new Animation(Constants.WALK_LOOP_DURATION,fire_throwFrames,Animation.PlayMode.LOOP);

            Array<TextureAtlas.AtlasRegion> icing_throwFrames = new Array<TextureAtlas.AtlasRegion>();
            icing_throwFrames.add(atlas.findRegion(Constants.ICING1));
            icing_throwFrames.add(atlas.findRegion(Constants.ICING2));
            icing=new Animation(Constants.WALK_LOOP_DURATION,icing_throwFrames,Animation.PlayMode.LOOP);

            Array<TextureAtlas.AtlasRegion> nunchaku_throwFrames = new Array<TextureAtlas.AtlasRegion>();
            nunchaku_throwFrames.add(atlas.findRegion(Constants.NUNCHAKU1));
            nunchaku_throwFrames.add(atlas.findRegion(Constants.NUNCHAKU2));
            nunchaku=new Animation(Constants.WALK_LOOP_DURATION,nunchaku_throwFrames,Animation.PlayMode.LOOP);
        }
    }

    public class OnscreenControlsAssets {

        public final TextureAtlas.AtlasRegion moveRight;
        public final TextureAtlas.AtlasRegion moveLeft;
        public final TextureAtlas.AtlasRegion down;
        public final TextureAtlas.AtlasRegion shoot;
        public final TextureAtlas.AtlasRegion jump;
        public final TextureAtlas.AtlasRegion ok;
        public final TextureAtlas.AtlasRegion music_on;
        public final TextureAtlas.AtlasRegion music_off;
        public final TextureAtlas.AtlasRegion easy;
        public final TextureAtlas.AtlasRegion medium;
        public final TextureAtlas.AtlasRegion hard;

        public OnscreenControlsAssets(TextureAtlas atlas) {
            moveRight = atlas.findRegion(Constants.MOVE_RIGHT_BUTTON);
            moveLeft = atlas.findRegion(Constants.MOVE_LEFT_BUTTON);
            shoot = atlas.findRegion(Constants.SHOOT_BUTTON);
            down = atlas.findRegion(Constants.DOWN_BUTTON);
            jump = atlas.findRegion(Constants.JUMP_BUTTON);
            ok = atlas.findRegion(Constants.OK_BUTTON);
            music_on = atlas.findRegion(Constants.MUSIC_ON_BUTTON);
            music_off = atlas.findRegion(Constants.MUSIC_OFF_BUTTON);
            easy = atlas.findRegion(Constants.DIFFICULTY_EASY);
            medium = atlas.findRegion(Constants.DIFFICULTY_MEDIUM);
            hard = atlas.findRegion(Constants.DIFFICULTY_HARD);
        }

    }
}
