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

            //arrays
            stands=new TextureAtlas.AtlasRegion[] {stand,stand_mappy};
        }
    }

    public class EnemigoAssets{
        public final Animation caracol;
        public final Animation azul;
        public final TextureAtlas.AtlasRegion azulDisparo;
        public final Animation cerdo;
        public final Animation murcielago;
        public final Animation rojo;
        public final TextureAtlas.AtlasRegion rojoDisparo;
        public final Animation[] enemigos;

        public final Animation rojo_mappy;
        public final Animation rosa_mappy;
        public final Animation[] enemigos_mappy;

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
            azulDisparo=atlas.findRegion(Constants.AZUL4);
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
            rojoDisparo=atlas.findRegion(Constants.ROJO4);

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
        }
    }

    public class AtaqueAssets{

        public final TextureAtlas.AtlasRegion hacha;
        public final Animation hachaThrow;
        public final TextureAtlas.AtlasRegion fireball;

        public AtaqueAssets(TextureAtlas atlas){
            hacha=atlas.findRegion(Constants.MARTILLO1);

            Array<TextureAtlas.AtlasRegion> hacha_throwFrames = new Array<TextureAtlas.AtlasRegion>();
            hacha_throwFrames.add(atlas.findRegion(Constants.MARTILLO1));
            hacha_throwFrames.add(atlas.findRegion(Constants.MARTILLO2));
            hacha_throwFrames.add(atlas.findRegion(Constants.MARTILLO3));
            hacha_throwFrames.add(atlas.findRegion(Constants.MARTILLO4));
            hachaThrow=new Animation(Constants.WALK_LOOP_DURATION,hacha_throwFrames,Animation.PlayMode.LOOP);

            fireball=atlas.findRegion(Constants.FIREBALL);
        }
    }

    public class OnscreenControlsAssets {

        public final TextureAtlas.AtlasRegion moveRight;
        public final TextureAtlas.AtlasRegion moveLeft;
        public final TextureAtlas.AtlasRegion shoot;
        public final TextureAtlas.AtlasRegion jump;
        public final TextureAtlas.AtlasRegion music_on;
        public final TextureAtlas.AtlasRegion music_off;
        public final TextureAtlas.AtlasRegion easy;
        public final TextureAtlas.AtlasRegion medium;
        public final TextureAtlas.AtlasRegion hard;

        public OnscreenControlsAssets(TextureAtlas atlas) {
            moveRight = atlas.findRegion(Constants.MOVE_RIGHT_BUTTON);
            moveLeft = atlas.findRegion(Constants.MOVE_LEFT_BUTTON);
            shoot = atlas.findRegion(Constants.SHOOT_BUTTON);
            jump = atlas.findRegion(Constants.JUMP_BUTTON);
            music_on = atlas.findRegion(Constants.MUSIC_ON_BUTTON);
            music_off = atlas.findRegion(Constants.MUSIC_OFF_BUTTON);
            easy = atlas.findRegion(Constants.DIFFICULTY_EASY);
            medium = atlas.findRegion(Constants.DIFFICULTY_MEDIUM);
            hard = atlas.findRegion(Constants.DIFFICULTY_HARD);
        }

    }
}
