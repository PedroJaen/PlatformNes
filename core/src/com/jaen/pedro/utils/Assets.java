package com.jaen.pedro.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
        /*
        public final Animation damage;
        public final TextureAtlas.AtlasRegion disparo;
        */


        public HeroeAssets(TextureAtlas atlas){
            Array<TextureAtlas.AtlasRegion> andarFrames = new Array<TextureAtlas.AtlasRegion>();
            andarFrames.add(atlas.findRegion("anda1"));
            andarFrames.add(atlas.findRegion("anda2"));
            andarFrames.add(atlas.findRegion("anda3"));
            andarFrames.add(atlas.findRegion("anda4"));
            andarFrames.add(atlas.findRegion("anda5"));
            andarFrames.add(atlas.findRegion("anda6"));
            andar=new Animation(Constants.WALK_LOOP_DURATION,andarFrames,Animation.PlayMode.LOOP);

            Array<TextureAtlas.AtlasRegion> saltaFrames = new Array<TextureAtlas.AtlasRegion>();
            saltaFrames.add(atlas.findRegion("salta1"));
            saltaFrames.add(atlas.findRegion("salta2"));
            saltaFrames.add(atlas.findRegion("salta3"));
            salta=new Animation(Constants.WALK_LOOP_DURATION,saltaFrames,Animation.PlayMode.LOOP);

            stand=atlas.findRegion("anda1");

            /*
            Array<TextureAtlas.AtlasRegion> damageFrames = new Array<TextureAtlas.AtlasRegion>();
            damageFrames.add(atlas.findRegion("damage1"));
            damageFrames.add(atlas.findRegion("damage2"));
            damage=new Animation(Constants.WALK_LOOP_DURATION,damageFrames,Animation.PlayMode.LOOP);

            disparo=atlas.findRegion("disparo");
            */

        }
    }

    public class EnemigoAssets{
        public final Animation caracol;
        /*
        public final Animation azul;
        public final Animation cerdo;
        public final Animation murcielago;
        public final Animation rojo;
        public final TextureAtlas.AtlasRegion rojoDisparo;
        */

        public EnemigoAssets(TextureAtlas atlas){

            Array<TextureAtlas.AtlasRegion> caracolFrames = new Array<TextureAtlas.AtlasRegion>();
            caracolFrames.add(atlas.findRegion("caracol1"));
            caracolFrames.add(atlas.findRegion("caracol2"));
            caracol=new Animation(Constants.WALK_LOOP_DURATION,caracolFrames,Animation.PlayMode.LOOP);

            /*
            Array<TextureAtlas.AtlasRegion> azulFrames = new Array<TextureAtlas.AtlasRegion>();
            azulFrames.add(atlas.findRegion("azul1"));
            azulFrames.add(atlas.findRegion("azul2"));
            azulFrames.add(atlas.findRegion("azul3"));
            azul=new Animation(Constants.WALK_LOOP_DURATION,azulFrames,Animation.PlayMode.LOOP);

            Array<TextureAtlas.AtlasRegion> cerdoFrames = new Array<TextureAtlas.AtlasRegion>();
            cerdoFrames.add(atlas.findRegion("cerdo1"));
            cerdoFrames.add(atlas.findRegion("cerdo2"));
            cerdo=new Animation(Constants.WALK_LOOP_DURATION,cerdoFrames,Animation.PlayMode.LOOP);

            Array<TextureAtlas.AtlasRegion> murcielagoFrames = new Array<TextureAtlas.AtlasRegion>();
            murcielagoFrames.add(atlas.findRegion("murcielago1"));
            murcielagoFrames.add(atlas.findRegion("murcielago2"));
            murcielago=new Animation(Constants.WALK_LOOP_DURATION,murcielagoFrames,Animation.PlayMode.LOOP);

            Array<TextureAtlas.AtlasRegion> rojoFrames = new Array<TextureAtlas.AtlasRegion>();
            rojoFrames.add(atlas.findRegion("rojo1"));
            rojoFrames.add(atlas.findRegion("rojo2"));
            rojoFrames.add(atlas.findRegion("rojo3"));
            rojo=new Animation(Constants.WALK_LOOP_DURATION,rojoFrames,Animation.PlayMode.LOOP);

            rojoDisparo=atlas.findRegion("rojo5");
            */

        }
    }

    public class ItemAssets{
        public final Animation fuego;
        public final TextureAtlas.AtlasRegion llave;
        /*
        public final TextureAtlas.AtlasRegion anillo;
        public final TextureAtlas.AtlasRegion corazon1;
        public final TextureAtlas.AtlasRegion corazon2;
        public final TextureAtlas.AtlasRegion flor1;
        public final TextureAtlas.AtlasRegion flor2;
        public final TextureAtlas.AtlasRegion leche;
        public final TextureAtlas.AtlasRegion leche2;
        public final TextureAtlas.AtlasRegion manzana;
        public final TextureAtlas.AtlasRegion melon;
        public final TextureAtlas.AtlasRegion piedra1;
        public final TextureAtlas.AtlasRegion piedra2;
        public final TextureAtlas.AtlasRegion piedra3;
        public final TextureAtlas.AtlasRegion piedra4;
        public final TextureAtlas.AtlasRegion piedra5;
        public final TextureAtlas.AtlasRegion piedra6;
        public final TextureAtlas.AtlasRegion piedra7;
        public final TextureAtlas.AtlasRegion piedra8;
        public final TextureAtlas.AtlasRegion piedra9;
        public final TextureAtlas.AtlasRegion pineaple;
        public final TextureAtlas.AtlasRegion platano;
        public final TextureAtlas.AtlasRegion vasija;
        */

        public ItemAssets(TextureAtlas atlas){
            Array<TextureAtlas.AtlasRegion> fuegoFrames = new Array<TextureAtlas.AtlasRegion>();
            fuegoFrames.add(atlas.findRegion("fuego1"));
            fuegoFrames.add(atlas.findRegion("fuego2"));
            fuego=new Animation(Constants.WALK_LOOP_DURATION,fuegoFrames,Animation.PlayMode.LOOP);

            llave=atlas.findRegion("llave");

            /*
            anillo=atlas.findRegion("anillo");
            corazon1=atlas.findRegion("corazon");
            corazon2=atlas.findRegion("corazon2");
            flor1=atlas.findRegion("flor1");
            flor2=atlas.findRegion("flor2");
            leche=atlas.findRegion("leche");
            leche2=atlas.findRegion("leche2");
            manzana=atlas.findRegion("manzana");
            melon=atlas.findRegion("melon");
            piedra1=atlas.findRegion("piedra1");
            piedra2=atlas.findRegion("piedra2");
            piedra3=atlas.findRegion("piedra3");
            piedra4=atlas.findRegion("piedra4");
            piedra5=atlas.findRegion("piedra5");
            piedra6=atlas.findRegion("piedra6");
            piedra7=atlas.findRegion("piedra7");
            piedra8=atlas.findRegion("piedra8");
            piedra9=atlas.findRegion("piedra9");
            pineaple=atlas.findRegion("pineaple");
            platano=atlas.findRegion("platano");
            vasija=atlas.findRegion("vasija");
            */
        }
    }

    public class AtaqueAssets{

        public final TextureAtlas.AtlasRegion hacha;
        public final Animation hachaThrow;
        /*
        public final Animation explosion;
        public final TextureAtlas.AtlasRegion fireball;
        */
        public AtaqueAssets(TextureAtlas atlas){
            hacha=atlas.findRegion("martillo2");

            Array<TextureAtlas.AtlasRegion> hacha_throwFrames = new Array<TextureAtlas.AtlasRegion>();
            hacha_throwFrames.add(atlas.findRegion("martillo1"));
            hacha_throwFrames.add(atlas.findRegion("martillo2"));
            hacha_throwFrames.add(atlas.findRegion("martillo3"));
            hacha_throwFrames.add(atlas.findRegion("martillo4"));
            hachaThrow=new Animation(Constants.WALK_LOOP_DURATION,hacha_throwFrames,Animation.PlayMode.LOOP);
            /*
            Array<TextureAtlas.AtlasRegion> explosionFrames = new Array<TextureAtlas.AtlasRegion>();
            explosionFrames.add(atlas.findRegion("explosion1"));
            explosionFrames.add(atlas.findRegion("explosion2"));
            explosionFrames.add(atlas.findRegion("explosion3"));
            explosionFrames.add(atlas.findRegion("explosion4"));
            explosionFrames.add(atlas.findRegion("explosion5"));
            explosion=new Animation(Constants.WALK_LOOP_DURATION,explosionFrames, Animation.PlayMode.LOOP);

            fireball=atlas.findRegion("fireball");
            */
        }
    }

    public class OnscreenControlsAssets {

        public final TextureAtlas.AtlasRegion moveRight;
        public final TextureAtlas.AtlasRegion moveLeft;
        public final TextureAtlas.AtlasRegion shoot;
        public final TextureAtlas.AtlasRegion jump;
        public final TextureAtlas.AtlasRegion music_on;
        public final TextureAtlas.AtlasRegion music_off;

        public OnscreenControlsAssets(TextureAtlas atlas) {
            moveRight = atlas.findRegion(Constants.MOVE_RIGHT_BUTTON);
            moveLeft = atlas.findRegion(Constants.MOVE_LEFT_BUTTON);
            shoot = atlas.findRegion(Constants.SHOOT_BUTTON);
            jump = atlas.findRegion(Constants.JUMP_BUTTON);
            music_on = atlas.findRegion(Constants.MUSIC_ON_BUTTON);
            music_off = atlas.findRegion(Constants.MUSIC_OFF_BUTTON);
        }

    }
}
