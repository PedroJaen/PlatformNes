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
    TextureRegion[] regions={Assets.instance.itemAssets.manzana,
            Assets.instance.itemAssets.melon,
            Assets.instance.itemAssets.pineaple,
            Assets.instance.itemAssets.platano
    };
    TextureRegion region;

    public Fruit(TiledMap map, Rectangle rectangle) {
        this.map = map;
        this.rectangle = rectangle;

        region=getFruit();
    }

    private TextureRegion getFruit() {
        int posicion=(int)(Math.random()*4);
        return regions[posicion];
    }

    public void render(SpriteBatch batch){
        Utils.drawTextureRegion(batch,region,rectangle.getX(),rectangle.getY());
    }
}
