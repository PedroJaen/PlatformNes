package com.jaen.pedro.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.jaen.pedro.utils.Assets;
import com.jaen.pedro.utils.Utils;

public class Fruit {
    TiledMap map;
    Rectangle rectangle;
    private int lvlCounter;
    TextureRegion region;

    public Fruit(TiledMap map, Rectangle rectangle,int lvlCounter) {
        this.map = map;
        this.rectangle = rectangle;
        this.lvlCounter=lvlCounter;

        region=getFruit();
    }

    private TextureRegion getFruit() {
        TextureRegion[] regions=null;

        switch(lvlCounter){
            case 0:
                regions=Assets.instance.itemAssets.regionsAI;
                break;
            case 1:
                regions=Assets.instance.itemAssets.regionsM;
                break;
        }

        int posicion=(int)(Math.random()*regions.length);

        return regions[posicion];
    }

    public void render(SpriteBatch batch){
        Utils.drawTextureRegion(batch,region,rectangle.getX(),rectangle.getY());
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

}
